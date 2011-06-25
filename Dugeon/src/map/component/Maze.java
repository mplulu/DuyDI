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
import map.factory.Coordinate;
import map.factory.Pixel;
import objects.Wall;
import objects.Way;

/**
 *
 * @author hoanggia
 */
public class Maze implements MapComponent{

    private int width, height;
    private Map<Coordinate, Pixel> pixels;
    private Map<Coordinate, Pixel> ways;
    private Set<Coordinate> visitedWays;
    private Map<Coordinate, Pixel> walls;

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new HashMap<Coordinate, Pixel>();
        ways = new HashMap<Coordinate, Pixel>();
        walls = new HashMap<Coordinate, Pixel>();
        visitedWays = new HashSet<Coordinate>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Pixel p;
                if (j % 2 == 0 || i % 2 == 0) {
                    p = new Pixel(new Coordinate(j, i), new Wall());
                    walls.put(new Coordinate(j, i), p);
                } else {
                    p = new Pixel(new Coordinate(j, i), new Way());
                    ways.put(new Coordinate(j, i), p);

                }
                pixels.put(new Coordinate(j, i), p);
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
                    ways.put(wallCoordinate, new Pixel(wallCoordinate, new Way()));
                    pixels.put(wallCoordinate, new Pixel(wallCoordinate, new Way()));
                    visitedWays.add(new Coordinate(nx, ny));
                    carveWay(new Coordinate(nx, ny));
                }
            }
        }



    }

    public Map<Coordinate, Pixel> getPixels() {
        return pixels;
    }

    public Map<Coordinate, Pixel> getWalls() {

        return walls;
    }

    public Map<Coordinate, Pixel> getWays() {
        return ways;
    }

    public Map<Coordinate, Pixel> getOuterWalls() {
        Map<Coordinate ,Pixel> outerWalls=new HashMap<Coordinate,Pixel>();
        for(Pixel p:walls.values()){
            if(p.getCoordinate().getX()==0||p.getCoordinate().getY()==0||p.getCoordinate().getX()==width-1||p.getCoordinate().getX()==height-1){
                outerWalls.put(p.getCoordinate(), p);
            }
        }
        return outerWalls;
    }
}
