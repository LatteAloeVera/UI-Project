import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class AdminMenuUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					AdminMenuUI frame = new AdminMenuUI();
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
	public AdminMenuUI() {
		setTitle("Teacher Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.inactiveCaptionBorder);
		tabbedPane.setBounds(10, 11, 834, 399);
		contentPane.add(tabbedPane);
		
		JPanel studentManagementPanel = new JPanel();
		studentManagementPanel.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Add / Remove Student", null, studentManagementPanel, null);
		studentManagementPanel.setLayout(null);
		
		JPanel addPanel = new JPanel();
		addPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		addPanel.setBackground(SystemColor.inactiveCaptionBorder);
		addPanel.setForeground(SystemColor.inactiveCaptionBorder);
		addPanel.setBounds(10, 11, 394, 349);
		studentManagementPanel.add(addPanel);
		addPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(147, 108, 206, 27);
		addPanel.add(textField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(147, 146, 206, 27);
		addPanel.add(passwordField);
		
		JLabel passwordLabel = new JLabel("Password :");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordLabel.setBounds(10, 136, 126, 44);
		addPanel.add(passwordLabel);
		
		JLabel usernameLabel = new JLabel("Username :");
		usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usernameLabel.setBounds(10, 97, 126, 44);
		addPanel.add(usernameLabel);
		
		JPanel removePanel = new JPanel();
		removePanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		removePanel.setBackground(SystemColor.inactiveCaptionBorder);
		removePanel.setBounds(425, 11, 394, 349);
		studentManagementPanel.add(removePanel);
		removePanel.setLayout(null);
		
		JComboBox studentNamesComboBox = new JComboBox();
		studentNamesComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		studentNamesComboBox.setBounds(216, 127, 168, 24);
		removePanel.add(studentNamesComboBox);
		updateComboBox(studentNamesComboBox);
		
		
		JButton addConfirmButton = new JButton("Confirm");
		addConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Student add button clicked:
				HashMap<String, User> userMap = readUserFile();
				String usrName = textField.getText().trim();
				String usrPass = passwordField.getText().trim();
				
				
				if(usrName.length() == 0 || usrPass.length() == 0) {
					errorBox("Please fill in all the empty areas!","Empty Area Remaining!");
				}
				else if(userMap.containsKey(usrName)) {
					errorBox("There is an another user named \"" + usrName + "\" , Please enter an another username!" ,"Empty Area Remaining!");
				}
				else {
					addNewUser(usrName, usrPass, "Student");
					updateComboBox(studentNamesComboBox);
					infoBox("New student \"" + usrName + "\" added!", "Successful!");
				}
				
			}
		});
		addConfirmButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addConfirmButton.setBackground(UIManager.getColor("Button.light"));
		addConfirmButton.setBounds(119, 215, 147, 44);
		addPanel.add(addConfirmButton);
		
		JLabel addStudentTitle = new JLabel("= Add A New Student =");
		addStudentTitle.setForeground(new Color(100, 149, 237));
		addStudentTitle.setHorizontalAlignment(SwingConstants.CENTER);
		addStudentTitle.setFont(new Font("Tahoma", Font.BOLD, 19));
		addStudentTitle.setBounds(10, 23, 374, 53);
		addPanel.add(addStudentTitle);
		
		
		
		JLabel removeStudentTitle = new JLabel("= Remove Student =");
		removeStudentTitle.setBounds(10, 23, 374, 48);
		removeStudentTitle.setHorizontalAlignment(SwingConstants.CENTER);
		removeStudentTitle.setForeground(new Color(100, 149, 237));
		removeStudentTitle.setFont(new Font("Tahoma", Font.BOLD, 19));
		removePanel.add(removeStudentTitle);
		
		
		
		JLabel lblNewLabel = new JLabel("Select Student Name :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(10, 105, 196, 58);
		removePanel.add(lblNewLabel);
		
		JButton removeConfirmButton = new JButton("Confirm");
		removeConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Student remove button clicked:
				
				if(studentNamesComboBox.getSelectedIndex() < 0) {
					errorBox("Nothing selected!", "Error!");
				}
				else {
					String usrName = studentNamesComboBox.getSelectedItem().toString();
					
					deleteUserFromFile(usrName);

					infoBox("Deleted from file!", "Successful!");
					updateComboBox(studentNamesComboBox);
				}
			}
		});
		removeConfirmButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		removeConfirmButton.setBackground(UIManager.getColor("Button.light"));
		removeConfirmButton.setBounds(129, 215, 147, 44);
		removePanel.add(removeConfirmButton);
		
		JPanel lessonManagementPanel = new JPanel();
		lessonManagementPanel.setBackground(Color.RED);
		tabbedPane.addTab("Add / Remove Lessons", null, lessonManagementPanel, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		
		
		
	}
	
	
	private void updateComboBox(JComboBox studentNamesComboBox) {
		HashMap<String, User> userMap = readUserFile();
		studentNamesComboBox.removeAllItems();
		for(String usrName : userMap.keySet()) {
			studentNamesComboBox.addItem(usrName);
		}
	}

	protected void addNewUser(String username, String password, String tag) {
		File userFile = new File("src/users.txt");
		String userLine = username + "," + password + "," + tag + "\n";
		if(!userFile.exists()) {
			try {
				userFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			FileWriter writer = new FileWriter("src/users.txt", true);
			writer.write(userLine);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void deleteUserFromFile(String usrName) {
		File oldFile = new File("src/users.txt"); 
		File newFile = new File("src/usersTEMP.txt");
		try {
			newFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(!oldFile.exists()) {
			try {
				oldFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			Scanner oldFileScan = new Scanner(oldFile);
			while(oldFileScan.hasNextLine()) {
				String[] userStuff = oldFileScan.nextLine().split(",");
				
				//if line is the line we want to remove, skip it.
				if(userStuff[0].equals(usrName)) {
					
				}
				//Write others onto new file
				else {
					try {
						String userLine = userStuff[0] + "," + userStuff[1] + "," + userStuff[2] + "\n";
						FileWriter newWriter = new FileWriter("src/usersTEMP.txt", true);
						newWriter.write(userLine);
						newWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}
			oldFileScan.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Delete old file
		oldFile.delete();
		
		//rename temp file
		newFile.renameTo(oldFile);
		
	}
	
	protected HashMap<String, User> readUserFile(){
		HashMap<String,User> userMap = new HashMap<String,User>();
		File userFile = new File("src/users.txt");
		if(!userFile.exists()) {
			try {
				userFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			Scanner fileScan = new Scanner(userFile);
			while(fileScan.hasNextLine()) {
				String[] userStuff = fileScan.nextLine().split(",");
				if(userStuff.length != 0) {
					if(userStuff[2].compareTo("Teacher") == 0) {
						Teacher teacher = new Teacher(userStuff[0], userStuff[1]);
						userMap.put(userStuff[0], teacher);
					}
					else {
						Student student = new Student(userStuff[0], userStuff[1]);
						userMap.put(userStuff[0], student);
					}
					
				}
			}
			fileScan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return userMap;
	} 
	
	public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
	
	public static void errorBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.ERROR_MESSAGE);
    }
	
	
}
