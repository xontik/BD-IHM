package app;

import controller.Controller;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import model.CustomJTableModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * Created by xontik on 31/05/2017.
 */
public class MainWindow extends JFrame {
    JPanel contentPane;
    JComboBox<String> combo;
    JButton add;
    JButton edit;
    JButton delete;
    JButton refresh;
    Controller ctrl;
    public static final String PC = "PC";
    public static final String CG = "CG";
    public static final String CPU = "CPU";
    JTable table;

    public String categorie = PC;

    public MainWindow() throws HeadlessException {
        super();
        setTitle("Mega cyber café gaming de la mort qui tue ! ");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600,300);
        ctrl = new Controller(table);

        setContentPane(makeContentPane());
        listeners();
        setVisible(true);

    }

    public JPanel makeContentPane(){
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        JPanel top = new JPanel();

        JLabel labelCombo = new JLabel("Selectionner la section a gerer : ");
        String []categories = {PC,CG,CPU};
        combo = new JComboBox<String>(categories);
        combo.setSelectedIndex(0);

        refresh = new JButton(new ActionValider("Actualiser", this)); //TODO ACTIONLISTENER


        top.setLayout(new BoxLayout(top,BoxLayout.X_AXIS));
        top.add(labelCombo);
        top.add(combo);
        top.add(refresh);
        top.add(Box.createGlue());

        contentPane.add(top,BorderLayout.NORTH);

        table = new JTable(new CustomJTableModel());
        ctrl.setTable(categorie);
        ctrl.setJTable(table);
        ctrl.refresh();
        JScrollPane scrollPane = new JScrollPane(table);


        contentPane.add(scrollPane,BorderLayout.CENTER);

        JPanel bottom = new JPanel();

        add = new JButton("Ajouter");// TODO ACTIONLISTENER
        edit = new JButton("Editer");// TODO ACTIONLISTENER
        edit.setEnabled(false);
        delete = new JButton("Supprimer");// TODO ACTIONLISTENER
        delete.setEnabled(false);


        bottom.setLayout(new BoxLayout(bottom,BoxLayout.X_AXIS));

        bottom.add(add);
        bottom.add(Box.createGlue());

        bottom.add(edit);
        bottom.add(delete);

        contentPane.add(bottom, BorderLayout.SOUTH);


        return contentPane;
    }

    private void listeners(){
        combo.addActionListener(e-> {categorie=(String)combo.getSelectedItem();ctrl.setTable(categorie);
            ctrl.refresh();});
        refresh.addActionListener(e-> {categorie=(String)combo.getSelectedItem();ctrl.setTable(categorie);
            ctrl.refresh();});

        add.addActionListener(e -> ctrl.add());
        edit.addActionListener(e -> ctrl.edit(((CustomJTableModel)table.getModel()).getRow(table.getSelectedRow())));
        delete.addActionListener(e -> ctrl.delete(Integer.valueOf((String)table.getValueAt(table.getSelectedRow(),0))));
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int debut = e.getFirstIndex();
                int fin = e.getLastIndex();
                boolean selected = false;
                for(int i=debut;i<=fin;i++){
                    if(table.isRowSelected(i)){
                        selected = true;
                    }
                }
                if(selected) {
                    edit.setEnabled(true);
                    delete.setEnabled(true);
                }else{
                    edit.setEnabled(false);
                    delete.setEnabled(false);
                }
            }
        });

    }
}
