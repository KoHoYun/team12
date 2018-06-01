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
	private String whenNull = "                                 ";

	private int spaceNum;
	private int leftHas;
	private int rightHas;
	private int lineNum = 0;
	private int left_num = 0;
	private int right_num = 0;
	private boolean isEqual = false;
	private boolean isEqual2 = true;

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

		if (!s.hasNextLine() && !s2.hasNextLine()) {// 두파일 모두 빈경우
			isEqual = true;
			new identicalFrame();
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
			
			if (LCS(line, line2) != MAX(line.length(), line2.length())) {// 두 문자열이 다른경우들
				isEqual2 = false;
				
				if (line.length() != 0 && line2.length() != 0) {
					rightHas = hasEqual(right_num, line, second);
					leftHas = hasEqual(left_num, line2, first);

					// 파일 밑에 어딘가 존재한다는 뜻
					if (rightHas == right_num && leftHas == left_num) {// 그냥 둘이 다른문자다.
						inLineDiff(line, line2, first, second);
						insert(first, Color.black, Color.black, "");
						insert(second, Color.black, Color.black, "");

					} else {
						if (rightHas == right_num && leftHas != left_num) {// 오른쪽 파일에 공백이 추가되어야 한다. 왼:노란색 오른:회색
							
							spaceNum = leftHas - left_num;
							for (i = 0; i < spaceNum; i++) {
								if (line.length() != 0) {
									insert(first, Color.BLACK, Color.YELLOW, line);
								} else {
									insert(first, Color.BLACK, Color.YELLOW, whenNull);
								}
								insert(second, Color.BLACK, Color.GRAY, whenNull);
								if (s.hasNextLine()) {
									line = s.nextLine();
									left_num++;
								}
							}
							lineNum += spaceNum;
							insert(first, Color.BLACK, Color.WHITE, line);
							insert(second, Color.BLACK, Color.WHITE, line2);
							
							//addSpace(spaceNum, lineNum, leftHas, left_num, line, line2, first, second, s);
						}
						
						
						else if (rightHas != right_num && leftHas == left_num) {
							spaceNum = rightHas - right_num;
							for (i = 0; i < spaceNum; i++) {
								if (line2.length() != 0) {
									insert(second, Color.BLACK, Color.YELLOW, line2);
								} else {
									insert(second, Color.BLACK, Color.YELLOW, whenNull);
								}
								insert(first, Color.BLACK, Color.GRAY, whenNull);
								if (s2.hasNextLine()) {
									line2 = s2.nextLine();
									right_num++;
								}
							}
							lineNum += spaceNum;
							insert(first, Color.BLACK, Color.WHITE, line);
							insert(second, Color.BLACK, Color.WHITE, line2);
							//addSpace(spaceNum, lineNum, rightHas, right_num, line2, line, second, first, s2);
						} else {// rightHas 우선으로 하자
							
						}
					}

				}

				else if (line.length() == 0 && line2.length() != 0) {// 공백,~공백
					leftHas = hasEqual(left_num, line2, first);
					if (leftHas == left_num) {// 밑에 동일한거 없다.
						insert(first, Color.BLACK, Color.YELLOW, whenNull);
						insert(second, Color.BLACK, Color.YELLOW, line2);
					} else {// 왼 공백수만큼 노랑, 오 공백수만큼 회색
						spaceNum = leftHas - left_num;
						for (i = 0; i < spaceNum; i++) {
							if (line.length() != 0) {
								insert(first, Color.BLACK, Color.YELLOW, line);
							} else {
								insert(first, Color.BLACK, Color.YELLOW, whenNull);
							}

							insert(second, Color.BLACK, Color.GRAY, whenNull);
							if (s.hasNextLine()) {
								line = s.nextLine();
								left_num++;
							}
						}
						lineNum += spaceNum;
						insert(first, Color.BLACK, Color.WHITE, line);
						insert(second, Color.BLACK, Color.WHITE, line2);
						
						
						//addSpace(spaceNum, lineNum, leftHas, left_num, line, line2, first, second, s);
					}
				} else {// ~공백, 공백
					rightHas = hasEqual(right_num, line, second);
					if (rightHas == right_num) {// 밑에 동일한거 없다.
						insert(first, Color.BLACK, Color.YELLOW, line);
						insert(second, Color.BLACK, Color.YELLOW, whenNull);
					} else {// 왼 공백수만큼 노랑, 오 공백수만큼 회색
						spaceNum = rightHas - right_num;
						for (i = 0; i < spaceNum; i++) {
							if (line2.length() != 0) {
								insert(second, Color.BLACK, Color.YELLOW, line);
							} else {
								insert(second, Color.BLACK, Color.YELLOW, whenNull);
							}

							insert(first, Color.BLACK, Color.GRAY, whenNull);
							if (s2.hasNextLine()) {
								line2 = s2.nextLine();
								right_num++;
							}
						}
						lineNum += spaceNum;
						insert(first, Color.BLACK, Color.WHITE, line);
						insert(second, Color.BLACK, Color.WHITE, line2);
						//addSpace(spaceNum, lineNum, rightHas, right_num, line2, line, second, first, s2);
					}
				}
			}

			else {

				// 같은 문자 추가
				insert(first, Color.BLACK, Color.WHITE, line);
				insert(second, Color.BLACK, Color.WHITE, line2);
				System.out.println("identical line");
			}

		}

		for (; s.hasNextLine();) {
			line = s.nextLine();
			insert(first, Color.BLACK, Color.YELLOW, line);
			insert(second, Color.BLACK, Color.GRAY, whenNull);
		}

		for (; s2.hasNextLine();) {
			line2 = s2.nextLine();
			insert(second, Color.BLACK, Color.YELLOW, line2);
			insert(first, Color.BLACK, Color.GRAY, whenNull);
		}

		if (!isEqual || !isEqual2) {
			// 넣어줬던거 지워준다
			remove(first, offset + 3);
			remove(second, offset2 + 3);
		}
		if (isEqual2) {
			new identicalFrame();
		}

		reader.close();
		reader2.close();
	}
	// a와 b의 동일한 문자 개수 출력

	// 같은 문자의 개수를 출력해준다. 띄어쓰기도 문자로 본다.
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
		StyleConstants.setForeground(style, foreG);// 글자색
		StyleConstants.setBackground(style, backG);// 배경색
		// 넣는법고민...
		doc.insertString(doc.getLength(), line + "\n", style);

		// doc.setParagraphAttributes(arg0, line.length(), style, true);
	}

	// 한 라인안에서 다른 부분 있을때 다른부분 색칠
	private void inLineDiff(String l, String l2, JTextPane tP, JTextPane tP2) throws Exception {
		String[] part = l.split("\\s");
		String[] part2 = l2.split("\\s");
		// ArrayList<String> p = new ArrayList<String>();
		// ArrayList<String> p2 = new ArrayList<String>();
		// arraylist에 넣어주기
		int j = 0;
		for (j = 0; j < part.length - 1; j++) {
			part[j] = part[j].toString() + " ";
		}
		for (j = 0; j < part2.length; j++) {
			part2[j] = part2[j].toString() + " ";
		}
		int min_array= MIN(part.length, part2.length);
		if (part.length > part2.length) {
			for (int n = 0; n < min_array; n++) {
				if (LCS(part[n], part2[n]) != MAX(part[n].length(), part2[n].length())) {
					insert_word(tP, Color.red, Color.orange, part[n]);
					insert_word(tP2, Color.red, Color.orange, part2[n]);
				} else {
					insert_word(tP, Color.black, Color.yellow, part[n]);
					insert_word(tP2, Color.black, Color.yellow, part2[n]);
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
					insert_word(tP, Color.black, Color.yellow, part[n]);
					insert_word(tP2, Color.black, Color.yellow, part2[n]);
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
					insert_word(tP, Color.black, Color.yellow, part[n]);
					insert_word(tP2, Color.black, Color.yellow, part2[n]);
				}
			}
		}

	}

	private void insert_word(JTextPane textP, Color foreG, Color backG, String line) throws Exception {
		StyledDocument doc = textP.getStyledDocument();
		Style style = textP.addStyle("String", null);
		StyleConstants.setForeground(style, foreG);// 글자색
		StyleConstants.setBackground(style, backG);// 배경색
		// 넣는법고민...
		doc.insertString(doc.getLength(), line, style);
	}
	
	/*public void addSpace(int space, int lineN, int has, int num, String l, String l2, JTextPane tP, JTextPane tP2, Scanner s) throws Exception {
		space = has - num;
		for (int i = 0; i < space; i++) {
			if (l.length() != 0) {
				insert(tP, Color.BLACK, Color.YELLOW, l);
			} else {
				insert(tP, Color.BLACK, Color.YELLOW, whenNull);
			}
			insert(tP2, Color.BLACK, Color.GRAY, whenNull);
			if (s.hasNextLine()) {
				l = s.nextLine();
				num++;
			}
		}
		lineN += space;
		insert(tP, Color.BLACK, Color.WHITE, l);
		insert(tP2, Color.BLACK, Color.WHITE, l2);
	}*/
}