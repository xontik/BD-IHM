package app;

import javax.swing.*;

/**
 * Created by xontik on 31/05/2017.
 */
public class BarApp {

    public static void main (String []args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow();
            }
        });
    }
}
