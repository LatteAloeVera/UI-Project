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
	private JTextField lessonNameTextField;
	private JTextField lessonCreditsTextField;
	private JTextField lessonCodeTextField;
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
		updateStudentComboBox(studentNamesComboBox);
		
		
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
					addNewUserToFile(usrName, usrPass, "Student");
					updateStudentComboBox(studentNamesComboBox);
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
					updateStudentComboBox(studentNamesComboBox);
				}
			}
		});
		removeConfirmButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		removeConfirmButton.setBackground(UIManager.getColor("Button.light"));
		removeConfirmButton.setBounds(129, 215, 147, 44);
		removePanel.add(removeConfirmButton);
		
		JPanel lessonManagementPanel = new JPanel();
		lessonManagementPanel.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Add / Remove Lessons", null, lessonManagementPanel, null);
		lessonManagementPanel.setLayout(null);
		
		JPanel addPanel_1 = new JPanel();
		addPanel_1.setLayout(null);
		addPanel_1.setForeground(SystemColor.inactiveCaptionBorder);
		addPanel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		addPanel_1.setBackground(SystemColor.inactiveCaptionBorder);
		addPanel_1.setBounds(10, 11, 394, 349);
		lessonManagementPanel.add(addPanel_1);
		
		lessonNameTextField = new JTextField();
		lessonNameTextField.setColumns(10);
		lessonNameTextField.setBounds(164, 109, 206, 27);
		addPanel_1.add(lessonNameTextField);
		
		JLabel lessonTeacherLabel = new JLabel("Lesson Teacher :");
		lessonTeacherLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lessonTeacherLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lessonTeacherLabel.setBounds(20, 135, 134, 44);
		addPanel_1.add(lessonTeacherLabel);
		
		JLabel lessonLabel_1 = new JLabel("Lesson Name :");
		lessonLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lessonLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lessonLabel_1.setBounds(20, 97, 134, 44);
		addPanel_1.add(lessonLabel_1);
		
		
		
		JLabel lblAddLessons = new JLabel("= Add A New Lesson =");
		lblAddLessons.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddLessons.setForeground(new Color(100, 149, 237));
		lblAddLessons.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblAddLessons.setBounds(10, 23, 374, 53);
		addPanel_1.add(lblAddLessons);
		
		lessonCreditsTextField = new JTextField();
		lessonCreditsTextField.setColumns(10);
		lessonCreditsTextField.setBounds(164, 182, 206, 27);
		addPanel_1.add(lessonCreditsTextField);
		
		JComboBox lessonTeachersComboBox = new JComboBox();
		lessonTeachersComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lessonTeachersComboBox.setBounds(164, 147, 206, 24);
		addPanel_1.add(lessonTeachersComboBox);
		updateTeacherComboBox(lessonTeachersComboBox);
		
		JLabel lessonCreditLabel = new JLabel("Lesson Credit :");
		lessonCreditLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lessonCreditLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lessonCreditLabel.setBounds(20, 170, 134, 44);
		addPanel_1.add(lessonCreditLabel);
		
		JLabel lessonCodeLabel = new JLabel("Lesson Code :");
		lessonCodeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lessonCodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lessonCodeLabel.setBounds(20, 210, 134, 44);
		addPanel_1.add(lessonCodeLabel);
		
		lessonCodeTextField = new JTextField();
		lessonCodeTextField.setColumns(10);
		lessonCodeTextField.setBounds(164, 220, 206, 27);
		addPanel_1.add(lessonCodeTextField);
		
		JPanel removePanel_1 = new JPanel();
		removePanel_1.setLayout(null);
		removePanel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		removePanel_1.setBackground(SystemColor.inactiveCaptionBorder);
		removePanel_1.setBounds(425, 11, 394, 349);
		lessonManagementPanel.add(removePanel_1);
		
		JComboBox lessonNamesComboBox = new JComboBox();
		lessonNamesComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lessonNamesComboBox.setBounds(203, 124, 168, 24);
		removePanel_1.add(lessonNamesComboBox);
		updateLessonComboBox(lessonNamesComboBox);
		
		JLabel lblRemoveLesson = new JLabel("= Remove Lesson =");
		lblRemoveLesson.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoveLesson.setForeground(new Color(100, 149, 237));
		lblRemoveLesson.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblRemoveLesson.setBounds(10, 23, 374, 48);
		removePanel_1.add(lblRemoveLesson);
		
		JLabel lblLessonRemove = new JLabel("Select Lesson Name :");
		lblLessonRemove.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLessonRemove.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLessonRemove.setBounds(0, 105, 196, 58);
		removePanel_1.add(lblLessonRemove);
		
		JButton removeConfirmButton_Lessons = new JButton("Confirm");
		removeConfirmButton_Lessons.setFont(new Font("Tahoma", Font.PLAIN, 14));
		removeConfirmButton_Lessons.setBackground(UIManager.getColor("Button.light"));
		removeConfirmButton_Lessons.setBounds(129, 215, 147, 44);
		removePanel_1.add(removeConfirmButton_Lessons);
		
		JButton addConfirmButton_Lessons = new JButton("Confirm");
		addConfirmButton_Lessons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//When clicked Add Lesson Confirm Button:
				HashMap<String, Lesson> lessonMap = readLessonFile();
				
				String lessonCode = lessonCodeTextField.getText();
				String lessonName = lessonNameTextField.getText();
				Teacher lessonTeacher;
				
				if(lessonCreditsTextField.getText().length() == 0 || lessonCode.length() == 0 || lessonName.length() == 0) {
					errorBox("Please fill in all the empty areas!", "Empty Area Remaining!");
				}
				else if(lessonTeachersComboBox.getSelectedIndex() < 0) {
					errorBox("Nothing selected as a teacher!", "Error!");
				}
				else if(lessonMap.containsKey(lessonName)) {
					errorBox("There is an another lesson named \"" + lessonName + "\", please enter an another lesson name!", "Name Exists!");
					lessonCodeTextField.setText("");
					lessonNameTextField.setText("");
					lessonCreditsTextField.setText("");
				}
				else {
					int lessonCredit = Integer.parseInt(lessonCreditsTextField.getText());
					String teacherName = lessonTeachersComboBox.getSelectedItem().toString();
					lessonTeacher = getTeacherByName(teacherName);
					addNewLessonToFile(lessonCode, lessonName, lessonCredit, lessonTeacher);
					updateLessonComboBox(lessonNamesComboBox);
					
					infoBox("New lesson\"" + lessonName + "\" added!","Successfull!");
					
					lessonCodeTextField.setText("");
					lessonNameTextField.setText("");
					lessonCreditsTextField.setText("");
				}
				
			}
		});
		addConfirmButton_Lessons.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addConfirmButton_Lessons.setBackground(UIManager.getColor("Button.light"));
		addConfirmButton_Lessons.setBounds(119, 294, 147, 44);
		addPanel_1.add(addConfirmButton_Lessons);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		
		
		
	}
	
	protected Teacher getTeacherByName(String teacherName) {
		HashMap<String, User> userMap = readUserFile();
		for(String usrName : userMap.keySet()) {
			if(usrName.equals(teacherName)) {
				return (Teacher)userMap.get(usrName);
			}
		}
		return null;
	}

	
	private void updateStudentComboBox(JComboBox studentNamesComboBox) {
		HashMap<String, User> userMap = readUserFile();
		studentNamesComboBox.removeAllItems();
		for(String usrName : userMap.keySet()) {
			if(userMap.get(usrName).getTag().equals("Student")) {
				studentNamesComboBox.addItem(usrName);
			}
		}
	}
	
	private void updateTeacherComboBox(JComboBox teacherNamesComboBox) {
		HashMap<String, User> userMap = readUserFile();
		teacherNamesComboBox.removeAllItems();
		for(String usrName : userMap.keySet()) {
			if(userMap.get(usrName).getTag().equals("Teacher")) {
				teacherNamesComboBox.addItem(usrName);
			}
		}
	}
	
	private void updateLessonComboBox(JComboBox lessonNamesComboBox) {
		HashMap<String, Lesson> lessonMap = readLessonFile();
		lessonNamesComboBox.removeAllItems();
		for(String lessonName : lessonMap.keySet()) {
			lessonNamesComboBox.addItem(lessonName);
		}
	}

	protected void addNewUserToFile(String username, String password, String tag) {
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
	
	protected void addNewLessonToFile(String code, String name, int credit, Teacher teacher) {
		File lessonFile = new File("src/lessons.txt");
		String lessonLine = code + "," + name + "," + credit + "," + teacher.getName() + "\n";
		if(!lessonFile.exists()) {
			try {
				lessonFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			FileWriter writer = new FileWriter("src/lessons.txt", true);
			writer.write(lessonLine);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void deleteLessonFromFile(String lessonName) {
		File oldFile = new File("src/lessons.txt"); 
		File newFile = new File("src/lessonsTEMP.txt");
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
				String[] lessonStuff = oldFileScan.nextLine().split(",");
				
				//if line is the line we want to remove, skip it.
				if(lessonStuff[1].equals(lessonName)) {
					
				}
				//Write others onto new file
				else {
					try {
						String lessonLine = lessonStuff[0] + "," + lessonStuff[1] + "," + lessonStuff[2] + "," + lessonStuff[3] + "\n";
						FileWriter newWriter = new FileWriter("src/lessonsTEMP.txt", true);
						newWriter.write(lessonLine);
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
	
	protected HashMap<String, Lesson> readLessonFile(){
		HashMap<String, Lesson> lessonMap = new HashMap<String, Lesson>();
		File lessonFile = new File("src/lessons.txt");
		if(!lessonFile.exists()) {
			try {
				lessonFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			Scanner fileScan = new Scanner(lessonFile);
			while(fileScan.hasNextLine()) {
				String[] lessonStuff = fileScan.nextLine().split(",");
				if(lessonStuff.length != 0) {
					//Found lesson, adding...
					String teacherName = lessonStuff[3];
					Lesson lesson = new Lesson(lessonStuff[0],lessonStuff[1], Integer.parseInt(lessonStuff[2]) ,getTeacherByName(teacherName));
					lessonMap.put(lessonStuff[1], lesson);			
				}
			}
			fileScan.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return lessonMap;
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
