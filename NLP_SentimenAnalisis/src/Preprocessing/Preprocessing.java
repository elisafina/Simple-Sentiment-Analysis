package Preprocessing;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Preprocessing {
	
	public static String cleanRT(String raw)
	 {
		 String hasil=raw;
		 
		 if(raw.contains("\"@"))
		 {
			 int awal = raw.indexOf("\"@");
			 int akhir = raw.lastIndexOf("\"");
			 hasil=raw.substring(0, awal) + raw.substring(akhir+1, raw.length());
			 hasil= hasil.trim();
		 }
		 if(raw.contains("RT"))
		 {
			 int awal = raw.indexOf("RT");
			 hasil=raw.substring(0, awal);
			 hasil= hasil.trim();
		 }
		 
		 
		 return hasil;
	 }
	 
	 public static String cleanMention (String raw)
	 {
		 return raw.replaceAll("@([A-Za-z0-9_]+)", "").trim();
	 }
	 
	 public static String cleanLink (String raw)
	 {
		 return raw.replaceAll("(?<http>(http:[/][/]|www.)([a-z]|[A-Z]|[0-9]|[/.]|[~])*)", "").trim();
	 }
	 public static String removeDups(String s)
	 {
	     if ( s.length() <= 1 ) return s;
	     if( s.substring(1,2).equals(s.substring(0,1)) ) return removeDups(s.substring(1));
	     else return s.substring(0,1) + removeDups(s.substring(1));
	 }
	 
	 public static String removePunct(String s)
	 {
		 
		 s = s.replaceAll("-", "");
		 s= s.replaceAll("\\p{Punct}", " ");
		 s= s.replaceAll("(\\s)+", " ");
		 return s.trim();
	 }
	 
	 public static String removeNumber(String s)
	 {
		 s=s.replaceAll("(\\d)+", "");
		 return s;
	 }
	 public static String removeStopWord(String input)
	    {
		 String strings = "";
	        HashSet<String> stopWords = new HashSet<String>();

	        try {
	            FileInputStream fstream = new FileInputStream("res/stop-word.txt");
	            DataInputStream in = new DataInputStream(fstream);
	            BufferedReader br = new BufferedReader(new InputStreamReader(in));
	            String strLine;
	            while ((strLine = br.readLine()) != null) {
	                strings += strLine;
	            }
	            in.close();
	        } catch (Exception e) {//Catch exception if any
	            e.printStackTrace();
	        }
	        String[] arrayString = strings.split("\\s");
	        for (int i = 0; i < arrayString.length; i++) {
	            if(arrayString[i].length() > 0){
	                stopWords.add(arrayString[i]);
	                //stopWords.put(arrayString[i], "ok");
	            }
	        }
	        String hasil ="";
	        input.toLowerCase();
	        input.replaceAll("\\p{Punct}", "");
	        String[] ret = input.split("\\s");
	        for (String token : ret) {
	            if(!stopWords.contains(token))
	            {
	                if(!token.matches("(wk)+")&&!token.matches("(ha)+"))
	                {
	                    //hasil+= removeDups(token)+" ";
	                    if(token.length()>=2)
	                    {
	                     hasil+= token+" ";
	                    }
	                }
	            }
	        }
	        return hasil.trim();
	    }
	 public static String removeKetawa(String s)
	 {
		 
		 s = s.replaceAll("hi(hi)+", "ketawa");
		 s = s.replaceAll("ha(ha)+", "ketawa");
		 s = s.replaceAll("he(he)+", "ketawa");
		 s = s.replaceAll("ho(ho)+", "ketawa");
		 s = s.replaceAll("wk(wk)+", "ketawa");
		 s = s.replaceAll("wq(wq)+", "ketawa");

		 return s.trim();
	 }
	 private static String soundex (String input)
	    {
	        
	        try {
	            input = input.replaceAll("<|>", "");
	        input = input.replaceAll("kh", "hh");
	        input = input.replaceAll("dj", "jj");
	        input = input.replaceAll("tj", "cc");
	        input = input.replaceAll("cq", "kk");
	        input = input.replaceAll("ck", "kk");
	        input = input.replaceAll("dz", "zz");
	        input = input.replaceAll("sj", "sy");
	        input = input.replaceAll("sy", "ss");
	        input = input.replaceAll("bh", "bb");
	        input = input.replaceAll("dh", "dd");
	        input = input.replaceAll("gh", "gg");
	        input = input.replaceAll("jh", "jj");
	        input = input.replaceAll("sh", "ss");
	        input = input.replaceAll("th", "tt");
	        input = input.replaceAll("zh", "zz");
	        input = input.replaceAll("v", "f");
	        input = input.replaceAll("ks", "xx");
	        input = input.replaceAll("oe", "uu");
	        input = input.replaceAll("ie", "ii");
	        input = input.replaceAll("y", "i");
	        
	        String head = input.substring(0,1);
	        //System.out.println(head);
	        String body = input.substring(1,input.length());
	        //System.out.println(body);
	        body =body.replaceAll("a|i|u|e|o|y", "0");
	        body =body.replaceAll("h|w", "7");
	        body =body.replaceAll("b|p|v|f", "1");
	        body =body.replaceAll("c|g|j|k|q|s|x|z", "2");
	        body =body.replaceAll("d|t", "3");
	        body =body.replaceAll("l", "4");
	        body =body.replaceAll("m|n", "5");
	        body =body.replaceAll("r", "6");
	        //System.out.println(body);
	        body= removeDups(body);
	        body = body.replaceAll("0", "");

	        return (head+body);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "";
	        }
	        
	        
	    }
	 public static String editToSoundex(String input)
	    {
	        String hasil ="";
	        String[] ret = input.split("\\s");
	        for (String token : ret) {
	        	if(token.length()>=1)
	        	{
	            hasil+=soundex(token)+" ";
	        	}
	        }
	        return hasil.trim();
	    }
	 
	 private static String removeAkhiran (String input)
	    {
	        if(input.endsWith("nya"))
	        {
	            input = input.substring(0, input.length()-3);
	            return input;
	        }
	        if(input.endsWith("ku"))
	        {
	            input = input.substring(0, input.length()-2);
	            return input;
	        }
	        if(input.endsWith("mu"))
	        {
	           input = input.substring(0, input.length()-2);
	            return input;
	        }
	        if(input.endsWith("kah"))
	        {
	            input = input.substring(0, input.length()-3);
	            return input;
	        }
	        if(input.endsWith("lah"))
	        {
	            input = input.substring(0, input.length()-3);
	            return input;
	        }
	        if(input.endsWith("pun"))
	        {
	            input = input.substring(0, input.length()-3);
	            return input;
	        }
	        return input;
	    }
	    private static String removeAwalan (String input)
	    {
	          if(input.startsWith("meng"))
	        {
	            input = input.replaceFirst("meng", "");
	            return input;
	        }
	        if(input.startsWith("men"))
	        {
	            input = input.replaceFirst("men", "");
	            return input;
	        }
	        if(input.startsWith("mem"))
	        {
	            input = input.replaceFirst("mem", "");
	            return input;
	        }
	        if(input.startsWith("me"))
	        {
	            input = input.replaceFirst("me", "");
	            return input;
	        }
	        if(input.startsWith("peng"))
	        {
	            input = input.replaceFirst("peng", "");
	            return input;
	        }
	        if(input.startsWith("pen"))
	        {
	            input = input.replaceFirst("pen", "");
	            return input;
	        }
	        if(input.startsWith("pem"))
	        {
	            input = input.replaceFirst("pem", "");
	            return input;
	        }
	        if(input.startsWith("di"))
	        {
	            input = input.replaceFirst("di", "");
	            return input;
	        }
	        if(input.startsWith("ke"))
	        {
	            input = input.replaceFirst("ke", "");
	            return input;
	        }
	        if(input.startsWith("ter"))
	        {
	            input = input.replaceFirst("ter", "");
	            return input;
	        }
	        if(input.startsWith("nge"))
	        {
	            input = input.replaceFirst("nge", "");
	            return input;
	        }
	        return input;
	    }
	    private static String stemmer (String input)
	    {
	        
	    	String strings = "";
	        HashSet<String> baseWord = new HashSet<String>();

	        try {
	            FileInputStream fstream = new FileInputStream("res/kata_dasar.txt");
	            DataInputStream in = new DataInputStream(fstream);
	            BufferedReader br = new BufferedReader(new InputStreamReader(in));
	            String strLine;
	            while ((strLine = br.readLine()) != null) {
	            	baseWord.add(strLine.toLowerCase());
	            }
	            in.close();
	            br.close();
	        } catch (Exception e) {//Catch exception if any
	            e.printStackTrace();
	        }
	        

	      String awal = input;
	      input = removeAkhiran(input);
	      input= removeAwalan(input);
	      if(baseWord.contains(input))
	      {
	      return input;
	      }
	      else
	      {
	          return awal;
	      }
	    }
	    public static String editToStem(String input)
	    {
	        String hasil ="";
	        String[] ret = input.split("\\s");
	        for (String token : ret) {
	        	if(ret.length>=1)
	        	{
	            hasil+=stemmer(token)+" ";
	        	}
	        }
	        System.out.println(hasil.trim());
	        return hasil.trim();
	    }
	    public static String convertEmoticon (String s)
	    {

	    			s=s.replace(">:]", "emohappy ");
					s=s.replace(":-)", "emohappy ");
					s=s.replace(":)", "emohappy ");
					s=s.replace(":o)", "emohappy ");
					s=s.replace(":]", "emohappy ");
					s=s.replace(":3", "emohappy ");
					s=s.replace(":c)", "emohappy ");
					s=s.replace(":>", "emohappy ");
					s=s.replace("=]", "emohappy ");
					s=s.replace("8)", "emohappy ");
					s=s.replace("=)", "emohappy ");
					s=s.replace(":}", "emohappy ");
					s=s.replace("\\(^.^)/", "emohappy ");
					s=s.replace("\\(?)/", "emohappy ");
					s=s.replace("(~^.^)~", "emohappy ");
					s=s.replace("^_^", "emohappy ");
					s=s.replace("^^", "emohappy ");
					s=s.replace("^ ^", "emohappy ");
					s=s.replace(":^)", "emohappy ");
					
					s=s.replace(">:D","emolaugh ");
					s=s.replace(":-D","emolaugh ");
					s=s.replace(":D","emolaugh ");
					s=s.replace(":))","emolaugh ");
					s=s.replace("=))","emolaugh ");
					s=s.replace("8-D)","emolaugh ");
					s=s.replace("8D","emolaugh ");
					s=s.replace("x-D","emolaugh ");
					s=s.replace("xD","emolaugh ");
					s=s.replace("=-D","emolaugh ");
					s=s.replace("=D","emolaugh ");
					s=s.replace("=-3","emolaugh ");
					s=s.replace("=3","emolaugh ");
					
					s=s.replace(">:[","emosad ");
					s=s.replace(":-(","emosad ");
					s=s.replace(":(","emosad ");
					s=s.replace(":-c)","emosad ");
					s=s.replace(":c","emosad ");
					s=s.replace(":-<","emosad ");
					s=s.replace(":<","emosad ");
					s=s.replace(":-[","emosad ");
					s=s.replace(":[","emosad ");
					s=s.replace(":{","emosad ");
					s=s.replace(">.>","emosad ");
					s=s.replace("<.<","emosad ");
					s=s.replace(">.<","emosad ");
					
					s=s.replace("D:<","emohorror ");
					s=s.replace("D:","emohorror ");
					s=s.replace("D8","emohorror ");
					s=s.replace("D;)","emohorror ");
					s=s.replace("D=","emohorror ");
					s=s.replace("DX","emohorror ");
					s=s.replace("v.v","emohorror ");
					s=s.replace("D-':","emohorror ");

				    s=s.replace(">:P","emotongue ");
					s=s.replace(":-P","emotongue ");
					s=s.replace(":P","emotongue ");
					s=s.replace("X-P","emotongue ");
					s=s.replace("=P","emotongue ");
					s=s.replace(":-b","emotongue ");
					s=s.replace(":b","emotongue ");
				
					s=s.replace(">:o","emoshock ");
					s=s.replace(":-o","emoshock ");
					s=s.replace(":o","emoshock ");
					s=s.replace("o_O","emoshock ");
					s=s.replace("o.O","emoshock ");
					s=s.replace("8-o","emoshock ");
					
				
					s=s.replace(">:\\", "emoannoyed ");
					s=s.replace(">:/", "emoannoyed ");
					s=s.replace(":-/", "emoannoyed ");
					s=s.replace(":-.", "emoannoyed ");
					s=s.replace(":/", "emoannoyed ");
					s=s.replace(":\\", "emoannoyed ");
					s=s.replace("=/", "emoannoyed ");
					s=s.replace("=\\", "emoannoyed ");
					s=s.replace(":S", "emoannoyed ");
					
				
				    s=s.replace(":|","emostraightface ");
					s=s.replace("._.","emostraightface ");
					s=s.replace("-_-\"","emostraightface ");
					s=s.replace("-_-","emostraightface ");
					s=s.replace(":-|","emostraightface ");

			return s.trim();
	    }
	    public static String convertNegasi(String s)
	    {
	    	String hasil="";
	    	String[] token = s.split("[ ]+");
			
			for(int i = 0; i< token.length; i++){
				
				
				
				if(	token[i].equalsIgnoreCase("bkn")||
					token[i].equalsIgnoreCase("bukan")||
					token[i].equalsIgnoreCase("tidak")||
					token[i].equalsIgnoreCase("enggak")||
					token[i].equalsIgnoreCase("ngk")||
					token[i].equalsIgnoreCase("g")||
					token[i].equalsIgnoreCase("ga")||
					token[i].equalsIgnoreCase("jangan")||
					token[i].equalsIgnoreCase("nggak")||
					token[i].equalsIgnoreCase("tak")||
					token[i].equalsIgnoreCase("tdk")||
					token[i].equalsIgnoreCase("gak")){
					
					token[i] = "tidak";
				}
				
				hasil+=token[i]+" ";
			}
			return hasil.trim();
	    }
	 public static void main (String [] args)
	 {
		/* ArrayList<String>  post =getData("res/data_raw/neutral.dat");
		// System.out.println(post);
		 ArrayList<String>  cleanPos= runCleaner(post);
		 printList(cleanPos);
		 printData(cleanPos, "res/data_clean/neutral.txt");*/
		 
		 System.out.println(editToSoundex("@amelia_triana kami masih terus mencari tahu penyebabnya, Amelia, karena rupanya tidak di semua ATM bermasalah, banyak juga yg berhasil"));
		 System.out.println(editToStem("Ngoreksi kerjaan. alhamdulillah progress nya bagus :)"));
			
	 }

}
