package org.example;

public class HiloCliente  implements Runnable{

    private Cuenta cuenta;
    private double cantidad; //la cantidad de saldo del deposito y retiro

    //Constructor
    public HiloCliente(Cuenta cuenta, double cantidad) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        if (cantidad>0){
            cuenta.depositar(cantidad);
            System.out.println("Depósito: "+ cantidad+ "€");

        }else{
            cuenta.retirar(-cantidad); //cantidad negativa para el retiro
            System.out.println("Retiro: "+ (-cantidad)+"€");


        }

    }
}
