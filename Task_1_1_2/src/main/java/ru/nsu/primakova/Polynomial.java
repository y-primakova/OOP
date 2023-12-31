package ru.nsu.primakova;

import static java.lang.Math.abs;

public class Polynomial {
    private final int length;
    private final int[] args;
    
    public Polynomial(int[] arr) {
        this.length = arr.length;
        this.args = new int[arr.length];
        System.arraycopy(arr, 0, this.args, 0, arr.length);
    }

    private Polynomial plus_minus(Polynomial p1, Polynomial p2, int koef) {
        var max = Math.max(p1.length, p2.length);
        var p = new Polynomial(new int[max]);
        for (int i = 0; i < max; i++) {
            if (i >= p1.length) {
                p.args[i] = p2.args[i];
                continue;
            }
            if (i >= p2.length) {
                p.args[i] = koef * p1.args[i];
                continue;
            }
            p.args[i] = p2.args[i] + koef * p1.args[i];
        }
        return p;
    }

    public Polynomial plus(Polynomial p) {
        return plus_minus(p, this, 1);
    }

    public Polynomial minus(Polynomial p) {
        return plus_minus(p, this, -1);
    }

    public Polynomial times(Polynomial p0) {
        int n1 = p0.length;
        int n2 = this.length;
        var p = new Polynomial(new int[n1 + n2 - 1]);
        for (int i = 0; i < n1 + n2 - 1; i++) {
            p.args[i] = 0;
        }
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                p.args[i + j] += p0.args[i] * this.args[j];
            }
        }
        return p;
    }

    public int evaluate(int number) {
        int value = 0;
        int deg = 1;
        for (int j : this.args) {
            value += j * deg;
            deg *= number;
        }
        return value;
    }

    public Polynomial differentiate(int deg) {
        var p = new Polynomial(new int[this.length - deg]);
        for (int i = deg; i < this.length; i++) {
            p.args[i - deg] = this.args[i];
        }
        for (int d = deg; d > 0; d--) {
            for (int i = 0; i < this.length - deg; i++) {
                p.args[i] = p.args[i] * (i + d);
            }
        }
        return p;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var p = (Polynomial) obj;

        for (int i = 0; i < Math.max(p.length, this.length); i++) {
            if (i >= p.length) {
                if (this.args[i] != 0) {
                    return false;
                }
                continue;
            }
            if (i >= this.length) {
                if (p.args[i] != 0) {
                    return false;
                }
                continue;
            }
            if (p.args[i] != this.args[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        boolean isEmpty = true;
        for (int i = this.length - 1; i >= 0; i--) {
            if (this.args[i] == 0) {
                continue;
            }
            if (!isEmpty && this.args[i] > 0) {
                str.append(" + ");
            }
            if (this.args[i] < 0) {
                if (isEmpty) {
                    str.append("-");
                } else {
                    str.append(" - ");
                }
            }
            if (i == 0) {
                str.append(abs(this.args[0]));
                continue;
            }
            if (abs(this.args[i]) == 1) {
                str.append("x");
            } else {
                str.append(abs(this.args[i])).append("x");
            }

            if (i != 1) {
                str.append("^").append(i);
            }
            isEmpty = false;
        }
        if (isEmpty) {
            str = new StringBuilder("0");
        }
        return str.toString();
    }
}