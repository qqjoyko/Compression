/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kang Yang
 */
public class HuffmanCode {
         private BinaryNode root;// root of huffman tree
      private int nodes;// number of total nodes in huffman tree
  
      private class BinaryNode implements Comparable<BinaryNode> {
          int frequency;// 出现的频率
          BinaryNode left;
          BinaryNode right;
          BinaryNode parent;
  
          public BinaryNode(int frequency, BinaryNode left, BinaryNode right,
                  BinaryNode parent) {
              this.frequency = frequency;
              this.left = left;
              this.right = right;
              this.parent = parent;
          }
  
          @Override
          public int compareTo(BinaryNode o) {
              return frequency - o.frequency;
          }
  
          public boolean isLeftChild() {
              return parent != null && parent.left == this;
          }
  
          public boolean isRightChild() {
              return parent != null && parent.right == this;
          }
    
    
}
}
