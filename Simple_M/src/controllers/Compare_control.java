package controllers;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.Utilities;

import org.junit.Test;


public class Merge_control {
	
	private int pos;
	private int selectMerge;
	private JTextPane first;
	private JTextPane second;
	private int lineNum;
	
	public Merge_control() {
	}
	/*This is a constructor for merging text*/
	public Merge_control(JTextPane first, JTextPane second,int pos,int i) throws BadLocationException {
		
		
		LeftTextTest(first);
		setLeftText(first);
		setRightText(second);
		setLineNum(pos);
		
		
		
		RightTextTest(second);
		
		System.out.println("                                     ");	
		
		System.out.println("Successfully Construct Merge_control");	
		
		/*call a function depend on 'i' (i==0 means user selected Merge to left)*/
		if(i == 0)
			MergetoLeft();
		else
			MergetoRight();
		
		
		
		 
	}	
	//+++++++++++++++++++++++++Unit test+++++++++++++++++++++++
	public void LeftTextTest(JTextPane first) {
		assert first == getLeftText() : "invalid left text";
	}
	public void RightTextTest(JTextPane second) {
		assert second == getRightText() : "invalid right text";
	}
	

	
	public void setLeftText(JTextPane first) {
		this.first = first;
	}
	public void setRightText(JTextPane second) {
		this.second = second;
	}
	public JTextPane getLeftText()
	{
		return this.first;
	}
	public JTextPane getRightText()
	{
		return this.second;
	}

	/*this function return line Number from pos(where user clicked)*/
	private void setLineNum(int pos)
	{
		int lineCount = (pos==0) ? 1 : 0;
        try {
            int offs=pos;
            while( offs>0) {
                offs=Utilities.getRowStart(first,offs)-1;
                lineCount++;
            }
            lineNum = lineCount;
        } catch (BadLocationException e) {
            e.printStackTrace();
        }     
	}
	
	@Test
	private void MergetoRight() throws BadLocationException {
		System.out.println("-----Run Merge to Right-----");
		StyledDocument doc1 = first.getStyledDocument();//get StyledDocument to get color
		StyledDocument doc2 = second.getStyledDocument();		
		Element root1 = doc1.getDefaultRootElement();//root1 stores element line by line
		Element root2 = doc2.getDefaultRootElement();
		String repLine = new String();
	
		
		ArrayList<Color> ColorSet = new ArrayList<Color>();//ColorSet have colors
		
		
		for(int i=0;;i++) {
			
			Element temp = root1.getElement(lineNum-1+i);//temp stores the element corresponding to the line number-1+i.
			Element tempColr = doc1.getCharacterElement(temp.getStartOffset());//Stores the first letter of the element.

			Color Colr = StyleConstants.getBackground(tempColr.getAttributes());//Stores the color in the first letter of the element.

			/*Yellow means different line, Orange means different section in the different line, Gray is added line with blank*/
			if((Colr == Color.YELLOW) ||(Colr == Color.GRAY) ||(Colr == Color.ORANGE)) {
			ColorSet.add(Colr);
			}
			else {
				break;
			}
		}
		
		

		/*Check the ColorSet*/
		for(int j=ColorSet.size()-1;j>=0;j--)
		{
			if(ColorSet.size() ==0)//There
			{
				break;
			}
			Element temp1 = root1.getElement(lineNum-1+j);//Get a element corresponding lineNum-1+j frome left textpane
			Element temp2 = root2.getElement(lineNum-1+j);//Get a element from right textpane
			
			int start1 = temp1.getStartOffset();
			int end1 = temp1.getEndOffset();
			int start2 = temp2.getStartOffset();
			int end2 = temp2.getEndOffset();
			
			
			if((ColorSet.get(j) == Color.yellow)||(ColorSet.get(j) == Color.orange)) {//if the line color is yellow or orange
				
				repLine = doc1.getText(start1, end1-start1);//get a colored line from doc1(left textpane)
				doc2.remove(start2, end2-start2);//remove the line at the doc2(right textpane)
				doc2.insertString(start2, repLine, null);//Insert a string at the place where the line is to be deleted.
				
				//after replacing a line, set color again.
				Style style = first.addStyle("String", null);
				StyleConstants.setForeground(style, Color.black);
				StyleConstants.setBackground(style, Color.white);
				doc1.setCharacterAttributes(start1, end1-start1, style, true);
           }
			
			
			else if(ColorSet.get(j) == Color.GRAY) {//if the line color is gray,delete a line at doc1,doc2
				doc1.remove(start1, end1-start1);
				doc2.remove(start2, end2-start2);
			}
			else {
				break;
			}
				
		}
		 
		}
	
	/*Same as Merge to Right */
	private void MergetoLeft() throws BadLocationException {
		System.out.println("-----Run Merge to Right-----");
		StyledDocument doc1 = first.getStyledDocument();
		StyledDocument doc2 = second.getStyledDocument();		
		Element root1 = doc1.getDefaultRootElement();
		Element root2 = doc2.getDefaultRootElement();
		String repLine = new String();
	
		
		ArrayList<Color> ColorSet = new ArrayList<Color>();
		
		
		for(int i=0;;i++) {
			
			Element temp = root2.getElement(lineNum-1+i);
			Element tempColr = doc2.getCharacterElement(temp.getStartOffset());

			Color Colr = StyleConstants.getBackground(tempColr.getAttributes());

			if((Colr == Color.YELLOW) ||(Colr == Color.GRAY)||(Colr == Color.ORANGE)) {
			ColorSet.add(Colr);
			}
			else {
				break;
			}
		}
		
		
		
		for(int j=ColorSet.size()-1;j>=0;j--)
		{
			if(ColorSet.size() ==0)
			{
				break;
			}
			Element temp1 = root1.getElement(lineNum-1+j);
			Element temp2 = root2.getElement(lineNum-1+j);
			
			int start1 = temp1.getStartOffset();
			int end1 = temp1.getEndOffset();
			int start2 = temp2.getStartOffset();
			int end2 = temp2.getEndOffset();
			
			
			if((ColorSet.get(j) == Color.yellow)||(ColorSet.get(j) == Color.orange)) {
				
				repLine = doc2.getText(start2, end2-start2);
				doc1.remove(start1, end1-start1);
				doc1.insertString(start1, repLine, null);
				
	
				Style style = second.addStyle("String", null);
				StyleConstants.setForeground(style, Color.black);
				StyleConstants.setBackground(style, Color.white);
				doc2.setCharacterAttributes(start2, end2-start2, style, true);
           }
			
			
			else if(ColorSet.get(j) == Color.GRAY) {
				doc1.remove(start1, end1-start1);;
				doc2.remove(start2, end2-start2);
			}
			else {
				break;
			}
				
		}
		 
		}

}
//end
