/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package map.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import map.factory.Coordinate;
import map.factory.Pixel;
import objects.Wall;
import objects.Way;

/**
 *
 * @author hoanggia
 */
public class Cavern implements MapComponent{
    private Map<Coordinate, Pixel> pixels;
    private  int width;
    private  int height;

    public Cavern(int width,int height){
        this.width=width;
        this.height=height;
        pixels=Cavern.generateMap(width,height);
    }


private static Map<Coordinate,Pixel> generateMap(int width,int height) {
        Map<Coordinate, Pixel> pixels = new HashMap<Coordinate, Pixel>();
        int minX = 0, maxX = width - 1;
        int minY = 0, maxY = height - 1;

        // Generate walls
        List<Pixel> wall = generateHorizontalWall(
                new Coordinate(0, 0),
                width,
                minY,
                height / 2);
        Coordinate lastCoordinate = wall.get(wall.size() - 1).getCoordinate();
        // Add to map
        for (Pixel p : wall) {
            pixels.put(p.getCoordinate(), p);
        }
        // Fill floor
        detectWaysTopToBottom(pixels, minX, maxX, minY, maxY);

        wall = generateVerticalWall(
                lastCoordinate,
                height,
                width / 2,
                maxX);
        lastCoordinate = wall.get(wall.size() - 1).getCoordinate();
        // Add to map
        for (Pixel p : wall) {
            pixels.put(p.getCoordinate(), p);
        }
        // Fill floor
        detectWaysRightToLeft(pixels, minX, maxX, minY, maxY);

        wall = generateVerticalWall(
                new Coordinate(0, 1),
                height,
                minX,
                width / 2);
        Coordinate beginCoordinate = wall.get(wall.size() - 1).getCoordinate();
        // Add to map
        for (Pixel p : wall) {
            pixels.put(p.getCoordinate(), p);
        }
        // Fill floor
        detectWaysLeftToRight(pixels, minX, maxX, minY, maxY);

        wall = generateHorizontalWall(
                beginCoordinate,
                (lastCoordinate.getX() - beginCoordinate.getX()) + 2,
                lastCoordinate.getY() - 1,
                maxY);
        // Add to map
        for (Pixel p : wall) {
            pixels.put(p.getCoordinate(), p);
        }
        // Fill floor
        detectWaysBottomToTop(pixels, minX, maxX, minY, maxY);

        List<Coordinate> ways = fillWays(pixels, minX, maxX, minY, maxY);
        return pixels;
    }

    private static List<Pixel> generateHorizontalWall(
            Coordinate beginCoor, int wallLength, int minY, int maxY) {
        Random random = new Random();
        List<Pixel> pixels = new ArrayList<Pixel>();

        for (int x = beginCoor.getX(), y = beginCoor.getY(); x < wallLength;) {
            pixels.add(
                    new Pixel(
                    new Coordinate(x, y), new Wall()));

            int r = random.nextInt(3) - 1;
            x++;
            y += r;

            if (y < minY) {
                y = minY;
            }

            if (y > maxY) {
                y = maxY;
            }
        }

        return pixels;
    }

    private static List<Pixel> generateVerticalWall(
            Coordinate beginCoor,
            int wallLength,
            int minX,
            int maxX) {

        Random random = new Random();
        List<Pixel> pixels = new ArrayList<Pixel>();

        for (int x = beginCoor.getX(), y = beginCoor.getY();
                y < wallLength;) {
            pixels.add(
                    new Pixel(
                    new Coordinate(x, y), new Wall()));

            int r = random.nextInt(3) - 1;
            y++;
            x += r;

            if (x < minX) {
                x = minX;
            }

            if (x > maxX) {
                x = maxX;
            }
        }

        return pixels;
    }

    private static List<Pixel> generateShapeHorizontalWall(
            Coordinate beginCoor,
            int wallLength,
            int minSegmentLength,
            int minY,
            int maxY) {

        Random random = new Random();
        List<Pixel> pixels = new ArrayList<Pixel>();

        for (int x = beginCoor.getX(), y = beginCoor.getY(), segmentLenght = 0; x < wallLength; ++x, ++segmentLenght) {
            pixels.add(
                    new Pixel(
                    new Coordinate(x, y), new Wall()));

            if (segmentLenght >= minSegmentLength) {
                segmentLenght = 0;
                if (random.nextBoolean()) {
                    int r = random.nextInt(3) - 1;
                    y += r;

                    if (y < minY) {
                        y = minY;
                    }

                    if (y > maxY) {
                        y = maxY;
                    }
                }
            }
        }

        return pixels;
    }

    private static List<Pixel> generateShapeVerticalWall(
            Coordinate beginCoor,
            int wallLength,
            int minSegmentLength,
            int minX,
            int maxX) {

        Random random = new Random();
        List<Pixel> pixels = new ArrayList<Pixel>();

        for (int x = beginCoor.getX(), y = beginCoor.getY(), segmentLength = 0;
                y < wallLength; ++y, ++segmentLength) {
            pixels.add(
                    new Pixel(
                    new Coordinate(x, y), new Wall()));

            if (segmentLength >= minSegmentLength) {
                segmentLength = 0;
                if (random.nextBoolean()) {
                    int r = random.nextInt(3) - 1;
                    x += r;


                    if (x < minX) {
                        x = minX;
                    }

                    if (x > maxX) {
                        x = maxX;
                    }
                }
            }
        }

        return pixels;
    }

