package compre;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.nio.file.*;
import java.util.LinkedList;
/**
 *
 * @author Kang Yang
 */
public class Huffman {
    private String str; 
    private HNode root;
    private boolean flag;
    private LinkedList<CharData> charList;
    private LinkedList<HNode> NodeList;

     private class CharData {
         int num = 1; 
         char c;

         public CharData(char ch){
             c = ch;
         }
     }

   
    public void creatHfmTree(String str) {
        this.str = str;

        NodeList = new LinkedList<HNode>();
        charList = new LinkedList<CharData>();

       
        getCharNum(str);

       
        creatNodes();

        
        Sort(NodeList);

       
        creatTree();

        
        root = NodeList.get(0);
    }

    
    private void getCharNum(String str) {

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i); 
            flag = true;

            for (int j = 0; j < charList.size(); j++) {
                CharData data = charList.get(j);

                if(ch == data.c){ 
                    
                    data.num++;
                    flag = false;
                    break;
                }
            }

            if(flag){
                
                charList.add(new CharData(ch)); 
            }

        }

    }

   
    private void creatNodes() {

        for (int i = 0; i < charList.size(); i++) {
            String data = charList.get(i).c + "";
            int count = charList.get(i).num;

            HNode node = new HNode(data, count); 
            NodeList.add(node); 
        }

    }

  
    private void creatTree() {

        while (NodeList.size() > 1) {
          
            HNode left = NodeList.poll();
            HNode right = NodeList.poll();

           
            left.code = "0";
            right.code = "1";
            setCode(left);
            setCode(right);

            int parentWeight = left.count + right.count;
            HNode parent = new HNode(parentWeight, left, right);

            NodeList.addFirst(parent);
            Sort(NodeList); 
        }
    }

   
    private void Sort(LinkedList<HNode> nodelist) {
        for (int i = 0; i < nodelist.size() - 1; i++) {
            for (int j = i + 1; j < nodelist.size(); j++) {
                HNode temp;
                if (nodelist.get(i).count > nodelist.get(j).count) {
                    temp = nodelist.get(i);
                    nodelist.set(i, nodelist.get(j));
                    nodelist.set(j, temp);
                }

            }
        }

    }

 
    private void setCode(HNode root) {

        if (root.lChild != null) {
            root.lChild.code = root.code + "0";
            setCode(root.lChild);
        }

        if (root.rChild != null) {
            root.rChild.code = root.code + "1";
            setCode(root.rChild);
        }
    }

  
    private void output(HNode node) {

        if (node.lChild == null && node.rChild == null) {
            System.out.println(node.data + ": " + node.code);
        }
        if (node.lChild != null) {
            output(node.lChild);
        }
        if (node.rChild != null) {
            output(node.rChild);
        }
    }

  
    public void output() {
        output(root);
    }



  

    private String hfmCodeStr = "";

  
    public String toHufmCode(String str) {

        for (int i = 0; i < str.length(); i++) {
            String c = str.charAt(i) + "";
            search(root, c);
        }

        return hfmCodeStr;
    }

  
    private void search(HNode root, String c) {
        if (root.lChild == null && root.rChild == null) {
            if (c.equals(root.data)) {
                hfmCodeStr += root.code; 
            }
        }
        if (root.lChild != null) {
            search(root.lChild, c);
        }
        if (root.rChild != null) {
            search(root.rChild, c);
        }
    }


  
    String result="";
    boolean target = false;
   
    public String CodeToString(String codeStr) {

        int start = 0;
        int end = 1;

        while(end <= codeStr.length()){
            target = false;
            String s = codeStr.substring(start, end);
            matchCode(root, s);
            
            if(target){
                start = end;
            }
            end++;
        }

        return result;
    }

   
    private void matchCode(HNode root, String code){
        if (root.lChild == null && root.rChild == null) {
            if (code.equals(root.code)) {
                result += root.data;
                target = true; 
            }
        }
        if (root.lChild != null) {
            matchCode(root.lChild, code);
        }
        if (root.rChild != null) {
            matchCode(root.rChild, code);
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

    }
    public static void main(String[] args)throws IOException {

        Huffman huff = new Huffman();
        
        String data = fileReader("C:\\Document.txt");
        huff.creatHfmTree(data);

        huff.output(); 

        
        String hufmCode = huff.toHufmCode(data); 
       // System.out.println("Encode:" + hufmCode);

        
        //System.out.println("Decode：" + huff.CodeToString(hufmCode));
        
        int z = 0;
        byte[] bb=new byte[hufmCode.length()/7+1];
    for(int i=0;i<hufmCode.length()/7;i++){
        String a = hufmCode.substring(z,z+7);
        byte b=Byte.parseByte(a,2);

        bb[i]=b;
        z = z+7;
        if (z+7>=hufmCode.length()){
        bb[i+1]=Byte.parseByte(hufmCode.substring(z),2);
        break;
}
}
        OutputStream output = new FileOutputStream("C:\\Result.dat");
         output.write(bb); 
         output.close();
    }
}
