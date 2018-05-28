import java.io.*;
import java.util.*;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
		first.getDocument().addDocumentListener(new MyDocumentListener());
		second.getDocument().addDocumentListener(new MyDocumentListener()); //같은 리스너 사용하기 방법 까먹음--> 변경 부분만 업데이트
		
		doc1 = first.getDocument();
		root1 = doc1.getDefaultRootElement();
	    doc2 = second.getDocument();
	    root2 = doc2.getDefaultRootElement();
	}
	class MyDocumentListener implements DocumentListener {
	    String newline = "\n";
	 
	    public void insertUpdate(DocumentEvent e) {
	    
	    }
	    public void removeUpdate(DocumentEvent e) {

	    }
	    public void changedUpdate(DocumentEvent e) {
	        //Plain text components do not fire these events
	    }

	}
	//한 줄씩 merge 줄 선택 gui에서 textarea 이벤트리스너 등록해야됨 
	public void mergeToRight(int line) throws BadLocationException {
		
			Element contentE1 = root1.getElement(line - 1);

			int start1 = contentE1.getStartOffset();
			int end1 = contentE1.getEndOffset();
			String repLine = doc1.getText(start1,end1);
					
			Element contentE2 = root2.getElement(line - 1);

	        int start2 = contentE2.getStartOffset();
	        int end2 = contentE2.getEndOffset();
	        
	        try {
	            doc2.remove(start2, end2 - start2 - 1);
	            doc2.insertString(start2, repLine, null);//documentation 변경 완료 
	        } catch (BadLocationException e) {
	            e.printStackTrace();
	        }
	        
	}
	
	public void mergeToLeft(int line) throws BadLocationException {
		Element contentE2 = root2.getElement(line - 1);

		int start2 = contentE2.getStartOffset();
		int end2 = contentE2.getEndOffset();
		String repLine = doc2.getText(start2,end2);
				
		Element contentE1 = root1.getElement(line - 1);

        int start1 = contentE1.getStartOffset();
        int end1 = contentE1.getEndOffset();
        
        try {
            doc1.remove(start1, end1 - start1 - 1);
            doc1.insertString(start1, repLine, null);//documentation 변경 완료 
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
	}

	
}
