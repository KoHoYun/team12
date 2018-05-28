package compare;

import java.io.*;
import java.util.*;

public class Compare {

	private boolean isLineEqual = false;
	private int[][] LCStable;
	private int val_LCS;
	private int numLine=0;
	ArrayList<Integer> index = new ArrayList<Integer>();//�ٸ� �κ��� �� ���� ����ִ�.

	public Compare() throws IOException {
		
		//���Ϻκ��� ���߿� gui�κ��̶� ���ļ� �޾ƾߵ�
		File file = new File("test1.txt");
		File file2 = new File("test2.txt");

		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedReader reader2 = new BufferedReader(new FileReader(file2));

		String line = null;
		String line2 = null;
		
		while ((line = reader.readLine()) != null) {
			line2 = reader2.readLine();
			if(line2 == null) {
				
				index.add(++numLine);
				while(reader.readLine() !=null) {
					index.add(++numLine);
				}
				break;
			}
			numLine++;
	
			val_LCS = LCS(line,line2);
			if(val_LCS == line.length()) {
				isLineEqual =true;
			}
			else {
				isLineEqual =false;
			}

			
			if(isLineEqual==false) {
				index.add(numLine);
			}
			
			/* Ȯ�ο� print
			if(!isLineEqual) {
				System.out.println(line + "��(��) " + line2+ "�� �ٸ��ϴ�");
			}
			*/
		}
		
		//2��° ������ �� ��� �� ��ȣ�� �־��ش�.
		while(reader2.readLine() !=null) {
			index.add(++numLine);
		}	
		//gui���� close�ؾߵ�.
		reader.close();
		reader2.close();
		
		//Ȯ���غ�����
		for(int i=0;i<index.size();i++) {
			System.out.println(index.get(i));
		}
		
	}

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

}
