package Preprocessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Cleaner {

	public static final String CLEAN_RT = "cleanrt";
	public static final String CLEAN_MENTION = "cleanmention"; // baseline
	public static final String CLEAN_LINK = "cleanlink"; // baseline
	public static final String REMOVE_DUPLICATE = "removedups"; // baseline
	public static final String REMOVE_PUNCT = "removepunct"; // baseline
	public static final String REMOVE_NUMBER = "removenumber";
	public static final String REMOVE_STOP = "removestop";
	public static final String CONVERT_KETAWA = "convketawa"; // baseline
	public static final String CONVERT_SOUNDEX = "convsoundex";
	public static final String CONVERT_STEMMING = "convstem";
	public static final String CONVERT_EMOTICON = "convemo";
	public static final String CONVERT_NEGASI = "convnegasi";
	public static final String RT_NUMBER = "rt_number";
	public static final String RT_EMO = "rt_emo";
	public static final String RT_NEGASI = "rt_negasi";
	public static final String NUMBER_EMO = "number_emo";
	public static final String NUMBER_NEGASI = "number_negasi";
	public static final String EMO_NEGASI = "emo_negasi";
	public static final String RT_NUMBER_EMO = "rt_number_emo";	
	public static final String RT_NUMBER_NEGASI = "rt_number_negasi";
	public static final String RT_EMO_NEGASI = "rt_emo_negasi";
	public static final String NUMBER_EMO_NEGASI = "number_emo_negasi";
	public static final String ALLFOR ="allfor";
	public static final String BASELINE = "baseline";
	public static final String TARGET[] ={"positive", "negative","neutral"};
	public static final String ngram[] ={"unigram", "bigram"};
	public static final String PAKET1[] = {BASELINE, CLEAN_RT,REMOVE_NUMBER, REMOVE_STOP, CONVERT_SOUNDEX, 
    	CONVERT_STEMMING, CONVERT_EMOTICON, CONVERT_NEGASI};
	public static final String PAKET2[]={RT_NUMBER , RT_EMO, RT_NEGASI , NUMBER_EMO , NUMBER_NEGASI 
		,EMO_NEGASI, RT_NUMBER_EMO , RT_NUMBER_NEGASI , RT_EMO_NEGASI, NUMBER_EMO_NEGASI};
	// static final String BASELINE[] = {CLEAN_MENTION, CLEAN_LINK,
	// CONVERT_KETAWA};

	public static ArrayList<String> getData(String path) {
		ArrayList<String> list = new ArrayList<>();
		try {
			BufferedReader read = new BufferedReader(new FileReader(path));

			String strLine;
			while ((strLine = read.readLine()) != null) {
				// System.out.println();
				list.add(strLine.substring(0, strLine.length() - 2));

			}
			read.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public static void printList(ArrayList<String> list) {
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
	}

	public static String getBaseline(String clean) {
		clean = Preprocessing.cleanMention(clean);
		clean = Preprocessing.cleanLink(clean);
		clean = Preprocessing.removeKetawa(clean);
		return clean;
	}
	
	public static String cleanOneTweet(String base, String prep, boolean isBigram)
	{
		String hasil="";
		String clean = base;
		switch (prep) {
		case BASELINE:
			clean= clean.toLowerCase();
			clean = getBaseline(clean);
			clean = Preprocessing.removeDups(clean);
			clean = Preprocessing.removePunct(clean);
			break;
		case CLEAN_RT:
			clean = Preprocessing.cleanRT(clean);
			clean= clean.toLowerCase();
			clean = getBaseline(clean);
			clean = Preprocessing.removeDups(clean);
			clean = Preprocessing.removePunct(clean);
			break;
		case REMOVE_NUMBER:
			clean= clean.toLowerCase();
			clean = Preprocessing.removeNumber(clean);
			clean = getBaseline(clean);
			clean = Preprocessing.removeDups(clean);
			clean = Preprocessing.removePunct(clean);
			break;
		case REMOVE_STOP:
			clean= clean.toLowerCase();
			clean = Preprocessing.removeDups(clean);
			clean = Preprocessing.removeStopWord(clean);
			clean = getBaseline(clean);
			clean = Preprocessing.removePunct(clean);
			break;
		case CONVERT_SOUNDEX:
			clean= clean.toLowerCase();
			clean = Preprocessing.removeDups(clean);
			clean = getBaseline(clean);
			clean = Preprocessing.editToSoundex(clean);
			clean = Preprocessing.removePunct(clean);
			break;
		case CONVERT_STEMMING:
			clean= clean.toLowerCase();
			clean = Preprocessing.removeDups(clean);
			clean = getBaseline(clean);
			clean = Preprocessing.editToStem(clean);
			clean = Preprocessing.removePunct(clean);
			break;
		case CONVERT_EMOTICON:
			clean = Preprocessing.removeDups(clean);
			clean = getBaseline(clean);
			clean = Preprocessing.convertEmoticon(clean);
			clean= clean.toLowerCase();
			clean = Preprocessing.removePunct(clean);
			break;
		case CONVERT_NEGASI:
			clean= clean.toLowerCase();
			clean = Preprocessing.convertNegasi(clean);
			clean = getBaseline(clean);
			clean = Preprocessing.removeDups(clean);
			clean = Preprocessing.removePunct(clean);
			break;
		case RT_NUMBER:
			clean = Preprocessing.cleanRT(clean);
			clean= clean.toLowerCase();
			clean=Preprocessing.removeNumber(clean);
			clean = getBaseline(clean);
			clean = Preprocessing.removeDups(clean);
			clean = Preprocessing.removePunct(clean);
			break;
		case RT_EMO:
			clean = Preprocessing.cleanRT(clean);
			clean = Preprocessing.removeDups(clean);
			clean = Preprocessing.convertEmoticon(clean);
			clean= clean.toLowerCase();
			clean = getBaseline(clean);
			clean = Preprocessing.removePunct(clean);
			break;
		case RT_NEGASI:
			clean = Preprocessing.cleanRT(clean);
			clean= clean.toLowerCase();
			clean = Preprocessing.removeDups(clean);
			clean = Preprocessing.convertNegasi(clean);
			clean = getBaseline(clean);
			clean = Preprocessing.removePunct(clean);
			break;
		case NUMBER_EMO:
			clean = Preprocessing.removeDups(clean);
			clean = Preprocessing.convertEmoticon(clean);
			clean= clean.toLowerCase();
			clean=Preprocessing.removeNumber(clean);
			clean = getBaseline(clean);
			clean = Preprocessing.removePunct(clean);
			break;
		case NUMBER_NEGASI:
			clean= clean.toLowerCase();
			clean=Preprocessing.removeNumber(clean);
			clean = Preprocessing.convertNegasi(clean);
			clean = getBaseline(clean);
			clean = Preprocessing.removeDups(clean);
			clean = Preprocessing.removePunct(clean);
			break;
		case EMO_NEGASI:
			clean = Preprocessing.removeDups(clean);
			clean = Preprocessing.convertEmoticon(clean);
			clean= clean.toLowerCase();
			clean=Preprocessing.convertNegasi(clean);
			clean = getBaseline(clean);
			clean = Preprocessing.removePunct(clean);
			break;
		case RT_NUMBER_EMO:
			clean = Preprocessing.cleanRT(clean);
			clean = Preprocessing.removeDups(clean);
			clean = Preprocessing.convertEmoticon(clean);
			clean= clean.toLowerCase();
			clean=Preprocessing.removeNumber(clean);
			clean = getBaseline(clean);
			clean = Preprocessing.removePunct(clean);
			break;
		case RT_NUMBER_NEGASI:
			clean = Preprocessing.cleanRT(clean);
			clean= clean.toLowerCase();
			clean=Preprocessing.removeNumber(clean);
			clean = getBaseline(clean);
			clean=Preprocessing.convertNegasi(clean);
			clean = Preprocessing.removeDups(clean);
			clean = Preprocessing.removePunct(clean);
			break;
		case RT_EMO_NEGASI:
			clean = Preprocessing.cleanRT(clean);
			clean = Preprocessing.removeDups(clean);
			clean = Preprocessing.convertEmoticon(clean);
			clean= clean.toLowerCase();
			clean=Preprocessing.convertNegasi(clean);
			clean = getBaseline(clean);
			clean = Preprocessing.removePunct(clean);
			break;
		case NUMBER_EMO_NEGASI:
			clean = Preprocessing.removeDups(clean);
			clean = Preprocessing.convertEmoticon(clean);
			clean= clean.toLowerCase();
			clean=Preprocessing.convertNegasi(clean);
			clean=Preprocessing.removeNumber(clean);
			clean = getBaseline(clean);
			clean = Preprocessing.removePunct(clean);
			break;
		case ALLFOR:
			clean = Preprocessing.cleanRT(clean);
			clean = Preprocessing.removeDups(clean);
			clean = Preprocessing.convertEmoticon(clean);
			clean= clean.toLowerCase();
			clean=Preprocessing.convertNegasi(clean);
			clean=Preprocessing.removeNumber(clean);
			clean = getBaseline(clean);
			clean = Preprocessing.removePunct(clean);
			break;

		default:
			break;
		}
		hasil=clean;
		if(isBigram)
		{
			String bi="";
			String [] tw = clean.split("\\s");
			for (int j = 1; j < tw.length; j++) {
				bi+= tw[j-1]+"|"+tw[j]+" ";
			}
			hasil=bi.trim();
		}
		return hasil;
	}

	public static ArrayList<String> runCleanerUnigram(ArrayList<String> list,
			String prep) {
		ArrayList<String> newlist = new ArrayList<>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			String clean = string;
			// Baseline

			switch (prep) {
			case BASELINE:
				clean= clean.toLowerCase();
				clean = getBaseline(clean);
				clean = Preprocessing.removeDups(clean);
				clean = Preprocessing.removePunct(clean);
				break;
			case CLEAN_RT:
				clean = Preprocessing.cleanRT(clean);
				clean= clean.toLowerCase();
				clean = getBaseline(clean);
				clean = Preprocessing.removeDups(clean);
				clean = Preprocessing.removePunct(clean);
				break;
			case REMOVE_NUMBER:
				clean= clean.toLowerCase();
				clean = Preprocessing.removeNumber(clean);
				clean = getBaseline(clean);
				clean = Preprocessing.removeDups(clean);
				clean = Preprocessing.removePunct(clean);
				break;
			case REMOVE_STOP:
				clean= clean.toLowerCase();
				clean = Preprocessing.removeDups(clean);
				clean = Preprocessing.removeStopWord(clean);
				clean = getBaseline(clean);
				clean = Preprocessing.removePunct(clean);
				break;
			case CONVERT_SOUNDEX:
				clean= clean.toLowerCase();
				clean = Preprocessing.removeDups(clean);
				clean = getBaseline(clean);
				clean = Preprocessing.editToSoundex(clean);
				clean = Preprocessing.removePunct(clean);
				break;
			case CONVERT_STEMMING:
				clean= clean.toLowerCase();
				clean = Preprocessing.removeDups(clean);
				clean = getBaseline(clean);
				clean = Preprocessing.editToStem(clean);
				clean = Preprocessing.removePunct(clean);
				break;
			case CONVERT_EMOTICON:
				clean = Preprocessing.removeDups(clean);
				clean = getBaseline(clean);
				clean = Preprocessing.convertEmoticon(clean);
				clean= clean.toLowerCase();
				clean = Preprocessing.removePunct(clean);
				break;
			case CONVERT_NEGASI:
				clean= clean.toLowerCase();
				clean = Preprocessing.convertNegasi(clean);
				clean = getBaseline(clean);
				clean = Preprocessing.removeDups(clean);
				clean = Preprocessing.removePunct(clean);
				break;
			case RT_NUMBER:
				clean = Preprocessing.cleanRT(clean);
				clean= clean.toLowerCase();
				clean=Preprocessing.removeNumber(clean);
				clean = getBaseline(clean);
				clean = Preprocessing.removeDups(clean);
				clean = Preprocessing.removePunct(clean);
				break;
			case RT_EMO:
				clean = Preprocessing.cleanRT(clean);
				clean = Preprocessing.removeDups(clean);
				clean = Preprocessing.convertEmoticon(clean);
				clean= clean.toLowerCase();
				clean = getBaseline(clean);
				clean = Preprocessing.removePunct(clean);
				break;
			case RT_NEGASI:
				clean = Preprocessing.cleanRT(clean);
				clean= clean.toLowerCase();
				clean = Preprocessing.removeDups(clean);
				clean = Preprocessing.convertNegasi(clean);
				clean = getBaseline(clean);
				clean = Preprocessing.removePunct(clean);
				break;
			case NUMBER_EMO:
				clean = Preprocessing.removeDups(clean);
				clean = Preprocessing.convertEmoticon(clean);
				clean= clean.toLowerCase();
				clean=Preprocessing.removeNumber(clean);
				clean = getBaseline(clean);
				clean = Preprocessing.removePunct(clean);
				break;
			case NUMBER_NEGASI:
				clean= clean.toLowerCase();
				clean=Preprocessing.removeNumber(clean);
				clean = Preprocessing.convertNegasi(clean);
				clean = getBaseline(clean);
				clean = Preprocessing.removeDups(clean);
				clean = Preprocessing.removePunct(clean);
				break;
			case EMO_NEGASI:
				clean = Preprocessing.removeDups(clean);
				clean = Preprocessing.convertEmoticon(clean);
				clean= clean.toLowerCase();
				clean=Preprocessing.convertNegasi(clean);
				clean = getBaseline(clean);
				clean = Preprocessing.removePunct(clean);
				break;
			case RT_NUMBER_EMO:
				clean = Preprocessing.cleanRT(clean);
				clean = Preprocessing.removeDups(clean);
				clean = Preprocessing.convertEmoticon(clean);
				clean= clean.toLowerCase();
				clean=Preprocessing.removeNumber(clean);
				clean = getBaseline(clean);
				clean = Preprocessing.removePunct(clean);
				break;
			case RT_NUMBER_NEGASI:
				clean = Preprocessing.cleanRT(clean);
				clean= clean.toLowerCase();
				clean=Preprocessing.removeNumber(clean);
				clean = getBaseline(clean);
				clean=Preprocessing.convertNegasi(clean);
				clean = Preprocessing.removeDups(clean);
				clean = Preprocessing.removePunct(clean);
				break;
			case RT_EMO_NEGASI:
				clean = Preprocessing.cleanRT(clean);
				clean = Preprocessing.removeDups(clean);
				clean = Preprocessing.convertEmoticon(clean);
				clean= clean.toLowerCase();
				clean=Preprocessing.convertNegasi(clean);
				clean = getBaseline(clean);
				clean = Preprocessing.removePunct(clean);
				break;
			case NUMBER_EMO_NEGASI:
				clean = Preprocessing.removeDups(clean);
				clean = Preprocessing.convertEmoticon(clean);
				clean= clean.toLowerCase();
				clean=Preprocessing.convertNegasi(clean);
				clean=Preprocessing.removeNumber(clean);
				clean = getBaseline(clean);
				clean = Preprocessing.removePunct(clean);
				break;
			default:
				break;
			}
			
			newlist.add(clean);
			// System.out.println(string);
		}
		return newlist;
	}
	public static ArrayList<String> runCleanerBigram(ArrayList<String> list,
			String prep) {
		ArrayList<String> uni = runCleanerUnigram(list, prep);
		ArrayList<String> bigram= new ArrayList<>();
		for (int i = 0; i < uni.size(); i++) {
			String tweet = uni.get(i);
			String bi="";
			String [] tw = tweet.split("\\s");
			for (int j = 1; j < tw.length; j++) {
				bi+= tw[j-1]+"|"+tw[j]+" ";
			}
			bigram.add(bi.trim());
			
		}
		
		return bigram;
	}

	public static void printData(ArrayList<String> dat,  String path) {
		try {
			Files.createDirectories(Paths.get(path.substring(0, path.lastIndexOf("/") )));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedWriter write;
		try {
			write = new BufferedWriter(new FileWriter(path));
			for (int i = 0; i < dat.size(); i++) {
				String twit = dat.get(i);
				write.write(twit + "\r\n");
			}
			write.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	public static void clean(String prep)
	{
		for (int i = 0; i < TARGET.length; i++) {

			ArrayList<String> uni =getData("res/data_raw/"+TARGET[i]+".dat");
			ArrayList<String> uniClean= runCleanerUnigram(uni, prep);
			printData(uniClean,"res/data_"+prep+"/unigram/"+TARGET[i]+".txt");
			
			ArrayList<String> bi =getData("res/data_raw/"+TARGET[i]+".dat");
			ArrayList<String> biClean= runCleanerBigram(bi, prep);
			printData(biClean,"res/data_"+prep+"/bigram/"+TARGET[i]+".txt");
			
			
				
			}
		System.out.println("DONE "+ prep);

	}
	public static void clean2(String prep)
	{
		for (int i = 0; i < TARGET.length; i++) {

			ArrayList<String> uni =getData("res/data_raw/"+TARGET[i]+".dat");
			ArrayList<String> uniClean= runCleanerUnigram(uni, prep);
			printData(uniClean,"res/data_"+prep+"/unigram/"+TARGET[i]+".txt");
			
			ArrayList<String> bi =getData("res/data_raw/"+TARGET[i]+".dat");
			ArrayList<String> biClean= runCleanerBigram(bi, prep);
			printData(biClean,"res/data_"+prep+"/bigram/"+TARGET[i]+".txt");
			
			ArrayList<String> uni2 =getData("res/data_raw/training_"+TARGET[i]+".txt");
			ArrayList<String> uniClean2= runCleanerUnigram(uni2, prep);
			printData(uniClean2,"res/data_"+prep+"/unigram/training_"+TARGET[i]+".txt");
			
			ArrayList<String> bi3 =getData("res/data_raw/training_"+TARGET[i]+".txt");
			ArrayList<String> biClean3= runCleanerBigram(bi3, prep);
			printData(biClean3,"res/data_"+prep+"/bigram/training_"+TARGET[i]+".txt");
			
			ArrayList<String> uni4 =getData("res/data_raw/uji_"+TARGET[i]+".txt");
			ArrayList<String> uniClean4= runCleanerUnigram(uni4, prep);
			printData(uniClean4,"res/data_"+prep+"/unigram/uji_"+TARGET[i]+".txt");
			
			ArrayList<String> bi5 =getData("res/data_raw/uji_"+TARGET[i]+".txt");
			ArrayList<String> biClean5= runCleanerBigram(bi5, prep);
			printData(biClean5,"res/data_"+prep+"/bigram/uji_"+TARGET[i]+".txt");
			
			
				
			}
		System.out.println("DONE "+ prep);

	}

	public static void main(String[] args) {
		/*
		 * ArrayList<String> post =getData("res/data_raw/neutral.dat"); //
		 * System.out.println(post); ArrayList<String> cleanPos=
		 * runCleaner(post); printList(cleanPos); printData(cleanPos,
		 * "res/data_clean/neutral.txt");
		 */
		for (int i = 0; i < PAKET2.length; i++) {
			//clean2(PAKET2[i]);
		}
		clean2(ALLFOR);
		
	}
}
