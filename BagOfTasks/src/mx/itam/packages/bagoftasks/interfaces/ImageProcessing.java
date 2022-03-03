package mx.itam.packages.bagoftasks.interfaces;

import mx.itam.packages.bagoftasks.serializableobjects.Task;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ImageProcessing extends Remote{
    public Task executeImageTask(Task task) throws RemoteException;
}
