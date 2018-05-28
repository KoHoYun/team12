package compare;

import java.io.*;
import java.util.*;

public class Compare {

	private boolean isLineEqual = false;
	private int[][] LCStable;
	private int val_LCS;
	private int numLine=0;
	ArrayList<Integer> index = new ArrayList<Integer>();//다른 부분의 줄 수가 들어있다.

	public Compare() throws IOException {
		
		//파일부분은 나중에 gui부분이랑 합쳐서 받아야됨
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
			
			/* 확인용 print
			if(!isLineEqual) {
				System.out.println(line + "와(과) " + line2+ "가 다릅니다");
			}
			*/
		}
		
		//2번째 파일이 더 길면 그 번호도 넣어준다.
		while(reader2.readLine() !=null) {
			index.add(++numLine);
		}	
		//gui에서 close해야됨.
		reader.close();
		reader2.close();
		
		//확인해보려고
		for(int i=0;i<index.size();i++) {
			System.out.println(index.get(i));
		}
		
	}

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

}
