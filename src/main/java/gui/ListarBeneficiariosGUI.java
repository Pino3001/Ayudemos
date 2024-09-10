package gui;

import datatypes.DtBeneficiario;
import interfaces.IControladorUsuario;

import javax.swing.*;
import java.util.List;

public class ListarBeneficiariosGUI extends JFrame {
    private JPanel background;
    private JPanel paneltitulo;
    private JPanel panelLista;
    private JList<String> listBeneficiarios;
    private JScrollPane scroll;
    private DefaultListModel<String> modeloLista;

    private IControladorUsuario controladorUsuario;

    public ListarBeneficiariosGUI(IControladorUsuario controladorUsuario) {
        this.controladorUsuario = controladorUsuario;
        modeloLista = new DefaultListModel<>();
        listBeneficiarios.setModel(modeloLista);

        cargarElementos();
    }

    private void createUIComponents() {
        this.background = new JPanel();
        setContentPane(background);
        setSize(450, 500);
    }

    // Funcion para cargar los elementos en la lista
    private void cargarElementos() {
        List<DtBeneficiario> beneficiarios = controladorUsuario.listarBeneficiarios();
        if (beneficiarios == null) {
            modeloLista.addElement("Muestro esto");
        } else {
            modeloLista.clear(); // Limpiar elementos existentes
            for (DtBeneficiario beneficiario : beneficiarios) {
                modeloLista.addElement(beneficiario.getNombre() + beneficiario.getMail() + beneficiario.getDireccion() + beneficiario.getBarrio().toString());
            }
        }
    }
}
