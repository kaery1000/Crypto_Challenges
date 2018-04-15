import java.util.ArrayList;
import java.util.List;


public class Set1_Challenge3 {
	
	public static void main(String[] args) {
        String hex = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736";
        Data.Encoding enc = Data.Encoding.HEX;

        System.out.println("Given the hex string:");
        System.out.println("\"" + hex + "\"");

        XORDecryption result = Set1_Challenge3.crackSingleCharXOR(hex, enc);

        System.out.println("the decryption by single character XOR is:");
        System.out.println("\"" + result.getPlaintext() + "\"");
        System.out.println("This decryption received a score of " + result.getScore() + " and ");
        System.out.println("was carried out with the character '" + result.getKey());
    }

	
	// Return an XORDecryption object containing the score of the best decryption, the character used for
    //   decryption, and the decrypted version of the input Data object
    public static XORDecryption crackSingleCharXOR(String ciphertext, Data.Encoding enc) {
        Data cipherTextData = new Data(ciphertext, enc);
        return crackSingleCharXOR(cipherTextData);
    }
    
    //helper method for the above method
    public static XORDecryption crackSingleCharXOR(Data cipherTextData) {
        char chr0 = Data.chr(0);	
        char chr127 = Data.chr(127);
        List<XORDecryption> xorDecryptions = new ArrayList<XORDecryption>();
        
        //now add the objects into the array with their score, the key used
        for (char possibleKey = chr0; possibleKey <= chr127; possibleKey++) { 
            xorDecryptions.add(decryptWith(possibleKey, cipherTextData));	
        }
        XORDecryption bestXORDecryption = xorDecryptions.get(0);	//just taking the first object from the list and storing in an object
        for (XORDecryption currXORDecryption : xorDecryptions) {
        	//this is for computing the the best score from the array list
            if (currXORDecryption.getScore() > bestXORDecryption.getScore()) {
                bestXORDecryption = currXORDecryption;
            }
        }
        return bestXORDecryption;	//return the best score
    }

	

   //returns the XORDecryption object with its score, the key used and the plaintext along with the input Data object
    private static XORDecryption decryptWith(char key, Data cipherTextData) {
        int ciphertextSize = cipherTextData.getSize();	//returns the length of the data object and store it

        //create a string builder the size of the byte array
        //store the key value into it so can we have a string with key in all its byte places 
        StringBuilder repSB = new StringBuilder(ciphertextSize);
        for (int i = 0; i < ciphertextSize; i++) {
        	repSB.append(key);
        }
        String repString = repSB.toString();
        //now construct another data obj with key value stored in every byte
        Data repStringData = new Data(repString, Data.Encoding.ASCII);
        
        //now XOR both the new data and the original ciphertext         
        Data plaintextData = Set1_Challenge2.XORCode(repStringData, cipherTextData);
        String plaintext = plaintextData.getASCII();
        
        //now calculate the score if the plaintext is likley in english
        double score = scoreIsEnglish(plaintext);

        return new XORDecryption(score, key, plaintext, cipherTextData);
    }

  
    // Score if the  string 'plaintext' is English text.
    private static double scoreIsEnglish(String plainText) {
        double scoreAccumulator = 0;
        for (int i = 0; i < plainText.length(); i++) {
            if (isPrintableASCII(plainText.charAt(i))) {
                scoreAccumulator++;
            }
            if (isASCIILetterOrSpace(plainText.charAt(i))) {
                scoreAccumulator++;
            }
        }
        return scoreAccumulator / plainText.length();
    }
    
    // Return true if c is a printable ASCII character
    private static boolean isPrintableASCII(char c) {
        return ' ' <= c && c < '~';
    }

    // Return true if c is an ASCII letter character 
    private static boolean isASCIILetterOrSpace(char c) {
        return ('a' <= c && c < 'z') || ('A' <= c && c <= 'Z') || (c == ' ');
    }
}
