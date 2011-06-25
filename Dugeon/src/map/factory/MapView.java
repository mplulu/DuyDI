/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package map.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import map.component.MapComponent;

/**
 *
 * @author hoanggia
 */
public class MapView {

    private Map<Coordinate, Pixel> pixelsMap;
    private Collection<Coordinate> ways;

    public MapView(MapComponent mapComponent) {
        this.pixelsMap = mapComponent.getPixels();
        this.ways = mapComponent.getWays().keySet();
    }

    public MapView() {
        pixelsMap = new TreeMap<Coordinate, Pixel>();
        ways = new ArrayList<Coordinate>();
    }

    public Map<Coordinate, Pixel> getPixelsMap() {
        return pixelsMap;
    }

    public void setPixelsMap(Map<Coordinate, Pixel> pixelsMap) {
        this.pixelsMap = pixelsMap;
    }

    public Collection<Coordinate> getWays() {
        return ways;
    }

    public void setWays(List<Coordinate> ways) {
        this.ways = ways;
    }

    public boolean addMapComponent(MapComponent component, Coordinate coordinate) {
        if (component.getPixels() == null) {
            return false;
        }
        for (Pixel p : component.getPixels().values()) {
            p.getCoordinate().shiftCoordinate(coordinate.getX(), coordinate.getY());
            pixelsMap.put(p.getCoordinate(), p);
        }

        return true;

    }
}
