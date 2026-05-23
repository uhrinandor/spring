package com.spring.graphics.panels.map;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import com.spring.graphics.panels.map.interfaces.RoadViewListener;
import com.spring.graphics.utils.LayerImageVisitor;
import com.spring.models.field.IRField;
import com.spring.models.layer.ILayer;
import com.spring.models.utils.IEntity;
import com.spring.models.utils.IObserver;

public class FieldView  extends JPanel implements IObserver{
    Point location;
    IRField field;
    List<Pin> pins;
    RoadViewListener roadViewListener;
    private Popup popup;

    Image background;

    public FieldView(IRField field, Point location){
        super();
        this.field = field;
        this.location = location;
        this.pins = new ArrayList<>();
        field.subscribe(this);
        setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
        setBounds(location.x, location.y, 50, 50);

        setBorder(field.isUnderGround()? BorderFactory.createLineBorder(new Color(165, 42, 42)) : BorderFactory.createLineBorder(Color.BLUE) );

        setBorder(BorderFactory.createLineBorder(Color.BLUE));        
        loadBackground();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JLabel label = new JLabel( String.format("%d",field.getId()));
                label.setOpaque(true);
                label.setBackground(new Color(255,255,220));
                label.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                Point screen = e.getLocationOnScreen();

                popup = PopupFactory.getSharedInstance().getPopup(
                    FieldView.this,
                    label,
                    screen.x + 10,
                    screen.y + 10
                );

                popup.show();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (popup != null) {
                    popup.hide();
                    popup = null;
                }
            }

            @Override
            public void mouseClicked(MouseEvent event){
                if(roadViewListener != null){
                     roadViewListener.onFieldClicked(field);
                }
            }
        });           
    }

    public void addPin(Pin pin){
        pins.add(pin);
        add(pin);
        revalidate();
        repaint();
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

    public int getX(){
        return location.x;
    }

    public int getY(){
        return location.y;
    }

    public IRField getField(){
        return field;
    }

    @Override
    public void notifyChange(IEntity entity) {
        loadBackground();
        repaint();
    }
}
