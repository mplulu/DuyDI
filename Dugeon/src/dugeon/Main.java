/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dugeon;

import map.factory.Coordinate;
import map.factory.MapView;
import console.MainConsole;
import java.awt.Color;
import javax.swing.JFrame;
import map.factory.Pixel;
import java.util.ArrayList;
import java.util.List;
import map.component.Corridor;
import map.component.MapComponent;
import objects.Door;
import objects.Player;
import objects.Wall;

/**
 *
 * @author hoanggia
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        //init data
        Object[][] items = new Object[5][3];
        items[0][0] = new Door();
        items[0][1] = new Wall();
        items[0][2] = new Wall();
        items[1][0] = new Wall();
        items[1][1] = new Player();
        items[1][2] = new Wall();
        items[2][0] = new Wall();
        items[2][1] = new Wall();
        items[2][2] = new Wall();
        items[3][0] = new Wall();
        items[3][1] = new Wall();
        items[3][2] = new Wall();
        items[4][0] = new Wall();
        items[4][1] = new Wall();
        items[4][2] = new Wall();


        Color[][] colors = new Color[5][3];
        colors[0][0] = Color.ORANGE;
        colors[0][1] = Color.ORANGE;
        colors[0][2] = Color.ORANGE;
        colors[1][0] = Color.WHITE;
        colors[1][1] = Color.WHITE;
        colors[1][2] = Color.WHITE;
        colors[2][0] = Color.ORANGE;
        colors[2][1] = Color.ORANGE;
        colors[2][2] = Color.ORANGE;
        colors[3][0] = Color.ORANGE;
        colors[3][1] = Color.ORANGE;
        colors[3][2] = Color.ORANGE;
        colors[4][0] = Color.ORANGE;
        colors[4][1] = Color.ORANGE;
        colors[4][2] = Color.ORANGE;

        List<Pixel> pixels = new ArrayList<Pixel>();

        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 3; ++j) {
                pixels.add(new Pixel(new Coordinate(i, j), items[i][j]));
            }
        }


        
        
        pixels = new ArrayList<Pixel>();
        for (int i = 5, ii = 0; i < 10; ++i, ++ii) {
            for (int j = 3, jj = 0; j < 6; ++j, ++jj) {
                pixels.add(new Pixel(new Coordinate(i, j), items[ii][jj]));
            }
        }
        MapComponent corridor1 = new Corridor(7, 5, true);
        MapComponent corridor2 = new Corridor(10, 3, false);

        MapView map=new MapView();
        map.addMapComponent(corridor1, new Coordinate(0, 0));
        map.addMapComponent(corridor2, new Coordinate(4, 0));


        JFrame frame = new JFrame();
        MainConsole console = new MainConsole(map);


        frame.add(console);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
