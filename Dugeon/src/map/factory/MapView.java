/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package map.factory;

import java.util.ArrayList;
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
    private List<Coordinate> ways;

    public MapView(Map<Coordinate, Pixel> pixelsMap, List<Coordinate> ways) {
        this.pixelsMap = pixelsMap;
        this.ways = ways;
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

    public List<Coordinate> getWays() {
        return ways;
    }

    public void setWays(List<Coordinate> ways) {
        this.ways = ways;
    }

    public boolean addMapComponent(MapComponent component, Coordinate coordinate) {
        if (component.getPixels() == null) {
            return false;
        }
        for (Pixel p : component.getPixels()) {
            p.getCoordinate().shiftCoordinate(coordinate.getX(), coordinate.getY());
            pixelsMap.put(p.getCoordinate(), p);
        }

        return true;

    }
}
