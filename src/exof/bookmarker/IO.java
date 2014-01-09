package exof.bookmarker;

import java.io.*;

public class IO {
	/**
	 * Read data from disk file.
	 * @param fileName
	 * @return
	 */
	public String readDB(String fileName) {
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
	 * Write data to disk file.
	 * @param fileName
	 * @param db
	 * @return
	 */
	public String writeDB(String fileName, String db) {
		
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
