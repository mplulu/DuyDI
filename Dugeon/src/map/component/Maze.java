/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package map.component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import model.Constant;
import model.Coordinate;
import objects.ViewablePixel;
import objects.Wall;
import objects.Way;

/**
 *
 * @author hoanggia
 */
public class Maze implements MapComponent {

    private int width, height;
    private Map<Coordinate, ViewablePixel> viewablePixels;
    private Map<Coordinate, ViewablePixel> ways;
    private Set<Coordinate> visitedWays;
    private Map<Coordinate, ViewablePixel> walls;

    public Maze(int width, int height) {
        this.width = width % 2 == 0 ? width + 1 : width;
        this.height = height % 2 == 0 ? height + 1 : height;
        viewablePixels = new HashMap<Coordinate, ViewablePixel>();
        ways = new HashMap<Coordinate, ViewablePixel>();
        walls = new HashMap<Coordinate, ViewablePixel>();
        visitedWays = new HashSet<Coordinate>();
        generateBasePixels();


        Coordinate c = (Coordinate) (getWays().keySet().iterator().next());
        carveWay(c);


    }

    private void generateBasePixels() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                ViewablePixel p;
                if (j % 2 == 0 || i % 2 == 0) {
                    p = new Wall(new Coordinate(j, i));

                } else {
                    p = new Way(new Coordinate(j, i));

                }
                viewablePixels.put(new Coordinate(j, i), p);
            }
        }
    }

    private void carveWay(Coordinate atCoordinate) {
        //0:n
        //1:e
        //2:w
        //3:s

        Set<Integer> directions = new HashSet<Integer>();
        directions.add(new Integer(0));
        directions.add(new Integer(1));
        directions.add(new Integer(2));
        directions.add(new Integer(3));

        Iterator i = directions.iterator();
        while (!directions.isEmpty()) {
            int nx, ny;
            Random r = new Random();
            int direction = r.nextInt(4);
            if (directions.contains(new Integer(direction))) {
                directions.remove(new Integer(direction));
                switch (direction) {
                    case Constant.NORTH:
                        nx = atCoordinate.getX();
                        ny = atCoordinate.getY() - 2;
                        break;
                    case Constant.EAST:
                        nx = atCoordinate.getX() + 2;
                        ny = atCoordinate.getY();
                        break;
                    case Constant.WEST:
                        nx = atCoordinate.getX() - 2;
                        ny = atCoordinate.getY();
                        break;
                    default:
                        nx = atCoordinate.getX();
                        ny = atCoordinate.getY() + 2;
                        break;
                }
                if (0 <= nx && nx < width && 0 <= ny && ny < height && !visitedWays.contains(new Coordinate(nx, ny))) {
                    Coordinate wallCoordinate = new Coordinate((nx + atCoordinate.getX()) / 2, (ny + atCoordinate.getY()) / 2);


                    viewablePixels.put(wallCoordinate, new Way(wallCoordinate));
                    visitedWays.add(new Coordinate(nx, ny));
                    carveWay(new Coordinate(nx, ny));
                }
            }
        }



    }

    public Map<Coordinate, ViewablePixel> getViewablePixels() {
        return viewablePixels;
    }

    public Map<Coordinate, ViewablePixel> getWays() {
        ways = new HashMap<Coordinate, ViewablePixel>();
        for (ViewablePixel p : viewablePixels.values()) {
            if (p instanceof Way) {
                ways.put(p.getCoordinate(), p);
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
        Map<Coordinate, ViewablePixel> outerWalls = new HashMap<Coordinate, ViewablePixel>();
        for (ViewablePixel p : getWalls().values()) {
            if (p.getCoordinate().getX() == 0 || p.getCoordinate().getY() == 0 || p.getCoordinate().getX() == width - 1 || p.getCoordinate().getX() == height - 1) {
                outerWalls.put(p.getCoordinate(), p);
            }
        }
        return outerWalls;
    }
}
