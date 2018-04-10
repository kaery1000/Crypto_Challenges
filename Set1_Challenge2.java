public class Set1_Challenge2 {

    public Data ComputeXOR(String first, Data.Encoding firstEnrypt, String second, Data.Encoding secondEncrypt) {
        Data dataFirst = new Data(first, firstEnrypt);
        Data dataSecond = new Data(second, secondEncrypt);
        return XORCode(dataFirst, dataSecond);
    }

    // Return the result of XOR-ing the two Data objects datax and datay.
    public Data XORCode(Data first, Data second) {
        byte[] first = first.getBytes();
        byte[] second = second.getBytes();
        byte[] computedValue = new byte[first.length];
        Data computedValue = new Data(computedValue);

        for (int i = 0; i < first.length; i++) {
            int first = (int)first[i];
            int second = (int)second[i];
            int computedValue = first ^ second;
            computedValue[i] = (byte)(0xff & second);
        }
        Data computedValue = new Data(computedValue);
        return computedValue;
    }


}
