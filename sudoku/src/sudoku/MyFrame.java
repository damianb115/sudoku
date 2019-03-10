package sudoku;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class MyFrame extends JFrame
{
	public MyFrame()
	{
		add(new MyPanel());
		pack();
		
		setResizable(false);
		setTitle("Sudoku");
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
