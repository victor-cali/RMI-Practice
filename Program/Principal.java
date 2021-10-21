package my.subasta;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Principal {

    public static void main( String args[] ) {

        SubastaVista vista;
        SubastaControlador controlador;
        InterfazSubasta modelo;

        String host = (args.length < 1) ? null : args[0];

        try {
            
            Registry registry = LocateRegistry.getRegistry(host);
            InterfazSubasta stub = (InterfazSubasta) registry.lookup("SubastaModelo");

            vista = new SubastaVista();
            modelo = stub;
            controlador = new SubastaControlador( vista, modelo );
    
            vista.asignarActionListener( controlador );
            vista.asignarListSelectionListener( controlador );

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
        }
    }
}
