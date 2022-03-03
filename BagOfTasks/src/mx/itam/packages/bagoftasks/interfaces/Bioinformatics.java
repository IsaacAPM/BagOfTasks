package mx.itam.packages.bagoftasks.interfaces;

import mx.itam.packages.bagoftasks.serializableobjects.Task;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Bioinformatics extends Remote{
    public Task executeBioTask(Task task) throws RemoteException;
}
