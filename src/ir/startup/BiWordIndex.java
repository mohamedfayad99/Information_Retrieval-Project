package ir.startup;
import static ir.startup.InvertedIndex1.readDocumentsFromFiles;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class BiWordIndex {
    private Map<String, Integer> index;

    public BiWordIndex() {
        index = new HashMap<>();
    }
static String string_all_data="";
    public void buildIndex(String dataset) {
        String[] words = dataset.split(" ");

        for (int i = 0; i < words.length - 1; i++) {
            String biWord = words[i] + " " + words[i + 1];
            if (index.containsKey(biWord)) {
                index.put(biWord, index.get(biWord) + 1);
            } else {
                index.put(biWord, 1);
            }
        }
    }

    public int getCount(String biWord) {
        return index.getOrDefault(biWord, 0);
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
    public static void main(String[] args) throws IOException {
       
       // System.out.println(index_method());
       // search_method("fast algorithm");
//        biWordIndex.buildIndex(dataset);
//
//        System.out.println(biWordIndex.getCount("as employed")); // Output: 1
//        System.out.println(biWordIndex.getCount("fast algorithm")); // Output: 1
//        System.out.println(biWordIndex.getCount("Whats problems")); // Output: 1
//        System.out.println(biWordIndex.getCount("Python is")); // Output: 0
    }
      public static String index_method() throws IOException{
            BiWordIndex biWordIndex = new BiWordIndex();
                List<String> fileNames = new ArrayList<>();
                    fileNames.add("word.txt");
                  String[] fileNamesArray = fileNames.toArray(new String[fileNames.size()]);
                  String[]   documents = readDocumentsFromFiles(fileNamesArray);  
                String dataset = "";
                for(String data:documents){
                    dataset+=data;
                }
                string_all_data=dataset;
                return dataset;
        }
    //searching
    public static void search_method(String string_from_user) throws IOException{
                BiWordIndex biWordIndex = new BiWordIndex();
                biWordIndex.buildIndex(index_method());

         JOptionPane.showMessageDialog(null,biWordIndex.getCount(string_from_user)); // Output: 1
       
    }
}





//import static java.lang.System.in;
//import java.util.*;
//import javax.swing.JOptionPane;
//
//public class BiWordIndex1 {
//
//    private Map<String, List<Integer>> index;
//
//    public BiWordIndex1() {
//        index = new HashMap<>();
//    }
//
//    public void addDocument(String document) {
//        String[] words = document.split("\\s+");
//        for (int i = 0; i < words.length - 1; i++) {
//            String biword = words[i] + " " + words[i + 1];
//            List<Integer> positions = index.getOrDefault(biword, new ArrayList<>());
//            positions.add(i);
//            index.put(biword, positions);
//        }
//    }
//
//    public List<Integer> getPositions(String biword) {
//        return index.getOrDefault(biword, new ArrayList<>());
//    }
//
//    public static void main(String[] args) {
//        BiWordIndex1 index = new BiWordIndex1();
//        index.addDocument("The quick brown fox jumps over the lazy dog");
//        index.addDocument("The lazy dog jumps over the quick brown fox");
//        index.addDocument("The brown fox is quick and the dog is lazy");
//     //   System.out.println(index.getPositions("quick brown"));
//        JOptionPane.showConfirmDialog(null,index.getPositions("quick brown fox"));
//    }
//}


//other code
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package javaapplication1;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.*;
//import javax.swing.*;
//
//public class BiWordIndex1 extends JFrame implements ActionListener {
//    private JTextField textField;
//    private JTextArea textArea;
//    private JButton indexButton;
//    private Map<String, Set<Integer>> index;
//
//    public BiWordIndex1() {
//        super("BiWordIndex");
//
//        index = new HashMap<>();
//
//        textField = new JTextField(30);
//        textArea = new JTextArea(10, 30);
//        textArea.setEditable(false);
//        indexButton = new JButton("Index");
//        indexButton.addActionListener(this);
//
//        JPanel panel = new JPanel();
//        panel.add(textField);
//        panel.add(indexButton);
//
//        Container container = getContentPane();
//        container.add(panel, BorderLayout.NORTH);
//        container.add(new JScrollPane(textArea), BorderLayout.CENTER);
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        pack();
//        setVisible(true);
//    }
//
//    public void actionPerformed(ActionEvent e) {
//        String text = textField.getText();
//        String[] words = text.split("\\s+");
//        for (int i = 0; i < words.length - 1; i++) {
//            String biword = words[i] + " " + words[i+1];
//            Set<Integer> set = index.get(biword);
//            if (set == null) {
//                set = new TreeSet<>();
//                index.put(biword, set);
//            }
//            set.add(i);
//        }
//        updateTextArea();
//    }
//
//    private void updateTextArea() {
//        StringBuilder sb = new StringBuilder();
//        for (Map.Entry<String, Set<Integer>> entry : index.entrySet()) {
//            sb.append(entry.getKey());
//            sb.append(": ");
//            sb.append(entry.getValue().toString());
//            sb.append("\n");
//        }
//        textArea.setText(sb.toString());
//    }
//
//    public static void main(String[] args) {
//        BiWordIndex1 biWordIndex1 = new BiWordIndex1();
//    }
//}