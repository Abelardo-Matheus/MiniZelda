package Abelardo.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Abelardo.word.Camera;
import Game.Game;

public class Vida extends Entity{


	private int frames = 0, maxframes = 15, index = 0, maxindex = 3;
	private BufferedImage[] sprites3;

	public Vida(int x, int y, int width, int heigth, BufferedImage sprite) {
		super(x, y, width, heigth, sprite);
		sprites3 = new BufferedImage[4];
		sprites3[0] = Game.spritesheet.getSprite(97, 20,  14, 12);
		sprites3[1] = Game.spritesheet.getSprite(113, 20, 14, 12);
		sprites3[2] = Game.spritesheet.getSprite(127, 20, 14, 12);
		sprites3[3] = Game.spritesheet.getSprite(142, 20, 14, 12);

	}
	public void render(Graphics g) {
		
		g.drawImage(sprites3[index], this.getX() - Camera.x,this.getY()-Camera.y, null);
				
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