package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import Abelardo.Graficos.Spritesheet;
import Abelardo.entities.Entity;
import Abelardo.entities.Player;
import Abelardo.word.Word;



public class Game extends Canvas implements Runnable, KeyListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	private final int WIDTH = 240;
	private final int HEIGHT = 160;
	private final int SCALE = 3;
	public static Player player;

	private Graphics g;
	

	public static Spritesheet spritesheet;
	public static Word word;
	
	private BufferedImage image;
	public static List<Entity> entities;
	
	public Game(){

		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		spritesheet = new Spritesheet("/spritesheet.png");
		player = new Player(0,0,16,16,spritesheet.getSprite(32, 0, 16, 16));
		entities.add(player);
		word = new Word("/map.png");
		 
		
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
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if (e instanceof Player) {
				// Ticks do Player
			}
			e.tick();
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
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		
		
		word.render(g);
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		
		g.dispose();// Limpar dados de imagem n�o usados
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		bs.show();


		
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0; 
		int frames = 0;
		double timer = System.currentTimeMillis();
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
					System.out.println("Fps:"+frames);
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
		if(e.getKeyCode()== KeyEvent.VK_RIGHT ) {
			player.right=true;
			//System.out.println("direita");
		}else if(e.getKeyCode()== KeyEvent.VK_LEFT ) {
			player.left=true;
			//System.out.println("esquerda");
		}
		if(e.getKeyCode()== KeyEvent.VK_UP ) {
			player.up=true;
			//.out.println("cima");
		}else if(e.getKeyCode()== KeyEvent.VK_DOWN ) {
			player.down=true;
			//System.out.println("baixo");
		}
		
	}


	

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_RIGHT ) {
			player.right=false;
		}else if(e.getKeyCode()== KeyEvent.VK_LEFT ) {
			player.left=false;
		}
		if(e.getKeyCode()== KeyEvent.VK_UP ) {
			player.up=false;
		}else if(e.getKeyCode()== KeyEvent.VK_DOWN ) {
			player.down=false;
		}
		
	}


	
		
	}
	
	
	
	

