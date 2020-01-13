package com.example.datastructure.stack;

/**
 * 栈 ---- 先进后出
 * <p>
 * 应用场景：
 * 1、单词逆序
 * 2、分隔符匹配
 */
public class Stack {

    private long[] arr;
    private int top;
    private int maxSize;

    public Stack(int max) {
        arr = new long[max];
        maxSize = max;
        top = -1;
    }

    public long pop() throws Exception {
        if (top == -1) {
            throw new Exception("已经到栈底了");
        }
        return arr[top--];
    }

    public void push(long value) throws Exception {
        if (top == maxSize) {
            throw new Exception("栈满了");
        }
        arr[++top] = value;
    }

    public long peek() throws Exception {
        if (top == -1) {
            throw new Exception("已经到栈底了");
        }
        return arr[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }
}
