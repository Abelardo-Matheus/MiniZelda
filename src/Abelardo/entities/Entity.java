package Abelardo.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {
	
	protected int x, y, width, heigth;
	private BufferedImage sprite;
	
	
	
	public Entity(int x,int y,int width,int heigth, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.heigth = heigth;
		this.sprite = sprite;
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
	
	
	public void render(Graphics g) {
		g.drawImage(sprite,this.getX(),this.getY(), null);
	}
	
	public void tick() {
		
	}
	
	

}
