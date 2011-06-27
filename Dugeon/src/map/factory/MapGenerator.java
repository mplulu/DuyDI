/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package map.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import map.component.Cavern;
import map.component.Corridor;
import map.component.MapComponent;
import map.component.Maze;
import map.component.OvalRoom;
import map.component.RectangleRoom;
import model.Coordinate;
import objects.ViewablePixel;
import objects.Wall;
import objects.Way;

/**
 *
 * @author hoanggia
 */
public class MapGenerator {

    private static final int maxSideOfComponent = 42;
    private Map<Coordinate, ViewablePixel> pixelsMap;
    private List<MapComponent> mapComponents;

    public MapGenerator() {
        pixelsMap = new TreeMap<Coordinate, ViewablePixel>();
        mapComponents = new ArrayList<MapComponent>();
    }

    public Map<Coordinate, ViewablePixel> generateMap() {
        Random r = new Random();
        int numberOfComponent = r.nextInt(5);
        for (int i = 0; i < 4; i++) {
            MapComponent component = getRandomMapComponent();
            System.out.println(component.getClass().getName());
            Coordinate c = new Coordinate(maxSideOfComponent * i + r.nextInt(41), maxSideOfComponent * i + r.nextInt(41));
            if (i != 0) {

                Map<Coordinate, ViewablePixel> oldOuterWall = new HashMap<Coordinate, ViewablePixel>(getOuterWalls());
                Map<Coordinate, ViewablePixel> componentOuterWall = new HashMap<Coordinate, ViewablePixel>(component.getOuterWalls());


                shiftAllCoordinateTo(component, c);

                Coordinate[] points = getNearestPoints(oldOuterWall, componentOuterWall);
                MapComponent corridor = new Corridor(points[0], points[1]);

                pixelsMap.putAll(component.getViewablePixels());
                pixelsMap.putAll(corridor.getViewablePixels());


            } else {
                pixelsMap.putAll(component.getViewablePixels());


            }

        }
        //generateWalls();

        return pixelsMap;
    }

    private boolean isOverlap(Map<Coordinate, ViewablePixel> componetA, Map<Coordinate, ViewablePixel> componetB) {
        for (Coordinate cA : componetA.keySet()) {
            for (Coordinate cB : componetB.keySet()) {
                if (cA.equals(cB)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Coordinate[] getNearestPoints(Map<Coordinate, ViewablePixel> componetA, Map<Coordinate, ViewablePixel> componetB) {
        int minDistance = Integer.MAX_VALUE;
        Coordinate[] points = new Coordinate[2];
        for (Coordinate cA : componetA.keySet()) {
            for (Coordinate cB : componetB.keySet()) {
                if (minDistance > cA.distanceTo(cB)) {
                    minDistance = cA.distanceTo(cB);
                    points[0] = cA;
                    points[1] = cB;
                }
            }
        }
        return points;
    }

    private void generateWalls() {
        Map<Coordinate, ViewablePixel> waysNow = new HashMap<Coordinate, ViewablePixel>(getWays());
        for (ViewablePixel p : waysNow.values()) {

            if (p instanceof Way) {
                if (!pixelsMap.containsKey(new Coordinate(p.getCoordinate().getX() + 1, p.getCoordinate().getY()))) {
                    ViewablePixel wall = new Wall(new Coordinate(p.getCoordinate().getX() + 1, p.getCoordinate().getY()));
                    pixelsMap.put(wall.getCoordinate(), wall);

                }
                if (!pixelsMap.containsKey(new Coordinate(p.getCoordinate().getX(), p.getCoordinate().getY() + 1))) {
                    ViewablePixel wall = new Wall(new Coordinate(p.getCoordinate().getX(), p.getCoordinate().getY() + 1));
                    pixelsMap.put(wall.getCoordinate(), wall);
                }
                if (!pixelsMap.containsKey(new Coordinate(p.getCoordinate().getX() - 1, p.getCoordinate().getY()))) {
                    ViewablePixel wall = new Wall(new Coordinate(p.getCoordinate().getX() - 1, p.getCoordinate().getY()));
                    pixelsMap.put(wall.getCoordinate(), wall);
                }
                if (!pixelsMap.containsKey(new Coordinate(p.getCoordinate().getX(), p.getCoordinate().getY() - 1))) {
                    ViewablePixel wall = new Wall(new Coordinate(p.getCoordinate().getX(), p.getCoordinate().getY() - 1));
                    pixelsMap.put(wall.getCoordinate(), wall);
                }

            }
        }
    }

    private MapComponent getRandomMapComponent() {
        Random r = new Random();
        int randomIntForType = r.nextInt(4);
        int randomIntForRadius = limitRangeForValue(maxSideOfComponent / 2, 11, r.nextInt());
        int randomIntForWidth = limitRangeForValue(maxSideOfComponent, 11, r.nextInt());
        int randomIntForHeight = limitRangeForValue(maxSideOfComponent, 11, r.nextInt());
        MapComponent component;
        switch (randomIntForType) {
            case 0:
                component = new Cavern(randomIntForRadius);
                break;
            case 1:
                component = new Maze(randomIntForWidth, randomIntForHeight);
                break;
            case 2:
                component = new OvalRoom(randomIntForRadius);
                break;
            default:
                component = new RectangleRoom(randomIntForWidth, randomIntForHeight);
                break;
        }
        return component;
    }

    private int limitRangeForValue(int max, int min, int value) {
        if (max < value) {
            return max;
        }
        if (min > value) {
            return min;
        }
        return value;
    }

    private Map<Coordinate, ViewablePixel> getWalls() {
        Map<Coordinate, ViewablePixel> walls = new HashMap<Coordinate, ViewablePixel>();
        for (ViewablePixel p : pixelsMap.values()) {
            if (p instanceof Wall) {
                walls.put(p.getCoordinate(), p);
            }
        }
        return walls;
    }

    private Map<Coordinate, ViewablePixel> getOuterWalls() {
        Map<Coordinate, ViewablePixel> outerWalls = new HashMap<Coordinate, ViewablePixel>();

        for (ViewablePixel p : getWalls().values()) {
            Coordinate n = new Coordinate(p.getCoordinate().getX(), p.getCoordinate().getY() - 1);
            Coordinate e = new Coordinate(p.getCoordinate().getX() + 1, p.getCoordinate().getY());
            Coordinate w = new Coordinate(p.getCoordinate().getX() - 1, p.getCoordinate().getY());
            Coordinate s = new Coordinate(p.getCoordinate().getX(), p.getCoordinate().getY() + 1);
            if (!pixelsMap.containsKey(n) || !pixelsMap.containsKey(e) || !pixelsMap.containsKey(w) || !pixelsMap.containsKey(s)) {
                outerWalls.put(p.getCoordinate(), p);
            }
        }
        return outerWalls;
    }

    private Map<Coordinate, ViewablePixel> getWays() {

        Map<Coordinate, ViewablePixel> ways = new HashMap<Coordinate, ViewablePixel>();
        for (ViewablePixel p : pixelsMap.values()) {
            if (p instanceof Way) {
                ways.put(p.getCoordinate(), p);
            }
        }

        return ways;
    }

    private void shiftAllCoordinateTo(MapComponent component, Coordinate coordinate) {
        for (ViewablePixel p : component.getViewablePixels().values()) {
            p.getCoordinate().shiftCoordinate(coordinate.getX(), coordinate.getY());
        }
    }
}
