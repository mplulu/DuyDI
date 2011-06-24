/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dugeon;

import map.factory.Coordinate;
import map.factory.MapView;
import console.MainConsole;
import javax.swing.JFrame;
import map.factory.Pixel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import objects.Wall;
import objects.Way;

/**
 *
 * @author hoanggia
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
        detectFloorTopToBottom(pixels, minX, maxX, minY, maxY);

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
        detectFloorRightToLeft(pixels, minX, maxX, minY, maxY);

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
        detectFloorLeftToRight(pixels, minX, maxX, minY, maxY);

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
        detectFloorBottomToTop(pixels, minX, maxX, minY, maxY);

        fillFloor(pixels, minX, maxX, minY, maxY);
        MapView map = new MapView(pixels);
        return map;
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

    private static MapView generateShapeMap() {
        Map<Coordinate, Pixel> pixels = new TreeMap<Coordinate, Pixel>();
        int width = 20, height = 20, minSegmentLength = 2;
        int minX = 0, maxX = width - 1;
        int minY = 0, maxY = height - 1;

        // Generate walls
        List<Pixel> wall = generateShapeHorizontalWall(
                new Coordinate(0, 0),
                width,
                minSegmentLength,
                minY,
                height / 2);
        Coordinate lastCoordinate = wall.get(wall.size() - 1).getCoordinate();
        // Add to map
        for (Pixel p : wall) {
            pixels.put(p.getCoordinate(), p);
        }
        // Fill floor
        detectFloorTopToBottom(pixels, minX, maxX, minY, maxY);

        wall = generateShapeVerticalWall(
                lastCoordinate,
                height,
                minSegmentLength,
                width / 2,
                maxX);
        lastCoordinate = wall.get(wall.size() - 1).getCoordinate();
        // Add to map
        for (Pixel p : wall) {
            pixels.put(p.getCoordinate(), p);
        }
        // Fill floor
        detectFloorRightToLeft(pixels, minX, maxX, minY, maxY);

        wall = generateShapeVerticalWall(
                new Coordinate(0, 1),
                height,
                minSegmentLength,
                minX,
                width / 2);
        Coordinate beginCoordinate = wall.get(wall.size() - 1).getCoordinate();
        // Add to map
        for (Pixel p : wall) {
            pixels.put(p.getCoordinate(), p);
        }
        // Fill floor
        detectFloorLeftToRight(pixels, minX, maxX, minY, maxY);

        wall = generateShapeHorizontalWall(
                beginCoordinate,
                (lastCoordinate.getX() - beginCoordinate.getX()) + 2,
                minSegmentLength,
                lastCoordinate.getY() - 1,
                maxY);
        // Add to map
        for (Pixel p : wall) {
            pixels.put(p.getCoordinate(), p);
        }
        // Fill floor
        detectFloorBottomToTop(pixels, minX, maxX, minY, maxY);

        // fill floors
        fillFloor(pixels, minX, maxX, minY, maxY);
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

    private static void detectFloorTopToBottom(
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
                    increaseFloorCounter(map, c);
                }
            }
            gotWall = false;
        }
    }

    private static void detectFloorBottomToTop(
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
                    increaseFloorCounter(map, c);
                }
            }
            gotWall = false;
        }
    }

    private static void detectFloorLeftToRight(
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
                    increaseFloorCounter(map, c);
                }
            }
            gotWall = false;
        }
    }

    private static void detectFloorRightToLeft(
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
                    increaseFloorCounter(map, c);
                }
            }
            gotWall = false;
        }
    }

    private static void increaseFloorCounter(Map<Coordinate, Pixel> map, Coordinate coor) {
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

    private static void fillFloor(
            Map<Coordinate, Pixel> map,
            int minX, int maxX,
            int minY, int maxY) {

        for (int x = minX; x <= maxX; ++x) {
            for (int y = minY; y <= maxY; ++y) {
                Coordinate c = new Coordinate(x, y);
                Pixel p = map.get(c);
                if (p != null && !p.isEmpty()) {
                    try {
                        int i = ((Integer) p.getObject()).intValue();

                        if (i == 4) {
                            p.setObject(new Way());
                        } else {
                            p.setObject(null);
                        }
                    } catch (Exception e) {
                        //ignore this cell
                    }
                }
            }
        }
    }
}
