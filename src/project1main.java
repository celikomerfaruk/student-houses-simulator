

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;




public class project1main {

	public static void main(String[] args)throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner reader = new Scanner(new File(args[0]));
		reader.useLocale(Locale.US);
		PrintStream writer = new PrintStream(new File(args[1]));
		Operations operations = new Operations();		
		
		//Reading data and allocating it to the correct data structure.
		while (reader.hasNextLine()) {
			String type = reader.next();
			if(type.equals("h") ) {
				operations.addHouse(new House(reader.nextInt(), reader.nextInt(), reader.nextDouble()));
			}
			else {
				if(type.equals("s") ) {
				operations.addStudent(new Student(reader.nextInt(), reader.next(), reader.nextInt(), reader.nextDouble()));
			}}
			reader.nextLine();
			}
		
		//Sorting lists by id
		operations.sortHouses();
		operations.sortStudents();
		
		//necessary actions are taken
		operations.Operate();
		
		//printing output to file
		writer.print(operations.generateOutput());
		
		reader.close();
		writer.close();
		
		}
		
	}


