package com.spring.graphics.panels.map;

import java.awt.Color;

import javax.swing.BorderFactory;

import com.spring.models.layer.Salt;
import com.spring.models.utils.IEntity;
import com.spring.models.utils.IObserver;

public class SaltView extends Pin implements IObserver {

    private Salt salt;

    public SaltView() {
        super();

        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setOpaque(true);
        setVisible(false);
    }

    public void setSalt(Salt salt) {
        if (this.salt == salt) {
            updateVisibility();
            return;
        }

        this.salt = salt;

        if (this.salt != null) {
            this.salt.subscribe(this);
        }

        updateVisibility();
    }

    private void updateVisibility() {
        setVisible(salt != null && salt.getTimer() != 0);
        revalidate();
        repaint();
    }

    @Override
    public void notifyChange(IEntity entity) {
        if (entity == salt) {
            updateVisibility();
        }
    }
}