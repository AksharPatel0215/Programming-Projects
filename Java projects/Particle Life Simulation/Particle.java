package main;

public class Particle {
private double x;
private double y;
private double velocityX;
private double velocityY;
private int type;

public Particle(double x, double y, double velocityX, double velocityY,int type) {
	this.setX(x);
	this.setY(y);
	this.setType(type);
	this.setVelocityX(velocityX);
	this.setVelocityY(velocityY);
}

public int getType() {
	return type;
}

public void setType(int type) {
	this.type = type;
}

public double getVelocityY() {
	return velocityY;
}

public void setVelocityY(double velocityY) {
	this.velocityY = velocityY;
}

public double getVelocityX() {
	return velocityX;
}

public void setVelocityX(double velocityX) {
	this.velocityX = velocityX;
}

public double getY() {
	return y;
}

public void setY(double y) {
	this.y = y;
}

public double getX() {
	return x;
}

public void setX(double x) {
	this.x = x;
}


void updatePosition(int width, int height) {
x += velocityX;
y += velocityY;

if(x < 0)
	x+= width;
if(y < 0)
	y+=height;
if(x>=width)
	x-= width;
if(y>=height)
	y-=height;
}
}