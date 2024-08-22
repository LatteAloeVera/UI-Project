import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class ButtonGradient extends JButton {
	//private Color color1 = Color.decode("#7F7FD5");
	//private Color color2 = Color.decode("#f64f59");
	
	private Color color1 = Color.decode("#7eb4fa");
	private Color color2 = Color.decode("#e4b7ee");
	private final Timer timer;
	//private final Timer timerPressed;
	private float alpha = 0.3f;
	private boolean mouseOver;
	//private boolean pressed;
	//private boolean pressedLocation;

	protected ButtonGradient(){
		Color txtColor = new Color(252, 249, 255);
		//Color txtColor = new Color(64, 64, 64);
		setContentAreaFilled(false);					//Makes the background of the button invisible
		setForeground(txtColor);						//Makes the text a specific color.
		setCursor(new Cursor(Cursor.HAND_CURSOR));		//On hover, makes the cursor hand cursor.
		setBorder(new EmptyBorder(10, 20, 10, 20));
		
		addMouseListener(new MouseAdapter() {			//To make actions whenever mouse hovers etc.
			@Override
			public void mouseEntered(MouseEvent me) {
				mouseOver = true;
				timer.start();
			}
			
			@Override
			public void mouseExited(MouseEvent me) {
				mouseOver = false;
				timer.start();

			}
			
			@Override
			public void mousePressed(MouseEvent me) {
				
			}
			
			
		});
		timer = new Timer(40,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(mouseOver) {
					if(alpha<0.8f) {			//if this part is higher, the higher lightning will be.
						alpha += 0.1f;			//if these alpha increaser is lower, smoother the animation will be. Default => Fast=0.5f; Medium=0.1f; Slow=0.01f;
						repaint();
					}
					else {
						alpha = 0.8f;			//Finalizes the alpha. Higher means more lighy.
						timer.stop();
						repaint();
					}
				}
				else {
					if(alpha > 0.3f) {
						alpha -= 0.1f;			//if these alpha increaser is lower, smoother the animation will be.
						repaint();
					}
					else {
						alpha = 0.3f;			//if this part is lower, the lower lightning will be.
						timer.stop();
						repaint();
					}
				}
			}
		});
		
		
	}
	
	@Override
	protected void paintComponent(Graphics grphcs){
		int width = getWidth();
		int height = getHeight();
		
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = img.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//g2.setColor(Color.yellow); This place is where will we be doing gradient.
		GradientPaint gra = new GradientPaint(0, 0, color1, width, 0, color2);
		g2.setPaint(gra);
		
		//Filling with the paint method, by using the given color:
		g2.fillRoundRect(0, 0, width, height, height, height);
		
		//Adding style:
		createStyle(g2);
		
		
		grphcs.drawImage(img, 0, 0, null);
		
		super.paintComponent(grphcs); //To change body of generated methods, choose Tools -> Templates.
		
	}
	
	private void createStyle(Graphics2D g2) {
		//Adding shade:
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
		int width = getWidth();
		int height = getHeight();
		GradientPaint gradientWhite = new GradientPaint(0, 0, new Color(255, 255, 255, 60), 0, height, new Color(255, 255, 255, 30)); 
		g2.setPaint(gradientWhite);
		Path2D.Float f = new Path2D.Float();
		f.moveTo(0, 0);
		int controll = height + (height/2);
		f.curveTo(0, 0, width/2, height, width, 0);
		g2.fill(f);
	}
}
