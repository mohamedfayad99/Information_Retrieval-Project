
package ir.startup;


import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 *
 * @author Nesma Mahmoud
 */
public class Searcher {
    public static void main(String[] args) throws IOException, ParseException {
    // searchmethod();
  }
    static String string_for_researcger="";
    
    public static  void searchmethod(String string_for_researcger ) throws IOException, ParseException{
        
        Directory indexDirct = FSDirectory.open(new File("indx")); // index directory 
        IndexReader reader = DirectoryReader.open(indexDirct);// Open index
        IndexSearcher is = new IndexSearcher(reader);      
        // build & parse the query then run it
        QueryParser parser = new QueryParser(Version.LUCENE_41,"contents",new SimpleAnalyzer(Version.LUCENE_41)); 
        Query query = parser.parse(string_for_researcger);                
        TopDocs hits = is.search(query, 10); // Search index
          
        // display the query result
        String s2=("Found " + hits.totalHits +     
         " document(s) that matched query '" + string_for_researcger + "':");
        String s1="";
        int x=0;
        for(ScoreDoc scoreDoc : hits.scoreDocs) {
          Document doc = is.doc(scoreDoc.doc); //Retrieve matching document  
         //  s1[x]=doc.get("fullpath");
          s1 +=doc.get("fullpath")+"\n";
          string_for_researcger=doc.toString();
    }
        JOptionPane.showMessageDialog(null,(s2+"\n"+s1));
        reader.close();  //Close IndexReader
        
        
    }
    public static String Content(){
            return string_for_researcger;
        }
}
