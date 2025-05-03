import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.awt.*;
import java.util.ArrayList;
class Main extends JFrame{
  public static ArrayList<Integer> frontier = new ArrayList<Integer>();

  Main(){
    start();
  }

  private char[][] maze;

  public void start(){
    Scanner scan = new Scanner(System.in);
    System.out.println("What size do you want your maze?(1-30)");
    int r = 2 * scan.nextInt()+1;
    
    maze = new char[r][r];

    for(int i = 0; i < maze.length; i++){
      for(int j = 0; j < maze.length; j++){
        if(i%2==0 || j%2==0 || i==maze.length-1||j==maze.length-1){
          maze[i][j] = 'W';
        } else {
          maze[i][j]=' ';
        }
      }
    }
    maze[0][1] = 'S';
    int endRand = (int)(Math.random()*((r-1)/2))*2+1;
    int endRand2 = (int)(Math.random()*((r-1)/2))*2+1;
    if(endRand2 == 0)
    maze[maze.length-1][endRand] = 'E';
    else
    maze[endRand][maze.length-1] = 'E';

    
    
    int rand = (int)(Math.random()*((r-1)/2))*2+1;
    int rand2 = (int)(Math.random()*((r-1)/2))*2+1;
    maze[rand][rand2] = '.';
    makeMaze(maze, rand, rand2, 1);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setSize(r*10,r*10+20);
  this.setLayout(null);
  this.getContentPane().setBackground(Color.blue);
    this.setVisible(true);
    
    System.out.println("Hit enter to solve.");
    String s = scan.nextLine();
    String s2 = scan.nextLine();
    
    boolean solved = markCorrectPathAndCountStars(maze, 0, 1, 0);
    repaint();
    
  }

  public void paint(Graphics gg){
    char[][] ditto = new char[maze.length][maze.length];
    for(int i = 0; i <  ditto.length; i++){
      for(int j = 0; j < ditto.length; j++){
        ditto[i][j] = maze[j][i];
      }
    }
    Graphics2D g = (Graphics2D)gg;
    g.setColor(Color.black);
    g.fillRect(0,0,maze.length*10,maze.length*10+20);
    
    for(int i = 0; i < maze.length; i++){
      for(int j = 0; j < maze.length; j++){
        if(ditto[i][j]=='W'){
          g.setColor(Color.blue);
          g.fillRect(i*10,j*10+20,10,10);
        } else if(ditto[i][j]=='*'){
          g.setColor(new Color(250, 247, 55));
          g.fillRect(i*10,j*10+20,10,10);
        } else if(ditto[i][j]=='E'||ditto[i][j]=='S'){
          g.setColor(new Color(245, 49, 189));
          g.fillRect(i*10,j*10+20,10,10);
        } 
      }
    }
      
  }
  public static void main(String[] args) {
    
    Main m = new Main();
  }

  

