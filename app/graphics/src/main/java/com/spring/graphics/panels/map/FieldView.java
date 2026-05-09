package com.spring.graphics.panels.map;

import java.awt.Color;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.spring.models.field.IRField;
import com.spring.models.utils.IEntity;
import com.spring.models.utils.IObserver;

public class FieldView  extends JPanel implements IObserver {
    Point location;
    IRField field;

    public FieldView(IRField field, Point location){
        super();
        this.field = field;
        this.location = location;
        field.subscribe(this);
        setBounds(location.x, location.y, 50, 50);
        add(new JLabel("FIELD"));
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }

    @Override
    public void notifyChange(IEntity entity) {
        repaint();
        revalidate();
    }
}
