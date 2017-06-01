package controller;

import DAO.DAO;
import DAO.PC;
import model.CustomJTableModel;

import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;
import java.util.ArrayList;

/**
 * Created by xontik on 31/05/2017.
 */
public class Controller {
    public static void refresh(CustomJTableModel model,String table){
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> colNames = new ArrayList<>();
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
}
