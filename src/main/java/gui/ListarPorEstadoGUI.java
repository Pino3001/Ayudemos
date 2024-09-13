package gui;

import datatypes.DtDistribucion;
import interfaces.Fabrica;
import interfaces.IControladorDistribucion;
import types.EstadoDistribucion;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class ListarPorEstadoGUI  extends JFrame {
    private IControladorDistribucion controladorDistribucion;
    private JComboBox<EstadoDistribucion> comboBox1;
    private JList<DtDistribucion> list1;
    private JPanel background;
    private JPanel panelTituloListPorEstado;
    private DefaultListModel<DtDistribucion> modeloLista;

    public ListarPorEstadoGUI(IControladorDistribucion controladorDistribucion) {
        this.controladorDistribucion = controladorDistribucion;
        modeloLista = new DefaultListModel<>();
        list1.setModel(modeloLista);
        for (EstadoDistribucion estadoDistribucion : EstadoDistribucion.values()){
            comboBox1.addItem(estadoDistribucion);
        }


        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                EstadoDistribucion estadoDistribucion = (EstadoDistribucion) comboBox1.getSelectedItem();
                List<DtDistribucion> dtDistribucionList = controladorDistribucion.listarDistribucionesPorEstado(estadoDistribucion);
              if (dtDistribucionList.isEmpty()){
                  
              }
                for (DtDistribucion dtDistribucion : dtDistribucionList){
                    modeloLista.addElement(dtDistribucion);
                }
            }
        });
    }

    public static void main(String[] args) {
        Fabrica fabrica = Fabrica.getInstancia();
        IControladorDistribucion distribucion = fabrica.getIControladorDistribucion();
        JFrame frame = new JFrame("ListarPorEstado");
        frame.setContentPane(new ListarPorEstadoGUI(distribucion).background);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
