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
	private String whenNull = "                                 ";

	private int spaceNum;
	private int leftHas;
	private int rightHas;
	private int lineNum = 0;
	private int left_num = 0;
	private int right_num = 0;
	private boolean isEqual = false;
	private boolean isEqual2 = true;
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

		if (!s.hasNextLine() && !s2.hasNextLine()) {// 占쎈あ占쎈솁占쎌뵬 筌뤴뫀紐� �뜮�뜃瑗랃옙�뒭
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

			if (LCS(line, line2) != MAX(line.length(), line2.length())) {// 占쎈あ �눧紐꾩쁽占쎈였占쎌뵠 占쎈뼄�몴硫멸펾占쎌뒭占쎈굶
				isEqual2 = false;
				if (line_check(line, line2)) {
					insert(first, Color.BLACK, Color.YELLOW, line);
					insert(second, Color.BLACK, Color.YELLOW, line2);
				} else {
					if (line.length() != 0 && line2.length() != 0) {
						rightHas = hasEqual(right_num, line, second);
						leftHas = hasEqual(left_num, line2, first);

						// 占쎈솁占쎌뵬 獄쏅쵐肉� 占쎈선占쎈탺揶쏉옙 鈺곕똻�삺占쎈립占쎈뼄占쎈뮉 占쎌몴
						if (rightHas == right_num && leftHas == left_num) {// 域밸챶源� 占쎈ぎ占쎌뵠 占쎈뼄�몴紐꺪�占쎌쁽占쎈뼄.
							inLineDiff(line, line2, first, second);
							insert(first, Color.black, Color.black, "");
							insert(second, Color.black, Color.black, "");

						} else {
							if (rightHas == right_num && leftHas != left_num) {// 占쎌궎�몴紐꾠걹 占쎈솁占쎌뵬占쎈퓠 �⑤벉媛싷옙�뵠
																				// �빊遺쏙옙占쎈┷占쎈선占쎈튊 占쎈립占쎈뼄. 占쎌뇢:占쎈걗占쏙옙占쎄퉳
																				// 占쎌궎�몴占�:占쎌돳占쎄퉳

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

								// addSpace(spaceNum, lineNum, leftHas, left_num, line, line2, first, second,
								// s);
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
								// addSpace(spaceNum, lineNum, rightHas, right_num, line2, line, second, first,
								// s2);
							} else {// rightHas 占쎌뒭占쎄퐨占쎌몵嚥∽옙 占쎈릭占쎌쁽

							}
						}

					}

					else if (line.length() == 0 && line2.length() != 0) {// �⑤벉媛�,~�⑤벉媛�
						leftHas = hasEqual(left_num, line2, first);
						if (leftHas == left_num) {// 獄쏅쵐肉� 占쎈짗占쎌뵬占쎈립椰꾬옙 占쎈씨占쎈뼄.
							insert(first, Color.BLACK, Color.YELLOW, whenNull);
							insert(second, Color.BLACK, Color.YELLOW, line2);
						} else {// 占쎌뇢 �⑤벉媛싷옙�땾筌띾슦寃� 占쎈걗占쎌삂, 占쎌궎 �⑤벉媛싷옙�땾筌띾슦寃� 占쎌돳占쎄퉳
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

							// addSpace(spaceNum, lineNum, leftHas, left_num, line, line2, first, second,
							// s);
						}
					} else {// ~�⑤벉媛�, �⑤벉媛�
						rightHas = hasEqual(right_num, line, second);
						if (rightHas == right_num) {// 獄쏅쵐肉� 占쎈짗占쎌뵬占쎈립椰꾬옙 占쎈씨占쎈뼄.
							insert(first, Color.BLACK, Color.YELLOW, line);
							insert(second, Color.BLACK, Color.YELLOW, whenNull);
						} else {// 占쎌뇢 �⑤벉媛싷옙�땾筌띾슦寃� 占쎈걗占쎌삂, 占쎌궎 �⑤벉媛싷옙�땾筌띾슦寃� 占쎌돳占쎄퉳
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
			insert(first, Color.BLACK, Color.YELLOW, line);
			insert(second, Color.BLACK, Color.GRAY, whenNull);
		}

		for (; s2.hasNextLine();) {
			line2 = s2.nextLine();
			insert(second, Color.BLACK, Color.YELLOW, line2);
			insert(first, Color.BLACK, Color.GRAY, whenNull);
		}

		if (!isEqual || !isEqual2) {
			// 占쎄퐫占쎈선餓Ρ됰쐲椰꾬옙 筌욑옙占쎌뜖餓ο옙占쎈뼄
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
		StyleConstants.setForeground(style, foreG);// 疫뀐옙占쎌쁽占쎄퉳
		StyleConstants.setBackground(style, backG);// 獄쏄퀗瑗랃옙源�
		// 占쎄퐫占쎈뮉甕곕벚�ц첋占�...
		doc.insertString(doc.getLength(), line + "\n", style);

		// doc.setParagraphAttributes(arg0, line.length(), style, true);
	}

	// 占쎈립 占쎌뵬占쎌뵥占쎈툧占쎈퓠占쎄퐣 占쎈뼄�몴占� �겫占썽겫占� 占쎌뿳占쎌뱽占쎈르 占쎈뼄�몴紐껓옙�겫占� 占쎄퉳燁삼옙
	private void inLineDiff(String l, String l2, JTextPane tP, JTextPane tP2) throws Exception {
		String[] part = l.split("\\s");
		String[] part2 = l2.split("\\s");
		// ArrayList<String> p = new ArrayList<String>();
		// ArrayList<String> p2 = new ArrayList<String>();
		// arraylist占쎈퓠 占쎄퐫占쎈선雅뚯눊由�
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
		
		for (int i =0 ; i < l.length(); i++) {
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
		
		if (LCS(temp.toString(), temp2.toString()) == MAX(3*j,3*j2)) {
			return true;
		}
		return false;

	}

}
