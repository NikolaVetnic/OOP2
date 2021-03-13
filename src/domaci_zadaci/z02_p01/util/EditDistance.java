package domaci_zadaci.z02_p01.util;

import java.util.Stack;

public class EditDistance {

	
	private String s1, s2;
	private int[][] d;
	
	
	public EditDistance(String s1, String s2) {
		this.s1 = s1;
		this.s2 = s2;
		d = new int[s1.length() + 1][s2.length() + 1];
		computeDistance();
	}


	private void computeDistance() {
		
		for (int i = 0; i < d.length; i++) d[i][0] = i;
		for (int i = 0; i < d[0].length; i++) d[0][i] = i;
		
		for (int i = 1; i < d.length; i++) {
			for (int j = 1; j < d[0].length; j++) {
				
				char c1 = s1.charAt(i - 1);
				char c2 = s2.charAt(j - 1);
				
				if (c1 == c2) {
					d[i][j] = d[i - 1][j - 1];
				} else {
					int dDel = d[i - 1][j    ];
					int dAdd = d[i    ][j - 1];
					int dSub = d[i - 1][j - 1];
					
					d[i][j] = 1 + Math.min(Math.min(dDel, dAdd), dSub);
				}
			}
		}
	}
	
	
	public int getDistance() {
		return d[s1.length()][s2.length()];
	}
	
	
	public void getExplanation() { 
		
		int i = s1.length(); 
		int j = s2.length();
		
		Stack<String> messages = new Stack<String>(); 
		
		int[] di = {-1, -1, 0};		// prirastaji
		int[] dj = {-1, 0, -1};
	
		while (d[i][j] > 0) {
			
			int min = Integer.MAX_VALUE; 
			int minIndex = -1;
	
			for (int k = 0; k < di.length; k++) {
				if (i + di[k] >= 0 && j + dj[k] >= 0) {
					if (d[i + di[k]][j + dj[k]] < min) { 
						min = d[i + di[k]][j + dj[k]]; 
						minIndex = k;
					}
				}
			}
			
			if (minIndex == 0) {
				if (d[i][j] != min)
					messages.push(s1.charAt(i - 1) + " --> " + s2.charAt(j - 1));
				i--;
				j--;
			} else if (minIndex == 1) {
				i--;
				messages.push(s1.charAt(i) + " deleted");
			} else {
				j--;
				messages.push(s2.charAt(j) + " inserted");
			}
		}
		
		if (messages.isEmpty()) {
			System.out.println("Identical strings...");
		} else {
			System.out.println("Transformations: ");
			while (!messages.isEmpty())
				System.out.println(messages.pop());
		}
	}
}
