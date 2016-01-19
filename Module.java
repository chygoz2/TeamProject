
public class Module {
	
	private String moduleCode, moduleTitle, timeSlot;
	private char room;
	private int classSize;
	//private char level;
	
	public Module(String mc, String mt, String ts, int cs, char r){
		moduleCode = mc;
		moduleTitle = mt;
		timeSlot = ts;
		room = r;
		classSize = cs;
		//level = mt.charAt(2);
	}
	
	/*accessor methods*/
	public String getModuleCode(){
		return moduleCode;
	}
	
	public String getModuleTitle(){
		return moduleTitle;
	}
	
	public String getTimeSlot(){
		return timeSlot;
	}
	
	public char getRoom(){
		return room;
	}
	
	public int getClassSize(){
		return classSize;
	}
	
	/*public char getLevel(){
		return level;
	}*/
	
	/*mutator methods*/
	public void setModuleCode(String s){
		moduleCode = s;
	}
	
	public void setModuleTitle(String s){
		moduleTitle = s;
	}
	
	public void setTimeSlot(String s){
		timeSlot = s;
	}
	
	public void setRoom(char c){
		room = c;
	}
	
	public void setClassSize(int i){
		classSize = i;
	}
}
