package com.danhuang.study.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class JavaCode {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.push(2);
        solution.push(3);
        solution.push(4);
        System.out.println(solution.pop());
    }

}
class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {

        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        stack1.push(node);
        while(!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }

    public int pop() {
        return stack2.pop();
    }
}