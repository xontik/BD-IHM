package DAO;

import app.BDD;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

/**
 * Created by xontik on 31/05/2017.
 */
public class DAO {
    public static ArrayList<String> getTableColumsName(String table){
        ResultSet rs = BDD.query("SELECT * FROM "+table);
        ArrayList<String> c;
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columsCount = rsmd.getColumnCount();
            c = new ArrayList<>();

            for(int i =1; i<=columsCount;i++){
                c.add(rsmd.getColumnName(i));

            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return c;
    }

    public static ArrayList<ArrayList<String>> getAllFromTable(String table){
        ResultSet rs = BDD.query("SELECT * FROM "+table);
        ArrayList<String> columns = getTableColumsName(table);

        ArrayList<ArrayList<String>> res = new ArrayList<>();

        try{
            while(rs.next()){
                ArrayList<String> line =  new ArrayList<>();
                for(String name:columns) {
                    line.add(rs.getString(name));
                }
                res.add(line);
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return res;
    }
}
