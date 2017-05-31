package DAO;

import app.BDD;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;



/**
 * Created by xontik on 31/05/2017.
 */
public class PC extends DAO{

    public static ArrayList<ArrayList<String>> getAllPc(){
        ResultSet rs = BDD.query("SELECT * FROM PC");

        ArrayList<ArrayList<String>> res = new ArrayList<>();

        try{
            while(rs.next()){
                ArrayList<String> line =  new ArrayList<>();
                line.add(Integer.toString(rs.getInt(1)));
                line.add(rs.getString(2));
                line.add(Integer.toString(rs.getInt(3)));
                line.add(Integer.toString(rs.getInt(4)));
                res.add(line);
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return res;
    }



}
