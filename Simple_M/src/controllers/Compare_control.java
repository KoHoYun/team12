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
	// replace empty line to space characters when it has to be colored
	private String whenNull = "                                                 ";
	private int spaceNum;
	private int leftHas;
	private int rightHas;
	private int lineNum = 0; // line number of styledocument
	private int left_num = 0; // line number of left file
	private int right_num = 0; // line number of right file
	private boolean isEqual = false;
	private boolean isEqual2 = true;//these two variables are used to check if two files are identical
	private boolean forRemove = false;
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
		
		/* executing when both files have next line to read. */
		while (s.hasNextLine() && s2.hasNextLine()) {
			forRemove = true;
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

							} else {
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
							}
						}

					}

					else if (line.length() == 0 && line2.length() != 0) {// if left line is empty and right is not.
						leftHas = hasEqual(left_num, line2, first);
						if (leftHas == left_num) {// if there isn't same line with right line in left file, rewrite that
													// two lines with coloring.
							insert(first, Color.red, Color.YELLOW, whenNull);
							insert(second, Color.red, Color.YELLOW, line2);
						} else {// if there is same line with right line in left file, add empty line with
								// yellow coloring in left file.
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

						}
					} else {// if right line is empty and left is not.
						rightHas = hasEqual(right_num, line, second);
						if (rightHas == right_num) {// if there isn't same line with left line in riht file, rewrite
													// that two lines with coloring.
							insert(first, Color.red, Color.YELLOW, line);
							insert(second, Color.red, Color.YELLOW, whenNull);
						} else {// if there is same line with left line in right file, add empty line with
								// yellow coloring in right file.
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
						}
					}
				}
			}

			else {
				insert(first, Color.BLACK, Color.WHITE, line);
				insert(second, Color.BLACK, Color.WHITE, line2);
			}

		}

		/*if one file is longer than the other one, add rest part of the file with coloring at the end.*/
		while(s.hasNextLine()){
			line = s.nextLine();
			insert(first, Color.red, Color.YELLOW, line);
			insert(second, Color.red, Color.GRAY, whenNull);
			isEqual2 =false;
		}
		while(s2.hasNextLine()){
			line2 = s2.nextLine();
			insert(second, Color.red, Color.YELLOW, line2);
			insert(first, Color.red, Color.GRAY, whenNull);
			isEqual2 =false;
		}

		if(forRemove)
		{
			remove(first, offset + 3);
			remove(second, offset2 + 3);
		}
		else {
			remove(first, offset );
			remove(second, offset2);
		}
		/*if two files are identical*/
		if (isEqual2) {
			new Identical_control(true);
		}

		reader.close();
		reader2.close();
	}

	/*counts number of identical characters in two string.*/
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
	

	/*
	 * find line of same content from the other file, if there isn't, return
	 * original line number.
	 */
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

	/*returns the length of styled document*/
	private int getOffset(JTextPane textP) {
		StyledDocument doc = textP.getStyledDocument();
		return doc.getLength();
	}

	/* remove the int value address of the JTextPane from the beginning */
	private void remove(JTextPane textP, int fin) throws Exception {
		StyledDocument doc = textP.getStyledDocument();
		doc.remove(0, fin);
	}

	/* color a string(line) value in JTextPane */
	private void insert(JTextPane textP, Color foreG, Color backG, String line) throws Exception {

		StyledDocument doc = textP.getStyledDocument();
		Style style = textP.addStyle("String", null);
		StyleConstants.setForeground(style, foreG);//character color
		StyleConstants.setBackground(style, backG);//background color of text
		doc.insertString(doc.getLength(), line + "\n", style);
	}
	
	
	/* find which part of lines are different in each line. */
	private void inLineDiff(String l, String l2, JTextPane tP, JTextPane tP2) throws Exception {
		String[] part = l.split("\\s");
		String[] part2 = l2.split("\\s");
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

	
	/*color a string(word) value in JtextPane */
	private void insert_word(JTextPane textP, Color foreG, Color backG, String line) throws Exception {
		StyledDocument doc = textP.getStyledDocument();
		Style style = textP.addStyle("String", null);
		StyleConstants.setForeground(style, foreG);
		StyleConstants.setBackground(style, backG);

		doc.insertString(doc.getLength(), line, style);
	}

	/*
	 * if there is space character in one line, check characters next to space
	 * character are same as the characters in the other line.
	 */
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
