

import java.util.Arrays;

public class BWT {

    public static void main(String[] args) {
        String str = "banana";
        System.out.println("input："+str);
        String enCodeStr = enCode(str);
        System.out.println("The encoded string is："+enCodeStr);
        System.out.println("The decoded string is："+deCode(enCodeStr));
    }

    // bwt encode
    public static String enCode(String line) {
        String str = line + "&";
        int len = str.length();
        // 1.rotate
        char[] charArray = str.toCharArray();
        char[][] ch = new char[len][len];
        for (int i = 0; i < len; i++) {
            char[] c_tmp = charArray.clone();
            for (int j = 0; j < len; j++) {
                ch[i][j] = c_tmp[j];
                if (j <= len - 2)
                    charArray[j + 1] = c_tmp[j];

            }
            charArray[0] = c_tmp[len - 1];

        }
        // 2.sort
        String[] strings = new String[len];
        for (int i = 0; i < len; i++) {
            StringBuffer chline = new StringBuffer();
            for (char c : ch[i]) {
                chline.append(c);
            }
            strings[i] = chline.toString();
        }

        Arrays.sort(strings);
        // 3.last line
        StringBuffer sBuffer = new StringBuffer();
        for (String s : strings) {
            sBuffer.append(s.substring(len - 1, len));
        }
        return sBuffer.toString();
    }

    // bwt decode
    public static String deCode(String str) {
        int len = str.length();
        String[] strArr = new String[len];
        for (int i = 0; i < len; i++) {
            strArr[i] = str.substring(i, i + 1) + ":" + i;
        }
        Arrays.sort(strArr);
        // for(String string : strArr)
        // System.out.println(string);
        StringBuffer sb = new StringBuffer();
        int num = 0;
        int corr = Integer.parseInt(strArr[0].split(":")[1]);
        while (num < len-1) {
            sb.append(strArr[corr].split(":")[0]);
            corr =Integer.parseInt( strArr[corr].split(":")[1]);
            num++;
        }
        return sb.toString();

    }

}