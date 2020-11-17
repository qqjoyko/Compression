import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Boggle{
    private int n;
    private char[] data;
    public Boggle(int n, char[] data){
        this.n=n;
        this.data=data;
    }
    public char[] adjacent(int position){
        char[] alias = new char[8];
        if(position-this.n-1>=0)
            alias[0]=this.data[position-this.n-1];
        else alias[0]='/';
        if(position-this.n>=0)
            alias[1]=this.data[position-this.n];
        else alias[1]='/';
        if(position-this.n+1>=0)
            alias[2]=this.data[position-this.n+1];
        else alias[2]='/';
        if(position-1>=0&&position%this.n!=0)
            alias[3]=this.data[position-1];
        else alias[3]='/';

        if(position+1<=this.n*this.n&&position%this.n!=0)
            alias[4]=this.data[position+1];
        else alias[4]='/';
        if(position+this.n-1<=this.n*this.n)
            alias[5]=this.data[position+this.n-1];
        else alias[5]='/';
        if(position+this.n<=this.n*this.n)
            alias[6]=this.data[position-this.n-1];
        else alias[6]='/';
        if(position+this.n+1<=this.n*this.n)
            alias[7]=this.data[position+this.n+1];
        else alias[7]='/';
        return alias;
    }
    public ArrayList<String> compare(Boggle bog, TrieTree dict){
        ArrayList<String> answer = new ArrayList<>();
        String ans = "";
        if(bog.n==0||bog.data==null) return answer;
        backTrack(bog,dict,answer,ans);
        return answer;
    }
    public void backTrack(Boggle bog,TrieTree dict,ArrayList<String> answer,String ans){
        String tmp="";
        int i=0;
        if(dict.search(ans)&&ans.length()>=bog.n-1) {
            answer.add(ans);
            for(int j=0;j<=8;j++) {
                tmp=ans;
                ans=ans+adjacent(i)[j];
                backTrack(bog,dict,answer,ans);
                ans=tmp;
            }
        }
        else{
            for(;i<bog.n*bog.n;i++){
                ans = ans + bog.data[i];
                for(int j=0;j<=8;j++) {
                    tmp=ans;
                    ans=ans+adjacent(i)[j];
                    backTrack(bog,dict,answer,ans);
                    ans=tmp;
                }
                ans = "";
            }
        }
    }
    public static void main(String[] args){

    }
}
class TrieNode{
    char ch;
    int freqs;  //记录单词出现次数
    Map<Character, TrieNode> nodeMap;

    public TrieNode(char ch, int freqs, Map<Character, TrieNode> nodeMap) {
        this.ch = ch;
        this.freqs = freqs;
        this.nodeMap = nodeMap;
    }
}
class TrieTree {
    private TrieNode root;

    public TrieTree(){
        root = new TrieNode('\u0000', 0, new HashMap<Character, TrieNode>());
    }
    public int query(String str){
        TrieNode cur=root;
        if(root==null){
            return 0;
        }
        for(int i=0;i<str.length();i++){
            TrieNode child=cur.nodeMap.get(str.charAt(i));
            if(child==null){
                return 0;
            }else {
                cur = child;
            }
        }
        return cur.freqs;
    }
    public void add(String str){
        TrieNode cur=root;
        if(root==null){
            return;
        }
        for(int i=0;i<str.length();i++){
            TrieNode child=cur.nodeMap.get(str.charAt(i));
            if(child==null){
                TrieNode node=new TrieNode(str.charAt(i),0,new HashMap<Character, TrieNode>());
                cur.nodeMap.put(str.charAt(i),node);
                cur=cur.nodeMap.get(str.charAt(i));
            }else{
                cur=child;
            }
        }
        cur.freqs++;
    }
    public boolean search(String str){
        TrieNode cur=root;
        if(root==null){
            return false;
        }
        for(int i=0;i<str.length();i++){
            TrieNode child=cur.nodeMap.get(str.charAt(i));
            if(child==null){
                return false;
            }else {
                cur = child;
            }
        }
        return true;
    }
}