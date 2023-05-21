package ir.startup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javax.swing.JOptionPane;

public class TermDocumentMatrix {
    private static Set<String> vocabulary;
    private static Map<String, Map<String, Integer>> documentFrequencies;
    private static int[][] termDocumentMatrix;
    private static List<String> sortedVocabulary;

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
static String stringfortoknization="";
    public static void indexDocuments() {
        vocabulary = new HashSet<>();
        documentFrequencies = new HashMap<>();
 // Define the file paths for the three datasets
        String[] filePaths = {"word.txt"};

        // Read documents from files
        String[] documents = readDocumentsFromFiles(filePaths);

        // Index the documents
      //  indexDocuments(documents);
        for (String document : documents) {
            // Preprocess the text by removing punctuation and converting to lowercase
            String[] words = document.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

            // Count the term frequencies for this document
            Map<String, Integer> termFrequencies = new HashMap<>();
            for (String word : words) {
                if (!word.isEmpty()) {
                    termFrequencies.put(word, termFrequencies.getOrDefault(word, 0) + 1);
                    vocabulary.add(word);
                }
            }

            // Add the term frequencies for this document to the document frequencies map
            documentFrequencies.put(document, termFrequencies);
        }

        // Create the term-document matrix
        termDocumentMatrix = new int[vocabulary.size()][documents.length];
        sortedVocabulary = new ArrayList<>(vocabulary);
        Collections.sort(sortedVocabulary);
        for (int i = 0; i < sortedVocabulary.size(); i++) {
            for (int j = 0; j < documents.length; j++) {
                Map<String, Integer> termFrequencies = documentFrequencies.get(documents[j]);
                int frequency = termFrequencies.getOrDefault(sortedVocabulary.get(i), 0);
                termDocumentMatrix[i][j] = frequency > 0 ? 1 : 0;
            }
        }
        
        // print the term-document matrix to the console
        for (int i = 0; i < sortedVocabulary.size(); i++) {
            System.out.print(sortedVocabulary.get(i) + "\t");
            stringfortoknization+=sortedVocabulary.get(i) ;
            for (int j = 0; j < documents.length; j++) {
                System.out.print(termDocumentMatrix[i][j] + "\t");
                stringfortoknization+=termDocumentMatrix[i][j];
            }stringfortoknization+="\t"+"\n";
            System.out.println();
        }
        System.out.println();
    }

    public static void searchmethod(String term) {
         List<Integer> matchingDocuments = new ArrayList<>();

        // Find the index of the term in the sorted vocabulary
        int index = Collections.binarySearch(sortedVocabulary, term);
        if (index >= 0) {
            // Term found in the vocabulary
            for (int j = 0; j < termDocumentMatrix[index].length; j++) {
                if (termDocumentMatrix[index][j] == 1) {
                    matchingDocuments.add(j);
                }
            }
        }
         JOptionPane.showMessageDialog(null,"Matching documents for term '" + term + "':");
        for (int docIndex : matchingDocuments) {
           JOptionPane.showMessageDialog(null,"Document " + (docIndex + 1));
        }
        
    }

    public static void main(String[] args) {
        indexDocuments();
        // Define the file paths for the three datasets
//        String[] filePaths = {"word1.txt"};
//
//        // Read documents from files
//        String[] documents = readDocumentsFromFiles(filePaths);
//
//        // Index the documents
//        indexDocuments(documents);

        // Search for a term
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter a term to search: ");
//        String searchTerm = scanner.nextLine();
//        scanner.close();
//       // List<Integer> matchingDocuments = search(searchTerm);
//        System.out.println("Matching documents for term '" + searchTerm + "':");
//        for (int docIndex : matchingDocuments) {
//            System.out.println("Document " + (docIndex + 1));
//        }
searchmethod("mohamed");
    } 
    
    
}
