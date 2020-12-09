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
          int frequency;
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
          if (roots.size() == 1)
              return roots.remove(0);
          PriorityQueue<BinaryNode> pq = new PriorityQueue<BinaryNode>(roots);
          while (pq.size() != 1) {
              BinaryNode left = pq.remove();
              BinaryNode right = pq.remove();
              BinaryNode parent = new BinaryNode(
                      left.frequency + right.frequency, left, right, null);
              left.parent = parent;
              right.parent = parent;
              pq.add(parent);
          }
          return (root = pq.remove());
      }
          public List<BinaryNode> make_set(Integer[] frequency) {
          List<BinaryNode> nodeList = new ArrayList<HuffmanCode.BinaryNode>(
                  frequency.length);
          for (Integer i : frequency) {
              nodeList.add(new BinaryNode(i, null, null, null));
          }
          nodes = frequency.length << 1 - 1;
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
          public String huffmanEncoding(List<BinaryNode> nodeList) {
         StringBuilder sb = new StringBuilder();
         BinaryNode currentNode;
         for (BinaryNode binaryNode : nodeList) {
             currentNode = binaryNode;
             while (currentNode != root) {
                 if (currentNode.isLeftChild())
                     sb.append("0");
                 else if (currentNode.isRightChild())
                     sb.append("1");
                 currentNode = currentNode.parent;
             }
         }
         return sb.toString();
     }
 
     public Map<BinaryNode, String> huffmanDecoding(String encodeString) {
         BinaryNode currentNode = root;
        
         Map<BinaryNode, String> node_Code = new HashMap<HuffmanCode.BinaryNode, String>();
         StringBuilder sb = new StringBuilder();
         for (int i = 0; i < encodeString.length(); i++) {
             
             char codeChar = encodeString.charAt(i);
             sb.append(codeChar);
             if (codeChar == '0')
                 currentNode = currentNode.left;
             else
                 currentNode = currentNode.right;
             if (currentNode.left == null && currentNode.right == null)
             {
                 node_Code.put(currentNode, sb.toString());
                 sb.delete(0, sb.length());
                 currentNode = root;
             }
         }
         return node_Code;
     }

     // for test purpose
     public static void main(String[] args) {
         Integer[] frequency = { 10, 15, 12, 3, 4, 13, 1 };
         HuffmanCode hc = new HuffmanCode();
         List<BinaryNode> nodeList = hc.make_set(frequency);
         hc.buildHuffmanTree(nodeList);
         int totalCost = hc.huffman_cost(nodeList);
         System.out.println(totalCost);
         String encodeStr = hc.huffmanEncoding(nodeList);
         System.out.println("String after encode: " + encodeStr);
         
         
         Map<BinaryNode, String> decodeMap = hc.huffmanDecoding(encodeStr);
         Set<Map.Entry<BinaryNode, String>> entrys = decodeMap.entrySet();
         for (Map.Entry<BinaryNode, String> entry : entrys) {
             BinaryNode node = entry.getKey();
             String code = entry.getValue();
             System.out.println("Node's frequency=" + node.frequency + " : " + code);
         }
     }
}
