package Abelardo.Graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import Abelardo.entities.Player;

public class UI {
	
	public void render (Graphics g) {
		
		Rectangle2D.Double rect1;
		
		g.setColor(Color.black);
		g.fillRect(3, 8, 108, 11);
		g.setColor(Color.red);
		g.fillRect(5, 10, 104, 7);
		g.setColor(new Color(0, 255, 200));
		g.fillRect(5, 10, ((int)Player.vida/2)+4, 7);
		g.setColor(Color.white);
		g.setFont(new Font("Arial ", Font.CENTER_BASELINE,9));
		g.drawString("vida:" +(int)Player.vida, 8,17);
		
		g.setColor(new Color(0, 255, 0));
		Graphics2D g2d = (Graphics2D) g;
		

	        g2d.setColor(Color.red);
	        
	        rect1 = new Rectangle2D.Double(140, 50, 10,(+Player.carga*4));
	        g2d.rotate(3.14,80, 100);
	        g2d.fill(rect1);
	        g2d.draw(rect1);
	    
	          
	         
	    
	    
	   
	}
	
	
	

}
