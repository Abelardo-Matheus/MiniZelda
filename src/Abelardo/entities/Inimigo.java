package Abelardo.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Abelardo.word.Camera;
import Abelardo.word.Word;
import Game.Game;

public class Inimigo extends Entity {
	
	private double spd = 1;
	
	private int maskx =8, masky = 8,maskw =10, maskh=10;

	public Inimigo(int x, int y, int width, int heigth, BufferedImage sprite) {
		super(x, y, width, heigth, sprite);
	}
	
	public void tick() {
		
		maskx = 0;
		masky = 0;
		maskw = 15;
		maskh = 15;
		
		if((int)x < Game.player.getX() && Word.isFree((int)(x+spd), this.getY())
				&& !isColliding((int)(x+spd), this.getY())) {
			x+=spd;
		}if((int)x > Game.player.getX()&& Word.isFree((int)(x-spd), this.getY())
				&& !isColliding((int)(x-spd), this.getY())) {
			x-=spd;
		}if((int)y < Game.player.getY()&& Word.isFree(this.getX(), (int)(y+spd))
				&& !isColliding(this.getX(), (int)(y+spd))) {
			y+=spd;
		}if((int)y > Game.player.getY()&& Word.isFree(this.getX(), (int)(y-spd))
				&& !isColliding(this.getX(), (int)(y-spd))) {
			y-=spd;
		}
	}
		
	public boolean isColliding(int xNext, int yNext) {
		Rectangle inimigoCurrent = new Rectangle(xNext + maskx,yNext + masky,maskw, maskh);
		
		for(int i = 0; i< Game.inimigos.size();i++) {
			Inimigo e =Game.inimigos.get(i);
			if(e == this) 
				continue;
		Rectangle TargetInimigo =  new Rectangle(e.getX() + maskx,e.getY() + masky,maskw, maskh);
		if(inimigoCurrent.intersects(TargetInimigo)){
			return true;
		}
			
		}
		
		return false;
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.red);
		g.fillRect(this.getX() + maskx - Camera.x,this.getY() + masky - Camera.y, maskw, maskh);
		
	}

}
