import java.awt.*;
import javax.swing.*;
public class Main extends JFrame {

  Body sun1, sun2, sun3;
  

  public Main(){
  sun1 = new Body(0, -150,50);
  sun2 = new Body(100, 100,50);
  sun3 = new Body(-200, 200, 50);
  
  
  
  

  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setSize(800,821);
  this.setLayout(null);
  this.setLocationRelativeTo(null);
  this.getContentPane().setBackground(Color.WHITE);
  this.setResizable(false);
  this.setVisible(true);
  

    

  }


  public void paint(Graphics gg){
    Graphics2D g = (Graphics2D)gg;
    g.translate(400, 421);
    g.setColor(Color.black);
    g.fillRect(-400,-400,800,800);
    g.setColor(new Color(255, 0, 0));
    drawCenteredCircle(g, (int)sun1.getX(), (int)sun1.getY(), sun1.getRad());
    g.setColor(new Color(0, 255, 0));
    drawCenteredCircle(g, (int)sun2.getX(), (int)sun2.getY(), sun2.getRad());
    g.setColor(new Color(0, 0, 255));
    drawCenteredCircle(g, (int)sun3.getX(), (int)sun3.getY(), sun3.getRad());

  
  }

  public void animate(){
    

    sun1.attractTo(sun2);
    sun2.attractTo(sun1);
    sun1.attractTo(sun3);
    sun2.attractTo(sun3);
    sun3.attractTo(sun1);
    sun3.attractTo(sun2);
    System.out.println("hi");
    

    //sun1.getNewX(sun2);
    //sun1.getNewY(sun2);
    
    this.repaint();
    
  }

  public void drawCenteredCircle(Graphics2D g, int x, int y, int r) {
  x = x-(r/2);
  y = y-(r/2);
  g.fillOval(x,y,r,r);
}

  

 

  
  
  public static void main(String[] args) {
    Main m = new Main();
     while(true){
      m.animate();
      try{
      Thread.sleep(10);
    }catch(InterruptedException ex){}
      }
   
  }
}
