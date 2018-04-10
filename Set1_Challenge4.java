import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.nio.charset.Charset;


public class Set1_Challenge4 {

     /*Return an XORDecryption representing the best decryption of any of the enc encoded strings in the file 'filename'.*/
    public static XORDecryption FindAndDecrypt(String filename, Data.Encoding enc) {
        String currLine;
        XORDecryption bestXORDecryption = null;
        XORDecryption currXORDecryption;
        /*using try with resources*/
        try ( InputStream inStream = new FileInputStream(filename);
            InputStreamReader inStreamReader = new InputStreamReader(inStream, Charset.forName("UTF-8"));
            BufferedReader bufReader = new BufferedReader(inStreamReader) ) {
            while (true) {
                currLine = bufReader.readLine();
                if (currLine == null) {break};
                currXORDecryption = Set1Challenge3.crackSingleCharXOR(currLine, enc);
                if (bestXORDecryption == null || currXORDecryption.getScore() > bestXORDecryption.getScore()) {
                    bestXORDecryption = currXORDecryption;
                }
            }
        } catch (Exception e) {
            throw new IOException("Unable to access and read file " + filename, e);
        }
        return bestXORDecryption;
    }
}
