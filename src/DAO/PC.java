package DAO;

import app.BDD;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;



/**
 * Created by xontik on 31/05/2017.
 */
public class PC extends DAO {

    public static ArrayList<ArrayList<String>> getPcWithJoinedDetail() {
        ResultSet rs = BDD.query("SELECT pc_id,nom,model_cpu,model_cg FROM pc join cg using(cg_id) join cpu using(cpu_id)");
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
                }
                res.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return res;
    }


    public static ArrayList<String> getColumnsPcWithJoinedDetail() {
        ResultSet rs = BDD.query("SELECT pc_id,nom,model_cpu,model_cg FROM pc join cg using(cg_id) join cpu using(cpu_id)");
        return getColumns(rs);
    }




}
