import javax.swing.*;
import java.sql.ResultSet;

/**
 * Created by xontik on 31/05/2017.
 */
public class TclApp {

    public static void main (String []args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ResultSet rs = BDD.query("Select * from PC");
                try{
                while(rs.next()){
                    System.out.println(rs.getInt(1));
                }}catch (Exception e){e.printStackTrace();}
            }
        });
    }
}
