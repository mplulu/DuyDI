package map.factory;

public class Coordinate implements Comparable<Coordinate>{

    /**
     * char[x][y]
     *
     * 00 01 02
     * 10 11 12
     * 20 21 22
     */
    private int x, y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void shiftCoordinate(int xOffset, int yOffset){
        this.y+=yOffset;
        this.x+=xOffset;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinate other = (Coordinate) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        return hash;
    }

    public int compareTo(Coordinate o) {
        if(x != o.x){
            return x - o.x;
        }
        
        if(y != o.y){
            return y - o.y;
        }

        return 0;
    }


}
