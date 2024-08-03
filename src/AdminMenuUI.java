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
import javax.swing.DefaultListModel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JSplitPane;

public class AdminMenuUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField studentTextField;
	private JPasswordField studentPasswordField;
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
	static DefaultListModel<String> enrolledLessonsModel = new DefaultListModel<>();
	static DefaultListModel<String> notEnrolledLessonsModel = new DefaultListModel<>();
	static JList enrolledLessons;
	static JList notEnrolledLessons;
	public AdminMenuUI() {
		setTitle("Teacher Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 460);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.inactiveCaption);
		tabbedPane.setBounds(10, 11, 834, 399);
		contentPane.add(tabbedPane);
		
		JPanel studentManagementPanel = new JPanel();
		studentManagementPanel.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Add / Remove Student", null, studentManagementPanel, null);
		tabbedPane.setBackgroundAt(0, SystemColor.inactiveCaption);
		studentManagementPanel.setLayout(null);
		
		JPanel studentAddPanel = new JPanel();
		studentAddPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		studentAddPanel.setBackground(SystemColor.inactiveCaptionBorder);
		studentAddPanel.setForeground(SystemColor.inactiveCaptionBorder);
		studentAddPanel.setBounds(10, 11, 394, 349);
		studentManagementPanel.add(studentAddPanel);
		studentAddPanel.setLayout(null);
		
		studentTextField = new JTextField();
		studentTextField.setColumns(10);
		studentTextField.setBounds(147, 108, 206, 27);
		studentAddPanel.add(studentTextField);
		
		studentPasswordField = new JPasswordField();
		studentPasswordField.setBounds(147, 146, 206, 27);
		studentAddPanel.add(studentPasswordField);
		
		JLabel passwordLabel = new JLabel("Password :");
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordLabel.setBounds(10, 136, 126, 44);
		studentAddPanel.add(passwordLabel);
		
		JLabel usernameLabel = new JLabel("Username :");
		usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usernameLabel.setBounds(10, 97, 126, 44);
		studentAddPanel.add(usernameLabel);
		
		JPanel studentRemovePanel = new JPanel();
		studentRemovePanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		studentRemovePanel.setBackground(SystemColor.inactiveCaptionBorder);
		studentRemovePanel.setBounds(425, 11, 394, 349);
		studentManagementPanel.add(studentRemovePanel);
		studentRemovePanel.setLayout(null);
		
		JComboBox studentNamesComboBox = new JComboBox();
		studentNamesComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		studentNamesComboBox.setBounds(216, 127, 168, 24);
		studentRemovePanel.add(studentNamesComboBox);
		updateStudentComboBox(studentNamesComboBox);
		
		
		JButton addStudentConfirmButton = new JButton("Confirm");
		addStudentConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Student add button clicked:
				HashMap<String, User> userMap = readUserFile();
				String usrName = studentTextField.getText().trim();
				String usrPass = studentPasswordField.getText().trim();
				
				
				if(usrName.length() == 0 || usrPass.length() == 0) {
					errorBox("Please fill in all the empty areas!","Empty Area Remaining!");
				}
				else if(userMap.containsKey(usrName)) {
					errorBox("There is an another user named \"" + usrName + "\" , Please enter an another username!" ,"Name Exists!");
				}
				else {
					addNewUserToFile(usrName, usrPass, "Student");
					updateStudentComboBox(studentNamesComboBox);
					infoBox("New student \"" + usrName + "\" added!", "Successful!");
				}
				
			}
		});
		addStudentConfirmButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addStudentConfirmButton.setBackground(UIManager.getColor("Button.light"));
		addStudentConfirmButton.setBounds(119, 215, 147, 44);
		studentAddPanel.add(addStudentConfirmButton);
		
		JLabel addStudentTitle = new JLabel("= Add A New Student =");
		addStudentTitle.setForeground(new Color(100, 149, 237));
		addStudentTitle.setHorizontalAlignment(SwingConstants.CENTER);
		addStudentTitle.setFont(new Font("Tahoma", Font.BOLD, 19));
		addStudentTitle.setBounds(10, 23, 374, 53);
		studentAddPanel.add(addStudentTitle);
		
		
		
		JLabel removeStudentTitle = new JLabel("= Remove Student =");
		removeStudentTitle.setBounds(10, 23, 374, 48);
		removeStudentTitle.setHorizontalAlignment(SwingConstants.CENTER);
		removeStudentTitle.setForeground(new Color(100, 149, 237));
		removeStudentTitle.setFont(new Font("Tahoma", Font.BOLD, 19));
		studentRemovePanel.add(removeStudentTitle);
		
		
		
		JLabel lblNewLabel = new JLabel("Select Student Name :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(10, 105, 196, 58);
		studentRemovePanel.add(lblNewLabel);
		
		JButton removeStudentConfirmButton = new JButton("Confirm");
		removeStudentConfirmButton.addActionListener(new ActionListener() {
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
					//TODO: make a function to update studentNameList and update it here.
				}
			}
		});
		removeStudentConfirmButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		removeStudentConfirmButton.setBackground(UIManager.getColor("Button.light"));
		removeStudentConfirmButton.setBounds(129, 215, 147, 44);
		studentRemovePanel.add(removeStudentConfirmButton);
		
		JPanel lessonManagementPanel = new JPanel();
		lessonManagementPanel.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Add / Remove Lessons", null, lessonManagementPanel, null);
		tabbedPane.setBackgroundAt(1, SystemColor.inactiveCaption);
		lessonManagementPanel.setLayout(null);
		
		JPanel lessonAddPanel = new JPanel();
		lessonAddPanel.setLayout(null);
		lessonAddPanel.setForeground(SystemColor.inactiveCaptionBorder);
		lessonAddPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lessonAddPanel.setBackground(SystemColor.inactiveCaptionBorder);
		lessonAddPanel.setBounds(10, 11, 394, 349);
		lessonManagementPanel.add(lessonAddPanel);
		
		lessonNameTextField = new JTextField();
		lessonNameTextField.setColumns(10);
		lessonNameTextField.setBounds(164, 109, 206, 27);
		lessonAddPanel.add(lessonNameTextField);
		
		JLabel lessonTeacherLabel = new JLabel("Lesson Teacher :");
		lessonTeacherLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lessonTeacherLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lessonTeacherLabel.setBounds(20, 135, 134, 44);
		lessonAddPanel.add(lessonTeacherLabel);
		
		JLabel lessonLabel_1 = new JLabel("Lesson Name :");
		lessonLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lessonLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lessonLabel_1.setBounds(20, 97, 134, 44);
		lessonAddPanel.add(lessonLabel_1);
		
		
		
		JLabel lblAddLessons = new JLabel("= Add A New Lesson =");
		lblAddLessons.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddLessons.setForeground(new Color(100, 149, 237));
		lblAddLessons.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblAddLessons.setBounds(10, 23, 374, 53);
		lessonAddPanel.add(lblAddLessons);
		
		lessonCreditsTextField = new JTextField();
		lessonCreditsTextField.setColumns(10);
		lessonCreditsTextField.setBounds(164, 182, 206, 27);
		lessonAddPanel.add(lessonCreditsTextField);
		
		JComboBox lessonTeacherNamesComboBox = new JComboBox();
		lessonTeacherNamesComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lessonTeacherNamesComboBox.setBounds(164, 147, 206, 24);
		lessonAddPanel.add(lessonTeacherNamesComboBox);
		updateTeacherComboBox(lessonTeacherNamesComboBox);
		
		JLabel lessonCreditLabel = new JLabel("Lesson Credit :");
		lessonCreditLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lessonCreditLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lessonCreditLabel.setBounds(20, 170, 134, 44);
		lessonAddPanel.add(lessonCreditLabel);
		
		JLabel lessonCodeLabel = new JLabel("Lesson Code :");
		lessonCodeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lessonCodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lessonCodeLabel.setBounds(20, 210, 134, 44);
		lessonAddPanel.add(lessonCodeLabel);
		
		lessonCodeTextField = new JTextField();
		lessonCodeTextField.setColumns(10);
		lessonCodeTextField.setBounds(164, 220, 206, 27);
		lessonAddPanel.add(lessonCodeTextField);
		
		JPanel lessonRemovePanel = new JPanel();
		lessonRemovePanel.setLayout(null);
		lessonRemovePanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lessonRemovePanel.setBackground(SystemColor.inactiveCaptionBorder);
		lessonRemovePanel.setBounds(425, 11, 394, 349);
		lessonManagementPanel.add(lessonRemovePanel);
		
		JComboBox lessonNamesComboBox = new JComboBox();
		lessonNamesComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lessonNamesComboBox.setBounds(203, 124, 168, 24);
		lessonRemovePanel.add(lessonNamesComboBox);
		updateLessonComboBox(lessonNamesComboBox);
		
		JLabel lblRemoveLesson = new JLabel("= Remove Lesson =");
		lblRemoveLesson.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoveLesson.setForeground(new Color(100, 149, 237));
		lblRemoveLesson.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblRemoveLesson.setBounds(10, 23, 374, 48);
		lessonRemovePanel.add(lblRemoveLesson);
		
		JLabel lblLessonRemove = new JLabel("Select Lesson Name :");
		lblLessonRemove.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLessonRemove.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLessonRemove.setBounds(0, 105, 196, 58);
		lessonRemovePanel.add(lblLessonRemove);
		
		JButton removeConfirmButton_Lessons = new JButton("Confirm");
		removeConfirmButton_Lessons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//When clicked Remove Lesson Confirm Button:
				if(lessonNamesComboBox.getSelectedIndex() < 0) {
					errorBox("Nothing selected!", "Error!");
				}
				else {
					String lessonName = lessonNamesComboBox.getSelectedItem().toString();
					
					deleteLessonFromFile(lessonName);

					infoBox("Deleted from file!", "Successful!");
					updateLessonComboBox(lessonNamesComboBox);
				}
				
			}
		});
		removeConfirmButton_Lessons.setFont(new Font("Tahoma", Font.PLAIN, 14));
		removeConfirmButton_Lessons.setBackground(UIManager.getColor("Button.light"));
		removeConfirmButton_Lessons.setBounds(129, 215, 147, 44);
		lessonRemovePanel.add(removeConfirmButton_Lessons);
		
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
				else if(lessonTeacherNamesComboBox.getSelectedIndex() < 0) {
					errorBox("Nothing selected as a teacher!", "Error!");
				}
				else if(lessonMap.containsKey(lessonName)) {
					errorBox("There is an another lesson named \"" + lessonName + "\", please enter an another lesson name!", "Name Exists!");
					lessonCodeTextField.setText("");
					lessonNameTextField.setText("");
					lessonCreditsTextField.setText("");
				}
				else if(doesLessonHashmapHasTheCode(lessonCode)) {
					errorBox("This code is the code of \"" + getLessonFromCode(lessonCode).getName() + "\". Please enter a different code.", "Code Exists!");
				}
				else {
					int lessonCredit = Integer.parseInt(lessonCreditsTextField.getText());
					String teacherName = lessonTeacherNamesComboBox.getSelectedItem().toString();
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
		lessonAddPanel.add(addConfirmButton_Lessons);
		
		JPanel enrollStudentsPanel = new JPanel();
		enrollStudentsPanel.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Enroll Students", null, enrollStudentsPanel, null);
		tabbedPane.setBackgroundAt(2, SystemColor.inactiveCaption);
		enrollStudentsPanel.setLayout(null);
		
		JPanel studentsListPanel = new JPanel();
		studentsListPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		studentsListPanel.setBackground(SystemColor.inactiveCaptionBorder);
		studentsListPanel.setBounds(10, 50, 163, 310);
		enrollStudentsPanel.add(studentsListPanel);
		studentsListPanel.setLayout(null);
		
		
		
		JPanel studentsLessonControlPanel = new JPanel();
		studentsLessonControlPanel.setBackground(SystemColor.inactiveCaptionBorder);
		studentsLessonControlPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		studentsLessonControlPanel.setBounds(183, 11, 636, 349);
		enrollStudentsPanel.add(studentsLessonControlPanel);
		studentsLessonControlPanel.setLayout(null);
		
		notEnrolledLessons = new JList<>( notEnrolledLessonsModel );
		notEnrolledLessons.setFont(new Font("Tahoma", Font.PLAIN, 19));
		notEnrolledLessons.setBackground(new Color(233, 240, 243));
		notEnrolledLessons.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		notEnrolledLessons.setBounds(61, 40, 196, 300);
		studentsLessonControlPanel.add(notEnrolledLessons);
		
		enrolledLessons = new JList<>( enrolledLessonsModel );
		enrolledLessons.setFont(new Font("Tahoma", Font.PLAIN, 19));
		enrolledLessons.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		enrolledLessons.setBackground(new Color(233, 240, 243));
		enrolledLessons.setBounds(358, 40, 196, 300);
		studentsLessonControlPanel.add(enrolledLessons);
		
		JList studentsNameList = new JList();
		
		JButton enrollLessonBtn = new JButton("►");
		enrollLessonBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//When enrollLessonBtn Clicked:
				String studentName = studentsNameList.getSelectedValue().toString();
				Student student = getStudentByName(studentName);
				String lessonName = notEnrolledLessons.getSelectedValue().toString();
				Lesson lesson = getLessonByName(lessonName);
				
				student.takeNewLesson(lesson);
				updateStudentLessonList(student);
			}
		});
		enrollLessonBtn.setEnabled(false);
		enrollLessonBtn.setBounds(285, 129, 44, 41);
		studentsLessonControlPanel.add(enrollLessonBtn);
		
		JButton dropLessonBtn = new JButton("◄");
		dropLessonBtn.setEnabled(false);
		dropLessonBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		dropLessonBtn.setBounds(285, 211, 44, 41);
		studentsLessonControlPanel.add(dropLessonBtn);
		
		
		studentsNameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		studentsNameList.setFont(new Font("Tahoma", Font.PLAIN, 21));
		studentsNameList.setBackground(SystemColor.inactiveCaptionBorder);
		studentsNameList.setModel(new AbstractListModel() {
			
			String[] values = getStudentNames();
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		studentsNameList.setBounds(10, 11, 143, 288);
		studentsListPanel.add(studentsNameList);
		studentsNameList.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent me) {
				 if(me.getClickCount() == 1) {
					 JList target = (JList)me.getSource();
		             int index = target.locationToIndex(me.getPoint());
		             if (index >= 0) {
		            	 //Valid option clicked.
		            	 Object item = target.getModel().getElementAt(index);
		                 //JOptionPane.showMessageDialog(null, item.toString());  <--------    use this to debug
		                 
		            	 
		                 updateStudentLessonList(getStudentByName(item.toString()));
		                 dropLessonBtn.setEnabled(true);
		                 enrollLessonBtn.setEnabled(true);
		                 
		             }
				 }
			 }

		});
		
		JLabel enrollLessonLabel = new JLabel("Enroll Lesson");
		enrollLessonLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		enrollLessonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		enrollLessonLabel.setBounds(267, 104, 80, 14);
		studentsLessonControlPanel.add(enrollLessonLabel);
		
		JLabel lblDropLesson = new JLabel("Drop Lesson");
		lblDropLesson.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDropLesson.setHorizontalAlignment(SwingConstants.CENTER);
		lblDropLesson.setBounds(267, 186, 80, 14);
		studentsLessonControlPanel.add(lblDropLesson);
		
		JLabel lblNotEnrolled = new JLabel("= Not Enrolled Lessons =");
		lblNotEnrolled.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotEnrolled.setForeground(new Color(100, 149, 237));
		lblNotEnrolled.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNotEnrolled.setBounds(61, 11, 196, 18);
		studentsLessonControlPanel.add(lblNotEnrolled);
		
		JLabel lblEnrolledLessons = new JLabel("= Enrolled Lessons =");
		lblEnrolledLessons.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnrolledLessons.setForeground(new Color(100, 149, 237));
		lblEnrolledLessons.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEnrolledLessons.setBounds(358, 11, 196, 18);
		studentsLessonControlPanel.add(lblEnrolledLessons);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(10, 11, 163, 28);
		enrollStudentsPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblStudents = new JLabel("= Students =");
		lblStudents.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudents.setForeground(new Color(100, 149, 237));
		lblStudents.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblStudents.setBounds(0, 0, 163, 28);
		panel.add(lblStudents);
		
		
		
	}
	
	//Returns a Teacher from the file with the given name
	protected Teacher getTeacherByName(String teacherName) {
		HashMap<String, User> userMap = readUserFile();
		for(String usrName : userMap.keySet()) {
			if(usrName.equals(teacherName)) {
				return (Teacher)userMap.get(usrName);
			}
		}
		return null;
	}

	//Returns a Student from the file with the given name
	protected Student getStudentByName(String studentName) {
		HashMap<String, User> userMap = readUserFile();
		for(String usrName : userMap.keySet()) {
			if(usrName.equals(studentName)) {
				return (Student)userMap.get(usrName);
			}
		}
		return null;
	}

	//Returns a Lesson from the file with the given name
	protected Lesson getLessonByName(String lessonName) {
		HashMap<String, Lesson> lessonMap = readLessonFile();
		for(String lsnName : lessonMap.keySet()) {
			if(lsnName.equals(lessonName)) {
				return (Lesson)lessonMap.get(lsnName);
			}
		}
		return null;
	}
	
	//Updates Student combo box inside adminMenuUI > Add&Remove Students > Remove Student 
	private void updateStudentComboBox(JComboBox studentNamesComboBox) {
		HashMap<String, User> userMap = readUserFile();
		studentNamesComboBox.removeAllItems();
		for(String usrName : userMap.keySet()) {
			if(userMap.get(usrName).getTag().equals("Student")) {
				studentNamesComboBox.addItem(usrName);
			}
		}
	}
	
	//Updates Teacher combo box inside adminMenuUI > Add&Remove Lessons  > Add Lesson 
	private void updateTeacherComboBox(JComboBox teacherNamesComboBox) {
		HashMap<String, User> userMap = readUserFile();
		teacherNamesComboBox.removeAllItems();
		for(String usrName : userMap.keySet()) {
			if(userMap.get(usrName).getTag().equals("Teacher")) {
				teacherNamesComboBox.addItem(usrName);
			}
		}
	}
	
	//Updates Lesson combo box inside  adminMenuUI > Add&Remove Lessons  > Remove Lesson 
	private void updateLessonComboBox(JComboBox lessonNamesComboBox) {
		HashMap<String, Lesson> lessonMap = readLessonFile();
		lessonNamesComboBox.removeAllItems();
		for(String lessonName : lessonMap.keySet()) {
			lessonNamesComboBox.addItem(lessonName);
		}
	}

	//Adds a new user to    "users.txt"
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
	 
	//Deletes a user from   "users.txt"
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
	
	//Adds a new lesson to  "lessons.txt"
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

	//Deletes a lesson from "lessons.txt"
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

	//Adds a new enrolled student with the lesson to "enrolledLessons.txt"
	protected static void addNewEnrolledCourseToFile(String username, String lessonName) {
		File enrolledFile = new File("src/enrolledLessons.txt");
		String lessonLine = username + "," + lessonName + "\n";
		if(!enrolledFile.exists()) {
			try {
				enrolledFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			FileWriter writer = new FileWriter("src/enrolledLessons.txt", true);
			writer.write(lessonLine);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Removes a enrolled lesson from "enrolledLessons.txt"
	protected void deleteEnrolledCourseFromFile(String username, String lessonName) {
		File oldFile = new File("src/enrolledLessons.txt"); 
		File newFile = new File("src/enrolledLessonsTEMP.txt");
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
				String[] enrollStuff = oldFileScan.nextLine().split(",");
				
				//if line is the line we want to remove, skip it.
				if(enrollStuff[0].equals(username) && enrollStuff[1].equals(lessonName)) {
					
				}
				//Write others onto new file
				else {
					try {
						String lessonLine = enrollStuff[0] + "," + enrollStuff[1] + "\n";
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
	
	//Returns a HashMap of User's name and Users from file     "users.txt"
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
	
	//Returns a HashMap of Lesson's name and Lessons from file "lessons.txt"
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
	
	//Returns a HashMap of user's name and the list of enrolled lesson's name from file "enrolledLessons.txt"
	protected static HashMap<String, ArrayList<String>> readEnrollFile(){
		HashMap<String, ArrayList<String>> enrollMap = new HashMap<>();
		File enrollFile = new File("src/enrolledLessons.txt");
		if(!enrollFile.exists()) {
			try {
				enrollFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		try {
			Scanner fileScan = new Scanner(enrollFile);
			while(fileScan.hasNextLine()) {
				String[] enrollStuff = fileScan.nextLine().split(",");
				if(!enrollMap.containsKey(enrollStuff[0])) {
					enrollMap.put(enrollStuff[0], new ArrayList<String>());
					enrollMap.get(enrollStuff[0]).add(enrollStuff[1]);
				}
				else {
					enrollMap.get(enrollStuff[0]).add(enrollStuff[1]);
				}
			}
			fileScan.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return enrollMap;
	}
	
	//Returns a String array of student names from file  	   "users.txt"
	protected String[] getStudentNames() {
		HashMap<String, User> userMap = readUserFile();
		ArrayList<String> names = new ArrayList<>();
		for(User user : userMap.values()) {
			if(user.getTag().equals("Student")) {
				names.add(user.getName());
			}
		}
		
		
		if(names.size() > 0) {
			String[] arr = new String[names.size()];
			for (int i = 0; i < names.size(); i++)
	            arr[i] = names.get(i);
			return arr;
		}
		else {
			String[] arr = new String[names.size()];
			return arr;
		}
		
	}
	
	//Returns True if "lessons.txt" has the given code
	protected boolean doesLessonHashmapHasTheCode(String code) {
		 HashMap<String, Lesson> lessonMap = readLessonFile();
		 for(Lesson lesson: lessonMap.values()) {
			 if(lesson.isTheSameCode(code)) {
				 return true;
			 }
		 }
		 return false;
	}
	
	//Returns the lesson which has the given code
	protected Lesson getLessonFromCode(String code) {
		 HashMap<String, Lesson> lessonMap = readLessonFile();
		 for(Lesson lesson: lessonMap.values()) {
			 if(lesson.isTheSameCode(code)) {
				 return lesson;
			 }
		 }
		 return null;
	}
	
	//Updates the Enrolled/notEnrolled JList of lessons inside adminMenuUI > Enroll Students 
	private void updateStudentLessonList(Student student) {
		//TODO: Auto-generated method stub
		HashMap<String, Lesson> lessonMap = readLessonFile();
		ArrayList<String> enrolledLessons = student.getTakenCourses();

		enrolledLessonsModel.removeAllElements();
		notEnrolledLessonsModel.removeAllElements();
		
		for(Lesson lesson : lessonMap.values()) {
			if(enrolledLessons.contains(lesson.getName())) {
				enrolledLessonsModel.addElement(lesson.getName());
			}
			else {
				notEnrolledLessonsModel.addElement(lesson.getName());
			}
		}
		
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
