package exof.bookmarker.data;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * For a book with it's name and additional information, user will add
 * his reading log to this book.
 * 
 * BookName / comments (e.g. e-filename)
 * 	<ReadLog>[]
 * 
 * Example:
 * "Just for Fun", "I Had read this 4 times."
 *  -[0] 2014/1/7 [real book/TW translation ver] page:240 / just before ch.22
 *  -[1] 2014/1/9 [eBook, 23mb.] page:251 / none
 *  -[2] ReadLog ...
 *  "Intel IA32 m", ""
 *  -[0] 2014/1/8 [253668.pdf] page:96 / protect mode.
 *  -[1] 2014/1/9 [253668.pdf] page:100 / 3.2.4 segmentation.
 */

public class BookReadHistory implements Serializable{
	private static final long serialVersionUID = 2L;
	//
	public String bookName;
	public String cmt;
	public ArrayList<ReadLog> rlAL;
	
	public BookReadHistory(String bookName, String cmt){
		this.bookName = bookName;
		this.cmt = cmt;
		this.rlAL = new ArrayList<ReadLog>();
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(" \"" + bookName + "\"");
		sb.append(", " + cmt);
		for(ReadLog rl : this.rlAL){
			sb.append( System.getProperty("line.separator") );
			sb.append( "-[" + this.rlAL.indexOf(rl) +"]." );
			sb.append( rl );
		}
		
		return sb.toString();
	}
}
