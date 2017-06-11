package DAO;

import app.BDD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

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
    public static boolean edit(String id,String b,String m,String s,String c){
        try{
            BDD.query("{call editCpu("+id+",'"+b+"','"+m+"',"+s+","+c+")}");
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String getCpuLabel(int id){
        try{
            ResultSet rs = BDD.query("SELECT model_cpu,brand from cpu where cpu_id ="+Integer.toString(id));
            rs.next();
            return rs.getString("brand") + " " + rs.getString("model_cpu");
        }catch(SQLException e){
            return null;
        }

    }

    public static HashMap<Integer,String> getCpuNotUsed() {
        HashMap<Integer, String> cpus = new HashMap<>();

        try {
            ResultSet rs = BDD.query("SELECT cpu_id,model_cpu,speed from cpu where cpu_id not in(select cpu_id from pc)");
            while (rs.next()) {
                cpus.put(rs.getInt("cpu_id"), rs.getString("model_cpu") + " " + rs.getString("speed"));
            }
        } catch (SQLException e) {
            return null;
        }
        return cpus;
    }
}
