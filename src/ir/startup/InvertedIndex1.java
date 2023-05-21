package ir.startup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import javax.swing.JOptionPane;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class InvertedIndex1 {

    private Map<String, Set<Integer>> invertedIndex;

    public InvertedIndex1() {
        invertedIndex = new HashMap<>();
    }
 
    public void createIndex(String[] documents) {
        int docId = 0;

        for (String document : documents) {
            String[] terms = document.split("\\s+");

            for (String term : terms) {
                term = term.toLowerCase();

                if (!invertedIndex.containsKey(term)) {
                    invertedIndex.put(term, new HashSet<>());
                }

                invertedIndex.get(term).add(docId);
            }

            docId++;
        }
    }
   static String stringfortoknization="";
    public static void search(String term) {
        term = term.toLowerCase();
           String checkingstring="";
            for(String ss :indexmethod()){
                checkingstring+=ss.toLowerCase();
            }
           //  System.out.println(checkingstring);
            // stringfortoknization=checkingstring;
            InvertedIndex1 index = new InvertedIndex1();
            index.createIndex(indexmethod());
            Set<Integer> searchResults = index.invertedIndex.getOrDefault(term, Collections.emptySet());
            if (searchResults.isEmpty()) {
                JOptionPane.showMessageDialog(null,term+ " not found.");
            } else {
                for (Integer docId : searchResults) {
                JOptionPane.showMessageDialog(null,term+" Founded in Document " + (docId + 1));
                }
            }
        
    }

    public static String[] readDocumentsFromFiles(String[] fileNamesArray) throws IOException {
        List<String> documents = new ArrayList<>();

        for (String fileName : fileNamesArray) {
            byte[] bytes = Files.readAllBytes(Paths.get(fileName));
            String content = new String(bytes);
            documents.add(content);
        }

        return documents.toArray(new String[0]);
    }

    public static void main(String[] args) {
    //   indexmethod();
    //   search();
    }
    public static String[] indexmethod(){
         String[] documents = null;
         try {
            List<String> fileNames = new ArrayList<>();
            fileNames.add("word1.txt");
            fileNames.add("dataset1.txt");
            fileNames.add("dataset2.txt");           
            String[] fileNamesArray = fileNames.toArray(new String[fileNames.size()]);
             documents = readDocumentsFromFiles(fileNamesArray);
              String checkingstring="";
            for(String ss :documents){
                checkingstring+=ss.toLowerCase();
            }
             System.out.println(checkingstring);
             stringfortoknization=checkingstring;
                return documents;
                 } catch (IOException e) {
                    e.printStackTrace();
                }
        return documents;
    }
}