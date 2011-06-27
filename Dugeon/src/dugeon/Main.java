/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dugeon;

import map.factory.MapView;
import console.MainConsole;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
//import map.component.Cavern;
import javax.swing.JScrollPane;
import map.component.Cavern;
import map.component.MapComponent;
import map.factory.MapGenerator;
import model.Player;

public class Main {

    public static void main(String[] args) {
        
        //MapView map = generateMap();
        //MapComponent mapComponent=new Cavern(21);
        //MapComponent mapComponent=new Corridor(new Coordinate(10, 8), new Coordinate(1, 0));
        MapGenerator ge=new MapGenerator();
        MapView map=new MapView(ge.generateMap());
        //MapView map=new MapView(new RectangleRoom(21,50));
        //MapView map=new MapView(new OvalRoom(7));
        //MapView map=new MapView(new Maze(20,50))
        //map.addMapComponent(new Cavern(15,15),new Coordinate(30,5));
        //map.addMapComponent(new Corridor(13, 3, false), new Coordinate(20,10));
        Player player = new Player(map.getWays().iterator().next());

        JFrame frame = new JFrame();
        MainConsole console = new MainConsole(map, player);
        JScrollPane scrollPane = new JScrollPane(console);
        frame.add(scrollPane);
        frame.setSize(new Dimension(700, 700));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        while (true) {
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            int random = new Random().nextInt(4);
//            switch (random) {
//                case 0:
//                    player.moveAhead();
//                    //Check
//                    if (map.getPixelsMap().get(player.getCoordinate()).getObject() instanceof Wall) {
//                        player.moveBack();
//                    }
//                    break;
//                case 1:
//                    player.moveBack();
//                    //Check
//                    if (map.getPixelsMap().get(player.getCoordinate()).getObject() instanceof Wall) {
//                        player.moveAhead();
//                    }
//                    break;
//                case 2:
//                    player.moveLeft();
//                    //Check
//                    if (map.getPixelsMap().get(player.getCoordinate()).getObject() instanceof Wall) {
//                        player.moveRight();
//                    }
//                    break;
//                case 3:
//                    player.moveRight();
//                    //Check
//                    if (map.getPixelsMap().get(player.getCoordinate()).getObject() instanceof Wall) {
//                        player.moveLeft();
//                    }
//            }
//
//            console.setPlayer(player);
//            console.revalidate();
//            console.repaint();
//        }
    }

    
}