    private static void detectWaysTopToBottom(
            Map<Coordinate, Pixel> map,
            int minX, int maxX,
            int minY, int maxY) {

        for (int x = minX; x <= maxX; ++x) {
            boolean gotWall = false;
            for (int y = minY; y <= maxY; ++y) {
                Coordinate c = new Coordinate(x, y);
                Pixel p = map.get(c);

                if (!gotWall) {
                    if (p != null && p.getObject() instanceof Wall) {
                        gotWall = true;
                    }
                } else {
                    increaseWayCounter(map, c);
                }
            }
            gotWall = false;
        }
    }

    private static void detectWaysBottomToTop(
            Map<Coordinate, Pixel> map,
            int minX, int maxX,
            int minY, int maxY) {

        for (int x = minX; x <= maxX; ++x) {
            boolean gotWall = false;
            for (int y = maxY; y >= minY; --y) {
                Coordinate c = new Coordinate(x, y);
                Pixel p = map.get(c);

                if (!gotWall) {
                    if (p != null && p.getObject() instanceof Wall) {
                        gotWall = true;
                    }
                } else {
                    increaseWayCounter(map, c);
                }
            }
            gotWall = false;
        }
    }

    private static void detectWaysLeftToRight(
            Map<Coordinate, Pixel> map,
            int minX, int maxX,
            int minY, int maxY) {

        for (int y = minY; y < maxY; ++y) {
            boolean gotWall = false;
            for (int x = minX; x <= maxX; ++x) {
                Coordinate c = new Coordinate(x, y);
                Pixel p = map.get(c);

                if (!gotWall) {
                    if (p != null && p.getObject() instanceof Wall) {
                        gotWall = true;
                    }
                } else {
                    increaseWayCounter(map, c);
                }
            }
            gotWall = false;
        }
    }

    private static void detectWaysRightToLeft(
            Map<Coordinate, Pixel> map,
            int minX, int maxX,
            int minY, int maxY) {

        for (int y = minY; y < maxY; ++y) {
            boolean gotWall = false;
            for (int x = maxX; x >= minX; --x) {
                Coordinate c = new Coordinate(x, y);
                Pixel p = map.get(c);

                if (!gotWall) {
                    if (p != null && p.getObject() instanceof Wall) {
                        gotWall = true;
                    }
                } else {
                    increaseWayCounter(map, c);
                }
            }
            gotWall = false;
        }
    }

    private static void increaseWayCounter(Map<Coordinate, Pixel> map, Coordinate coor) {
        Pixel p = map.get(coor);
        if (p != null) {
            int value = 1;

            if (!p.isEmpty()) {
                try {
                    Integer i = (Integer) p.getObject();
                    value = i.intValue() + 1;
                } catch (Exception ex) {
                    //ignore this cell
                    return;
                }
            }

            p.setObject(new Integer(value));
        } else {
            p = new Pixel(coor, new Integer(1));
            map.put(coor, p);
        }
    }

    private static List<Coordinate> fillWays(
            Map<Coordinate, Pixel> map,
            int minX, int maxX,
            int minY, int maxY) {

        List<Coordinate> ways = new ArrayList<Coordinate>();
        for (int x = minX; x <= maxX; ++x) {
            for (int y = minY; y <= maxY; ++y) {
                Coordinate c = new Coordinate(x, y);
                Pixel p = map.get(c);
                if (p != null && !p.isEmpty()) {
                    try {
                        int i = ((Integer) p.getObject()).intValue();

                        if (i == 4) {
                            p.setObject(new Way());
                            ways.add(c);
                        } else {
                            p.setObject(null);
                        }
                    } catch (Exception e) {
                        //ignore this cell
                    }
                }
            }
        }

        return ways;
    }

    public Map<Coordinate, Pixel> getPixels() {
        return pixels;
    }

    public Map<Coordinate, Pixel> getWays() {
        Map<Coordinate,Pixel> ways=new HashMap<Coordinate,Pixel>();
        for(Pixel p:pixels.values()){
            if(p.getObject() instanceof Way){
                ways.put(p.getCoordinate(), p);
            }
        }
        return ways;
    }

    public Map<Coordinate, Pixel> getWalls() {
        Map<Coordinate,Pixel> ways=new HashMap<Coordinate,Pixel>();
        for(Pixel p:pixels.values()){
            if(p.getObject() instanceof Wall){
                ways.put(p.getCoordinate(), p);
            }
        }
        return ways;
    }

    public Map<Coordinate, Pixel> getOuterWalls() {
        Map<Coordinate,Pixel> ways=new HashMap<Coordinate,Pixel>();

        return ways;
    }
}
