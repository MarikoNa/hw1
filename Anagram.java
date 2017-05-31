import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Anagram {
    public static void main(String[] args) {
	ArrayList<String> dtext = new ArrayList<String>(); 
	try {
	    BufferedReader br = new BufferedReader(new FileReader("dictionary.txt"));
	    try {
		while(true) {
		    String line = br.readLine();
		    if(line == null) {
			break;
		    }
		    dtext.add(line);
		}
	    } finally {
		br.close();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	Scanner sc = new Scanner(System.in);
	String word = sc.next();
	word = word.toLowerCase();
	System.out.println("--- Answer ---");
	findAnagram(dtext,word);
    }
    static void findAnagram(ArrayList d,String word) {
	char[] cw = word.toCharArray();
	dU(cw);
	String sortWord = sort(cw);
	boolean isAnagram = false; 
	ArrayList<String> pMatchWord = new ArrayList<String>();
	int Max = 0;
	for(int i=0; i<d.size(); i++) {
	    String a = (String)d.get(i);
	    String sma = a.toLowerCase();
	    char[] dw = sma.toCharArray();
	    dU(dw);
	    String sortDic = sort(dw);
	    if(sortWord.equals(sortDic)) {
		System.out.println(a);
		isAnagram = true;
	    }
	    if(!isAnagram) {
		boolean isPMatch = true;
		int j=0;
		int k=0;
		int point =0;
		while(j<dw.length) {
		    if(cw[k] == dw[j]) {
			if("jkqxz".matches(".*"+String.valueOf(cw[k])+".*")) {
				point += 3;
			    } else if ("cfhlmpvwy".matches(".*"+String.valueOf(cw[k])+".*")){
				point += 2;
			    } else {
				point++;
			    }
			    j++; k++;
			} else {
			    k++;
			}
			if(k==cw.length) {
			    isPMatch = false;
			    break;
			}
			
		}
		if(isPMatch) {
		    if(point > Max) {
			pMatchWord.clear();
			pMatchWord.add(a);
			Max = point;
		    } else if (point == Max) {
			pMatchWord.add(a);
		    }
		}
	    }
	}
	if(!isAnagram) {
	    for(int i=0;i<pMatchWord.size();i++) {
		System.out.println(pMatchWord.get(i));
	    }
	}
	pMatchWord.clear();
    }
    static String sort(char[] c) {
	for(int i=0;i<c.length-1;i++) {
	    boolean flag = false;
	    for(int j=c.length-1;j>i;j--) {
		if(c[j-1] > c[j]) {
		    char tmp = c[j];
		    c[j] = c[j-1];
		    c[j-1]=tmp;
		    flag = true;
		}
	    }
	    if(!flag) break;
	}
	String s = new String(c);
	return s;
    }
    static void dU(char[] c) {
	int i=0;
	while(i<c.length) {
	    if(c[i] == 'q') {
		i++;
		while(i<c.length-1) {
		    c[i] = c[i+1];
		    i++;
		}
	    }
	    i++;
	}
	
    }
}
		      
			     
