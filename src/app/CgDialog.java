package app;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Fanou on 11/06/2017.
 */
public class CgDialog {

    JPanel form = new JPanel();
    JTextField brand = new JTextField();
    JTextField model = new JTextField();
    JTextField price = new JTextField();
    public CgDialog() {

        form.setLayout(new GridLayout(8,1));
        brand.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, brand.getPreferredSize().height) );

        model.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, model.getPreferredSize().height) );
        price.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, price.getPreferredSize().height) );
        form.add(new JLabel("Brand :"));
        form.add(brand);

        form.add(new JLabel("Model :"));
        form.add(model);

        form.add(new JLabel("Prix :"));
        form.add(price);

        //TODO DO WHILE VERIF DATA


    }
    public CgDialog(String brand,String model,String price){
        this();
        this.brand.setText(brand);
        this.model.setText(model);
        this.price.setText(price);

    }

    public String getBrand(){return brand.getText();}

    public String getModel() {
        return model.getText();
    }

    public String getPrice() {
        return price.getText();
    }


    public int getResult() {
        return JOptionPane.showConfirmDialog(null,form,"Ajouter une CG",JOptionPane.OK_CANCEL_OPTION);
    }
}
