import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
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
	
	private float alphaPressedDefault = 0.3f;										//How much will the button highlight itself on click (0f = none, 1f = fully white, 0.3f = default)

	private Color pressedShineColor = Color.WHITE;									//When pressed highlight the button with the given color (Color.WHITE = default)
	private Color styleGradientColor1 = new Color(255, 255, 255);
	private Color styleGradientColor2 = new Color(255, 255, 255);					//Sets the color of the curved style on top of the button 
	private boolean isStyle2Active = false;											//Activates style2 style.
	private boolean buttonClickEffect = true;
	
	
	private float alphaForHoveringLowest = 0.3f;									//Sets the lowest highlighting there will be on the button while no hovering.
	private float alphaForHoveringHighest = 0.8f;									//Sets the highest highlighting there will be on the button while no hovering.
	private float alphaForHoveringChangeSpeed = 0.1f;								//Speed of the change of highlighting when not/being hovered. More low it is, smoother the animation will be. (Fast=0.5f; Medium=0.1f; Slow=0.01f)
	private float alphaForHoveringCurrent;
	
	private Color color1 = Color.decode("#7eb4fa");		//Default color
	private Color color2 = Color.decode("#e4b7ee");		//Default color
	private final Timer timer;
	private final Timer timerPressed;
	private boolean mouseOver;
	
	private boolean pressed;
	private Point pressedLocation;
	private float pressedSize;
	private float sizeSpeed = 16f;													//Speed of the click area increase (16f = default)
	private float alphaPressed = alphaPressedDefault;	
	
	public Color getColor1() {
		return color1;
	}

	public void setColor1(Color color1) {
		this.color1 = color1;
	}

	public Color getColor2() {
		return color2;
	}

	public void setColor2(Color color2) {
		this.color2 = color2;
	}

	public float getSizeSpeed() {
		return sizeSpeed;
	}

	public void setSizeSpeed(float sizeSpeed) {
		this.sizeSpeed = sizeSpeed;
	}
	
	public void setAlphaPressedDefault(float alphaPressedDefault) {
		this.alphaPressedDefault = alphaPressedDefault;
	}
	
	public boolean isButtonClickEffect() {
		return buttonClickEffect;
	}

	public void setButtonClickEffect(boolean buttonClickEffect) {
		this.buttonClickEffect = buttonClickEffect;
	}
	
	public void setPressedShineColor(Color pressedShineColor) {
		this.pressedShineColor = pressedShineColor;
	}
	
	public void setStyleGradientColor1(Color styleGradientColor1) {
		this.styleGradientColor1 = styleGradientColor1;
	}

	public void setStyleGradientColor2(Color styleGradientColor2) {
		this.styleGradientColor2 = styleGradientColor2;
	}
	
	public void setStyle2Active(boolean isStyle2Active) {
		this.isStyle2Active = isStyle2Active;
	}
	
	public float getAlphaForHoveringLowest() {
		return alphaForHoveringLowest;
	}

	public void setAlphaForHoveringLowest(float alphaForHovering) {
		this.alphaForHoveringLowest = alphaForHovering;
	}

	public float getAlphaForHoveringHighest() {
		return alphaForHoveringHighest;
	}

	public void setAlphaForHoveringHighest(float alphaForHoveringHighest) {
		this.alphaForHoveringHighest = alphaForHoveringHighest;
	}

	public float getAlphaForHoveringChangeSpeed() {
		return alphaForHoveringChangeSpeed;
	}

	public void setAlphaForHoveringChangeSpeed(float alphaForHoveringChangeSpeed) {
		this.alphaForHoveringChangeSpeed = alphaForHoveringChangeSpeed;
	}

	public float getAlphaPressedDefault() {
		return alphaPressedDefault;
	}

	public Color getPressedShineColor() {
		return pressedShineColor;
	}

	public boolean isStyle2Active() {
		return isStyle2Active;
	}
	


	protected ButtonGradient(){
		//TODO: button needs to be updated after being created so the buttons dont get stuck on first frame.
		
		Color txtColor = new Color(252, 249, 255);		//default text color
		//Color txtColor = new Color(64, 64, 64);
		setContentAreaFilled(false);					//Makes the background of the button invisible
		setForeground(txtColor);						//Makes the text a specific color.
		setCursor(new Cursor(Cursor.HAND_CURSOR));		//On hover, makes the cursor hand cursor.
		setBorder(new EmptyBorder(10, 20, 10, 20));
		setFont(new Font("Geist Medium", Font.BOLD, 27));
		
		
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
				pressedSize = 0;
				alphaPressed = alphaPressedDefault;
				pressed = true;
				pressedLocation = me.getPoint();
				if(buttonClickEffect) {
					timerPressed.setDelay(0);
					timerPressed.start();
				}
			}
			
			
		});
		timer = new Timer(40,new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(mouseOver) {
					if(alphaForHoveringCurrent < alphaForHoveringHighest) {							
						alphaForHoveringCurrent += alphaForHoveringChangeSpeed;						
						repaint();
					}
					else {
						alphaForHoveringCurrent = alphaForHoveringHighest;							
						timer.stop();
						repaint();
					}
				}
				else {
					if(alphaForHoveringCurrent > alphaForHoveringLowest) {
						if(alphaForHoveringCurrent - alphaForHoveringChangeSpeed <= 0) {
							alphaForHoveringCurrent = alphaForHoveringLowest;
						}
						else {
							alphaForHoveringCurrent -= alphaForHoveringChangeSpeed;	
						}
						repaint();
					}
					else {
						alphaForHoveringCurrent = alphaForHoveringLowest;			
						timer.stop();
						repaint();
					}
				}
			}
		});
	
		timerPressed = new Timer(0, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				pressedSize += sizeSpeed;
				if(alphaPressed<=0) {
					pressed = false;
					timerPressed.stop();
				}
				else {
					repaint();
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
		
		if(pressed) {
			paintPressed(g2);
		}
		
		
		g2.dispose();
		grphcs.drawImage(img, 0, 0, null);
		
		super.paintComponent(grphcs); //To change body of generated methods, choose Tools -> Templates.
		
	}
	
	private void createStyle(Graphics2D g2) {
		//Adding shade:
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alphaForHoveringCurrent));
		int width = getWidth();
		int height = getHeight();
		

		
		Path2D.Float f = new Path2D.Float();
		if(isStyle2Active) {
			Color styleGradientPart1 = new Color(styleGradientColor1.getRed(), styleGradientColor1.getBlue(), styleGradientColor1.getGreen(), 30);
			Color styleGradientPart2 = new Color(styleGradientColor2.getRed(), styleGradientColor2.getBlue(), styleGradientColor2.getGreen(), 60);
			GradientPaint styleGradient = new GradientPaint(0, 0, styleGradientPart1, 0, height, styleGradientPart2); 
			g2.setPaint(styleGradient);
			f.moveTo(0, 0);
			f.lineTo(width, 0);
			f.lineTo(width, height);
			f.lineTo(0, height);
			f.lineTo(0, 0);
		
			g2.fill(f);
		}
		else {
			Color styleGradientPart1 = new Color(styleGradientColor1.getRed(), styleGradientColor1.getBlue(), styleGradientColor1.getGreen(), 60);
			Color styleGradientPart2 = new Color(styleGradientColor2.getRed(), styleGradientColor2.getBlue(), styleGradientColor2.getGreen(), 30);
			GradientPaint styleGradient = new GradientPaint(0, 0, styleGradientPart1, 0, height, styleGradientPart2); 
			g2.setPaint(styleGradient);
			f.moveTo(0, 0);
			f.curveTo(0, 0, width/2, height, width, 0);
			g2.fill(f);
		}
	}
	
	private void paintPressed(Graphics2D g2) {
		if (pressedLocation.x - (pressedSize / 2) < 0 && pressedLocation.x + (pressedSize / 2) > getWidth()) {
	        timerPressed.setDelay(20);
	        alphaPressed -= 0.05f;
	        if (alphaPressed < 0) {
	            alphaPressed = 0;
	        }
	    }

	    g2.setColor(pressedShineColor);
	    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alphaPressed));
	    float x = pressedLocation.x - (pressedSize / 2);
	    float y = pressedLocation.y - (pressedSize / 2);
	    g2.fillOval((int) x, (int) y, (int) pressedSize, (int) pressedSize);
	}
}
