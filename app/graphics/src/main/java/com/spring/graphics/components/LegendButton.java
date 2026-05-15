package com.spring.graphics.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LegendButton extends BaseButton{

    Legend legend;

    public LegendButton(){
        super("Legend");

        legend = new Legend();

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent E){
                openLegend();
            }
        });
    }

    public void openLegend(){
        legend.setLocationRelativeTo(null);
        legend.setVisible(true);
    }
    
}
