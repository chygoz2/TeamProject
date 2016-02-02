import java.util.ArrayList;

public class Timetable {

	//instance variables
	private ArrayList<Module> programme;
	private int moduleCount;
	
	//constructor to initialize the instance variables
	public Timetable(){
		programme = new ArrayList<Module>();
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
		programme.add(m); //add the module object to the arraylist
		moduleCount++; //increment the number of modules currently present in the timetable
	}
	
	/**
	 * method to assign a module a time slot
	 * @param tt is the code of the module
	 * @param ts is the time slot to be assigned to the module
	 */
	public void assignModuleToTimeSlot(String tt, String ts){
		Module m = getModuleByCode(tt); //gets the module with the specified title
		m.setTimeSlot(ts); //sets the time slot of that module
	}
	
	/**
	 * method to remove a module from a time slot
	 * @param tt is the code of the module
	 * @param ts is the time slot to be removed from the module
	 */
	public void removeModuleFromTimeSlot(String tt){
		Module m = getModuleByCode(tt); //gets the module with the specified title
		m.setTimeSlot("?????"); //sets the timeslot of the module to "?????" indicating that the module has no time slot
	}
	
	/**
	 * method to assign a room to a module
	 * @param tt is the code of the module
	 * @param r is the room to be assigned to the module
	 */
	public void addModuleToRoom(String tt, char r){
		Module m = getModuleByCode(tt); //gets the module with the specified title
		m.setRoom(r);
	}
	
	/**
	 * method to remove a room from a module
	 * @param tt is the code of the module
	 * @param r is the room to be removed
	 */
	public void removeModuleFromRoom(String tt){
		Module m = getModuleByCode(tt); //gets the module with the specified title
		m.setRoom('?'); //sets the room assigned to the module to '?' indicating that the module is not assigned to any room
	}
	
	/**
	 * method to search for a module by its code
	 * @param s is the code to be searched for
	 * @return the module required
	 */
	public Module getModuleByCode(String s){
		for(Module m: programme){
			if(m != null && m.getModuleCode() == s) //if a module with the specified title is found,
				return m; //return that module
		}
		return null; //else module not found. Return null
	}
	
	/**
	 * method to search for module(s) assigned to a particular time slot
	 * @param t is the time to be searched for
	 * @return the modules if any
	 */
	public ArrayList<Module> getModuleByTime(String time)
	{
		//uses a list to store the module since a time slot can have multiple modules assigned to it but with different rooms
		ArrayList<Module> list = new ArrayList<Module>(); 
		for(Module m: programme)
		{
			if(m != null && m.getTimeSlot().equals(time)) //if a module is assigned to the specified time slot,
				list.add(m); //add the module to the list
		}
		return list; //return the list
	}
	
	/**
	 * accessor method to get all the modules in the timetable
	 * @return an array containing all modules
	 */
	public ArrayList<Module> getModules(){
		return programme;
	}

	/**
	 * @return the moduleCount
	 */
	public int getModuleCount() {
		return moduleCount;
	}
}
