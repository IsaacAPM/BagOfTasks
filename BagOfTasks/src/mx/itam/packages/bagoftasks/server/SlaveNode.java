package mx.itam.packages.bagoftasks.server;

import mx.itam.packages.bagoftasks.interfaces.Bioinformatics;
import mx.itam.packages.bagoftasks.interfaces.DataMining;
import mx.itam.packages.bagoftasks.interfaces.ImageProcessing;
import mx.itam.packages.bagoftasks.serializableobjects.Task;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Remote;

public class SlaveNode implements Bioinformatics, DataMining, ImageProcessing {
    private String tipoServicio;
    private Registry registry;

    public SlaveNode(){
        super();
    }

    public SlaveNode(String tipoServicio, Registry registry) throws RemoteException{
        this.tipoServicio = tipoServicio;
        this.registry = registry;
    }

    public void despliegue(){

        try {

            SlaveNode engine = new SlaveNode();

            if(this.tipoServicio.equals("Imagenes")){
                ImageProcessing stub = (ImageProcessing) UnicastRemoteObject.exportObject(engine,0);
                this.registry.rebind(this.tipoServicio, stub);
                System.out.println("Servicio de imagenes desplegado");
            }else if(this.tipoServicio.equals("Mineria")){
                DataMining stub = (DataMining) UnicastRemoteObject.exportObject(engine,0);
                this.registry.rebind(this.tipoServicio, stub);
                System.out.println("Servicio de mineria desplegado");
            }else if(this.tipoServicio.equals("Bioinformatica")){
                Bioinformatics stub = (Bioinformatics) UnicastRemoteObject.exportObject(engine,0);
                this.registry.rebind(this.tipoServicio, stub);
                System.out.println("Servicio de bioinformatica desplegado");
            }else {
                System.out.println("Tipo de servicio invàlido");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Task executeBioTask(Task task) throws RemoteException{
        try {
            Thread.sleep(task.getLength());
            task.setOutput("Tarea de bioinformatica terminada");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return task;
    }

    public Task executeDataTask(Task task) throws RemoteException{
        try {
            Thread.sleep(task.getLength());
            task.setOutput("Tarea de minerìa terminada");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return task;
    }

    public Task executeImageTask(Task task) throws RemoteException{
        try {
            Thread.sleep(task.getLength());
            task.setOutput("Tarea de procesamiento de imagenes terminada");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return task;
    }

}
