package app;



import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by xontik on 31/05/2017.
 */
public class ActionValider extends AbstractAction {
    MainWindow main;

    public ActionValider(String name, MainWindow main) {
        super(name);
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Valider !");
    }
}
