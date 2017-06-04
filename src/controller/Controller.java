package controller;

import DAO.DAO;
import DAO.PC;

import app.MainWindow;
import model.CustomJTableModel;
import javax.swing.*;
import java.util.ArrayList;

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
        JOptionPane.showMessageDialog(null,"Ici fenetre qui pour ajouter : "+table);
    }
    public void delete(int id){
        boolean deleted = true;

        switch (table){
            case MainWindow.PC:
                deleted = PC.delete(id);
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
