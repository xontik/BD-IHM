package controller;

import DAO.DAO;
import DAO.PC;

import app.CgDialog;
import app.CpuDialog;
import app.MainWindow;
import app.PcDialog;
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

                CpuDialog cpuDial = new CpuDialog();
                //TODO DO WHILE VERIF DATA
                int result = cpuDial.getResult();
                if(result == JOptionPane.YES_OPTION){

                    if(CPU.add(cpuDial.getBrand(),cpuDial.getModel(),cpuDial.getSpeed(),cpuDial.getCore())){
                        JOptionPane.showMessageDialog(null, "Le CPU a bien été ajouté !");
                        refresh();
                    }else{
                        JOptionPane.showMessageDialog(null, "Erreur impossible d'ajouter le CPU !");
                    }
                }

                break;}
            case MainWindow.CG: {
                CgDialog cgDialog = new CgDialog();

                //TODO DO WHILE VERIF DATA
                int result = cgDialog.getResult();

                if (result == JOptionPane.YES_OPTION) {
                    if (CG.add(cgDialog.getBrand(), cgDialog.getModel(), cgDialog.getPrice())) {
                        JOptionPane.showMessageDialog(null, "La CG a bien été ajoutée !");
                        refresh();
                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur impossible d'ajouter la CG !");
                    }
                }
                break;
            }
            case MainWindow.PC: {


                PcDialog pcDialog = new PcDialog();
                //TODO DO WHILE VERIF DATA
                if(!pcDialog.isComponentAvailable()){
                    JOptionPane.showMessageDialog(null, "Erreur : plus de composant disponible!");
                }else{
                    int result = pcDialog.getResult();
                    if (result == JOptionPane.YES_OPTION) {
                        if (PC.add(pcDialog.getName(),pcDialog.getIdCpu(),pcDialog.getIdCg())) {
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
        //JOptionPane.showMessageDialog(null,"Ici fenetre qui pour editer : "+ table +" "+s);


        switch (table) {
            case MainWindow.CPU:{

                CpuDialog cpuDial = new CpuDialog(data.get(1),data.get(2),data.get(3),data.get(4));
                //TODO DO WHILE VERIF DATA
                int result = cpuDial.getResult();
                if(result == JOptionPane.YES_OPTION){

                    if(CPU.edit(data.get(0),cpuDial.getBrand(),cpuDial.getModel(),cpuDial.getSpeed(),cpuDial.getCore())){
                        JOptionPane.showMessageDialog(null, "Le CPU a bien été mis a jour !");
                        refresh();
                    }else{
                        JOptionPane.showMessageDialog(null, "Erreur impossible d'ajouter le CPU !");
                    }
                }

                break;}
            case MainWindow.CG: {
                CgDialog cgDialog = new CgDialog(data.get(1),data.get(2),data.get(3));

                //TODO DO WHILE VERIF DATA
                int result = cgDialog.getResult();

                if (result == JOptionPane.YES_OPTION) {
                    if (CG.edit(data.get(0),cgDialog.getBrand(), cgDialog.getModel(), cgDialog.getPrice())) {
                        JOptionPane.showMessageDialog(null, "La CG a bien été mis a jour !");
                        refresh();
                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur impossible de mettre a jour la CG !");
                    }
                }
                break;
            }
            case MainWindow.PC: {


                PcDialog pcDialog = new PcDialog(data.get(1),Integer.valueOf(data.get(3)),Integer.valueOf(data.get(5)));
                //TODO DO WHILE VERIF DATA
                if(!pcDialog.isComponentAvailable()){
                    JOptionPane.showMessageDialog(null, "Erreur : plus de composant disponible!");
                }else{
                    int result = pcDialog.getResult();
                    if (result == JOptionPane.YES_OPTION) {
                        if (PC.edit(data.get(0),pcDialog.getName(),Integer.toString(pcDialog.getIdCpu()),Integer.toString(pcDialog.getIdCg()))) {
                            JOptionPane.showMessageDialog(null, "Le PC a bien été mis a jour !");
                            refresh();
                        } else {
                            JOptionPane.showMessageDialog(null, "Erreur impossible de mettre a jour le PC !");
                        }
                    }
                }
            }
        }
    }
}
