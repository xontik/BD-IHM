package controller;

import DAO.DAO;
import DAO.PC;

import app.MainWindow;
import model.CustomJTableModel;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DAO.*;

/**
 * Created by xontik on 31/05/2017.
 */
public class Controller {
    private JTable jtable;
    private String table;

    public Controller(JTable t) {
        jtable = t;
    }
    public void setTable(String tab){
        table = tab;
    }
    public void setJTable(JTable t){
        jtable = t;
    }

    public void refresh(){
        ArrayList<ArrayList<String>> data;
        ArrayList<String> colNames;
        if(table=="PC"){
            data = PC.getPcWithJoinedDetail();
            colNames = PC.getColumnsPcWithJoinedDetail();

        }else{
            data = DAO.getAllFromTable(table);
            colNames = DAO.getTableColumsName(table);
            System.out.println(table);

        }
        CustomJTableModel model = (CustomJTableModel)jtable.getModel();
        model.setColumnNames(colNames);
        model.setData(data);
    }

    public void add(){
        switch (table) {
            case MainWindow.CPU:{

                JPanel form = new JPanel();
                form.setLayout(new GridLayout(8,1));
                JTextField brand = new JTextField();
                brand.setMaximumSize(
                        new Dimension(Integer.MAX_VALUE, brand.getPreferredSize().height) );

                JTextField model = new JTextField();
                model.setMaximumSize(
                        new Dimension(Integer.MAX_VALUE, model.getPreferredSize().height) );
                JTextField speed = new JTextField();
                speed.setMaximumSize(
                        new Dimension(Integer.MAX_VALUE, speed.getPreferredSize().height) );
                JTextField core = new JTextField();
                core.setMaximumSize(
                        new Dimension(Integer.MAX_VALUE, core.getPreferredSize().height) );

                form.add(new JLabel("Brand :"));
                form.add(brand);

                form.add(new JLabel("Model :"));
                form.add(model);

                form.add(new JLabel("Speed :"));
                form.add(speed);

                form.add(new JLabel("Core : "));
                form.add(core);
                //TODO DO WHILE VERIF DATA
                int result = JOptionPane.showConfirmDialog(null,form,"Ajouter un CPU",JOptionPane.OK_CANCEL_OPTION);
                if(result == JOptionPane.YES_OPTION){

                    if(CPU.add(brand.getText(),model.getText(),speed.getText(),core.getText())){
                        JOptionPane.showMessageDialog(null, "Le CPU a bien été ajouté !");
                        refresh();
                    }else{
                        JOptionPane.showMessageDialog(null, "Erreur impossible d'ajouter le CPU !");
                    }
                }

                break;}
            case MainWindow.CG: {
                JPanel form = new JPanel();
                form.setLayout(new GridLayout(8, 1));
                JTextField brand = new JTextField();
                brand.setMaximumSize(
                        new Dimension(Integer.MAX_VALUE, brand.getPreferredSize().height));

                JTextField model = new JTextField();
                model.setMaximumSize(
                        new Dimension(Integer.MAX_VALUE, model.getPreferredSize().height));
                JTextField price = new JTextField();
                price.setMaximumSize(
                        new Dimension(Integer.MAX_VALUE, price.getPreferredSize().height));

                form.add(new JLabel("Brand :"));
                form.add(brand);

                form.add(new JLabel("Model :"));
                form.add(model);

                form.add(new JLabel("Price :"));
                form.add(price);

                //TODO DO WHILE VERIF DATA
                int result = JOptionPane.showConfirmDialog(null, form, "Ajouter un CPU", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.YES_OPTION) {

                    if (CG.add(brand.getText(), model.getText(), price.getText())) {
                        JOptionPane.showMessageDialog(null, "La CG a bien été ajoutée !");
                        refresh();
                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur impossible d'ajouter la CG !");
                    }
                }

                break;
            }
            case MainWindow.PC: {

                //CPU.getCpusIdNotUsed();
                HashMap<Integer,String> cgMap = CG.getCgNotUsed();

                Integer[] idCg = new Integer[cgMap.size()];
                String[] nameCg = new String[cgMap.size()];
                int index = 0;
                for (Map.Entry<Integer, String> mapEntry : cgMap.entrySet()) {
                    idCg[index] = mapEntry.getKey();
                    nameCg[index] = mapEntry.getValue();
                    index++;
                }
                HashMap<Integer,String> cpuMap = CPU.getCpuNotUsed();

                Integer[] idCpu = new Integer[cpuMap.size()];
                String[] nameCpu = new String[cpuMap.size()];
                index = 0;
                for (Map.Entry<Integer, String> mapEntry : cpuMap.entrySet()) {
                    idCpu[index] = mapEntry.getKey();
                    nameCpu[index] = mapEntry.getValue();
                    index++;
                }

                JPanel form = new JPanel();
                form.setLayout(new GridLayout(8, 1));
                JTextField name = new JTextField();
                name.setMaximumSize(
                        new Dimension(Integer.MAX_VALUE, name.getPreferredSize().height));

                JComboBox cpu = new JComboBox(nameCpu);

                JComboBox cg = new JComboBox(nameCg);


                form.add(new JLabel("Nom :"));
                form.add(name);

                form.add(new JLabel("CPU :"));
                form.add(cpu);

                form.add(new JLabel("CG :"));
                form.add(cg);

                //TODO DO WHILE VERIF DATA
                if(cgMap.isEmpty() || cpuMap.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Erreur : plus de composant disponible!");
                }else{
                    int result = JOptionPane.showConfirmDialog(null, form, "Ajouter un PC", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        if (PC.add(name.getText(),idCpu[cpu.getSelectedIndex()],idCg[cg.getSelectedIndex()])) {
                            JOptionPane.showMessageDialog(null, "Le PC a bien été ajoutée !");
                            refresh();
                        } else {
                            JOptionPane.showMessageDialog(null, "Erreur impossible d'ajouter le PC !");
                        }
                    }
                }
            }
        }
    }
    public void delete(int id){
        boolean deleted = true;

        switch (table){
            case MainWindow.PC:
                deleted = PC.delete(id);
                break;
            case MainWindow.CG:
                deleted = CG.delete(id);
                break;
            case MainWindow.CPU:
                deleted = CPU.delete(id);
                break;

        }

        if(deleted){
            JOptionPane.showMessageDialog(null,table+" numero : "+Integer.toString(id)+" a été suprimé.");
            this.refresh();
        }else{
            JOptionPane.showMessageDialog(null,"Erreur : supression impossible.","Erreur",JOptionPane.ERROR_MESSAGE);
        }

    }
    public void edit(ArrayList<String> data){
        String s = "";
        for(String d : data){
            s+=" "+d;
        }
        JOptionPane.showMessageDialog(null,"Ici fenetre qui pour editer : "+ table +" "+s);
    }
}
