/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package naiveBayes;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import Preprocessing.Cleaner;


/**
 *
 * @author irfannurhakim
 */
public class NaiveBayesTextClassifier {

    /**
     * @param args the command line arguments
     */
	static String[] TargetClass = {"pos", "neg", "neut"};
    public static void main(String[] args) {

        

    	/*for (int i = 0; i < Preprocessing.Cleaner.PAKET2.length; i++) {
			for (int j = 0; j < Preprocessing.Cleaner.ngram.length; j++) {
				System.out.println(Preprocessing.Cleaner.PAKET2[i]+" ----- "+Preprocessing.Cleaner.ngram[j]);
				classification(Preprocessing.Cleaner.PAKET2[i], Preprocessing.Cleaner.ngram[j]);
				
			}
		}*/
    	//classification(Preprocessing.Cleaner.ALLFOR, Preprocessing.Cleaner.ngram[0]);
    	//classification(Preprocessing.Cleaner.ALLFOR, Preprocessing.Cleaner.ngram[1]);
    	//String tweet = JOptionPane.showInputDialog(null,"Masukkan tweet");
    	//classificationOne(Preprocessing.Cleaner.ALLFOR, Preprocessing.Cleaner.ngram[0], tweet);
    	
    	//classificationOne(Preprocessing.Cleaner.CONVERT_STEMMING, Preprocessing.Cleaner.ngram[0], "@amelia_triana kami masih terus mencari tahu penyebabnya, Amelia, karena rupanya tidak di semua ATM bermasalah, banyak juga yg berhasil");
    	//classificationOne(Preprocessing.Cleaner.NUMBER_EMO_NEGASI, Preprocessing.Cleaner.ngram[0], "Macetnya parah -_________- #jakarta");

    	
    	String path = "C:\\Users\\user\\Desktop\\Tugas_Twitter\\soundex\\soundex_stem_stop_training_netral.txt";
        String path_pos = "C:\\Users\\user\\Desktop\\Tugas_Twitter\\soundex\\soundex_stem_stop_training_positive.txt";
        String path_neg = "C:\\Users\\user\\Desktop\\Tugas_Twitter\\soundex\\soundex_stem_stop_training_negative.txt";

        String stopWord = "/Users/hadipratama/Documents/Kuliah/Machine_Learning/stopword.txt";
        String[] TargetClass = {"pos", "neg", "not"};

        Learner l = new Learner();
        l.doLearning(path, TargetClass[2]);
        l.doLearning(path_pos, TargetClass[0]);
        l.doLearning(path_neg, TargetClass[1]);

        HashMap<String, Double> r = l.getJumDokPerKelas();
        for (Map.Entry<String, Double> entry : r.entrySet()) {
            String string = entry.getKey();
            Double integer = entry.getValue();
            //System.out.println(string + ":" + integer);
        }
        
        doUjiNB(l, "C:\\Users\\user\\Desktop\\Tugas_Twitter\\soundex\\soundex_stem_stop_uji_netral.txt", 
                "C:\\Users\\user\\Desktop\\Tugas_Twitter\\soundex\\nb_soundex_stem_stop_uji_netral.txt");
        doUjiNB(l, "C:\\Users\\user\\Desktop\\Tugas_Twitter\\soundex\\soundex_stem_stop_uji_positive.txt", 
                "C:\\Users\\user\\Desktop\\Tugas_Twitter\\soundex\\nb_soundex_stem_stop_uji_positive.txt");
        doUjiNB(l, "C:\\Users\\user\\Desktop\\Tugas_Twitter\\soundex\\soundex_stem_stop_uji_negative.txt", 
                "C:\\Users\\user\\Desktop\\Tugas_Twitter\\soundex\\nb_soundex_stem_stop_uji_negative.txt");

//        l.doClassifier("indosat <makian>");
////        HashMap<String, Double> s = l.getScoreClass();
////        for (Map.Entry<String, Double> entry : s.entrySet()) {
////            String string = entry.getKey();
////            Double double1 = entry.getValue();
////            //System.out.println(string + ":" + double1);
////        }
//        String test="";
//        TreeMap<Double, String> ss = l.getScoreClassSort();
//        for (Map.Entry<Double, String> entry : ss.entrySet()) {
//            Double double1 = entry.getKey();
//            String string = entry.getValue();
//            System.out.println(double1 + ":" + string);
//            test=string;
//        }
//        System.out.println(test);


    }
    public static void classificationOne(String prep, String ngram, String tweet)
    {
    	String path = "res/data_"+prep+"/"+ngram+"/training_neutral.txt";
        String path_pos = "res/data_"+prep+"/"+ngram+"/training_positive.txt";
        String path_neg = "res/data_"+prep+"/"+ngram+"/training_negative.txt";


        

        Learner l = new Learner();
        l.doLearning(path, TargetClass[2]);
        l.doLearning(path_pos, TargetClass[0]);
        l.doLearning(path_neg, TargetClass[1]);

        HashMap<String, Double> r = l.getJumDokPerKelas();
        for (Map.Entry<String, Double> entry : r.entrySet()) {
            String string = entry.getKey();
            Double integer = entry.getValue();
            //System.out.println(string + ":" + integer);
        }
        System.out.println ("Tweet :" + tweet);
        String tweetClean="";
        
        if(ngram.equals(Cleaner.ngram[0]))
        {
        	tweetClean = Cleaner.cleanOneTweet(tweet, prep, false);
        }
        else
        {
        	tweetClean = Cleaner.cleanOneTweet(tweet, prep, true);
        }
        
        System.out.println ("Tweet Clean:" + tweetClean);
        l.doClassifier(tweetClean);

        String target="";
        double nbTarget=0;
        TreeMap<Double, String> ss = new TreeMap<Double, String>();
                ss= l.getScoreClassSort();
               // System.out.println(ss);
        
        for (Map.Entry<Double, String> entry : ss.entrySet()) {
            Double double1 = entry.getKey();
            String string = entry.getValue();
            //System.out.println(double1 + ":" + string);
            target = string;
            nbTarget=double1;
            
            
        }
        System.out.println("CLASS ="+target);
        System.out.println("================================");
        ss.clear();
    }
    public static void classification (String prep, String ngram)
    {
    	String path = "res/data_"+prep+"/"+ngram+"/training_neutral.txt";
        String path_pos = "res/data_"+prep+"/"+ngram+"/training_positive.txt";
        String path_neg = "res/data_"+prep+"/"+ngram+"/training_negative.txt";


        

        Learner l = new Learner();
        l.doLearning(path, TargetClass[2]);
        l.doLearning(path_pos, TargetClass[0]);
        l.doLearning(path_neg, TargetClass[1]);

        HashMap<String, Double> r = l.getJumDokPerKelas();
        for (Map.Entry<String, Double> entry : r.entrySet()) {
            String string = entry.getKey();
            Double integer = entry.getValue();
            //System.out.println(string + ":" + integer);
        }
        
        System.out.println("______POSITIVE______");
        doUjiNB(l, "res/data_"+prep+"/"+ngram+"/uji_positive.txt", 
        		"res/data_"+prep+"/"+ngram+"/nb_uji_positive.txt");
        System.out.println("______NEGATIVE_______");
        doUjiNB(l, "res/data_"+prep+"/"+ngram+"/uji_negative.txt", 
        		"res/data_"+prep+"/"+ngram+"/nb_uji_negative.txt");
        System.out.println("______NEUTRAL________");
        doUjiNB(l, "res/data_"+prep+"/"+ngram+"/uji_neutral.txt", 
        		"res/data_"+prep+"/"+ngram+"/nb_uji_neutral.txt");
        System.out.println("==============================================");
    }
    
