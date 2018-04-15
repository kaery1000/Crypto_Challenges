import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;


public class Set1_Challenge8 {
	
	public static void main(String[] args) {
        String filename = "8.txt";
        Data.Encoding enc = Data.Encoding.HEX;

        String result;
        try {
            result = Set1_Challenge8.aesECBFind(filename, enc);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println("In file " + filename + ", the AES ECB encrypted line is:");
        System.out.println("\"" + result + "\"");
    }

	// Detect which line of the encoded data found in filename which is encoded under AES in ECB mode and return AESECBDecryption 
    public static String aesECBFind(String filename, Data.Encoding enc)throws IOException {
        double bestScore = 0;
        double currScore;
        String bestLine = "";
        String currLine;
        try ( InputStream inStream = new FileInputStream(filename);
              InputStreamReader inStreamReader = new InputStreamReader(inStream, Charset.forName("UTF-8"));
              BufferedReader bufReader = new BufferedReader(inStreamReader) ) {
            while (true) {
            	//reading lines from the file once at a time
                currLine = bufReader.readLine();
                if (currLine == null) {
                	break;
                }
                //getting score
                currScore = scoreIsAESECB(currLine, enc);
                
                //update the bestScore
                if (currScore > bestScore) {
                    bestLine = currLine;
                    bestScore = currScore;
                }
            }
        } catch(Exception e) {
            throw new IOException("Unable to access file " + filename, e);
        }
        return bestLine;
    }

    // Score if  encoded string is encrypted under AES in ECB mode.
    private static double scoreIsAESECB(String ciphertext, Data.Encoding enc) {
        Data ciphertextData = new Data(ciphertext, enc);
        if (ciphertextData.getSize() % 16 != 0) {
            return 0;
        }
        ArrayList<byte[]> byteArray = new ArrayList<byte[]>();
        for (int i = 0; i < ciphertextData.getSize() / 16; i++) {
            byteArray.add(Arrays.copyOfRange(ciphertextData.getBytes(), i * 16, i * 16 + 16));
        }
        double numDuplicates = 0;
        for (int i = 0; i < byteArray.size(); i++) {
            for (int j = i; j < byteArray.size(); j++) {
                if (Arrays.equals(byteArray.get(i), byteArray.get(j))) {
                    numDuplicates++;
                }
            }
        }
        return (numDuplicates / ciphertextData.getSize());
    }

}
