public class Set1_Challenge5 {
	
	
	public static void main(String[] args) {
        String plaintext = "Burning 'em, if you ain't quick and nimble\nI go crazy when I hear a cymbal";
        String key = "ICE";
        String ciphertext = "0b3637272a2b2e63622c2e69692a23693a2a3c6324202d623d63343c2a26226324272765272a282b2f20430a652e2c652a3124333a653e2b2027630c692b20283165286326302e27282f";

        System.out.println("Encrypting the plaintext:");
        System.out.println("\"" + plaintext + "\"");
        System.out.println("With the key:");
        System.out.println("\"" + key + "\"");
        System.out.println("Should give the cipherText (in hex):");
        System.out.println("\"" + ciphertext + "\"");

        Data result = Set1_Challenge5.encryptWithRepKeyXOR(key, plaintext);

        if (result.getHex().equals(ciphertext)) {
            System.out.println("This is computed correctly");
        } else {
            System.out.println("This is not computed coreectly.");
        }
    }
	
	 // Return the hex string created by encrypting ASCII string plaintext with key under repeating XOR.
    public static Data encryptWithRepKeyXOR(String key, String plaintext) {
        String repString = getRepString(plaintext.length(), key);
        Data ciphertext = Set1_Challenge2.ComputeXOR(repString, Data.Encoding.ASCII, plaintext, Data.Encoding.ASCII);
        return ciphertext;
    }

    // Return a string of length len consisting of repetitions of key.
    private static String getRepString(int len, String key) {
        StringBuilder repStringBuilder = new StringBuilder(len + key.length() - 1);
        while (repStringBuilder.length() < len) {
            repStringBuilder.append(key);	//this will have repeateted keys of ICEICS.....
        }
        repStringBuilder.setLength(len); //trim the string builder to set the length equal to the ciphertext length
        return repStringBuilder.toString();
    }
    
    // Return the ASCII string created by decrypting encoding encoded string ciphertext with key under repeating XOR.
    public static String decryptWithRepKeyXOR(String key, Data ciphertext) {
        String ciphertextHex = ciphertext.getHex();
        String repString = getRepString(ciphertext.getSize(), key);
        Data plaintextData = Set1_Challenge2.ComputeXOR(repString, Data.Encoding.ASCII, ciphertextHex, Data.Encoding.HEX);
        String plaintext = plaintextData.getASCII();
        return plaintext;
    }
}
