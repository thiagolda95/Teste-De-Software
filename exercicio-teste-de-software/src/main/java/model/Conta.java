package model;

import exception.SaqueMaiorQueSaldoException;

public class Conta {

    private double saldo;

    public void depositar(double valor){
        saldo+=valor;
    }

    public void sacar(double valor){
        if(this.saldo > 0){
            if(valor > this.saldo){
                throw new SaqueMaiorQueSaldoException("Valor não pode ser maior que o saldo atual");
            }
            else{
                this.saldo = this.saldo - valor;
                System.out.println("Seu saldo atual é: " + this.saldo);
            }
        }
        else{
            System.out.println("você não tem saldo");
        }
    }

    public void sacarLento(double valor){
        if(valor>getSaldo()){
            throw new SaqueMaiorQueSaldoException("Valor não pode ser maior que o saldo atual");
        }
        saldo-=valor;

        try {
            Thread.sleep(6* 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public double getSaldo(){
        return this.saldo;
    }
}
