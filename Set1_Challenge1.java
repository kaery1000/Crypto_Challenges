import java.nio.ByteBuffer;
import java.util.Base64;


public class Set1_Challenge1 {

    public void hexToBase64(String ... hexCode) {	// methods can even take more than one Hex code
        for(String code : hexCode ) {
        	Data datax = new Data(x, Data.Encoding.HEX); //arguments passed to constructor includes the data and its encoding
        	printf("The serial output for the hex code is: %s%n", datax.getBase64());//prints out the result for every code provided
        } 
    }
}
