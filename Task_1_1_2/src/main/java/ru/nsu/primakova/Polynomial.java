package ru.nsu.primakova;
import static java.lang.Math.abs;

public class Polynomial {
    private final int length;
    public final int[] args;
    public Polynomial(int[] arr){
        this.length = arr.length;
        this.args = arr;
    }
    public Polynomial plus(Polynomial p){
        int n1 = p.length;
        int n2 = this.length;
        int n_max = Math.max(n1,n2);
        Polynomial p_new = new Polynomial(new int[n_max]);
        for(int i = 0; i < n_max; i++){
            if(i >= n1){
                p_new.args[i] = this.args[i];
                continue;
            }
            if(i >= n2){
                p_new.args[i] = p.args[i];
                continue;
            }
            p_new.args[i] = p.args[i] + this.args[i];
        }
        return p_new;
    }
    public Polynomial minus(Polynomial p){
        int n1 = p.length;
        int n2 = this.length;
        int n_max = Math.max(n1,n2);
        Polynomial p_new = new Polynomial(new int[n_max]);
        for(int i = 0; i < n_max; i++){
            if(i >= n1){
                p_new.args[i] = this.args[i];
                continue;
            }
            if(i >= n2){
                p_new.args[i] = ((-1)*p.args[i]);
                continue;
            }
            p_new.args[i] = this.args[i] - p.args[i];
        }
        return p_new;
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
    public boolean equals(Polynomial p){
        int n1 = p.length;
        int n2 = this.length;
        int n_max = Math.max(n1,n2);
        for(int i = 0; i < n_max; i++){
            if(i >= n1){
                if(this.args[i]!=0) {
                    return false;
                }
                continue;
            }
            if(i >= n2){
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