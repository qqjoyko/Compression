package compre;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

    }
    public static void main(String[] args) {

        Huffman huff = new Huffman();

        String data = "aaaaaabcdesufsasgdebbhchdbabhdcxsdchd";
        huff.creatHfmTree(data);

        huff.output(); 

        
        String hufmCode = huff.toHufmCode(data); 
        System.out.println("Encode:" + hufmCode);

        
        System.out.println("Decode：" + huff.CodeToString(hufmCode));            
    }
}
