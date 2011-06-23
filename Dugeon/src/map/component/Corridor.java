/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package map.component;

import java.util.ArrayList;
import java.util.List;
import map.factory.Coordinate;
import map.factory.Pixel;
import objects.Wall;
import objects.Way;

/**
 *
 * @author hoanggia
 */
public class Corridor implements MapComponent {

    private List<Pixel> pixels;
    private List<Pixel> wallPixels;
    private boolean isVectical;

    public Corridor(int length, int width, boolean isVectical) {
        pixels=new ArrayList<Pixel>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                int x = isVectical ? i : j;
                int y = isVectical ? j : i;
                if (isVectical) {
                    if(x==0||x==width-1){
                        Pixel p=new Pixel(new Coordinate(x, y), new Wall());
                        pixels.add(p);
                        wallPixels.add(p);
                    }else{
                        pixels.add(new Pixel(new Coordinate(x, y),new Way()));
                    }

                }else{
                    if(y==0||y==width-1){
                        Pixel p=new Pixel(new Coordinate(x, y), new Wall());
                        pixels.add(p);
                        wallPixels.add(p);
                    }else{
                        pixels.add(new Pixel(new Coordinate(x, y),new Way()));
                    }
                }

            }
        }
    }

    public List<Pixel> getPixels() {
        return pixels;
    }
}
