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
         /**
       * 
       * @param roots
       *            initial root of each tree
       * @return root of huffman tree
       */
      public BinaryNode buildHuffmanTree(List<BinaryNode> roots) {
          if (roots.size() == 1)// 只有一个结点
              return roots.remove(0);
          PriorityQueue<BinaryNode> pq = new PriorityQueue<BinaryNode>(roots);//优先级队列保存所有叶子结点
          while (pq.size() != 1) {
              BinaryNode left = pq.remove();//频率最小的先出队列
              BinaryNode right = pq.remove();
              BinaryNode parent = new BinaryNode(
                      left.frequency + right.frequency, left, right, null);//构造父结点
              left.parent = parent;
              right.parent = parent;
              pq.add(parent);//新构造好的根结点插入到优先级队列中
          }
          return (root = pq.remove());
      }
          public List<BinaryNode> make_set(Integer[] frequency) {
          List<BinaryNode> nodeList = new ArrayList<HuffmanCode.BinaryNode>(
                  frequency.length);
          for (Integer i : frequency) {
              nodeList.add(new BinaryNode(i, null, null, null));
          }
          nodes = frequency.length << 1 - 1;// huffman 树中结点个数等于叶子结点个数乘以2减去1
          return nodeList;
      }
         public int huffman_cost(List<BinaryNode> nodeList) {
          int cost = 0;
          int level;
          BinaryNode currentNode;
          for (BinaryNode binaryNode : nodeList) {
              level = 0;
              currentNode = binaryNode;
              while (currentNode != root) {
                  currentNode = currentNode.parent;
                  level++;
              }
              cost += level * binaryNode.frequency;
          }
         return cost;
   }        
}
