package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {
	
	public String[] options = {"COMEÇAR","CARREGAR JOGO", "SAIR"};
	public int currentOption = 0, MaxOptions = options.length - 1;
	
	public boolean up, down, enter;
	
	public static boolean pause = false;
	
	
	public void render(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect(	0, 0, Game.WIDTH*Game.SCALE, Game.HEIGTH*Game.SCALE);
		g.setFont(new Font("Arial", Font.BOLD, 65));
		g.setColor(new Color(255,0,0, 255));
		g.drawString("DUNGEONS SOULS", (Game.WIDTH)/4,(Game.HEIGTH));
		
		
		if(pause == false) {
			g.setFont(new Font("Arial", Font.BOLD, 25));
			g.setColor(new Color(255, 255,255, 255));
		g.drawString("COMEÇAR", (Game.WIDTH)+45,(Game.HEIGTH)+100);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.setColor(new Color(255, 255,255, 255));
		g.drawString("CARREGAR JOGO", (Game.WIDTH),(Game.HEIGTH)+150);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.setColor(new Color(255, 255,255, 255));
		g.drawString("SAIR", (Game.WIDTH)+80,(Game.HEIGTH)+200);
		if(options[currentOption] == "COMEÇAR") {
			g.drawString(">", (Game.WIDTH),(Game.HEIGTH)+100);
		}else if(options[currentOption] == "CARREGAR JOGO") {
			g.drawString(">", (Game.WIDTH)-40,(Game.HEIGTH)+150);
		}else if(options[currentOption] == "SAIR") {
			g.drawString(">", (Game.WIDTH)+40,(Game.HEIGTH)+200);
		}
		}else {
			String conti = "CONTINUAR";
			String Menu = "MENU";
			options[0] = conti;
			options[1] = Menu;
			g.setFont(new Font("Arial", Font.BOLD, 25));
			g.setColor(new Color(255, 255,255, 255));
			g.drawString("CONTINUAR", (Game.WIDTH)+45,(Game.HEIGTH)+100);
			g.setFont(new Font("Arial", Font.BOLD, 25));
			g.setColor(new Color(255, 255,255, 255));
			g.drawString("MENU", (Game.WIDTH)+75,(Game.HEIGTH)+150);
			g.setFont(new Font("Arial", Font.BOLD, 25));
			g.setColor(new Color(255, 255,255, 255));
			g.drawString("SAIR", (Game.WIDTH)+80,(Game.HEIGTH)+200);
			if(options[currentOption] == "CONTINUAR") {
				g.drawString(">", (Game.WIDTH)+10,(Game.HEIGTH)+100);
			}else if(options[currentOption] == "MENU") {
				g.drawString(">", (Game.WIDTH)+40,(Game.HEIGTH)+150);
			}else if(options[currentOption] == "SAIR") {
				g.drawString(">", (Game.WIDTH)+40,(Game.HEIGTH)+200);
			}
			}
			
		}
		
		
	
	
	
	public void tick(){
		
		if(up) {
			up = false;
			currentOption--;
			if(currentOption < 0) {
				currentOption = MaxOptions;
			}
		}if(down) {
			down = false;
			currentOption++;
			if(currentOption > MaxOptions) {
				currentOption = MaxOptions;
			}
		}if(enter) {
			enter = false;
			if(options[currentOption] == "COMEÇAR" || options[currentOption] == "CONTINUAR") {
				Game.gamestate = "NORMAL";
				pause = false;
				
			}else if(options[currentOption] == "SAIR" ) {
				System.exit(1);
			}else if(options[currentOption] == "MENU" ) {
				Game.gamestate = "MENU";
			}
				
		}
		
	}

}
