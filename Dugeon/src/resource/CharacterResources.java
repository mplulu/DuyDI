/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package resource;

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
public class CharacterResources {

    private static final Map<String, Character> objectsCharsMap= new TreeMap<String,Character>();

    static{
        objectsCharsMap.put(Wall.class.getName(), '\u2588');
        objectsCharsMap.put(Door.class.getName(), 'U');
        objectsCharsMap.put(Player.class.getName(), '@');
        objectsCharsMap.put(Way.class.getName(), '.');
    }


    public char getCharFor(String objectName){
        return objectsCharsMap.get(objectName);
    }

}
