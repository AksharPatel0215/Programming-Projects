public class Tile{
  private int x1;
  private int y1; 
  private int x2;
  private int y2;
  private boolean filled;
  private int neighbors;

  public Tile(int x1, int y1){
    this.x1 = x1;
    this.y1 = y1;
    filled = false;
    y2 = y1+19;
    x2 = x1+19;
    neighbors =0;
  }

  public void setFilled(boolean f){
    filled = f;
  }

  public boolean getFilled(){
    return filled;
  }

  public void incrementNeighbors(int n){
    neighbors +=n;
  }

  public void setNeighbors(int n){
    neighbors = n;
  }

  public int getNeighbors(){
    return neighbors;
  }

  public void check(int x, int y){
    if(x >= x1 && x <= x2 && y>=y1 && y<=y2){
      if(filled == false)
      setFilled(true);
      else
      setFilled(false);
    }
  }
  
}