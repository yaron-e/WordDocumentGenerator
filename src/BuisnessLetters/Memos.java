package BuisnessLetters;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import Shared.Util;

public class Memos {
	public static void main(String[] args) {
		// Recipeant info
	    	String streetRec = "123 Winner's Road";
	    	String city = "Manassas, VA 20109";
	    	
	    	//Author info
	    	String name= "Yaron Eidelman";
	    	String street = "10204 Aqua View Ct";
	    	String address = "Nokesville, Virginia 20181";
	    	String recipiantName = "Bob Smith";
	    	
	    	// paragraphs
	    	
	    	String to = "Employees";
	    	String from = "Managment";
	    	String subject = "Promotion";
	    	
	    	ArrayList<String> para = new ArrayList<String>();
	    	para.add("The first paragraph of a typical business letter is used to state the main point of the letter. Begin with a friendly opening; then quickly transition into the purpose of your letter. Use a couple of sentences to explain the purpose, but do not go in to detail until the next paragraph.");
	    	para.add("Beginning with the second paragraph, state the supporting details to justify your purpose. These may take the form of background information, statistics or first-hand accounts. A few short paragraphs within the body of the letter should be enough to support your reasoning.");
	    	para.add("Finally, in the closing paragraph, briefly restate your purpose and why it is important. If the purpose of your letter is employment related, consider ending your letter with your contact information. However, if the purpose is informational, think about closing with gratitude for the reader's time.");
	    	
	    	
	        XWPFDocument document = new XWPFDocument();
	        
	        
	        
	        
	        XWPFParagraph introPara = document.createParagraph();
	        XWPFRun intro = introPara.createRun();
	        	intro.setText("TO: "+to);
	        	intro.addBreak(BreakType.TEXT_WRAPPING);
	        	intro.addBreak();
	        	intro.setText("FROM: "+ from);
	        	intro.addBreak(BreakType.TEXT_WRAPPING);
	        	intro.addBreak();
	        	intro.setText("DATE: "+Util.date());
	        	intro.addBreak(BreakType.TEXT_WRAPPING);
	        	intro.addBreak();
	        	intro.setText("SUBJECT: "+ subject);
	        	intro.addBreak(BreakType.TEXT_WRAPPING);
	        	intro.addBreak();
	        	intro.setFontSize(15);
	        	
	         XWPFParagraph paraOne = document.createParagraph();
		     XWPFRun one = paraOne.createRun();
		     	for(int i=0; i< para.size(); i++){
		     		one.setText(para.get(i));
		     		one.addBreak(BreakType.TEXT_WRAPPING);
		     		one.addBreak();		     		
		     	}
	      
	 
	        FileOutputStream outStream = null;
	        try {
	            outStream = new FileOutputStream("Memo.docx");
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	 
	        try {
	            document.write(outStream);
	            outStream.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        System.out.print("done  "+ Util.dateFull());
	    }
}
