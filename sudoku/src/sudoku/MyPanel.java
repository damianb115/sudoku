package sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MyPanel extends JPanel implements MouseListener, KeyListener
{
	Board board;
	
	public MyPanel()
	{
		setPreferredSize(new Dimension(600,600));
		setBackground(Color.green);
		
		board = new Board();
		addMouseListener(this);
		addKeyListener(this);
		setFocusable(true);
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		board.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		board.markTileIfClickedInside(e.getX(), e.getY());
		
		board.printMarkedTile();
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) 
	{	
		if(e.getKeyCode() == KeyEvent.VK_LEFT) 
			board.moveLeftMarkedTile();
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
			board.moveRightMarkedTile();
		if(e.getKeyCode() == KeyEvent.VK_UP) 
			board.moveUpMarkedTile();
		if(e.getKeyCode() == KeyEvent.VK_DOWN) 
			board.moveDownMarkedTile();
		else	
			board.setValueOfMarkedTile(""+e.getKeyChar());
		
		board.printMarkedTile();
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}
