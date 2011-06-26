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
public class Corridor implements MapComponent {

    private Map<Coordinate,ViewablePixel> viewablePixels;
    private Map<Coordinate,ViewablePixel> wallViewablePixels;
    private Map<Coordinate,ViewablePixel> wayViewablePixels;
    private boolean isVectical;

    public Corridor(int length, int width, boolean isVectical) {
        viewablePixels=new HashMap<Coordinate,ViewablePixel>();
        wallViewablePixels=new HashMap<Coordinate,ViewablePixel>();
        wayViewablePixels=new HashMap<Coordinate,ViewablePixel>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                int x = isVectical ? i : j;
                int y = isVectical ? j : i;
                if (isVectical) {
                    if(x==0||x==width-1){
                        ViewablePixel p=new Wall(new Coordinate(x, y));
                        viewablePixels.put(p.getCoordinate(),p);
                        wallViewablePixels.put(p.getCoordinate(),p);
                    }else{
                        ViewablePixel p=new Way(new Coordinate(x, y));
                        viewablePixels.put(p.getCoordinate(),p);
                        wayViewablePixels.put(p.getCoordinate(),p);
                    }

                }else{
                    if(y==0||y==width-1){
                        ViewablePixel p=new Wall(new Coordinate(x, y));
                        viewablePixels.put(p.getCoordinate(),p);
                        wallViewablePixels.put(p.getCoordinate(),p);
                    }else{
                        ViewablePixel p=new Way(new Coordinate(x, y));
                        viewablePixels.put(p.getCoordinate(),p);
                        wayViewablePixels.put(p.getCoordinate(),p);

                    }
                }

            }
        }
    }

    public Map<Coordinate,ViewablePixel> getViewablePixels() {
        return viewablePixels;
    }

    public Map<Coordinate, ViewablePixel> getWays() {
        return wayViewablePixels;
    }

    public Map<Coordinate, ViewablePixel> getWalls() {
        return wallViewablePixels;
    }

    public Map<Coordinate, ViewablePixel> getOuterWalls() {
        return wallViewablePixels;
    }
}
