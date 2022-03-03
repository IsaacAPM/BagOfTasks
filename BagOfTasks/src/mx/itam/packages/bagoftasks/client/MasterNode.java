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
        System.setProperty("java.security.policy",
                "src/mx/itam/packages/bagoftasks/client/client.policy");

        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        String name = "BagOfTasks";
        try {
            Registry registry = LocateRegistry.getRegistry("localhost");

            Task[] imageTasks = new Task[10];
            Task[] miningTasks = new Task[20];
            Task[] bioTasks = new Task[15];

            int i = 1;
            int j = 5;
            for (Task task:imageTasks) {
                task = new Task("T"+i, "Imagenes",j);
                i++;
                switch (j){
                    case 20: j = 30;
                        break;
                    case 30: j = 5;
                        break;
                    default: j = j + 5;
                        break;
                }
            }
            j = 5;
            for (Task task:miningTasks) {
                task = new Task("T"+i, "Mineria",j);
                i++;
                switch (j){
                    case 20: j = 30;
                        break;
                    case 30: j = 5;
                        break;
                    default: j = j + 5;
                        break;
                }
            }
            j = 5;
            for (Task task:bioTasks) {
                task = new Task("T"+i, "Bioinformatica",j);
                i++;
                switch (j){
                    case 20: j = 30;
                        break;
                    case 30: j = 5;
                        break;
                    default: j = j + 5;
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

            bioNodo.join();
            minNodo.join();
            imgNodo.join();

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
                SlaveNode slave = (SlaveNode) this.registry.lookup(this.bag[0].getRequirementId());

                for(Task task:this.bag) {
                    if (task.getRequirementId().equals("Imagenes")) {
                        slave.executeImageTask(task);
                    } else if (task.getRequirementId().equals("Mineria")) {
                        slave.executeDataTask(task);
                    } else if (task.getRequirementId().equals("Bioinformatica")) {
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


