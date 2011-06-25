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
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import map.factory.Coordinate;
import map.factory.Pixel;
import objects.Item;
import objects.Player;
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
    private Player player;
    private Map<Coordinate, Pixel> pixels;

    public MainConsole() {
        setPreferredSize(new Dimension(600, 600));
    }

    public MainConsole(MapView map, Player player) {
        this();
        this.map = map;
        this.player = player;
        pixels = new HashMap<Coordinate, Pixel>();

    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(DEFAULT_BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawMap(g, map, player);
    }

    public void drawMap(Graphics g, MapView map, Player player) {
        g.setFont(DEFAULT_FONT);

        FontRenderContext fontRenderContext = new FontRenderContext(DEFAULT_FONT.getTransform(), false, false);
        Rectangle2D bound = DEFAULT_FONT.getStringBounds(".", fontRenderContext);
        int fontWidth = (int) bound.getWidth();
        int fontHeight = (int) bound.getHeight();
        pixels.putAll(map.getPixelsMap());
        pixels.put(player.getCoordinate(), new Pixel(player.getCoordinate(), player));
        System.out.println("e");
        for (Pixel p : pixels.values()) {
            Coordinate c = p.getCoordinate();
            int x = fontWidth * (c.getX() + 1);
            int y = fontHeight * (c.getY() + 1);
            if (p != null && !p.isEmpty()) {
                Item i = (Item) p.getObject();
                String s = "" + re.getCharFor(i.getClass().getName());
                g.setColor(i.getColor());
                g.drawString(s, x, y);
            }
        }


    }
}
