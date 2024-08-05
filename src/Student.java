import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Student extends User {
	private ArrayList<String> takenLessons;

	Student(String username, String password) {
		super(username, password,"Student");
		
		HashMap<String, ArrayList<String>> enrollMap = FuncManager.readEnrollFile();
		if(enrollMap.containsKey(username)) {
			this.takenLessons = enrollMap.get(username);
		}
		else {
			this.takenLessons = new ArrayList<String>();
		}
		
		
	}
	
	protected ArrayList<String> getTakenCourses(){
		return this.takenLessons;
	}

	protected void takeNewLesson(Lesson lesson) {
		this.takenLessons.add(lesson.getName());
		FuncManager.addNewEnrolledCourseToFile(this.getName(), lesson.getName());

	}

	protected void dropLesson(Lesson lesson) {
		this.takenLessons.remove(lesson.getName());
		FuncManager.deleteEnrolledCourseFromFile(this.getName(), lesson.getName());
		
	}
	
	
	
}
