/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package map.component;

import java.awt.geom.Rectangle2D;
import java.util.Map;
import model.Coordinate;
import objects.ViewablePixel;

/**
 *
 * @author hoanggia
 */
public interface MapComponent {
    public abstract Map<Coordinate,ViewablePixel> getViewablePixels();
    public abstract Map<Coordinate,ViewablePixel> getWays();
    public abstract Map<Coordinate,ViewablePixel> getWalls();
    public abstract Map<Coordinate,ViewablePixel> getOuterWalls();



}
