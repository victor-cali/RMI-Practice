package my.subasta;

import java.rmi.*;
import java.util.Vector;

public interface InterfazSubasta extends Remote {

    public boolean registraUsuario( String nombre ) throws RemoteException;

    public boolean agregaProductoALaVenta(
        String vendedor, 
        String producto,
        float precioInicial 
    ) throws RemoteException;
    
    public boolean agregaOferta(
        String comprador, 
        String producto, 
        float monto 
    ) throws RemoteException;
    
    public Vector<InformacionProducto> obtieneCatalogo() throws RemoteException;

}
