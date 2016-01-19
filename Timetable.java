
public class Timetable {

	//instance variables
	private Module [] programme;
	private final int SIZE = 24;
	private int moduleCount;
	
	public Timetable(){
		// default constructor
		programme = new Module[SIZE];
		moduleCount = 0;
	}
	
	/**
	 * method to create and add a module to the timetable
	 * @param line is a string containing a line read from the ModulesIn.txt file
	 */
	public void addModule(String line){
		String [] token = line.split("[ ]+"); //split the line into an array of strings
		String code = token[0]; //element at index 0 of token will be the module code
		String title = token[1]; //element at index 1 of token will be the module title
		String time = token[2]; //element at index 2 of token will be the time slot for module
		char room = token[3].charAt(0); //element at index 3 of token will be a string containing just one letter to represent room.
										//this string is converted to a char type
		int capacity = Integer.parseInt(token[4]); //element at index 4 of token will be the number of people enrolled in the module.
										//it is converted to an integer
		Module m = new Module(code,title,time,capacity,room); //create a module object from the variables read
		programme[moduleCount] = m; //add the module object to the array
		moduleCount++; //increment the number of modules currently present in the timetable
	}
	
	//why will you want to delete a module???
	public void deleteModule(Module m){
		int index = getModuleIndex(m);
		programme[index] = null;
		moduleCount--;
	}
	
	//leave return type void for now. Will change it to return status of operation
	public void assignModuleToTimeSlot(String tt, String ts){
		Module m = getModuleByCode(tt);
		m.setTimeSlot(ts);
	}
	
	public void removeModuleFromTimeSlot(String tt, String ts){
		Module m = getModuleByCode(tt);
		m.setTimeSlot(null);
	}
	
	public void addModuleToRoom(String tt, char r){
		Module m = getModuleByCode(tt);
		m.setRoom(r);
	}
	
	public void removeModuleFromRoom(String tt, char r){
		Module m = getModuleByCode(tt);
		m.setRoom(' ');
	}
	
	public int getModuleIndex(Module m){
		for(int i=0; i<programme.length; i++){
			if(programme[i].getModuleTitle() == m.getModuleTitle())
				return i;
		}
		return -1;
	}
	
	public Module getModuleByCode(String s){
		for(Module m: programme){
			if(m.getModuleCode() == s)
				return m;
		}
		return null;
	}
	
	public Module [] getModules(){
		return programme;
	}
}
