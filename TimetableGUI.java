import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class TimetableGUI extends JFrame implements ActionListener{

	private Timetable tt;
	private JTable table;
	private JTextArea ta1;
	private JComboBox<String> cb1;
	private JComboBox<String> cb2;
	private JComboBox<String> cb3;
	private JButton b1,b2; //b1 is remove, b2 is assign
	private String [][] rowData;
	private Room [] rooms;

	public TimetableGUI(){
		tt = new Timetable();
		initializeRooms();
		setTitle("Timetable");
		setSize(800,600);
		setLocation(350,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridLayout grid = new GridLayout(2,1);
		setLayout(grid);
		
		String [] columnNames = {" ","A","B","C","D","E","F","G","H"};

		rowData = new String[10][9];
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
		JScrollPane sP = new JScrollPane(table);

		
		GridLayout grid2 = new GridLayout(2,1);
		p1.setLayout(grid2);
		
		p1.add(sP);

		//p1.add(sP);
		//p1.add(p3);

		GridLayout grid3 = new GridLayout(4,2);
		p3.setLayout(grid3);
		JLabel l1 = new JLabel("Module Code");
		JLabel l2 = new JLabel("Time Slot");
		JLabel l3 = new JLabel("Room");

		cb1 = new JComboBox<String>();

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
		b1.addActionListener(this);
		b2.addActionListener(this);

		p3.add(l1); p3.add(cb1);
		p3.add(l2); p3.add(cb2);
		p3.add(l3); p3.add(cb3);
		p3.add(b1); p3.add(b2);

		p4.add(p3);
		TableColumn tc = table.getColumnModel().getColumn(0);
		tc.setPreferredWidth(90);
		table.setRowHeight(25);

		ta1 = new JTextArea(13,36);
		ta1.setFont(new Font("Courier", Font.PLAIN, 14));
		ta1.setEditable(false);
		//Adds a scroll pane to the text area
		JScrollPane textPane = new JScrollPane(ta1);
		//p2.setLayout(new BorderLayout());
		p2.add(textPane);

		add(p1);
		add(p2);
		add(p3);
		readFile();
		fillTable();
	}

	public void readFile(){

		try {
			FileReader reader = new FileReader("ModulesIn.txt");
			Scanner scanner = new Scanner(reader);
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				tt.addModule(line);
			}

			displayCourses();

			reader.close();
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}

	}
	
	public void displayCourses()
	{
		String courses = "";
		Module [] m = tt.getModules();
		courses += String.format("%-10s%-10s%-8s%-8s%n", "Code","Time","Room","Size");
		for(int i=0; i<m.length; i++){
			courses += String.format("%-10s%-10s%-8s%-8s%n", m[i].getModuleCode(), m[i].getTimeSlot(),
					m[i].getRoom(), m[i].getClassSize());
			cb1.addItem(m[i].getModuleCode());
		}
		ta1.setText(courses);
	}
	
	public void fillTable(){
		Module [] m = tt.getModules();
		for(int i=0; i<m.length; i++)
		{
			int row = 0, col = 0;
			String time = m[i].getTimeSlot();
			char room = m[i].getRoom();
			switch(time){
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
			
			switch(room){
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
			if(row != -1 || col != -1)
				rowData[row][col] = m[i].getModuleCode();
			AbstractTableModel tm = (AbstractTableModel)table.getModel();
			tm.fireTableDataChanged();
		}
	}
	
	public void initializeRooms(){
		rooms = new Room[8];
		rooms[0] = new Room('A',100);
		rooms[1] = new Room('B',100);
		rooms[2] = new Room('C',60);
		rooms[3] = new Room('D',60);
		rooms[4] = new Room('E',60);
		rooms[5] = new Room('F',30);
		rooms[6] = new Room('G',30);
		rooms[7] = new Room('H',30);
	}
	
	public void actionPerformed(ActionEvent e){
		Object source = (JButton)e.getSource();
		if(source == b2){
			String code = (String)cb1.getSelectedItem(); 
			String time = (String)cb2.getSelectedItem();
			String room = (String)cb3.getSelectedItem();
			Module m = tt.getModuleByCode(code);
			m.setRoom(room.charAt(0));
			m.setTimeSlot(time);
			fillTable();
			displayCourses();
		}
	}
	public static void main(String [] args){
		JFrame f = new TimetableGUI();
		f.setVisible(true);
	}
}
