package DAO;

import app.BDD;

import java.sql.SQLException;

/**
 * Created by xontik on 31/05/2017.
 */
public class CPU {
    public static boolean add(String b, String m,String s,String c){
        try{
            BDD.query("{call addToCpu('"+b+"','"+m+"',"+s+","+c+")}");
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean delete(int id){
        try{
            BDD.query("DELETE FROM CPU WHERE cpu_id="+Integer.toString(id));
        }catch(SQLException e){
            return false;
        }
        return true;
    }
}
