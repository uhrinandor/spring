package com.spring.graphics.utils;

import javax.swing.ImageIcon;

import com.spring.models.layer.ILayer;
import com.spring.models.layer.ILayerVisitor;
import com.spring.models.layer.Ice;
import com.spring.models.layer.Layer;
import com.spring.models.layer.Snow;
import com.spring.models.layer.Stone;
import com.spring.models.utils.Entity;

public class LayerImageVisitor extends Entity implements ILayerVisitor {
    
    private ImageIcon icon = null;
    private String Key = "layer";

    public ImageIcon getIcon() { 
        resolve(Key);
        return icon; 
    }
 

    public String getKey() { return Key; }
 
    @Override
    public void visit(Layer l) {
        resolve("layer");
    }

    @Override
    public void visit(Snow s) {
        resolve(switch (s.getLevel()) {
            case 1 -> "snow";
            case 2 -> "compressedsnow";
            default -> "highsnow";
        });
    }
 
    @Override
    public void visit(Ice i) {
        resolve(i.getBroken() ? "brokenice" : "ice");
    }
 

    @Override
    public void visit(Stone s) {
        ILayer previous = s.getPrevious();
        if (previous != null) {
            previous.accept(this);        
        }
        resolve(Key + "_stone");   
    }
 
    private void resolve(String name) {
        if(name!=null){
            Key = name;
        }        
        icon = loadIcon(Key);
    }
 
    @Override
    public java.util.List<String> init() {
        return java.util.List.of("fallbackKey: " + Key);
    }

    private ImageIcon loadIcon(String name){
        return new ImageIcon(LayerImageVisitor.class.getResource("/fields/" + name + ".jpeg"));
    };
}
