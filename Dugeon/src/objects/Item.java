package objects;

import java.awt.Color;
import resource.CharacterResources;
import resource.ColorResources;

public interface Item {
    public static final CharacterResources CHAR_RE=new CharacterResources();
    public static final ColorResources COLOR_RE=new ColorResources();

    public abstract char getRepresentChar();
    public abstract Color getColor();
}
