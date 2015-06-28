import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.text.TableView.TableRow;

import org.apache.poi.wp.usermodel.CharacterRun;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class CreateDocumentFromScratch {
 
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
    	ArrayList<String> para = new ArrayList<String>();
    	para.add("The first paragraph of a typical business letter is used to state the main point of the letter. Begin with a friendly opening; then quickly transition into the purpose of your letter. Use a couple of sentences to explain the purpose, but do not go in to detail until the next paragraph.");
    	para.add("Beginning with the second paragraph, state the supporting details to justify your purpose. These may take the form of background information, statistics or first-hand accounts. A few short paragraphs within the body of the letter should be enough to support your reasoning.");
    	para.add("Finally, in the closing paragraph, briefly restate your purpose and why it is important. If the purpose of your letter is employment related, consider ending your letter with your contact information. However, if the purpose is informational, think about closing with gratitude for the reader's time.");
    	
    	
    	
        XWPFDocument document = new XWPFDocument();
        
        DateFormat dateFormat = new SimpleDateFormat("dd, yyyy hh:mm");
        Date date = new Date();
        
        //Recipient Address
        XWPFParagraph recipeantPara = document.createParagraph();
        XWPFRun recipeant = recipeantPara.createRun();
        	recipeant.setText(streetRec ,0);
        	recipeant.addBreak();
        	recipeant.setText(city, 1);
        	recipeant.addBreak();
        	//recipeant.addBreak();
        
       //Author info
        XWPFParagraph letterHead = document.createParagraph();
        XWPFRun letterhead = letterHead.createRun();
        
    		letterhead.setText(new SimpleDateFormat("MMMM").format(date)+" "+dateFormat.format(date), 0);
    		letterhead.addBreak();
        	letterhead.addBreak();
    		letterhead.setText(name, 1);
        	letterhead.addBreak();
        	letterhead.setText(street, 2);
        	letterhead.addBreak();
        	letterhead.setText(address, 3);
        	letterhead.addBreak();
        	letterhead.addBreak();
        	letterhead.setText("Dear "+recipiantName+":", 4);
        	letterhead.addBreak();

        	
        	
        
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun paragraphOneRunOne = paragraph.createRun();
        int  i =0;
        		for(i=0; i < para.size(); i++){
        			paragraphOneRunOne.setText(para.get(i), i);
        			paragraphOneRunOne.addBreak();
        			paragraphOneRunOne.addBreak();
        		}
        		
        paragraphOneRunOne.setText("Sincerely, ", i++);
        paragraphOneRunOne.addBreak();
        paragraphOneRunOne.addBreak();
        paragraphOneRunOne.addBreak();
        paragraphOneRunOne.setText(name, i++);
        paragraphOneRunOne.addBreak();
        /*paragraphOneRunOne.setText("Hello world! This is paragraph one! !!");
        		paragraphOne.setBorderBottom(Borders.CUP); // Creates a Line under the paragraph
        XWPFRun paragraphOneRunTwo = paragraphOne.createRun();
        		paragraphOneRunTwo.setText(" More text in paragraph one...");
 
        XWPFParagraph paragraphTwo = document.createParagraph();
        XWPFRun paragraphTwoRunOne = paragraphTwo.createRun();
        paragraphTwoRunOne.setText("And this is paragraph two.");*/
 
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream("Hello.docx");
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
        System.out.print("done");
    }
 
}