package ir.startup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import javax.swing.JOptionPane;

public class PositionalIndex {
    private Map<String, List<Integer>> index;

    public PositionalIndex() {
        index = new HashMap<>();
    }

    public void addDocument(String document) {
        String[] terms = document.split("\\s+"); // Split the document into terms

        for (int position = 0; position < terms.length; position++) {
            String term = terms[position].toLowerCase();

            if (!index.containsKey(term)) {
                index.put(term, new ArrayList<>());
            }

            List<Integer> positions = index.get(term);
            positions.add(position);
        }
    }

    public List<Integer> getPosition(String term) {
        return index.getOrDefault(term.toLowerCase(), Collections.emptyList());
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
//        PositionalIndex positionalIndex = new PositionalIndex();
//String ali="ali mo an";
//        // Add documents to the positional index
//        positionalIndex.addDocument(ali);
//        positionalIndex.addDocument("This is the second document");
//        positionalIndex.addDocument("And this is the third document");
//
//        // Retrieve positions of a term
//        List<Integer> positions = positionalIndex.getPosition("an");
//
//        // Print the positions
//        System.out.println("Positions of the term 'document': " + positions);
    }
    static String stringfortoknization="";
    public static String Indixmethod() throws IOException{
         String[] documents = null;
         List<String> fileNames = new ArrayList<>();
            fileNames.add("word.txt");          
            String[] fileNamesArray = fileNames.toArray(new String[fileNames.size()]);
             documents = readDocumentsFromFiles(fileNamesArray);
              String checkingstring="";
            for(String ss :documents){
                checkingstring+=ss.toLowerCase();
            }
             System.out.println(checkingstring);
             stringfortoknization=checkingstring;
        return checkingstring;
             
    }
    public static void searchmethod(String string_from_user) throws IOException{
            PositionalIndex positionalIndex = new PositionalIndex();
            positionalIndex.addDocument(Indixmethod());
            List<Integer> positions = positionalIndex.getPosition(string_from_user);

           // Print the positions
          JOptionPane.showMessageDialog(null,"Positions of the term 'document': " + positions);
}
}
