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
		navBarPanel.setBackground(new Color(252, 249, 255));
		navBarPanel.setBounds(0, 0, 260, 681);
		contentPane.add(navBarPanel);
		navBarPanel.setLayout(null);
		
		ButtonGradient mainMenuButton = new ButtonGradient();
		mainMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		mainMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Clicked Main Menu Button:
			}
		});
		mainMenuButton.setFont(new Font("Geist", Font.BOLD, 15));
		mainMenuButton.setForeground(new Color(93, 93, 93));
		mainMenuButton.setText("Main Menu");
		mainMenuButton.setColor2(new Color(252, 249, 255, 0));
		mainMenuButton.setColor1(new Color(252, 249, 255, 0));
		mainMenuButton.setBounds(80, 230, 170, 50);
		navBarPanel.add(mainMenuButton);
		
		JLabel mainMenuIconLabel = new JLabel("New label");
		mainMenuIconLabel.setIcon(new ImageIcon("C:\\Users\\Ayberk Sevgi\\git\\UI-Project\\content\\homeIcon.png"));
		mainMenuIconLabel.setBounds(50, 242, 25, 25);
		navBarPanel.add(mainMenuIconLabel);
		
		ButtonGradient studentManagementMenuButton = new ButtonGradient();
		studentManagementMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		studentManagementMenuButton.setColor2(new Color(252, 249, 255, 0));
		studentManagementMenuButton.setColor1(new Color(252, 249, 255, 0));
		studentManagementMenuButton.setForeground(new Color(93, 93, 93));
		studentManagementMenuButton.setText("Student Menu");
		studentManagementMenuButton.setFont(new Font("Geist", Font.BOLD, 15));
		
		studentManagementMenuButton.setBounds(80, 290, 170, 50);
		navBarPanel.add(studentManagementMenuButton);
		
		JLabel studentMenuIcon = new JLabel("New label");
		studentMenuIcon.setIcon(new ImageIcon("C:\\Users\\Ayberk Sevgi\\git\\UI-Project\\content\\studentIcon.png"));
		studentMenuIcon.setBounds(50, 302, 25, 25);
		navBarPanel.add(studentMenuIcon);
		
		ButtonGradient teacherManagementMenuButton = new ButtonGradient();
		teacherManagementMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		teacherManagementMenuButton.setColor2(new Color(252, 249, 255, 0));
		teacherManagementMenuButton.setColor1(new Color(252, 249, 255, 0));
		teacherManagementMenuButton.setFont(new Font("Geist", Font.BOLD, 15));
		teacherManagementMenuButton.setForeground(new Color(93, 93, 93));
		teacherManagementMenuButton.setText("Teacher Menu");
		teacherManagementMenuButton.setBounds(80, 350, 170, 50);
		navBarPanel.add(teacherManagementMenuButton);
		
		JLabel teacherMenuIcon = new JLabel("New label");
		teacherMenuIcon.setIcon(new ImageIcon("C:\\Users\\Ayberk Sevgi\\git\\UI-Project\\content\\teacherIcon.png"));
		teacherMenuIcon.setBounds(50, 362, 25, 25);
		navBarPanel.add(teacherMenuIcon);
		
		ButtonGradient lessonManagementMenuButton = new ButtonGradient();
		lessonManagementMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		lessonManagementMenuButton.setColor2(new Color(252, 249, 255, 0));
		lessonManagementMenuButton.setColor1(new Color(252, 249, 255, 0));
		lessonManagementMenuButton.setFont(new Font("Geist", Font.BOLD, 15));
		lessonManagementMenuButton.setForeground(new Color(93, 93, 93));
		lessonManagementMenuButton.setText("Lesson Menu");
		lessonManagementMenuButton.setBounds(80, 410, 170, 50);
		navBarPanel.add(lessonManagementMenuButton);
		
		JLabel lessonMenuIcon = new JLabel("New label");
		lessonMenuIcon.setIcon(new ImageIcon("C:\\Users\\Ayberk Sevgi\\git\\UI-Project\\content\\lessonIcon.png"));
		lessonMenuIcon.setBounds(50, 422, 25, 25);
		navBarPanel.add(lessonMenuIcon);
		
		ButtonGradient enrollMenuButton = new ButtonGradient();
		enrollMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		enrollMenuButton.setColor2(new Color(252, 249, 255, 0));
		enrollMenuButton.setColor1(new Color(252, 249, 255, 0));
		enrollMenuButton.setFont(new Font("Geist", Font.BOLD, 15));
		enrollMenuButton.setForeground(new Color(93, 93, 93));
		enrollMenuButton.setText("Enroll Menu");
		enrollMenuButton.setBounds(80, 470, 170, 50);
		navBarPanel.add(enrollMenuButton);
		
		JLabel enrollMenuIcon = new JLabel("New label");
		enrollMenuIcon.setIcon(new ImageIcon("C:\\Users\\Ayberk Sevgi\\git\\UI-Project\\content\\enrollmentIcon.png"));
		enrollMenuIcon.setBounds(50, 482, 25, 25);
		navBarPanel.add(enrollMenuIcon);
		
		JLabel adminUIMenuPanelBgLabel = new JLabel("New label");
		adminUIMenuPanelBgLabel.setIcon(new ImageIcon("C:\\Users\\Ayberk Sevgi\\git\\UI-Project\\content\\AdminUIMenuPanelBg.png"));
		adminUIMenuPanelBgLabel.setBounds(0, 0, 260, 681);
		navBarPanel.add(adminUIMenuPanelBgLabel);
		
		JPanel uiPanel = new JPanel();
		uiPanel.setBackground(new Color(252, 249, 255));
		uiPanel.setBounds(260, 0, 1004, 681);
		contentPane.add(uiPanel);
		uiPanel.setLayout(null);
	}
}
