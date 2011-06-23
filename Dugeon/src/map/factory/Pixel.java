package map.factory;

import java.awt.Color;

public class Pixel {

    private Coordinate coordinate;
    private Object object;

    public Pixel(Coordinate coordinate, Object object) {
        this.coordinate = coordinate;
        this.object = object;
    }



    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
