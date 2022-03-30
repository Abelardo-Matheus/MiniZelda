package Abelardo.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Abelardo.word.Camera;
import Game.Game;

public class TiroFogo extends Entity{
	
	private double dx;
	private double dy;
	private double spd = 3;
	private BufferedImage spritetiro;
	//private BufferedImage[] spritetiroE;
	public static boolean tiroE = false, tiroD = false;
	
	public static int direito_dir = 0,esquerdo_dir = 1;
	public static int dir = direito_dir;
	
	private int life = 100, curLife = 0;;
	
	private int frames = 0, maxframes = 5, index = 0, maxindex = 3;
	
	
	public void tick() {
		
		x += dx*spd;
		y += dy*spd;
		curLife++;
		if(curLife == life) {
			Game.balas.remove(this);
			return;
		}
		
		frames++;
		if(frames==maxframes) {
			frames=0;
			index++;
			if(index>maxindex) {
				index=0;
			}
		}
		
	}
	
	public TiroFogo(int x, int y, int width, int heigth, BufferedImage sprite, double dx, double dy) {
		super(x, y, width, heigth, sprite);
		this.dx = dx;
		this.dy = dy;
		
		//spritetiroD = new BufferedImage[4];
		//spritetiroE = new BufferedImage[4];a
		spritetiro = Game.spritesheet.getSprite(90, 106, 8, 6);
		//spritetiroD[1] = Game.spritesheet.getSprite(95, 103, 18, 14);
		//spritetiroD[2] = Game.spritesheet.getSprite(115, 103, 18, 14);
		//spritetiroD[3] = Game.spritesheet.getSprite(137, 103, 18, 14);
		
		//spritetiroE[0] = Game.spritesheet.getSprite(139, 119, 18, 14);
		//spritetiroE[1] = Game.spritesheet.getSprite(118, 119, 18, 14);
		//spritetiroE[2] = Game.spritesheet.getSprite(98, 119, 18, 14);
		//spritetiroE[3] = Game.spritesheet.getSprite(76, 119, 18, 14);
		
	}
	
	public void render(Graphics g) {
		
		
		g.drawImage(spritetiro, this.getX()+4 - Camera.x,this.getY()+8-Camera.y, null);
		
		//g.setColor(Color.white);
		//g.fillOval(this.getX()-Camera.x, this.getY()-Camera.y, 3, 3);
		
		//if(dir == direito_dir) {
		

		
		//}else if(dir == esquerdo_dir ) {
		//	g.drawImage(spritetiroE[index], this.getX()-4 - Camera.x,this.getY()+8-Camera.y, null);

		//}
		
		
	}
	

}
