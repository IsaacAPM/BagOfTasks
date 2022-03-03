package mx.itam.packages.bagoftasks.interfaces;

import mx.itam.packages.bagoftasks.serializableobjects.Task;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DataMining extends Remote{
    public Task executeDataTask(Task task) throws RemoteException;
}
