package controller;

import DAO.PC;
import model.CustomJTableModel;

import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;
import java.util.ArrayList;

/**
 * Created by xontik on 31/05/2017.
 */
public class Controller {
    public static void refreshPC(CustomJTableModel model){
        ArrayList<ArrayList<String>> data = PC.getAllPc();
        ArrayList<String> colNames = PC.getTableColumsName("PC");


        model.setColumnNames(colNames);
        model.setData(data);


    }
}
