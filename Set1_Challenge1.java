
public class Set1_Challenge1 {

	public static void main(String[] args) {
        String hex = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";
        String base64 = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t";

        System.out.println("Given the hex string:");
        System.out.println("\"" + hex + "\"");
        System.out.println("the base-64 encoding should be:");
        System.out.println("\"" + base64 + "\"");

        Data hexData = new Data(hex, Data.Encoding.HEX);
        String calculatedBase64 = Set1_Challenge1.hexToBase64(hex);

        if (calculatedBase64.equals(base64)) {
            System.out.println("Computed Correctly");
        } else {
            System.out.println("Not Computed Correctly");
        }
        System.out.println("");
        System.out.println("This string translates in ASCII to:");
        System.out.println("\"" + hexData.getASCII() + "\"");
    }
	
    // Return the base-64 encoded string corresponding to hex string x    
    public static String hexToBase64(String hexString) {
    	//now we create a new data object where we use the constructor which take in a string and its encoding type
        Data data = new Data(hexString, Data.Encoding.HEX);	
        
        //returning the data in its base64 form
        return data.getBase64();
    }

}