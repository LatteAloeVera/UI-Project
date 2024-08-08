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

public class LoginUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;

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
		setBounds(100, 100, 529, 285);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(10, 11, 492, 224);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JRadioButton studentRadioButton = new JRadioButton("Student");
		studentRadioButton.setBackground(SystemColor.inactiveCaptionBorder);
		studentRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		studentRadioButton.setBounds(108, 125, 109, 23);
		panel.add(studentRadioButton);
		
		JRadioButton teacherRadioButton = new JRadioButton("Teacher");
		teacherRadioButton.setBackground(SystemColor.inactiveCaptionBorder);
		teacherRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		teacherRadioButton.setBounds(269, 125, 109, 23);
		panel.add(teacherRadioButton);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(teacherRadioButton);
		buttonGroup.add(studentRadioButton);
		
		JLabel usernameLabel = new JLabel("Username :");
		usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usernameLabel.setBounds(74, 11, 126, 44);
		panel.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password :");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordLabel.setBounds(74, 67, 126, 44);
		panel.add(passwordLabel);
		
		
		usernameField = new JTextField();
		usernameField.setBounds(210, 24, 193, 27);
		panel.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(210, 80, 193, 27);
		panel.add(passwordField);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBackground(SystemColor.inactiveCaption);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//login pressed
				HashMap<String, User> userMap = FuncManager.readUserFile();
				String usrName = usernameField.getText();
				String usrPass = passwordField.getText();	
				
				if(getSelectedButtonText(buttonGroup) == null){
					//didn't selected student or teacher
					FuncManager.errorBox("Please select teacher or student!","Warning!");
				}
				else {
					if(usernameField.getText().trim().compareTo("") == 0 || passwordField.getText().trim().compareTo("") == 0) {
						//empty fields
						FuncManager.errorBox("Please fill in all the empty areas!","Empty Area Remaining!");
					}
					else {
						if(userMap.containsKey(usrName)) {
							if(userMap.get(usrName).compareTo(new User(usrName,usrPass,getSelectedButtonText(buttonGroup),null)) == 0) {
								//login successful!
								FuncManager.infoBox("Login successful, returning to main menu...", "Successful!");
								dispose();
								
								if(userMap.get(usrName).getTag().compareTo("Teacher") == 0) {
									new AdminMenuUI().setVisible(true);
								}
								else{
									new StudentMenuUI().setVisible(true);
								}
							}
							else if(userMap.get(usrName).compareTo(new User(usrName,usrPass,getSelectedButtonText(buttonGroup),null)) == 2) {
								//wrong tag!
								FuncManager.errorBox("There's not a user with this username, please try again!", "Warning!");
							}
							else {
								//wrong password!
								FuncManager.errorBox("Password is wrong, please try again!", "Warning!");
							}
						}
						else {
							//wrong username!
							FuncManager.errorBox("There's not a user with this username, please try again!", "Warning!");
						}
					}
				}
				
			}
		});
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		loginButton.setBounds(108, 176, 112, 37);
		panel.add(loginButton);
		
		


		
		JButton registerButton = new JButton("Register");
		registerButton.setBackground(SystemColor.inactiveCaption);
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//register pressed
				if(getSelectedButtonText(buttonGroup) == null){
					//didn't selected student or teacher
					FuncManager.errorBox("Please select teacher or student!","Warning!");
				}
				else {
					if(usernameField.getText().trim().compareTo("") == 0 || passwordField.getText().trim().compareTo("") == 0) {
						//empty fields
						FuncManager.errorBox("Please fill in all the empty areas!","Empty Area Remaining!");
					}
					else {
						HashMap<String, User> userMap = FuncManager.readUserFile();
						
						if(userMap.containsKey(usernameField.getText())) {
							//user already exists!
							FuncManager.errorBox("User already exists! Please use login.", "Warning!");
						}
						else {
							//create user!
							FuncManager.addNewUserToFile(usernameField.getText(), passwordField.getText(), getSelectedButtonText(buttonGroup));
							FuncManager.infoBox("Register successful, please login with your username.","Successful!");
						}
						
					}
				}
				passwordField.setText("");
			}
			
		});
		
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		registerButton.setBounds(266, 175, 112, 38);
		panel.add(registerButton);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{usernameField, passwordField, studentRadioButton, teacherRadioButton, loginButton, registerButton}));
		
		studentRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Student radio button pressed
				registerButton.setEnabled(false);
			}
		});
		
		teacherRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Teacher radio button pressed
				registerButton.setEnabled(true);
			}
		});

		
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
