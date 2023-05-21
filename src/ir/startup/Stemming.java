/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.startup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

import org.tartarus.snowball.ext.PorterStemmer;

public class Stemming {
    public static String[] readDocumentsFromFiles(String[] fileNames) {
        ArrayList<String> docList = new ArrayList<>();
        for (String fileName : fileNames) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                StringBuilder text = new StringBuilder();
                boolean isInParagraph = false;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith(".I")) {
                        // Start a new document
                        if (text.length() > 0) {
                            // Split the text into individual words
                            String[] words = text.toString().split("\\s+");
                            // Add the words to the document list
                            docList.add(String.join(" ", words));
                            text.setLength(0);
                            isInParagraph = false;
                        }
                    } else if (line.startsWith(".W")) {
                        // Start capturing text
                        isInParagraph = true;
                    } else if (isInParagraph && !line.startsWith(".")) {
                        // Append line to text
                        text.append(line).append("\n");
                    }
                }
                // Add the last document
                if (text.length() > 0) {
                    // Split the text into individual words
                    String[] words = text.toString().split("\\s+");
                    // Add the words to the document list
                    docList.add(String.join(" ", words));
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return docList.toArray(new String[0]);
    }

    public static void main(String[] args) throws IOException {
//        PorterStemmer stemmer = new PorterStemmer();
//
//        // Read documents from files
//        String[] fileNames = {"word.txt"};
//        String[] documents = readDocumentsFromFiles(fileNames);
//
//        // Process documents
//        for (String document : documents) {
//            String[] words = document.split("\\s+");
//            for (String word : words) {
//                stemmer.setCurrent(word);
//                stemmer.stem();
//                String stemmedWord = stemmer.getCurrent();
//                System.out.println("Original: " + word + " Stemmed: " + stemmedWord);
//            }
//        }
         search_method("mohameds","moj nbhj ghjmbjk mlk kj kjhj jgh dgf td jhu");
    }
    
    public static void search_method(String string_from_user,String string_from_Algoritm){
         PorterStemmer stemmer = new PorterStemmer();
         List<String> list = new ArrayList<>(Arrays.asList(string_from_Algoritm.split(" ")));
         list.replaceAll(String::trim);
            for (String data : list) {
                           stemmer.setCurrent(string_from_user);
                           stemmer.stem();
                           String stemmedWord = stemmer.getCurrent();
                           JOptionPane.showMessageDialog(null,"Original: " + string_from_user + " Stemmed: " + stemmedWord);
                           break;
                   }
    }
     public static void index_method(String string_from_Algoritm){
         PorterStemmer stemmer = new PorterStemmer();
         List<String> list = new ArrayList<>(Arrays.asList(string_from_Algoritm.split(" ")));
         list.replaceAll(String::trim);
            for (String data : list) {
                       String[] words = data.split("\\s+");
                       for (String word : words) {
                           stemmer.setCurrent(word);
                           stemmer.stem();
                           String stemmedWord = stemmer.getCurrent();
                           System.out.println("Original: " + word + " Stemmed: " + stemmedWord);
                           
                       }
                   }
    } 
}