    public static void doUjiNB (Learner lean, String pathUji, String pathHasil)
    {
        int [] counter = {0,0,0,0};
        
         try {
            BufferedReader read = new BufferedReader(new FileReader(pathUji));
            BufferedWriter write = new BufferedWriter(new FileWriter(pathHasil));
            
             String strLine;
            while ((strLine = read.readLine()) != null) {
                //lean = new Learner();
                lean.doClassifier(strLine);
                counter[0] ++;
                String target="";
                double nbTarget=0;
                TreeMap<Double, String> ss = new TreeMap<Double, String>();
                        ss= lean.getScoreClassSort();
                       // System.out.println(ss);
                
                for (Map.Entry<Double, String> entry : ss.entrySet()) {
                    Double double1 = entry.getKey();
                    String string = entry.getValue();
                    //System.out.println(double1 + ":" + string);
                    target = string;
                    nbTarget=double1;
                    
                    
                }
               
                //System.out.println("================================");
                ss.clear();
                if(target.equals(TargetClass[0]))
                    {
                        counter[1]++;
                    }
                    if(target.equals(TargetClass[1]))
                    {
                        counter[2]++;
                    }
                    if(target.equals(TargetClass[2]))
                    {
                        counter[3]++;
                    }
                write.write(target+"="+strLine+"\r\n");
            }
             //System.out.println(pathHasil);
             //System.out.println("total doc ="+counter[0]);
             System.out.println("pos ="+counter[1]);
             System.out.println("neg ="+counter[2]);
             System.out.println("neut ="+counter[3]);
            read.close();
            write.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
