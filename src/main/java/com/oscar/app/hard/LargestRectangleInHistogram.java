package com.oscar.app.hard;

import java.util.Stack;

/*
 * We loop through the entire array and store indexes of 
 * elements in the stack in increasing order until we find
 * one which is less than the previous.
 *
 * Then we stop and start popping from the stack and calculating
 * the area they span using this formula.
 *
 * Say we stopped and index i (we did not add this index since it is not increasing)
 * we pop the top index 
 *      current = stack.pop()
 * it will be spanning from its index - 1 to our stopped index i - 1. So 
 * the area will be heights[current] * (i - 1 - stack.peek()). 
 * If the stack is empty then we know that the current index spans from -1
 *
 * We  continue till the stack is empty.
 *
 * 
 * Then we have a case where we reach the end of the heights array without getting 
 * a value that is less than the previous.
 *
 * To solve that we need to loop until the the lenght of the heights array then when that happens
 * we again trigger the stack popping and aggretation.
 * */
public class LargestRectangleInHistogram {

  public static int largestRectangleArea(int[] heights) {
    int maxArea = 0;
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i <= heights.length; i++) {
      while (!stack.isEmpty() && (i == heights.length || heights[stack.peek()] > heights[i])) {
        int current = stack.pop();
        int left = stack.isEmpty() ? -1 : stack.peek();
        maxArea = Integer.max(maxArea, heights[current] * (i - 1 - left));
      }

      stack.push(i);

    }

    return maxArea;
  }

}
