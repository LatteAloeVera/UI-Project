import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

public class NewAdminMenuUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		
		JPanel navBarPanel = new JPanel();
		navBarPanel.setBackground(new Color(250, 244, 255));
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
			}
		});
		
		mainMenuButton.setFont(new Font("Geist", Font.BOLD, 15));
		mainMenuButton.setForeground(new Color(93, 93, 93));
		mainMenuButton.setText("Main Menu");
		mainMenuButton.setColor2(new Color(252, 249, 255));
		mainMenuButton.setColor1(new Color(252, 249, 255));
		mainMenuButton.setBounds(75, 160, 150, 50);
		navBarPanel.add(mainMenuButton);
		
		JLabel mainMenuIconLabel = new JLabel("New label");
		mainMenuIconLabel.setIcon(new ImageIcon("C:\\Users\\Ayberk Sevgi\\git\\UI-Project\\content\\homeIcon.png"));
		mainMenuIconLabel.setBounds(45, 172, 25, 25);
		navBarPanel.add(mainMenuIconLabel);
		
		ButtonGradient studentManagementMenuButton = new ButtonGradient();
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
		studentManagementMenuButton.setForeground(new Color(93, 93, 93));
		studentManagementMenuButton.setText("Student Menu");
		studentManagementMenuButton.setFont(new Font("Geist", Font.BOLD, 15));
		
		studentManagementMenuButton.setBounds(75, 220, 150, 50);
		navBarPanel.add(studentManagementMenuButton);
		
		JLabel studentMenuIcon = new JLabel("New label");
		studentMenuIcon.setIcon(new ImageIcon("C:\\Users\\Ayberk Sevgi\\git\\UI-Project\\content\\studentIcon.png"));
		studentMenuIcon.setBounds(45, 232, 25, 25);
		navBarPanel.add(studentMenuIcon);
		
		ButtonGradient teacherManagementMenuButton = new ButtonGradient();
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
		teacherManagementMenuButton.setForeground(new Color(93, 93, 93));
		teacherManagementMenuButton.setText("Teacher Menu");
		teacherManagementMenuButton.setBounds(75, 280, 150, 50);
		navBarPanel.add(teacherManagementMenuButton);
		
		JLabel teacherMenuIcon = new JLabel("New label");
		teacherMenuIcon.setIcon(new ImageIcon("C:\\Users\\Ayberk Sevgi\\git\\UI-Project\\content\\teacherIcon.png"));
		teacherMenuIcon.setBounds(45, 292, 25, 25);
		navBarPanel.add(teacherMenuIcon);
		
		ButtonGradient lessonManagementMenuButton = new ButtonGradient();
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
		lessonManagementMenuButton.setForeground(new Color(93, 93, 93));
		lessonManagementMenuButton.setText("Lesson Menu");
		lessonManagementMenuButton.setBounds(75, 340, 150, 50);
		navBarPanel.add(lessonManagementMenuButton);
		
		JLabel lessonMenuIcon = new JLabel("New label");
		lessonMenuIcon.setIcon(new ImageIcon("C:\\Users\\Ayberk Sevgi\\git\\UI-Project\\content\\lessonIcon.png"));
		lessonMenuIcon.setBounds(45, 352, 25, 25);
		navBarPanel.add(lessonMenuIcon);
		
		ButtonGradient enrollMenuButton = new ButtonGradient();
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
		enrollMenuButton.setForeground(new Color(93, 93, 93));
		enrollMenuButton.setText("Enroll Menu");
		enrollMenuButton.setBounds(75, 400, 150, 50);
		navBarPanel.add(enrollMenuButton);
		
		JLabel enrollMenuIcon = new JLabel("New label");
		enrollMenuIcon.setIcon(new ImageIcon("C:\\Users\\Ayberk Sevgi\\git\\UI-Project\\content\\enrollmentIcon.png"));
		enrollMenuIcon.setBounds(45, 412, 25, 25);
		navBarPanel.add(enrollMenuIcon);
		
		ButtonGradient logoutMenuButton = new ButtonGradient();
		logoutMenuButton.setText("Log Out");
		logoutMenuButton.setStyleGradientColor2(new Color(180, 180, 180));
		logoutMenuButton.setStyleGradientColor1(new Color(180, 180, 180));
		logoutMenuButton.setStyle2Active(true);
		logoutMenuButton.setPressedShineColor(Color.LIGHT_GRAY);
		logoutMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		logoutMenuButton.setForeground(new Color(93, 93, 93));
		logoutMenuButton.setFont(new Font("Geist", Font.BOLD, 15));
		logoutMenuButton.setColor2(new Color(252, 249, 255));
		logoutMenuButton.setColor1(new Color(252, 249, 255));
		logoutMenuButton.setAlphaPressedDefault(0.1f);
		logoutMenuButton.setAlphaForHoveringLowest(0.0f);
		logoutMenuButton.setAlphaForHoveringChangeSpeed(0.3f);
		logoutMenuButton.setBounds(75, 570, 150, 50);
		navBarPanel.add(logoutMenuButton);
		
		JLabel enrollMenuIcon_1 = new JLabel("New label");
		enrollMenuIcon_1.setIcon(new ImageIcon("C:\\Users\\Ayberk Sevgi\\git\\UI-Project\\content\\logoutIcon.png"));
		enrollMenuIcon_1.setBounds(45, 582, 25, 25);
		navBarPanel.add(enrollMenuIcon_1);
		
		JLabel adminUIMenuPanelBgLabel = new JLabel("New label");
		adminUIMenuPanelBgLabel.setBackground(new Color(249, 249, 249));
		adminUIMenuPanelBgLabel.setIcon(new ImageIcon("C:\\Users\\Ayberk Sevgi\\git\\UI-Project\\content\\AdminUIMenuPanelBg.png"));
		adminUIMenuPanelBgLabel.setBounds(0, 0, 260, 681);
		navBarPanel.add(adminUIMenuPanelBgLabel);
		
		JPanel uiPanel = new JPanel();
		uiPanel.setBackground(new Color(250, 244, 255));
		uiPanel.setBounds(260, 0, 1004, 681);
		contentPane.add(uiPanel);
		uiPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Ayberk Sevgi\\git\\UI-Project\\content\\AdminUIPanelBg2.png"));
		lblNewLabel.setBounds(0, 0, 1004, 681);
		uiPanel.add(lblNewLabel);
	}
}
