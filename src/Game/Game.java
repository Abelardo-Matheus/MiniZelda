package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import Abelardo.Graficos.Spritesheet;
import Abelardo.Graficos.UI;
import Abelardo.entities.Entity;
import Abelardo.entities.Inimigo;
import Abelardo.entities.Player;
import Abelardo.entities.TiroFogo;
import Abelardo.word.Camera;
import Abelardo.word.Word;



public class Game extends Canvas implements Runnable, KeyListener, MouseListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	public final static int WIDTH = 240;
	public final static  int HEIGTH = 160;
	public final static int SCALE = 3;
	public static Player player;

	private Graphics g;
	
	private int cur_level = 1,Maxlevel = 3;
	private int cur_fade = 0,Maxfade = 255;
	private int cur_fade2 = 0,Maxfade2 = 100;
	

	public static Spritesheet spritesheet;
	public static Word word;
	
	private BufferedImage image;
	public static List<Entity> entities;
	public static List<Inimigo> inimigos;
	public static List<TiroFogo> balas;
	
	public static Random rand;
	
	public Menu menu;
	
	public static String gamestate = "MENU";
	private boolean MensagemReiniciar = true;
	private int framesGameOver = 0;
	
	private boolean restarGame = false;
	
	public UI ui;
	
	public Game(){
		rand = new Random();
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGTH*SCALE));
		initFrame();
		
		ui = new UI();
		image = new BufferedImage(WIDTH, HEIGTH, BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		inimigos = new ArrayList<Inimigo>();
		balas = new ArrayList<TiroFogo>();
		spritesheet = new Spritesheet("/spritesheet.png");
		player = new Player(0,0,16,16,spritesheet.getSprite(32, 0, 12, 16));
		entities.add(player);
		word = new Word("/level1.png");
		 
		menu = new Menu();
		}
	
	public void initFrame() {
		frame = new JFrame("JOGUIN");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
	}
	public void tick(){
		
		
		
		
		if(gamestate == "NORMAL") {
			this.restarGame=false;
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if (e instanceof Player) {
				// Ticks do Player
			}
			e.tick(); 
		}
		
		for(int i = 0;i < balas.size();i++) {
			balas.get(i).tick();
		}
		
		if(inimigos.size() == 0) {
			//Proximo lvl
			cur_level++;
			if(cur_level > Maxlevel) {
				cur_level=1;
			}
			String newWord = "level"+cur_level+".png";
			//System.out.println(""+newWord);
			Word.RestartGame(newWord);
		}
		}else if(gamestate == "MENU") {
			menu.tick();
			
		}
		
		else if(gamestate == "GAMEOVER") {
			this.framesGameOver++;
			cur_fade+=3;
			if(cur_fade > Maxfade) {
				cur_fade=255;
			}
			cur_fade2+=3;
			if(cur_fade2 > Maxfade2) {
				cur_fade2=100;
			}if(this.framesGameOver == 28) {
				this.framesGameOver=0;
				if(this.MensagemReiniciar) {
				this.MensagemReiniciar = false;
			}else {
				this.MensagemReiniciar = true;
			}
			if(restarGame == true) {
				this.restarGame=false;
				this.gamestate="NORMAL";
				cur_level = 1;
				String newWord = "level"+cur_level+".png";
				Word.RestartGame(newWord);
				
				
			}
				
				
			}
			
		}
		
		
		
		
	}
	public synchronized void stop(){
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}

	public synchronized void start(){
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
		
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
	
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		
		g = image.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH*SCALE, HEIGTH*SCALE);
		
		
		word.render(g);
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
			
		}
		for(int i = 0;i < balas.size();i++) {
			balas.get(i).render(g);
		}
		ui.render(g);
		
		g.dispose();// Limpar dados de imagem n�o usados
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGTH * SCALE, null);
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.setColor(Color.white);
		g.drawString("Cargas", 18,468);
		if(gamestate== "GAMEOVER") {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(0,0,0,cur_fade2));
			g2.fillRect(0, 180, WIDTH*SCALE, (HEIGTH)/2);
			g2.setFont(new Font("Arial", Font.BOLD, 50));
			g2.setColor(new Color(255,0,0,cur_fade));
			g2.drawString("VOCÊ MORREU", (WIDTH+100)/2,(HEIGTH*SCALE)/2);
			
			if(MensagemReiniciar) {
			g2.setFont(new Font("Arial", Font.BOLD, 20));
			g2.setColor(new Color(255,255, 255,cur_fade));
			g2.drawString("<ENTER PARA REINICIAR>", WIDTH,(HEIGTH)+150);
			g2.setFont(new Font("Arial", Font.BOLD, 15));
			g2.setColor(new Color(255,255, 255,cur_fade));
			g2.drawString("<M PARA MENU>", WIDTH+70,(HEIGTH)+170);
			}
			}if(gamestate == "MENU") {
				menu.render(g);
			
			}
		
		bs.show();


		
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0; 
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while(isRunning = true) {
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns; 
			lastTime = now;
			if(delta>=1) {
				tick();
				render();
				frames++;
				delta--;
			}
				if(System.currentTimeMillis()-timer >= 1000) {
					//System.out.println("Fps:"+frames);
					frames = 0;
					timer += 1000;
				}
			}
		stop();
		}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D ) {
			player.right=true;
			
			//System.out.println("direita");
		}else if(e.getKeyCode()== KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left=true;
	
			//System.out.println("esquerda");
		}
		if(e.getKeyCode()== KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.up=true;
			//.out.println("cima");
			if(gamestate == "MENU") {
				menu.up = true;
			}
		}
		else if(e.getKeyCode()== KeyEvent.VK_DOWN|| e.getKeyCode() == KeyEvent.VK_S ) {
			player.down=true;
			//System.out.println("baixo");
			if(gamestate == "MENU") {
				menu.down = true;
		}
		}
		
		
		if(e.getKeyCode()== KeyEvent.VK_X || e.getKeyCode() == KeyEvent.VK_1){
			player.atirando = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_M) {
			cur_level = 1;
			String newWord = "level"+cur_level+".png";
			Word.RestartGame(newWord);
			Game.gamestate = "MENU";
			
			
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.restarGame = true;
			if(gamestate == "MENU") {
				menu.enter = true;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			Game.gamestate = "MENU";
			Menu.pause = true;
		}
		
	}


	

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D ) {
			player.right=false;
		}else if(e.getKeyCode()== KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left=false;
		}
		if(e.getKeyCode()== KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.up=false;
		}else if(e.getKeyCode()== KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down=false;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Player.mouseShoot = true;
		player.mx = (e.getX() /3);
		player.my = (e.getY() /3);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	
		
	}
	
	
	
	

