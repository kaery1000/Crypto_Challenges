
public class Set1_Challenge2 {
	
	 public static void main(String[] args) {
	        String hex1 = "1c0111001f010100061a024b53535009181c";
	        String hex2 = "686974207468652062756c6c277320657965";
	        String hex3 = "746865206b696420646f6e277420706c6179";

	        Data data2 = new Data(hex2, Data.Encoding.HEX);
	        Data data3 = new Data(hex3, Data.Encoding.HEX);

	        System.out.println("The XOR of the hex string:");
	        System.out.println("\"" + hex1 + "\"");
	        System.out.println("and the hex string:");
	        System.out.println("\"" + hex2 + "\"");
	        System.out.println("should be:");
	        System.out.println("\"" + hex3 + "\"");

	        Data result = Set1_Challenge2.ComputeXOR(hex1, Data.Encoding.HEX, hex2, Data.Encoding.HEX);

	        if (result.getHex().equals(hex3)) {
	            System.out.println("Computed Correctly");
	        } else {
	            System.out.println("Not Computed Correctly");
	            System.out.println("The output is :");
	            System.out.println("\"" + result.getHex() + "\"");
	        }
	        System.out.println("");
	        System.out.println("The second string translates in ASCII to:");
	        System.out.println("\"" + data2.getASCII() + "\"");
	        System.out.println("and the third string translates in ASCII to:");
	        System.out.println("\"" + data3.getASCII() + "\"");
	    }

	 public static Data ComputeXOR(String first, Data.Encoding firstEnrypt, String second, Data.Encoding secondEncrypt) {
	        Data dataFirst = new Data(first, firstEnrypt);	//creating the first data object
	        Data dataSecond = new Data(second, secondEncrypt);	//creating the second data object
	        return XORCode(dataFirst, dataSecond);
	    }

	    // Return the result of XORing the two Data objects dataFirst and dataSecond.
	    public static Data XORCode(Data first, Data second) {
	        byte[] firstBA = first.getBytes();	//store the first bytes in a Byte array
	        byte[] secondBA = second.getBytes();	//store the second bytes in a Byte array
	        byte[] computedBA = new byte[firstBA.length];	//make a new byte array object //same length a inputs
	       
	        //now compute the XOR for each bytes of the inputs and return the computed data object
	        for (int i = 0; i < firstBA.length; i++) {
	            int firstB = (int)firstBA[i];	
	            int secondB = (int)secondBA[i];
	            int computedB = firstB ^ secondB;
	            computedBA[i] = (byte)(0xff & computedB);
	        }
	        Data computedValue = new Data(computedBA);
	        return computedValue;
	    }


}
