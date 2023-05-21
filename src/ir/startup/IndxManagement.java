package ir.startup;

import java.io.IOException;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

/**
 *
 * @author Nesma Mahmoud
 */
public class IndxManagement {
    public static void main(String[] args) throws IOException {

        String[] ids = {"1", "2"};   // id
        String[] unindexed = {"Netherlands", "Italy"};  //country
        String[] unstored = {"Amsterdam has lots of bridges",  // contents
                                 "Venice has lots of canals"};
        String[] text = {"Amsterdam", "Venice"};  // city
  
        Directory directory = new RAMDirectory();  // index directory RAM
//     configure index with the analyzer instance
      IndexWriterConfig analyzerConfig = 
                new IndexWriterConfig(Version.LUCENE_42, new SimpleAnalyzer(Version.LUCENE_42));  
      IndexWriter writer = new IndexWriter(directory, analyzerConfig);
      
      // build docs & index them inside Lucene 
    for (int i = 0; i < ids.length; i++) {      //3
      Document doc = new Document();
      doc.add(new Field("id", ids[i],
                        Field.Store.YES,
                        Field.Index.NOT_ANALYZED));
      doc.add(new Field("country", unindexed[i],
                        Field.Store.YES,
                        Field.Index.NO));
      doc.add(new Field("contents", unstored[i],
                        Field.Store.NO,
                        Field.Index.ANALYZED));
      doc.add(new Field("city", text[i],
                        Field.Store.YES,
                        Field.Index.ANALYZED));
      writer.addDocument(doc);
    }
    //--------------------------------------------------------------------------
    /*
        System.out.println("# of Docs before delete = " + writer.numDocs());
        deleteDoc(writer);
        System.out.println("# of Docs after delete = " + writer.numDocs());
*/
    
    //--------------------------------------------------------------------------
    } // end main()
    
    /*
    //delete doc from Lucene index
public static void deleteDoc(IndexWriter writer) throws IOException {
    writer.deleteDocuments(new Term("id", "1"));  //Delete first document
    writer.commit();
  }
*/
public static void updateDoc(IndexWriter writer) throws IOException {

    // create a new Doc
    Document newdoc = new Document();                               
    newdoc.add(new Field("id", "1",
                      Field.Store.YES,
                      Field.Index.NOT_ANALYZED));    
    newdoc.add(new Field("country", "Netherlands",
                      Field.Store.YES,
                      Field.Index.NO));                
    newdoc.add(new Field("contents",                    
                      "Den Haag has a lot of museums",
                      Field.Store.NO,
                      Field.Index.ANALYZED));      
    newdoc.add(new Field("city", "Den Haag",
                      Field.Store.YES,
                      Field.Index.ANALYZED));       

    // replace an old doc with the new doc 
    writer.updateDocument(new Term("id", "1"), newdoc);
    System.out.println("Doc 1 updated successfully");
  }
}
