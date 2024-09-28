import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class NewAdminMenuUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TextField studentNameField;
	private TextField studentPassField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewAdminMenuUI frame = new NewAdminMenuUI();
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
	public NewAdminMenuUI() {
		setBackground(new Color(93, 93, 93));
		setFont(new Font("Geist Medium", Font.BOLD, 20));
		setTitle("Admin Menu");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Ayberk Sevgi\\git\\UI-Project\\content\\adminPanelIco.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 175, 1280, 720);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(252, 249, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel uiPanel = new JPanel();
		uiPanel.setBackground(new Color(250, 255, 255));
		uiPanel.setBounds(260, 0, 1004, 681);
		contentPane.add(uiPanel);
		uiPanel.setLayout(null);
		
		JPanel studentMenuPanel = new JPanel();
		studentMenuPanel.setBackground(new Color(250, 255, 255));
		studentMenuPanel.setBounds(0, 0, 1004, 681);
		uiPanel.add(studentMenuPanel);
		studentMenuPanel.setLayout(null);
		studentMenuPanel.setVisible(false);
		
		studentNameField = new TextField();
		studentNameField.setHintLabelFont(new Font("Geist Medium", Font.PLAIN, 17));
		studentNameField.setForeground(new Color(90, 90, 90));
		studentNameField.setFont(new Font("Geist", Font.PLAIN, 17));
		studentNameField.setLineColor(new Color(154, 188, 252));
		studentNameField.setHintLabelColor(new Color(150, 150, 150));
		studentNameField.setHintLabelText("Student's Name\r\n");
		studentNameField.setBounds(140, 252, 220, 50);
		studentMenuPanel.add(studentNameField);
		studentNameField.setColumns(10);
		
		studentPassField = new TextField();
		studentPassField.setHintLabelFont(new Font("Geist Medium", Font.PLAIN, 17));
		studentPassField.setForeground(new Color(90, 90, 90));
		studentPassField.setFont(new Font("Geist", Font.PLAIN, 17));
		studentPassField.setLineColor(new Color(154, 188, 252));
		studentPassField.setHintLabelColor(new Color(150, 150, 150));
		studentPassField.setHintLabelText("Student's Password");
		studentPassField.setColumns(10);
		studentPassField.setBounds(140, 331, 220, 50);
		studentMenuPanel.add(studentPassField);
		
		JComboBox studentNamesComboBox = new JComboBox();
		studentNamesComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		studentNamesComboBox.setBounds(733, 317, 206, 27);
		studentMenuPanel.add(studentNamesComboBox);
		
		ButtonGradient addStudentButton = new ButtonGradient();
		addStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Student add button clicked:
				HashMap<String, User> userMap = FuncManager.readUserFile();
				String usrName = studentNameField.getText().trim();
				String usrPass = studentPassField.getText().trim();

				studentNameField.setText("");
				studentPassField.setText("");

				if (usrName.length() == 0 || usrPass.length() == 0) {
					FuncManager.errorBox("Please fill in all the empty areas!", "Empty Area Remaining!");
				} else if (userMap.containsKey(usrName)) {
					FuncManager.errorBox("There is an another user named \"" + usrName + "\" , Please enter an another username!",
							"Name Exists!");
				} else {
					try {
						FuncManager.addNewUserToFile(usrName, usrPass, "Student");
					} catch (UnsupportedTypeException e1) {
						e1.printStackTrace();
					}
					//TODO: After implementing other stuff, make sure to uncomment these!!!!
					
					//updateStudentComboBox(studentNamesComboBox);
					//updateEnrollingStudentList();
					FuncManager.infoBox("New student \"" + usrName + "\" added!", "Successful!");
				}

			}
		});
		addStudentButton.setSizeSpeed(18.0f);
		addStudentButton.setColor2(new Color(223, 188, 233));
		addStudentButton.setColor1(new Color(135, 181, 241));
		addStudentButton.setFont(new Font("Geist Medium", Font.BOLD, 23));
		addStudentButton.setText("Add!");
		addStudentButton.setBounds(177, 432, 160, 50);
		studentMenuPanel.add(addStudentButton);
		
		ButtonGradient removeStudentButton = new ButtonGradient();
		removeStudentButton.setText("Remove!");
		removeStudentButton.setSizeSpeed(18.0f);
		removeStudentButton.setFont(new Font("Geist Medium", Font.BOLD, 23));
		removeStudentButton.setColor2(new Color(223, 188, 233));
		removeStudentButton.setColor1(new Color(135, 181, 241));
		removeStudentButton.setBounds(660, 432, 160, 50);
		studentMenuPanel.add(removeStudentButton);
		
		JLabel lblNewLabel = new JLabel("= Add A New Student =                    = Remove A Student =");
		lblNewLabel.setForeground(new Color(120, 120, 120));
		lblNewLabel.setFont(new Font("Geist Medium", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 120, 972, 75);
		studentMenuPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Select Student Name :");
		lblNewLabel_2.setForeground(new Color(150, 150, 150));
		lblNewLabel_2.setFont(new Font("Geist Medium", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(506, 300, 217, 58);
		studentMenuPanel.add(lblNewLabel_2);
		
		JLabel adminUILeftMenuPanelBg_1 = new JLabel("New label");
		adminUILeftMenuPanelBg_1.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\AdminUIPanelBg2.png"));
		adminUILeftMenuPanelBg_1.setBackground(new Color(249, 249, 249));
		adminUILeftMenuPanelBg_1.setBounds(0, 0, 1004, 681);
		studentMenuPanel.add(adminUILeftMenuPanelBg_1);
		
		JPanel teacherMenuPanel = new JPanel();
		teacherMenuPanel.setBackground(new Color(250, 244, 255));
		teacherMenuPanel.setBounds(0, 0, 1004, 681);
		uiPanel.add(teacherMenuPanel);
		teacherMenuPanel.setLayout(null);
		teacherMenuPanel.setVisible(false);

		
		JLabel lblNewLabel_1 = new JLabel("Teacher");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(0, 0, 157, 44);
		teacherMenuPanel.add(lblNewLabel_1);
		
		JLabel adminUILeftMenuPanelBg_2 = new JLabel("New label");
		adminUILeftMenuPanelBg_2.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\AdminUIPanelBg2.png"));
		adminUILeftMenuPanelBg_2.setBackground(new Color(249, 249, 249));
		adminUILeftMenuPanelBg_2.setBounds(0, 0, 1004, 681);
		teacherMenuPanel.add(adminUILeftMenuPanelBg_2);
		
		JPanel lessonMenuPanel = new JPanel();
		lessonMenuPanel.setBackground(new Color(250, 244, 255));
		lessonMenuPanel.setBounds(0, 0, 1004, 681);
		uiPanel.add(lessonMenuPanel);
		lessonMenuPanel.setLayout(null);
		lessonMenuPanel.setVisible(false);

		
		JLabel lblNewLabel_1_1 = new JLabel("Lesson");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(0, 0, 157, 44);
		lessonMenuPanel.add(lblNewLabel_1_1);
		
		JLabel adminUILeftMenuPanelBg_3 = new JLabel("New label");
		adminUILeftMenuPanelBg_3.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\AdminUIPanelBg2.png"));
		adminUILeftMenuPanelBg_3.setBackground(new Color(249, 249, 249));
		adminUILeftMenuPanelBg_3.setBounds(0, 0, 1004, 681);
		lessonMenuPanel.add(adminUILeftMenuPanelBg_3);
		
		JPanel enrollMenuPanel = new JPanel();
		enrollMenuPanel.setBackground(new Color(250, 244, 255));
		enrollMenuPanel.setBounds(0, 0, 1004, 681);
		uiPanel.add(enrollMenuPanel);
		enrollMenuPanel.setLayout(null);
		enrollMenuPanel.setVisible(false);

		
		JLabel lblNewLabel_1_2 = new JLabel("Enroll");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(0, 0, 157, 44);
		enrollMenuPanel.add(lblNewLabel_1_2);
		
		JLabel adminUILeftMenuPanelBg_4 = new JLabel("New label");
		adminUILeftMenuPanelBg_4.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\AdminUIPanelBg2.png"));
		adminUILeftMenuPanelBg_4.setBackground(new Color(249, 249, 249));
		adminUILeftMenuPanelBg_4.setBounds(0, 0, 1004, 681);
		enrollMenuPanel.add(adminUILeftMenuPanelBg_4);
		
		JPanel mainMenuPanel = new JPanel();
		mainMenuPanel.setBackground(new Color(250, 244, 255));
		mainMenuPanel.setBounds(0, 0, 1004, 681);
		uiPanel.add(mainMenuPanel);
		mainMenuPanel.setLayout(null);
		
		JLabel welcomeLabel = new JLabel("Welcome To The Admin Menu");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setForeground(new Color(95, 90, 103));
		welcomeLabel.setFont(new Font("Geist Medium", Font.BOLD, 32));
		welcomeLabel.setBounds(10, 51, 972, 51);
		mainMenuPanel.add(welcomeLabel);
		
		JLabel adminUILeftMenuPanelBg = new JLabel("New label");
		adminUILeftMenuPanelBg.setBackground(new Color(249, 249, 249));
		adminUILeftMenuPanelBg.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\AdminUIPanelBg2.png"));
		adminUILeftMenuPanelBg.setBounds(0, 0, 1004, 681);
		mainMenuPanel.add(adminUILeftMenuPanelBg);
		
		JPanel navBarPanel = new JPanel();
		navBarPanel.setBackground(new Color(250, 255, 255));
		navBarPanel.setBounds(0, 0, 260, 681);
		contentPane.add(navBarPanel);
		navBarPanel.setLayout(null);
		
		ButtonGradient mainMenuButton = new ButtonGradient();
		mainMenuButton.setAlphaPressedDefault(0.1f);
		mainMenuButton.setAlphaForHoveringChangeSpeed(0.3f);
		mainMenuButton.setAlphaForHoveringLowest(0.0f);
		mainMenuButton.setStyle2Active(true);
		mainMenuButton.setStyleGradientColor2(new Color(180, 180, 180));
		mainMenuButton.setStyleGradientColor1(new Color(180, 180, 180));
		mainMenuButton.setPressedShineColor(new Color(192, 192, 192));
		mainMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		mainMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Clicked Main Menu Button:
				mainMenuPanel.setVisible(true);
				studentMenuPanel.setVisible(false);
				teacherMenuPanel.setVisible(false);
				lessonMenuPanel.setVisible(false);
				enrollMenuPanel.setVisible(false);
				
			}
		});
		
		mainMenuButton.setFont(new Font("Geist", Font.BOLD, 15));
		mainMenuButton.setForeground(new Color(120, 120, 120));
		mainMenuButton.setText("Main Menu");
		mainMenuButton.setColor2(new Color(252, 249, 255));
		mainMenuButton.setColor1(new Color(252, 249, 255));
		mainMenuButton.setBounds(75, 160, 150, 50);
		navBarPanel.add(mainMenuButton);
		
		JLabel mainMenuIconLabel = new JLabel("New label");
		mainMenuIconLabel.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\homeIcon.png"));
		mainMenuIconLabel.setBounds(45, 172, 25, 25);
		navBarPanel.add(mainMenuIconLabel);
		
		ButtonGradient studentManagementMenuButton = new ButtonGradient();
		studentManagementMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Clicked Student Menu Button
				mainMenuPanel.setVisible(false);
				studentMenuPanel.setVisible(true);
				teacherMenuPanel.setVisible(false);
				lessonMenuPanel.setVisible(false);
				enrollMenuPanel.setVisible(false);
			}
		});
		studentManagementMenuButton.setAlphaPressedDefault(0.1f);
		studentManagementMenuButton.setAlphaForHoveringChangeSpeed(0.3f);
		studentManagementMenuButton.setAlphaForHoveringLowest(0.0f);
		studentManagementMenuButton.setStyleGradientColor2(new Color(180, 180, 180));
		studentManagementMenuButton.setStyleGradientColor1(new Color(180, 180, 180));
		studentManagementMenuButton.setStyle2Active(true);
		studentManagementMenuButton.setPressedShineColor(new Color(192, 192, 192));
		studentManagementMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		studentManagementMenuButton.setColor2(new Color(252, 249, 255));
		studentManagementMenuButton.setColor1(new Color(252, 249, 255));
		studentManagementMenuButton.setForeground(new Color(120, 120, 120));
		studentManagementMenuButton.setText("Student Menu");
		studentManagementMenuButton.setFont(new Font("Geist", Font.BOLD, 15));
		
		studentManagementMenuButton.setBounds(75, 220, 150, 50);
		navBarPanel.add(studentManagementMenuButton);
		
		JLabel studentMenuIcon = new JLabel("New label");
		studentMenuIcon.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\studentIcon.png"));
		studentMenuIcon.setBounds(45, 232, 25, 25);
		navBarPanel.add(studentMenuIcon);
		
		ButtonGradient teacherManagementMenuButton = new ButtonGradient();
		teacherManagementMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Clicked Teacher Menu Button
				mainMenuPanel.setVisible(false);
				studentMenuPanel.setVisible(false);
				teacherMenuPanel.setVisible(true);
				lessonMenuPanel.setVisible(false);
				enrollMenuPanel.setVisible(false);
			}
		});
		teacherManagementMenuButton.setAlphaPressedDefault(0.1f);
		teacherManagementMenuButton.setAlphaForHoveringChangeSpeed(0.3f);
		teacherManagementMenuButton.setAlphaForHoveringLowest(0.0f);
		teacherManagementMenuButton.setStyleGradientColor1(new Color(180, 180, 180));
		teacherManagementMenuButton.setStyleGradientColor2(new Color(180, 180, 180));
		teacherManagementMenuButton.setStyle2Active(true);
		teacherManagementMenuButton.setPressedShineColor(new Color(192, 192, 192));
		teacherManagementMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		teacherManagementMenuButton.setColor2(new Color(252, 249, 255));
		teacherManagementMenuButton.setColor1(new Color(252, 249, 255));
		teacherManagementMenuButton.setFont(new Font("Geist", Font.BOLD, 15));
		teacherManagementMenuButton.setForeground(new Color(120, 120, 120));
		teacherManagementMenuButton.setText("Teacher Menu");
		teacherManagementMenuButton.setBounds(75, 280, 150, 50);
		navBarPanel.add(teacherManagementMenuButton);
		
		JLabel teacherMenuIcon = new JLabel("New label");
		teacherMenuIcon.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\teacherIcon.png"));
		teacherMenuIcon.setBounds(45, 292, 25, 25);
		navBarPanel.add(teacherMenuIcon);
		
		ButtonGradient lessonManagementMenuButton = new ButtonGradient();
		lessonManagementMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Clicked Lesson Menu Button
				mainMenuPanel.setVisible(false);
				studentMenuPanel.setVisible(false);
				teacherMenuPanel.setVisible(false);
				lessonMenuPanel.setVisible(true);
				enrollMenuPanel.setVisible(false);
			}
		});
		lessonManagementMenuButton.setAlphaPressedDefault(0.1f);
		lessonManagementMenuButton.setAlphaForHoveringChangeSpeed(0.3f);
		lessonManagementMenuButton.setAlphaForHoveringLowest(0.0f);
		lessonManagementMenuButton.setStyleGradientColor2(new Color(180, 180, 180));
		lessonManagementMenuButton.setStyleGradientColor1(new Color(180, 180, 180));
		lessonManagementMenuButton.setStyle2Active(true);
		lessonManagementMenuButton.setPressedShineColor(new Color(192, 192, 192));
		lessonManagementMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		lessonManagementMenuButton.setColor2(new Color(252, 249, 255));
		lessonManagementMenuButton.setColor1(new Color(252, 249, 255));
		lessonManagementMenuButton.setFont(new Font("Geist", Font.BOLD, 15));
		lessonManagementMenuButton.setForeground(new Color(120, 120, 120));
		lessonManagementMenuButton.setText("Lesson Menu");
		lessonManagementMenuButton.setBounds(75, 340, 150, 50);
		navBarPanel.add(lessonManagementMenuButton);
		
		JLabel lessonMenuIcon = new JLabel("New label");
		lessonMenuIcon.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\lessonIcon.png"));
		lessonMenuIcon.setBounds(45, 352, 25, 25);
		navBarPanel.add(lessonMenuIcon);
		
		ButtonGradient enrollMenuButton = new ButtonGradient();
		enrollMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Clicked Enroll Menu Button
				mainMenuPanel.setVisible(false);
				studentMenuPanel.setVisible(false);
				teacherMenuPanel.setVisible(false);
				lessonMenuPanel.setVisible(false);
				enrollMenuPanel.setVisible(true);
			}
		});
		enrollMenuButton.setAlphaPressedDefault(0.1f);
		enrollMenuButton.setAlphaForHoveringChangeSpeed(0.3f);
		enrollMenuButton.setAlphaForHoveringLowest(0.0f);
		enrollMenuButton.setStyleGradientColor2(new Color(180, 180, 180));
		enrollMenuButton.setStyleGradientColor1(new Color(180, 180, 180));
		enrollMenuButton.setStyle2Active(true);
		enrollMenuButton.setPressedShineColor(new Color(192, 192, 192));
		enrollMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		enrollMenuButton.setColor2(new Color(252, 249, 255));
		enrollMenuButton.setColor1(new Color(252, 249, 255));
		enrollMenuButton.setFont(new Font("Geist", Font.BOLD, 15));
		enrollMenuButton.setForeground(new Color(120, 120, 120));
		enrollMenuButton.setText("Enroll Menu");
		enrollMenuButton.setBounds(75, 400, 150, 50);
		navBarPanel.add(enrollMenuButton);
		
		JLabel enrollMenuIcon = new JLabel("New label");
		enrollMenuIcon.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\enrollmentIcon.png"));
		enrollMenuIcon.setBounds(45, 412, 25, 25);
		navBarPanel.add(enrollMenuIcon);
		
		ButtonGradient logoutMenuButton = new ButtonGradient();
		logoutMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		logoutMenuButton.setText("Log Out");
		logoutMenuButton.setStyleGradientColor2(new Color(180, 180, 180));
		logoutMenuButton.setStyleGradientColor1(new Color(180, 180, 180));
		logoutMenuButton.setStyle2Active(true);
		logoutMenuButton.setPressedShineColor(Color.LIGHT_GRAY);
		logoutMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		logoutMenuButton.setForeground(new Color(90, 90, 90));
		logoutMenuButton.setFont(new Font("Geist", Font.BOLD, 15));
		logoutMenuButton.setColor2(new Color(252, 249, 255));
		logoutMenuButton.setColor1(new Color(252, 249, 255));
		logoutMenuButton.setAlphaPressedDefault(0.1f);
		logoutMenuButton.setAlphaForHoveringLowest(0.0f);
		logoutMenuButton.setAlphaForHoveringChangeSpeed(0.3f);
		logoutMenuButton.setBounds(75, 570, 150, 50);
		navBarPanel.add(logoutMenuButton);
		
		JLabel enrollMenuIcon_1 = new JLabel("New label");
		enrollMenuIcon_1.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\logoutIcon.png"));
		enrollMenuIcon_1.setBounds(45, 582, 25, 25);
		navBarPanel.add(enrollMenuIcon_1);
		
		JLabel schoolIcon = new JLabel("");
		schoolIcon.setHorizontalAlignment(SwingConstants.CENTER);
		schoolIcon.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\schoolLogoBig.png"));
		schoolIcon.setBounds(0, 0, 260, 149);
		navBarPanel.add(schoolIcon);
		
		JLabel adminUIMenuPanelBg = new JLabel("New label");
		adminUIMenuPanelBg.setBackground(new Color(250, 255, 255));
		adminUIMenuPanelBg.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\AdminUIMenuPanelBg2.png"));
		adminUIMenuPanelBg.setBounds(0, 0, 260, 681);
		navBarPanel.add(adminUIMenuPanelBg);
	}
}
