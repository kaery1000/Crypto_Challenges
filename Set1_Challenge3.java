import java.util.ArrayList;
import java.util.List;


public class Set1_Challenge3 {

    /*Return an XORDecryption with the score of the best decryption*/
    public static XORDecryption crackSingleCharXOR(String ciphertext, Data.Encoding enc) {
        Data ciphertextData = new Data(ciphertext, enc);
        return crackSingleCharXOR(ciphertextData);
    }

    /*Overloaded helper mehtod for the above method*/
    public static XORDecryption crackSingleCharXOR(Data ciphertextData) {
        char chr0 = Data.chr(0);
        char chr127 = Data.chr(127);
        List<XORDecryption> xorDecryptions = new ArrayList<XORDecryption>(127);
        for (char possibleKey = chr0; possibleKey <= chr127; possibleKey++) {
            xorDecryptions.add(decryptWith(possibleKey, ciphertextData));
        }
        XORDecryption bestXORDecryption = xorDecryptions.get(0);
        for (XORDecryption current : xorDecryptions) {
            if (current.getScore() > bestXORDecryption.getScore()) {
                bestXORDecryption = current;
            }
        }
        return bestXORDecryption;
    }

   

     /*Return an XORDecryption with key key, plaintext determined by decrypting the Data object
     ciphertextData with key, and score determined by scoring the decryption on how likely it is
     to be English text.*/
    private static XORDecryption decryptWith(char key, Data ciphertextData) {
        int ciphertextSize = ciphertextData.getSize();

        StringBuffer repStringBuffer = new StringBuffer(ciphertextSize);
        for (int i = 0; i < ciphertextSize; i++) {
            repStringBuffer.append(key);
        }
        String repString = repStringBuffer.toString();
        Data repStringData = new Data(repString, Data.Encoding.ASCII);

        Data plaintextData = Set1Challenge2.fixedXOR(repStringData, ciphertextData);
        String plaintext = plaintextData.getASCII();
        double score = scoreEnglish(plaintext);

        return new XORDecryption(score, key, plaintext, ciphertextData);
    }

    /* Score for how likely it is that string plaintext is English text.*/
    private static double scoreEnglish(String plaintext) {
        double score = 0;
        for (int i = 0; i < plaintext.length(); i++) {
            if (isPrintableASCII(plaintext.charAt(i))) {
                score++;
            }
            if (isASCIILetterOrSpace(plaintext.charAt(i))) {
                score++;
            }
        }
        return score / plaintext.length();
    }

    /*  Return true if c is a printable ASCII character and false if not.*/
    private static boolean isPrintableASCII(char c) {
        return ' ' <= c && c < '~';
    }

    /* Return true if c is an ASCII letter character(or even white space) and false if not.*/
    private static boolean isASCIILetterOrSpace(char c) {
        return ('a' <= c && c < 'z') || ('A' <= c && c <= 'Z') || (c == ' ');
    }

}
