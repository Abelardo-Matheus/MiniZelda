package Abelardo.entities;

import java.awt.image.BufferedImage;

import Abelardo.word.Word;
import Game.Game;

public class Inimigo extends Entity {
	
	private double spd = 1;

	public Inimigo(int x, int y, int width, int heigth, BufferedImage sprite) {
		super(x, y, width, heigth, sprite);
	}
	
	public void tick() {
		
		if((int)x < Game.player.getX() && Word.isFree((int)(x+spd), this.getY())) {
			x+=spd;
		}if((int)x > Game.player.getX()&& Word.isFree((int)(x-spd), this.getY())) {
			x-=spd;
		}if((int)y < Game.player.getY()&& Word.isFree(this.getX(), (int)(y+spd))) {
			y+=spd;
		}if((int)y > Game.player.getY()&& Word.isFree(this.getX(), (int)(y-spd))) {
			y-=spd;
		}
		
		
	}

}
