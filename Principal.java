public class Principal {

    public static void main( String args[] ) {

        SubastaVista vista;
        SubastaControlador controlador;
        SubastaModelo modelo;

        vista = new SubastaVista();
        modelo = new SubastaModelo();
        controlador = new SubastaControlador( vista, modelo );

        vista.asignarActionListener( controlador );
        vista.asignarListSelectionListener( controlador );
    }
}
