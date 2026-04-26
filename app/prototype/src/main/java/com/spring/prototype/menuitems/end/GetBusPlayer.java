package com.spring.prototype.menuitems.end;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.EndController;
import com.spring.models.player.IRPlayer;
import com.spring.models.utils.Tracer;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Beállítja a következő hókotró lépést
 */
public class GetBusPlayer extends MenuItem {
    private EndController controller;
    private int serial;
    private static final Pattern PATTERN = Pattern.compile("(?i)^GET-BUSPLAYER\\s+(\\d+)$");

    public GetBusPlayer(EndController controller) {
        super("GET-BUSPLAYER <serial>");
        this.controller = controller;
    }

    @Override
    public void execute() {
        IRPlayer player = controller.getBusPlayers(serial);
        if(player != null){
            Tracer.getInstance().newObject(player);
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