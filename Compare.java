package simple_merge;

import java.io.*;
import java.util.*;

import javax.swing.JTextArea;

public class Compare {

   private int[][] LCStable;
   private int lineNum=0;
   
   ArrayList<diff> a = new ArrayList<diff>();
   
   public Compare(JTextArea first, JTextArea second) throws IOException {
      
      StringReader read = new StringReader(first.getText());
      StringReader read2 = new StringReader(second.getText());

      BufferedReader reader = new BufferedReader(read);
      BufferedReader reader2 = new BufferedReader(read2);
      
      Scanner s = new Scanner(reader);
      Scanner s2 = new Scanner(reader2);
      
      String line = null;
      String line2 = null;
      ArrayList<String> left = new ArrayList<String>();
      ArrayList<String> right= new ArrayList<String>();
      int sameline = 0;
      int spaceNum;
      int i;
   
      while (s.hasNextLine() && s2.hasNextLine()) {
    	  line= s.nextLine();
    	  line2 =s2.nextLine();
         lineNum++;
         if(LCS(line,line2)!=line.length()) {//�� ���ڿ��� �ٸ�����

            if(line!="\n"&&line2!="\n") {//�� ���ڵ��� �ٸ����
               sameline = hasEqual(lineNum, line,s2);
               spaceNum =sameline-lineNum;
               if(spaceNum==0) {
                  sameline = hasEqual(lineNum, line2,s);
                  if(spaceNum ==0) {
                     left.add(line);
                     right.add(line2);
                     a.add(new diff(false,false,spaceNum,left,right));
                  }
                  else {//left���Ͽ��� right������ lineNum�� �ٸ������� ������. ��������.
                     for(i=0; i<spaceNum+1;i++) {
                        reader.readLine();
                     }

                     left.add(line);
                     right.add(line2);
                     a.add(new diff(false, true,spaceNum, left, right));
                  }
               }
               else {//right���Ͽ��� left������ lineNum�� �ٸ������� ������. ��������.
                  for(i=0; i<spaceNum+1;i++) {
                     right.add(line2);
                     reader2.readLine();
                  }
                  left.add(line);
                  
                  a.add(new diff(true, false,spaceNum, left, right));
               }
            }
            else if(line == "\n" &&line2 !="\n") {//����,~����
               sameline = hasEqual(lineNum, line2, s);
               spaceNum =sameline-lineNum;
               if(spaceNum == 0) {//�ؿ� �����Ѱ� ����. �׳� �ٸ����� ex) s2
                  left.add(line);
                  right.add(line2);
                  a.add(new diff(false, false,spaceNum, left,right));
                  }
               else {//�ؿ� ������ �ִ�. ���� ���ܾߵ�
                  
                  for(i=0; i<spaceNum;i++) {
                     line =reader.readLine();
                  }
                  left.add(line);
                  right.add(line2);
                  //������ ���Ͽ� ���� �̷����� â�� ǥ�õǾ���Ѵ�.
                  a.add(new diff(false, true,spaceNum, left,right));
               }
            }
            else{//~����, ����
               sameline = hasEqual(lineNum, line, s2);
               spaceNum =sameline-lineNum;
               if(spaceNum == 0) {//�ؿ� �����Ѱ� ����. �׳� �ٸ����� ex) s2
                  left.add(line);
                  right.add(line2);
                  a.add(new diff(false, false,spaceNum, left,right));
                  }
               else {//�ؿ� ������ �ִ�. ���� ���ܾߵ�
                  for(i=0; i<spaceNum+1;i++) {
                     reader2.readLine();
                  }
                  left.add(line);
                  right.add(line2);
                  //���� ���Ͽ� ���� �̷����� â�� ǥ�õǾ���Ѵ�.
                  a.add(new diff(true,false,spaceNum,left,right));
            }
         
   
      }
   
      System.out.println(a.get(0).spaceNum+"//"+a.get(0).left+"//"+a.get(0).right);
      }
 
         
   }
      reader.close();
      reader2.close();
}
   //a�� b�� ������ ���� ���� ���

   //���� ������ ������ ������ش�. ���⵵ ���ڷ� ����.
   private int LCS(String a, String b) {
      LCStable= new int[a.length()+1][b.length()+1];
      int i,j;
   
      for(i=1; i<LCStable.length;i++) {
         for(j=1;j<LCStable[i].length;j++) {
            if(a.charAt(i-1)==b.charAt(j-1)) {
               LCStable[i][j] = LCStable[i-1][j-1]+1;
            }
            else {
               LCStable[i][j] = MAX(LCStable[i-1][j], LCStable[i][j-1]);
            }
         }
      }
      return LCStable[a.length()][b.length()];
   }

   private int MAX(int upper, int left) {
      return upper>left ? upper:left;
   }
   
   private int hasEqual(int l, String s, Scanner r) throws IOException {
	  
      for(int i=0; i<l;i++) {
    	  r.nextLine();
      }
      String line;
      while(r.hasNextLine()) {
    	 line = r.nextLine();
         l++;
         if(LCS(line,s)==s.length()) {
            break;
         }
         
      }
      return l;
   }
   
}