package com.spring.graphics.panels.map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.spring.graphics.panels.map.interfaces.RoadViewListener;
import com.spring.graphics.utils.LayerImageVisitor;
import com.spring.models.field.IRField;
import com.spring.models.layer.ILayer;
import com.spring.models.utils.IEntity;
import com.spring.models.utils.IObserver;


public class FieldView  extends JPanel implements IObserver {
    Point location;
    IRField field;
    List<Pin> pins;
    RoadViewListener roadViewListener;

    Image background;

    public FieldView(IRField field, Point location){
        super();
        this.field = field;
        this.location = location;
        field.subscribe(this);
        setBounds(location.x, location.y, 50, 50);
        setLayout(new BorderLayout());
        add(new JLabel("FIELD", SwingConstants.CENTER));
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
        loadBackground(); 
    }

    public void setListener(RoadViewListener listener) {
        this.roadViewListener = listener;
    }

    private void loadBackground(){
        LayerImageVisitor visitor = new LayerImageVisitor();
        ILayer layer = field.getLayer();

        if(layer != null){
            layer.accept(visitor);
            background = visitor.getIcon().getImage();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    public void notifyChange(IEntity entity) {
        loadBackground();
        repaint();
    }
}
