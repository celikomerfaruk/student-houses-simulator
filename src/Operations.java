


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Operations {
	private ArrayList<Student> waitingStudents ;
	private PriorityQueue<Student> homelessGraduatedStudents ;
	private ArrayList<House> availableHouses;
	private PriorityQueue<House>  unavailableHouses;
	private PriorityQueue<Student> ungraduatedStudents;
	
	
	public Operations(){
		waitingStudents = new ArrayList<Student>();
		availableHouses = new ArrayList<House>();
		homelessGraduatedStudents = new PriorityQueue<Student>(new StudentsComparator());
		unavailableHouses = new PriorityQueue<>(new UnavailableHouseComparator());
		ungraduatedStudents = new PriorityQueue<>(new UngraduateStudentsComparator());
		
	}
	public void addStudent(Student student) {
		if (student.getDuration() == 0) {
			homelessGraduatedStudents.add(student);
			
		}else {
			waitingStudents.add(student);
			ungraduatedStudents.add(student);
		}
		
	}
	public void  addHouse(House house) {
		if(house.getDuration() == 0 )
		availableHouses.add(house);else {
			unavailableHouses.add(house);
		}
		
	}
	//The function where necessary actions are taken at the end of each semester.
	public void Operate() {
		int passedSemester = 0;
		while(!waitingStudents.isEmpty()) {
		Iterator<House> houseIterator = availableHouses.iterator();
		
		House tempHouse;
		Student tempStudent;
		while(houseIterator.hasNext()) {
			tempHouse = houseIterator.next();
			Iterator<Student> studentIterator = waitingStudents.iterator();
			while(studentIterator.hasNext()) {
				tempStudent = studentIterator.next();
				if(tempHouse.getRating() >= tempStudent.getRating()) {
			//		enterHouse(tempStudent, tempHouse);
					tempHouse.setDuration(tempStudent.getDuration());
					studentIterator.remove();
					ungraduatedStudents.remove(tempStudent);
					unavailableHouses.add(tempHouse);
					houseIterator.remove();
					break;
				}
			}
		}
		
		passedSemester++;
		refreshHouses(passedSemester);
		sortHouses();
		refreshStudents(passedSemester);
		}
	}
	
	
	
	public void sortHouses() {
		availableHouses.sort(new HousesComparator());
	}
	public void sortStudents() {
		waitingStudents.sort(new StudentsComparator());
	}
	//The condition of the students is checked at the end of each semester.
	private void refreshStudents(int passedSemester) {
		
		Student tmpStudent;
		while(!ungraduatedStudents.isEmpty()) {
			
			if(ungraduatedStudents.peek().getDuration() ==passedSemester) {
				tmpStudent = ungraduatedStudents.poll();
				waitingStudents.remove(tmpStudent);
				homelessGraduatedStudents.add(tmpStudent);
			}else {
				return;
			}
			
		}
		
	}
	//The condition of the houses is checked at the end of each semester.
	private void refreshHouses(int passedSemester) {
		
		while (!unavailableHouses.isEmpty()) {
			
			if(unavailableHouses.peek().getDuration()==passedSemester) {
				
				availableHouses.add(unavailableHouses.poll());
			}
			else {
				return;
			}
		}
	}
	
	public String generateOutput() {
		String output = "";
		
		while (!homelessGraduatedStudents.isEmpty()) {
			output+=homelessGraduatedStudents.poll().getName()+ "\n";
			
		}
		return output;
	}
}

class UnavailableHouseComparator implements Comparator<House>{

	
	public int compare(House h1 , House h2) {
		// TODO Auto-generated method stub
		return h1.getDuration() - h2.getDuration();
	}

	
	
}
class HousesComparator implements Comparator<House>{
	public int compare(House h1 , House h2) {
		
		return h1.getId() - h2.getId();
	}
}
class StudentsComparator implements Comparator<Student>{
	public int compare(Student s1 , Student s2) {
		
		return s1.getId() - s2.getId();
	}
}
class UngraduateStudentsComparator implements Comparator<Student>{
	public int compare(Student s1 , Student s2) {
		
		return s1.getDuration() - s2.getDuration();
	}
}