  public static void display(char[][] maze){
    for(int i =0; i < maze.length; i++){
      for(int j = 0; j < maze.length; j++){
        if(maze[i][j]=='W')
        System.out.print("\033[0;30m["+"W"+"]\033[0m");
        else if(maze[i][j]=='S'||maze[i][j]=='E')
        System.out.print("\033[0;32m["+maze[i][j]+"]\033[0m");
        else if(maze[i][j]=='*')
        System.out.print("\033[0;31m["+maze[i][j]+"]\033[0m");
        else
        System.out.print("   ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void makeMaze(char[][] maze, int currentX, int currentY, int count){
    if(count < ((maze.length-1)/2)*((maze.length-1)/2)){
    int up =  currentY - 2;
    int down = currentY + 2;
    int left = currentX - 2;
    int right = currentX + 2;

    if(up >= 0 &&  maze[currentX][up] == ' '){
      maze[currentX][up] = '*';
      frontier.add(currentX*1000+up);
    }

    if(down < maze.length && maze[currentX][down] == ' '){
      maze[currentX][down] = '*';
      frontier.add(currentX*1000+down);
    }

    if(left >= 0 && maze[left][currentY] == ' '){
      maze[left][currentY] = '*';
      frontier.add(left*1000+currentY);
    }

    if(right < maze.length && maze[right][currentY] == ' '){

      maze[right][currentY] = '*';
      frontier.add(right*1000+currentY);
    }

    int randFront = (int)(Math.random()*frontier.size());
    int rand = frontier.get(randFront);
    frontier.remove(randFront);
    int randX = rand/1000;
    int randY = rand%1000;
    maze[randX][randY] = '.';

    up = randY - 2;
    down = randY + 2;
    left = randX - 2;
    right = randX + 2;
  int superRand = (int)(Math.random()*4);

      if(superRand == 0){
     if(right < maze.length && maze[right][randY]=='.'){
      maze[randX+1][randY] = '.';
    }else if(up >= 0 && maze[randX][up]=='.'){
      maze[randX][randY-1] = '.';
    } else if(down < maze.length && maze[randX][down]=='.'){
      maze[randX][randY+1] = '.';
    } else if(left >= 0 && maze[left][randY]=='.'){
      maze[randX-1][randY] = '.';
    }

     } else if(superRand == 1){
        if(up >= 0 && maze[randX][up]=='.'){
      maze[randX][randY-1] = '.';
    } else if(down < maze.length && maze[randX][down]=='.'){
      maze[randX][randY+1] = '.';
    } else if(left >= 0 && maze[left][randY]=='.'){
      maze[randX-1][randY] = '.';
    } else if(right < maze.length && maze[right][randY]=='.'){
      maze[randX+1][randY] = '.';
    }
     } else if(superRand == 2){
        if(down < maze.length && maze[randX][down]=='.'){
      maze[randX][randY+1] = '.';
    } else if(left >= 0 && maze[left][randY]=='.'){
      maze[randX-1][randY] = '.';
    } else if(right < maze.length && maze[right][randY]=='.'){
      maze[randX+1][randY] = '.';
    } else if(up >= 0 && maze[randX][up]=='.'){
      maze[randX][randY-1] = '.';
    } 
     } else {
        if(left >= 0 && maze[left][randY]=='.'){
      maze[randX-1][randY] = '.';
    } else if(right < maze.length && maze[right][randY]=='.'){
      maze[randX+1][randY] = '.';
    } else if(up >= 0 && maze[randX][up]=='.'){
      maze[randX][randY-1] = '.';
    } else if(down < maze.length && maze[randX][down]=='.'){
      maze[randX][randY+1] = '.';
    } 
     }
     makeMaze(maze, randX, randY, count+1);
    
    }
  }

  public static boolean markCorrectPathAndCountStars(char[][] maze, int r, int c, int count)
   {
     boolean inBounds = r >= 0 && r < maze.length && c >= 0 && c < maze[0].length;

     if(inBounds && (maze[r][c]=='.'||maze[r][c]=='E'||maze[r][c]=='S')){
     
       
     if(maze[r][c]=='E'){
       for(int i = 0; i < maze.length; i++){
         for(int j = 0; j < maze[0].length; j++){
           if(maze[i][j]=='X'){
             maze[i][j]='.';
           } 
         }
       }
        System.out.println("Number of steps = "+count);
       return true;
     }
    if(maze[r][c]=='.'){
      maze[r][c] = 'X';
    } 
     boolean canMove = markCorrectPathAndCountStars(maze, r-1, c, count+1);

     if(!canMove){
       
       canMove =  markCorrectPathAndCountStars(maze, r+1, c,count+1);
     }

     if(!canMove){
       
       canMove =  markCorrectPathAndCountStars(maze, r,c-1, count+1);
       
     }

     if(!canMove){
       canMove =  markCorrectPathAndCountStars(maze, r,c+1,count+1);
     }

    if(canMove){
      maze[r][c]  = '*';
      maze[0][1]  = 'S';
    }

      return canMove;
     }
     
    return false;
     
   }
}