package com.example.datastructure.tree234;

public class Tree234 {

    private Node root = new Node();

    public int find(long key) {
        Node curNode = root;
        int childNumber;
        while (true) {
            if ((childNumber = curNode.findItem(key)) != -1) {
                return childNumber;
            } else if (curNode.isLeaf()) {
                return -1;
            } else {
                curNode = getNextChild(curNode, key);
            }
        }
    }

    public Node getNextChild(Node theNode, long theValue) {
        int j;
        int numItems = theNode.getNumItems();
        for (j = 0; j < numItems; j++) {
            if (theValue < theNode.getItem(j).dData) {
                return theNode.getChild(j);
            }
        }
        return theNode.getChild(j);
    }

    public void insert(long key) {
        Node curNode = root;
        DataItem tempItem = new DataItem(key);
        while (true) {
            if (curNode.isFull()) {

            } else if (curNode.isLeaf()) {
                break;
            } else {
                curNode = getNextChild(curNode, key);
            }
        }
        curNode.insertItem(tempItem);
    }

    public void split(Node thisNode) {
        DataItem itemB, itemC;
        Node parent, child2, child3;
        int itemIndex;

        itemC = thisNode.removeItem();
        itemB = thisNode.removeItem();

        child2 = thisNode.disconnectChild(2);
        child3 = thisNode.disconnectChild(3);

        Node newRight = new Node();

        if (thisNode == root) {
            root = new Node();
            parent = root;
            root.connectChild(0, thisNode);
        } else {
            parent = thisNode.getParent();
            itemIndex = parent.insertItem(itemB);
            int n = parent.getNumItems();
            for (int j = n - 1; j > itemIndex; j--) {
                Node temp = parent.disconnectChild(j);
                parent.connectChild(j + 1, temp);
            }
        }
    }

}
