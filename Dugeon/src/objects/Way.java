/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.awt.Color;
import model.Coordinate;

/**
 *
 * @author hoanggia
 */
public class Way implements ViewablePixel {

    private Coordinate coordinate;

    public Way(Coordinate coordinate) {
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
