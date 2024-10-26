import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JMenuBar;
import java.awt.List;
import java.awt.Scrollbar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import java.awt.Component;

public class NewAdminMenuUI<E> extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox teacherNamesForLessonComboBox;
	
	private JLabel amountOfStudentslbl;
	private JLabel amountOfTeacherslbl;
	private JLabel amountOfLessonslbl;
	private JLabel selectedStudentInfoLbl;
	
	static DefaultListModel<String> enrolledLessonsModel = new DefaultListModel<>();
	static DefaultListModel<String> notEnrolledLessonsModel = new DefaultListModel<>();
	static JList notEnrolledLessonList;
	static JList enrolledLessonList;
	static JList studentList_2;

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
		uiPanel.setBackground(new Color(250, 244, 255));
		uiPanel.setBounds(260, 0, 1004, 681);
		contentPane.add(uiPanel);
		uiPanel.setLayout(null);
		String[] studentArray = FuncManager.getStudentNames();
		Arrays.sort(studentArray);
		
		JPanel enrollMenuPanel = new JPanel();
		enrollMenuPanel.setBackground(new Color(250, 244, 255));
		enrollMenuPanel.setBounds(0, 0, 1004, 681);
		uiPanel.add(enrollMenuPanel);
		enrollMenuPanel.setLayout(null);
		enrollMenuPanel.setVisible(false);
		
		ButtonGradient enrollLessonButton = new ButtonGradient();
		enrollLessonButton.setEnabled(false);
		enrollLessonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Enroll button pressed:
				String studentName = studentList_2.getSelectedValue().toString();
				Student student = FuncManager.getStudentByName(studentName);
				if (notEnrolledLessonList.isSelectionEmpty()) {
					FuncManager.errorBox("There are no selected lesson to enroll!", "Error");
				} else {
					String lessonName = notEnrolledLessonList.getSelectedValue().toString();
					Lesson lesson = FuncManager.getLessonByName(lessonName);
					student.takeNewLesson(lesson);
					updateStudentLessonLists(student);
				}
			}
		});
		enrollLessonButton.setText("Enroll ->");
		enrollLessonButton.setStyleGradientColor2(new Color(244, 244, 247));
		enrollLessonButton.setStyleGradientColor1(new Color(133, 126, 143));
		enrollLessonButton.setStyle2Active(true);
		enrollLessonButton.setPressedShineColor(Color.LIGHT_GRAY);
		enrollLessonButton.setForeground(new Color(120, 120, 120));
		enrollLessonButton.setFont(new Font("Geist", Font.BOLD, 20));
		enrollLessonButton.setColor2(new Color(224, 224, 233));
		enrollLessonButton.setColor1(new Color(244, 244, 247));
		enrollLessonButton.setAlphaPressedDefault(0.1f);
		enrollLessonButton.setAlphaForHoveringLowest(0.0f);
		enrollLessonButton.setAlphaForHoveringChangeSpeed(0.3f);
		enrollLessonButton.setBounds(556, 297, 141, 50);
		enrollMenuPanel.add(enrollLessonButton);
		
		ButtonGradient dropLessonButton = new ButtonGradient();
		dropLessonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Drop Lesson pressed:
				String studentName = studentList_2.getSelectedValue().toString();
				Student student = FuncManager.getStudentByName(studentName);
				if (enrolledLessonList.isSelectionEmpty()) {
					FuncManager.errorBox("There are no selected lesson to drop!", "Error");
				} else {
					String lessonName = enrolledLessonList.getSelectedValue().toString();
					Lesson lesson = FuncManager.getLessonByName(lessonName);
					student.dropLesson(lesson);
					updateStudentLessonLists(student);
				}
			}
		});
		dropLessonButton.setEnabled(false);
		dropLessonButton.setText("Drop <-");
		dropLessonButton.setStyleGradientColor2(new Color(244, 244, 247));
		dropLessonButton.setStyleGradientColor1(new Color(133, 126, 143));
		dropLessonButton.setStyle2Active(true);
		dropLessonButton.setPressedShineColor(Color.LIGHT_GRAY);
		dropLessonButton.setForeground(new Color(120, 120, 120));
		dropLessonButton.setFont(new Font("Geist", Font.BOLD, 20));
		dropLessonButton.setColor2(new Color(244, 244, 247));
		dropLessonButton.setColor1(new Color(224, 224, 233));
		dropLessonButton.setAlphaPressedDefault(0.1f);
		dropLessonButton.setAlphaForHoveringLowest(0.0f);
		dropLessonButton.setAlphaForHoveringChangeSpeed(0.3f);
		dropLessonButton.setBounds(556, 370, 141, 50);
		enrollMenuPanel.add(dropLessonButton);
		
		JLabel lblSelectAStudent_1 = new JLabel("Select A Student To Enroll/Drop");
		lblSelectAStudent_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectAStudent_1.setForeground(new Color(95, 90, 103));
		lblSelectAStudent_1.setFont(new Font("Geist Medium", Font.BOLD, 32));
		lblSelectAStudent_1.setBounds(10, 51, 972, 51);
		enrollMenuPanel.add(lblSelectAStudent_1);
		
		JScrollPane studentScrollPane_2 = new JScrollPane();
		studentScrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		studentScrollPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		studentScrollPane_2.setBackground(new Color(133, 126, 143));
		studentScrollPane_2.setBounds(60, 170, 220, 380);
		enrollMenuPanel.add(studentScrollPane_2);
		
		//Making a background invisible JList:
		studentList_2 = new JList<>();
		studentList_2.setSelectionBackground(new Color(240, 240, 240));
		studentList_2.setDragEnabled(true);
		studentList_2.setForeground(new Color(100, 100, 100));
		studentScrollPane_2.setViewportView(studentList_2);
		studentList_2.setVisibleRowCount(2);
		studentList_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		studentList_2.setModel(new AbstractListModel() {
			String[] values = studentArray;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		studentList_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if(me.getClickCount() == 1) {
					JList target = (JList) me.getSource();
					int index = target.locationToIndex(me.getPoint());
					if(index >= 0) {
						//Valid option clicked
						Object item = target.getModel().getElementAt(index);
						
						updateStudentLessonLists(FuncManager.getStudentByName(item.toString()));
						dropLessonButton.setEnabled(true);
						enrollLessonButton.setEnabled(true);
					}
				}
			}
		
		});
		
		studentList_2.setToolTipText("");
		studentList_2.setBackground(new Color(249, 249, 249));
		studentList_2.setFont(new Font("Geist Medium", Font.PLAIN, 19));
		studentList_2.setBorder(null);
		
		
		JPanel fillerPanel1 = new JPanel();
		studentScrollPane_2.setRowHeaderView(fillerPanel1);
		fillerPanel1.setBackground(new Color(176, 172, 183));
		
		JPanel namePanel1 = new JPanel();
		studentScrollPane_2.setColumnHeaderView(namePanel1);
		namePanel1.setBackground(new Color(133, 126, 143));
		
		JLabel nameLabel1 = new JLabel("= Students =");
		nameLabel1.setForeground(new Color(248, 244, 251));
		nameLabel1.setFont(new Font("Geist Medium", Font.BOLD, 24));
		namePanel1.add(nameLabel1);
		
		JScrollPane notEnrolledLessonsScrollPane = new JScrollPane();
		notEnrolledLessonsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		notEnrolledLessonsScrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		notEnrolledLessonsScrollPane.setBackground(new Color(133, 126, 143));
		notEnrolledLessonsScrollPane.setBounds(326, 170, 220, 380);
		enrollMenuPanel.add(notEnrolledLessonsScrollPane);
		
		JPanel namePanel2 = new JPanel();
		notEnrolledLessonsScrollPane.setColumnHeaderView(namePanel2);
		namePanel2.setBackground(new Color(133, 126, 143));
		
		JLabel nameLabel2 = new JLabel("= Not Enrolled =");
		nameLabel2.setForeground(new Color(248, 244, 251));
		nameLabel2.setFont(new Font("Geist Medium", Font.PLAIN, 24));
		namePanel2.add(nameLabel2);
		
		notEnrolledLessonList = new JList<>(notEnrolledLessonsModel);
		notEnrolledLessonsScrollPane.setViewportView(notEnrolledLessonList);
		notEnrolledLessonList.setToolTipText("");
		notEnrolledLessonList.setBackground(new Color(249, 249, 249));
		notEnrolledLessonList.setFont(new Font("Geist Medium", Font.PLAIN, 19));
		notEnrolledLessonList.setBorder(null);
		
		JPanel fillerPanel2 = new JPanel();
		fillerPanel2.setBackground(new Color(176, 172, 183));
		notEnrolledLessonsScrollPane.setRowHeaderView(fillerPanel2);
		
		JScrollPane enrolledLessonsScrollPane = new JScrollPane();
		enrolledLessonsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		enrolledLessonsScrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		enrolledLessonsScrollPane.setBackground(new Color(133, 126, 143));
		enrolledLessonsScrollPane.setBounds(707, 170, 220, 380);
		enrollMenuPanel.add(enrolledLessonsScrollPane);
		
		JPanel namePanel3 = new JPanel();
		enrolledLessonsScrollPane.setColumnHeaderView(namePanel3);
		namePanel3.setBackground(new Color(133, 126, 143));
		
		JLabel nameLabel3 = new JLabel("= Enrolled =");
		nameLabel3.setForeground(new Color(248, 244, 251));
		nameLabel3.setFont(new Font("Geist Medium", Font.PLAIN, 24));
		namePanel3.add(nameLabel3);
		
		enrolledLessonList = new JList<>(enrolledLessonsModel);
		enrolledLessonsScrollPane.setViewportView(enrolledLessonList);
		enrolledLessonList.setToolTipText("");
		enrolledLessonList.setBackground(new Color(249, 249, 249));
		enrolledLessonList.setFont(new Font("Geist Medium", Font.PLAIN, 19));
		enrolledLessonList.setBorder(null);
		
		JPanel fillerPanel3 = new JPanel();
		fillerPanel3.setBackground(new Color(176, 172, 183));
		enrolledLessonsScrollPane.setRowHeaderView(fillerPanel3);
		
		JLabel adminUILeftMenuPanelBg_4 = new JLabel("New label");
		adminUILeftMenuPanelBg_4.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\AdminUIPanelBg2.png"));
		adminUILeftMenuPanelBg_4.setBackground(new Color(249, 249, 249));
		adminUILeftMenuPanelBg_4.setBounds(0, 0, 1004, 681);
		enrollMenuPanel.add(adminUILeftMenuPanelBg_4);
		
		JPanel allStudentsMenuPanel = new JPanel();
		allStudentsMenuPanel.setBackground(new Color(250, 244, 255));
		allStudentsMenuPanel.setBounds(0, 0, 1004, 681);
		uiPanel.add(allStudentsMenuPanel);
		allStudentsMenuPanel.setLayout(null);
		
		JLabel lblSelectAStudent = new JLabel("Select A Student To See Details");
		lblSelectAStudent.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectAStudent.setForeground(new Color(95, 90, 103));
		lblSelectAStudent.setFont(new Font("Geist Medium", Font.BOLD, 32));
		lblSelectAStudent.setBounds(10, 51, 972, 51);
		allStudentsMenuPanel.add(lblSelectAStudent);
		
		selectedStudentInfoLbl = new JLabel("\r\n");
		selectedStudentInfoLbl.setHorizontalAlignment(SwingConstants.LEFT);
		selectedStudentInfoLbl.setForeground(new Color(95, 90, 103));
		selectedStudentInfoLbl.setFont(new Font("Geist Medium", Font.BOLD, 22));
		selectedStudentInfoLbl.setBounds(404, 223, 491, 51);
		allStudentsMenuPanel.add(selectedStudentInfoLbl);
		
		JScrollPane studentScrollPane = new JScrollPane();
		studentScrollPane.setBackground(new Color(133, 126, 143));
		studentScrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		studentScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		studentScrollPane.setBounds(60, 170, 220, 380);
		allStudentsMenuPanel.add(studentScrollPane);
		
		//Making a background invisible JList:
		JList studentList = new JList<>();
		studentList.setSelectionBackground(new Color(240, 240, 240));
		studentList.setDragEnabled(true);
		studentList.setForeground(new Color(100, 100, 100));
		studentScrollPane.setViewportView(studentList);
		studentList.setVisibleRowCount(2);
		studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		studentList.setModel(new AbstractListModel() {
			String[] values = studentArray;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		studentList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if(me.getClickCount() == 1) {
					JList target = (JList) me.getSource();
					int index = target.locationToIndex(me.getPoint());
					if(index >= 0) {
						//Valid option clicked
						Object item = target.getModel().getElementAt(index);
						updateStudentInfo(item.toString());
					}
				}
			}

			
		});
		
		studentList.setToolTipText("");
		studentList.setBackground(new Color(249, 249, 249));
		studentList.setFont(new Font("Geist Medium", Font.PLAIN, 19));
		studentList.setBorder(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 172, 183));
		studentScrollPane.setRowHeaderView(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(133, 126, 143));
		studentScrollPane.setColumnHeaderView(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("= Students =");
		lblNewLabel_1.setForeground(new Color(248, 244, 251));
		lblNewLabel_1.setFont(new Font("Geist Medium", Font.BOLD, 24));
		panel_1.add(lblNewLabel_1);
		//Resizing to the max size so every member of the list to be shown
		int studentListSize = studentList.getModel().getSize();
		
			
			JLabel adminUILeftMenuPanelBg_4_1 = new JLabel("New label");
			adminUILeftMenuPanelBg_4_1.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\AdminUIPanelBg2.png"));
			adminUILeftMenuPanelBg_4_1.setBackground(new Color(249, 249, 249));
			adminUILeftMenuPanelBg_4_1.setBounds(0, 0, 1004, 681);
			allStudentsMenuPanel.add(adminUILeftMenuPanelBg_4_1);
		
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
		
		JLabel lblStudentsInSchool = new JLabel("• Students in school                      : ");
		lblStudentsInSchool.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentsInSchool.setForeground(new Color(128, 121, 138));
		lblStudentsInSchool.setFont(new Font("Geist Medium", Font.BOLD, 21));
		lblStudentsInSchool.setBounds(100, 150, 330, 50);
		mainMenuPanel.add(lblStudentsInSchool);
		
		amountOfStudentslbl = new JLabel("0");
		amountOfStudentslbl.setHorizontalAlignment(SwingConstants.LEFT);
		amountOfStudentslbl.setForeground(new Color(159, 154, 167));
		amountOfStudentslbl.setFont(new Font("Geist Medium", Font.BOLD, 26));
		amountOfStudentslbl.setBounds(440, 150, 240, 50);
		mainMenuPanel.add(amountOfStudentslbl);
		
		JLabel lblTeachersInSchool = new JLabel("• \r\nTeachers in school                      : ");
		lblTeachersInSchool.setHorizontalAlignment(SwingConstants.LEFT);
		lblTeachersInSchool.setForeground(new Color(128, 121, 138));
		lblTeachersInSchool.setFont(new Font("Geist Medium", Font.BOLD, 21));
		lblTeachersInSchool.setBounds(100, 220, 330, 50);
		mainMenuPanel.add(lblTeachersInSchool);
		
		amountOfTeacherslbl = new JLabel("0");
		amountOfTeacherslbl.setHorizontalAlignment(SwingConstants.LEFT);
		amountOfTeacherslbl.setForeground(new Color(159, 154, 167));
		amountOfTeacherslbl.setFont(new Font("Geist Medium", Font.BOLD, 26));
		amountOfTeacherslbl.setBounds(440, 220, 240, 50);
		mainMenuPanel.add(amountOfTeacherslbl);
		
		JLabel lblAmountOfLessons = new JLabel("• Amount of lessons in school   : ");
		lblAmountOfLessons.setHorizontalAlignment(SwingConstants.LEFT);
		lblAmountOfLessons.setForeground(new Color(128, 121, 138));
		lblAmountOfLessons.setFont(new Font("Geist Medium", Font.BOLD, 21));
		lblAmountOfLessons.setBounds(100, 293, 330, 50);
		mainMenuPanel.add(lblAmountOfLessons);
		
		amountOfLessonslbl = new JLabel("0");
		amountOfLessonslbl.setHorizontalAlignment(SwingConstants.LEFT);
		amountOfLessonslbl.setForeground(new Color(159, 154, 167));
		amountOfLessonslbl.setFont(new Font("Geist Medium", Font.BOLD, 26));
		amountOfLessonslbl.setBounds(440, 293, 240, 50);
		mainMenuPanel.add(amountOfLessonslbl);
		
		JLabel adminUILeftMenuPanelBg = new JLabel("New label");
		adminUILeftMenuPanelBg.setBackground(new Color(249, 249, 249));
		adminUILeftMenuPanelBg.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\AdminUIPanelBg2.png"));
		adminUILeftMenuPanelBg.setBounds(0, 0, 1004, 681);
		mainMenuPanel.add(adminUILeftMenuPanelBg);
		
		updateLabelsForMainMenu();
		
		JPanel studentMenuPanel = new JPanel();
		studentMenuPanel.setBackground(new Color(250, 244, 255));
		studentMenuPanel.setBounds(0, 0, 1004, 681);
		uiPanel.add(studentMenuPanel);
		studentMenuPanel.setLayout(null);
		studentMenuPanel.setVisible(false);
		
		TextField studentNameField = new TextField();
		studentNameField.setHintLabelFont(new Font("Geist Medium", Font.PLAIN, 17));
		studentNameField.setForeground(new Color(90, 90, 90));
		studentNameField.setFont(new Font("Geist", Font.PLAIN, 17));
		studentNameField.setLineColor(new Color(154, 188, 252));
		studentNameField.setHintLabelColor(new Color(150, 150, 150));
		studentNameField.setHintLabelText("Student's Name\r\n");
		studentNameField.setBounds(140, 252, 220, 50);
		studentMenuPanel.add(studentNameField);
		studentNameField.setColumns(10);
		
		TextField studentPassField = new TextField();
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
		updateStudentComboBox(studentNamesComboBox);
		
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
					
					updateStudentComboBox(studentNamesComboBox);
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
		removeStudentButton.addActionListener(new ActionListener() {
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
					//updateEnrollingStudentList();
					// TODO: make a function to update studentNameList and update it here.
				}
			}
		});
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
		
		TextField teacherNameField = new TextField();
		teacherNameField.setLineColor(new Color(154, 188, 252));
		teacherNameField.setHintLabelText("Teacher's Name\r\n");
		teacherNameField.setHintLabelFont(new Font("Geist Medium", Font.PLAIN, 17));
		teacherNameField.setHintLabelColor(new Color(150, 150, 150));
		teacherNameField.setForeground(new Color(90, 90, 90));
		teacherNameField.setFont(new Font("Geist", Font.PLAIN, 17));
		teacherNameField.setColumns(10);
		teacherNameField.setBounds(140, 252, 220, 50);
		teacherMenuPanel.add(teacherNameField);
		
		TextField teacherPassField = new TextField();
		teacherPassField.setLineColor(new Color(154, 188, 252));
		teacherPassField.setHintLabelText("Teacher's Password");
		teacherPassField.setHintLabelFont(new Font("Geist Medium", Font.PLAIN, 17));
		teacherPassField.setHintLabelColor(new Color(150, 150, 150));
		teacherPassField.setForeground(new Color(90, 90, 90));
		teacherPassField.setFont(new Font("Geist", Font.PLAIN, 17));
		teacherPassField.setColumns(10);
		teacherPassField.setBounds(140, 331, 220, 50);
		teacherMenuPanel.add(teacherPassField);
		
		JComboBox teacherNamesComboBox = new JComboBox();
		teacherNamesComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		teacherNamesComboBox.setBounds(733, 317, 206, 27);
		teacherMenuPanel.add(teacherNamesComboBox);
		updateTeacherComboBox(teacherNamesComboBox);
		
		ButtonGradient addTeacherButton = new ButtonGradient();
		addTeacherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Teacher add button clicked:
				HashMap<String, User> userMap = FuncManager.readUserFile();
				String usrName = teacherNameField.getText().trim();
				String usrPass = teacherPassField.getText().trim();

				teacherNameField.setText("");
				teacherPassField.setText("");

				if (usrName.length() == 0 || usrPass.length() == 0) {
					FuncManager.errorBox("Please fill in all the empty areas!", "Empty Area Remaining!");
				} else if (userMap.containsKey(usrName)) {
					FuncManager.errorBox("There is an another user named \"" + usrName + "\" , Please enter an another username!",
							"Name Exists!");
				} else {
					try {
						FuncManager.addNewUserToFile(usrName, usrPass, "Teacher");
					} catch (UnsupportedTypeException e1) {
						e1.printStackTrace();
					}
					//TODO: After implementing other stuff, make sure to uncomment these!!!!
					
					updateTeacherComboBox(teacherNamesComboBox);
					updateTeacherComboBox(teacherNamesForLessonComboBox);
					//updateEnrollingStudentList();
					FuncManager.infoBox("New teacher \"" + usrName + "\" added!", "Successful!");
				}
			}
		});
		addTeacherButton.setText("Add!");
		addTeacherButton.setSizeSpeed(18.0f);
		addTeacherButton.setFont(new Font("Geist Medium", Font.BOLD, 23));
		addTeacherButton.setColor2(new Color(223, 188, 233));
		addTeacherButton.setColor1(new Color(135, 181, 241));
		addTeacherButton.setBounds(177, 432, 160, 50);
		teacherMenuPanel.add(addTeacherButton);
		
		ButtonGradient removeTeacherButton = new ButtonGradient();
		removeTeacherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Teacher remove button clicked:

				if (teacherNamesComboBox.getSelectedIndex() < 0) {
					FuncManager.errorBox("Nothing selected!", "Error!");
				} else {
					String teacherName = teacherNamesComboBox.getSelectedItem().toString();
					FuncManager.deleteAllLessonsFromTeacher(teacherName);
					FuncManager.deleteAllStudentDataFromEnrolledCourses(teacherName);
					FuncManager.deleteUserFromFile(teacherName);

					FuncManager.infoBox("Deleted from file!", "Successful!");
					updateTeacherComboBox(teacherNamesComboBox);
					updateTeacherComboBox(teacherNamesForLessonComboBox);
					//updateEnrollingStudentList();
					// TODO: make a function to update studentNameList and update it here.
				}
			}
		});
		removeTeacherButton.setText("Remove!");
		removeTeacherButton.setSizeSpeed(18.0f);
		removeTeacherButton.setFont(new Font("Geist Medium", Font.BOLD, 23));
		removeTeacherButton.setColor2(new Color(223, 188, 233));
		removeTeacherButton.setColor1(new Color(135, 181, 241));
		removeTeacherButton.setBounds(660, 432, 160, 50);
		teacherMenuPanel.add(removeTeacherButton);
		
		JLabel selectTeacherNameLabel = new JLabel("Select a Teacher :");
		selectTeacherNameLabel.setForeground(new Color(150, 150, 150));
		selectTeacherNameLabel.setFont(new Font("Geist Medium", Font.PLAIN, 20));
		selectTeacherNameLabel.setBounds(506, 300, 220, 58);
		teacherMenuPanel.add(selectTeacherNameLabel);
		
		JLabel lblAddA = new JLabel("= Add A New Teacher =                    = Remove A Teacher =");
		lblAddA.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddA.setForeground(new Color(120, 120, 120));
		lblAddA.setFont(new Font("Geist Medium", Font.BOLD, 30));
		lblAddA.setBounds(10, 120, 972, 75);
		teacherMenuPanel.add(lblAddA);
		
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
		
		TextField lessonNameField = new TextField();
		lessonNameField.setLineColor(new Color(154, 188, 252));
		lessonNameField.setHintLabelText("Lesson Name\r\n");
		lessonNameField.setHintLabelFont(new Font("Geist Medium", Font.PLAIN, 17));
		lessonNameField.setHintLabelColor(new Color(150, 150, 150));
		lessonNameField.setForeground(new Color(90, 90, 90));
		lessonNameField.setFont(new Font("Geist", Font.PLAIN, 17));
		lessonNameField.setColumns(10);
		lessonNameField.setBounds(154, 206, 220, 50);
		lessonMenuPanel.add(lessonNameField);
		
		TextField lessonCreditField = new TextField();
		lessonCreditField.setLineColor(new Color(154, 188, 252));
		lessonCreditField.setHintLabelText("Lesson Credit");
		lessonCreditField.setHintLabelFont(new Font("Geist Medium", Font.PLAIN, 17));
		lessonCreditField.setHintLabelColor(new Color(150, 150, 150));
		lessonCreditField.setForeground(new Color(90, 90, 90));
		lessonCreditField.setFont(new Font("Geist", Font.PLAIN, 17));
		lessonCreditField.setColumns(10);
		lessonCreditField.setBounds(154, 272, 220, 50);
		lessonMenuPanel.add(lessonCreditField);
		
		TextField lessonCodeField = new TextField();
		lessonCodeField.setLineColor(new Color(154, 188, 252));
		lessonCodeField.setHintLabelText("Lesson Code");
		lessonCodeField.setHintLabelFont(new Font("Geist Medium", Font.PLAIN, 17));
		lessonCodeField.setHintLabelColor(new Color(150, 150, 150));
		lessonCodeField.setForeground(new Color(90, 90, 90));
		lessonCodeField.setFont(new Font("Geist", Font.PLAIN, 17));
		lessonCodeField.setColumns(10);
		lessonCodeField.setBounds(154, 337, 220, 50);
		lessonMenuPanel.add(lessonCodeField);
		
		JComboBox teacherNamesForLessonComboBox = new JComboBox();
		teacherNamesForLessonComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		teacherNamesForLessonComboBox.setBounds(255, 423, 206, 27);
		lessonMenuPanel.add(teacherNamesForLessonComboBox);
		updateTeacherComboBox(teacherNamesForLessonComboBox);
		
		JComboBox lessonNamesComboBox = new JComboBox();
		lessonNamesComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lessonNamesComboBox.setBounds(732, 285, 206, 27);
		lessonMenuPanel.add(lessonNamesComboBox);
		updateLessonComboBox(lessonNamesComboBox);
		
		ButtonGradient addLessonButton = new ButtonGradient();
		addLessonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When clicked Add Lesson Confirm Button:
				HashMap<String, Lesson> lessonMap = FuncManager.readLessonFile();

				String lessonCode = lessonCodeField.getText();
				String lessonName = lessonNameField.getText();
				Teacher lessonTeacher;

				if (lessonCreditField.getText().length() == 0 || lessonCode.length() == 0
						|| lessonName.length() == 0) {
					FuncManager.errorBox("Please fill in all the empty areas!", "Empty Area Remaining!");
				} else if (teacherNamesForLessonComboBox.getSelectedIndex() < 0) {
					FuncManager.errorBox("Nothing selected as a teacher!", "Error!");
				} else if (lessonMap.containsKey(lessonName)) {
					FuncManager.errorBox("There is an another lesson named \"" + lessonName
							+ "\", please enter an another lesson name!", "Name Exists!");
				} else if (FuncManager.doesLessonFileHasTheCode(lessonCode)) {
					FuncManager.errorBox("This code is the code of \"" + FuncManager.getLessonByCode(lessonCode).getName()
							+ "\". Please enter a different code.", "Code Exists!");
				} else {
					int lessonCredit = Integer.parseInt(lessonCreditField.getText());
					String teacherName = teacherNamesForLessonComboBox.getSelectedItem().toString();
					lessonTeacher = FuncManager.getTeacherByName(teacherName);
					FuncManager.addNewLessonToFile(lessonCode, lessonName, lessonCredit, lessonTeacher);
					updateLessonComboBox(lessonNamesComboBox);

					FuncManager.infoBox("New lesson\"" + lessonName + "\" added!", "Successfull!");

					lessonCodeField.setText("");
					lessonNameField.setText("");
					lessonCreditField.setText("");
				}
				//if(!studentsNameList.isSelectionEmpty()){
				//	String studentName = studentsNameList.getSelectedValue().toString();
				//	updateStudentLessonList(FuncManager.getStudentByName(studentName));
				//}
			}
		});
		addLessonButton.setText("Add!");
		addLessonButton.setSizeSpeed(18.0f);
		addLessonButton.setFont(new Font("Geist Medium", Font.BOLD, 23));
		addLessonButton.setColor2(new Color(223, 188, 233));
		addLessonButton.setColor1(new Color(135, 181, 241));
		addLessonButton.setBounds(184, 501, 160, 50);
		lessonMenuPanel.add(addLessonButton);
		
		ButtonGradient removeLessonButton = new ButtonGradient();
		removeLessonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When clicked Remove Lesson Confirm Button:
				if (lessonNamesComboBox.getSelectedIndex() < 0) {
					FuncManager.errorBox("Nothing selected!", "Error!");
				} else {
					String lessonName = lessonNamesComboBox.getSelectedItem().toString();

					FuncManager.deleteLessonFromFile(lessonName);

					FuncManager.infoBox("Deleted from file!", "Successful!");
					updateLessonComboBox(lessonNamesComboBox);
					
					//if(!studentsNameList.isSelectionEmpty()){
					//	String studentName = studentsNameList.getSelectedValue().toString();
					//	updateStudentLessonList(FuncManager.getStudentByName(studentName));
					//}
				}
			}
		});
		removeLessonButton.setText("Remove!");
		removeLessonButton.setSizeSpeed(18.0f);
		removeLessonButton.setFont(new Font("Geist Medium", Font.BOLD, 23));
		removeLessonButton.setColor2(new Color(223, 188, 233));
		removeLessonButton.setColor1(new Color(135, 181, 241));
		removeLessonButton.setBounds(659, 400, 160, 50);
		lessonMenuPanel.add(removeLessonButton);
		
		JLabel lblAddA_1 = new JLabel("= Add A New Lesson =                    = Remove A Lesson =");
		lblAddA_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddA_1.setForeground(new Color(120, 120, 120));
		lblAddA_1.setFont(new Font("Geist Medium", Font.BOLD, 30));
		lblAddA_1.setBounds(10, 120, 972, 75);
		lessonMenuPanel.add(lblAddA_1);
		
		JLabel selectTeacherLabel = new JLabel("Select a Teacher  :");
		selectTeacherLabel.setForeground(new Color(150, 150, 150));
		selectTeacherLabel.setFont(new Font("Geist Medium", Font.PLAIN, 20));
		selectTeacherLabel.setBounds(60, 406, 220, 58);
		lessonMenuPanel.add(selectTeacherLabel);
		
		JLabel lblSelectALesson = new JLabel("Select a Lesson:");
		lblSelectALesson.setForeground(new Color(150, 150, 150));
		lblSelectALesson.setFont(new Font("Geist Medium", Font.PLAIN, 20));
		lblSelectALesson.setBounds(533, 268, 220, 58);
		lessonMenuPanel.add(lblSelectALesson);
		
		JLabel adminUILeftMenuPanelBg_3 = new JLabel("New label");
		adminUILeftMenuPanelBg_3.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\AdminUIPanelBg2.png"));
		adminUILeftMenuPanelBg_3.setBackground(new Color(249, 249, 249));
		adminUILeftMenuPanelBg_3.setBounds(0, 0, 1004, 681);
		lessonMenuPanel.add(adminUILeftMenuPanelBg_3);
		
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
				mainMenuPanel.setVisible(true);
				studentMenuPanel.setVisible(false);
				teacherMenuPanel.setVisible(false);
				lessonMenuPanel.setVisible(false);
				enrollMenuPanel.setVisible(false);
				allStudentsMenuPanel.setVisible(false);

				updateLabelsForMainMenu();
				selectedStudentInfoLbl.setText("");
			}
		});
		
		mainMenuButton.setFont(new Font("Geist", Font.BOLD, 15));
		mainMenuButton.setForeground(new Color(120, 120, 120));
		mainMenuButton.setText("Main Menu");
		mainMenuButton.setColor2(new Color(252, 249, 255));
		mainMenuButton.setColor1(new Color(252, 249, 255));
		mainMenuButton.setBounds(75, 160, 150, 50);
		navBarPanel.add(mainMenuButton);
		
		JLabel mainMenuIconLabel = new JLabel("");
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
				allStudentsMenuPanel.setVisible(false);

				selectedStudentInfoLbl.setText("");
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
		
		studentManagementMenuButton.setBounds(75, 215, 150, 50);
		navBarPanel.add(studentManagementMenuButton);
		
		JLabel studentMenuIcon = new JLabel("");
		studentMenuIcon.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\studentIcon.png"));
		studentMenuIcon.setBounds(45, 227, 25, 25);
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
				allStudentsMenuPanel.setVisible(false);

				selectedStudentInfoLbl.setText("");
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
		teacherManagementMenuButton.setBounds(75, 270, 150, 50);
		navBarPanel.add(teacherManagementMenuButton);
		
		JLabel teacherMenuIcon = new JLabel("");
		teacherMenuIcon.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\teacherIcon.png"));
		teacherMenuIcon.setBounds(45, 282, 25, 25);
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
				allStudentsMenuPanel.setVisible(false);

				selectedStudentInfoLbl.setText("");
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
		lessonManagementMenuButton.setBounds(75, 325, 150, 50);
		navBarPanel.add(lessonManagementMenuButton);
		
		JLabel lessonMenuIcon = new JLabel("");
		lessonMenuIcon.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\lessonIcon.png"));
		lessonMenuIcon.setBounds(45, 337, 25, 25);
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
				allStudentsMenuPanel.setVisible(false);

				selectedStudentInfoLbl.setText("");
				updateStudentList(studentList_2);
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
		enrollMenuButton.setBounds(75, 380, 150, 50);
		navBarPanel.add(enrollMenuButton);
		
		JLabel enrollMenuIcon = new JLabel("");
		enrollMenuIcon.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\enrollmentIcon.png"));
		enrollMenuIcon.setBounds(45, 392, 25, 25);
		navBarPanel.add(enrollMenuIcon);
		
		ButtonGradient allStudentsMenuButton = new ButtonGradient();
		allStudentsMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Clicked All Students Menu Button
				mainMenuPanel.setVisible(false);
				studentMenuPanel.setVisible(false);
				teacherMenuPanel.setVisible(false);
				lessonMenuPanel.setVisible(false);
				enrollMenuPanel.setVisible(false);
				allStudentsMenuPanel.setVisible(true);
				
				updateStudentList(studentList);
			}
		});
		allStudentsMenuButton.setText("All Students");
		allStudentsMenuButton.setStyleGradientColor2(new Color(180, 180, 180));
		allStudentsMenuButton.setStyleGradientColor1(new Color(180, 180, 180));
		allStudentsMenuButton.setStyle2Active(true);
		allStudentsMenuButton.setPressedShineColor(Color.LIGHT_GRAY);
		allStudentsMenuButton.setHorizontalAlignment(SwingConstants.LEFT);
		allStudentsMenuButton.setForeground(new Color(120, 120, 120));
		allStudentsMenuButton.setFont(new Font("Geist", Font.BOLD, 15));
		allStudentsMenuButton.setColor2(new Color(252, 249, 255));
		allStudentsMenuButton.setColor1(new Color(252, 249, 255));
		allStudentsMenuButton.setAlphaPressedDefault(0.1f);
		allStudentsMenuButton.setAlphaForHoveringLowest(0.0f);
		allStudentsMenuButton.setAlphaForHoveringChangeSpeed(0.3f);
		allStudentsMenuButton.setBounds(75, 435, 150, 50);
		navBarPanel.add(allStudentsMenuButton);
		
		JLabel allStudentsMenuIcon = new JLabel("");
		allStudentsMenuIcon.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\allStudentsIcon.png"));
		allStudentsMenuIcon.setBounds(45, 447, 25, 25);
		navBarPanel.add(allStudentsMenuIcon);
		
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
		
		JLabel logoutIcon = new JLabel("");
		logoutIcon.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\logoutIcon.png"));
		logoutIcon.setBounds(45, 582, 25, 25);
		navBarPanel.add(logoutIcon);
		
		JLabel schoolIcon = new JLabel("");
		schoolIcon.setHorizontalAlignment(SwingConstants.CENTER);
		schoolIcon.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\schoolLogoBig.png"));
		schoolIcon.setBounds(0, 0, 260, 149);
		navBarPanel.add(schoolIcon);
		
		JLabel adminUIMenuPanelBg = new JLabel("");
		adminUIMenuPanelBg.setBackground(new Color(250, 255, 255));
		adminUIMenuPanelBg.setIcon(new ImageIcon("C:\\Users\\ayberk\\eclipse-workspace\\SMS\\content\\AdminUIMenuPanelBg2.png"));
		adminUIMenuPanelBg.setBounds(0, 0, 260, 681);
		navBarPanel.add(adminUIMenuPanelBg);
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
		
		// Updates labels in the adminMenuUI > Main Menu
		private void updateLabelsForMainMenu() {
			amountOfStudentslbl.setText(String.valueOf(FuncManager.getStudentCount()));
			amountOfTeacherslbl.setText(String.valueOf(FuncManager.getTeacherCount()));
			amountOfLessonslbl.setText(String.valueOf(FuncManager.getLessonCount()));
		}
		
		// Updates JList in the adminMenuUI > All Students Menu
		private void updateStudentList(JList studentList) {
			String[] studentArray = FuncManager.getStudentNames();
			Arrays.sort(studentArray);
			
			studentList.setModel(new AbstractListModel() {
				String[] values = studentArray;
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});
		}
		
		// Updates JLists in adminMenuUI > Enroll Menu according to the student
		private void updateStudentLessonLists(Student student) {
			// TODO Auto-generated method stub
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
		
		// Updates labels in the adminMenuUI > All Students Menu
		private void updateStudentInfo(String string) {
			Student student = FuncManager.getStudentByName(string);
			selectedStudentInfoLbl.setText(student.getName() + ", " + student.getID() + " :");
		}
}
