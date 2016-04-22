package First;


import java.awt.Graphics2D;
import java.util.Random;
import java.awt.Rectangle;

public class Ball {
	private static final int DIAMETER = 30;
	
	int x = 0;
	int y = 0;
	int xa = 1;
	int ya = 1;
	private Game game;

	public Ball(Game game) {
		this.game = game;
	}

	void move() {
		Random rn = new Random();
		int answer = rn.nextInt(10)+1;
		
		boolean changeDirection = true;
		if (x + xa < 0)
			xa = game.speed;
		else if (x + xa > game.getWidth() - DIAMETER)
			xa = -game.speed;
		else if (y + ya < 0)
			ya = game.speed;
		else if (y + ya > game.getHeight() - DIAMETER)
			game.gameOver();
		else if (collision()){
			ya = -game.speed;
			
			y = game.racquet.getTopY() - DIAMETER;
			game.speed++;
			game.count++;
			answer = rn.nextInt(10)+1;
			if(answer>5){
				game.speed=8;
				
			}
			if(game.speed>8){
				game.speed=1;
				Racquet.WITH-=10;
			}
			
			if(Racquet.WITH==50){
				Racquet.WITH=300;
			}
		} else 
			changeDirection = false;
		
		
		x = x + xa;
		y = y + ya;
		
	}

	private boolean collision() {
		return game.racquet.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}