/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package map.factory;

import java.util.HashMap;
import java.util.Map;
import map.component.MapComponent;

/**
 *
 * @author hoanggia
 */
public class MapView {

    private Map<Coordinate,Pixel> pixelsMap;

    public MapView(Map<Coordinate,Pixel> pixelsMap) {
        this.pixelsMap=pixelsMap;
    }

    public MapView(){
        pixelsMap=new HashMap<Coordinate,Pixel>();
    }


    public Map<Coordinate, Pixel> getPixelsMap() {
        return pixelsMap;
    }

    public void setPixelsMap(Map<Coordinate, Pixel> pixelsMap) {
        this.pixelsMap = pixelsMap;
    }

    

    

    

    public boolean addMapComponent(MapComponent component,Coordinate coordinate){
        if(component.getPixels()==null){
            return false;
        }
        for(Pixel p:component.getPixels()){
            p.getCoordinate().shiftCoordinate(coordinate.getX(), coordinate.getY());
            pixelsMap.put(p.getCoordinate(), p);
        }

        return true;

    }
}
