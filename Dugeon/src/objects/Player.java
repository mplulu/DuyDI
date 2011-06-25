package objects;

import java.awt.Color;
import map.factory.Coordinate;

public class Player implements Item {

    private Coordinate coordinate;
    private Coordinate previous;

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
        setPreviousCoordinate();
        coordinate.setX(coordinate.getX() - 1);
    }

    public void moveRight() {
        setPreviousCoordinate();
        coordinate.setX(coordinate.getX() + 1);
    }

    public void moveAhead() {
        setPreviousCoordinate();
        coordinate.setY(coordinate.getY() - 1);
    }

    public void moveBack() {
        setPreviousCoordinate();
        coordinate.setY(coordinate.getY() + 1);
    }

    private  void setPreviousCoordinate(){
        previous=new Coordinate(coordinate.getX(), coordinate.getY());
    }

    public void goBack(){
        coordinate=new Coordinate(previous.getX(),previous.getY());
    }

    public char getRepresentChar() {
        return CHAR_RE.getCharFor(this.getClass().getName());
    }

    public Color getColor() {
        return COLOR_RE.getColorFor(this.getClass().getName());
    }
}
