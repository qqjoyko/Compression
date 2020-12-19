package compre;

import com.sun.javaws.IconUtil;

import java.io.*;
import java.util.*;


public class LZW {
    /** Compress a string to a list of output symbols. */
    public static List<Integer> compress(String inputString) {
        // Build the dictionary.
        int dictSize = 256;
        Map<String, Integer> dictionary = new HashMap<String,Integer>();
        for (int i = 0; i < 256; i++)
            dictionary.put("" + (char)i, i);

        String w = "";
        List<Integer> result = new ArrayList<Integer>();
        for (char c : inputString.toCharArray()) {
            String wc = w + c;
            if (dictionary.containsKey(wc))
                w = wc;
            else {
                result.add(dictionary.get(w));
                // Add wc to the dictionary.
                dictionary.put(wc, dictSize++);
                w = "" + c;
            }
        }

        // Output the code for w.
        if (!w.equals(""))
            result.add(dictionary.get(w));
        return result;
    }

    /** Decompress a list of output ks to a string. */
    public static String decompress(List<Integer> compressed) {
        // Build the dictionary.
        int dictSize = 256;
        Map<Integer,String> dictionary = new HashMap<Integer,String>();
        for (int i = 0; i < 256; i++)
            dictionary.put(i, "" + (char)i);

        String w = "" + (char)(int)compressed.remove(0);
        StringBuffer result = new StringBuffer(w);
        for (int k : compressed) {
            String entry;
            if (dictionary.containsKey(k))
                entry = dictionary.get(k);
            else if (k == dictSize)
                entry = w + w.charAt(0);
            else
                throw new IllegalArgumentException("Bad compressed k: " + k);

            result.append(entry);

            // Add w+entry[0] to the dictionary.
            dictionary.put(dictSize++, w + entry.charAt(0));

            w = entry;
        }
        return result.toString();
    }

    public static String fileReader(String sc) throws IOException {
        File file = new File(sc);
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis,"utf-8"),5*1024*1024);

        StringBuilder output = new StringBuilder();
        String line = "";
        StringBuilder content = new StringBuilder();
        while((line = reader.readLine()) != null){
            content.append(line);
            if(content.length()>2000){
                BW one = new BW(content.toString());
                output.append(one.enCode());
                content = new StringBuilder();
            }
        }
        BW one = new BW(content.toString());
        output.append(one.enCode());
        return output.toString();
    }

    public static String fileReader2(String sc) throws IOException {
        File file = new File(sc);
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "utf-8"), 5 * 1024 * 1024);

        StringBuilder output = new StringBuilder();
        String line = "";
        StringBuilder content = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            content.append(line);
        }
        return content.toString();
    }



    public static List<Integer> testBWInputCompressed(String file) throws IOException {
        String BWstring = fileReader(file);
        List<Integer> compressed = compress(BWstring);
        return compressed;
    }







    public static void main(String[] args) throws IOException {
        //small test


//        System.out.println("original string: ABABAB");
//        System.out.println("\n");
//        List<Integer> smallCompressed = compress("6 66666666? 6 666  ?   ?  ?66?   6");
//        System.out.println("compress result:" + smallCompressed);
//        System.out.println(smallCompressed.size());
//        System.out.println("\n");
//        String smallDecompressed = decompress(smallCompressed);
//        System.out.println("decompress result:" + smallDecompressed);




//        use BW string to test LZW compress and decompress
        long startTime1=System.currentTimeMillis();
        List<Integer> compressed = testBWInputCompressed("D:/JavaLearning_S1/593final_git/Decameron.txt");
        System.out.println(compressed.size());  //code
        System.out.println( "\n");
        long endTime1=System.currentTimeMillis();

        System.out.println("running time： "+(endTime1-startTime1)+"ms");
//

        long startTime=System.currentTimeMillis();
        List<Integer> compressed2 = compress(fileReader2("D:/JavaLearning_S1/593final_git/Decameron.txt"));
//        System.out.println(compressed2);
        System.out.println(compressed2.size());
        long endTime=System.currentTimeMillis();

        System.out.println("running time： "+(endTime-startTime)+"ms");

//        String input = "abcabcabcabcabcabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcd";
//        System.out.println("input string: " + input);
//        System.out.println("input length: " + input.length());
//        System.out.println("compressed output: " + compress(input));
//        System.out.println("compressed output length: " + compress(input).size());

//        BW one = new BW(input);
//        String tmp = one.enCode();
//        System.out.println("BW string: " + tmp);
//        System.out.println("compressed output: " + compress(tmp).size());
//        String decompressed = decompress(compressed);
//        System.out.println(decompressed);
//        System.out.println( " ");

    }
}