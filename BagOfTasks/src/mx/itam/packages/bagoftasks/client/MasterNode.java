package mx.itam.packages.bagoftasks.client;

import mx.itam.packages.bagoftasks.serializableobjects.Task;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MasterNode {

    public static void main(String[] args) {
        System.setProperty("java.security.policy",
                "src/mx/itam/packages/bagoftasks/client/client.policy");

        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        String name = "BagOfTasks";
        try {
            Registry registry = LocateRegistry.getRegistry("148.205.133.194");

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

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

}
