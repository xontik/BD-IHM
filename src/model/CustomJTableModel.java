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
        System.out.println(data.get(rowIndex).get(columnIndex));
        return data.get(rowIndex).get(columnIndex);
    }


    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }


    public void removeAllRow(){
        while(getRowCount()!=0){
            data.remove(0);
        }
    }
    public void setData(ArrayList<ArrayList<String>> d){
        data = d;
        fireTableDataChanged();
    }

    public void removeAllColumn(){
        while(getColumnCount()!=0){
            columnNames.remove(0);
        }
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
