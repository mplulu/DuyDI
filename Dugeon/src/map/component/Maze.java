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
import model.Coordinate;
import objects.ViewablePixel;
import objects.Wall;
import objects.Way;

/**
 *
 * @author hoanggia
 */
public class Maze implements MapComponent{

    private int width, height;
    private Map<Coordinate, ViewablePixel> ViewablePixels;
    private Map<Coordinate, ViewablePixel> ways;
    private Set<Coordinate> visitedWays;
    private Map<Coordinate, ViewablePixel> walls;

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        ViewablePixels = new HashMap<Coordinate, ViewablePixel>();
        ways = new HashMap<Coordinate, ViewablePixel>();
        walls = new HashMap<Coordinate, ViewablePixel>();
        visitedWays = new HashSet<Coordinate>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                ViewablePixel p;
                if (j % 2 == 0 || i % 2 == 0) {
                    p = new Wall(new Coordinate(j, i));
                    walls.put(new Coordinate(j, i), p);
                } else {
                    p = new Way(new Coordinate(j, i));
                    ways.put(new Coordinate(j, i), p);

                }
                ViewablePixels.put(new Coordinate(j, i), p);
            }
        }
        //Coordinate c=(Coordinate)(ways.keySet().iterator().next());
        carveWay(new Coordinate(11, 11));


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
                    case 0:
                        nx = atCoordinate.getX();
                        ny = atCoordinate.getY() - 2;
                        break;
                    case 1:
                        nx = atCoordinate.getX() + 2;
                        ny = atCoordinate.getY();
                        break;
                    case 2:
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
                    walls.remove(wallCoordinate);

                    ways.put(wallCoordinate, new Way(wallCoordinate));
                    ViewablePixels.put(wallCoordinate, new Way(wallCoordinate));
                    visitedWays.add(new Coordinate(nx, ny));
                    carveWay(new Coordinate(nx, ny));
                }
            }
        }



    }

    public Map<Coordinate, ViewablePixel> getViewablePixels() {
        return ViewablePixels;
    }

    public Map<Coordinate, ViewablePixel> getWalls() {

        return walls;
    }

    public Map<Coordinate, ViewablePixel> getWays() {
        return ways;
    }

    public Map<Coordinate, ViewablePixel> getOuterWalls() {
        Map<Coordinate ,ViewablePixel> outerWalls=new HashMap<Coordinate,ViewablePixel>();
        for(ViewablePixel p:walls.values()){
            if(p.getCoordinate().getX()==0||p.getCoordinate().getY()==0||p.getCoordinate().getX()==width-1||p.getCoordinate().getX()==height-1){
                outerWalls.put(p.getCoordinate(), p);
            }
        }
        return outerWalls;
    }

    public Map<Coordinate, ViewablePixel> getViewablePixel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
