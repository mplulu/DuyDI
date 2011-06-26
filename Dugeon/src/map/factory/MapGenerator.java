/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package map.factory;

import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import map.component.MapComponent;
import map.component.Maze;
import model.Coordinate;
import objects.ViewablePixel;

/**
 *
 * @author hoanggia
 */
public class MapGenerator {

    private Map<Coordinate, ViewablePixel> pixelsMap;
    private Collection<MapComponent> mapComponents;


    public MapGenerator() {
        pixelsMap = new TreeMap<Coordinate, ViewablePixel>();
    }

    private boolean addMapComponent(MapComponent component, Coordinate coordinate) {
        if (component.getViewablePixels() == null) {
            return false;
        }
        for (ViewablePixel p : component.getViewablePixels().values()) {
            p.getCoordinate().shiftCoordinate(coordinate.getX(), coordinate.getY());
            pixelsMap.put(p.getCoordinate(), p);
        }

        return true;
    }

    public Map<Coordinate, ViewablePixel> generateMap(){
        return new Maze(21, 21).getViewablePixels();
    }

//    private MapComponent generateRandomComponent(){
//        Random r=new Random();
//
//    }
}
