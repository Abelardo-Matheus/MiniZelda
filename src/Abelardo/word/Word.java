package Abelardo.word;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Word {
	
	private Tiles[] tiles;
	public static int WIDTH, HEIGHT; 
	
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
						if(pixelatual == 0xFF000000) {
							//chão
							tiles[xx + (yy*map.getWidth())] = new ChaoTile(xx*16,yy*16, Tiles.TILE_CHAO);
						}else if(pixelatual== 0xFFFFFFFF) {
							//parede
							tiles[xx + (yy*map.getWidth())] = new ChaoTile(xx*16,yy*16, Tiles.TILE_PAREDE);
						}else if(pixelatual== 0xFF0800FF) {
							//player
							tiles[xx + (yy*map.getWidth())] = new ChaoTile(xx*16,yy*16, Tiles.TILE_CHAO);
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
	
	public void render(Graphics g){
		for(int xx= 0; xx < WIDTH; xx++) {
			for(int yy= 0; yy < HEIGHT; yy++) {
				Tiles tile = tiles[xx + (yy * WIDTH)];
				tile.render(g);
			}
		}
		
		
	}
	
	
	

}
