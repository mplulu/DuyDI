/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dugeon;

import map.factory.MapView;
import console.MainConsole;
import javax.swing.JFrame;
//import map.component.Cavern;
import map.component.MapComponent;
import map.component.OvalRoom;
import map.component.RectangleRoom;
import model.Player;

public class Main {

    public static void main(String[] args) {
        //MapView map = generateMap();
        //MapComponent mapComponent=new Maze(21,21);
        MapComponent mapComponent=new RectangleRoom(11,15);
        MapView map=new MapView(mapComponent);
        //map.addMapComponent(new Cavern(15,15),new Coordinate(30,5));
        //map.addMapComponent(new Corridor(13, 3, false), new Coordinate(20,10));
        Player player = new Player(map.getWays().iterator().next());

        JFrame frame = new JFrame();
        MainConsole console = new MainConsole(map, player);

        frame.add(console);
        frame.pack();
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
