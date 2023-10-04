package org.example;

public class Lanzador {
    public static void main(String[] args) {
    //Creamos una cuenta con 10.000 de saldo inicial y los numero de los depositos y retiros

        Cuenta cuenta = new Cuenta(10000);

        int numDepositos100 = 400;
        int numDepositos50 = 200;
        int numDepositos20 = 600;
        int numRetiros100 = 400;
        int numRetiros50 = 200;
        int numRetiros20 = 600;

        // Se calcula el número total de clientes o hilos que realizarán transacciones.
        int numClientes = numDepositos100 + numDepositos50 + numDepositos20 + numRetiros100 + numRetiros50 + numRetiros20;

        //Se crea un arreglo de objetos Thread llamado hilosClientes con un tamaño igual a numClientes

        Thread[] hilosClientes = new Thread[numClientes];

        //Asignamos valores positivos y negativos para los retiros y depositos
        for (int i = 0; i < numClientes; i++) {
            double cantidad;

            if (i < numDepositos100) {
                cantidad = 100;
            } else if (i < numDepositos100 + numDepositos50) {
                cantidad = 50;
            } else if (i < numDepositos100 + numDepositos50 + numDepositos20) {
                cantidad = 20;
            } else if (i < numDepositos100 + numDepositos50 + numDepositos20 + numRetiros100) {
                cantidad = -100; // Retiro de 100 euros
            } else if (i < numDepositos100 + numDepositos50 + numDepositos20 + numRetiros100 + numRetiros50) {
                cantidad = -50; // Retiro de 50 euros
            } else {
                cantidad = -20; // Retiro de 20 euros
            }

            HiloCliente cliente = new HiloCliente(cuenta, cantidad);
            hilosClientes[i] = new Thread(cliente);
            hilosClientes[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (Thread hilo : hilosClientes) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Registrar el saldo final por la terminal
        double saldoFinal = cuenta.obtenerSaldo();
        System.out.println("Saldo Final: " + saldoFinal);

        if (saldoFinal == 10000) {
            System.out.println("La simulación ha funcionado correctamente.");
        } else {
            System.out.println("La simulación ha fallado.");
        }


    }
}
