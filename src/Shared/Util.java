package Shared;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static String dateFull(){
		DateFormat dateFormat = new SimpleDateFormat("dd, yyyy hh:mm");
        Date date = new Date();
        
       String s = new SimpleDateFormat("MMMM").format(date)+" "+dateFormat.format(date);
       
       return s;
	}
	public static String date() {
		DateFormat dateFormat = new SimpleDateFormat("dd, yyyy");
        Date date = new Date();
        
        String s = new SimpleDateFormat("MMMM").format(date)+" "+dateFormat.format(date);
       
       return s;
	}
}
