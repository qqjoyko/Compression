import java.util.*;

public class BW{
    static final String BEGIN = "\u0002";//Use STX//cite: wiki BWT way avoid the old way(only use a end mark#)can't deal with "space"
    static final String END = "\u0003";//Use ETX
    private ArrayList<String> status;
    private String revStr;
    private int len;
    public BW(String str){
        this.revStr= BEGIN+str+END;
        this.len=revStr.length();
        status= new ArrayList<>(len);
        for(int i=0;i<this.len;i++) {
            String part1 = revStr.substring(i);
            String part2 = revStr.substring(0, i);
            status.add(part1 + part2);
        }
        this.status.sort(String::compareTo);
    }
    public String getstatus(int i){
        return this.status.get(i);
    }
    public String enCode(){
        StringBuilder sb = new StringBuilder();
        for (String s : this.status) {
            sb.append(s.charAt(s.length() - 1));
        }
        return sb.toString();
    }
    public static String deCode(String str){//back to rotate way,low efficient//LF way can't fix bugs,need help
        int len = str.length();
        List<String> table = new ArrayList<>();
        for (int i = 0; i < len; ++i) {
            table.add("");
        }
        for (int j = 0; j < len; ++j) {
            for (int i = 0; i < len; ++i) {
                table.set(i, str.charAt(i) + table.get(i));
            }
            table.sort(String::compareTo);
        }
        for (String row : table) {
            if (row.endsWith(END)) {
                return row.substring(1, len - 1);//get rid of STX,ETX
            }
        }
        return "";
    }
    public static void main(String args[]){
        BW one = new BW("I like banana !$%^& t2es3t^&     no problem");
        String tmp = one.enCode();
        System.out.println(tmp);
        System.out.println(deCode(tmp));
    }
}
