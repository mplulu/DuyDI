package objects;

import java.awt.Color;
import model.Coordinate;

public class Door implements ViewablePixel {

    private Coordinate coordinate;

    public Door(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public char getRepresentChar() {
        return CHAR_RE.getCharFor(this.getClass().getName());
    }

    public Color getColor() {
        return COLOR_RE.getColorFor(this.getClass().getName());
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
