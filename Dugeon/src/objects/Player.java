package objects;

import java.awt.Color;
import map.factory.Coordinate;

public class Player implements Item {

    private Coordinate coordinate;

    public Player(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void moveLeft() {
        coordinate.setX(coordinate.getX() - 1);
    }

    public void moveRight() {
        coordinate.setX(coordinate.getX() + 1);
    }

    public void moveAhead() {
        coordinate.setY(coordinate.getY() - 1);
    }

    public void moveBack() {
        coordinate.setY(coordinate.getY() + 1);
    }

    public char getRepresentChar() {
        return CHAR_RE.getCharFor(this.getClass().getName());
    }

    public Color getColor() {
        return COLOR_RE.getColorFor(this.getClass().getName());
    }
}
