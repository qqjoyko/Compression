package compre;

import java.io.*;
import java.util.*;


public class LZW {
    /** Compress a string to a list of output symbols. */
    public static List<Integer> compress(String uncompressed) {
        // Build the dictionary.
        int dictSize = 256;
        Map<String, Integer> dictionary = new HashMap<String,Integer>();
        for (int i = 0; i < 256; i++)
            dictionary.put("" + (char)i, i);

        String w = "";
        List<Integer> result = new ArrayList<Integer>();
        for (char c : uncompressed.toCharArray()) {
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



    public static List<Integer> testBWInputCompressed(String file) throws IOException {
        String BWstring = fileReader(file);
        List<Integer> compressed = compress(BWstring);
        return compressed;
    }







    public static void main(String[] args) throws IOException {
        //small test


        List<Integer> smallCompressed = compress("TOBEORNOTTOBEORTOBEORNOT");
        System.out.println(smallCompressed);
        System.out.println("\n");
        String smallDecompressed = decompress(smallCompressed);
        System.out.println(smallDecompressed);
        System.out.println("\n");



        //use BW string to test LZW compress and decompress
        List<Integer> compressed = testBWInputCompressed("D:/JavaLearning_S1/593final_git/Decameron.txt");
        System.out.println(compressed);  //code
        System.out.println( "\n");

//        String decompressed = decompress(compressed);
//        System.out.println(decompressed);
//        System.out.println( " ");

    }
}