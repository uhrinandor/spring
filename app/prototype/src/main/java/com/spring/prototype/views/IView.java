package com.spring.prototype.views;

public interface IView {
    public void render();
    public void handle(String input);
    public String getTitle();
    public void showCommands();
}
