package Abelardo.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Game.Game;


public class Player extends Entity{
	
	public boolean right ,up,down , left ;
	public int direito_dir = 0,esquerdo_dir = 1;
	public int dir = direito_dir;
	public int spd = 2;
	
	private int frames = 0, maxframes = 10, index = 0, maxindex = 3;
	private boolean moved = false;
	private BufferedImage[] DireitoP;
	private BufferedImage[] EsquerdoP;
	
	
	
	public Player(int x, int y,int width, int heigth, BufferedImage sprite) {
		super(x ,y, width, heigth,sprite);
		
		DireitoP = new BufferedImage[4];
		EsquerdoP = new BufferedImage[4];
		
		DireitoP[0] = Game.spritesheet.getSprite(33, 0, 14, 16);
		DireitoP[1] = Game.spritesheet.getSprite(49, 0, 14, 16);
		DireitoP[2] = Game.spritesheet.getSprite(65, 0, 14, 16);
		DireitoP[3] = Game.spritesheet.getSprite(81, 0, 14, 16);
		
		EsquerdoP[3] = Game.spritesheet.getSprite(81, 16, 14, 16);
		EsquerdoP[2] = Game.spritesheet.getSprite(65, 16, 14, 16);
		EsquerdoP[1] = Game.spritesheet.getSprite(49, 16, 14, 16);
		EsquerdoP[0] = Game.spritesheet.getSprite(33, 16, 14, 16);
		
	}
	
	
	
	
	public void tick() {
		moved = false;
		if(right==true) {
			moved = true;
			dir = direito_dir;
			x+=spd;
			
		}else if(left==true) {
			moved = true;
			dir = esquerdo_dir;
			 x-=spd;
		}
		
		if(up==true) {
			moved = true;
			y-=spd;
		}else if(down==true) {
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
		
	}
	
	public void render(Graphics g) {
		if(dir == direito_dir){
		g.drawImage(DireitoP[index], this.getX(),this.getY(), null);
		}else if(dir == esquerdo_dir){
			g.drawImage(EsquerdoP[index], this.getX(),this.getY(), null);
			}else {
				
			}
	}
	
	
	
}
