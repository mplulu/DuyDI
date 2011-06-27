/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package map.component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import model.Constant;
import model.Coordinate;
import objects.ViewablePixel;
import objects.Wall;
import objects.Way;

/**
 *
 * @author hoanggia
 */
public class Cavern implements MapComponent {

    private Map<Coordinate, ViewablePixel> viewablePixels;
    private Map<Coordinate, ViewablePixel> ways;
    private Map<Coordinate, ViewablePixel> walls;
    private Map<Coordinate, ViewablePixel> outerWalls;
    private int radius;
    private ViewablePixel center;

    public Cavern(int radius) {
        this.radius = radius;
        center = new Way(new Coordinate(radius, radius));
        viewablePixels = new HashMap<Coordinate, ViewablePixel>();
        viewablePixels.put(center.getCoordinate(), center);
        putNextViewablePixel(center, Constant.NORTH);
        putNextViewablePixel(center, Constant.EAST);
        putNextViewablePixel(center, Constant.WEST);
        putNextViewablePixel(center, Constant.SOUTH);
    }

    private void putNextViewablePixel(ViewablePixel lastPixel, int direction) {

        Coordinate c;
        switch (direction) {
            case Constant.NORTH:
                c = new Coordinate(lastPixel.getCoordinate().getX() + 1, lastPixel.getCoordinate().getY());
                break;
            case Constant.EAST:
                c = new Coordinate(lastPixel.getCoordinate().getX(), lastPixel.getCoordinate().getY() + 1);
                break;
            case Constant.WEST:
                c = new Coordinate(lastPixel.getCoordinate().getX() - 1, lastPixel.getCoordinate().getY());
                break;
            default:
                c = new Coordinate(lastPixel.getCoordinate().getX(), lastPixel.getCoordinate().getY() - 1);
                break;
        }
        if (!viewablePixels.containsKey(c) && c.getX() >= 0 && c.getX() < radius * 2 && c.getY() < radius * 2 && c.getY() >= 0) {
            //System.out.println("a");
            Random r = new Random();
            boolean isWay = r.nextInt(radius - 1) > lastPixel.getCoordinate().distanceTo(center.getCoordinate()) - 3;
            ViewablePixel p;
            if (isWay) {
                p = new Way(c);
                viewablePixels.put(c, p);
                putNextViewablePixel(p, Constant.NORTH);
                putNextViewablePixel(p, Constant.EAST);
                putNextViewablePixel(p, Constant.WEST);
                putNextViewablePixel(p, Constant.SOUTH);
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
        if (walls == null) {
            walls = new HashMap<Coordinate, ViewablePixel>();
            for (ViewablePixel p : viewablePixels.values()) {
                if (p instanceof Wall) {
                    walls.put(p.getCoordinate(), p);
                }
            }
        }
        return walls;
    }

    public Map<Coordinate, ViewablePixel> getOuterWalls() {
        if (outerWalls == null) {
            outerWalls = new HashMap<Coordinate, ViewablePixel>();

            for (ViewablePixel p : getWalls().values()) {
                Coordinate n = new Coordinate(p.getCoordinate().getX(), p.getCoordinate().getY() - 1);
                Coordinate e = new Coordinate(p.getCoordinate().getX() + 1, p.getCoordinate().getY());
                Coordinate w = new Coordinate(p.getCoordinate().getX() - 1, p.getCoordinate().getY());
                Coordinate s = new Coordinate(p.getCoordinate().getX(), p.getCoordinate().getY() + 1);
                if (!viewablePixels.containsKey(n) || !viewablePixels.containsKey(e) || !viewablePixels.containsKey(w) || !viewablePixels.containsKey(s)) {
                    outerWalls.put(p.getCoordinate(), p);
                }
            }
        }
        return outerWalls;
    }
}
