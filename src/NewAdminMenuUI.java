import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JButton;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage("content/adminPanelIco.png"));
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
		navBarPanel.setBounds(0, 0, 235, 681);
		contentPane.add(navBarPanel);
		navBarPanel.setLayout(null);
		
		ButtonGradient mainMenuButton = new ButtonGradient();
		mainMenuButton.setFont(new Font("Geist", Font.BOLD, 15));
		mainMenuButton.setForeground(new Color(93, 93, 93));
		mainMenuButton.setText("Main Menu");
		mainMenuButton.setColor2(new Color(252, 249, 255));
		mainMenuButton.setColor1(new Color(252, 249, 255));
		mainMenuButton.setBounds(50, 145, 175, 50);
		navBarPanel.add(mainMenuButton);
		
		ButtonGradient studentManagementMenuButton = new ButtonGradient();
		studentManagementMenuButton.setForeground(new Color(93, 93, 93));
		studentManagementMenuButton.setText("Student Menu");
		studentManagementMenuButton.setFont(new Font("Geist", Font.BOLD, 15));
		studentManagementMenuButton.setBounds(50, 205, 175, 50);
		navBarPanel.add(studentManagementMenuButton);
		
		ButtonGradient teacherManagementMenuButton = new ButtonGradient();
		teacherManagementMenuButton.setFont(new Font("Geist", Font.BOLD, 15));
		teacherManagementMenuButton.setForeground(new Color(93, 93, 93));
		teacherManagementMenuButton.setText("Teacher Menu");
		teacherManagementMenuButton.setBounds(50, 265, 175, 50);
		navBarPanel.add(teacherManagementMenuButton);
		
		ButtonGradient lessonManagementMenuButton = new ButtonGradient();
		lessonManagementMenuButton.setFont(new Font("Geist", Font.BOLD, 15));
		lessonManagementMenuButton.setForeground(new Color(93, 93, 93));
		lessonManagementMenuButton.setText("Lesson Menu");
		lessonManagementMenuButton.setBounds(50, 325, 175, 50);
		navBarPanel.add(lessonManagementMenuButton);
		
		ButtonGradient studentEnrollMenuButton = new ButtonGradient();
		studentEnrollMenuButton.setFont(new Font("Geist", Font.BOLD, 15));
		studentEnrollMenuButton.setForeground(new Color(93, 93, 93));
		studentEnrollMenuButton.setText("Enrollment Menu");
		studentEnrollMenuButton.setBounds(50, 385, 175, 50);
		navBarPanel.add(studentEnrollMenuButton);
		
		JPanel uiPanel = new JPanel();
		uiPanel.setBackground(new Color(248, 247, 249));
		uiPanel.setBounds(235, 0, 1030, 681);
		contentPane.add(uiPanel);
		uiPanel.setLayout(null);
	}
}
