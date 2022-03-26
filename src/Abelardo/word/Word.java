package Abelardo.word;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Abelardo.entities.Anel;
import Abelardo.entities.Carga;
import Abelardo.entities.Entity;
import Abelardo.entities.Inimigo;
import Game.Game;

public class Word {
	
	public static Tiles[] tiles;
	public static int WIDTH, HEIGHT; 
	public static final int TILE_SIZE = 16;
	
	public Word(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tiles[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
				for(int xx = 0 ;xx < map.getWidth();xx++) {
					for(int yy = 0;yy < map.getHeight();yy++) {
						int pixelatual = pixels[xx+(yy*map.getWidth())];
						tiles[xx + (yy*map.getWidth())] = new ChaoTile(xx*16,yy*16, Tiles.TILE_CHAO);
						if(pixelatual == 0xFF000000) {
							//chão
							tiles[xx + (yy*map.getWidth())] = new ChaoTile(xx*16,yy*16, Tiles.TILE_CHAO);
						}else if(pixelatual== 0xFF979E9C) {
							//parede Esquerda
							tiles[xx + (yy*map.getWidth())] = new ParedeTile(xx*16,yy*16, Tiles.TILE_PAREDEE);
						}else if(pixelatual== 0xFFFFFFFF) {
							//parede LAVA
							tiles[xx + (yy*map.getWidth())] = new ParedeTile(xx*16,yy*16, Tiles.TILE_PAREDEL);
						}else if(pixelatual== 0xFF9B9988) {
							//parede CIMA
							tiles[xx + (yy*map.getWidth())] = new ParedeTile(xx*16,yy*16, Tiles.TILE_PAREDEC);
						}else if(pixelatual== 0xFF998D6E) {
							//parede BAIXO
							tiles[xx + (yy*map.getWidth())] = new ParedeTile(xx*16,yy*16, Tiles.TILE_PAREDEB);
						}else if(pixelatual== 0xFF00083D) {
							//parede CANTO
							tiles[xx + (yy*map.getWidth())] = new ParedeTile(xx*16,yy*16, Tiles.TILE_PAREDECANTO);
						}else if(pixelatual== 0xFF3B3F3F) {
							//parede direita
							tiles[xx + (yy*map.getWidth())] = new ParedeTile(xx*16,yy*16, Tiles.TILE_PAREDED);
						}else if(pixelatual== 0xFFFF0AFF) {
							//player
							Game.player.setX(xx*16);
							Game.player.setY(yy*16);
						}else if(pixelatual== 0xFFFF0AFF) {
							//anel
							Game.entities.add(new Anel(xx*16, yy*16, 16, 16,Entity.ANEL_EN));
						}else if(pixelatual== 0xFFFF0000) {
							//inimigo
							Game.entities.add(new Inimigo(xx*16, yy*16, 16, 16,Entity.INIMIGO_EN));
						}else if(pixelatual== 0xFF3AFFEB) {
							//carga
							Game.entities.add(new Carga(xx*16, yy*16, 16, 16,Entity.CARGAS_EN ));
						}else {
							//chão
							tiles[xx + (yy*map.getWidth())] = new ChaoTile(xx*16,yy*16, Tiles.TILE_CHAO);
						}
						
					}
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean isFree(int xNext, int yNext) {
		int x1 = xNext / TILE_SIZE;
		int y1 = yNext /TILE_SIZE;
		
		int x2 = (xNext+TILE_SIZE-1)/ TILE_SIZE;
		int y2 = yNext /TILE_SIZE;
		
		int x3 = xNext /TILE_SIZE;
		int y3 = (yNext+TILE_SIZE-1) / TILE_SIZE;
		
		int x4 = (xNext+TILE_SIZE-1) / TILE_SIZE;
		int y4 = (yNext+TILE_SIZE-1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1 * Word.WIDTH)] instanceof ParedeTile) ||
				(tiles[x2 + (y2 * Word.WIDTH)] instanceof ParedeTile)||
				(tiles[x3 + (y3 * Word.WIDTH)] instanceof ParedeTile)||
				(tiles[x4 + (y4 * Word.WIDTH)] instanceof ParedeTile));
		
	}
	
	public void render(Graphics g){
		
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT) 
					continue;
				Tiles tile = tiles[xx + (yy * WIDTH)];
				tile.render(g);
				
			}
		}
		
		
	}
	
	
	

}
