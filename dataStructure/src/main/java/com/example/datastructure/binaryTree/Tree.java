package com.example.datastructure.binaryTree;

/**
 * 二叉搜索树
 */
public class Tree {

    private Node root;

    public Node getRoot() {
        return root;
    }

    /**
     * 查找关键字值的节点
     * <p>
     * 时间复杂度O(log2N)
     *
     * @param key
     * @return
     */
    public Node find(int key) {
        Node current = root;
        while (current.iData != key) {
            if (current.iData < key) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    /**
     * 比父节点小的，插入到左子节点
     * 比父节点大的，插入到右子节点
     *
     * <p>
     * 插入新节点
     *
     * @param key
     * @param data
     */
    public void insert(int key, double data) {
        Node node = new Node(key, data);

        Node current = root;
        Node parent;//指向父节点

        if (root == null) {
            root = node;
            return;
        }

        while (true) {
            parent = current;
            if (current.iData > key) {//指向左子节点
                current = current.leftChild;
                if (current == null) {
                    parent.leftChild = node;
                    return;
                }
            } else {//指向右子节点
                current = current.rightChild;
                if (current == null) {
                    parent.rightChild = node;
                    return;
                }
            }
        }
    }

    /**
     * 递归的方法中序遍历整棵树
     *
     * @param localRoot
     */
    public void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.println(localRoot.iData);
            inOrder(localRoot.rightChild);
        }
    }

    /**
     * 返回最小值
     *
     * @return
     */
    public Node minimum() {
        Node current, last = null;
        current = root;
        while (current != null) {
            last = current;
            current = current.leftChild;
        }
        return last;
    }

    /**
     * 返回最大值
     *
     * @return
     */
    public Node maxmum() {
        Node current, last = null;
        current = root;
        while (current != null) {
            last = current;
            current = current.rightChild;
        }
        return last;
    }

    public boolean delete(int key) {
        Node current;
        Node parent = null;
        boolean isLeftChild = false;

        current = root;
        while (current.iData != key) {
            if (current.iData < key) {
                parent = current;//保存父节点
                current = current.leftChild;
                isLeftChild = true;
            } else {
                parent = current;//保存父节点
                current = current.rightChild;
                isLeftChild = false;
            }
            if (current == null) {
                return false;
            }
        }

        if (current.leftChild == null && current.rightChild == null) {
            //没有子节点
            if (current == root) {
                root = null;
                return true;
            }

            if (isLeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
        } else if (current.leftChild != null && current.rightChild != null) {
            //有两个子节点
            Node successor = getSuccessor(current);

            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.leftChild = successor;
            } else {
                parent.rightChild = successor;//3、把删除节点从它的父节点的rightChild字段删除，把这个字段设置为后继节点
            }
            successor.leftChild = current.leftChild;//4、把后继节点的左子节点设置删除节点的左子节点
        } else {
            //只有一个子节点
            if (current == root) {
                if (current.leftChild != null) {
                    root = current.leftChild;
                } else {
                    root = current.rightChild;
                }
            }

            if (current.leftChild != null) {
                if (isLeftChild) {
                    parent.leftChild = current.leftChild;
                } else {
                    parent.rightChild = current.leftChild;
                }
            } else {
                if (isLeftChild) {
                    parent.leftChild = current.rightChild;
                } else {
                    parent.rightChild = current.rightChild;
                }
            }

        }
        return true;
    }

    /**
     * 获取后继节点
     *
     * @param delNode
     * @return
     */
    public Node getSuccessor(Node delNode) {

        Node successorParent = delNode;
        Node current = delNode.rightChild;
        Node successor = delNode;

        while (current != null) {//找到后继节点
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }

        if (successor != delNode.rightChild) {
            successorParent.leftChild = successor.rightChild;//1、把后继父节点的leftChild字段置为successor的右子节点
            successor.rightChild = delNode.rightChild;//2、把后继节点的右子节点设置为删除节点的右子节点
        }

        return null;
    }

}
