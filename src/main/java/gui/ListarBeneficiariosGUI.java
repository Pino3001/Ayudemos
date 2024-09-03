package gui;

import datatypes.DtBeneficiario;
import interfaces.IAltaUsuario;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

public class ListarBeneficiariosGUI extends JFrame {
    private JPanel background;
    private JPanel paneltitulo;
    private JPanel panelLista;
    private JList<String> listBeneficiarios;
    private JScrollPane scroll;
    private DefaultListModel<String> modeloLista;

    private IAltaUsuario altaUsuario;

    public ListarBeneficiariosGUI(IAltaUsuario altaUsuario)  {
        this.altaUsuario = altaUsuario;
        modeloLista = new DefaultListModel<>();
        listBeneficiarios.setModel(modeloLista);

        cargarElementos();
    }

    private void createUIComponents() {
        this.background = new JPanel();
        setContentPane(background);
        setSize(500,600);
    }

    // Funcion para cargar los elementos en la lista
    private void cargarElementos() {
        List<DtBeneficiario> beneficiarios = altaUsuario.listarBeneficiarios();
        if(beneficiarios == null) {
            modeloLista.addElement("Muestro esto");
        }else {
            modeloLista.clear(); // Limpiar elementos existentes
             for (DtBeneficiario beneficiario : beneficiarios) {
               modeloLista.addElement(" "+beneficiario.getNombre() + " eMail: " + beneficiario.getMail() + " Direccion: " + beneficiario.getDireccion() + " Barrio: " + beneficiario.getBarrio().toString());
            }
        }
    }
}
