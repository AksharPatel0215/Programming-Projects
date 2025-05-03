public class Tile {
    private int value;
    private int XCoordinate;
    private int YCoordinate;
    private int pixelXStart;
    private int pixelYStart;
    private boolean flip;


    public Tile(int value, int XCoordinate, int YCoordinate){
        this.value = value;
        this.XCoordinate = XCoordinate;
        this.YCoordinate = YCoordinate;
        this.pixelXStart = XCoordinate * 2;
        this.pixelYStart = YCoordinate * 2;
    }

    public void setValue(int newValue){
        value = newValue;
    }

    public int getValue(){
        return value;
    }

    public int getPixelXStart(){
        return pixelXStart;
    }

    public int getPixelYStart(){
        return pixelYStart;
    }


    public boolean isFlipped(){
        return flip;
    }

    public void flip(){
        flip = true;
    }

    public void resetflip(){
        flip = false;
    }
}
