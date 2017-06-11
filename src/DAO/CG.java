package DAO;

import app.BDD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

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
    public static HashMap<Integer,String> getCgNotUsed() {
        HashMap<Integer, String> cpus = new HashMap<>();

        try {
            ResultSet rs = BDD.query("SELECT cg_id,model_cg,brand from cg where cg_id not in(select cg_id from pc)");
            while (rs.next()) {
                cpus.put(rs.getInt("cg_id"), rs.getString("model_cg") + " " + rs.getString("brand"));
            }
        } catch (SQLException e) {
            return null;
        }
        return cpus;
    }
    public static boolean edit(String id,String b,String m,String p){
        try{
            BDD.query("{call editCg("+id+",'"+b+"','"+m+"',"+p+")}");
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static String getCgLabel(int id){
        try{
            ResultSet rs = BDD.query("SELECT model_cg,brand from cg where cg_id ="+Integer.toString(id));
            rs.next();
            return rs.getString("model_cg") + " " + rs.getString("brand");
        }catch(SQLException e){
            return null;
        }

    }
}
