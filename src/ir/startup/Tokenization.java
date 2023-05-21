package ir.startup;
import java.util.StringTokenizer;

public class Tokenization {
   public static void main(String[] args) {
//      String xx= "This is a sample text for tokenization in Java.";
//       System.out.println(tokenmethod(xx));
   }
   public static String tokenmethod(String tokenstring){
       
       String text="" ;
      
      // Split the text into tokens using whitespace as the delimiter
      String[] tokens = tokenstring.split("\\s+");
      
      // Loop through the tokens and print them
      for (String token : tokens) {
       //   return token;
         text += token + "\n";
        // System.out.println(token);
      }
     return text;
   }

}
