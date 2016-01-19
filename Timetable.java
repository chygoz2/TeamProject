
public class Timetable {

	private Module [] programme;
	private final int SIZE = 24;
	private int moduleCount;
	
	public Timetable(){
		// default constructor
		programme = new Module[SIZE];
		moduleCount = 0;
	}
	
	public void addModule(String line){
		String [] token = line.split("[ ]+");
		String code = token[0];
		String title = token[1];
		String time = token[2];
		char room = token[3].charAt(0);
		int capacity = Integer.parseInt(token[4]);
		Module m = new Module(code,title,time,capacity,room);
		programme[moduleCount] = m;
		moduleCount++;
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
