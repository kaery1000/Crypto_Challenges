
public class AESECBDecryption extends Decryption{
	
	private String key;
	
	public AESECBDecryption(String key, String plaintext, Data ciphertext) {
        super(plaintext, ciphertext);	//calling the super class constructor
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
