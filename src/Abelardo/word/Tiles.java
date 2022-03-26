package Abelardo.word;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Game.Game;

public class Tiles {
	
	public static BufferedImage TILE_CHAO = Game.spritesheet.getSprite(0, 0, 16, 16);
	public static BufferedImage TILE_PAREDEL = Game.spritesheet.getSprite(16,0 , 16, 16);
	public static BufferedImage TILE_PAREDED = Game.spritesheet.getSprite(0, 16, 16, 16);
	public static BufferedImage TILE_PAREDEE = Game.spritesheet.getSprite(16, 16, 16, 16);
	public static BufferedImage TILE_PAREDEB = Game.spritesheet.getSprite(0, 33, 16, 16);
	public static BufferedImage TILE_PAREDEC = Game.spritesheet.getSprite(16, 33, 16, 16);
	public static BufferedImage TILE_PAREDECANTO = Game.spritesheet.getSprite(16, 50, 16, 16);
	
	private BufferedImage sprite;
	private int x, y;
	
	public Tiles(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
		
	public void render(Graphics g) {
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
		
		
	}
	

}
