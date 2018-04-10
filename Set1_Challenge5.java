public class Set1_Challenge5 {

     /*Return the hex string created by encrypting ASCII string plaintext with key under repeating XOR*/
    public static Data encryptWithRepKeyXOR(String key, String plaintext) {
        String repString = getRepString(plaintext.length(), key);
        Data cipherText = Set1_Challenge2.fixedXOR(repString, Data.Encoding.ASCII, plaintext, Data.Encoding.ASCII);
        return cipherText;
    }

   /* Return the ASCII string after decrypting encoding encoded string cipherText with key under repeating XOR*/
    public static String decryptWithRepKeyXOR(String key, Data cipherTextHex) {
        String cipherTextHex = cipherTextHex.getHex();
        String repString = getRepString(cipherTextHex.getSize(), key);
        Data plaintextData = Set1_Challenge2.fixedXOR(repString, Data.Encoding.ASCII, cipherTextHex, Data.Encoding.HEX);
        String plaintext = plaintextData.getASCII();
        return plaintext;
    }

    /*Return a string of length len consisting of repetitions of key*/
    private static String getRepString(int len, String key) {
        StringBuilder repSB = new StringBuilder(len + key.length() - 1);
        while (repSB.length() < len) {
            repSB.append(key);
        }
        repSB.setLength(len);
        return repSB.toString();
    }

}
