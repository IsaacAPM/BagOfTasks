package mx.itam.packages.bagoftasks.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Deployer {

    public static void main(String[] args) {
        System.setProperty("java.security.policy",
                "src/mx/itam/packages/bagoftasks/server/server.policy");

        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {

            String serverAddres = "localhost";
            System.setProperty("java.rmi.server.hostname",serverAddres);

            LocateRegistry.createRegistry(1099);

            Registry registry = LocateRegistry.getRegistry();

            SlaveNode slaveImg = new SlaveNode("Imagenes",registry);
            SlaveNode slaveMin = new SlaveNode("Mineria",registry);
            SlaveNode slaveBio = new SlaveNode("Bioinformatica",registry);

            slaveImg.despliegue();
            slaveMin.despliegue();
            slaveBio.despliegue();


        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
