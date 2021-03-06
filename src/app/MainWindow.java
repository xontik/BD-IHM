package app;

import controller.Controller;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import model.CustomJTableModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by xontik on 31/05/2017.
 */
public class MainWindow extends JFrame {
    JPanel contentPane;
    JComboBox<String> combo;
    public static String PC = "PC";
    public static String CG = "CG";
    public static String CPU = "CPU";
    JTable table;

    public MainWindow() throws HeadlessException {
        super();
        setTitle("Mega cyber café gaming de la mort qui tue ! ");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600,600);
        setContentPane(makeContentPane());
        setVisible(true);

    }

    public JPanel makeContentPane(){
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        JPanel top = new JPanel();

        JLabel labelCombo = new JLabel("Selectionner la section a gerer : ");
        String []categories = {PC,CG,CPU};
        combo = new JComboBox<String>(categories);
        JButton valid = new JButton(new ActionValider("Valider/Actualiser", this)); //TODO ACTIONLISTENER
        valid.addActionListener(e-> Controller.refreshPC((CustomJTableModel)table.getModel()));


        top.setLayout(new BoxLayout(top,BoxLayout.X_AXIS));
        top.add(labelCombo);
        top.add(combo);
        top.add(valid);
        top.add(Box.createGlue());

        contentPane.add(top,BorderLayout.NORTH);

        table = new JTable(new CustomJTableModel());
        Controller.refreshPC((CustomJTableModel)table.getModel());
        JScrollPane scrollPane = new JScrollPane(table);


        contentPane.add(scrollPane,BorderLayout.CENTER);

        JPanel bottom = new JPanel();

        JButton add = new JButton("Ajouter");// TODO ACTIONLISTENER
        add.setEnabled(false);
        JButton edit = new JButton("Editer");// TODO ACTIONLISTENER
        edit.setEnabled(false);
        JButton delete = new JButton("Supprimer");// TODO ACTIONLISTENER
        delete.setEnabled(false);


        bottom.setLayout(new BoxLayout(bottom,BoxLayout.X_AXIS));

        bottom.add(Box.createGlue());
        bottom.add(add);
        bottom.add(edit);
        bottom.add(delete);

        contentPane.add(bottom, BorderLayout.SOUTH);


        return contentPane;
    }
}
