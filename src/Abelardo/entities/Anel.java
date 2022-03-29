package Abelardo.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Abelardo.word.Camera;
import Game.Game;

public class Anel extends Entity{
	

	private int frames = 0, maxframes = 16, index = 0, maxindex = 4;
	private BufferedImage[] sprites;

	public Anel(int x, int y, int width, int heigth, BufferedImage sprite) {
		super(x, y, width, heigth, sprite);
		sprites = new BufferedImage[5];
		sprites[0] = Game.spritesheet.getSprite(88, 68,  11, 18);
		sprites[1] = Game.spritesheet.getSprite(100, 68, 11, 18);
		sprites[2] = Game.spritesheet.getSprite(114, 68, 11, 18);
		sprites[3] = Game.spritesheet.getSprite(129, 68, 11, 18);
		sprites[4] = Game.spritesheet.getSprite(143, 68, 11, 18);

	}
	public void render(Graphics g) {
		
		g.drawImage(sprites[index], this.getX() - Camera.x,this.getY()-Camera.y, null);
				
	}
	public void tick() {
		
		frames++;
		if(frames==maxframes) {
			frames=0;
			index++;
			if(index>maxindex) {
				index=0;
			}
		}
	}

}
