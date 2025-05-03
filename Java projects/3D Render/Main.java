import java.awt.*;
import javax.swing.*;

public class Main extends JFrame{
    double[][] cube = new double[8][3];

    double[][] pyramid = new double[5][3];
    double[][] hcube = new double[16][4];
    double[][] pcube = new double[16][3];
    double theta = Math.toRadians(0.3);
    double[] test = new double[300];
    double fov = 500;

        
        
    

public Main(){
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setSize(800,800);
  this.setLayout(null);
  this.setLocationRelativeTo(null);
  this.getContentPane().setBackground(Color.black);
  this.setResizable(false);
  this.setVisible(true);


    


        hcube[0] = new double[]{-100, -100, 100, 100};
        hcube[1] = new double[]{-100, 100, 100, 100};
        hcube[2] = new double[]{100, 100, 100, 100};
        hcube[3] = new double[]{100, -100, 100, 100};
        
        hcube[4] = new double[]{-100, -100, -100, 100};
        hcube[5] = new double[]{-100, 100, -100, 100};
        hcube[6] = new double[]{100, 100, -100, 100};
        hcube[7] = new double[]{100, -100, -100, 100};

        hcube[8] = new double[]{-100, -100, 100, -100};
        hcube[9] = new double[]{-100, 100, 100, -100};
        hcube[10] = new double[]{100, 100, 100, -100};
        hcube[11] = new double[]{100, -100, 100, -100};
        
        hcube[12] = new double[]{-100, -100, -100, -100};
        hcube[13] = new double[]{-100, 100, -100, -100};
        hcube[14] = new double[]{100, 100, -100, -100};
        hcube[15] = new double[]{100, -100, -100, -100};


  

        for(int i = 0; i < pcube.length; i++){
        for(int j = 0; j < pcube[0].length; j++){
        pcube[i][j] = project(hcube[i][j], hcube[i][3]);
        }
        }
        






        for(int i = 0; i < cube.length; i++){
            for(int j = 0; j < cube[0].length; j++){
                switch (j) {
                    case 2:
                        if(i>3){
                            cube[i][j] = -100;
                        }else{
                            cube[i][j] = 100;
                        }   break;
                    case 0:
                        if(i==0||i==1||i==4||i==5){
                            cube[i][j]=-100;
                        } else {
                            cube[i][j]=100;
                        }   break;
                    default:
                        if(i==0||i==3||i==4||i==7){
                            cube[i][j] = -100;
                        } else {
                            cube[i][j] = 100;
                        }   break;
                }
            }
        }
        /*


        pyramid[0][0] = 350;
        pyramid[0][1] = 350;
        pyramid[0][2] = 0;

        pyramid[1][0] = 350;
        pyramid[1][1] = 450;
        pyramid[1][2] = 0;

        pyramid[2][0] = 450;
        pyramid[2][1] = 450;
        pyramid[2][2] = 0;

        pyramid[3][0] = 450;
        pyramid[3][1] = 350;
        pyramid[3][2] = 0;


        pyramid[4][0] = 400;
        pyramid[4][1] = 400;
        pyramid[4][2] = 100;

*/
        for(int i = 0; i < pyramid.length; i++){
            for(int j = 0; j <pyramid[0].length; j++){
                if(i==4){
                    if(j<2){
                        pyramid[i][j] = 0;
                    } else {
                        pyramid[i][j] = 100;
                    }
                } else if(j==0){
                    if(i<=1){
                        pyramid[i][j] = -50;
                    } else {
                        pyramid[i][j] = 50;
                    }
                } else if(j==1){
                    if(i==0||i==3){
                        pyramid[i][j]=-50;
                    } else{
                        pyramid[i][j]=50;
                    }
                } else if(j==2){
                    if(i!=4){
                        pyramid[i][j]=0;
                    }
                }
            }
        }

}

public void paint(Graphics g){
        Graphics2D gg = (Graphics2D)g;
        gg.setColor(Color.BLACK);
        gg.fillRect(0,0,800,800);

        gg.translate(400, 400);

    
        gg.setColor(Color.RED);
        
        /*
       
        gg.drawLine((int)project(cube[0][0], cube[0][2]),  (int)project(cube[0][1], cube[0][2]),(int)project(cube[1][0], cube[1][2]),(int)project(cube[1][1], cube[1][2]));
        gg.drawLine((int)project(cube[1][0], cube[1][2]),  (int)project(cube[1][1], cube[1][2]),(int)project(cube[2][0], cube[2][2]),(int)project(cube[2][1], cube[2][2]));
        gg.drawLine((int)project(cube[2][0], cube[2][2]),  (int)project(cube[2][1], cube[2][2]),(int)project(cube[3][0], cube[3][2]),(int)project(cube[3][1], cube[3][2]));
        gg.drawLine((int)project(cube[3][0], cube[3][2]),  (int)project(cube[3][1], cube[3][2]),(int)project(cube[0][0], cube[0][2]),(int)project(cube[0][1], cube[0][2]));
 
        gg.drawLine((int)project(cube[4][0], cube[4][2]),  (int)project(cube[4][1], cube[4][2]),(int)project(cube[5][0], cube[5][2]),(int)project(cube[5][1], cube[5][2]));
        gg.drawLine((int)project(cube[5][0], cube[5][2]),  (int)project(cube[5][1], cube[5][2]),(int)project(cube[6][0], cube[6][2]),(int)project(cube[6][1], cube[6][2]));
        gg.drawLine((int)project(cube[6][0], cube[6][2]),  (int)project(cube[6][1], cube[6][2]),(int)project(cube[7][0], cube[7][2]),(int)project(cube[7][1], cube[7][2]));
        gg.drawLine((int)project(cube[7][0], cube[7][2]),  (int)project(cube[7][1], cube[7][2]),(int)project(cube[4][0], cube[4][2]),(int)project(cube[4][1], cube[4][2]));

        gg.drawLine((int)project(cube[0][0], cube[0][2]),  (int)project(cube[0][1], cube[0][2]),(int)project(cube[4][0], cube[4][2]),(int)project(cube[4][1], cube[4][2]));
        gg.drawLine((int)project(cube[1][0], cube[1][2]),  (int)project(cube[1][1], cube[1][2]),(int)project(cube[5][0], cube[5][2]),(int)project(cube[5][1], cube[5][2]));
        gg.drawLine((int)project(cube[2][0], cube[2][2]),  (int)project(cube[2][1], cube[2][2]),(int)project(cube[6][0], cube[6][2]),(int)project(cube[6][1], cube[6][2]));
        gg.drawLine((int)project(cube[3][0], cube[3][2]),  (int)project(cube[3][1], cube[3][2]),(int)project(cube[7][0], cube[7][2]),(int)project(cube[7][1], cube[7][2]));
       */


        /*
        gg.drawLine((int)project(pyramid[0][0], pyramid[0][2]),  (int)project(pyramid[0][1], pyramid[0][2]),(int)project(pyramid[1][0], pyramid[1][2]),(int)project(pyramid[1][1], pyramid[1][2]));
        gg.drawLine((int)project(pyramid[1][0], pyramid[1][2]),  (int)project(pyramid[1][1], pyramid[1][2]),(int)project(pyramid[2][0], pyramid[2][2]),(int)project(pyramid[2][1], pyramid[2][2]));
        gg.drawLine((int)project(pyramid[2][0], pyramid[2][2]),  (int)project(pyramid[2][1], pyramid[2][2]),(int)project(pyramid[3][0], pyramid[3][2]),(int)project(pyramid[3][1], pyramid[3][2]));
        gg.drawLine((int)project(pyramid[3][0], pyramid[3][2]),  (int)project(pyramid[3][1], pyramid[3][2]),(int)project(pyramid[0][0], pyramid[0][2]),(int)project(pyramid[0][1], pyramid[0][2]));
        
        gg.drawLine((int)project(pyramid[0][0], pyramid[0][2]),  (int)project(pyramid[0][1], pyramid[0][2]),(int)project(pyramid[4][0], pyramid[4][2]),(int)project(pyramid[4][1], pyramid[1][2]));
        gg.drawLine((int)project(pyramid[1][0], pyramid[1][2]),  (int)project(pyramid[1][1], pyramid[1][2]),(int)project(pyramid[4][0], pyramid[4][2]),(int)project(pyramid[4][1], pyramid[2][2]));
        gg.drawLine((int)project(pyramid[2][0], pyramid[2][2]),  (int)project(pyramid[2][1], pyramid[2][2]),(int)project(pyramid[4][0], pyramid[4][2]),(int)project(pyramid[4][1], pyramid[3][2]));
        gg.drawLine((int)project(pyramid[3][0], pyramid[3][2]),  (int)project(pyramid[3][1], pyramid[3][2]),(int)project(pyramid[4][0], pyramid[4][2]),(int)project(pyramid[4][1], pyramid[0][2]));

        */
       
        gg.drawLine((int)project(pcube[0][0], pcube[0][2]),  (int)project(pcube[0][1], pcube[0][2]),(int)project(pcube[1][0], pcube[1][2]),(int)project(pcube[1][1], pcube[1][2])); 
        gg.drawLine((int)project(pcube[1][0], pcube[1][2]),  (int)project(pcube[1][1], pcube[1][2]),(int)project(pcube[2][0], pcube[2][2]),(int)project(pcube[2][1], pcube[2][2])); 
        gg.drawLine((int)project(pcube[2][0], pcube[2][2]),  (int)project(pcube[2][1], pcube[2][2]),(int)project(pcube[3][0], pcube[3][2]),(int)project(pcube[3][1], pcube[3][2]));   
        gg.drawLine((int)project(pcube[3][0], pcube[3][2]),  (int)project(pcube[3][1], pcube[3][2]),(int)project(pcube[0][0], pcube[0][2]),(int)project(pcube[0][1], pcube[0][2]));
        
        gg.drawLine((int)project(pcube[4][0], pcube[4][2]),  (int)project(pcube[4][1], pcube[4][2]),(int)project(pcube[5][0], pcube[5][2]),(int)project(pcube[5][1], pcube[5][2]));
        gg.drawLine((int)project(pcube[5][0], pcube[5][2]),  (int)project(pcube[5][1], pcube[5][2]),(int)project(pcube[6][0], pcube[6][2]),(int)project(pcube[6][1], pcube[6][2]));
        gg.drawLine((int)project(pcube[6][0], pcube[6][2]),  (int)project(pcube[6][1], pcube[6][2]),(int)project(pcube[7][0], pcube[7][2]),(int)project(pcube[7][1], pcube[7][2]));
        gg.drawLine((int)project(pcube[7][0], pcube[7][2]),  (int)project(pcube[7][1], pcube[7][2]),(int)project(pcube[4][0], pcube[4][2]),(int)project(pcube[4][1], pcube[4][2]));

        gg.drawLine((int)project(pcube[0][0], pcube[0][2]),  (int)project(pcube[0][1], pcube[0][2]),(int)project(pcube[4][0], pcube[4][2]),(int)project(pcube[4][1], pcube[4][2]));
        gg.drawLine((int)project(pcube[1][0], pcube[1][2]),  (int)project(pcube[1][1], pcube[1][2]),(int)project(pcube[5][0], pcube[5][2]),(int)project(pcube[5][1], pcube[5][2]));
        gg.drawLine((int)project(pcube[2][0], pcube[2][2]),  (int)project(pcube[2][1], pcube[2][2]),(int)project(pcube[6][0], pcube[6][2]),(int)project(pcube[6][1], pcube[6][2]));
        gg.drawLine((int)project(pcube[3][0], pcube[3][2]),  (int)project(pcube[3][1], pcube[3][2]),(int)project(pcube[7][0], pcube[7][2]),(int)project(pcube[7][1], pcube[7][2]));
       
       gg.setColor(Color.BLUE);

        gg.drawLine((int)project(pcube[8][0], pcube[8][2]),  (int)project(pcube[8][1], pcube[8][2]),(int)project(pcube[9][0], pcube[9][2]),(int)project(pcube[9][1], pcube[9][2]));
        gg.drawLine((int)project(pcube[9][0], pcube[9][2]),  (int)project(pcube[9][1], pcube[9][2]),(int)project(pcube[10][0], pcube[10][2]),(int)project(pcube[10][1], pcube[10][2]));
        gg.drawLine((int)project(pcube[10][0], pcube[10][2]),  (int)project(pcube[10][1], pcube[10][2]),(int)project(pcube[11][0], pcube[11][2]),(int)project(pcube[11][1], pcube[11][2]));
        gg.drawLine((int)project(pcube[11][0], pcube[11][2]),  (int)project(pcube[11][1], pcube[11][2]),(int)project(pcube[8][0], pcube[8][2]),(int)project(pcube[8][1], pcube[8][2]));

        gg.drawLine((int)project(pcube[12][0], pcube[12][2]),  (int)project(pcube[12][1], pcube[12][2]),(int)project(pcube[13][0], pcube[13][2]),(int)project(pcube[13][1], pcube[13][2]));
        gg.drawLine((int)project(pcube[13][0], pcube[13][2]),  (int)project(pcube[13][1], pcube[13][2]),(int)project(pcube[14][0], pcube[14][2]),(int)project(pcube[14][1], pcube[14][2]));
        gg.drawLine((int)project(pcube[14][0], pcube[14][2]),  (int)project(pcube[14][1], pcube[14][2]),(int)project(pcube[15][0], pcube[15][2]),(int)project(pcube[15][1], pcube[15][2]));
        gg.drawLine((int)project(pcube[15][0], pcube[15][2]),  (int)project(pcube[15][1], pcube[15][2]),(int)project(pcube[12][0], pcube[12][2]),(int)project(pcube[12][1], pcube[12][2]));

        gg.drawLine((int)project(pcube[8][0], pcube[8][2]),  (int)project(pcube[8][1], pcube[8][2]),(int)project(pcube[12][0], pcube[12][2]),(int)project(pcube[12][1], pcube[12][2]));
        gg.drawLine((int)project(pcube[9][0], pcube[9][2]),  (int)project(pcube[9][1], pcube[9][2]),(int)project(pcube[13][0], pcube[13][2]),(int)project(pcube[13][1], pcube[13][2]));
        gg.drawLine((int)project(pcube[10][0], pcube[10][2]),  (int)project(pcube[10][1], pcube[10][2]),(int)project(pcube[14][0], pcube[14][2]),(int)project(pcube[14][1], pcube[14][2]));
        gg.drawLine((int)project(pcube[11][0], pcube[11][2]),  (int)project(pcube[11][1], pcube[11][2]),(int)project(pcube[15][0], pcube[15][2]),(int)project(pcube[15][1], pcube[15][2]));

        gg.drawLine((int)project(pcube[0][0], pcube[0][2]),  (int)project(pcube[0][1], pcube[0][2]),(int)project(pcube[8][0], pcube[8][2]),(int)project(pcube[8][1], pcube[8][2]));
        gg.drawLine((int)project(pcube[1][0], pcube[1][2]),  (int)project(pcube[1][1], pcube[1][2]),(int)project(pcube[9][0], pcube[9][2]),(int)project(pcube[9][1], pcube[9][2]));
        gg.drawLine((int)project(pcube[2][0], pcube[2][2]),  (int)project(pcube[2][1], pcube[2][2]),(int)project(pcube[10][0], pcube[10][2]),(int)project(pcube[10][1], pcube[10][2]));
        gg.drawLine((int)project(pcube[3][0], pcube[3][2]),  (int)project(pcube[3][1], pcube[3][2]),(int)project(pcube[11][0], pcube[11][2]),(int)project(pcube[11][1], pcube[11][2]));
        gg.drawLine((int)project(pcube[4][0], pcube[4][2]),  (int)project(pcube[4][1], pcube[4][2]),(int)project(pcube[12][0], pcube[12][2]),(int)project(pcube[12][1], pcube[12][2]));
        gg.drawLine((int)project(pcube[5][0], pcube[5][2]),  (int)project(pcube[5][1], pcube[5][2]),(int)project(pcube[13][0], pcube[13][2]),(int)project(pcube[13][1], pcube[13][2]));
        gg.drawLine((int)project(pcube[6][0], pcube[6][2]),  (int)project(pcube[6][1], pcube[6][2]),(int)project(pcube[14][0], pcube[14][2]),(int)project(pcube[14][1], pcube[14][2]));
        gg.drawLine((int)project(pcube[7][0], pcube[7][2]),  (int)project(pcube[7][1], pcube[7][2]),(int)project(pcube[15][0], pcube[15][2]),(int)project(pcube[15][1], pcube[15][2]));    


        //on mac vertical shift is 28 pixels
        
        

    }

