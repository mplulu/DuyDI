/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package resource;

import java.awt.Color;
import java.util.Map;
import java.util.TreeMap;
import objects.Door;
import model.Player;
import objects.Wall;
import objects.Way;

/**
 *
 * @author hoanggia
 */
public class ColorResources {
    private static final Map<String, Color> objectsColorsMap= new TreeMap<String,Color>();

    static{
        objectsColorsMap.put(Wall.class.getName(), Color.DARK_GRAY);
        objectsColorsMap.put(Door.class.getName(), Color.DARK_GRAY);
        objectsColorsMap.put(Player.class.getName(), Color.WHITE);
        objectsColorsMap.put(Way.class.getName(), Color.WHITE);
    }


    public Color getColorFor(String objectName){
        return objectsColorsMap.get(objectName);
    }




}
