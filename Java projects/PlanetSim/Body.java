public class Body {
    private double mass;
    private int radius; 
    private double[] pos;
    private double[] vel;
    private double[] acc;
    
    public Body(double X, double Y, double mass){
        this.mass = mass;
        this.radius = (int)mass * 2 / 5;
        pos = new double[2];
        vel = new double[2];
        acc = new double[2];
        pos[0]=X;
        pos[1]=Y;
    }

    public double getX(){
        return pos[0];
    }

    public double getY(){
        return pos[1];
    }

    public double[] getAcc(){
        return acc;
    }

    public double[] getVel(){
        return vel;
    }

    public void setVel(double a, double b){
        vel[0] = a;
        vel[1] = b;
    }

    public double getMass(){
        return mass;
    }

    public int getRad(){
        return radius;
    }

    public double getDistance(Body other){
        double d =  Math.sqrt(Math.pow(pos[0]-other.getX(), 2)+Math.pow(pos[1]-other.getY(), 2));
        if(d*d < 100){
            d = 10;
        }
        return d;
        
    }

    public double getXDistance(Body other){
        double d =  pos[0]-other.getX();
        if(d*d < 1000){
            d = 100;
        }

        return d;     
    }

    public double getYDistance(Body other){
        double d =  pos[1]-other.getY();
        if(d*d < 1000){
            d = 100;
        }

        return d;     
    }

    public double[] getNorm(Body other){
        double[] norm = new double[2];
        double r = this.getDistance(other);
        norm[0] = (other.getX()-pos[0])/r;
        norm[1] = (other.getY()-pos[1])/r;
        return norm;
    }

    public void VectorAdd(double[] a, double[] b){
        for(int i =0; i < a.length; i++){
            a[i]+=b[i];
        }
    }


public void attractTo(Body other){
    double[] norm = getNorm(other);
    double deltaTime = 0.1;
    double dx = getXDistance(other);
    double dy = getXDistance(other);
    dx*=dx;
    dy*=dy;
    double G = 100;
    double Xforce = G * this.mass * other.getMass() / dx;
    double Yforce = G * this.mass * other.getMass() / dy;
    norm[0] *= Xforce / this.mass;
    norm[1] *= Yforce / this.mass;
    acc[0] = norm[0];
    acc[1] = norm[1];
    this.vel[0] += this.acc[0] * deltaTime;
    this.vel[1] += this.acc[1] * deltaTime;
    this.pos[0] += this.vel[0] * deltaTime;
    this.pos[1] += this.vel[1] * deltaTime;
    System.out.println("x: "+this.vel[0]);
    System.out.println("y: "+this.vel[1]);

}







    public void update(){
        pos[0]+=vel[0]*0.1;
        pos[1]+=vel[1]*0.1;
        vel[0]+=acc[0]*0.1;
        vel[1]+=acc[1]*0.1;
    }


    

/*
    public double getForce(Body other){

    }

*/



    /*
    F=ma
    a = v^2/r
    F= mv^2/r
    F = Gm1m2/r^2
    Gm1m2/r^2=mv^2/r
    v = sqrt(Gm1/r) for m1
    v = sqrt(Gm2/r) for m2

    p=mv
    p= integral(F)dt = Ft
    x = x +integral(mv/m)dt = x+integral(v)dt = x + dx

    Step 1:
    locate positions of each body and find radius
    Step 2: 
    Normalize vector between bodies by dividing components by magnitude
    Step 3:
    use radius, mass, normalized component, and other constants to find gravitational force F
    Step 4:
    Integrate( but not really) F over time dt to get impulse.
    Step 5:
    Divide impulse by mass to obtain velocity
    Step 6: 
    Integrate( again not really) velocity over time to get displacement dx
    Step 7:
    add dx to x to get updated x position
    Step 8:
    Kinda reverse and repeat for other body because force F is attraction between both bodies
    Step 9: 
    repeat with vertical movement to get updated y position
    
    */
}