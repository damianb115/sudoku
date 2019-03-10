package sudoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Tile extends Rectangle
{	
	private int value;
	private boolean isMarked;
	
	public Tile(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		Random random = new Random();
		value = random.nextInt(10);
		isMarked = false;
	}
	
	public int getValue()
	{
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isMarked() {
		return isMarked;
	}

	public void setMarked(boolean isMarked) {
		this.isMarked = isMarked;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.WHITE);
		if(isMarked) g.setColor(Color.lightGray);
		g.fillRect(x, y, width, height);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier new", Font.BOLD, 40));
		g.drawString(""+value, x + 14, y + 38);
	}
}
