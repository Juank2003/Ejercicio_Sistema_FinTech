package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;    //se usan para implementar bloqueos en los hilos
public class Cuenta {
    private double saldo;
    private Lock lock = new ReentrantLock();    //sincronizar las operaciones de depósito y retiro de fondos para evitar problemas de concurrencia.

    public Cuenta(double saldoInicial){ //inicializar el saldo de la cuenta cuando se crea una nueva instancia
        this.saldo= saldoInicial;
    }

    // Creamos los metodos depositar y retirar

    /*bloqueo (lock.lock()) para asegurar que solo un hilo a la vez pueda ejecutar la operación.
    Se incrementa el saldo de la cuenta en la cantidad especificada.
    Se libera el bloqueo (lock.unlock()) en un bloque finally.*/
    public void depositar (double cantidad){
        lock.lock();
        try {
            saldo +=cantidad;
        }finally {
            lock.unlock();
        }
    }

    public void retirar(double cantidad) {  //funciona igual que depositar
        lock.lock();
        try {
            saldo -= cantidad;
        } finally {
            lock.unlock();
        }
    }

    // Obtener el saldo final

    public double getSaldo(){
    return saldo;
    }


}
