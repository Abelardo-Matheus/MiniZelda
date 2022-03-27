package Abelardo.Graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Abelardo.entities.Player;

public class UI {
	
	public void render (Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect(3, 8, 108, 11);
		g.setColor(Color.red);
		g.fillRect(5, 10, 104, 7);
		g.setColor(new Color(0, 255, 200));
		g.fillRect(5, 10, ((int)Player.vida/2)+4, 7);
		g.setColor(Color.white);
		g.setFont(new Font("Arial ", Font.CENTER_BASELINE,9));
		g.drawString("vida:" +(int)Player.vida, 8,17);
	}

}
