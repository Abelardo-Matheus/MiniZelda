package Abelardo.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


import Abelardo.word.Camera;
import Abelardo.word.Word;
import Game.Game;


public class Player extends Entity{
	
	public boolean right ,up,down , left ;
	public int direito_dir = 0,esquerdo_dir = 1;
	public int dir = direito_dir;
	public int spd = 2;
	
	public static double Maxvida = 100,vida =200;
	
	private int frames = 0, maxframes = 10, index = 0, maxindex = 3;
	private boolean moved = false;
	private BufferedImage[] DireitoP;
	private BufferedImage[] EsquerdoP;
	
	public static int carga = 0;
	
	public Player(int x, int y,int width, int heigth, BufferedImage sprite) {
		super(x ,y, width, heigth,sprite);
		
		DireitoP = new BufferedImage[4];
		EsquerdoP = new BufferedImage[4];
		
		DireitoP[0] = Game.spritesheet.getSprite(34, 0, 12, 16);
		DireitoP[1] = Game.spritesheet.getSprite(50, 0, 12, 16);
		DireitoP[2] = Game.spritesheet.getSprite(66, 0, 12, 16);
		DireitoP[3] = Game.spritesheet.getSprite(82, 0, 12, 16);
		
		EsquerdoP[3] = Game.spritesheet.getSprite(82, 16, 12, 16);
		EsquerdoP[2] = Game.spritesheet.getSprite(66, 16, 12, 16);
		EsquerdoP[1] = Game.spritesheet.getSprite(50, 16, 12, 16);
		EsquerdoP[0] = Game.spritesheet.getSprite(34, 16, 12, 16);
		
	}
	
	
	
	
	
	
	public void tick() {
		moved = false;
		if(right && Word.isFree((int)(x+spd), this.getY())) {
			moved = true;
			dir = direito_dir;
			x+=spd;
			
		}else if(left && Word.isFree((int)(x-spd),this.getY())) {
			moved = true;
			dir = esquerdo_dir;
			 x-=spd;
		}
		
		if(up && Word.isFree(this.getX(),(int)(y-spd))) {
			moved = true;
			y-=spd;
		}else if(down && Word.isFree(this.getX(),(int)(y+spd))) {
			moved = true;
			 y+=spd;
		}
		
		if(moved == true) {
			frames++;
			if(frames==maxframes) {
				frames=0;
				index++;
				if(index>maxindex) {
					index=0;
				}
			}
		}
		
		this.checkCollisionVida();
		this.checkColisionCarga();
		
		Camera.x = Camera.clamp(this.x - (Game.WIDTH/2),0, Word.WIDTH * 16 -Game.WIDTH);
		Camera.y = Camera.clamp(this.y - (Game.HEIGTH/2),0, Word.HEIGTH * 16-Game.HEIGTH);
		
	}
	
	public void checkColisionCarga() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Carga) {
				if(Entity.isColliding(this, e)) {
					carga+=2;
					Game.entities.remove(i);

				}
			}
		}
	}
	
	
	public void checkCollisionVida() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Vida) {
				if(Entity.isColliding(this, e)) {
					vida+=80;
					if(vida >= 200) {
						vida = 200;
					}
					Game.entities.remove(i);
					return;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		if(dir == direito_dir){
		g.drawImage(DireitoP[index], this.getX()+4 - Camera.x,this.getY() - Camera.y, null);
		}else if(dir == esquerdo_dir){
			g.drawImage(EsquerdoP[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
			
	}



	}
}
