package sudoku;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Board 
{
	private  Tile  tile[][];
	private int markedTiles_i;
	private int markedTiles_j;
	private final int CORRECT_SUM_OF_VALUES = 45;
	
	private int startX = 58, startY = 55;
	private int gap = 5;
	private int tileWidth = 50, tileHeight = 50;
	private int width = 9*tileWidth+8*gap;
	private int height = 9*tileHeight+8*gap;
	
	public Board()
	{
		tile = new Tile[9][9];
		create();
		markTile(0,0);
	}
	
	public void create()
	{
		//this function create new Tiles and sets their coordinates
		
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				tile[i][j] = new Tile(startX + (tileWidth+gap)*j, startY + (tileHeight+gap)*i, tileWidth, tileHeight);
			}
		}
	}
	
	public void draw(Graphics g)
	{		
		//tiles drawing
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				tile[i][j].draw(g);							
			}
		}
		
		//lines drawing
		g.setColor(Color.BLACK);		
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{				
				((Graphics2D) g).setStroke(new BasicStroke(2));			
				g.drawLine(startX-3 + (tileWidth+gap)*j, startY, startX-3 + (tileWidth+gap)*j, startY + height);
				
				((Graphics2D) g).setStroke(new BasicStroke(5));			
				if(j>0 && j%3==0) g.drawLine(startX-2 + (tileWidth+gap)*j, startY, startX-2 + (tileWidth+gap)*j, startY + height);
			}
			
			((Graphics2D) g).setStroke(new BasicStroke(2));			
			g.drawLine(startX, startY-2 + (tileHeight+gap)*i, startX + width, startY-2 + (tileHeight+gap)*i);
			
			((Graphics2D) g).setStroke(new BasicStroke(5));	
			if(i>0 && i%3==0) g.drawLine(startX, startY-2 + (tileHeight+gap)*i, startX + width, startY-2 + (tileHeight+gap)*i);
		}
		
		//border drawing
		g.drawRect(startX, startY, width, height);
	}

	public void unmarkAllTiles()
	{
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{			
				tile[i][j].setMarked(false);
			}
		}
	}
	
	public void markTileIfClickedInside(int x, int y)
	{
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				if(tile[i][j].contains(new Point(x, y)))
				{
					//markedTiles_i = i;
					//markedTiles_j = j;
					markTile(i, j);
				}
			}
		}
	}

	public void markTile(int i, int j)
	{
		unmarkAllTiles();
		markedTiles_i = i;
		markedTiles_j = j;
		tile[markedTiles_i][markedTiles_j].setMarked(true);
	}
	
	public void setValueOfMarkedTile(String value)
	{
		try
		{
			int valueInt = Integer.parseInt(value);
			tile[markedTiles_i][markedTiles_j].setValue(valueInt);
		}
		catch(NumberFormatException ex) {}		
	}
	
	public void moveLeftMarkedTile()
	{
		if(markedTiles_j > 0) 
		{
			markTile(markedTiles_i, --markedTiles_j);
		}
	}
	
	public void moveRightMarkedTile()
	{
		if(markedTiles_j < 8) 
		{
			markTile(markedTiles_i, ++markedTiles_j);
		}
	}
	
	public void moveUpMarkedTile()
	{
		if(markedTiles_i > 0) 
		{
			markTile(--markedTiles_i, markedTiles_j);
		}
	}
	
	public void moveDownMarkedTile()
	{
		if(markedTiles_i < 8) 
		{
			markTile(++markedTiles_i, markedTiles_j);
		}
	}
	
	public void printMarkedTile()
	{
		System.out.println("[" + markedTiles_i + "; " + markedTiles_j + "]"); 
	}

	public boolean checkCorrectnesInRow(int row)
	{
		int sum = 0;
		for(int i = 0; i < 9; i++)
		{	
			sum += tile[row][i].getValue();
		}
		
		if(sum == CORRECT_SUM_OF_VALUES) return true;
		return false;
	}
	
	public boolean checkCorrectnesInColumn(int column)
	{
		int sum = 0;
		for(int i = 0; i < 9; i++)
		{	
			sum += tile[i][column].getValue();
		}
		
		if(sum == CORRECT_SUM_OF_VALUES) return true;
		return false;
	}

	public boolean checkCorrectnesInSection(int section)
	{
		int sum = 0;
		int iStart=0, jStart=0;
		
		if(section == 1) {iStart = 0; jStart = 0;}
		if(section == 2) {iStart = 0; jStart = 3;}
		if(section == 3) {iStart = 0; jStart = 6;}
		if(section == 4) {iStart = 3; jStart = 0;}
		if(section == 5) {iStart = 3; jStart = 3;}
		if(section == 6) {iStart = 3; jStart = 6;}
		if(section == 7) {iStart = 6; jStart = 0;}
		if(section == 8) {iStart = 6; jStart = 3;}
		if(section == 9) {iStart = 6; jStart = 6;}
		
		for(int i = iStart; i < iStart+3; i++)
		{	
			for(int j = jStart; j < jStart+3; j++)
			{	
				sum += tile[i][j].getValue();
			}
		}
		
		if(sum == CORRECT_SUM_OF_VALUES) return true;
		return false;
	}

	public boolean checkCorrectnessOfBoard()
	{
		for(int i = 0; i < 9; i++)
		{
			if(!checkCorrectnesInRow(i)) return false;
			if(!checkCorrectnesInColumn(i)) return false;
			if(!checkCorrectnesInSection(i)) return false;
		}
		
		return true;
	}
}
