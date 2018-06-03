package controllers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

import javax.print.attribute.AttributeSet;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.text.Utilities;


public class Merge_control {
	public Merge_control() {
	}
	public Merge_control(JTextPane first, JTextPane second,int pos,int i) throws BadLocationException {
		
		int lineNum = getLineCount(first,pos);	
		System.out.println("Successfully Construct Merge_control*");
		lineNum = getLineCount(first,pos);		
		
		
		/*if(i == 0)
			MergetoLeft(first,second,lineNum);
		else*/
			MergetoRight(first,second,lineNum);
		
		
		 
	}	

	
	
	public void MergetoRight(JTextPane first,JTextPane second,int lineNum) throws BadLocationException {
		System.out.println("Enter the MergetoRIGHT*");
		StyledDocument doc1 = first.getStyledDocument();
		StyledDocument doc2 = second.getStyledDocument();		
		Element root1 = doc1.getDefaultRootElement();
		Element root2 = doc2.getDefaultRootElement();
		String repLine = new String();
	
		
		ArrayList<Color> ColorSet = new ArrayList<Color>();
		
		
		/*//////////////test
		Element temp3 = root1.getElement(lineNum-1);
		Element temp4 = root2.getElement(lineNum-1);
		
		int start3 = temp3.getStartOffset();
		int end3 = temp4.getEndOffset();
		int start4 = temp3.getStartOffset();
		int end4 = temp4.getEndOffset();
		
		repLine = doc1.getText(start3, end3-start3);
		
		System.out.println("선택된 줄은 "+repLine);

		System.out.println("시작 offset "+start3+"end 오프셋은"+end3);
		////////////////////*/
		
		
		for(int i=0;;i++) {//Colorset만드려고
			Element temp = root1.getElement(lineNum-1+i);//다음 엘리먼트 
			Color Colr = getColor(temp);
			if((Colr == Color.YELLOW) ||(Colr == Color.GRAY)) {
			ColorSet.add(Colr);
			}
			else {
				System.out.println("라인을 다시 선택하세요");
				break;
			}
		}//colorset저장 size구하면 라인수 알 수 있음
		
		
		//Colorset 임의로 넣기
		ColorSet.add(Color.yellow);
		ColorSet.add(Color.yellow);
		
		for(int j=ColorSet.size()-1;j>=0;j--)
		{
			if(ColorSet.size() ==0)
			{
				System.out.println("Colorset의 size가 0입니다.");
				break;
			}
			System.out.println("Colorset의 size가"+ColorSet.size()+"입니다.");
			Element temp1 = root1.getElement(lineNum-1+j);
			Element temp2 = root2.getElement(lineNum-1+j);
			
			int start1 = temp1.getStartOffset();
			int end1 = temp1.getEndOffset();
			int start2 = temp2.getStartOffset();
			int end2 = temp2.getEndOffset();
			
			
			if(ColorSet.get(j) == Color.yellow) {
				
				repLine = doc1.getText(start1, end1-start1);
				doc2.remove(start2, end2-start2);
				doc2.insertString(start2, repLine, null);
				
	
				Style style = first.addStyle("String", null);
				StyleConstants.setForeground(style, Color.black);
				StyleConstants.setBackground(style, Color.white);
				doc1.setCharacterAttributes(start1, end1-start1, style, true);
           }
			
			
			else if(ColorSet.get(j) == Color.GRAY) {
				System.out.println("++++gray color line 선택됨");
				String tt = doc1.getText(start1, end1-start1);

				System.out.println("++++삭제할 텍스트는"+tt);
				doc1.remove(start1, end1-start1);;
				System.out.println("++++doc1에서 제거");
				doc2.remove(start2, end2-start2);

				System.out.println("++++doc2에서 제거");
			}
			else {
				System.out.println("왜 이상한 색이 저장되어 있지?");
				break;
			}
				
		}
		
		///doc2의 string 출력해보기
		 Element l = doc1.getDefaultRootElement();
		 Element r = doc2.getDefaultRootElement();
		 int s1 =l.getStartOffset();
		 int e1 =l.getEndOffset();
		 int s2 = r.getStartOffset();
		 int e2 = r.getEndOffset();
		 String lline = new String(doc1.getText(s1, e1-s1));
		 String rline = new String(doc1.getText(s2, e2-s2));
		 
		 System.out.println(lline);
		 System.out.println("*********************************************************");
		 System.out.println(rline);
		 
		 
		}
	
	public void MergetoLeft(JTextPane first,JTextPane second,int lineNum) throws BadLocationException {
		System.out.println("Enter the MergetoLEFT*");
		Document doc1 = first.getStyledDocument();
		Document doc2 = second.getStyledDocument();		
		Element root1 = doc1.getDefaultRootElement();
		Element root2 = doc2.getDefaultRootElement();
		String repLine = new String();
		
		ArrayList<Color> ColorSet = new ArrayList<Color>();
		
		
		for(int i=0;;i++) {//Colorset만드려고
			Element temp = root2.getElement(lineNum-1+i);//다음 엘리먼트 
			Color Colr = getColor(temp);
			if((Colr == Color.YELLOW) ||(Colr == Color.GRAY)) {
			ColorSet.add(Colr);
			}
			else {
				System.out.println("라인을 다시 선택하세요");
				break;
			}
		}//colorset저장 size구하면 라인수 알 수 있음
		
		
		
		for(int j=ColorSet.size()-1;j>=0;j--)
		{
			if(ColorSet.size() ==0)
			{
				System.out.println("Colorset의 size가 0입니다.");
				break;
			}
			Element temp1 = root1.getElement(lineNum-1+j);
			Element temp2 = root2.getElement(lineNum-1+j);
			
			int start1 = root1.getStartOffset();
			int end1 = root1.getEndOffset();
			int start2 = root2.getStartOffset();
			int end2 = root2.getEndOffset();
			
			
			if(ColorSet.get(j) == Color.yellow) {
				
				repLine = doc2.getText(start2, end2-start2+1);
				doc2.remove(start2, end2-start2+1);
				doc2.insertString(start2, repLine, null);
			}
			
			else if(ColorSet.get(j) == Color.GRAY) {
				doc1.remove(start1, end1-start1);;
				doc2.remove(start2, end2-start2);
			}
			else {
				System.out.println("왜 이상한 색이 저장되어 있지?");
				break;
			}
				
		}
	}
	
	public int getLineCount(JTextPane textPane, int pos)
	{
		int lineCount = (pos==0) ? 1 : 0;
        try {
            int offs=pos;
            while( offs>0) {
                offs=Utilities.getRowStart(textPane,offs)-1; // 오프셋으로 로우스타트 알 수 있음
                lineCount++;
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        System.out.println("선택된 라인은" + lineCount);
        return lineCount;
        //line rn(row number) = 클릭된줄넘버 저장 
        
	}

	public int getLineCount(JTextPane textPane){ 
        int lineCount=0;
        Scanner sc=new Scanner(textPane.getText());
        while(sc.hasNextLine()){
            String line = sc.nextLine(); 
            lineCount++;
       }
        System.out.println("라인넘버는" + lineCount);
        return lineCount;
    }
	public boolean validColor(Element elem) {
		
		Color colr = getColor(elem);
		if((colr== Color.YELLOW) || (colr== Color.ORANGE) ||(colr== Color.GRAY))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public Color getColor(Element elem) {//++++++++++++찌효 만들어주세영
		Color colr = Color.YELLOW;
		if((colr== Color.YELLOW) || (colr== Color.ORANGE) ||(colr== Color.GRAY))
		{
			return colr;
		}
		else
		{
			return colr;
		}
	}
}
