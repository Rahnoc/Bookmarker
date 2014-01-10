package exof.bookmarker.data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * A single activity record after reading a book.
 * [TimeStamp] [Edition/filename] [current page] [comment]
 * 
 */


public class ReadLog implements Serializable{
	private static final long serialVersionUID = 1L;
	//
	public Date ts;
	public String fn;
	public String pg;
	public String cmt;
	
	public ReadLog(String fn, String pg, String cmt){
		this.ts = new Date();
		this.fn = fn;
		this.pg = pg;
		this.cmt = cmt;
	}
	
	
	@Override
	public String toString(){
		 SimpleDateFormat sdF = new SimpleDateFormat("yy/MM/dd HH:mm");
		
		StringBuffer rtbs = new StringBuffer();
		rtbs.append( "<" + sdF.format(this.ts) + ">");
		rtbs.append( "[" + fn + "]");
		rtbs.append( " Page:" + pg );
		rtbs.append( " /" + cmt);
		
		return rtbs.toString();
	}
}