import java.awt.Color;
import java.io.*;
import java.util.*;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

//compare시 객체생성, 왼쪽이1 오른쪽이 2
public class mergeText {

	JTextPane left = new JTextPane();
	JTextPane right = new JTextPane();
	Document doc1, doc2;
	Element root1, root2;
	int count;
	
	public mergeText() {		
	}
	
	public mergeText(JTextPane first, JTextPane second) {
		first.getDocument().addDocumentListener(new MyDocumentListener());
		second.getDocument().addDocumentListener(new MyDocumentListener()); //같은 리스너 사용하기 방법 까먹음--> 변경 부분만 업데이트
		//utilites 의 getrowstart로 offset이 있다면 어디서 시작하는지 받을 수 있음. 라인넘버는?
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
	public void mergeToRight(int line) throws BadLocationException{
			Color colr = Color.WHITE;
			int start1=0,end1=0;
			int start2=0,end2=0;
			
			ArrayList<Element> elementSet = new ArrayList<Element>();
			StyledDocument leftdoc = left.getStyledDocument();
			StyledDocument rightdoc = right.getStyledDocument();
			
			Element root1 = leftdoc.getDefaultRootElement();
			Element root2 = rightdoc.getDefaultRootElement();
			
			
			//다시하기 다음줄 색깔을 미리 알아야 함 고치기 line 또 써야해서 line값 변하면 안됨 
			do {
				
				Element text1 = root1.getElement(line-1);		
				colr = StyleConstants.getBackground(text1.getAttributes());
				
				elementSet.add(text1);
				
				
				line++;
			}while((colr == Color.GRAY) || (colr == Color.YELLOW) || (colr == Color.ORANGE));
			
			start1 = elementSet.get(0).getStartOffset();
			for(int i =0;i<elementSet.size();i++)
			{
				end1 = elementSet.get(i).getEndOffset();
			}
			
			
			Element text2 = root2.getElement(line-1);
			start2 = text2.getStartOffset();
			for(int j=0;j<elementSet.size()-1;j++) {
				text2 = root2.getElement(line-1+j);
				end2 = text2.getEndOffset();
			}
			
			/*Element elem=((StyledDocument)left.getDocument()).getCharacterElement(line-1);
			Color colr= StyleConstants.getBackground(elem.getAttributes());
			*/
			String repLine = left.getText(start1,end1);
	        try {
	            rightdoc.remove(start2, end2 - start2 - 1);
	            rightdoc.insertString(start2, repLine, null);//documentation 변경 완료 
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
