package com.spring.prototype.menuitems.init;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.InitController;
import com.spring.models.field.IField;
import com.spring.models.utils.Tracer;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Ez a menüpont kilistázza sorszámozva az elérhető field-eket
 */
public class ListField extends MenuItem {
    InitController controller;
    private static final Pattern PATTERN = Pattern.compile("(?i)^LIST-FIELDS$");

    public ListField(InitController controller) {
        super("LIST-FIELDS");
        this.controller = controller;
    }

    @Override
    public boolean parse(String input) {
        Matcher matcher = PATTERN.matcher(input.trim());
        return matcher.matches();
    }

    @Override
    public void execute() {
        List<IField> fields = controller.listFields();
        Tracer tracer = Tracer.getInstance();
        for(int i = 0; i < fields.size(); i++) {
            tracer.info(String.format("[%d]", i));
            tracer.newObject(fields.get(i));
        }
    }
}