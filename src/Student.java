

public class Student  {
	private int id;
	private int duration;
	private Double rating;
	private String name;
	public Student(int id,String name ,int duration ,Double rating) {
		this.id = id;
		this.duration = duration;
		this.rating = rating;
		this.name = name;
	}
	
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getId() {
		return id;
	}
	public Double getRating() {
		return rating;
	}
	public String getName() {
		return name;
	}
	
	

}
