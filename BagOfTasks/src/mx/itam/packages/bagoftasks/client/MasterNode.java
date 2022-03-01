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
            Task[] BioTasks = new Task[15];


        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

}
