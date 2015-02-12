package sorting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Gui extends JComponent{

	private Double[] values;
	private Double first, second;
	
	public synchronized void paintComponent(Graphics g)
	{
		if(values == null)
		{
			System.out.println("Array is null...");
			return;
		}
		else
		{
			Graphics2D g2 = (Graphics2D) g;
			int width = getWidth() / values.length;
			for (int i = 0; i < values.length; i++) {
				Double temp = values[i];
				Rectangle2D rect = new Rectangle2D.Double(width * i, getHeight()-temp , width, temp);
				if(temp == first || temp == second)
				{
					g2.setColor(Color.yellow);
					
				}
				else
				{
					g2.setColor(Color.green);
				}
				g2.fill(rect);
			}
		}
	}

	public synchronized void setValues(Double[] values, Double o1, Double o2) {
		
		this.values = (Double[])values.clone();
		this.first = o1;
		this.second = o2;
		repaint();
	}
	
}
