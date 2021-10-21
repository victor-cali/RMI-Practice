package my.subasta;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Hashtable;
import java.util.Vector;


public class SubastaModelo implements InterfazSubasta{

    Hashtable<String,String> usuarios;
    Hashtable<String,InformacionProducto> productos;
    Hashtable<String,InformacionOferta> ofertas;

    public SubastaModelo() throws RemoteException {

        super();
        usuarios = new Hashtable<String,String>();
        productos = new Hashtable<String,InformacionProducto>();
        ofertas = new Hashtable<String,InformacionOferta>();
    }

    public boolean registraUsuario( String nombre ) throws RemoteException {

        if( !usuarios.containsKey(nombre) ) {

            System.out.println( "Agregando un nuevo usuario: " + nombre );
            usuarios.put( nombre, nombre );
            return true;

        } else

            return false;
    } 

    public boolean agregaProductoALaVenta(
        String vendedor, 
        String producto,
        float precioInicial 
    ) throws RemoteException {
        if( !productos.containsKey(producto) ) {

            System.out.println( "Agregando un nuevo producto: " + producto );
            productos.put(
                producto, 
                new InformacionProducto(vendedor, producto, precioInicial)
            );
            return true;

        } else

            return false;
    }

    public boolean agregaOferta(
        String comprador, 
        String producto, 
        float monto 
    ) throws RemoteException {

        if( productos.containsKey(producto) ) {

            InformacionProducto infoProd;
            infoProd = (InformacionProducto) productos.get(producto);

            if( infoProd.actualizaPrecio(monto) ) {

                ofertas.put(
                    producto + comprador,
                    new InformacionOferta( comprador,producto,monto) 
                );
                return true;

            } else

            return false;

        } else

            return false;
    }

    public Vector<InformacionProducto> obtieneCatalogo() throws RemoteException {
        Vector<InformacionProducto> resultado;
        resultado = new Vector<InformacionProducto>( productos.values() );
        return resultado;
    }

    public static void main(String args[]) {
	
        try {

            SubastaModelo obj = new SubastaModelo();
            InterfazSubasta stub = (InterfazSubasta) UnicastRemoteObject.exportObject(obj, 0);
    
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("SubastaModelo", stub);
    
            System.err.println("Server ready");

        } catch (Exception e) {

            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();

        }
    }
}
