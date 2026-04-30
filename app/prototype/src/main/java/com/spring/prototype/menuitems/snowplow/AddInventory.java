package com.spring.prototype.menuitems.snowplow;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.models.head.Broom;
import com.spring.models.head.Brush;
import com.spring.models.head.Dragon;
import com.spring.models.head.IceBreaker;
import com.spring.models.head.SaltSpreader;
import com.spring.models.head.StoneSplasher;
import com.spring.models.layer.Biokerosene;
import com.spring.models.layer.Salt;
import com.spring.models.layer.Stone;
import com.spring.models.utils.Tracer;
import com.spring.models.vehicle.IInventoryItem;
import com.spring.controllers.controllers.SnowPlowController;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Parancs: ADD-INVENTORY <item>
 * Ezzel tudunk hozzáadni egy új elemet a hókotró inventoryjához
 */
public class AddInventory extends MenuItem{
    public AddInventory(SnowPlowController controller) {
        super("ADD-INVENTORY <item>");
        this.controller = controller;
    }

    private static final Pattern PATTERN = Pattern.compile(
    "(?i)^\\s*ADD-INVENTORY\\s+(BIOKEROSENE|SALT|STONE|DRAGON|ICEBREAKER|STONESPLASHER|BROOM|BRUSH|SALTSPREADER)\\s*$");

    SnowPlowController controller;
    String item;

    @Override
    public void execute() {
        IInventoryItem inventoryItem;
        switch(this.item){
            case "BIOKEROSENE":
                inventoryItem = new Biokerosene();
                break;
            case "SALT":
                inventoryItem = new Salt();
                break;
            case "STONE":
                inventoryItem = new Stone();
                break;
            case "DRAGON":
                inventoryItem = new Dragon();
                break;
            case "ICEBREAKER":
                inventoryItem = new IceBreaker();
                break;
            case "STONESPLASHER":
                inventoryItem = new StoneSplasher();
                break;
            case "BROOM":
                inventoryItem = new Broom();
                break;
            case "BRUSH":
                inventoryItem = new Brush();
                break;
            case "SALTSPREADER":
                inventoryItem = new SaltSpreader();
                break;
            default:
                return;
        }

        if(controller.addInventory(inventoryItem)){
            Tracer.getInstance().info("Inventory item added: " + item);
        } else {
            Tracer.getInstance().error("Failed to add inventory item: " + item);
        }
    }

    @Override
    public boolean parse(String input) {
        Matcher matcher = PATTERN.matcher(input.trim());

        if (matcher.matches()) {
            item = matcher.group(1).toUpperCase();
            return true;
        }

        return false;
    }
    
}
