/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package map.component;

import java.util.HashMap;
import java.util.Map;
import model.Coordinate;
import objects.ViewablePixel;
import objects.Wall;
import objects.Way;

/**
 *
 * @author hoanggia
 */
public class OvalRoom implements MapComponent {

    private Map<Coordinate, ViewablePixel> viewablePixels;
    private Map<Coordinate, ViewablePixel> ways;
    private Map<Coordinate, ViewablePixel> walls;
    private ViewablePixel center;
    private int radius;

    public OvalRoom(int radius) {
        this.radius = radius;
        center = new Way(new Coordinate(radius, radius));
        viewablePixels = new HashMap<Coordinate, ViewablePixel>();
        putNextViewablePixel(center, 0);
        putNextViewablePixel(center, 1);
        putNextViewablePixel(center, 2);
        putNextViewablePixel(center, 3);

    }

    private void putNextViewablePixel(ViewablePixel lastPixel, int direction) {
        Coordinate c;
        switch (direction) {
            case 0:
                c = new Coordinate(lastPixel.getCoordinate().getX() + 1, lastPixel.getCoordinate().getY());
                break;
            case 1:
                c = new Coordinate(lastPixel.getCoordinate().getX(), lastPixel.getCoordinate().getY() + 1);
                break;
            case 2:
                c = new Coordinate(lastPixel.getCoordinate().getX() - 1, lastPixel.getCoordinate().getY());
                break;
            default:
                c = new Coordinate(lastPixel.getCoordinate().getX(), lastPixel.getCoordinate().getY() - 1);
                break;
        }
        if (!viewablePixels.containsKey(c) && c.getX() >= 0 && c.getX() <= radius * 2 && c.getY() <= radius * 2 && c.getY() >= 0) {
            //System.out.println("a");
            ViewablePixel p;
            if (center.getCoordinate().distanceTo(c) != radius) {
                p = new Way(c);
                viewablePixels.put(c, p);
                putNextViewablePixel(p, 0);
                putNextViewablePixel(p, 1);
                putNextViewablePixel(p, 2);
                putNextViewablePixel(p, 3);
            } else {
                p = new Wall(c);
                viewablePixels.put(c, p);
            }
        }
    }

    public Map<Coordinate, ViewablePixel> getViewablePixels() {
        return viewablePixels;
    }

    public Map<Coordinate, ViewablePixel> getWays() {
        if (ways == null) {
            ways = new HashMap<Coordinate, ViewablePixel>();
            for (ViewablePixel p : viewablePixels.values()) {
                if (p instanceof Way) {
                    ways.put(p.getCoordinate(), p);
                }
            }
        }
        return ways;
    }

    public Map<Coordinate, ViewablePixel> getWalls() {

        walls = new HashMap<Coordinate, ViewablePixel>();
        for (ViewablePixel p : viewablePixels.values()) {
            if (p instanceof Wall) {
                walls.put(p.getCoordinate(), p);
            }
        }

        return walls;
    }

    public Map<Coordinate, ViewablePixel> getOuterWalls() {

        walls = new HashMap<Coordinate, ViewablePixel>();
        for (ViewablePixel p : viewablePixels.values()) {
            if (p instanceof Wall) {
                walls.put(p.getCoordinate(), p);
            }
        }

        return walls;
    }
}
