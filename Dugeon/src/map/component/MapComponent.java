/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package map.component;

import java.util.Map;
import map.factory.Coordinate;
import map.factory.Pixel;

/**
 *
 * @author hoanggia
 */
public interface MapComponent {
    public abstract Map<Coordinate,Pixel> getPixels();
    public abstract Map<Coordinate,Pixel> getWays();
    public abstract Map<Coordinate,Pixel> getWalls();
    public abstract Map<Coordinate,Pixel> getOuterWalls();



}
