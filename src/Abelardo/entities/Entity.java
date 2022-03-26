package Abelardo.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Game.Game;

public class Entity {
	
	protected int x, y, width, heigth;
	private BufferedImage sprite;
	
	public static BufferedImage VIDA_EN = Game.spritesheet.getSprite(95, 0, 16, 14);
	public static BufferedImage ANEL_EN = Game.spritesheet.getSprite(114, 2, 12, 12);
	public static BufferedImage INIMIGO_EN = Game.spritesheet.getSprite(112, 18, 15, 14);
	public static BufferedImage CARGAS_EN = Game.spritesheet.getSprite(96, 17, 16, 15);
	
	
	
	
	public Entity(int x,int y,int width,int heigth, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.heigth = heigth;
		this.sprite = sprite;
	}
	
	public void setX(int newX) {
		this.x = newX;
	}
	public void setY(int newY) {
		this.y = newY;
	}
	
	public int getX() {
		return (int)this.x;
	}
	public int getY() {
		return (int)this.y;
	}
	public int getWidth() {
		return (int)this.width;
	}
	public int getHeigth() {
		return (int)this.heigth;
	}
	
	
	public void render(Graphics g) {
		g.drawImage(sprite,this.getX(),this.getY(), null);
	}
	
	public void tick() {
		
	}
	
	

}
