import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


public class Set1_Challenge4 {
	
	public static void main(String[] args) {
        String filename = "4.txt";
        Data.Encoding enc = Data.Encoding.HEX;

        XORDecryption result = null;
        try {
            result = Set1_Challenge4.xorFindAndDecrypt(filename, enc);
        } catch (Exception e) {
        	System.out.println(e.getMessage());          
        }

        System.out.println("The hex string decrypted is:");
        System.out.println("\"" + result.getCiphertext() + "\"");
        System.out.println("and the decryption by single character XOR is:");
        System.out.println("\"" + result.getPlaintext() + "\"");
        System.out.println("This decryption received a score of " + result.getScore() + " and ");
        System.out.println("was carried out with the character '" + result.getKey());
    }

	// Return an XORDecryption representing the object with best decryption of any of the encoded lines in the file .
    public static XORDecryption xorFindAndDecrypt(String fileName, Data.Encoding enc) throws IOException {
    	
        String currLine;
        XORDecryption bestXORDecryption = null;
        XORDecryption currXORDecryption;
        //using try with resources to open and close connection// no need to use finally in this case
        try ( InputStream inStream = new FileInputStream(fileName);
              InputStreamReader inStreamReader = new InputStreamReader(inStream, Charset.forName("UTF-8"));
              BufferedReader bufReader = new BufferedReader(inStreamReader) ) {
            while (true) {
                currLine = bufReader.readLine();	//reading from the file
               
                if (currLine == null) { //base condition
                	break;
                }
                currXORDecryption = Set1_Challenge3.crackSingleCharXOR(currLine, enc); //returning the score and storing in the object
                
                //updating the score
                if (bestXORDecryption == null || currXORDecryption.getScore() > bestXORDecryption.getScore()) {
                    bestXORDecryption = currXORDecryption;
                }
            } //end of while loop
        } catch (Exception e) {
            throw new IOException("Unable to access and read file " + fileName, e);
        }
        
        return bestXORDecryption;
    }
}
