package com.spring.graphics.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class LegendButton extends BaseButton{
    int counter = 0;
    Legend legend;

    public LegendButton(){
        super("Legend");

        legend = new Legend();

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent E){
                counter++;
                if(counter >= 10){
                    JDialog dialog = new JDialog((JDialog) null, "Plot Twist", true);
                    dialog.setSize(300, 300);
                    dialog.setLocationRelativeTo(null);

                    try {
                        ImageIcon image = new ImageIcon(
                            getClass().getResource("/logo/bus.jpeg")
                        );

                        JLabel label = new JLabel(image);
                        dialog.add(label);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    dialog.setVisible(true);
                }
                openLegend();
                
            }
        });
    }

    public void openLegend(){
        legend.setLocationRelativeTo(null);
        legend.setVisible(true);
    }
    
}
