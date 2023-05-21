
package ir.startup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;

public class StopWordsFilter {
    private Set<String> stopWords;

    public StopWordsFilter() {
        // Initialize the stop words set with a dataset
        String[] stopWordsArray = {
            "a", "an", "and", "are", "as", "at", "be", "by", "for", "from",
            "has", "he", "in", "is", "it", "its", "of", "on", "that", "the",
            "to", "was", "were", "will", "with"
        };
        stopWords = new HashSet<>(Arrays.asList(stopWordsArray));
    }

    public boolean isStopWord(String word) {
        // Check if the word is a stop word
        return stopWords.contains(word.toLowerCase());
    }

    public static void main(String[] args) {
        StopWordsFilter filter = new StopWordsFilter();

        String[] words = {
            "the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"
        };

        for (String word : words) {
            if (filter.isStopWord(word)) {
                System.out.println(word + " is a stop word.");
            } else {
                System.out.println(word + " is not a stop word.");
            }
        }
index_method("mohamed fayad");
search_method("mohamed fayad");
    }
    
    public static void index_method(String string_from_Algoritm){
        StopWordsFilter filter = new StopWordsFilter();
          String[] array = string_from_Algoritm.split(" ");
        String print_data="";
        for(String data:array){
             if (filter.isStopWord(data)) {
                System.out.println(data + " is a stop word.");
            } else {
                System.out.println(data + " is not a stop word.");
            }
        }
        
    } 
    public static void search_method(String string_from_User){
        StopWordsFilter filter = new StopWordsFilter();
          String[] array = string_from_User.split(" ");
        for(String data:array){
             if (filter.isStopWord(data)) {
               JOptionPane.showMessageDialog(null,data + " is a stop word.");
            } else {
               JOptionPane.showMessageDialog(null,data + " is not a stop word.");
            }
        } 
        
    } 
}