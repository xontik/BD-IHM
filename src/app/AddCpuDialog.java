package app;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Fanou on 10/06/2017.
 */
public class AddCpuDialog  extends JDialog {
    public AddCpuDialog(Dialog owner) {
        super(owner, "Ajouter un CPU", true);
        setSize(250,250);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));

        Box bbrand = new Box(BoxLayout.LINE_AXIS);
        Box bmodel = new Box(BoxLayout.LINE_AXIS);
        Box bspeed = new Box(BoxLayout.LINE_AXIS);
        Box bcore = new Box(BoxLayout.LINE_AXIS);

        contentPane.add(bbrand);
        contentPane.add(bmodel);
        contentPane.add(bspeed);
        contentPane.add(bcore);

        JTextField brand = new JTextField();
        brand.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, brand.getPreferredSize().height) );

        JTextField model = new JTextField();
        model.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, model.getPreferredSize().height) );
        JTextField speed = new JTextField();
        speed.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, speed.getPreferredSize().height) );
        JTextField core = new JTextField();
        core.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, core.getPreferredSize().height) );

        bbrand.add(new JLabel("Brand :"));
        bbrand.add(brand);

        bmodel.add(new JLabel("Model :"));
        bmodel.add(model);

        bspeed.add(new JLabel("Speed :"));
        bspeed.add(speed);

        bcore.add(new JLabel("Core :"));
        bcore.add(core);


        Box bottom = new Box(BoxLayout.LINE_AXIS);
        bottom.add(Box.createGlue());
        bottom.add(new JButton("Cancel"));
        bottom.add(Box.createGlue());
        bottom.add(new JButton("OK"));
        bottom.add(Box.createGlue());

        contentPane.add(bottom);

        setContentPane(contentPane);

        setVisible(true);
    }
}
