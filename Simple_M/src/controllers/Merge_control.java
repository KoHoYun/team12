package controllers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.Utilities;


public class Merge_control {
	public Merge_control() {
	}
	public Merge_control(JTextPane first, JTextPane second,int pos,int i) throws BadLocationException {
		
		int lineNum = getLineCount(first,pos);	
		System.out.println("Successfully Construct Merge_control*");
		lineNum = getLineCount(first,pos);
		
		if(i == 0)
			MergetoLeft(first,second,lineNum);
		else
			MergetoRight(first,second,lineNum);
		
		
		 
	}	

	
	
	public void MergetoRight(JTextPane first,JTextPane second,int lineNum) throws BadLocationException {
		System.out.println("Enter the MergetoLeft*");
		Document doc1 = first.getStyledDocument();
		Document doc2 = second.getStyledDocument();		
		Element root1 = doc1.getDefaultRootElement();
		Element root2 = doc2.getDefaultRootElement();
		
		ArrayList<String> repLine = new ArrayList<String>();
		ArrayList<Color> ColorSet = new ArrayList<Color>();
		
		Element text1 = root1.getElement(lineNum-1);//linenum에 맞게 추출되는지 확인
		for(int j=0;;j++) {//Colorset만드려고
			Color Colr = getColor(text1);
			if((Colr == Color.ORANGE) || (Colr == Color.YELLOW) ||(Colr == Color.GRAY)) {
			ColorSet.add(Colr);
			text1 = root1.getElement(lineNum-1+j);//다음 엘리먼트 
			}
			else
				break;
		}//colorset저장 size구하면 라인수 알 수 있음
		
		int start1=text1.getStartOffset(), end1=text1.getEndOffset();
		int start2=0, end2=0;
		
		//컬러 맞으면 반복
		try {
			repLine.add(doc1.getText(start1, end1-start1));
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//repLine에 복사해야 하는 텍스트 저장
		
		//세가지 경우의 수로 나누어 작성 모두 회색/모두 노랑색/회색+노랑색
		
		//colorset을 뒤에서 부터 검사해서 만약 회색이면 그줄을 양쪽에서 지운다. 노랑색일땐 아래 참고하기  
		
		//중복되는거 개짱많음 어떻게 함수로 빼서 사용할지 생각해보기  
		
		
		
		
		text1 = root1.getElement(lineNum-1);
		Element text2 = root2.getElement(lineNum-1);
		//++++++++++++++++++++++++++++모두 gray++++++++++++
		for(int j=ColorSet.size()-1;j>=0;j--)
		{
			if(ColorSet.get(j) == Color.GRAY)
			{
				if(repLine.size()==1)
				{
					start2 = text2.getStartOffset();
					end2 = text2.getEndOffset();
				}
				else if(repLine.size()>1)
				{
					start2 = text2.getStartOffset();//오른쪽꺼 시작 오프셋 구하기
					text2 = root2.getElement(lineNum-1+repLine.size()-1);//마지막 줄lineNum-1 => lineNum에 해당하는 element구하기 element는 0시작하니까, repLine.size()-1는 마지막줄 나타내기 위해서
					end2 = text2.getEndOffset(); //마지막 줄 엔드오프셋
				}
				doc2.remove(start2,end2); //doc2에서 제거 
			
				//doc1에서 remove
				
				if(repLine.size()==1)
				{
					start1 = text1.getStartOffset();
					end1 = text1.getEndOffset();
				}
				else if(repLine.size()>1)
				{
					start1 = text1.getStartOffset();//오른쪽꺼 시작 오프셋 구하기
					text1 = root1.getElement(lineNum-1+repLine.size()-1);//마지막 줄lineNum-1 => lineNum에 해당하는 element구하기 element는 0시작하니까, repLine.size()-1는 마지막줄 나타내기 위해서
					end1 = text1.getEndOffset(); //마지막 줄 엔드오프셋
				}
				doc1.remove(start1, end1);	
				
			
			}
		}
		//++++++++++++++++++++++++++++++++++++++++++++++++
		
		
		
		
		
		
		
		
		
		
		//++++++++++++++++++++모두 yellow or orange++++++++++++++++++++++++++++
		boolean gray = false;
		for(int j=0;j<ColorSet.size();j++)
		{
			if(ColorSet.get(j)==Color.GRAY)
				gray = true;
		}
		
		///아래의 코드는 노랑색만 가능 (gray == false일때)
		if(repLine.size()==1)
		{
			start2 = text2.getStartOffset();
			end2 = text2.getEndOffset();
		}
		else if(repLine.size()>1)
		{
			start2 = text2.getStartOffset();//오른쪽꺼 시작 오프셋 구하기
			text2 = root2.getElement(lineNum-1+repLine.size()-1);//마지막 줄lineNum-1 => lineNum에 해당하는 element구하기 element는 0시작하니까, repLine.size()-1는 마지막줄 나타내기 위해서
			end2 = text2.getEndOffset(); //마지막 줄 엔드오프셋
		}
		
		doc2.remove(start2,end2); //doc에서 제거 
		
		Style style = first.addStyle("String", null);
		for(int j=repLine.size();j>0;j--) { //1번부터 넣으면 offset이 바껴서 못찾음
		doc2.insertString(start2,repLine.get(j),style);
		}
		//회색이면 회색인 오프셋 받아서 삭제하면됨
		
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
		
		
		//end1 = root1.getEndOffset();
			
		//lineElem = elem.getElement(lineCount);
		
	}
	public void MergetoLeft(JTextPane first,JTextPane second,int lineNum) {
		Document doc1 = first.getStyledDocument();
		Document doc2 = second.getStyledDocument();		
		Element root1 = doc1.getDefaultRootElement();
		Element root2 = doc2.getDefaultRootElement();
		//pos넣어서 getElementIndex 해보기 아마 될듯
	
		
		ArrayList<String> repLine = new ArrayList<String>();
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
