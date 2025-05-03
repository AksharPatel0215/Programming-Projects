package main;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import java.util.Timer;


public class Main {
final static double MAX_DISTANCE = 1131.370;
public static void main(String args[]) {
	List<Particle> particles = new ArrayList<Particle>();
	InteractionRules rules = new InteractionRules(5);
	
	rules.setRule(0, 0, 0.03);  // Type 0 clusters with its own kind
	rules.setRule(0, 1, 0.02);  // Type 0 mildly attracted to Type 1
	rules.setRule(0, 2, -0.02); // Type 0 avoids Type 2
	rules.setRule(0, 3, -0.01); // Type 0 slightly avoids Type 3
	rules.setRule(0, 4, 0.1);  // Type 0 mildly pushes Type 4 (density)

	rules.setRule(1, 0, 0.01);  // Type 1 slightly attracted to Type 0
	rules.setRule(1, 1, 0.05);  // Type 1 clusters with its own kind
	rules.setRule(1, 2, -0.03); // Type 1 avoids Type 2
	rules.setRule(1, 3, -0.02); // Type 1 avoids Type 3
	rules.setRule(1, 4, 0.02);  // Type 1 mildly pushes Type 4 (density)

	rules.setRule(2, 0, -0.02); // Type 2 avoids Type 0
	rules.setRule(2, 1, -0.03); // Type 2 avoids Type 1
	rules.setRule(2, 2, 0.04);  // Type 2 clusters with its own kind
	rules.setRule(2, 3, 0.01);  // Type 2 slightly attracted to Type 3
	rules.setRule(2, 4, 0.2);  // Type 2 pushes Type 4 (density)

	rules.setRule(3, 0, -0.01); // Type 3 avoids Type 0
	rules.setRule(3, 1, -0.02); // Type 3 avoids Type 1
	rules.setRule(3, 2, 0.01);  // Type 3 slightly attracted to Type 2
	rules.setRule(3, 3, 0.06);  // Type 3 clusters with its own kind
	rules.setRule(3, 4, 0.1);  // Type 3 strongly pushes Type 4 (density)

	rules.setRule(4, 0, 0.01);  // Type 4 slightly attracted to Type 0
	rules.setRule(4, 1, 0.02);  // Type 4 mildly attracted to Type 1
	rules.setRule(4, 2, 0.03);  // Type 4 moderately attracted to Type 2
	rules.setRule(4, 3, 0.05);  // Type 4 strongly attracted to Type 3
	rules.setRule(4, 4, 0.00);  // Type 4 does not cluster with itself


	
	for(int i = 0; i < 2000; i++) {
	particles.add(new Particle(
	Math.random() * 800,
	Math.random() * 800,
	Math.random() * 1 - 0.5,
    Math.random() * 1 - 0.5,
	(int)(Math.random() * 5)
			));
	}
	
	 JPanel panel = new JPanel() {
         @Override
         protected void paintComponent(Graphics g) {
             super.paintComponent(g);
             g.setColor(Color.BLACK);
             g.fillRect(0, 0, 800, 800);
             for (Particle p : particles) {
                 switch (p.getType()) {
                     case 0 -> g.setColor(Color.RED);
                     case 1 -> g.setColor(Color.BLUE);
                     case 2 -> g.setColor(Color.GREEN);
                     case 3 -> g.setColor(Color.MAGENTA);
                     case 4 -> g.setColor(Color.ORANGE);
                 }
                 g.fillOval((int) p.getX(), (int) p.getY(), 5, 5);
             }
         }
     };
     panel.setPreferredSize(new Dimension(800, 800));

     // Set up the JFrame
     JFrame frame = new JFrame("Particle Life Simulation");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.add(panel);
     frame.pack();
     frame.setVisible(true);


	while(true) {
		try {
		
		for (Particle p : particles) {
	        applyForces(p, particles, rules);
	        p.updatePosition(800, 800);
	    }
	    panel.repaint();
	    Thread.sleep(10);
		} catch(InterruptedException e) {
			
		}
	}
	
	
}
public static void applyForces(Particle p, List<Particle> particles, InteractionRules rules) {
    double fx = 0, fy = 0;
    double forceScaling = 0.01; // Scaling factor for forces
    double maxSpeed = 4.0; // Maximum allowed speed for particles

    for (Particle other : particles) {
        if (p == other)
            continue;

        double dx = other.getX() - p.getX();
        double dy = other.getY() - p.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance > 0 && distance < 100) {
            double force = rules.getRule(p.getType(), other.getType());
       


            if (p.getType() == other.getType()) {
                // Adjust same-type interactions to include stronger repulsion at close range
                if (distance < 20) {
                    force = -Math.abs(force * 8); // Strong repulsion at very short distances
                } else if (distance < 50) {
                    force *= -0.5; // Slight repulsion at mid-range
                } else {
                    force *= 0.2; // Weak attraction at long range
                }
            } else {
                // Different-type interactions (as before)
                if (distance < 20) {
                    force = -Math.abs(force * 10); // Strong repulsion at very short distances
                } else if (distance < 50) {
                    force *= (50 - distance) / 30.0; // Gradual transition between repulsion and attraction
                } else {
                    force = Math.abs(force) * (distance / 100); // Attraction at larger distances
                }
            }

            fx += forceScaling * force * dx / distance;
            fy += forceScaling * force * dy / distance;
        }
    }

    // Update velocity with damping
    p.setVelocityX((p.getVelocityX() + fx) * 0.98); // Slightly less damping
    p.setVelocityY((p.getVelocityY() + fy) * 0.98);

    // Cap the velocity
    double speed = Math.sqrt(p.getVelocityX() * p.getVelocityX() + p.getVelocityY() * p.getVelocityY());
    if (speed > maxSpeed) {
        p.setVelocityX(p.getVelocityX() / speed * maxSpeed);
        p.setVelocityY(p.getVelocityY() / speed * maxSpeed);
    }
}









}
