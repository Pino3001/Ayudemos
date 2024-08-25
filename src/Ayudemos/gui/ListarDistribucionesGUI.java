package Ayudemos.gui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;



public class ListarDistribucionesGUI extends javax.swing.JFrame {
    private JPanel ListarDistribuciones;




    public class SidebarExample {

        private static void createAndShowGUI() {
            // Crear el frame
            JFrame frame = new JFrame("Sidebar Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 400);

            // Crear los nodos del árbol
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("Develop");
            DefaultMutableTreeNode toolsNode = new DefaultMutableTreeNode("Tools for development");
            DefaultMutableTreeNode navigationNode = new DefaultMutableTreeNode("Navigation");
            DefaultMutableTreeNode userInterfaceNode = new DefaultMutableTreeNode("User interface");

            // Añadir subelementos a "User interface"
            userInterfaceNode.add(new DefaultMutableTreeNode("Splash screen and app icon"));
            userInterfaceNode.add(new DefaultMutableTreeNode("Safe areas"));
            userInterfaceNode.add(new DefaultMutableTreeNode("Fonts"));
            userInterfaceNode.add(new DefaultMutableTreeNode("Assets"));
            userInterfaceNode.add(new DefaultMutableTreeNode("Color themes"));
            userInterfaceNode.add(new DefaultMutableTreeNode("Animation"));
            userInterfaceNode.add(new DefaultMutableTreeNode("Store data"));
            userInterfaceNode.add(new DefaultMutableTreeNode("Next steps"));

            // Añadir nodos al árbol
            toolsNode.add(navigationNode);
            toolsNode.add(userInterfaceNode);
            root.add(toolsNode);

            // Crear el JTree
            JTree tree = new JTree(root);

            // Expandir los nodos por defecto
            tree.expandRow(0);
            tree.expandRow(1);

            // Crear un JScrollPane para el JTree
            JScrollPane treeScrollPane = new JScrollPane(tree);

            // Añadir el JScrollPane al frame
            frame.add(treeScrollPane);

            // Hacer visible el frame
            frame.setVisible(true);
        }

        public static void main(String[] args) {
            // Iniciar la aplicación en el hilo de eventos de Swing
            SwingUtilities.invokeLater(SidebarExample::createAndShowGUI);
        }
    }















}
