package ir.startup;

    import java.io.File;
    import java.io.FileReader;
    import java.io.IOException;
    import org.apache.lucene.analysis.core.SimpleAnalyzer;
    import org.apache.lucene.document.Document;
    import org.apache.lucene.document.Field;
    import org.apache.lucene.index.IndexWriter;
    import org.apache.lucene.index.IndexWriterConfig;
    import org.apache.lucene.store.Directory;
    import org.apache.lucene.store.FSDirectory;
    import org.apache.lucene.util.Version;
    import org.apache.pdfbox.Loader;
    import org.apache.pdfbox.pdmodel.PDDocument;
    import org.apache.pdfbox.text.PDFTextStripper;

    /**
     *
     * @author Nesma Mahmoud
     */
    public class Indexer {

    /**
         * @param args the command line arguments
         */
        public static void main(String[] args) throws IOException {
    }String content="";
       
        public  void indexmethod() throws IOException{
            
            // index directory 
    //      Directory indexDirct = FSDirectory.open(new File("indexDir")); 
             Directory indexDictory = FSDirectory.open(new File("indx"));
             SimpleAnalyzer sa = new SimpleAnalyzer(Version.LUCENE_42);
    //     configure index with the analyzer instance
          IndexWriterConfig analyzerConfig = 
                    new IndexWriterConfig(Version.LUCENE_42, sa);  
          IndexWriter writer = new IndexWriter(indexDictory, analyzerConfig);
          String dataDir = "dataset"; // Index *.txt files from this directory
          File[] files = new File(dataDir).listFiles();


            for (File f: files) {   // for each file in the directory
               if (!f.isDirectory() && !f.isHidden() && f.exists() && f.canRead()){
               System.out.println("Indexing " + f.getCanonicalPath());

                  Document doc = new Document(); // 

                  PDDocument pdfdoc= Loader.loadPDF(f);
                  
                    String pdfContent = new PDFTextStripper().getText(pdfdoc);
                     content=pdfContent.toString();
                    doc.add(new Field("contents",pdfContent,Field.Store.YES, Field.Index.ANALYZED));



                    //doc.add(new Field("filename", f.getName(), //Index file name
                    //Field.Store.YES, Field.Index.NOT_ANALYZED));

                    doc.add(new Field("fullpath", f.getCanonicalPath(), // Index file full path    
                    Field.Store.YES, Field.Index.NOT_ANALYZED));

                    writer.addDocument(doc); // Add document to Lucene index
                }
        }
            System.out.println("# of Docs indexed = " + writer.numDocs());
            System.out.println("Lucene Index Built Successfully.");
        writer.close();  // close the writer
        }
         public String Content(){
            return content;
        }
    }
