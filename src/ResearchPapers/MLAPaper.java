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
	/**
	 * @param args
	 * @throws IOException
	 * @throws XmlException
	 */
	public static void main(String[] args) throws IOException, XmlException {

		//Author info
		String name= "Yaron Eidelman";
		String lastName = name.split(" ")[1];
		String teacherName = "Professor Smith";
		String className = "English 302";
		String dueDate = "" + Util.date();
		String title = "Computer Science title";

		ArrayList<String> para = new ArrayList<String>();
		para.add("The first paragraph of a typical business letter is used to state the main point of the letter. Begin with a friendly opening; then quickly transition into the purpose of your letter. Use a couple of sentences to explain the purpose, but do not go in to detail until the next paragraph.");
		para.add("Beginning with the second paragraph, state the supporting details to justify your purpose. These may take the form of background information, statistics or first-hand accounts. A few short paragraphs within the body of the letter should be enough to support your reasoning.");
		para.add("Finally, in the closing paragraph, briefly restate your purpose and why it is important. If the purpose of your letter is employment related, consider ending your letter with your contact information. However, if the purpose is informational, think about closing with gratitude for the reader's time.");


		XWPFDocument document = new XWPFDocument();
		
		//Creates the page header
		createHeader(document, lastName);
		
		// Creates the authors and paper information
		setAuthorInfo(document, name, teacherName, className, dueDate);
		
		//Set paper title
		setTitle(document, title);

		//Calls on createParagraph() method which creates a single paragraph
		for(int i=0; i< para.size(); i++){
			createParagraph(document, para.get(i));
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
	
	//Set the title of the paper
	private static void setTitle(XWPFDocument document, String title) {
		XWPFParagraph paraOne = document.createParagraph();
		XWPFRun one = paraOne.createRun();
		paraOne.setAlignment(ParagraphAlignment.CENTER);
		one.setText(title);
	}
	
	// Set author and page information
	private static void setAuthorInfo( XWPFDocument document, String name, String teacherName, String className ,String dueDate){
		XWPFParagraph paraOne = document.createParagraph();
		XWPFRun one = paraOne.createRun();
		paraOne.setAlignment(ParagraphAlignment.LEFT);
		
		one.setText(name); // Set author name
		one.addBreak(BreakType.TEXT_WRAPPING);
		one.setText(teacherName);
		one.addBreak(BreakType.TEXT_WRAPPING);
		one.setText(className);
		one.addBreak(BreakType.TEXT_WRAPPING);
		one.setText(dueDate);
		//one.addBreak();
		
		
	}
	
	//Creates a single paragraph with a one tab indentation
	private static void createParagraph(XWPFDocument document, String para) {
		XWPFParagraph paraOne = document.createParagraph();
		paraOne.setFirstLineIndent(700); // Indents first line of paragraph to the equivalence of one tab
		XWPFRun one = paraOne.createRun();
		one.setFontSize(12);
		one.setFontFamily("Times New Roman");
		one.setText(para);
	}
	
	// Creates page header of the authors last name. 
	/*!!!!!!!!!!!!!!!!!!!!!  **** Need to add page number ****** !!!!!!!!!!!!!!!!!*/
	private static void createHeader(XWPFDocument document, String lastName) throws IOException, XmlException {
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
	}
}
