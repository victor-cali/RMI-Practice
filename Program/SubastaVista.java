package my.subasta;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionListener;

public class SubastaVista {

    JFrame principal;
    JTextField usuario;
    JTextField producto;
    JTextField precioInicial;
    JTextField monto;
    DefaultComboBoxModel <String> productos;
    JLabel precioActual;
    JList <String> lista;
    JButton conectar;
    JButton salir;
    JButton ponerALaVenta;
    JButton obtenerLista;
    JButton ofrecer;

    public SubastaVista() {

        JFrame.setDefaultLookAndFeelDecorated( true );
        Container panel;

        principal = new JFrame( "Cliente Subasta" );
        panel = principal.getContentPane();
        panel.setLayout( new GridLayout(0,2) );
        usuario = new JTextField();
        panel.add( new JLabel("Nombre del usuario") );
        panel.add( usuario );

        conectar = new JButton( "Conectar" );
        salir = new JButton( "Salir" );
        panel.add( conectar );
        panel.add( salir );

        producto = new JTextField();
        precioInicial = new JTextField();
        panel.add( new JLabel("Producto a ofrecer") );
        panel.add( producto );
        panel.add( new JLabel("Precio inicial") );
        panel.add( precioInicial );

        ponerALaVenta = new JButton( "Poner a la venta" );
        panel.add( ponerALaVenta );
        panel.add( new JLabel() );

        productos = new DefaultComboBoxModel<String>();
        lista = new JList<String>( productos ); //data has type Object[]
        lista.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        lista.setLayoutOrientation( JList.VERTICAL );
        JScrollPane listaScroller = new JScrollPane( lista );
        listaScroller.setPreferredSize( new Dimension(250, 80) );
        obtenerLista = new JButton( "Obtener lista" );
        panel.add( obtenerLista );
        panel.add( listaScroller );

        precioActual = new JLabel();
        panel.add( new JLabel("Precio actual") );
        panel.add( precioActual );

        monto = new JTextField();
        ofrecer = new JButton( "Ofrecer" );
        panel.add( ofrecer );
        panel.add( monto );

        principal.setSize( 400, 400 );
        principal.setVisible( true );
    }

    public void asignarActionListener(ActionListener controlador) {
        conectar.addActionListener( controlador );
        salir.addActionListener( controlador );
        ponerALaVenta.addActionListener( controlador );
        obtenerLista.addActionListener( controlador );
        ofrecer.addActionListener( controlador );
    }

    public void asignarListSelectionListener( ListSelectionListener controlador ) {

	    lista.addListSelectionListener( controlador );

    }

    public String getUsuario() {

        return usuario.getText();
    }

    public String getProducto() {

        return producto.getText();
    }

    public float getPrecioInicial() {

        float resultado = 0.0f;

        try {

            resultado = Float.parseFloat( precioInicial.getText() );

        } catch( Exception e ) {

            System.out.println( "Hay problemas con el precio inicial" );
        }

        return resultado;
    }

    public void reinicializaListaProductos() {

        productos.removeAllElements();
    }

    public void agregaProducto( String prod ) {

        productos.addElement( prod );
    }
    public void desplegarPrecio( String precio ) {

        precioActual.setText( precio );
    }

    public float getMontoOfrecido() {

        float resultado = 0.0f;

        try {

            resultado = Float.parseFloat( monto.getText() );

        } catch( Exception e ) {

            System.out.println( "Hay problemas con el monto ofrecido" );
        }

        return resultado;
    }

    public String getProductoSeleccionado() {

        return (String)lista.getSelectedValue();
    }
}
