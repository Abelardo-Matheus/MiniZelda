package Abelardo.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Abelardo.word.Camera;
import Game.Game;

public class Entity {
	
	protected int x, y, width, heigth;
	protected BufferedImage sprite;
	
	private int maskx = 0, masky =0,mwidth=16 , mheigth=16;
	
	public static BufferedImage VIDA_EN = Game.spritesheet.getSprite(97, 20, 14, 12);
	public static BufferedImage ANEL_EN = Game.spritesheet.getSprite(115, 3, 10, 10);
	public static BufferedImage INIMIGO_EN = Game.spritesheet.getSprite(95, 35, 15, 15);
	public static BufferedImage CARGAS_EN = Game.spritesheet.getSprite(62, 53, 16, 15);
	
	
	
	
	public Entity(int x,int y,int width,int heigth, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.heigth = heigth;
		this.sprite = sprite;
		
		this.maskx = 0;
		this.masky = 0;
		this.heigth = heigth;
		this.width = width;
	}
	
	public void setMask(int maskx, int masky, int mwidth, int mheigth) {
		
		this.maskx = maskx;
		this.masky = masky;
		this.mwidth = mwidth;
		this.mheigth = mheigth;
		
	}
	
	public void setX(int newX) {
		this.x = newX;
	}
	public void setY(int newY) {
		this.y = newY;
	}
	
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeigth() {
		return this.heigth;
	}
	public static boolean isColliding(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskx, e1.getY()+e1.masky, e1.mwidth, e1.mheigth);
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskx, e2.getY()+e2.masky, e2.mwidth, e2.mheigth);
		
		return e1Mask.intersects(e2Mask);
	}
	
	public void render(Graphics g) {
		
		g.drawImage(sprite,this.getX() - Camera.x,this.getY() - Camera.y, null);
		g.setColor(Color.red);
		g.fillRect(this.getX()+maskx - Camera.x,this.getY()+ masky - Camera.y, mwidth, mheigth);
	}
	
	public void tick() {
		
	}
	
	
	
	

}
