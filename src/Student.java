import java.util.HashMap;

public class Student extends User {
	private HashMap<String, Lesson> takenLessons;

	Student(String username, String password) {
		super(username, password,"Student");
		this.takenLessons = new HashMap<String, Lesson>();
	}
	
	protected HashMap<String, Lesson> getTakenCourses(){
		return this.takenLessons;
	}

	protected void takeNewLesson(Lesson lesson) {
		this.takenLessons.put(lesson.getName(), lesson);
		
		//TODO: implement writing to file and delete the top part (HashMap<String, Lesson> takenLessons) . it will not work that way.
	}
	
}
