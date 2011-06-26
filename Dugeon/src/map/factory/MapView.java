/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package map.factory;

import model.Coordinate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import map.component.MapComponent;
import objects.ViewablePixel;

/**
 *
 * @author hoanggia
 */
public class MapView {

    private Map<Coordinate, ViewablePixel> pixelsMap;
    private Collection<Coordinate> ways;

    public MapView(MapComponent mapComponent) {
        this.pixelsMap = mapComponent.getViewablePixels();
        this.ways = mapComponent.getWays().keySet();
    }

    public MapView() {
        pixelsMap = new TreeMap<Coordinate, ViewablePixel>();
        ways = new ArrayList<Coordinate>();
    }

    public Map<Coordinate, ViewablePixel> getViewablePixelsMap() {
        return pixelsMap;
    }

    public void setViewablePixelsMap(Map<Coordinate, ViewablePixel> pixelsMap) {
        this.pixelsMap = pixelsMap;
    }

    public Collection<Coordinate> getWays() {
        return ways;
    }

    public void setWays(List<Coordinate> ways) {
        this.ways = ways;
    }

    public boolean addMapComponent(MapComponent component, Coordinate coordinate) {
        if (component.getViewablePixels() == null) {
            return false;
        }
        for (ViewablePixel p : component.getViewablePixels().values()) {
            p.getCoordinate().shiftCoordinate(coordinate.getX(), coordinate.getY());
            pixelsMap.put(p.getCoordinate(), p);
        }

        return true;

    }
}
