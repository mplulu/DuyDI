/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package console;

import map.factory.MapView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import javax.swing.JPanel;
import map.factory.Pixel;
import objects.Item;
import resource.CharacterResources;

/**
 *
 * @author hoanggia
 */
public class MainConsole extends JPanel {

    private static final Color DEFAULT_FOREGROUND = Color.LIGHT_GRAY;
    private static final Color DEFAULT_BACKGROUND = Color.BLACK;
    private static final Font DEFAULT_FONT = new Font("Courier New", Font.PLAIN, 20);
    private static final int HEIGHT_OFFSET = 7;
    private static CharacterResources re = new CharacterResources();
    private MapView map;

    public MainConsole() {
        setPreferredSize(new Dimension(300, 300));
    }

    public MainConsole(MapView map) {
        this();
        this.map = map;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(DEFAULT_BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawMap(map, g);
    }

    public static void drawMap(MapView map, Graphics g) {
        g.setFont(DEFAULT_FONT);

        FontRenderContext fontRenderContext = new FontRenderContext(DEFAULT_FONT.getTransform(), false, false);
        Rectangle2D bound = DEFAULT_FONT.getStringBounds(".", fontRenderContext);
        int fontWidth = (int) bound.getWidth();
        int fontHeight = (int) bound.getHeight() - HEIGHT_OFFSET;

        Collection<Pixel> pixels = map.getPixelsMap().values();

        for (Pixel p : pixels) {
            int x = fontWidth * (p.getCoordinate().getX() + 1);
            int y = fontHeight * (p.getCoordinate().getY() + 1);
            if (p != null && !p.isEmpty()) {
                Item i = (Item) p.getObject();
                String s = "" + re.getCharFor(i.getClass().getName());
                g.setColor(i.getColor());
                g.drawString(s, x, y);
            }
        }
    }
}
