public class Lesson {

	private String code;
	private String name;
	private int credit;
	private Teacher teacher;
	
	public Lesson(String code, String name, int credit, Teacher teacher) {
		this.code = code;
		this.name = name;
		this.credit = credit;
		this.teacher = teacher;
	}
	
	protected String getName() {
		return this.name;
	}
	
	protected String getCode() {
		return this.code;
	}
	
	protected int getCredit() {
		return this.credit;
	}
	
	protected Teacher getTeacher() {
		return this.teacher;
	}
	
	
}
