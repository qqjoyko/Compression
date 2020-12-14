/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kang Yang
 */
public class HNode {

    public String code = "";
    public String data = "";
    public int count;
    public HNode lChild;
    public HNode rChild;

    public HNode() {
    }

    public HNode(String data, int count) {
        this.data = data;
        this.count = count;
    }

    public HNode(int count, HNode lChild, HNode rChild) {
        this.count = count;
        this.lChild = lChild;
        this.rChild = rChild;
    }

    public HNode(String data, int count, HNode lChild, HNode rChild) {
        this.data = data;
        this.count = count;
        this.lChild = lChild;
        this.rChild = rChild;
    }

}
