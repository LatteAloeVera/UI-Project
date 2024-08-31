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
import java.util.Random;
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
import javax.swing.DropMode;

public class LoginUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idField;
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
		setBounds(300, 175, 1280, 720);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel artPanel = new JPanel();
		artPanel.setBackground(new Color(252, 249, 255));
		artPanel.setBounds(769, 0, 495, 681);
		contentPane.add(artPanel);
		artPanel.setLayout(null);
		

		Random rand = new Random();
		int rand_int1 = rand.nextInt(10);
		String motivationMsg = "";
		
		switch(rand_int1) {
		case 0  -> motivationMsg = "          - \"The future belongs to those who believe in the beauty of their dreams.\"";
		case 1  -> motivationMsg = "          - \"It always seems impossible until its done.\"";
		case 2  -> motivationMsg = "          - \"No one is perfect. That's why pencils have erasers.\"";
		case 3  -> motivationMsg = "          - \"Today a reader, tomorrow a leader.\"";
		case 4  -> motivationMsg = "          - \"Develop a passion for learning. If you do, you will never cease to grow.\"";
		case 5  -> motivationMsg = "          - \"You learn something every day if you pay attention.\"";
		case 6  -> motivationMsg = "          - \"You have to expect things of yourself before you can do them.\"";
		case 7  -> motivationMsg = "          - \"The best way to predict your future is to create it.\"";
		case 8  -> motivationMsg = "          - \"Life is beatiful... Live every moment, be happy...\"";
		case 9  -> motivationMsg = "          - \"You got this, don't worry.\"";
		default -> motivationMsg = "          - \"What a beatiful day today!\"";
		}
		
		
		JLabel motivationLabel = new JLabel("<HTML> <p style=\"text-align:center\" color=\"#707070\"> " + motivationMsg + "</p> </HTML>");
		motivationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		motivationLabel.setFont(new Font("Geist Medium", Font.PLAIN, 16));
		motivationLabel.setForeground(new Color(0, 0, 0, 120));
		motivationLabel.setBounds(39, 478, 369, 100);
		artPanel.add(motivationLabel);
		
		
		JLabel illustrationLabel = new JLabel("");
		illustrationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		illustrationLabel.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\loginArt.png"));
		illustrationLabel.setBounds(0, 180, 495, 281);
		artPanel.add(illustrationLabel);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(SystemColor.window);
		loginPanel.setBounds(0, 0, 770, 681);
		contentPane.add(loginPanel);
		loginPanel.setLayout(null);
		
		JLabel usernameLabel = new JLabel("User ID :");
		usernameLabel.setForeground(new Color(93, 93, 93));
		usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		usernameLabel.setFont(new Font("Geist Medium", Font.PLAIN, 20));
		usernameLabel.setBounds(139, 247, 151, 44);
		loginPanel.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password :");
		passwordLabel.setForeground(new Color(93, 93, 93));
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setFont(new Font("Geist Medium", Font.PLAIN, 20));
		passwordLabel.setBounds(164, 302, 126, 44);
		loginPanel.add(passwordLabel);
		
		
		idField = new JTextField();
		idField.setFont(new Font("Geist", Font.PLAIN, 15));
		idField.setBackground(new Color(252, 249, 255));
		idField.setBounds(300, 258, 193, 27);
		loginPanel.add(idField);
		idField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Geist", Font.PLAIN, 15));
		passwordField.setBackground(new Color(252, 249, 255));
		passwordField.setEchoChar('*');
		passwordField.setBounds(300, 313, 193, 27);
		loginPanel.add(passwordField);
		
		ButtonGradient loginButton = new ButtonGradient();
		this.setFont(new Font("Geist Medium", Font.BOLD, 27));
		loginButton.setText("Login");
		loginButton.setForeground(Color.decode("#fcf9ff"));
		loginButton.setBackground(new Color(252, 249, 255));
		loginButton.setButtonClickEffect(true);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Login button pressed
				HashMap<String, User> userMap = FuncManager.readUserFile();
				String usrID = idField.getText();
				String usrPass = passwordField.getText();	
				
				if(usrID.trim().compareTo("") == 0 || passwordField.getText().trim().compareTo("") == 0) {
					//Empty fields!
					FuncManager.errorBox("Please fill in all the empty areas!","Empty Area Remaining!");
				}
				else {
					//Checking id...
					if(FuncManager.doesIDExists(usrID)) {
						User usr =  FuncManager.getUserByID(usrID);;
						String usrName = usr.getName();
						
						//Cheking password
						if(userMap.get(usrName).compareTo(new User(usrName,usrPass,usr.getTag(),usr.getID())) == 0) {
							//Login successful!
							FuncManager.infoBox("Login successful, returning to main menu...", "Successful!");
							dispose();
							
							sessionUserName = usrName;
							if(userMap.get(usrName).getTag().compareTo("Admin") == 0)
								new AdminMenuUI().setVisible(true);
							else if(userMap.get(usrName).getTag().compareTo("Teacher") == 0) 
								new TeacherMenuUI().setVisible(true);
							else
								new StudentMenuUI().setVisible(true);
							
						}
						//Password is wrong!
						else 
							FuncManager.errorBox("Password is wrong, please try again!", "Warning!");
					}
					//Username Wrong!
					else 
						FuncManager.errorBox("There's not a user with this username, please try again!", "Warning!");
				}
				
			}
		});
		loginButton.setBounds(254, 390, 161, 57);
		loginPanel.add(loginButton);
		
		JLabel loginLabel = new JLabel("Welcome!");
		loginLabel.setBackground(new Color(0, 0, 64));
		loginLabel.setForeground(Color.DARK_GRAY);
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setFont(new Font("Geist Medium", Font.BOLD, 54));
		loginLabel.setBounds(0, 124, 656, 71);
		loginPanel.add(loginLabel);
		
	
		JLabel backgroundLabel = new JLabel("New label");
		backgroundLabel.setBackground(new Color(128, 128, 192));
		backgroundLabel.setHorizontalAlignment(SwingConstants.LEFT);
		backgroundLabel.setBounds(0, 0, 770, 681);
		loginPanel.add(backgroundLabel);
		backgroundLabel.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\lgnBackground4.png"));
		

		
		
		
	}
}
