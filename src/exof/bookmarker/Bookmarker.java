package exof.bookmarker;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import exof.bookmarker.data.BookReadHistory;
import exof.bookmarker.data.ReadLog;


/**
 * Bookmarker v.0.0.2
 * Command line UI for user to record his reading progress for different books.
 * 
 * @author Rahnoc@gmail.com
 *
 */
public class Bookmarker {

	public static void main(String[] args) {
		String dataFileName = "bmdb";
		String outTextFileName = "bmdata.txt";
		
		Bookmarker bm = new Bookmarker();
		bm.initCL(dataFileName, outTextFileName);
	}
	
	private void initCL(String fileName, String outTextFileName){
		ArrayList<BookReadHistory> brhAL;
		boolean run=true;
		
		IO io = new IO();
		try{
			brhAL = io.read(fileName);
		}catch(FileNotFoundException e){
			brhAL = new ArrayList<BookReadHistory>();
		}
		
		System.out.println("== Bookmarker v0.0.2 ==");
		
		Scanner input = new Scanner(System.in);
		
		while(run){
			System.out.println("======================");
			System.out.println("[1] Add a new read log");
			System.out.println("[2] Del a read log");
			System.out.println("[3] Add a new book");
			System.out.println("[4] Del a book");
			System.out.println("[5] List all");
			System.out.println("[9] Output to textfile");
			System.out.println("[0] Exit");
			System.out.print("input command>");
			String cmd = input.nextLine();
			switch(cmd){
				case "1":
					if(brhAL.size() == 0) {
						System.out.println("No book exists.");
						break;
					}
					System.out.println( this.showBooks(brhAL, false) );
					System.out.println("which book?>");
					int bookIdx = Integer.parseInt(input.nextLine());
					
					BookReadHistory brh;
					try{
						brh = brhAL.get( bookIdx );
					}catch(IndexOutOfBoundsException e){
						System.out.println("No book index matched.");
						break;
					}
					input.reset();
					
					//get ReadLog data.
					System.out.println("Enter edition/filename>");
					String ef = input.nextLine();
					System.out.println("Enter page>");
					String pg = input.nextLine();
					System.out.println("Enter comment>");
					String brCmt = input.nextLine();
					
					//Add ReadLog.
					brh.rlAL.add( new ReadLog(ef, pg, brCmt) );
					
					System.out.println("+new readlog...");
					//Write back to disk.
					io.write(brhAL, fileName);
				break;
					
				case "2":
					System.out.println( this.showBooks(brhAL, false) );
					System.out.println("Whitch book? [index]>");
					bookIdx = Integer.parseInt(input.nextLine());
					
					brh = brhAL.get(bookIdx);
					System.out.println( brh );
					System.out.println("Whitch read log? [index]>");
					int rlIdx = Integer.parseInt(input.nextLine());
					
					try{
						brh.rlAL.remove(rlIdx);
					}catch(IndexOutOfBoundsException e){
						System.out.println("No read log index matched.");
						break;
					}
					
					System.out.println("-read log...");
					//Write back to disk.
					io.write(brhAL, fileName);
				break;
				
				case "3":
					System.out.println( this.showBooks(brhAL, false) );
					System.out.println("Input new book name>");
					String bookName = input.nextLine();
					System.out.println("Input comments for this book>");
					String bCmt = input.nextLine();
					
					brhAL.add( new BookReadHistory(bookName, bCmt) );
					System.out.println("+new book...");
					//Write back to disk.
					io.write(brhAL, fileName);
				break;
				
				case "4":
					System.out.println( this.showBooks(brhAL, false) );
					System.out.println("Delete whitch book? [index]>");
					bookIdx = Integer.parseInt(input.nextLine());
					
					try{
						brhAL.remove(bookIdx);
					}catch(IndexOutOfBoundsException e){
						System.out.println("No book index matched.");
						break;
					}
					
					System.out.println("-book...");
					//Write back to disk.
					io.write(brhAL, fileName);
				break;
				
				case "5":
					System.out.println( "++++ [ Current Book Read History ] ++++" );
					System.out.println( this.showBooks(brhAL, true) );
				break;
				
				case "9":
					io.writeTXT(outTextFileName, this.showBooks(brhAL, true));
					System.out.println(outTextFileName + " is updated.");
				break;
				
				case "0":
					System.out.println("Exit application...");
					run = false;
				break;
					
				default:
					run = false;
				break;
			}
			
		}//End switch.
		
		input.close();
	}
	
	/**
	 * Get String representation for ArrayList<BookReadHistory>.
	 * @param brhAL
	 * @param deep false for only book name, true for detailed readlog.
	 * @return
	 */
	private String showBooks(ArrayList<BookReadHistory> brhAL, boolean deep){
		String rtStr = new String();
		for(BookReadHistory brh : brhAL){
			if( !deep ){
				rtStr += "[" + brhAL.indexOf(brh) + "]" + brh.bookName;				
			}else{
				rtStr += "[" + brhAL.indexOf(brh) + "]" + brh;
			}
			rtStr += System.lineSeparator();
		}
		
		return rtStr;
	}
	
}
