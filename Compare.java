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
         if(LCS(line,line2)!=line.length()) {//두 문자열이 다른경우들

            if(line!="\n"&&line2!="\n") {//두 문자들이 다를경우
               sameline = hasEqual(lineNum, line,s2);
               spaceNum =sameline-lineNum;
               if(spaceNum==0) {
                  sameline = hasEqual(lineNum, line2,s);
                  if(spaceNum ==0) {
                     left.add(line);
                     right.add(line2);
                     a.add(new diff(false,false,spaceNum,left,right));
                  }
                  else {//left파일에서 right파일의 lineNum이 다른곳에서 존재함. 공백생긴다.
                     for(i=0; i<spaceNum+1;i++) {
                        reader.readLine();
                     }

                     left.add(line);
                     right.add(line2);
                     a.add(new diff(false, true,spaceNum, left, right));
                  }
               }
               else {//right파일에서 left파일의 lineNum이 다른곳에서 존재함. 공백생긴다.
                  for(i=0; i<spaceNum+1;i++) {
                     right.add(line2);
                     reader2.readLine();
                  }
                  left.add(line);
                  
                  a.add(new diff(true, false,spaceNum, left, right));
               }
            }
            else if(line == "\n" &&line2 !="\n") {//공백,~공백
               sameline = hasEqual(lineNum, line2, s);
               spaceNum =sameline-lineNum;
               if(spaceNum == 0) {//밑에 동일한거 없다. 그냥 다른거임 ex) s2
                  left.add(line);
                  right.add(line2);
                  a.add(new diff(false, false,spaceNum, left,right));
                  }
               else {//밑에 같은거 있다. 공백 생겨야됨
                  
                  for(i=0; i<spaceNum;i++) {
                     line =reader.readLine();
                  }
                  left.add(line);
                  right.add(line2);
                  //오른쪽 파일에 공백 미뤄져서 창에 표시되어야한다.
                  a.add(new diff(false, true,spaceNum, left,right));
               }
            }
            else{//~공백, 공백
               sameline = hasEqual(lineNum, line, s2);
               spaceNum =sameline-lineNum;
               if(spaceNum == 0) {//밑에 동일한거 없다. 그냥 다른거임 ex) s2
                  left.add(line);
                  right.add(line2);
                  a.add(new diff(false, false,spaceNum, left,right));
                  }
               else {//밑에 같은거 있다. 공백 생겨야됨
                  for(i=0; i<spaceNum+1;i++) {
                     reader2.readLine();
                  }
                  left.add(line);
                  right.add(line2);
                  //왼쪽 파일에 공백 미뤄져서 창에 표시되어야한다.
                  a.add(new diff(true,false,spaceNum,left,right));
            }
         
   
      }
   
      System.out.println(a.get(0).spaceNum+"//"+a.get(0).left+"//"+a.get(0).right);
      }
 
         
   }
      reader.close();
      reader2.close();
}
   //a와 b의 동일한 문자 개수 출력

   //같은 문자의 개수를 출력해준다. 띄어쓰기도 문자로 본다.
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