import java.util.HashMap;

public class Student extends User {
	private HashMap<String, Lesson> takenCourses;

	Student(String username, String password) {
		super(username, password,"Student");
		this.takenCourses = new HashMap<String, Lesson>();
	}

}
