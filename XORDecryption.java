public class XORDecryption extends Decryption {

    private double score;
    private char key;

    // Constructor
    public XORDecryption(double score, char key,  String plaintext, Data ciphertext) {
        super(plaintext, ciphertext);	//calling the super class constructor
        this.score = score;
        this.key = key;
    }

    // Getters
    public double getScore() {
        return score;
    }
    public char getKey() {
        return key;
    }


}