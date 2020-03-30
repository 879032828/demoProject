package com.example.datastructure.tree234;

public class Node {

    private static final int ORDER = 4;
    private int numItems;
    private Node parent;
    private Node childArray[] = new Node[ORDER];
    private DataItem itemArray[] = new DataItem[ORDER - 1];

    /**
     * 连接子节点
     *
     * @param childNum
     * @param child
     */
    public void connectChild(int childNum, Node child) {
        childArray[childNum] = child;
        if (child != null) {
            child.parent = this;
        }
    }

    /**
     * 断开子节点并返回该子节点
     *
     * @param childNum
     * @return
     */
    public Node disconnectChild(int childNum) {
        Node tempNode = childArray[childNum];
        childArray[childNum] = null;
        return tempNode;
    }

    /**
     * 获取对应位置的子节点
     *
     * @param childNum
     * @return
     */
    public Node getChild(int childNum) {
        return childArray[childNum];
    }

    /**
     * 获取父节点
     *
     * @return
     */
    public Node getParent() {
        return parent;
    }

    /**
     * 判断当前节点是否为叶节点
     *
     * @return
     */
    public boolean isLeaf() {
        return (childArray[0] == null);
    }

    /**
     * 获取当前数据项的个数
     *
     * @return
     */
    public int getNumItems() {
        return numItems;
    }

    /**
     * 获取数据项
     *
     * @param index
     * @return
     */
    public DataItem getItem(int index) {
        return itemArray[index];
    }

    /**
     * 判断数据项是否满了
     *
     * @return
     */
    public boolean isFull() {
        return (numItems == ORDER - 1);
    }

    /**
     * 查找对应的item
     *
     * @param key
     * @return
     */
    public int findItem(long key) {
        for (int j = 0; j < ORDER - 1; j++) {
            if (itemArray[j] == null) {
                break;
            } else if (itemArray[j].dData == key) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 插入数据项
     *
     * @param newItem
     * @return
     */
    public int insertItem(DataItem newItem) {
        numItems++;
        long newkey = newItem.dData;

        for (int j = ORDER - 2; j >= 0; j--) {
            if (itemArray[j] == null) {
                continue;
            } else {
                long itsKey = itemArray[j].dData;
                if (newkey > itsKey) {
                    itemArray[j + 1] = newItem;
                    return j + 1;
                } else {
                    itemArray[j + 1] = itemArray[j];
                }
            }
        }

        itemArray[0] = newItem;
        return 0;
    }

    /**
     * 删除数据项
     *
     * @return
     */
    public DataItem removeItem() {
        DataItem temp = itemArray[numItems - 1];
        itemArray[numItems - 1] = null;
        numItems--;
        return temp;
    }

    public void displayNode(){
        for (int i = 0; i < numItems; i++){
            itemArray[i].displayItem();
        }
    }

}









