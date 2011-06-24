/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dugeon;

import map.factory.Coordinate;
import map.factory.MapView;
import console.MainConsole;
import java.awt.Color;
import javax.swing.JFrame;
import map.factory.Pixel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import objects.Wall;

/**
 *
 * @author hoanggia
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MapView map = generateMap();

        JFrame frame = new JFrame();
        MainConsole console = new MainConsole(map);

        frame.add(console);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static MapView generateMap() {
        Map<Coordinate, Pixel> pixels = new TreeMap<Coordinate, Pixel>();
        int width = 20, height = 20;

        // Generate walls
        List<Pixel> wall = generateHorizontalWall(
                new Coordinate(0, 0),
                width,
                0,
                height / 2);
        Coordinate lastCoordinate = wall.get(wall.size() - 1).getCoordinate();
        // Add to map
        for (Pixel p : wall) {
            pixels.put(p.getCoordinate(), p);
        }
        // Fill floor
        fillFloorTopToBottom(pixels, wall, height - 1);

        wall = generateVerticalWall(
                lastCoordinate,
                height,
                width / 2,
                width - 1);
        lastCoordinate = wall.get(wall.size() - 1).getCoordinate();
        // Add to map
        for (Pixel p : wall) {
            pixels.put(p.getCoordinate(), p);
        }
        // Fill floor
        fillFloorRightToLeft(pixels, wall, 0);

        wall = generateVerticalWall(
                new Coordinate(0, 1),
                height,
                0,
                width / 2);
        Coordinate beginCoordinate = wall.get(wall.size() - 1).getCoordinate();
        // Add to map
        for (Pixel p : wall) {
            pixels.put(p.getCoordinate(), p);
        }
        // Fill floor
        fillFloorLeftToRight(pixels, wall, width - 1);

        wall = generateHorizontalWall(
                beginCoordinate,
                (lastCoordinate.getX() - beginCoordinate.getX()) + 1,
                lastCoordinate.getY() - 1,
                height - 1);
        // Add to map
        for (Pixel p : wall) {
            pixels.put(p.getCoordinate(), p);
        }
        // Fill floor
        fillFloorBottomToTop(pixels, wall, 0);

        MapView map = new MapView(pixels);
        return map;
    }

    private static List<Pixel> generateHorizontalWall(Coordinate beginCoor, int wallLength, int minY, int maxY) {
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

    private static MapView generateShapeMap() {
        Map<Coordinate, Pixel> pixels = new TreeMap<Coordinate, Pixel>();
        int width = 20, height = 20, minSegmentLength = 2;

        // Generate walls
        List<Pixel> wall = generateShapeHorizontalWall(
                new Coordinate(0, 0),
                width,
                minSegmentLength,
                0,
                height / 2);
        Coordinate lastCoordinate = wall.get(wall.size() - 1).getCoordinate();
        // Add to map
        for (Pixel p : wall) {
            pixels.put(p.getCoordinate(), p);
        }
        // Fill floor
        fillFloorTopToBottom(pixels, wall, height - 1);

        wall = generateShapeVerticalWall(
                lastCoordinate,
                height,
                minSegmentLength,
                width / 2,
                width - 1);
        lastCoordinate = wall.get(wall.size() - 1).getCoordinate();
        // Add to map
        for (Pixel p : wall) {
            pixels.put(p.getCoordinate(), p);
        }
        // Fill floor
        fillFloorRightToLeft(pixels, wall, 0);

        wall = generateShapeVerticalWall(
                new Coordinate(0, 1),
                height,
                minSegmentLength,
                0,
                width / 2);
        Coordinate beginCoordinate = wall.get(wall.size() - 1).getCoordinate();
        // Add to map
        for (Pixel p : wall) {
            pixels.put(p.getCoordinate(), p);
        }
        // Fill floor
        fillFloorLeftToRight(pixels, wall, width - 1);

        wall = generateShapeHorizontalWall(
                beginCoordinate,
                (lastCoordinate.getX() - beginCoordinate.getX()),
                minSegmentLength,
                lastCoordinate.getY() - 1,
                height - 1);
        // Add to map
        for (Pixel p : wall) {
            pixels.put(p.getCoordinate(), p);
        }
        // Fill floor
        fillFloorBottomToTop(pixels, wall, 0);

        MapView map = new MapView(pixels);
        return map;
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

    private static void fillFloorTopToBottom(Map<Coordinate, Pixel> map, List<Pixel> walls, int maxY) {
        for (Pixel wall : walls) {
            Coordinate c = wall.getCoordinate();
            int x = c.getX();

            for (int y = c.getY(); y <= maxY; ++y) {
                System.out.println("maxY: " + maxY + " y: " + y);
                increaseFloorCounter(map, new Coordinate(x, y));
            }
        }
    }

    private static void fillFloorBottomToTop(Map<Coordinate, Pixel> map, List<Pixel> walls, int minY) {
        for (Pixel wall : walls) {
            Coordinate c = wall.getCoordinate();
            int x = c.getX();

            for (int y = c.getY(); y >= minY; --y) {
                System.out.println("minY: " + minY + " y: " + y);
                increaseFloorCounter(map, new Coordinate(x, y));
            }
        }
    }

    private static void fillFloorLeftToRight(Map<Coordinate, Pixel> map, List<Pixel> walls, int maxX) {
        for (Pixel wall : walls) {
            Coordinate c = wall.getCoordinate();
            int y = c.getY();

            for (int x = c.getX(); x <= maxX; x++) {
                System.out.println("maxX: " + maxX + " x: " + x);
                increaseFloorCounter(map, new Coordinate(x, y));
            }
        }
    }

    private static void fillFloorRightToLeft(Map<Coordinate, Pixel> map, List<Pixel> walls, int minX) {
        for (Pixel wall : walls) {
            Coordinate c = wall.getCoordinate();
            int y = c.getY();

            for (int x = c.getX(); x >= minX; --x) {
                System.out.println("minX: " + minX + " x: " + x);
                increaseFloorCounter(map, new Coordinate(x, y));
            }
        }
    }

    private static void increaseFloorCounter(Map<Coordinate, Pixel> map, Coordinate coor) {
        Pixel p = map.get(coor);
        if (p != null) {
            Object o = p.getObject();
            int value = 1;

            if (o != null) {
                try {
                    Integer i = (Integer) o;
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
}
