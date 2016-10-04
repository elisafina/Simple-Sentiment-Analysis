/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Preprocessing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Splitter {
   
    public static ArrayList<Twitter> data = new ArrayList<Twitter>();
    public static ArrayList<Twitter> dataUji = new ArrayList<Twitter>();
    
    public static void getData(String prep, String ngram)
    {
        try {
            BufferedReader read[] = new BufferedReader[3];
            read[0] = new BufferedReader(new FileReader("res/data_"+prep+"/"+ngram+"/positive.txt"));
            read[1] = new BufferedReader(new FileReader("res/data_"+prep+"/"+ngram+"/negative.txt"));
            read[2] = new BufferedReader(new FileReader("res/data_"+prep+"/"+ngram+"/neutral.txt"));
            
          
            for (int i = 0; i < read.length; i++) {
                String strLine;
                 while ((strLine = read[i].readLine()) != null) {
                     Twitter twit = new Twitter(strLine,i);
                     data.add(twit);
                
            }
                 Collections.shuffle(data);
                 read[i].close();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void splitData()
    {
        int jumTraining = data.size()/3;
        int size = data.size();
        
        for (int i = 0; i < jumTraining; i++) {
           
            int randIndex = (int) Math.round(Math.random()*100);
            Twitter twit = data.get(randIndex);
            dataUji.add(twit);
            data.remove(randIndex);
            size--;
        }
    }
    
    public static void printData (boolean isTraining, ArrayList<Twitter> dat, String prep, String ngram)
    {
        BufferedWriter write[] = new BufferedWriter[3];
        try {
             if(isTraining)
            {
            	 
             write[0] = new BufferedWriter(new FileWriter("res/data_"+prep+"/"+ngram+"/training_positive.txt"));
            write[1] = new BufferedWriter(new FileWriter("res/data_"+prep+"/"+ngram+"/training_negative.txt"));
            write[2] = new BufferedWriter(new FileWriter("res/data_"+prep+"/"+ngram+"/training_neutral.txt"));
            }
             else
             {
            	 write[0] = new BufferedWriter(new FileWriter("res/data_"+prep+"/"+ngram+"/uji_positive.txt"));
                 write[1] = new BufferedWriter(new FileWriter("res/data_"+prep+"/"+ngram+"/uji_negative.txt"));
                 write[2] = new BufferedWriter(new FileWriter("res/data_"+prep+"/"+ngram+"/uji_neutral.txt"));
             }
             
             for (int i = 0; i < dat.size(); i++) {
                Twitter twit = dat.get(i);
                write[twit.getTarget()].write(twit.getText()+"\r\n");
            }
             write[0].close();
             write[1].close();
             write[2].close();
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    
    
    public static void getData2()
    {
        try {
            BufferedReader read[] = new BufferedReader[3];
            read[0] = new BufferedReader(new FileReader("res/data_raw/positive.dat"));
            read[1] = new BufferedReader(new FileReader("res/data_raw/negative.dat"));
            read[2] = new BufferedReader(new FileReader("res/data_raw/neutral.dat"));
            
          
            for (int i = 0; i < read.length; i++) {
                String strLine;
                 while ((strLine = read[i].readLine()) != null) {
                     Twitter twit = new Twitter(strLine,i);
                     data.add(twit);
                
            }
                 Collections.shuffle(data);
                 read[i].close();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void splitData2()
    {
        int jumTraining = data.size()/3;
        int size = data.size();
        
        for (int i = 0; i < jumTraining; i++) {
           
            int randIndex = (int) Math.round(Math.random()*100);
            Twitter twit = data.get(randIndex);
            dataUji.add(twit);
            data.remove(randIndex);
            size--;
        }
    }
    
    public static void printData2 (boolean isTraining, ArrayList<Twitter> dat)
    {
        BufferedWriter write[] = new BufferedWriter[3];
        try {
             if(isTraining)
            {
            	 
             write[0] = new BufferedWriter(new FileWriter("res/data_raw/training_positive.txt"));
            write[1] = new BufferedWriter(new FileWriter("res/data_raw/training_negative.txt"));
            write[2] = new BufferedWriter(new FileWriter("res/data_raw/training_neutral.txt"));
            }
             else
             {
            	 write[0] = new BufferedWriter(new FileWriter("res/data_raw/uji_positive.txt"));
                 write[1] = new BufferedWriter(new FileWriter("res/data_raw/uji_negative.txt"));
                 write[2] = new BufferedWriter(new FileWriter("res/data_raw/uji_neutral.txt"));
             }
             
             for (int i = 0; i < dat.size(); i++) {
                Twitter twit = dat.get(i);
                write[twit.getTarget()].write(twit.getText()+"\r\n");
            }
             write[0].close();
             write[1].close();
             write[2].close();
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    
    public static void main(String[] args) {
    	
    	/*for (int i = 0; i < Cleaner.PAKET1.length; i++) {
			for (int j = 0; j < Cleaner.ngram.length; j++) {
				
				data= new ArrayList<>();
				dataUji = new ArrayList<>();
				getData(Cleaner.PAKET1[i], Cleaner.ngram[j]);
		        System.out.println(data);
		        System.out.println(data.size());
		        splitData();
		        System.out.println(data.size());
		        System.out.println(dataUji.size());
		        printData(true, data, Cleaner.PAKET1[i], Cleaner.ngram[j]);
		        printData(false, dataUji, Cleaner.PAKET1[i], Cleaner.ngram[j]);
		        data.clear();
		        dataUji.clear();
			}
		}*/

		data= new ArrayList<>();
		dataUji = new ArrayList<>();
		getData2();
        System.out.println(data);
        System.out.println(data.size());
        splitData2();
        System.out.println(data.size());
        System.out.println(dataUji.size());
        printData2(true, data);
        printData2(false, dataUji);
        data.clear();
        dataUji.clear();
        
    }
}
