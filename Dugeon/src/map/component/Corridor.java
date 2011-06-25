/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package map.component;

import java.util.HashMap;
import java.util.Map;
import map.factory.Coordinate;
import map.factory.Pixel;
import objects.Wall;
import objects.Way;

/**
 *
 * @author hoanggia
 */
public class Corridor implements MapComponent {

    private Map<Coordinate,Pixel> pixels;
    private Map<Coordinate,Pixel> wallPixels;
    private Map<Coordinate,Pixel> wayPixels;
    private boolean isVectical;

    public Corridor(int length, int width, boolean isVectical) {
        pixels=new HashMap<Coordinate,Pixel>();
        wallPixels=new HashMap<Coordinate,Pixel>();
        wayPixels=new HashMap<Coordinate,Pixel>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                int x = isVectical ? i : j;
                int y = isVectical ? j : i;
                if (isVectical) {
                    if(x==0||x==width-1){
                        Pixel p=new Pixel(new Coordinate(x, y), new Wall());
                        pixels.put(p.getCoordinate(),p);
                        wallPixels.put(p.getCoordinate(),p);
                    }else{
                        Pixel p=new Pixel(new Coordinate(x, y), new Way());
                        pixels.put(p.getCoordinate(),p);
                        wayPixels.put(p.getCoordinate(),p);
                    }

                }else{
                    if(y==0||y==width-1){
                        Pixel p=new Pixel(new Coordinate(x, y), new Wall());
                        pixels.put(p.getCoordinate(),p);
                        wallPixels.put(p.getCoordinate(),p);
                    }else{
                        Pixel p=new Pixel(new Coordinate(x, y), new Way());
                        pixels.put(p.getCoordinate(),p);
                        wayPixels.put(p.getCoordinate(),p);

                    }
                }

            }
        }
    }

    public Map<Coordinate,Pixel> getPixels() {
        return pixels;
    }

    public Map<Coordinate, Pixel> getWays() {
        return wayPixels;
    }

    public Map<Coordinate, Pixel> getWalls() {
        return wallPixels;
    }

    public Map<Coordinate, Pixel> getOuterWalls() {
        return wallPixels;
    }
}
