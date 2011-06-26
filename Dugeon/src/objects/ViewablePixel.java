package objects;

import java.awt.Color;
import model.Coordinate;
import resource.CharacterResources;
import resource.ColorResources;

public interface ViewablePixel {

    public static final CharacterResources CHAR_RE = new CharacterResources();
    public static final ColorResources COLOR_RE = new ColorResources();

    public abstract char getRepresentChar();
    public abstract Color getColor();
    public abstract Coordinate getCoordinate();
}
