import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.Arrays;


public class Set1Challenge8 {

     /*Detect which line of the encoded data found in file is encoded under AES in ECB mode
       and return an AESECBDecryption representing the decryption of that line.*/
    public static String aesECBFind(String filename, Data.Encoding enc)
    throws IOException {
        double bestScore = 0;
        double currentScore;
        String bestLine = "";
        String currentLine;
        try ( InputStream inStream = new FileInputStream(filename);
              InputStreamReader inStreamReader = new InputStreamReader(inStream, Charset.forName("UTF-8"));
              BufferedReader bufReader = new BufferedReader(inStreamReader) ) {
            while (true) {
                currentLine = bufReader.readLine();
                if (currentLine == null) {break;}
                currentScore = scoreIsAESECB(currentLine, enc);
                if (currentScore > bestScore) {
                    bestLine = currentLine;
                    bestScore = currentScore;
                }
            }
        } catch(Exception e) {
            throw new IOException("Unable to access and read file " + filename, e);
        }
        return bestLine;
    }

    /* Score how likely it is that encoded string cipherText is encrypted under AES in ECB mode.*/
    private static double scoreIsAESECB(String cipherText, Data.Encoding enc) {
        Data cipherTextData = new Data(cipherText, enc);
        if (cipherTextData.getSize() % 16 != 0) {
            return 0;
        }
        ArrayList<byte[]> byteChunks = new ArrayList<byte[]>();
        for (int i = 0; i < cipherTextData.getSize() / 16; i++) {
            byteChunks.add(Arrays.copyOfRange(cipherTextData.getBytes(), i * 16, i * 16 + 16));
        }
        double numDuplicates = 0;
        for (int i = 0; i < byteChunks.size(); i++) {
            for (int j = i; j < byteChunks.size(); j++) {
                if (Arrays.equals(byteChunks.get(i), byteChunks.get(j))) {
                    numDuplicates++;
                }
            }
        }
        return numDuplicates / cipherTextData.getSize();
    }

}