    public void animate(){
/*
        for(int i = 0; i < pcube.length; i++){
        rotate(hcube[i], 1);
            for(int k = 0; k < pcube[0].length; k++){
                pcube[i][k]=project(hcube[i][k], hcube[i][3]);
            }
            
        }
       */ 

        for(int i = 0; i < pcube.length; i++){

           rotateYW(hcube[i]);
            rotateZW(hcube[i]);
            
            
            
            for(int j=0; j < pcube[0].length; j++){
            pcube[i][j]=project(hcube[i][j], hcube[i][3]);
            
            }
            
        }
        

/*
        for(int i = 0; i < cube.length; i++){
          //  rotate(cube[i], 2);
            rotate(cube[i], 1);
            rotate(cube[i], 0);

            
        }
   */
/*

        for(int i = 0; i < pyramid.length; i++){
            rotate(pyramid[i], 0);
            rotate(pyramid[i], 1);
            rotate(pyramid[i], 2);

        }
        */

    

        this.repaint();
    }

    public void rotate(double[] input){
        double a = input[0];
        double b = input[1]; 
        double c = input[2];
        input[0] = a;
        input[1] = (b*Math.cos(theta))-(c*Math.sin(theta));
        input[2] = (c*Math.cos(theta))+(b*Math.sin(theta));
    }


public void rotateZW(double[] input){
    double a = input[0];
    double b = input[1]; 
    double c = input[2];
    double d = input[3];
    input[0] = a;
    input[1] = b;
    input[2] = c * Math.cos(theta) + d * Math.sin(theta);
    input[3] = d * Math.cos(theta) - c * Math.sin(theta);
}

public void rotateXW(double[] input){
    double a = input[0];
    double b = input[1];
    double c = input[2];
    double d = input[3];
    input[0] = a * Math.cos(theta) - d * Math.sin(theta);
    input[1] = b;
    input[2] = c;
    input[3] = a * Math.sin(theta) + d * Math.cos(theta);
}

public void rotateYW(double[] input){
    double a = input[0];
    double b = input[1];
    double c = input[2];
    double d = input[3];
    input[0] = a;
    input[1] = b * Math.cos(theta) - d * Math.sin(theta);
    input[2] = c;
    input[3] = b * Math.sin(theta) + d * Math.cos(theta);
}

public void rotateXY(double[] input){
    double a = input[0];
    double b = input[1];
    double c = input[2];
    double d = input[3];
    input[0] = a;
    input[1] = b * Math.cos(theta) - c * Math.sin(theta);
    input[2] = b * Math.sin(theta) + c * Math.cos(theta);
    input[3] = d;
}

    public void rotate(double[] input, int count){
 
        System.out.println();

        for(int i = 0; i < count; i++){
            double start = input[0];
            input[0] = input[1];
            input[1] = input[2];
            input[2] = start;
        }

        rotate(input);



        for(int i = 0; i < count; i++){
            double start = input[2];
            input[2] = input[1];
            input[1] = input[0];
            input[0] = start;
        }

       
    }

    public double project(double input, double z){
    double p = (((input) * fov)/(z+fov));
    return p;
    }

    public double project4D(double input, double w){
        return (input/(w));

    }
    public static void main(String args[]){
        Main m = new Main();
        System.out.println(m.project(m.pcube[2][0], m.pcube[1][2]));

/*
        for(int i = 0; i < m.pcube.length; i++){

            for(int j=0; j < 10; j++){
            m.rotate(m.hcube[i], 1);
            
            }
            for(int k = 0; k < m.pcube[0].length; k++){
                m.pcube[i][k]=m.project(m.hcube[i][k], m.hcube[i][3]);
            }
            
        }
*/


        
        
        
        


        while(true){
      m.animate();
      try{
      Thread.sleep(10);
    }catch(InterruptedException ex){}
      }

      
    }

    
}