package ResearchPapers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.border.Border;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

import Shared.Util;

public class MLAPaper {
	public static void main(String[] args) throws IOException, XmlException {

	    	//Author info
	    	String name= "Yaron Eidelman";
	    	String lastName = name.split(" ")[1];
	    	
	    	ArrayList<String> para = new ArrayList<String>();
	    	para.add("The first paragraph of a typical business letter is used to state the main point of the letter. Begin with a friendly opening; then quickly transition into the purpose of your letter. Use a couple of sentences to explain the purpose, but do not go in to detail until the next paragraph.");
	    	para.add("Beginning with the second paragraph, state the supporting details to justify your purpose. These may take the form of background information, statistics or first-hand accounts. A few short paragraphs within the body of the letter should be enough to support your reasoning.");
	    	para.add("Finally, in the closing paragraph, briefly restate your purpose and why it is important. If the purpose of your letter is employment related, consider ending your letter with your contact information. However, if the purpose is informational, think about closing with gratitude for the reader's time.");
	    	
	    	
	        XWPFDocument document = new XWPFDocument();
	        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
				XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);
				CTP ctpHeader = CTP.Factory.newInstance();
		        CTR ctrHeader = ctpHeader.addNewR();
		        CTText ctHeader = ctrHeader.addNewT();
		        String headerText = lastName;
		        ctHeader.setStringValue(headerText);	
	        
		        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
		        XWPFParagraph[] parsHeader = new XWPFParagraph[1];
		        headerParagraph.setAlignment(ParagraphAlignment.RIGHT);
		        parsHeader[0] = headerParagraph;
		        policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);
		        
	        
	        	
	       /* XWPFParagraph introPara = document.createParagraph();
	        XWPFRun intro = introPara.createRun();
	        */	
	        	
	        XWPFParagraph paraOne = document.createParagraph();
	        	paraOne.setFirstLineIndent(700); // Indents first line of paragraph to the equivalence of one tab
		    XWPFRun one = paraOne.createRun();
		     	for(int i=0; i< para.size(); i++){
		     		one.setText(para.get(i));
		     		one.addBreak(BreakType.TEXT_WRAPPING);
		     		one.addBreak();		     		
		     	}
	      
	 
	        FileOutputStream outStream = null;
	        try {
	            outStream = new FileOutputStream("ResearchPaper.docx");
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
