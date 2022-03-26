package Abelardo.word;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Word {
	
	
	
	public Word(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			for(int i = 0; i < pixels.length;i++) {
				if(pixels[i]== 0xFFFF0000) {
					System.out.println("vermei");
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	

}
