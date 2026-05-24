package com.spring.graphics.panels.map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.security.cert.LDAPCertStoreParameters;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.spring.models.layer.IRSalt;
import com.spring.models.layer.Salt;
import com.spring.models.utils.IEntity;
import com.spring.models.utils.IObserver;

public class SaltView extends Pin implements IObserver {

    private IRSalt salt;
    JLabel counter;

    public SaltView() {
        super();
         setLayout(new BorderLayout());

        counter = new JLabel("");
        counter.setHorizontalAlignment(SwingConstants.CENTER);
        counter.setVerticalAlignment(SwingConstants.CENTER);
        counter.setForeground(Color.BLACK);

        add(counter, BorderLayout.CENTER);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setOpaque(true);
        setVisible(false);
    }

    public void setSalt(IRSalt salt) {
        this.salt = salt;

        if (this.salt != null && this.salt.getTimer() > 0) {
            counter.setText("" + salt.getTimer());
            counter.setVisible(true);
            setVisible(true);
            this.salt.subscribe(this);
        } else {
            counter.setText("");
            counter.setVisible(false);
            setVisible(false);
        }

        revalidate();
        repaint();
    }

    @Override
    public void notifyChange(IEntity entity) {
        if(salt.getTimer() > 0){
            counter.setText(""+salt.getTimer());
        }
        else{
            setVisible(false);
        }
        revalidate();
        repaint();
    }
}