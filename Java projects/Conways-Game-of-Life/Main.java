import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends JFrame implements MouseListener {
  Tile[][] tiles = new Tile[20][20];
  public Main(){
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setSize(400,421);
  this.setLayout(null);
  this.addMouseListener(this);
  this.setLocationRelativeTo(null);
  this.getContentPane().setBackground(Color.white);
  this.setResizable(false);
  this.setVisible(true);

    for(int i = 0; i < 20; i++){
      for(int j =  0; j  < 20; j++){
        tiles[i][j] = new Tile(i*20, j*20+21);
      }
    }

  }

  public void paint(Graphics gg){
    
    Graphics2D g = (Graphics2D)gg;
    g.setColor(Color.black);
    g.fillRect(0,21,400,400);
    g.setColor(new Color(255, 255, 255));
    //g.drawRect(0,21,399,399);
    for(int i = 0; i < 20; i++){
      for(int j = 0; j < 20; j++){
        if(tiles[i][j].getFilled()==false)
        g.drawRect(i*20, j*20+21, 19, 19);
        else 
        g.fillRect(i*20, j*20+21, 20, 20);
      }
    }
  }

  @Override
  public void mouseClicked(MouseEvent e){

    for(int i = 0; i < 20; i++){
      for(int j = 0; j < 20; j++){
        tiles[i][j].check(e.getX(), e.getY());
      }
    }
    this.repaint();
  }

  public void game(){
    for(int i = 0; i <20; i++){
      for(int j = 0; j  < 20; j++){
        tiles[i][j].setNeighbors(0);
       
        if(i==0 && j==0){ //case1 top left corner
          if(tiles[i+1][j+1].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i][j+1].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i+1][j].getFilled())
            tiles[i][j].incrementNeighbors(1);
        } else if(i==0 && j ==19){//case2 bottom left corner
          if(tiles[i+1][j-1].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i][j-1].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i+1][j].getFilled())
            tiles[i][j].incrementNeighbors(1);
        } else if(i==19 && j == 0){//case3 top right corner
          if(tiles[i-1][j].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i][j+1].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i-1][j+1].getFilled())
            tiles[i][j].incrementNeighbors(1);
        } else if(i==19 && j == 19){//case4 bottom right corner
          if(tiles[i-1][j].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i][j-1].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i-1][j-1].getFilled())
            tiles[i][j].incrementNeighbors(1);
        } else if(i==19){//case6 right edge
          if(tiles[i-1][j+1].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i-1][j+1].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i-1][j].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i][j+1].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i][j-1].getFilled())
            tiles[i][j].incrementNeighbors(1);
        }else if(j==0){//case6 top edge
          if(tiles[i+1][j+1].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i-1][j+1].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i+1][j].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i-1][j].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i][j+1].getFilled())
            tiles[i][j].incrementNeighbors(1);
        } else if(i==0){//case7 left edge
          if(tiles[i+1][j+1].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i+1][j-1].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i+1][j].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i][j+1].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i][j-1].getFilled())
            tiles[i][j].incrementNeighbors(1);
        }else if(j==19){//case8 bottom edge
          if(tiles[i-1][j-1].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i+1][j-1].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i-1][j].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i+1][j].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i-1][j].getFilled())
            tiles[i][j].incrementNeighbors(1);
        } else {//case9 main case
          if(tiles[i][j+1].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i][j-1].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i+1][j].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i-1][j].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i+1][j-1].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i+1][j+1].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i-1][j+1].getFilled())
            tiles[i][j].incrementNeighbors(1);
          if(tiles[i-1][j-1].getFilled())
            tiles[i][j].incrementNeighbors(1);
        }

      }
    }

    for(int i = 0; i < 20; i++){
      for(int j = 0; j < 20; j++){
        if((tiles[i][j].getNeighbors() == 2 ||tiles[i][j].getNeighbors() == 3)&&tiles[i][j].getFilled()==true){
          tiles[i][j].setFilled(true);
        } else if(tiles[i][j].getNeighbors()<2){
          tiles[i][j].setFilled(false);
        } else if(tiles[i][j].getNeighbors()>3){
          tiles[i][j].setFilled(false);
        } else if(tiles[i][j].getNeighbors() == 3 && tiles[i][j].getFilled()==false){
          tiles[i][j].setFilled(true);
        } else {
          tiles[i][j].setFilled(false);
        }
      }
    }

    this.repaint();
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
    while(true){
      System.out.println("How many genarations would you like to see of this pattern?");
      int x = scan.nextInt();

      for(int i = 0; i < x; i++){
      m.game();
      try{
      Thread.sleep(1500);
    }catch(InterruptedException ex){}
      }
    
    }
  }
}