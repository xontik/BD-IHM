package controller;

import DAO.DAO;
import DAO.PC;

import model.CustomJTableModel;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by xontik on 31/05/2017.
 */
public class Controller {
    public static void refresh(CustomJTableModel model,String table){
        ArrayList<ArrayList<String>> data;
        ArrayList<String> colNames;
        if(table=="PC"){
            data = PC.getPcWithJoinedDetail();
            colNames = PC.getColumnsPcWithJoinedDetail();

        }else{
            data = DAO.getAllFromTable(table);
            colNames = DAO.getTableColumsName(table);
        }

        model.setColumnNames(colNames);
        model.setData(data);
    }

    public static void add(String type){
        JOptionPane.showMessageDialog(null,"Ici fenetre qui pour ajouter : "+type);
    }
    public static void delete(String type, int id){
        JOptionPane.showMessageDialog(null,"Ici fenetre qui pour ajouter : "+type+" "+Integer.toString(id));
    }
    public static void edit(String type, ArrayList<String> data){
        String s = "";
        for(String d : data){
            s+=" "+d;
        }
        JOptionPane.showMessageDialog(null,"Ici fenetre qui pour ajouter : "+s);
    }
}
