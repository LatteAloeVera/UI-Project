import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class FuncManager {

	//						--------------------------------------------------------------
	//						================== FILE MANAGEMENT FUNCTIONS ==================
	//						--------------------------------------------------------------
	
	
	// Returns a HashMap of User's name and Users from file "users.txt"
	protected static HashMap<String, User> readUserFile() {
		HashMap<String, User> userMap = new HashMap<String, User>();
		File userFile = new File("src/users.txt");
		if (!userFile.exists()) {
			try {
				userFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			Scanner fileScan = new Scanner(userFile);
			while (fileScan.hasNextLine()) {
				String[] userStuff = fileScan.nextLine().split(",");
				if (userStuff.length != 0) {
					if (userStuff[2].compareTo("Teacher") == 0) {
						Teacher teacher = new Teacher(userStuff[0], userStuff[1]);
						userMap.put(userStuff[0], teacher);
					} else {
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

	// Returns a HashMap of Lesson's name and Lessons from file "lessons.txt"
	protected static HashMap<String, Lesson> readLessonFile() {
		HashMap<String, Lesson> lessonMap = new HashMap<String, Lesson>();
		File lessonFile = new File("src/lessons.txt");
		if (!lessonFile.exists()) {
			try {
				lessonFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			Scanner fileScan = new Scanner(lessonFile);
			while (fileScan.hasNextLine()) {
				String[] lessonStuff = fileScan.nextLine().split(",");
				if (lessonStuff.length != 0) {
					// Found lesson, adding...
					String teacherName = lessonStuff[3];
					Lesson lesson = new Lesson(lessonStuff[0], lessonStuff[1], Integer.parseInt(lessonStuff[2]),
							getTeacherByName(teacherName));
					lessonMap.put(lessonStuff[1], lesson);
				}
			}
			fileScan.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return lessonMap;
	}

	// Returns a HashMap of user's name and the list of enrolled lesson's name from file "enrolledLessons.txt"
	protected static HashMap<String, ArrayList<String>> readEnrollFile() {
		HashMap<String, ArrayList<String>> enrollMap = new HashMap<>();
		File enrollFile = new File("src/enrolledLessons.txt");
		if (!enrollFile.exists()) {
			try {
				enrollFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			Scanner fileScan = new Scanner(enrollFile);
			while (fileScan.hasNextLine()) {
				String[] enrollStuff = fileScan.nextLine().split(",");
				if (!enrollMap.containsKey(enrollStuff[0])) {
					enrollMap.put(enrollStuff[0], new ArrayList<String>());
					enrollMap.get(enrollStuff[0]).add(enrollStuff[1]);
				} else {
					enrollMap.get(enrollStuff[0]).add(enrollStuff[1]);
				}
			}
			fileScan.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return enrollMap;
	}

	// Adds a new user to "users.txt"
	protected static void addNewUserToFile(String username, String password, String tag) {
		File userFile = new File("src/users.txt");
		//TODO: ADD ID TO THE LINE.
		String userLine = username + "," + password + "," + tag + "\n";
		if (!userFile.exists()) {
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

	// Deletes a user from "users.txt"
	protected static void deleteUserFromFile(String usrName) {
		File oldFile = new File("src/users.txt");
		File newFile = new File("src/usersTEMP.txt");
		try {
			newFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (!oldFile.exists()) {
			try {
				oldFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			Scanner oldFileScan = new Scanner(oldFile);
			while (oldFileScan.hasNextLine()) {
				String[] userStuff = oldFileScan.nextLine().split(",");

				// if line is the line we want to remove, skip it.
				if (userStuff[0].equals(usrName)) {

				}
				// Write others onto new file
				else {
					try {
						String userLine = userStuff[0] + "," + userStuff[1] + "," + userStuff[2] + "\n";
						//TODO: ADD ID TO THE TEMP FILE.
						FileWriter newWriter = new FileWriter("src/usersTEMP.txt", true);
						newWriter.write(userLine);
						newWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
			oldFileScan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Delete old file
		oldFile.delete();

		// rename temp file
		newFile.renameTo(oldFile);

	}

	// Adds a new lesson to "lessons.txt"
	protected static void addNewLessonToFile(String code, String name, int credit, Teacher teacher) {
		File lessonFile = new File("src/lessons.txt");
		String lessonLine = code + "," + name + "," + credit + "," + teacher.getName() + "\n";
		if (!lessonFile.exists()) {
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

	// Deletes a lesson from "lessons.txt"
	protected static void deleteLessonFromFile(String lessonName) {
		File oldFile = new File("src/lessons.txt");
		File newFile = new File("src/lessonsTEMP.txt");
		try {
			newFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (!oldFile.exists()) {
			try {
				oldFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			Scanner oldFileScan = new Scanner(oldFile);
			while (oldFileScan.hasNextLine()) {
				String[] lessonStuff = oldFileScan.nextLine().split(",");

				// if line is the line we want to remove, skip it.
				if (lessonStuff[1].equals(lessonName)) {

				}
				// Write others onto new file
				else {
					try {
						String lessonLine = lessonStuff[0] + "," + lessonStuff[1] + "," + lessonStuff[2] + ","
								+ lessonStuff[3] + "\n";
						FileWriter newWriter = new FileWriter("src/lessonsTEMP.txt", true);
						newWriter.write(lessonLine);
						newWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
			oldFileScan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Delete old file
		oldFile.delete();

		// rename temp file
		newFile.renameTo(oldFile);
	}

	// Adds a new enrolled student with the lesson to "enrolledLessons.txt"
	protected static void addNewEnrolledCourseToFile(String username, String lessonName) {
		File enrolledFile = new File("src/enrolledLessons.txt");
		String lessonLine = username + "," + lessonName + "\n";
		if (!enrolledFile.exists()) {
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

	// Removes a enrolled lesson from "enrolledLessons.txt"
	protected static void deleteEnrolledCourseFromFile(String username, String lessonName) {
		File oldFile = new File("src/enrolledLessons.txt");
		File newFile = new File("src/enrolledLessonsTEMP.txt");
		try {
			newFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (!oldFile.exists()) {
			try {
				oldFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			Scanner oldFileScan = new Scanner(oldFile);
			while (oldFileScan.hasNextLine()) {
				String[] enrollStuff = oldFileScan.nextLine().split(",");

				// if line is the line we want to remove, skip it.
				if (enrollStuff[0].equals(username) && enrollStuff[1].equals(lessonName)) {

				}
				// Write others onto new file
				else {
					try {
						String lessonLine = enrollStuff[0] + "," + enrollStuff[1] + "\n";
						FileWriter newWriter = new FileWriter("src/enrolledLessonsTEMP.txt", true);
						newWriter.write(lessonLine);
						newWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
			oldFileScan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Delete old file
		oldFile.delete();

		// rename temp file
		newFile.renameTo(oldFile);
	}

	// Deletes all of the enrolled lessons of a student from file "enrolledLessons.txt"
	protected static void deleteAllStudentDataFromEnrolledCourses(String studentName) {
		HashMap<String, ArrayList<String>> enrollMap = readEnrollFile();

		if(enrollMap.get(studentName) != null) {
			for (String lessonName : enrollMap.get(studentName)) {
				deleteEnrolledCourseFromFile(studentName, lessonName);
			}
		}
	}
	
	// Deletes all of the enrolled students of a lesson from file "enrolledLessons.txt"
	protected static void deleteAllLessonDataFromEnrolledCourses(String lessonName) {
		File oldFile = new File("src/enrolledLessons.txt");
		File newFile = new File("src/enrolledLessonsTEMP.txt");
		try {
			newFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (!oldFile.exists()) {
			try {
				oldFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			Scanner oldFileScan = new Scanner(oldFile);
			while (oldFileScan.hasNextLine()) {
				String[] enrollStuff = oldFileScan.nextLine().split(",");

				// if line is the line we want to remove, skip it.
				if (enrollStuff[1].equals(lessonName)) {

				}
				// Write others onto new file
				else {
					try {
						String lessonLine = enrollStuff[0] + "," + enrollStuff[1] + "\n";
						FileWriter newWriter = new FileWriter("src/enrolledLessonsTEMP.txt", true);
						newWriter.write(lessonLine);
						newWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
			oldFileScan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Delete old file
		oldFile.delete();

		// rename temp file
		newFile.renameTo(oldFile);
	}

	
	
	//						--------------------------------------------------------------
	//						================== FILE OPERATION FUNCTIONS ==================
	//						--------------------------------------------------------------
	
	
	// Returns a Teacher from the file with the given name
	protected static Teacher getTeacherByName(String teacherName) {
		HashMap<String, User> userMap = readUserFile();
		for (String usrName : userMap.keySet()) {
			if (usrName.equals(teacherName)) {
				return (Teacher) userMap.get(usrName);
			}
		}
		return null;
	}

	// Returns a Student from the file with the given name
	protected static Student getStudentByName(String studentName) {
		HashMap<String, User> userMap = readUserFile();
		for (String usrName : userMap.keySet()) {
			if (usrName.equals(studentName)) {
				return (Student) userMap.get(usrName);
			}
		}
		return null;
	}

	// Returns a Lesson from the file with the given name
	protected static Lesson getLessonByName(String lessonName) {
		HashMap<String, Lesson> lessonMap = readLessonFile();
		for (String lsnName : lessonMap.keySet()) {
			if (lsnName.equals(lessonName)) {
				return (Lesson) lessonMap.get(lsnName);
			}
		}
		return null;
	}
	
	// Returns a String array of student names from file "users.txt"
	protected static String[] getStudentNames() {
		HashMap<String, User> userMap = readUserFile();
		ArrayList<String> names = new ArrayList<>();
		for (User user : userMap.values()) {
			if (user.getTag().equals("Student")) {
				names.add(user.getName());
			}
		}

		if (names.size() > 0) {
			String[] arr = new String[names.size()];
			for (int i = 0; i < names.size(); i++)
				arr[i] = names.get(i);
			return arr;
		} else {
			String[] arr = new String[names.size()];
			return arr;
		}

	}

	// Returns True if "lessons.txt" has the given code
	protected static boolean doesLessonFileHasTheCode(String code) {
		HashMap<String, Lesson> lessonMap = readLessonFile();
		for (Lesson lesson : lessonMap.values()) {
			if (lesson.isTheSameCode(code)) {
				return true;
			}
		}
		return false;
	}

	// Returns the lesson which has the given code
	protected static Lesson getLessonByCode(String code) {
		HashMap<String, Lesson> lessonMap = readLessonFile();
		for (Lesson lesson : lessonMap.values()) {
			if (lesson.isTheSameCode(code)) {
				return lesson;
			}
		}
		return null;
	}
	
	
	
	//						--------------------------------------------------------------
	//						==================   OTHER TYPE FUNCTIONS   ==================
	//						--------------------------------------------------------------

	
	// Makes a pop-up infoBox with wanted message & title
	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	// Makes a pop-up errorBox with wanted message & title
	public static void errorBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.ERROR_MESSAGE);
	}
	
	
}
