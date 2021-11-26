

public class House {
	private int id;
	private int duration;
	private Double rating;
	public House(int id,int duration,Double rating) {
		this.id = id;
		this.duration = duration;
		this.rating = rating;
	}
	
	

	public int getId() {
		return id;
	}

	public Double getRating() {
		return rating;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	

}
