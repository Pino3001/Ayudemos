package gui;

import datatypes.DtBeneficiario;
import interfaces.IControladorUsuario;
import types.EstadoBeneficiario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListarBeneficiariosEstadoGUI extends JFrame {
    // Componentes generados por el archivo .form
    private JPanel background;
    private JComboBox<EstadoBeneficiario> estadoComboBox;
    private JButton listarButton;
    private JScrollPane scrollPane;
    private JList<String> listaBeneficiarios; // Cambiamos el tipo del JList para mostrar Strings
    private JPanel paneltitulo;
    private JScrollPane scroll;
    private JPanel panelLista;

    private IControladorUsuario controladorUsuarioService;

    public ListarBeneficiariosEstadoGUI(IControladorUsuario controladorUsuarioService) {
        this.controladorUsuarioService = controladorUsuarioService;

        // Configuración de la ventana usando el .form
        setContentPane(background);
        setTitle("Listar Beneficiarios por Estado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        // Cargar los barrios en el comboBox
        cargarEstados();

        // Acción del botón "Listar Beneficiarios"
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EstadoBeneficiario estadoSeleccionado = (EstadoBeneficiario) estadoComboBox.getSelectedItem();
                List<DtBeneficiario> beneficiarios = controladorUsuarioService.listarBeneficiariosPorEstado(estadoSeleccionado);

                // Convertir la lista de beneficiarios a un array de Strings para mostrar en el JList
                String[] nombresBeneficiarios = new String[beneficiarios.size()];
                for (int i = 0; i < beneficiarios.size(); i++) {
                    DtBeneficiario beneficiario = beneficiarios.get(i);
                    nombresBeneficiarios[i] = beneficiario.getNombre() + " - " + beneficiario.getDireccion() + " - " + beneficiario.getEstado();
                }

                // Actualizar el JList con los nombres de los beneficiarios
                listaBeneficiarios.setListData(nombresBeneficiarios);
            }
        });
    }

    private void cargarEstados() {
        // Recorrer los valores del enum Barrio y agregarlos al comboBox
        for (EstadoBeneficiario estado : EstadoBeneficiario.values()) {
            estadoComboBox.addItem(estado);
        }
    }

    public void setPosicion(int x, int y){
        this.setLocation(x, y);
    }
}
