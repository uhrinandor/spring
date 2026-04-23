package com.spring.prototype.menuitems.init;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.InitController;
import com.spring.models.field.IRField;
import com.spring.models.layer.ILayer;
import com.spring.models.layer.Ice;
import com.spring.models.layer.Layer;
import com.spring.models.layer.Snow;
import com.spring.models.layer.Stone;
import com.spring.models.utils.Tracer;
import com.spring.prototype.menuitems.MenuItem;

public class AddField extends MenuItem{
    InitController controller;
    boolean isUnderGround;
    String type;


    private static final Pattern PATTERN = Pattern.compile(
    "(?i)^\\s*ADD-FIELD\\s+(HIGHSNOW|LOWSNOW|ICE|BROKENICE|LAYER|STONE-ICE|STONE-HIGHSNOW|STONE-LOWSNOW|STONE-LAYER)\\s+(T|F)\\s*$"
);

	public AddField(InitController controller) {
		super("ADD-FIELD <layerType> <isUnderGround>");
		this.controller = controller;
	}

    @Override
    public void execute() {
        ILayer layer;
        switch(type){
            case "HIGHSNOW":
                layer = new Snow(2);
                break;
            case "LOWSNOW":
                layer = new Snow(1);
                break;
            case "ICE":
                layer = new Ice();
                break;
            case "BROKENICE":                
                layer = new Ice(true);
                break;
            case "LAYER":
                layer = new Layer();
                break;
            case "STONE-ICE":
                Stone stoneIce = new Stone();
                stoneIce.setPrevious(new Ice());
                layer = stoneIce;
                break;
            case "STONE-HIGHSNOW":
                Stone stoneHighSnow = new Stone();
                stoneHighSnow.setPrevious(new Snow(2));
                layer = stoneHighSnow;
                break;
            case "STONE-LOWSNOW":
                Stone stoneLowSnow = new Stone();
                stoneLowSnow.setPrevious(new Snow(1));
                layer = stoneLowSnow;
                break;
            case "STONE-LAYER":
                Stone stoneLayer = new Stone();
                stoneLayer.setPrevious(new Layer());
                layer = stoneLayer;
                break;
            default:
                controller.error("Invalid layer type");
                return;
        }

        IRField field = controller.addField(layer, isUnderGround);
        Tracer.getInstance().info(String.format("%s added with %s", field, layer));
    }

    @Override
    public boolean parse(String input) {
        Matcher matcher = PATTERN.matcher(input.trim());

        if (matcher.matches()) {
            type = matcher.group(1).toUpperCase();
            isUnderGround = matcher.group(2).equalsIgnoreCase("T");
            return true;
        }

        return false;
    }
    
}
