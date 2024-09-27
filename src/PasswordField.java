import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;


public class PasswordField extends JPasswordField {

	private final Animator animator;
	private float location;
	private boolean show;
	private boolean mouseOver = false;
	private boolean animateHinText = true;
	private String hintLabelText = "Label";
	private Font hintLabelFont = new Font("Geist", Font.PLAIN, 19);
	private Color hintLabelColor = new Color(150, 150, 150);
	private Color lineColor = hintLabelColor;

	public Font getHintLabelFont() {
		return hintLabelFont;
	}

	public void setHintLabelFont(Font hintLabelFont) {
		this.hintLabelFont = hintLabelFont;
	}
	
	public String getHintLabelText() {
		return hintLabelText;
	}

	public void setHintLabelText(String hintLabelText) {
		this.hintLabelText = hintLabelText;
	}

	public Color getHintLabelColor() {
		return hintLabelColor;
	}

	public void setHintLabelColor(Color hintLabelColor) {
		this.hintLabelColor = hintLabelColor;
	}
	
	public String getLabelText() {
		return hintLabelText;
	}

	public void setLabelText(String labelText) {
		this.hintLabelText = labelText;
	}

	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	
	public PasswordField() {
			setBorder(new EmptyBorder(20, 3, 10, 3));
			setSelectionColor(new Color(176, 204, 255));			//The color when you select a part of the text
			setOpaque(false);										//Sets background invisible
			
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent me) {
					mouseOver = true;
					repaint();
				}
				@Override
				public void mouseExited(MouseEvent me) {
					mouseOver = false;
					repaint();
				}
			});
			
			addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent fe) {
					showing(false);
				}
				
				@Override
				public void focusLost(FocusEvent fe) {
					showing(true);
				}
			});
			
			TimingTarget target = new TimingTargetAdapter() {
				@Override
				public void begin() {
					super.begin();
					animateHinText = String.valueOf(getPassword()).equals("");
				}
				
				@Override
				public void timingEvent(float fraction) {
					location = fraction;
					repaint();
				}
			};
			
			animator = new Animator(200, target);		//Sets how many ticks will there be on 1 second
			animator.setResolution(10);					//Sets image/tick ratio (1 image = x tick)
			animator.setAcceleration(0.5f);
			animator.setDeceleration(0.5f);
	}
	
	private void showing (boolean action) {
		if (animator.isRunning()) {
			animator.stop();
		} else {
			location =1;
		}
		animator.setStartFraction(1f - location);
		show = action;
		location = 1f - location;
		animator.start();
	}
	
	@Override
	public void paint(Graphics grphcs) {
		super.paint(grphcs);
		Graphics2D g2 = (Graphics2D) grphcs;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		int width = getWidth();
		int height = getHeight();
		
		if(mouseOver) {
			g2.setColor(lineColor);						//Changes the line when mouseOver
		}
		else {
			g2.setColor(hintLabelColor);		//Changes the line when mouse leaves
		}
		
		
		g2.fillRect(2, height - 1, width - 4, 1);
		createHintText(g2);
		createLineStyle(g2);
		g2.dispose();
	}
	
	//This creates the animated hint text
	private void createHintText(Graphics2D g2) {
		Insets in = getInsets();
		g2.setColor(hintLabelColor);
		g2.setFont(hintLabelFont);
		FontMetrics ft = g2.getFontMetrics();
		Rectangle2D r2 = ft.getStringBounds(hintLabelText, g2);
		double height = getHeight() - in.top - in.bottom;
		double textY = (height - r2.getHeight()) / 2;
		double size;
		
		if(animateHinText) {
			if(show) {
				size = 21 * (1 - location);
			}
			else {
				size = 21 * location;
			}
		} else {
			size = 21;
		}
		
		g2.drawString(hintLabelText, in.right,(int) (in.top + textY + ft.getAscent() - size));
	}
	
	//Makes line thicker on click
	private void createLineStyle(Graphics2D g2) {			
		if(isFocusOwner()) {
			double width = getWidth();
			double height = getHeight();
			g2.setColor(lineColor);
			double size;
			
			if(show) {
				size = width * (1 - location);
			} else {
				size = width * location;
			}
			double x = (width - size)/2;
			g2.fillRect((int)(x + 2), (int) (height - 2), (int) size, 2);
		}
	}

	@Override 
	public void setText(String string){
			if(!String.valueOf(getPassword()).equals(string)) {
				showing(string.equals(string));
			}
			super.setText(string);
	}
	
	
}
