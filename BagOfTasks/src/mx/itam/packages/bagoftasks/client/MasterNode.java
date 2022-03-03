package mx.itam.packages.bagoftasks.client;

import mx.itam.packages.bagoftasks.interfaces.Bioinformatics;
import mx.itam.packages.bagoftasks.interfaces.DataMining;
import mx.itam.packages.bagoftasks.interfaces.ImageProcessing;
import mx.itam.packages.bagoftasks.serializableobjects.Task;
import mx.itam.packages.bagoftasks.server.SlaveNode;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MasterNode {

    public static void main(String[] args) {

        String name = "BagOfTasks";
        try {
            Registry registry = LocateRegistry.getRegistry("localhost");

            Task[] imageTasks = new Task[10];
            Task[] miningTasks = new Task[20];
            Task[] bioTasks = new Task[15];

            int j = 1;
            int k = 5;
            for(int i=0;i<10;i++){
                imageTasks[i] = new Task("T"+j, "Imagenes",k);
                j++;
                switch (k){
                    case 20: k = 30;
                        break;
                    case 30: k = 5;
                        break;
                    default: k = k + 5;
                        break;
                }
            }


            k = 5;
            for(int i=0;i<20;i++){
                miningTasks[i] = new Task("T"+j, "Mineria",k);
                j++;
                switch (k){
                    case 20: k = 30;
                        break;
                    case 30: k = 5;
                        break;
                    default: k = k + 5;
                        break;
                }
            }

            k = 5;
            for(int i=0;i<15;i++){
                bioTasks[i] = new Task("T"+j, "Bioinformatica",k);
                j++;
                switch (k){
                    case 20: k = 30;
                        break;
                    case 30: k = 5;
                        break;
                    default: k = k + 5;
                        break;
                }
            }


            MyThread bioNodo = new MyThread(registry,bioTasks);
            MyThread minNodo = new MyThread(registry,miningTasks);
            MyThread imgNodo = new MyThread(registry,imageTasks);

            long start = System.currentTimeMillis();

            bioNodo.start();
            minNodo.start();
            imgNodo.start();

            imgNodo.join();
            minNodo.join();
            bioNodo.join();



            long end = System.currentTimeMillis();
            System.out.println(end-start);

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class MyThread extends Thread{
        private Registry registry;
        private Task[] bag;

        public MyThread(Registry registry,Task[] bag){
            this.registry = registry;
            this.bag = bag;
        }

        public void run(){
            try {

                for(Task task:this.bag) {
                    if (task.getRequirementId().equals("Imagenes")) {
                        ImageProcessing slave = (ImageProcessing) this.registry.lookup(this.bag[0].getRequirementId());
                        slave.executeImageTask(task);
                    } else if (task.getRequirementId().equals("Mineria")) {
                        DataMining slave = (DataMining) this.registry.lookup(this.bag[0].getRequirementId());
                        slave.executeDataTask(task);
                    } else if (task.getRequirementId().equals("Bioinformatica")) {
                        Bioinformatics slave = (Bioinformatics) this.registry.lookup(this.bag[0].getRequirementId());
                        slave.executeBioTask(task);
                    } else {
                        System.out.println("Tipo de servicio invàlido");
                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
        }
    }

}


