import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.SystemColor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class StudentMenuUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private User currentStudent = FuncManager.getStudentByName(LoginUI.sessionUserName);
	private String currentStudentName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentMenuUI frame = new StudentMenuUI();
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
	public StudentMenuUI() {
		//Non-comment to make easy to open without login
		//if(currentStudent == null)
		//	currentStudent = new Student("Selen","uwu","224220002");
		
		currentStudentName = currentStudent.getName();
		setTitle("Student Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 460);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 834, 400);
		contentPane.add(tabbedPane);
		
		JPanel mainMenuPanel = new JPanel();
		mainMenuPanel.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Main Menu", null, mainMenuPanel, null);
		mainMenuPanel.setLayout(null);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		infoPanel.setBackground(SystemColor.inactiveCaptionBorder);
		infoPanel.setBounds(10, 11, 398, 172);
		mainMenuPanel.add(infoPanel);
		infoPanel.setLayout(null);
		
		JPanel picturePanel = new JPanel();
		picturePanel.setBorder(new LineBorder(SystemColor.activeCaption, 2, true));
		picturePanel.setBounds(20, 71, 52, 65);
		infoPanel.add(picturePanel);
		
		
		JLabel lblWelcome = new JLabel("= Welcome, "+ currentStudentName + "=");
		lblWelcome.setBounds(10, 11, 378, 49);
		infoPanel.add(lblWelcome);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setForeground(new Color(100, 149, 237));
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 19));
		
		JLabel lblUsrNameAndNumber = new JLabel(currentStudentName + ", " + currentStudent.getID() + ".");
		lblUsrNameAndNumber.setForeground(SystemColor.windowBorder);
		lblUsrNameAndNumber.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsrNameAndNumber.setBounds(82, 78, 133, 14);
		infoPanel.add(lblUsrNameAndNumber);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1_1.setBounds(10, 189, 398, 172);
		mainMenuPanel.add(panel_1_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(418, 11, 398, 350);
		mainMenuPanel.add(panel);
	}
}
