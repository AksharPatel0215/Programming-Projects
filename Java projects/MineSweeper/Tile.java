import java.util.ArrayList;
public class Tile {
    ArrayList<Tile> neighbors;
    private boolean isBomb;
    private int numBombs;
    private boolean isFlagged;
    private boolean isOpened;
    private int x;
    private int y;
    private int x1;
    private int y1;
    private int x2;
    private int y2;


    public Tile(int x, int y, boolean isBomb){
        this.x = x;
        this.y = y;
        x1 = x*20;
        x2 = x1 + 19;
        y1 = y*20+42;
        y2 = y1 + 19;
        this.isBomb = isBomb; 
        numBombs = 0;
        isOpened = false;
        isFlagged = false;
        neighbors = new ArrayList<Tile>();
    }

    public boolean getIsBomb(){
        return isBomb;
    }

    public int getNumBombs(){
        return numBombs;
    }

    public void incNumBombs(){
        numBombs++;
    }

    public boolean getIsFlagged(){
        return isFlagged;
    }

    public void toggleFlag(){
        isFlagged = !isFlagged;
    }

    public boolean getIsOpened(){
        return isOpened;
    }

    public void Open(){
        if(isFlagged)
        toggleFlag();
        else
        isOpened = true;
    }

    public String getCords(){
        return "("+x1+"-"+x2+", "+y1+"-"+y2+")";
    }

    public boolean check(int a, int b, int c){
    if(a >= x1 && a <= x2 && b>=y1 && b<=y2){
      if(c==1){
        Open();
      } else 
        toggleFlag();
      
      return true;
    }
    return false;
  }

  public void makeBomb(){
    isBomb = true;
  }

    public String toString(){
        if(isFlagged){
            return "[!]";
        } else if(!isOpened){
            return "[?]";
        } else if(isBomb){
            return "[*]";
        } else if(numBombs == 0){
            return "[ ]";
        } else {
            return "["+numBombs+"]";
        }
    }
}