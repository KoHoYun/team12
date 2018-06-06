package controllers;

import models.*;
import views.*;

import java.awt.Color;
import java.io.*;
import java.util.*;

import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Compare_control {

   private int[][] LCStable;

   private int offset;
   private int offset2;

   private String line = null;
   private String line2 = null;
   private String whenNull = "                                 ";//rep

   private int spaceNum;
   private int leftHas;
   private int rightHas;
   private int lineNum = 0; // line number of styledocument
   private int left_num = 0; // line number of left file
   private int right_num = 0; // line number of right file
   private boolean isEqual = false;
   private boolean isEqual2 = true;//
   private ArrayList<Character> temp = new ArrayList<Character>();
   private ArrayList<Character> temp2 = new ArrayList<Character>();

   public Compare_control(JTextPane first, JTextPane second) throws IOException, Exception {

      StringReader read = new StringReader(first.getText());
      StringReader read2 = new StringReader(second.getText());

      BufferedReader reader = new BufferedReader(read);
      BufferedReader reader2 = new BufferedReader(read2);

      Scanner s = new Scanner(reader);
      Scanner s2 = new Scanner(reader2);
      int i;

      offset = getOffset(first);
      offset2 = getOffset(second);

      /* If both files are empty. */
      if (!s.hasNextLine() && !s2.hasNextLine()) {
         isEqual = true;
      }

      while (s.hasNextLine() && s2.hasNextLine()) {
         line = s.nextLine();
         line2 = s2.nextLine();

         lineNum++;
         right_num++;
         left_num++;
         if (lineNum == 1) {
            insert(first, Color.black, Color.black, "\n%");
            insert(second, Color.black, Color.black, "\n%");
         }
         /* both string are different */
         if (LCS(line, line2) != MAX(line.length(), line2.length())) {
            isEqual2 = false;
            if (line_check(line, line2)) {
               insert(first, Color.red, Color.YELLOW, line);
               insert(second, Color.red, Color.YELLOW, line2);
            } else {
               if (line.length() != 0 && line2.length() != 0) {
                  rightHas = hasEqual(right_num, line, second);
                  leftHas = hasEqual(left_num, line2, first);

                  if (rightHas == right_num && leftHas == left_num) {
                     inLineDiff(line, line2, first, second);
                     insert(first, Color.black, Color.black, "");
                     insert(second, Color.black, Color.black, "");

                  } else {
                     if (rightHas == right_num && leftHas != left_num) {
                        spaceNum = leftHas - left_num;
                        for (i = 0; i < spaceNum; i++) {
                           if (line.length() != 0) {
                              insert(first, Color.red, Color.YELLOW, line);
                           } else {
                              insert(first, Color.red, Color.YELLOW, whenNull);
                           }
                           insert(second, Color.red, Color.GRAY, whenNull);
                           if (s.hasNextLine()) {
                              line = s.nextLine();
                              left_num++;
                           }
                        }
                        lineNum += spaceNum;
                        insert(first, Color.BLACK, Color.WHITE, line);
                        insert(second, Color.BLACK, Color.WHITE, line2);

                        // addSpace(spaceNum, lineNum, leftHas, left_num, line, line2, first, second,
                        // s);
                     }

                     else if (rightHas != right_num && leftHas == left_num) {
                        spaceNum = rightHas - right_num;
                        for (i = 0; i < spaceNum; i++) {
                           if (line2.length() != 0) {
                              insert(second, Color.red, Color.YELLOW, line2);
                           } else {
                              insert(second, Color.red, Color.YELLOW, whenNull);
                           }
                           insert(first, Color.red, Color.GRAY, whenNull);
                           if (s2.hasNextLine()) {
                              line2 = s2.nextLine();
                              right_num++;
                           }
                        }
                        lineNum += spaceNum;
                        insert(first, Color.BLACK, Color.WHITE, line);
                        insert(second, Color.BLACK, Color.WHITE, line2);
                        // addSpace(spaceNum, lineNum, rightHas, right_num, line2, line, second, first,
                        // s2);
                     } else {// rightHas �뜝�럩�뮡�뜝�럡�맖�뜝�럩紐드슖�댙�삕 �뜝�럥由��뜝�럩�겱

                     }
                  }

               }

               else if (line.length() == 0 && line2.length() != 0) {// 占썩뫀踰됧첎占�,~占썩뫀踰됧첎占�
                  leftHas = hasEqual(left_num, line2, first);
                  if (leftHas == left_num) {// �뛾�룆理먫굢占� �뜝�럥吏쀥뜝�럩逾у뜝�럥由썸ㅀ袁ъ삕 �뜝�럥�뵪�뜝�럥堉�.
                     insert(first, Color.red, Color.YELLOW, whenNull);
                     insert(second, Color.red, Color.YELLOW, line2);
                  } else {// �뜝�럩�눁 占썩뫀踰됧첎�떣�삕占쎈빢嶺뚮씭�뒭野껓옙 �뜝�럥嫄쀥뜝�럩�굚, �뜝�럩沅� 占썩뫀踰됧첎�떣�삕占쎈빢嶺뚮씭�뒭野껓옙
                        // �뜝�럩�뤂�뜝�럡�돰
                     spaceNum = leftHas - left_num;
                     for (i = 0; i < spaceNum; i++) {
                        if (line.length() != 0) {
                           insert(first, Color.red, Color.YELLOW, line);
                        } else {
                           insert(first, Color.red, Color.YELLOW, whenNull);
                        }

                        insert(second, Color.red, Color.GRAY, whenNull);
                        if (s.hasNextLine()) {
                           line = s.nextLine();
                           left_num++;
                        }
                     }
                     lineNum += spaceNum;
                     insert(first, Color.BLACK, Color.WHITE, line);
                     insert(second, Color.BLACK, Color.WHITE, line2);

                     // addSpace(spaceNum, lineNum, leftHas, left_num, line, line2, first, second,
                     // s);
                  }
               } else {// ~占썩뫀踰됧첎占�, 占썩뫀踰됧첎占�
                  rightHas = hasEqual(right_num, line, second);
                  if (rightHas == right_num) {// �뛾�룆理먫굢占� �뜝�럥吏쀥뜝�럩逾у뜝�럥由썸ㅀ袁ъ삕 �뜝�럥�뵪�뜝�럥堉�.
                     insert(first, Color.red, Color.YELLOW, line);
                     insert(second, Color.red, Color.YELLOW, whenNull);
                  } else {// �뜝�럩�눁 占썩뫀踰됧첎�떣�삕占쎈빢嶺뚮씭�뒭野껓옙 �뜝�럥嫄쀥뜝�럩�굚, �뜝�럩沅� 占썩뫀踰됧첎�떣�삕占쎈빢嶺뚮씭�뒭野껓옙
                        // �뜝�럩�뤂�뜝�럡�돰
                     spaceNum = rightHas - right_num;
                     for (i = 0; i < spaceNum; i++) {
                        if (line2.length() != 0) {
                           insert(second, Color.red, Color.YELLOW, line);
                        } else {
                           insert(second, Color.red, Color.YELLOW, whenNull);
                        }

                        insert(first, Color.red, Color.GRAY, whenNull);
                        if (s2.hasNextLine()) {
                           line2 = s2.nextLine();
                           right_num++;
                        }
                     }
                     lineNum += spaceNum;
                     insert(first, Color.BLACK, Color.WHITE, line);
                     insert(second, Color.BLACK, Color.WHITE, line2);
                     // addSpace(spaceNum, lineNum, rightHas, right_num, line2, line, second, first,
                     // s2);
                  }
               }
            }
         }

         else {
            insert(first, Color.BLACK, Color.WHITE, line);
            insert(second, Color.BLACK, Color.WHITE, line2);
         }

      }

      for (; s.hasNextLine();) {
         line = s.nextLine();
         insert(first, Color.red, Color.YELLOW, line);
         insert(second, Color.red, Color.GRAY, whenNull);
      }

      for (; s2.hasNextLine();) {
         line2 = s2.nextLine();
         insert(second, Color.red, Color.YELLOW, line2);
         insert(first, Color.red, Color.GRAY, whenNull);
      }

      if (!isEqual || !isEqual2) {
         // �뜝�럡�맜�뜝�럥�꽑繞벿〓맧�맪濾곌쒀�삕 嶺뚯쉻�삕�뜝�럩�쐳繞벿우삕�뜝�럥堉�
         remove(first, offset + 3);
         remove(second, offset2 + 3);
      }
      if (isEqual2) {
         new Identical_control(true);
      }

      reader.close();
      reader2.close();
   }

   private int LCS(String a, String b) {
      LCStable = new int[a.length() + 1][b.length() + 1];
      int i, j;

      for (i = 1; i < LCStable.length; i++) {
         for (j = 1; j < LCStable[i].length; j++) {
            if (a.charAt(i - 1) == b.charAt(j - 1)) {
               LCStable[i][j] = LCStable[i - 1][j - 1] + 1;
            } else {
               LCStable[i][j] = MAX(LCStable[i - 1][j], LCStable[i][j - 1]);
            }
         }
      }
      return LCStable[a.length()][b.length()];
   }

   private int MAX(int upper, int left) {
      return upper > left ? upper : left;
   }

   private int MIN(int upper, int left) {
      return upper < left ? upper : left;
   }

   private int hasEqual(int l, String s, JTextPane j) throws IOException {

      StringReader read = new StringReader(j.getText());
      BufferedReader reader = new BufferedReader(read);
      Scanner r = new Scanner(reader);
      int no = l;
      for (int i = 0; i < l; i++) {
         r.nextLine();
      }
      String lineTemp;
      while (r.hasNextLine()) {
         lineTemp = r.nextLine();
         if (lineTemp.equals("%")) {
            return no;
         } else {
            l++;
         }

         if (LCS(lineTemp, s) == MAX(s.length(), lineTemp.length())) {
            return l;
         }
      }
      return no;

   }

   private int getOffset(JTextPane textP) {
      StyledDocument doc = textP.getStyledDocument();
      return doc.getLength();
   }

   private void remove(JTextPane textP, int fin) throws Exception {
      StyledDocument doc = textP.getStyledDocument();
      doc.remove(0, fin);
   }

   private void insert(JTextPane textP, Color foreG, Color backG, String line) throws Exception {

      StyledDocument doc = textP.getStyledDocument();
      Style style = textP.addStyle("String", null);
      StyleConstants.setForeground(style, foreG);// �뼨�먯삕�뜝�럩�겱�뜝�럡�돰
      StyleConstants.setBackground(style, backG);// �뛾�룄�쀧몭�엪�삕繹먲옙
      // �뜝�럡�맜�뜝�럥裕됬뵓怨뺣쿅占싼놁쾵�뜝占�...
      doc.insertString(doc.getLength(), line + "\n", style);

      // doc.setParagraphAttributes(arg0, line.length(), style, true);
   }

   // �뜝�럥由� �뜝�럩逾у뜝�럩逾ε뜝�럥�닱�뜝�럥�뱺�뜝�럡�맋 �뜝�럥堉꾬옙紐닷뜝占� 占쎄껀�뜝�띂寃ュ뜝占�
   // �뜝�럩肉녑뜝�럩諭썲뜝�럥瑜� �뜝�럥堉꾬옙紐댐쭗猿볦삕占쎄껀�뜝占� �뜝�럡�돰�뇖�궪�삕
   private void inLineDiff(String l, String l2, JTextPane tP, JTextPane tP2) throws Exception {
      String[] part = l.split("\\s");
      String[] part2 = l2.split("\\s");
      // ArrayList<String> p = new ArrayList<String>();
      // ArrayList<String> p2 = new ArrayList<String>();
      // arraylist�뜝�럥�뱺 �뜝�럡�맜�뜝�럥�꽑�썒�슣�닁�뵳占�
      int j = 0;
      for (j = 0; j < part.length - 1; j++) {
         part[j] = part[j].toString() + " ";
      }
      for (j = 0; j < part2.length; j++) {
         part2[j] = part2[j].toString() + " ";
      }
      int min_array = MIN(part.length, part2.length);
      if (part.length > part2.length) {
         for (int n = 0; n < min_array; n++) {
            if (LCS(part[n], part2[n]) != MAX(part[n].length(), part2[n].length())) {
               insert_word(tP, Color.red, Color.orange, part[n]);
               insert_word(tP2, Color.red, Color.orange, part2[n]);
            } else {
               insert_word(tP, Color.red, Color.yellow, part[n]);
               insert_word(tP2, Color.red, Color.yellow, part2[n]);
            }
         }
         for (int n = min_array; n < part.length; n++) {
            insert_word(tP, Color.red, Color.orange, part[n]);
         }
      } else if (part.length < part2.length) {
         for (int n = 0; n < min_array; n++) {
            if (LCS(part[n], part2[n]) != MAX(part[n].length(), part2[n].length())) {
               insert_word(tP, Color.red, Color.orange, part[n]);
               insert_word(tP2, Color.red, Color.orange, part2[n]);
            } else {
               insert_word(tP, Color.red, Color.yellow, part[n]);
               insert_word(tP2, Color.red, Color.yellow, part2[n]);
            }
         }
         for (int n = min_array; n < part2.length; n++) {
            insert_word(tP2, Color.red, Color.orange, part2[n]);
         }
      } else {
         for (int n = 0; n < min_array; n++) {
            if (LCS(part[n], part2[n]) != MAX(part[n].length(), part2[n].length())) {
               insert_word(tP, Color.red, Color.orange, part[n]);
               insert_word(tP2, Color.red, Color.orange, part2[n]);
            } else {
               insert_word(tP, Color.red, Color.yellow, part[n]);
               insert_word(tP2, Color.red, Color.yellow, part2[n]);
            }
         }
      }

   }

   private void insert_word(JTextPane textP, Color foreG, Color backG, String line) throws Exception {
      StyledDocument doc = textP.getStyledDocument();
      Style style = textP.addStyle("String", null);
      StyleConstants.setForeground(style, foreG);
      StyleConstants.setBackground(style, backG);

      doc.insertString(doc.getLength(), line, style);
   }

   private boolean line_check(String l, String l2) {

      char[] k = null;
      char[] k2 = null;
      int j = 0;
      int j2 = 0;
      k = l.toCharArray();
      k2 = l2.toCharArray();

      temp.clear();
      temp2.clear();

      for (int i = 0; i < l.length(); i++) {
         if (k[i] != ' ') {

            temp.add(k[i]);
            j++;
         }
      }

      for (int i = 0; i < l2.length(); i++) {
         if (k2[i] != ' ') {
            temp2.add(k2[i]);
            j2++;
         }
      }

      if (LCS(temp.toString(), temp2.toString()) == MAX(3 * j, 3 * j2)) {
         return true;
      }
      return false;

   }

}
