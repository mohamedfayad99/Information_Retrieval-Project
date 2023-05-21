
package ir.startup;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

/**
 *
 * @author Nesma Mahmoud
 */
public class LuceneAnalyzers {
    private static final String SAMPLE_TEXT
  = "XY&Z Corporation – xyz@example.com";
    public static void main(String[] args) throws IOException {
        StandardAnalyzer sa = new StandardAnalyzer(Version.LUCENE_42);
        WhitespaceAnalyzer wsa = new WhitespaceAnalyzer(Version.LUCENE_42);
        SimpleAnalyzer sima = new SimpleAnalyzer(Version.LUCENE_42);
        StopAnalyzer swa = new StopAnalyzer(Version.LUCENE_42);
        System.out.println("StandardAnalyzer ->  " + analyze(SAMPLE_TEXT, sa));
        System.out.println("WhitespaceAnalyzer ->  " + analyze(SAMPLE_TEXT, wsa));
        System.out.println("SimpleAnalyzer ->  " + analyze(SAMPLE_TEXT, sima));
        System.out.println("StopAnalyzer ->  " + analyze(SAMPLE_TEXT, swa));

    }
    
    public static List<String> analyze(String text, Analyzer analyzer) throws IOException{
    List<String> result = new ArrayList<String>();
    TokenStream tokenStream = analyzer.tokenStream( "", new StringReader(text));
    
    CharTermAttribute attr = tokenStream.addAttribute(CharTermAttribute.class);
    tokenStream.reset();
    while(tokenStream.incrementToken()) {
       result.add(attr.toString());
    }       
    return result;
}
}
