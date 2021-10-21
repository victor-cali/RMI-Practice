package my.subasta;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;

import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.rmi.RemoteException;


public class SubastaControlador implements ActionListener, ListSelectionListener {

    SubastaVista vista;
    InterfazSubasta modelo;
    Hashtable <String,String> listaConPrecios;

    public SubastaControlador( SubastaVista v, InterfazSubasta m ) {

        vista = v;
        modelo = m;

    }

    public void actionPerformed( ActionEvent evento ) {

        String usuario;
        String producto;
        float monto;

        System.out.println( "<<" + evento.getActionCommand() + ">>" );

        if (evento.getActionCommand().equals("Salir")) {

            System.exit(1);

        } else if( evento.getActionCommand().equals("Conectar") ) {

            usuario = vista.getUsuario();
            System.out.println( "Registrarse como usuario: " + usuario );
            try {
                modelo.registraUsuario( usuario );
            } catch (RemoteException e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }

        } else if( evento.getActionCommand().equals("Poner a la venta") ) {

            usuario = vista.getUsuario();
            producto = vista.getProducto();
            monto = vista.getPrecioInicial();
            System.out.println( "Haciendo oferta del producto: " + producto );
            try {
                modelo.agregaProductoALaVenta( usuario, producto, monto );
            } catch (RemoteException e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }

        } else if( evento.getActionCommand().equals("Obtener lista") ) {

            Vector<InformacionProducto> lista;
            try {
                lista = modelo.obtieneCatalogo();
                Enumeration <InformacionProducto> it;
                InformacionProducto info;
                listaConPrecios = new Hashtable<String,String>();
                vista.reinicializaListaProductos();
                it = lista.elements();
                while (it.hasMoreElements()) {
                    info = (InformacionProducto) it.nextElement();            
                    listaConPrecios.put( info.producto,String.valueOf(info.precioActual) );
                    vista.agregaProducto( info.producto );
                }
            } catch (RemoteException e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }
        } else if( evento.getActionCommand().equals("Ofrecer") ) {

            producto = vista.getProductoSeleccionado();
            monto = vista.getMontoOfrecido();
            usuario = vista.getUsuario();
            try {
                modelo.agregaOferta( usuario, producto, monto );
            } catch (RemoteException e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }
        }
    }

    public void valueChanged(ListSelectionEvent e) {
        try {
            if (e.getValueIsAdjusting() == false) {
                @SuppressWarnings("unchecked") 
                JList <String> lista = (JList<String>)e.getSource();
                String item = (String)lista.getSelectedValue();
                if (item != null) {
                    System.out.println(item);
                    String precio = (String)listaConPrecios.get(item);
                    vista.desplegarPrecio( precio );
                }
            }
        } catch (ClassCastException exception) {

            System.out.println(exception.getMessage());
        }
    }   
}
