package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by xontik on 31/05/2017.
 */
public class CustomJTableModel extends AbstractTableModel{
    ArrayList<String> columnNames = new ArrayList<>();
    ArrayList<ArrayList<String>> data = new ArrayList<>();

    public CustomJTableModel() {


    }


    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex).get(columnIndex);
    }
    public ArrayList<String> getRow(int index){
        return data.get(index);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }



    public void setData(ArrayList<ArrayList<String>> d){
        data = d;
        fireTableDataChanged();
    }


    public void setColumnNames(ArrayList<String> c){
        columnNames = c;
        fireTableStructureChanged();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    @Override
    public int findColumn(String columnName) {
        return columnNames.indexOf(columnName);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnNames.get(columnIndex).getClass();
    }


}
