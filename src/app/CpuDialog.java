package app;

import DAO.CPU;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Fanou on 10/06/2017.
 */
public class CpuDialog {
    JPanel form = new JPanel();
    JTextField brand = new JTextField();
    JTextField model = new JTextField();
    JTextField speed = new JTextField();
    JTextField core = new JTextField();
    public CpuDialog() {

        form.setLayout(new GridLayout(8,1));
        brand.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, brand.getPreferredSize().height) );

        model.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, model.getPreferredSize().height) );
        speed.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, speed.getPreferredSize().height) );
        core.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, core.getPreferredSize().height) );

        form.add(new JLabel("Brand :"));
        form.add(brand);

        form.add(new JLabel("Model :"));
        form.add(model);

        form.add(new JLabel("Speed :"));
        form.add(speed);

        form.add(new JLabel("Core : "));
        form.add(core);
        //TODO DO WHILE VERIF DATA


    }
    public CpuDialog(String brand,String model,String speed,String core){
        this();
        this.brand.setText(brand);
        this.model.setText(model);
        this.speed.setText(speed);
        this.core.setText(core);
    }

    public String getBrand(){return brand.getText();}

    public String getModel() {
        return model.getText();
    }

    public String getSpeed() {
        return speed.getText();
    }

    public String getCore() {
        return core.getText();
    }

    public int getResult() {
        return JOptionPane.showConfirmDialog(null,form,"Ajouter un CPU",JOptionPane.OK_CANCEL_OPTION);
    }
}
