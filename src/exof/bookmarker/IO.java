package exof.bookmarker;

import java.io.*;
import java.util.ArrayList;

import exof.bookmarker.data.BookReadHistory;
import exof.bookmarker.data.ReadLog;

/**
 * Implementation of some IO mechanisms.
 * 
 * 1. for ArrayList<BookReadHistory>.
 * 2. for ReadLog.
 * 3. for general Text data.
 * 
 */
public class IO {
	
	/**
	 * Write whole reading records to disk.
	 * @param brhAL
	 * @param fileName
	 */
	public void write(ArrayList<BookReadHistory> brhAL, String fileName){
		File file = new File(fileName);
		
		try{ 
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			for(BookReadHistory brh : brhAL){
				oos.writeObject( brh );
			}
			
			oos.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Read reading records for all books from disk.
	 * Create one if not exists.
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	public ArrayList<BookReadHistory> read(String fileName) throws FileNotFoundException {
		ArrayList<BookReadHistory> brhAL = new ArrayList<BookReadHistory>();
		File file = new File(fileName);
		
		if(!file.exists()){
			throw new FileNotFoundException();
		}
		
		try{
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			while(fis.available() > 0){
				brhAL.add( (BookReadHistory)ois.readObject() );
			}
			ois.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return brhAL;
	}
	
	/**
	 * Write single read log to disk.
	 * @param rl
	 * @param fileName
	 */
	public void writeLog(ReadLog rl, String fileName){
		File file = new File(fileName);
		 
		try{ 
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject( rl );
			
			oos.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Read single read log from disk.
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	public ReadLog readLog(String fileName) throws FileNotFoundException{
		ReadLog rl = null;
		File file = new File(fileName);
		
		if(!file.exists()){
			throw new FileNotFoundException();
		}
		
		try{
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			rl = (ReadLog)ois.readObject();
			ois.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return rl;
	}
	
	
	/**
	 * Read text data from disk file.
	 * @param fileName
	 * @return
	 */
	public String readTXT(String fileName) {
		String db = new String();
		String line;
		
		try{
			FileReader fr = new FileReader(fileName);
			
			BufferedReader br = new BufferedReader(fr);
			while( (line=br.readLine()) != null ){
				db += line + System.getProperty("line.separator");
			}
			
			br.close();
		}catch(FileNotFoundException ex){
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return db;
	}
	
	/**
	 * Write text data to disk file.
	 * @param fileName
	 * @param db
	 * @return
	 */
	public String writeTXT(String fileName, String db) {
		
		try{
			FileWriter fw = new FileWriter(fileName);
			
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(db);
			
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return db;
	}
}
