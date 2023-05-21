package ir.startup;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Normalization {
    
    //لو عندك كلمه تبحث عن كل الكمات اللي شبها
    private List<String> documents;

    public Normalization(List<String> documents) {
        this.documents = documents;
    }

    public List<String> search(String query) {
        List<String> results = new ArrayList<>();
        String normalizedQuery = normalize(query);

        for (String document : documents) {
            String normalizedDocument = normalize(document);
            if (normalizedDocument.contains(normalizedQuery)) {
                results.add(document);
            }
        }

        return results;
    }

    public static String normalize(String input) {
        // Convert to lowercase
        String normalized = input.toLowerCase();

        // Remove non-alphanumeric characters (except spaces)
        normalized = normalized.replaceAll("[^a-z0-9\\s]", "");

        // Remove excess whitespace
        normalized = normalized.trim().replaceAll("\\s+", " ");
        normalized = Normalizer.normalize(normalized, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", " ");
        return normalized;
    }

    public static void main(String[] args) {
//        List<String> documents = readDocumentsFromFile("word1.txt");
//
//        // Take query from user
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter your query: ");
//        String query = scanner.nextLine();
//
//        Normalization engine = new Normalization(documents);
//        List<String> results = engine.search(query);
//
//        System.out.println(results);
    }
    public static void normlizatiometod_index(String string_from_Algoritm){
        
        //  List<String> documents = readDocumentsFromFile("word1.txt");
        String[] text=string_from_Algoritm.split(" ");
         String getingdata="";
         for(String data :text){
             getingdata+=data+"\n";
         }
        System.out.println(getingdata);
    }
    public static void normlizatiometod_search(String string_from_user,String string_from_Algoritm){
           //read from Algoritm chosen
             List<String> list = new ArrayList<>(Arrays.asList(string_from_Algoritm.split(" ")));
             // Optional: Trim whitespace from each element
             list.replaceAll(String::trim);
            Normalization engine = new Normalization(list);
            List<String> results = engine.search(string_from_user);
             JOptionPane.showMessageDialog(null,results);
    }

    public static List<String> readDocumentsFromFile(String filename) {
        List<String> documents = new ArrayList<>();

        // Read documents from a file
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                documents.add(line);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        return documents;
    }
}
