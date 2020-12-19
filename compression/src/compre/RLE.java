package compre;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RLE {
    public static String encode(String input) {
        StringBuffer tmp = new StringBuffer();
        for (int i=0;i<input.length();i++) {
            int len = 1;
            while (i+1<input.length()&&input.charAt(i)==input.charAt(i+1)) {
                len++;
                i++;
            }
            tmp.append(input.charAt(i));
            tmp.append(len);
        }
        return tmp.toString();
    }
    public static String decode(String encrypted) {
        StringBuffer tmp = new StringBuffer();
        int len;
        Pattern pattern = Pattern.compile("[a-zA-Z]|[0-9]+");
        Matcher matcher = pattern.matcher(encrypted);
        while (matcher.find()) {
            String tmp2 = matcher.group();
            matcher.find();
            len = Integer.parseInt(matcher.group());
            while (len>0) {
                tmp.append(tmp2);
                len--;
            }
        }
        return tmp.toString();
    }

    public static void main(String[] args) throws IOException {
        File file = new File("Decameron.txt"); // change the file need to be compressed
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis,"utf-8"),5*1024*1024);
        String line = "";
        StringBuilder content = new StringBuilder();
        while((line = reader.readLine()) != null){
            content.append(line);
        }

        String input = content.toString();
        System.out.println("input: "+input);
        byte[] buff1 = input.getBytes();
        double i = buff1.length;

        String output=encode(input);
        byte[] buff2 = output.getBytes();
        double j = buff2.length;
        System.out.println("encode: "+output);
        System.out.println("compressed data/original data:"+(j/i));


//        System.out.println("decode:"+decode(output));
    }
}
