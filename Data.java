import java.nio.ByteBuffer;
import java.util.Base64;


public class Data {
	
	 // Byte array to store information
    private byte[] data;

    // Constructors
    public Data(int n) {
        data = intToBytes(n);
    }
    public Data(byte[] bytes) {
        data = bytes;
    }
    
    public Data(Data d) {
        this(d.getInt());
    }
    public Data(String info, Encoding encoding) {
        switch(encoding) {
        case DEC :
        	 data = intToBytes(Integer.parseInt(info));
        	 break;
        
        case HEX :
        	
        	//making base conditions correct
        	 if (info.length() == 1) {
                 info = "0" + info;
             } else if (info.length() == 0) {
                 info = "00";
             }
        	 
             int halfLength = info.length() / 2;
             data = new byte[halfLength];
             for (int i = 0; i < halfLength; i++) {
                 data[i] = hexToByte(info.charAt(2*i), info.charAt(2*i + 1));
             }
             break;
             
        case BASE64 :
        	data = Base64.getDecoder().decode(info);
        	break;
        
        default :	//default is ASCII
        	 data = new byte[info.length()];
             for (int i = 0; i < info.length(); i++) {
                 data[i] = (byte)(info.charAt(i));
             }        
        }     
    }
    //
   

    // Return true if that equals this; return false otherwise.
    public boolean equals(Data that) {
        int thisLength = getSize();
        if (thisLength != that.getSize()) {
            return false;
        }
        byte[] thatBytes = that.getBytes();
        for (int i = 0; i < thisLength; i++) {
            if (data[i] != thatBytes[i]) {
                return false;
            }
        }
        return true;
    }

    // Return data as an integer.
    public int getInt() {
        int result = 0;
        for (byte b : data) {
            result = (result << 8) + b + ((b < 0) ? 256 : 0);
        }
        return result;
    }

    // Return data as a hex string.
    public String getHex() {
        String result = "";
        for (byte b : data) {
            result += byteToHex(b);
        }
        if (result.length() % 2 == 1) {
            result = "0" + result;
        } else if (result.length() == 0) {
            result = "00";
        }
        return result;
    }

    // Return data as a base64 string.
    public String getBase64() {
        return Base64.getEncoder().encodeToString(data);
    }

    // Return data as an ASCII string.
    public String getASCII() {
        String result = "";
        for (byte b : data) {
            result += chr(b);
        }
        return result;
    }

    // Return data as a byte array.
    public byte[] getBytes() {
        return data;
    }

    // Return the number of bytes in this object.
    public int getSize() {
        return data.length;
    }

    // Return the ASCII character corresponding to integer n.
    public static char chr(int n) {
        return (char) n;
    }

    // Return the numeric value of an ASCII character c.
    public static int ord(char c) {
        return (int)c;
    }

  

    // Set data based on the byte values of integer n.
    private static byte[] intToBytes(int n) {
        return ByteBuffer.allocate(4).putInt(n).array();
    }

    // Return a byte corresponding to hex characters x and y.
    private static byte hexToByte(char x, char y) {
        return (byte) (16 * hexDigitToInt(x) + hexDigitToInt(y));
    }

    // Return a length two hex string corresponding to byte b.
    private static String byteToHex(byte b) {
        int byteVal = (int)b;
        return "" + intToHexDigit(byteVal / 16) + intToHexDigit(byteVal % 16);
    }

    // Return a hex digit corresponding to integer n.
    private static char intToHexDigit(int n) {
        if (0 <= n && n <= 9) {
            return chr(48 + n); // 48 == ord('0')
        } else {
            return chr(97 + n - 10); // 97 == ord('a')
        }
    }

    // Return an integer corresponding to hex digit x.
    private static int hexDigitToInt(char x) {
        if ('0' <= x && x <= '9') {
            return x - 48;      // 48 == ord('0')
        } else if ('A' <= x && x <= 'Z') {
            return x + 10 - 65; // 65 == ord('A')
        } else {
            return x + 10 - 97; // 97 == ord('a')
        }
    }

    
 // Enum (as an inner class) type to indicate the encoding of strings
    public enum Encoding { 
        DEC,
        HEX,
        BASE64,
        ASCII
    }
}