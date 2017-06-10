package DAO;

import app.BDD;

import java.sql.SQLException;

/**
 * Created by xontik on 31/05/2017.
 */
public class CG {
    public static boolean add(String b, String m,String s){
        try{
            BDD.query("{call addToCg('"+m+"','"+b+"',"+s+")}");
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean delete(int id){
        try{
            BDD.query("DELETE FROM CG WHERE cg_id="+Integer.toString(id));
        }catch(SQLException e){
            return false;
        }
        return true;
    }
}
