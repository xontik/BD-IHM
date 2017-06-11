package app;

import DAO.CG;
import DAO.CPU;
import DAO.PC;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fanou on 11/06/2017.
 */
public class PcDialog {
    JTextField name = new JTextField();
    JComboBox cpu;
    JComboBox cg;
    Integer[] idCg ;
    String[] nameCg;
    JPanel form = new JPanel();

    HashMap<Integer,String> cpuMap;
    HashMap<Integer,String> cgMap;

    Integer[] idCpu;
    String[] nameCpu;
    public void fillArrays(){
        int index = 0;
        idCg = new Integer[cgMap.size()];
        nameCg = new String[cgMap.size()];
        for (Map.Entry<Integer, String> mapEntry : cgMap.entrySet()) {
            idCg[index] = mapEntry.getKey();
            nameCg[index] = mapEntry.getValue();
            index++;
        }
        index = 0;
        idCpu = new Integer[cpuMap.size()];
        nameCpu = new String[cpuMap.size()];
        for (Map.Entry<Integer, String> mapEntry : cpuMap.entrySet()) {
            idCpu[index] = mapEntry.getKey();
            nameCpu[index] = mapEntry.getValue();
            index++;
        }
    }
    public void makePanel(){
        form.setLayout(new GridLayout(8, 1));
        name.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, name.getPreferredSize().height));

        cpu = new JComboBox(nameCpu);

        cg = new JComboBox(nameCg);


        form.add(new JLabel("Nom :"));
        form.add(name);

        form.add(new JLabel("CPU :"));
        form.add(cpu);

        form.add(new JLabel("CG :"));
        form.add(cg);
    }
    public PcDialog(){

        cgMap = CG.getCgNotUsed();
        cpuMap = CPU.getCpuNotUsed();

        fillArrays();
        makePanel();

    }
    public PcDialog(String nom,int idCpu,int idCg){
        cgMap = CG.getCgNotUsed();
        cpuMap = CPU.getCpuNotUsed();

        String currentCgLabel = CG.getCgLabel(idCg);
        System.out.println(currentCgLabel);
        cgMap.put(idCg,currentCgLabel);
        String currentCpuLabel = CPU.getCpuLabel(idCpu);
        cpuMap.put(idCpu,currentCpuLabel);


        fillArrays();
        makePanel();

        cpu.setSelectedIndex(cpuMap.size()-1);
        cg.setSelectedIndex(cgMap.size()-1);
    }
    public String getName(){
        return name.getText();
    }
    public int getIdCpu(){
        return idCpu[cpu.getSelectedIndex()];
    }
    public int getIdCg(){
        return idCg[cg.getSelectedIndex()];
    }
    public boolean isComponentAvailable(){
        return !(cgMap.isEmpty() || cpuMap.isEmpty());
    }
    public int getResult() {
        return JOptionPane.showConfirmDialog(null,form,"Ajouter un PC",JOptionPane.OK_CANCEL_OPTION);
    }
}
