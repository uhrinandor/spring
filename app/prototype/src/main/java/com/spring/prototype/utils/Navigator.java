package com.spring.prototype.utils;

import com.spring.prototype.views.IView;

/**
 * Ezzel az osztállyal tudunk navigálni a nézetek között.
 */
public class Navigator {
    IView currentView;
    public void navigateTo(IView view) {
        this.currentView = view;
    }

    public IView getCurrentView() {
        return currentView;
    }
}
