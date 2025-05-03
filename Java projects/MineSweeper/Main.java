import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
public class Main extends JFrame implements MouseListener {

  
  Tile[][] tiles = new Tile[16][16];
  boolean firstMove = true;
  public Main(){
  
  for(int i = 0; i < tiles.length; i++){
      for(int j =  0; j  < tiles[i].length; j++){
        tiles[i][j] = new Tile(i, j, false);
      }
    }
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setSize(400,442);
  this.setLayout(null);
  this.addMouseListener(this);
  this.setLocationRelativeTo(null);
  this.getContentPane().setBackground(Color.white);
  this.setResizable(false);
  this.setVisible(true);

    

  }
  public void getTiles(){
    for(int i = 0; i < tiles.length; i ++){
        for(int j = 0; j < tiles.length; j++){
            System.out.print(tiles[i][j].getIsFlagged());
        }
        System.out.println();
    }
  }

  public void paint(Graphics gg){
    
    Graphics2D g = (Graphics2D)gg;
    g.setColor(Color.black);
    g.fillRect(0,42,400,400);
    g.setColor(new Color(255, 255, 255));

    for(int i = 0; i < tiles.length; i++){
      for(int j = 0; j < tiles[i].length; j++){
        
        if(tiles[i][j].getIsOpened()){
        if(tiles[i][j].getIsBomb()){
        g.drawRect(i*20, j*20+42, 20, 20);
        g.setColor(new Color(255, 0, 255));
        g.fillRect(i*20, j*20+42, 20, 20);
        g.setColor(new Color(255, 255, 255));
        } else {
        g.drawRect(i*20, j*20+42, 20, 20);
        g.fillRect(i*20, j*20+42, 20, 20);
        
        
        g.setColor(new Color(0, 0, 255));
        g.drawString(tiles[i][j].getNumBombs()+"",i*20+5, j*20+57);
        g.setColor(new Color(255, 255, 255));
        }
        }else if(tiles[i][j].getIsFlagged()){
        g.drawRect(i*20, j*20+42, 20, 20);
        g.setColor(new Color(255, 255, 0));
        g.fillRect(i*20, j*20+42, 20, 20);
        g.setColor(new Color(255, 255, 255));
        }else{
        g.drawRect(i*20, j*20+42, 20, 20);
        }
      }
    }
  }

  @Override
  public void mouseClicked(MouseEvent e){
    int x = e.getX();
    int y = e.getY();
    int z = e.getButton();
    int a = 0;
    int b = 0;
    boolean tile = false;
    for(int i = 0; i < tiles.length; i++){
      for(int j = 0; j < tiles[i].length; j++){
        tile = tiles[i][j].check(x, y, z);
        if(tile){
          a = i;
          b = j;
          break;
        }
      }
    }
    if(firstMove){
 
      
      firstMove = false;

      ArrayList<Integer> available = new ArrayList<Integer>();
      for(int i = 0 ; i < tiles.length; i++){
        for(int j = 0; j < tiles[0].length; j++){
          if((i<a-2||i>a+2)&&(j<=b-2||j>=b+2)){
            available.add(100*i+j);
          }
        }
      }

      for (int i = 0; i < 32; i++) {
          int r = (int)(Math.random()*available.size());
          int g = available.get(r);
          available.remove(r);
          int n = g/100;
          int m = g%100;
          tiles[n][m].makeBomb();
          for(int j = n-1; j<=n+1; j++){
            for(int k = m-1; k<=m+1; k++){
              if(j>=0&&j<tiles.length &&k>=0&&k<tiles[0].length){
                tiles[j][k].incNumBombs();
              }
            }
          }
      }

     floodFill(a, b);
      
      }
      int count = 0;
      for(int i = 0; i < tiles.length; i++){
        for(int j = 0; j < tiles[0].length; j++){
          
          if(tiles[i][j].getIsOpened()&&tiles[i][j].getIsBomb()){
            System.out.println("YOU LOSE");
            return;
          } 
          if(tiles[i][j].getIsOpened()){
            count++;
          }
          if(256-count==32){
            System.out.println("YOU WIN");
          }
          /*
          if(256-count == 32){
            System.out.println("YOU WIN!");
            setVisible(false); //you can't see me!
            dispose(); //Destroy the JFrame object        
            return;
          }
          */
        }
      }
    

    
    
    
    this.repaint();
  }

  public void floodFill(int x, int y){
    if(x-1>=0&& ((tiles[x-1][y].getNumBombs()==0&&!tiles[x-1][y].getIsOpened())||tiles[x][y].getNumBombs()==0&&tiles[x-1][y].getNumBombs()!=0)){
      tiles[x-1][y].Open();
      floodFill(x-1, y);
    }
     if(x+1<tiles.length&& ((tiles[x+1][y].getNumBombs()==0&&!tiles[x+1][y].getIsOpened())||tiles[x][y].getNumBombs()==0&&tiles[x+1][y].getNumBombs()!=0)){
      tiles[x+1][y].Open();
      floodFill(x+1, y);
    }
     if(y-1>=0&& ((tiles[x][y-1].getNumBombs()==0&&!tiles[x][y-1].getIsOpened())||tiles[x][y].getNumBombs()==0&&tiles[x][y-1].getNumBombs()!=0)){
      tiles[x][y-1].Open();
      floodFill(x, y-1);
    }
    if(y+1<tiles[0].length&& ((tiles[x][y+1].getNumBombs()==0&&!tiles[x][y+1].getIsOpened())||tiles[x][y].getNumBombs()==0&&tiles[x][y+1].getNumBombs()!=0)){
      tiles[x][y+1].Open();
      floodFill(x, y+1);
    }


   
  }

    @Override
  public void mouseEntered(MouseEvent e){
    
  }

  @Override
  public void mouseExited(MouseEvent e){
    
  }

  @Override
  public void mousePressed(MouseEvent e){
    
  }

  @Override
  public void mouseReleased(MouseEvent e){
    
  }
  

  /*
mouseClicked(MouseEvent e)
Invoked when the mouse button has been clicked (pressed and released) on a component.
void	mouseEntered(MouseEvent e)
Invoked when the mouse enters a component.
void	mouseExited(MouseEvent e)
Invoked when the mouse exits a component.
void	mousePressed(MouseEvent e)
Invoked when a mouse button has been pressed on a component.
void	
  */
  
  
  public static void main(String[] args) {
    Main m = new Main();
    Scanner scan = new  Scanner(System.in);
   
  }
}
