import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.Window.Type;
import java.awt.SystemColor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Color;

public class LoginUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernamePartialIDField;
	private JPasswordField passwordField;
	protected static String sessionUserName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					LoginUI frame = new LoginUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginUI() {
		setFont(new Font("Dialog", Font.BOLD, 20));
		setIconImage(Toolkit.getDefaultToolkit().getImage("content/loginIco.png"));
		setForeground(SystemColor.inactiveCaption);
		setBackground(SystemColor.inactiveCaption);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(SystemColor.window);
		loginPanel.setBounds(0, 0, 639, 681);
		contentPane.add(loginPanel);
		loginPanel.setLayout(null);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		
		JLabel usernameLabel = new JLabel("Username ID :");
		usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usernameLabel.setBounds(129, 244, 151, 44);
		loginPanel.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password :");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordLabel.setBounds(154, 300, 126, 44);
		loginPanel.add(passwordLabel);
		
		
		usernamePartialIDField = new JTextField();
		usernamePartialIDField.setBounds(290, 257, 193, 27);
		loginPanel.add(usernamePartialIDField);
		usernamePartialIDField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(290, 313, 193, 27);
		loginPanel.add(passwordField);
		
		JButton loginButton = new JButton("Login");
		loginButton.setEnabled(false);
		loginButton.setBackground(SystemColor.inactiveCaption);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Login button pressed
				HashMap<String, User> userMap = FuncManager.readUserFile();
				String usrID = usernamePartialIDField.getText();
				String usrPass = passwordField.getText();	
				
				if(getSelectedButtonText(buttonGroup) == null){
					//Didn't selected student or teacher!
					FuncManager.errorBox("Please select teacher or student!","Warning!");
				}
				else {
					if(usrID.trim().compareTo("") == 0 || passwordField.getText().trim().compareTo("") == 0) {
						//Empty fields!
						FuncManager.errorBox("Please fill in all the empty areas!","Empty Area Remaining!");
					}
					else {
						//Checking id...
						if(FuncManager.doesIDExists(usrID)) {
							User usr =  FuncManager.getUserByID(usrID);;
							String usrName = usr.getName();
							
							//Cheking password and Tag...
							if(userMap.get(usrName).compareTo(new User(usrName,usrPass,getSelectedButtonText(buttonGroup),usr.getID())) == 0) {
								//Login successful!
								FuncManager.infoBox("Login successful, returning to main menu...", "Successful!");
								dispose();
								
								sessionUserName = usrName;
								
								if(userMap.get(usrName).getTag().compareTo("Teacher") == 0) 
									new AdminMenuUI().setVisible(true);
								else
									new StudentMenuUI().setVisible(true);
								
							}
							//Checking if tag is wrong...
							else if(userMap.get(usrName).compareTo(new User(usrName,usrPass,getSelectedButtonText(buttonGroup),null)) == 2) 
								FuncManager.errorBox("There's not a user with this username, please try again!", "Warning!");
							//Password is wrong!
							else 
								FuncManager.errorBox("Password is wrong, please try again!", "Warning!");
						}
						//Username Wrong!
						else 
							FuncManager.errorBox("There's not a user with this username, please try again!", "Warning!");
					}
				}
				
			}
		});
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		loginButton.setBounds(249, 375, 112, 37);
		loginPanel.add(loginButton);
		

		
		JLabel backgroundLabel = new JLabel("New label");
		backgroundLabel.setBounds(0, 0, 639, 681);
		loginPanel.add(backgroundLabel);
		backgroundLabel.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\Test.jpg"));
		
		JPanel artPanel = new JPanel();
		artPanel.setBackground(new Color(252, 249, 255));
		artPanel.setBounds(638, 0, 626, 681);
		contentPane.add(artPanel);
		artPanel.setLayout(null);
		
		
		JLabel illustrationLabel = new JLabel("New label");
		illustrationLabel.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\loginArt.png"));
		illustrationLabel.setBounds(139, 181, 330, 281);
		artPanel.add(illustrationLabel);
		
		
		
	}
	
	
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
}
