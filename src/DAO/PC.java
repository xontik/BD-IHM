package DAO;

import app.BDD;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;



/**
 * Created by xontik on 31/05/2017.
 */
public class PC extends DAO {

    public static ArrayList<ArrayList<String>> getPcWithJoinedDetail() {
        ResultSet rs;
        try {
            rs = BDD.query("SELECT  pc_id,nom,model_cpu,cpu_id,model_cg,cg_id FROM pc join cg using(cg_id) join cpu using(cpu_id)");
        }catch(SQLException e){
            return null;
        }
        return rsToArray(rs);

    }
    public static ArrayList<ArrayList<String>> rsToArray(ResultSet rs){
        ArrayList<String> columns = getColumns(rs);

        ArrayList<ArrayList<String>> res = new ArrayList<>();

        try {
            while (rs.next()) {
                ArrayList<String> line = new ArrayList<>();
                for (String name : columns) {
                    line.add(rs.getString(name));
                    //System.out.println(rs.getString(name));
                }
                res.add(line);
                System.out.println(line.get(0));

            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return res;
    }


    public static ArrayList<String> getColumnsPcWithJoinedDetail() {
        ResultSet rs;

        try {
            rs = BDD.query("SELECT pc_id,nom,model_cpu,cpu_id,model_cg,cg_id FROM pc join cg using(cg_id) join cpu using(cpu_id) order by pc_id asc");
        }catch(SQLException e){
            return null;
        }

        return getColumns(rs);
    }

    public static boolean delete(int id){
        try{
            BDD.query("DELETE FROM PC WHERE pc_id="+Integer.toString(id));
        }catch(SQLException e){
            return false;
        }
        return true;
        
    }
    public static boolean add(String nom, int cpu,int cg){
        try{
            BDD.query("{call addToPc('"+nom+"',"+cpu+","+cg+")}");
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean edit(String id,String n,String idCPU,String idCG){
        try{
            BDD.query("{call editPc("+id+",'"+n+"','"+idCPU+"',"+idCG+")}");
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }





}
