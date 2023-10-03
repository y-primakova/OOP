package ru.nsu.primakova;
import static java.lang.Math.abs;

public class Polynomial {
    private final int length;
    private final int[] args;
    public Polynomial(int[] arr){
        this.length = arr.length;
        this.args = arr;
    }
    private Polynomial plus_minus(Polynomial p1, Polynomial p2, int koef){
        int n1 = p1.length;
        int n2 = p2.length;
        int n_max = Math.max(n1,n2);
        var p_new = new Polynomial(new int[n_max]);
        for(int i = 0; i < n_max; i++){
            if(i >= n1){
                p_new.args[i] = p2.args[i];
                continue;
            }
            if(i >= n2){
                p_new.args[i] = koef*p1.args[i];
                continue;
            }
            p_new.args[i] = p2.args[i] + koef*p1.args[i];
        }
        return p_new;
    }
    public Polynomial plus(Polynomial p){
        return plus_minus(p, this, 1);
    }
    public Polynomial minus(Polynomial p){
        return plus_minus(p, this, -1);
    }
    public Polynomial times(Polynomial p){
        int n1 = p.length;
        int n2 = this.length;
        var p_new = new Polynomial(new int[n1 + n2 - 1]);
        for(int i = 0; i < n1 + n2 - 1; i++){
            p_new.args[i] = 0;
        }
        for(int i = 0; i < n1; i++){
            for(int j = 0; j < n2; j++){
                p_new.args[i+j] += p.args[i] * this.args[j];
            }
        }
        return p_new;
    }
    public int evaluate(int number){
        int value = 0;
        int deg = 1;
        for (int j : this.args) {
            value += j * deg;
            deg *= number;
        }
        return value;
    }
    public Polynomial differentiate(int deg){
        var p_new = new Polynomial(new int[this.length - deg]);
        for(int i = deg; i < this.length; i++){
            p_new.args[i - deg] = this.args[i];
        }
        for(int d = deg; d > 0; d--){
            for(int i = 0; i < this.length - deg; i++){
                p_new.args[i] = p_new.args[i]*(i+d);
            }
        }
        return p_new;
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

        for(int i = 0; i < Math.max(p.length,this.length); i++){
            if(i >= p.length){
                if(this.args[i]!=0) {
                    return false;
                }
                continue;
            }
            if(i >= this.length){
                if(p.args[i]!=0) {
                    return false;
                }
                continue;
            }
            if(p.args[i]!=this.args[i]){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String str = "";
        for(int i = this.length - 1; i >= 0; i--){
            if(this.args[i] == 0) continue;
            if (!str.isEmpty() && this.args[i]>0){
                str += " + ";
            }
            if (this.args[i]<0){
                if(str.isEmpty()){
                    str += "-";
                }
                else{
                    str += " - ";
                }
            }
            if(i==0){
                str += abs(this.args[0]);
                continue;
            }
            if(abs(this.args[i]) == 1){
                str += "x";
            }
            else{
                str += abs(this.args[i]) + "x";
            }

            if(i!=1){
                str += "^" + i;
            }
        }
        return str;
    }
}