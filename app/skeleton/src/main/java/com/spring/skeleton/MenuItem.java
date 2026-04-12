package com.spring.skeleton;

public class MenuItem {
    String title;
    Runnable action;

    public MenuItem(String title, Runnable action) {
        this.title = title;
        this.action = action;
    }
    public String getTitle() {
        return title;
    }   

    public void execute() {
        action.run();
    }
}
