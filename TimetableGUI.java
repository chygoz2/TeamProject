import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class TimetableGUI extends JFrame implements ActionListener{

	//instance variables
	private Timetable tt;
	private Module [] m;
	private JTable table;
	private JTextArea ta1;
	private JComboBox<String> cb1;
	private JComboBox<String> cb2;
	private JComboBox<String> cb3;
	private JButton b1,b2,b3,b4; //b1 is remove, b2 is assign, b3 is save, b4 is exit
	private String [][] rowData;
	
	/**
	 * constructor to create a TimetableGUI object
	 */
	public TimetableGUI(){
		tt = new Timetable();
		m = tt.getModules();
		//initializeRooms(); may not be needed
		
		if(readFile()) //if ModulesIn.txt file can be read and opened, lay out components
		{
			layoutGUIComponents();
			displayCourses();
			fillTable();
		}
		else //otherwise terminate program
			System.exit(0);
	}
	
	private void layoutGUIComponents()
	{
		setTitle("Timetable");
		setSize(650,500);
		setLocation(350,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		GridLayout grid = new GridLayout(2,2);
		setLayout(grid);
		*/
		
		String [] columnNames = {" ","A","B","C","D","E","F","G","H"}; //correspond to the names of columns

		rowData = new String[10][9]; //creates a String array object to hold table data
		//fills the first column of each row of the table with the specified time slots
		rowData[0][0] = "MonAM";	rowData[1][0] = "MonPM";  
		rowData[2][0] = "TueAM";	rowData[3][0] = "TuePM";
		rowData[4][0] = "WedAM";	rowData[5][0] = "WedPM";
		rowData[6][0] = "ThuAM";	rowData[7][0] = "ThuPM";
		rowData[8][0] = "FriAM";	rowData[9][0] = "FriPM";

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		
		table = new JTable(rowData,columnNames);
		//Prevents column headers from being rearranged
		table.getTableHeader().setReorderingAllowed(false);
		table.setPreferredScrollableViewportSize(new Dimension(600, 250));
		JScrollPane sP = new JScrollPane(table);

		
		GridLayout grid2 = new GridLayout(1,1);
		p1.setLayout(grid2);
		
		p1.add(sP);

		//p1.add(sP);
		//p1.add(p3);

		GridLayout grid3 = new GridLayout(5,2);
		p3.setLayout(grid3);
		JLabel l1 = new JLabel("Module Code");
		JLabel l2 = new JLabel("Time Slot");
		JLabel l3 = new JLabel("Room");

		//combobox to hold the module classes
		cb1 = new JComboBox<String>();

		//combobox to hold the timeslots
		cb2 = new JComboBox<String>();
		cb2.addItem("MonAM");
		cb2.addItem("MonPM");
		cb2.addItem("TueAM");
		cb2.addItem("TuePM");
		cb2.addItem("WedAM");
		cb2.addItem("WedPM");
		cb2.addItem("ThuAM");
		cb2.addItem("ThuPM");
		cb2.addItem("FriAM");
		cb2.addItem("FriPM");

		//combobox to hold the rooms
		cb3 = new JComboBox<String>();
		cb3.addItem("A");
		cb3.addItem("B");
		cb3.addItem("C");
		cb3.addItem("D");
		cb3.addItem("E");
		cb3.addItem("F");
		cb3.addItem("G");
		cb3.addItem("H");

		b1 = new JButton("Remove");
		b2 = new JButton("Assign");
		b3 = new JButton("Save Timetable");
		b4 = new JButton("Quit");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);

		p3.add(l1); p3.add(cb1);
		p3.add(l2); p3.add(cb2);
		p3.add(l3); p3.add(cb3);
		p3.add(b1); p3.add(b2);
		p3.add(b3); p3.add(b4);

		//Panel p4 will have p2 and p3 added onto it
		GridLayout grid4 = new GridLayout(1,2);
		p4.setLayout(grid4);
		p4.add(p3);
		
		TableColumn tc = table.getColumnModel().getColumn(0);
		tc.setPreferredWidth(90);
		table.setRowHeight(25);

		ta1 = new JTextArea(14,36);
		ta1.setFont(new Font("Courier", Font.PLAIN, 14));
		ta1.setEditable(false);
		//Adds a scroll pane to the text area
		JScrollPane textPane = new JScrollPane(ta1);
		p2.setLayout(new BorderLayout());
		p2.add(textPane);
		p4.add(p2);

		add(p1, BorderLayout.NORTH);
		add(p4);
	}

	/**
	 * method to read the content of the ModulesIn.txt file
	 */
	private boolean readFile(){

		try {
			FileReader reader = new FileReader("ModulesIn.txt");
			Scanner scanner = new Scanner(reader);
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				tt.addModule(line);
			}

			reader.close();
			scanner.close();
			return true;
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "The file was not found", "Error", JOptionPane.ERROR_MESSAGE); //notify user that file was not found
			return false;
		} catch (IOException e){
			JOptionPane.showMessageDialog(null, "The file could not be opened", "Error", JOptionPane.ERROR_MESSAGE); //notify user that file could not be opened
			return false;
		}

	}
	
	/**
	 * method to display the courses in the text area
	 */
	private void displayCourses()
	{
		String courses = "";
		courses += String.format("%-10s%-10s%-8s%-8s%n", "Code","Time","Room","Size");
		for(int i=0; i<m.length; i++){
			courses += String.format("%-10s%-10s%-8s%-8s%n", m[i].getModuleCode(), m[i].getTimeSlot(),
					m[i].getRoom(), m[i].getClassSize());
			cb1.addItem(m[i].getModuleCode());
		}
		ta1.setText(courses);
	}
	
	/**
	 * method to update table with timetable contents
	 */
	private void fillTable(){
		clearTable(); //clear the table contents
		for(int i=0; i<m.length; i++)
		{
			String time = m[i].getTimeSlot();
			char room = m[i].getRoom();
		
			int row = getIndexForTimeSlot(time);
			int col = getIndexForRoom(room);
			
			if(row != -1 && col != -1)
				rowData[row][col] = m[i].getModuleCode();
			AbstractTableModel tm = (AbstractTableModel)table.getModel();
			tm.fireTableDataChanged();
		}
	}
	
	/**
	 * method to clear table contents
	 */
	private void clearTable(){
		
		for(int row=0; row<10; row++)
			for(int col=1; col<9; col++)
				rowData[row][col] = "";
	}
	
	/**
	 * helper method to get what index a time slot belongs to 
	 * @param ts is the time slot whose index is required
	 * @return the required index
	 */
	private int getIndexForTimeSlot(String ts)
	{
		int row;
		switch(ts){
		case "MonAM":
			row = 0;
			break;
		case "MonPM":
			row = 1;
			break;
		case "TueAM":
			row = 2;
			break;
		case "TuePM":
			row = 3;
			break;
		case "WedAM":
			row = 4;
			break;
		case "WedPM":
			row = 5;
			break;
		case "ThuAM":
			row = 6;
			break;
		case "ThuPM":
			row = 7;
			break;
		case "FriAM":
			row = 8;
			break;
		case "FriPM":
			row = 9;
			break;
		default:
			row = -1;
			break;
		}
		return row;
	}
	
	private int getIndexForRoom(char r)
	{
		int col;
		switch(r){
		case 'A':
			col = 1;
			break;
		case 'B':
			col = 2;
			break;
		case 'C':
			col = 3;
			break;
		case 'D':
			col = 4;
			break;
		case 'E':
			col = 5;
			break;
		case 'F':
			col = 6;
			break;
		case 'G':
			col = 7;
			break;
		case 'H':
			col = 8;
			break;
		default:
			col = -1;
			break;
		}
		return col;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String code = (String)cb1.getSelectedItem(); 
		String time = (String)cb2.getSelectedItem();
		String room = (String)cb3.getSelectedItem();
		
		Object source = (JButton)e.getSource();
		if(source == b2)
		{
	
			if(validateInput(code, time, room))
			{	
				
				Module m = tt.getModuleByCode(code);
				m.setRoom(room.charAt(0));
				m.setTimeSlot(time);
				fillTable();
				displayCourses();
			}
		}
		
		//for removal operation
		/*
		else if(source == b1)
		{
			int row = getIndexForTimeSlot(time);
			int col = getIndexForRoom(room.charAt(0));
		
			tt.removeModuleFromRoom(code, room.charAt(0));
			tt.removeModuleFromTimeSlot(code, time);
			rowData[row][col] = "";
			fillTable();
			displayCourses();
		}
		*/
		
		//If the save button is clicked, calls the saveOutput() method to save the timetable data to a text file
		else if(source == b3)
			saveOutput();
		
		/*
		  If the quit button is pressed, will ask the user if they have saved the timetable first.
		  If they click YES, quit the program. Else if NO is pressed, will ask the user if they wish to save the timetable.
		  If Yes is clicked, saves the timetable and quits the program. Else, quits the program without saving
		*/
		else if(source == b4)
		{
			int confirm = JOptionPane.showConfirmDialog(null, "You are about to exit the program. Have you saved the timetable first?", "Exit Program?",  JOptionPane.YES_NO_CANCEL_OPTION);
			
			if(confirm == JOptionPane.YES_OPTION)
				System.exit(0);
			else if(confirm == JOptionPane.NO_OPTION)
			{
				int confirm2 = JOptionPane.showConfirmDialog(null, "Would you like to save the timetable?", "Exit Program?", JOptionPane.YES_NO_OPTION);
				
				if(confirm2 == JOptionPane.YES_OPTION)
				{
					saveOutput();
					System.exit(0);
				}
				else
					System.exit(0);
			}
		}
	}
	
	/**
	 * Method to process saving the data from the timetable to a text file called ModulesOut.txt
	 */
	private void saveOutput()
	{
		//String output = ta1.getText(); 
		//had to modify the output variable because the format of text in the text area and the format in the moduleIn.txt file are not the same
		//whereas according to the specification, the format of ModulesIn.txt and ModulesOut.txt should be similar
		
		String output = "";
		//loop to extract details of each module from the module array
		for(Module mo: m)
		{
			output += String.format("%s %s %s %s %s%n", mo.getModuleCode(), mo.getModuleTitle(), mo.getTimeSlot(), mo.getRoom(), mo.getClassSize());
		}
		
		try
		{
			FileWriter writer = new FileWriter("ModulesOut.txt");
			writer.write(output);
			writer.close();
		}
		catch (IOException x)
		{
			JOptionPane.showMessageDialog(null, "Output Error", "Error: File I/O Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * validates the inputs selected to check if the required criteria are met
	 * @param module to be acted upon
	 * @param time to be assigned to module
	 * @param room to be assigned to module
	 * @return validation result
	 */
	private boolean validateInput(String code, String time, String room)
	{
		Module module = tt.getModuleByCode(code);
		if(validateRoomCapacity(module, room)) //checks if the room is big enough to accommodate the class size of the module
				if(validateCourseSubjectAndLevel(module, time)) //checks if there is another module with the same level already taking place on the time slot to be assigned
					if(validateFreeTimeSlotAndRoom(module, time, room)) //checks if the selected time slot and room have another module assigned to it
						return true;
		return false;
	}
	
	/**
	 * method to get the capacity of a room
	 * @param r is the room whose capacity is required
	 * @return the required capacity
	 */
	private int getRoomCapacity(String r)
	{
		int capacity;
		switch(r){
		case "A":
			capacity = 100;
			break;
		case "B":
			capacity = 100;
			break;
		case "C":
			capacity = 60;
			break;
		case "D":
			capacity = 60;
			break;
		case "E":
			capacity = 60;
			break;
		case "F":
			capacity = 30;
			break;
		case "G":
			capacity = 30;
			break;
		case "H":
			capacity = 30;
			break;
		default:
			capacity = 0;
		}
		return capacity;
	}
	
	/**
	 * method to check if the room to be assigned to a module can accomodate the class size
	 * @param module that the room is to be assigned to
	 * @param room that is to be assigned
	 * @return result of validation
	 */
	private boolean validateRoomCapacity(Module module, String room)
	{
		int classSize = module.getClassSize();
		int roomSize = getRoomCapacity(room);
		if(classSize>roomSize)
		{
			JOptionPane.showMessageDialog(null, "Room is too small for class", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}	
		return true;
	}
	
	/**
	 * method to check if the time to be assigned to a module already has another module with the same subject and year
	 * @param module that the time is to be assigned to
	 * @param time that is to be assigned
	 * @return result of validation
	 */
	private boolean validateCourseSubjectAndLevel(Module module, String time)
	{
		char level1 = module.getLevel(); //get the level of the course to be added
		String subj1 = module.getSubject(); //get the subject of the course to be added
		ArrayList<Module> list = tt.getModuleByTime(time); //get a list containing modules taking place at the specified time
		if(!list.isEmpty()) //if the list is not empty
		{
			//iterate through the list
			ListIterator<Module> li = list.listIterator(); 
			while(li.hasNext())
			{
				Module m = li.next(); //get a module in the list
				
				char level2 = m.getLevel(); //get the level of that module
				String subj2 = m.getSubject(); //get the subject of that module
				
				//if the module to be assigned is the same as what was already there in the time slot, validation is passed. (The operation is just a case of reassignment)
				if(m.equals(module))
					return true;
				
				//if a module with the same level and subject with the new module to be assigned exists, validation fails
				if(subj1.equals(subj2) && level1 == level2)
				{
					JOptionPane.showMessageDialog(null, "There is another module with the same subject and year taking place at this the selected time slot",
															"Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}
		return true;
		
	}
	
	/**
	 * method to check if the chosen time slot and room is not occupied
	 * @param time to be checked
	 * @param room to be checked
	 * @return result of validation
	 */
	private boolean validateFreeTimeSlotAndRoom(Module module, String time, String room)
	{
		ArrayList<Module> list = tt.getModuleByTime(time); //get a list containing modules taking place at the specified time
		if(!list.isEmpty()) //if the list is not empty
		{
			//iterate through the list
			ListIterator<Module> li = list.listIterator(); 
			while(li.hasNext())
			{
				Module m = li.next(); //get a module in the list
				
				//if the module to be assigned is the same as what is already there in the time slot and room, validation is passed. (Nothing happens)
				if(m.equals(module))
					return true;
				
				//if a module with that time and room exists, validation fails
				if(m.getRoom() == room.charAt(0))
				{
					JOptionPane.showMessageDialog(null, "There is another module at the selected time and room. Please remove that module first or choose another time or room.",
															"Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}
		
		return true;
	}
	

	public static void main(String [] args){
		JFrame f = new TimetableGUI();
		f.setVisible(true);
	}
	
}
