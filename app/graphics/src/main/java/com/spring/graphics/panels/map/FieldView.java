package com.spring.graphics.panels.map;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.spring.graphics.panels.map.interfaces.RoadViewListener;
import com.spring.models.field.IRField;
import com.spring.models.utils.IEntity;
import com.spring.models.utils.IObserver;

public class FieldView  extends JPanel implements IObserver {
    Point location;
    IRField field;
    RoadViewListener listener;

    public FieldView(IRField field, Point location){
        // TODO:
        super();
        this.field = field;
        this.location = location;
        field.subscribe(this);
        setBounds(location.x, location.y, 90, 50);
        add(new JLabel(field.toString()));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event){
                if(listener != null){
                    listener.onFieldClicked(field);
                }
            }
        });
    }

    public void setListener(RoadViewListener listener) {
        this.listener = listener;
    }

    @Override
    public void notifyChange(IEntity entity) {
        // TODO:
        repaint();
        revalidate();
    }
}
