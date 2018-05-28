import java.io.*;
import java.util.*;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;

//compare시 객체생성, 왼쪽이1 오른쪽이 2
public class mergeText {

	Document doc1, doc2;
	Element root1, root2;
	public mergeText() {		
	}
	
	public mergeText(JTextArea first, JTextArea second) {
		doc1 = first.getDocument();
		root1 = doc1.getDefaultRootElement();
	    doc2 = second.getDocument();
	    root2 = doc2.getDefaultRootElement();
	}
	//한 줄씩 merge 줄 선택 
	public void mergeToRight(int line) {
		
			Element contentE1 = root1.getElement(line - 1);

			int start1 = contentE1.getStartOffset();
			int end1 = contentE1.getEndOffset();
			String repLine = doc1.getText(start1,end1);
					
			Element contentE2 = root2.getElement(line - 1);

	        int start2 = contentE2.getStartOffset();
	        int end2 = contentE2.getEndOffset();
	        
	        try {
	            doc2.remove(start2, end2 - start2 - 1);
	            doc2.insertString(start2, repLine, null);
	        } catch (BadLocationException e) {
	            e.printStackTrace();
	        }
	        
	//documentation listener사용
	}
	
	public void mergeToLeft() {
	
	}
	public void 
	
}
