package com.spring.prototype.menuitems.end;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.EndController;
import com.spring.models.field.IRField;
import com.spring.models.utils.Tracer;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Beállítja a következő hókotró lépést
 */
public class GetField extends MenuItem {
    private EndController controller;
    private int serial;
    private static final Pattern PATTERN = Pattern.compile("(?i)^GET-FIELD\\s+(\\d+)$");

    public GetField(EndController controller) {
        super("GET-FIELD <serial>");
        this.controller = controller;
    }

    @Override
    public void execute() {
        IRField field = controller.getField(serial);
        if(field != null){
            Tracer.getInstance().newObject(field);
        }
    }

    @Override
    public boolean parse(String input) {
        Matcher matcher = PATTERN.matcher(input.trim());
        if (matcher.matches()) {
            this.serial = Integer.parseInt(matcher.group(1));
            return true;
        }
        return false;
    }
}