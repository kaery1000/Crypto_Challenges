import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class Set1_Challenge7 {
	
	 public static void main(String[] args) {
	        String key = "YELLOW SUBMARINE";
	        String filename = "C:/Users/Karan/Desktop/7.txt";
	        Data.Encoding enc = Data.Encoding.BASE64;

	        AESECBDecryption result = null;
	        try {
	            result = Set1_Challenge7.aesECBDecryptFile(key, filename, enc);
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }

	        System.out.println("The decryption of file " + filename + " is:");
	        System.out.println("\"" + result.getPlaintext() + "\"");
	        System.out.println("This decryption was carried out with the key:" + result.getKey() );
	}

	 // Return an AESECBDecryption representing the decryption of the encoded data with a key, from the file
    public static AESECBDecryption aesECBDecryptFile(String key, String filename, Data.Encoding enc) throws IOException, IllegalArgumentException {
        String currLine;
        String ciphertext = "";
        
        //again using try with resources
        try ( InputStream inStream = new FileInputStream(filename);
              InputStreamReader inStreamReader = new InputStreamReader(inStream, Charset.forName("UTF-8"));
              BufferedReader buffReader = new BufferedReader(inStreamReader) ) {
            //reading the file and storing encoded lines into the buffer
        	while (true) {
                currLine = buffReader.readLine();
                if (currLine == null) break;
                ciphertext += currLine;
            }
        } catch (Exception e) {
            throw new IOException("Unable to access file " + filename, e);
        }
        
        //create a Data object with this ciphertext and enc of Base64
        Data ciphertextData = new Data(ciphertext, enc);
        AESECBDecryption result;

        try {
        	//using the java built in libraries to create cypher object
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");	//creates an AES key
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");	//create  the AES ECB cipher
            cipher.init(Cipher.DECRYPT_MODE, aesKey);  

            String plaintext = new String(cipher.doFinal(ciphertextData.getBytes()));
            result = new AESECBDecryption(key, plaintext, ciphertextData);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to decrypt contents of file " + filename, e);
        }
        return result;
    }
}
