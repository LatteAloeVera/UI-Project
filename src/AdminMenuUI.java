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
import java.awt.Toolkit;

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
	static DefaultListModel<String> studentsNameListModel = new DefaultListModel<>();
	static JList studentsNameList;
	static JList enrolledLessons;
	static JList notEnrolledLessons;
	private JTextField textField;
	private JPasswordField passwordField;

	public AdminMenuUI() {
		setTitle("Admin Menu");
		setIconImage(Toolkit.getDefaultToolkit().getImage("content/adminPanelIco.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 175, 1280, 720);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(252, 249, 255));
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setFont(new Font("Geist Medium", Font.PLAIN, 13));
		tabbedPane.setBackground(SystemColor.inactiveCaption);
		tabbedPane.setBounds(0, 0, 1264, 681);
		contentPane.add(tabbedPane);

		JPanel studentManagementPanel = new JPanel();
		studentManagementPanel.setBackground(new Color(252, 249, 255));
		tabbedPane.addTab("Add / Remove Student", null, studentManagementPanel, null);
		tabbedPane.setBackgroundAt(0, SystemColor.inactiveCaption);
		studentManagementPanel.setLayout(null);

		JPanel studentAddPanel = new JPanel();
		studentAddPanel.setBorder(null);
		studentAddPanel.setBackground(new Color(252, 249, 255));
		studentAddPanel.setForeground(new Color(252, 249, 255));
		studentAddPanel.setBounds(10, 11, 615, 631);
		studentManagementPanel.add(studentAddPanel);
		studentAddPanel.setLayout(null);

		studentTextField = new JTextField();
		studentTextField.setColumns(10);
		studentTextField.setBounds(294, 219, 206, 27);
		studentAddPanel.add(studentTextField);

		studentPasswordField = new JPasswordField();
		studentPasswordField.setBounds(294, 273, 206, 27);
		studentAddPanel.add(studentPasswordField);

		JLabel passwordLabel = new JLabel("Password :");
		passwordLabel.setForeground(new Color(93, 93, 93));
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setFont(new Font("Geist Medium", Font.PLAIN, 20));
		passwordLabel.setBounds(157, 263, 126, 44);
		studentAddPanel.add(passwordLabel);

		JLabel nameLabel = new JLabel("Student Name :");
		nameLabel.setForeground(new Color(93, 93, 93));
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setFont(new Font("Geist Medium", Font.PLAIN, 20));
		nameLabel.setBounds(116, 208, 167, 44);
		studentAddPanel.add(nameLabel);
		
		JPanel studentRemovePanel = new JPanel();
		studentRemovePanel.setBounds(634, 11, 615, 631);
		studentManagementPanel.add(studentRemovePanel);
		studentRemovePanel.setBorder(null);
		studentRemovePanel.setBackground(new Color(252, 249, 255));
		studentRemovePanel.setLayout(null);
		
		JComboBox studentNamesComboBox = new JComboBox();
		studentNamesComboBox.setBounds(320, 235, 206, 27);
		studentRemovePanel.add(studentNamesComboBox);
		studentNamesComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		updateStudentComboBox(studentNamesComboBox);

		ButtonGradient addStudentConfirmButton = new ButtonGradient();
		addStudentConfirmButton.setText("Add Student");
		addStudentConfirmButton.setFont(new Font("Geist Medium", Font.BOLD, 22));
		addStudentConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Student add button clicked:
				HashMap<String, User> userMap = FuncManager.readUserFile();
				String usrName = studentTextField.getText().trim();
				String usrPass = studentPasswordField.getText().trim();

				studentTextField.setText("");
				studentPasswordField.setText("");

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
					updateStudentComboBox(studentNamesComboBox);
					updateEnrollingStudentList();
					FuncManager.infoBox("New student \"" + usrName + "\" added!", "Successful!");
				}

			}
		});
		addStudentConfirmButton.setBackground(UIManager.getColor("Button.light"));
		addStudentConfirmButton.setBounds(194, 353, 223, 61);
		addStudentConfirmButton.setFont(new Font("Geist Medium", Font.BOLD, 22));	
		studentAddPanel.add(addStudentConfirmButton);

		JLabel addStudentTitle = new JLabel("= Add A New Student =");
		addStudentTitle.setForeground(Color.DARK_GRAY);
		addStudentTitle.setHorizontalAlignment(SwingConstants.CENTER);
		addStudentTitle.setFont(new Font("Geist Medium", Font.BOLD, 34));
		addStudentTitle.setBounds(0, 99, 615, 61);
		studentAddPanel.add(addStudentTitle);
		
		
		
		
		JLabel removeStudentTitle = new JLabel("= Remove Student =");
		removeStudentTitle.setBounds(0, 99, 615, 58);
		removeStudentTitle.setHorizontalAlignment(SwingConstants.CENTER);
		removeStudentTitle.setForeground(Color.DARK_GRAY);
		removeStudentTitle.setFont(new Font("Geist Medium", Font.BOLD, 34));
		studentRemovePanel.add(removeStudentTitle);
								
		JLabel lblNewLabel = new JLabel("Select Student Name :");
		lblNewLabel.setForeground(new Color(93, 93, 93));
		lblNewLabel.setFont(new Font("Geist Medium", Font.PLAIN, 20));
		lblNewLabel.setBounds(87, 218, 217, 58);
		studentRemovePanel.add(lblNewLabel);
		
		ButtonGradient removeStudentConfirmButton = new ButtonGradient();
		removeStudentConfirmButton.setBounds(208, 341, 223, 61);
		studentRemovePanel.add(removeStudentConfirmButton);
		removeStudentConfirmButton.setFont(new Font("Geist Medium", Font.BOLD, 22));
		removeStudentConfirmButton.setText("Remove Student");
		removeStudentConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Student remove button clicked:

				if (studentNamesComboBox.getSelectedIndex() < 0) {
					FuncManager.errorBox("Nothing selected!", "Error!");
				} else {
					String studentName = studentNamesComboBox.getSelectedItem().toString();
					FuncManager.deleteAllStudentDataFromEnrolledCourses(studentName);
					FuncManager.deleteUserFromFile(studentName);

					FuncManager.infoBox("Deleted from file!", "Successful!");
					updateStudentComboBox(studentNamesComboBox);
					updateEnrollingStudentList();
					// TODO: make a function to update studentNameList and update it here.
				}
			}
		});
		removeStudentConfirmButton.setBackground(UIManager.getColor("Button.light"));
		
		
		

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
				// When clicked Remove Lesson Confirm Button:
				if (lessonNamesComboBox.getSelectedIndex() < 0) {
					FuncManager.errorBox("Nothing selected!", "Error!");
				} else {
					String lessonName = lessonNamesComboBox.getSelectedItem().toString();

					FuncManager.deleteLessonFromFile(lessonName);

					FuncManager.infoBox("Deleted from file!", "Successful!");
					updateLessonComboBox(lessonNamesComboBox);
					
					FuncManager.deleteAllLessonDataFromEnrolledCourses(lessonName);
					
					if(!studentsNameList.isSelectionEmpty()){
						String studentName = studentsNameList.getSelectedValue().toString();
						updateStudentLessonList(FuncManager.getStudentByName(studentName));
					}
					
					
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
				// When clicked Add Lesson Confirm Button:
				HashMap<String, Lesson> lessonMap = FuncManager.readLessonFile();

				String lessonCode = lessonCodeTextField.getText();
				String lessonName = lessonNameTextField.getText();
				Teacher lessonTeacher;

				if (lessonCreditsTextField.getText().length() == 0 || lessonCode.length() == 0
						|| lessonName.length() == 0) {
					FuncManager.errorBox("Please fill in all the empty areas!", "Empty Area Remaining!");
				} else if (lessonTeacherNamesComboBox.getSelectedIndex() < 0) {
					FuncManager.errorBox("Nothing selected as a teacher!", "Error!");
				} else if (lessonMap.containsKey(lessonName)) {
					FuncManager.errorBox("There is an another lesson named \"" + lessonName
							+ "\", please enter an another lesson name!", "Name Exists!");
					lessonCodeTextField.setText("");
					lessonNameTextField.setText("");
					lessonCreditsTextField.setText("");
				} else if (FuncManager.doesLessonFileHasTheCode(lessonCode)) {
					FuncManager.errorBox("This code is the code of \"" + FuncManager.getLessonByCode(lessonCode).getName()
							+ "\". Please enter a different code.", "Code Exists!");
				} else {
					int lessonCredit = Integer.parseInt(lessonCreditsTextField.getText());
					String teacherName = lessonTeacherNamesComboBox.getSelectedItem().toString();
					lessonTeacher = FuncManager.getTeacherByName(teacherName);
					FuncManager.addNewLessonToFile(lessonCode, lessonName, lessonCredit, lessonTeacher);
					updateLessonComboBox(lessonNamesComboBox);

					FuncManager.infoBox("New lesson\"" + lessonName + "\" added!", "Successfull!");

					lessonCodeTextField.setText("");
					lessonNameTextField.setText("");
					lessonCreditsTextField.setText("");
				}
				if(!studentsNameList.isSelectionEmpty()){
					String studentName = studentsNameList.getSelectedValue().toString();
					updateStudentLessonList(FuncManager.getStudentByName(studentName));
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

		notEnrolledLessons = new JList<>(notEnrolledLessonsModel);
		notEnrolledLessons.setFont(new Font("Tahoma", Font.PLAIN, 19));
		notEnrolledLessons.setBackground(new Color(233, 240, 243));
		notEnrolledLessons.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		notEnrolledLessons.setBounds(61, 40, 196, 300);
		studentsLessonControlPanel.add(notEnrolledLessons);

		enrolledLessons = new JList<>(enrolledLessonsModel);
		enrolledLessons.setFont(new Font("Tahoma", Font.PLAIN, 19));
		enrolledLessons.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		enrolledLessons.setBackground(new Color(233, 240, 243));
		enrolledLessons.setBounds(358, 40, 196, 300);
		studentsLessonControlPanel.add(enrolledLessons);

		studentsNameList = new JList<>(studentsNameListModel);

		JButton enrollLessonBtn = new JButton("►");
		enrollLessonBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When enrollLessonBtn Clicked:
				String studentName = studentsNameList.getSelectedValue().toString();
				Student student = FuncManager.getStudentByName(studentName);
				if (notEnrolledLessons.isSelectionEmpty()) {
					FuncManager.errorBox("There are no selected lesson to enroll!", "Error");
				} else {
					String lessonName = notEnrolledLessons.getSelectedValue().toString();
					Lesson lesson = FuncManager.getLessonByName(lessonName);
					student.takeNewLesson(lesson);
					updateStudentLessonList(student);
				}
			}
		});
		enrollLessonBtn.setEnabled(false);
		enrollLessonBtn.setBounds(285, 129, 44, 41);
		studentsLessonControlPanel.add(enrollLessonBtn);

		JButton dropLessonBtn = new JButton("◄");
		dropLessonBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Pressed drop lesson button
				String studentName = studentsNameList.getSelectedValue().toString();
				Student student = FuncManager.getStudentByName(studentName);
				if (enrolledLessons.isSelectionEmpty()) {
					FuncManager.errorBox("There are no selected lesson to drop!", "Error");
				} else {
					String lessonName = enrolledLessons.getSelectedValue().toString();
					Lesson lesson = FuncManager.getLessonByName(lessonName);
					student.dropLesson(lesson);
					updateStudentLessonList(student);
				}
			}
		});
		dropLessonBtn.setEnabled(false);
		dropLessonBtn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		dropLessonBtn.setBounds(285, 211, 44, 41);
		studentsLessonControlPanel.add(dropLessonBtn);

		studentsNameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		studentsNameList.setFont(new Font("Tahoma", Font.PLAIN, 21));
		studentsNameList.setBackground(SystemColor.inactiveCaptionBorder);
		studentsNameList.setModel(new AbstractListModel() {

			String[] values = FuncManager.getStudentNames();

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
				if (me.getClickCount() == 1) {
					JList target = (JList) me.getSource();
					int index = target.locationToIndex(me.getPoint());
					if (index >= 0) {
						// Valid option clicked.
						Object item = target.getModel().getElementAt(index);
						// JOptionPane.showMessageDialog(null, item.toString()); <-------- use this to
						// debug

						updateStudentLessonList(FuncManager.getStudentByName(item.toString()));
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
		
		JPanel teacherManagementPanel = new JPanel();
		teacherManagementPanel.setLayout(null);
		teacherManagementPanel.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("New tab", null, teacherManagementPanel, null);
		
		JPanel teacherAddPanel = new JPanel();
		teacherAddPanel.setLayout(null);
		teacherAddPanel.setForeground(SystemColor.inactiveCaptionBorder);
		teacherAddPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		teacherAddPanel.setBackground(SystemColor.inactiveCaptionBorder);
		teacherAddPanel.setBounds(10, 11, 394, 349);
		teacherManagementPanel.add(teacherAddPanel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(147, 108, 206, 27);
		teacherAddPanel.add(textField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(147, 146, 206, 27);
		teacherAddPanel.add(passwordField);
		
		JLabel passwordLabel_1 = new JLabel("Password :");
		passwordLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordLabel_1.setBounds(10, 136, 126, 44);
		teacherAddPanel.add(passwordLabel_1);
		
		JLabel usernameLabel_1 = new JLabel("Username :");
		usernameLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		usernameLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usernameLabel_1.setBounds(10, 97, 126, 44);
		teacherAddPanel.add(usernameLabel_1);
		
		JButton addStudentConfirmButton_1 = new JButton("Confirm");
		addStudentConfirmButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addStudentConfirmButton_1.setBackground(UIManager.getColor("Button.light"));
		addStudentConfirmButton_1.setBounds(119, 215, 147, 44);
		teacherAddPanel.add(addStudentConfirmButton_1);
		
		JLabel addStudentTitle_1 = new JLabel("= Add A New Student =");
		addStudentTitle_1.setHorizontalAlignment(SwingConstants.CENTER);
		addStudentTitle_1.setForeground(new Color(100, 149, 237));
		addStudentTitle_1.setFont(new Font("Tahoma", Font.BOLD, 19));
		addStudentTitle_1.setBounds(10, 23, 374, 53);
		teacherAddPanel.add(addStudentTitle_1);
		
		JPanel studentRemovePanel_1 = new JPanel();
		studentRemovePanel_1.setLayout(null);
		studentRemovePanel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		studentRemovePanel_1.setBackground(SystemColor.inactiveCaptionBorder);
		studentRemovePanel_1.setBounds(425, 11, 394, 349);
		teacherManagementPanel.add(studentRemovePanel_1);
		
		JComboBox studentNamesComboBox_1 = new JComboBox();
		studentNamesComboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		studentNamesComboBox_1.setBounds(216, 127, 168, 24);
		studentRemovePanel_1.add(studentNamesComboBox_1);
		
		JLabel removeStudentTitle_1 = new JLabel("= Remove Student =");
		removeStudentTitle_1.setHorizontalAlignment(SwingConstants.CENTER);
		removeStudentTitle_1.setForeground(new Color(100, 149, 237));
		removeStudentTitle_1.setFont(new Font("Tahoma", Font.BOLD, 19));
		removeStudentTitle_1.setBounds(10, 23, 374, 48);
		studentRemovePanel_1.add(removeStudentTitle_1);
		
		JLabel lblNewLabel_1 = new JLabel("Select Student Name :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(10, 105, 196, 58);
		studentRemovePanel_1.add(lblNewLabel_1);
		
		JButton removeStudentConfirmButton_1 = new JButton("Confirm");
		removeStudentConfirmButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		removeStudentConfirmButton_1.setBackground(UIManager.getColor("Button.light"));
		removeStudentConfirmButton_1.setBounds(129, 215, 147, 44);
		studentRemovePanel_1.add(removeStudentConfirmButton_1);

	}
	

	// Updates the Enrolled/notEnrolled JList of lessons inside adminMenuUI > Enroll Students
	private void updateStudentLessonList(Student student) {
		// TODO: Auto-generated method stub
		HashMap<String, Lesson> lessonMap = FuncManager.readLessonFile();
		ArrayList<String> enrolledLessons = student.getTakenCourses();

		enrolledLessonsModel.removeAllElements();
		notEnrolledLessonsModel.removeAllElements();

		for (Lesson lesson : lessonMap.values()) {
			if (enrolledLessons.contains(lesson.getName())) {
				enrolledLessonsModel.addElement(lesson.getName());
			} else {
				notEnrolledLessonsModel.addElement(lesson.getName());
			}
		}

	}

	// Updates the Enrolling Students JList of students inside adminMenuUI > Enroll Students
	private void updateEnrollingStudentList() {
		HashMap<String, User> userMap = FuncManager.readUserFile();
		studentsNameListModel.removeAllElements();

		for (User user : userMap.values()) {
			if (user.getTag().equals("Student")) {
				studentsNameListModel.addElement(user.getName());
			}
		}

		studentsNameList.setModel(studentsNameListModel);
	}
	

	// Updates Student combo box inside adminMenuUI > Add&Remove Students > Remove Student
	private void updateStudentComboBox(JComboBox studentNamesComboBox) {
		HashMap<String, User> userMap = FuncManager.readUserFile();
		studentNamesComboBox.removeAllItems();
		for (String usrName : userMap.keySet()) {
			if (userMap.get(usrName).getTag().equals("Student")) {
				studentNamesComboBox.addItem(usrName);
			}
		}
	}

	// Updates Teacher combo box inside adminMenuUI > Add&Remove Lessons > Add Lesson
	private void updateTeacherComboBox(JComboBox teacherNamesComboBox) {
		HashMap<String, User> userMap = FuncManager.readUserFile();
		teacherNamesComboBox.removeAllItems();
		for (String usrName : userMap.keySet()) {
			if (userMap.get(usrName).getTag().equals("Teacher")) {
				teacherNamesComboBox.addItem(usrName);
			}
		}
	}

	// Updates Lesson combo box inside adminMenuUI > Add&Remove Lessons > Remove Lesson
	private void updateLessonComboBox(JComboBox lessonNamesComboBox) {
		HashMap<String, Lesson> lessonMap = FuncManager.readLessonFile();
		lessonNamesComboBox.removeAllItems();
		for (String lessonName : lessonMap.keySet()) {
			lessonNamesComboBox.addItem(lessonName);
		}
	}



}
