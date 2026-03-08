package com.oscar.app.hard;

import java.util.Stack;

/**
 * The trick here is to store use a stack that keeps
 * track of invalid parentheses and whenever we find valid
 * once we pop them so we will remain with only boundaries of
 * invalid parentheses.
 * (()
 * )()())
 */
public class LongestValidParentheses {
  public static int longestValidParentheses(String s) {
    int res = 0;
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < s.length(); i++) {
      if (!stack.isEmpty()
          && s.charAt(stack.peek()) == '('
          && s.charAt(i) == ')') {

        stack.pop();
        int left = stack.isEmpty() ? -1 : stack.peek();
        res = Integer.max(res, i - left);

      } else {
        stack.push(i);
      }

    }

    return res;
  }

}
