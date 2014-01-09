package exof.bookmarker;

import java.util.Scanner;

public class Bookmarker {

	public static void main(String[] args) {
		String dbFileName = "bmdata.txt";
		
		Bookmarker bm = new Bookmarker();
		bm.init( dbFileName );
		
	}

	private void init(String fileName){
		boolean run=true;
		
		IO io = new IO();
		String db = io.readDB(fileName);
		
		System.out.println("== Bookmarker v0.0.1 ==");
		System.out.println("[1]Add new record");
		System.out.println("[2]List record");
		System.out.println("[3]Exit");
		
		Scanner input = new Scanner(System.in);
		
		while(run){
			System.out.println("");
			System.out.print("input command>");
			String cmd = input.nextLine();
			switch(cmd){
				case "1":
					System.out.println("Keyin new record:");
					String newEntry = input.nextLine();
					db += newEntry + System.getProperty("line.separator");
					System.out.println("+new recording...");
					io.writeDB(fileName, db);
				break;
					
				case "2":
					System.out.println("Current records:");
					System.out.println(db);
				break;
					
				case "3":
					System.out.println("Exit application...");
					run = false;
				break;
					
				default:
					run = false;
				break;
			}
			
		}
			
	}
}
