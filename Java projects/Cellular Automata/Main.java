import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class Main extends JFrame{

  Tile[][] tiles = new Tile[1000][500];
  boolean initial;
  public Main(){
    initial = true;

    for(int row = 0; row < tiles.length; row++){
    for(int col = 0; col < tiles[0].length; col++){
    int random = (int)(Math.random() * 10);
    if(random == 1)
    tiles[row][col] = new Tile(random, row, col);
    else
    tiles[row][col] = new Tile(0, row, col);
  }
  }
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setSize(2000,1028);
  this.setLayout(null);
  this.setLocationRelativeTo(null);
  this.getContentPane().setBackground(Color.white);
  this.setResizable(false);
  this.setVisible(true);
  initial = false;
 
  }

  public void conway(){
    for(int row = 0; row < tiles.length; row++){
        for(int col = 0; col < tiles[0].length; col++){
          int countNeighbors = 0;
            for(int xBound = row-1; xBound <= row+1; xBound++){
                for(int yBound = col-1; yBound <= col+1; yBound++){
                    if(xBound < 0 || xBound >=tiles.length ||yBound < 0 || yBound >=tiles[0].length||xBound==row && yBound ==col){
                        
                    } else if (tiles[xBound][yBound].getValue()==1) {
                        countNeighbors++;
                    }
                }
            }
            if(tiles[row][col].getValue()==0 && countNeighbors == 3 || tiles[row][col].getValue()==1 &&countNeighbors > 3 ||tiles[row][col].getValue()==1 &&countNeighbors < 2){
              tiles[row][col].flip();
            }
    }
  }
  this.repaint();
  }



  public void paint(Graphics gg){
    Graphics2D g = (Graphics2D)gg;
    g.translate(0, 28);

    if(initial){
    for(int row = 0; row < tiles.length; row++){
        for(int col = 0; col < tiles[0].length; col++){
            if(tiles[row][col].getValue()==1){
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.BLACK);
            }

            g.fillRect(tiles[row][col].getPixelXStart(), tiles[row][col].getPixelYStart(), 2, 2);
        }
    }
    } else {
     for(int row = 0; row < tiles.length; row++){
        for(int col = 0; col < tiles[0].length; col++){
            if(tiles[row][col].isFlipped()){
              if(tiles[row][col].getValue()==0){
                g.setColor(Color.WHITE);
                tiles[row][col].setValue(1);
              } else {
                g.setColor(Color.BLACK);
                tiles[row][col].setValue(0);
              }
           g.fillRect(tiles[row][col].getPixelXStart(), tiles[row][col].getPixelYStart(), 2, 2);
           tiles[row][col].resetflip();
           
            }

            
        }
    }
    }
  }






  
  
  public static void main(String[] args) {
    Main m = new Main();





      while(true){
        m.conway();
      
      try{
      Thread.sleep(100);
    }catch(InterruptedException ex){}
      }
      
    
    }
  }
