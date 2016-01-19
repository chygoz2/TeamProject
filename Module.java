
public class Module {
	
	//instance variables
	private String moduleCode, moduleTitle, timeSlot;
	private char room;
	private int classSize;
	//private char level; still contemplating if it is needed
	
	/**
	 * constructor to initialize instance variables
	 * @param mc parameter to represent module code
	 * @param mt parameter to represent module title
	 * @param ts parameter to represent module time slot
	 * @param cs parameter to represent class size
	 * @param r parameter to represent room
	 */
	public Module(String mc, String mt, String ts, int cs, char r){
		moduleCode = mc;
		moduleTitle = mt;
		timeSlot = ts;
		room = r;
		classSize = cs;
		//level = mt.charAt(2); still contemplating if it is needed
	}
	
	/**
	 * accessor method to return module code
	 * @return module code
	 */
	public String getModuleCode(){
		return moduleCode;
	}
	
	/**
	 * accessor method to return module title
	 * @return module title
	 */
	public String getModuleTitle(){
		return moduleTitle;
	}
	
	/**
	 * accessor method to return module time slot
	 * @return module time slot
	 */
	public String getTimeSlot(){
		return timeSlot;
	}
	
	/**
	 * accessor method to return room assigned to module
	 * @return room
	 */
	public char getRoom(){
		return room;
	}
	
	/**
	 * accessor method to return class size
	 * @return classSize
	 */
	public int getClassSize(){
		return classSize;
	}
	
	/**
	 * mutator method to modify module code
	 * @param s 
	 */
	public void setModuleCode(String s){
		moduleCode = s;
	}
	
	/**
	 * mutator method to modify module title
	 * @param s
	 */
	public void setModuleTitle(String s){
		moduleTitle = s;
	}
	
	/**
	 * mutator method to modify module time slot
	 * @param s
	 */
	public void setTimeSlot(String s){
		timeSlot = s;
	}
	
	/**
	 * mutator method to modify room assigned to module
	 * @param c
	 */
	public void setRoom(char c){
		room = c;
	}
	
	/**
	 * mutator method to modify class size
	 * @param i
	 */
	public void setClassSize(int i){
		classSize = i;
	}
}
