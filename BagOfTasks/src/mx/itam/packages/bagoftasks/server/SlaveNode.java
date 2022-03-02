package mx.itam.packages.bagoftasks.server;

import mx.itam.packages.bagoftasks.interfaces.Bioinformatics;
import mx.itam.packages.bagoftasks.interfaces.DataMining;
import mx.itam.packages.bagoftasks.interfaces.ImageProcessing;
import mx.itam.packages.bagoftasks.serializableobjects.Task;

public class SlaveNode implements Bioinformatics, DataMining, ImageProcessing {

    public Task executeBioTask(Task task){
        try {
            Thread.sleep(task.getLength());
            task.setOutput("Tarea de bioinformatica terminada");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return task;
    }

    public Task executeDataTask(Task task){
        try {
            Thread.sleep(task.getLength());
            task.setOutput("Tarea de minerìa terminada");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return task;
    }

    public Task executeImageTask(Task task){
        try {
            Thread.sleep(task.getLength());
            task.setOutput("Tarea de procesamiento de imagenes terminada");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return task;
    }

}
