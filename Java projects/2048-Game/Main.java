import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.awt.*;
import java.util.ArrayList;

public class Main extends JFrame implements KeyListener{

 
 Main(){  
  
  start();
  
   


  
 }

  public void start(){
    board = new int[4][4];
  scan = new Scanner(System.in);
  gameOv = new int[4][4];
   spawnRand(board);
    spawnRand(board);
    cMove = true;
    score = 0;
    isMove = false;
   gameOver = false;
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setSize(500,500);
  this.setLayout(null);
  this.addKeyListener(this);
  this.getContentPane().setBackground(Color.black);

    this.setVisible(true);
  }

  private int[][] board;
  private Scanner scan;
  private int[][] gameOv;
  private boolean cMove;
  private int score;
  private boolean isMove;
  private boolean gameOver;

  public boolean gameO(){
    return gameOver;
  }
  
  public void print(int[][] board){
    for(int i = 0; i < 4; i ++){
      for(int j = 0; j < 4; j++){
        System.out.print("["+board[i][j]+"]");
      }
      System.out.println();
    }
  }

  public int getScore(){
    return score;
  }

  public boolean canSpawn(int[][] board, int[][] check){
    boolean change = false;
    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 4; j++){
        if(check[i][j]!=board[i][j]){
          change = true;
        }
      }
    }

    return change && cMove;
  }

  public int getHighest(){
    int highest = board[0][0];
    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 4; j++){
        if(board[i][j]>highest){
          highest = board[i][j];
        }
      }
    }
    return highest;
  }

  public boolean canMerge(int[][]temp){
    int[][]temp2 = new int[4][4];
    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 4; j++){
        temp2[i][j] = temp[i][j];
      }
    }
    condenseUp(temp2);
    mergeUp(temp2);
    condenseUp(temp2);
    condenseDown(temp2);
    mergeDown(temp2);
    condenseDown(temp2);
    condenseLeft(temp2);
    mergeLeft(temp2);
    condenseLeft(temp2);

    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 4; j++){
        if(temp2[i][j]!=temp[i][j])
          return true;
      }
    }
    return false;
  }

  
  public void paint(Graphics gg) {
    int[][] ditto = new int[4][4];
    for(int i = 0; i <  4; i++){
      for(int j = 0; j < 4; j++){
        ditto[i][j] = board[j][i];
      }
    }
    Graphics2D g = (Graphics2D)gg;
    g.setColor(new Color(87,87,87));
    g.fillRect(20, 20, 360,340);
    g.setFont(new Font("Purisa", Font.PLAIN, 20));
    
    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 4; j++){
        if(ditto[i][j] == 0){
        g.setColor(new Color(139,139,139));
        g.fillRoundRect(i*80+50, j*80+40, 60, 60,10,10);
        } else if(ditto[i][j]==2){
        g.setColor(new Color(227, 217, 202));
        g.fillRoundRect(i*80+50, j*80+40, 60, 60,10,10);
        g.setColor(new Color(255,255, 255));
        g.drawString("2", i*80+75, j*80+75);
        } else if(ditto[i][j]==4){
        g.setColor(new Color(237, 202, 145));
        g.fillRoundRect(i*80+50, j*80+40, 60, 60,10,10);
        g.setColor(new Color(255,255, 255));
        g.drawString("4", i*80+73, j*80+75);
        } else if(ditto[i][j]==8){
        g.setColor(new Color(245, 157, 81));
        g.fillRoundRect(i*80+50, j*80+40, 60, 60,10,10);
        g.setColor(new Color(255,255, 255));
        g.drawString("8", i*80+73, j*80+75);
        } else if(ditto[i][j]==16){
        g.setColor(new Color(250, 107, 35));
        g.fillRoundRect(i*80+50, j*80+40, 60, 60,10,10);
        g.setColor(new Color(255,255, 255));
        g.drawString("16", i*80+67, j*80+75);
        } else if(ditto[i][j]==32){
        g.setColor(new Color(252, 91, 66));
        g.fillRoundRect(i*80+50, j*80+40, 60, 60,10,10);
        g.setColor(new Color(255,255, 255));
        g.drawString("32", i*80+67, j*80+75);
        } else if(ditto[i][j]==64){
        g.setColor(new Color(255, 35, 2));
        g.fillRoundRect(i*80+50, j*80+40, 60, 60,10,10);
        g.setColor(new Color(255,255, 255));
        g.drawString("64", i*80+67, j*80+75);
        } else if(ditto[i][j]==128){
        g.setColor(new Color(252, 239, 88));
        g.fillRoundRect(i*80+50, j*80+40, 60, 60,10,10);
        g.setColor(new Color(255,255, 255));
        g.drawString("128", i*80+61, j*80+75);
        } else if(ditto[i][j]==256){
        g.setColor(new Color(245, 213, 54));
        g.fillRoundRect(i*80+50, j*80+40, 60, 60,10,10);
        g.setColor(new Color(255,255, 255));
        g.drawString("256", i*80+61, j*80+75);
        } else if(ditto[i][j]==512){
        g.setColor(new Color(250, 213, 30));
        g.fillRoundRect(i*80+50, j*80+40, 60, 60,10,10);
        g.setColor(new Color(255,255, 255));
        g.drawString("512", i*80+61, j*80+75);
        } else if(ditto[i][j]==1024){
        g.setColor(new Color(237, 198, 5));
        g.fillRoundRect(i*80+50, j*80+40, 60, 60,10,10);
        g.setColor(new Color(255,255, 255));
        g.drawString("1024", i*80+55, j*80+75);
        } else if(ditto[i][j]==2048){
        g.setColor(new Color(158, 132, 2));
        g.fillRoundRect(i*80+50, j*80+40, 60, 60,10,10);
        g.setColor(new Color(255,255, 255));
        g.drawString("2048", i*80+55, j*80+75);
        }
      }
    }
  } 

  public boolean canMove(){
    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 4; j++){
      gameOv[i][j] = board[i][j];
      }
    }
    condenseUp(gameOv);
    mergeUp(gameOv);
    condenseUp(gameOv);
    if(canSpawn(board,gameOv)||canMerge(gameOv))
      return true;
    condenseDown(gameOv);
    mergeDown(gameOv);
    condenseDown(gameOv);
    if(canSpawn(board,gameOv)||canMerge(gameOv))
      return true;
    condenseLeft(gameOv);
    mergeLeft(gameOv);
    condenseLeft(gameOv);
    if(canSpawn(board,gameOv)||canMerge(gameOv))
      return true;
    condenseRight(gameOv);
    mergeRight(gameOv);
    condenseRight(gameOv);
    if(canSpawn(board,gameOv)||canMerge(gameOv))
      return true;
    return false;
    
  }

  public void spawnRand(int[][] board){
    ArrayList<Integer> open = new ArrayList<Integer>();
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[0].length; j++){
        if(board[i][j] == 0){
          open.add((i+1)*10+j);
        }
      }
    }
    if(open.size()==0){
      cMove = false;
    } else {
    int rand1 = open.get((int)(Math.random()*open.size()));
    int rand2 = (int)(Math.random()*10+1);
    if(rand2 == 1){
      rand2 = 4;
    } else {
      rand2 = 2;
    }
    board[rand1/10-1][rand1%10] = rand2;
    }
  }

  public void condenseRight(int[][] board){

    for(int i = board.length-1; i >= 0; i--){
      for(int j = board[0].length-2; j>= 0; j--){
        if(board[i][j]!=0){
          int col = j;
          while(col!=3&&board[i][col+1]==0){
            board[i][col+1] = board[i][col];
            board[i][col] = 0;
            col++;            
          }        
          }
        }
      }
    }


    public void condenseLeft(int[][] board){

    for(int i = 0; i < board.length; i++){
      for(int j = 1; j < board[0].length; j++){
        if(board[i][j]!=0){
          int col = j;
          while(col!=0&&board[i][col-1]==0){
            board[i][col-1] = board[i][col];
            board[i][col] = 0;
            col--;            
          }        
          }
        }
      }
    }

  public void condenseUp(int[][] board){

    for(int i = 1; i < board.length; i++){
      for(int j = 0; j < board[0].length; j++){
        if(board[i][j]!=0){
          int row = i;
          while(row!=0&&board[row-1][j]==0){
            board[row-1][j] = board[row][j];
            board[row][j] = 0;
            row--;            
          }        
          }
        }
      }
    }

  public void condenseDown(int[][] board){

    for(int i = board.length-2; i >= 0; i--){
      for(int j = board[0].length-1; j >=0; j--){
        if(board[i][j]!=0){
          int row = i;
          while(row!=3&&board[row+1][j]==0){
            board[row+1][j] = board[row][j];
            board[row][j] = 0;
            row++;            
          }        
          }
        }
      }
    }

  public void mergeRight(int[][] board){

    for(int i = board.length-1; i >= 0; i--){
      for(int j = board[0].length-2; j>= 0; j--){
        if(board[i][j]!=0){
          if(board[i][j+1]==board[i][j]){
            board[i][j+1] += board[i][j];
            if(isMove)
            score+=board[i][j+1];
            board[i][j]=0;
          }        
          }
        }
      }

    }

  public void mergeLeft(int[][] board){
    for(int i = 0; i < board.length; i++){
      for(int j = 1; j < board[0].length; j++){
        if(board[i][j]!=0){
          if(board[i][j-1]==board[i][j]){
            board[i][j-1] += board[i][j];
            if(isMove)
            score+=board[i][j-1];
            board[i][j] = 0;
          }     
          }
        }
      }


    }

  public void mergeUp(int[][] board){
    for(int i = 1; i < board.length; i++){
      for(int j = 0; j < board[0].length; j++){
        if(board[i][j]!=0){
          if(board[i-1][j]==board[i][j]){
            board[i-1][j] += board[i][j];
            if(isMove)
            score+=board[i-1][j];
            board[i][j] = 0;
          }      
          }
        }
      }



    }

  public void mergeDown(int[][] board){
    for(int i = board.length-2; i >= 0; i--){
      for(int j = board[0].length-1; j >=0; j--){
        if(board[i][j]!=0){
          if(board[i+1][j]==board[i][j]){
            board[i+1][j] += board[i][j];
            if(isMove)
            score+=board[i+1][j];
            board[i][j] = 0;
          }    
          }
        }
      }


    }



 @Override
 public void keyTyped(KeyEvent e) {
  //keyTyped = Invoked when a key is typed. Uses KeyChar, char output
  
  
 }

 @Override
 public void keyPressed(KeyEvent e) {
  //keyPressed = Invoked when a physical key is pressed down. Uses KeyCode, int output
   if(!gameOver){

   int[][] temp = new int[4][4];
    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 4; j++){
        temp[i][j] = board[i][j];
      }
    }
    isMove = true;
   if(e.getKeyCode()==37||e.getKeyCode()==65){
     condenseLeft(temp);
      mergeLeft(temp);
      condenseLeft(temp); 
   } else if(e.getKeyCode()==38||e.getKeyCode()==87){
      condenseUp(temp);
      mergeUp(temp);
      condenseUp(temp);
   } else if(e.getKeyCode()==39||e.getKeyCode()==68){
      condenseRight(temp);
      mergeRight(temp);
      condenseRight(temp); 
   } else if(e.getKeyCode()==40||e.getKeyCode()==83){
     condenseDown(temp);
      mergeDown(temp);
      condenseDown(temp);
   }


     isMove = false;
    if(canSpawn(board, temp)){
      spawnRand(temp);
    }

    for(int i = 0; i < 4; i++){
      for(int j = 0; j < 4; j++){
        board[i][j] = temp[i][j];
      }
    }
   System.out.println("YOUR SCORE IS: "+score);
    System.out.println("YOUR HIGHEST NUMBER IS: "+getHighest());
   if(canMove()==false){
     gameOver = true;
     System.out.println("GAME OVER");
     System.out.println("YOUR SCORE WAS: "+score);
     System.out.println("YOUR HIGHEST NUMBER WAS: "+getHighest());
   } else if(getHighest()==2048){
     System.out.println("GAME OVER");
     System.out.println("YOUR SCORE WAS: "+score);
     System.out.println("YOU GOT 2048");
     System.out.println("Re-run the code for a new game.");
   }
   } else {
     System.out.println("Re-run the code for a new game.");
   }

   this.repaint();
 }


 @Override
 public void keyReleased(KeyEvent e) {

 }

  public static void main(String[] args){
    System.out.println("2048");
    Main m  = new Main();

  }
}