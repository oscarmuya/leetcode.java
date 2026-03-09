package com.oscar.app.hard;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {
  public static int[] maxSlidingWindow(int[] nums, int k) {
    int[] res = new int[nums.length - k + 1];
    Deque<Integer> deque = new ArrayDeque<>();

    for (int i = 0; i < nums.length; i++) {
      // Poll first if outside the window
      if (!deque.isEmpty() && deque.peek() < i - k + 1) {
        deque.poll();
      }

      // Poll last index if smaller than the ith
      while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
        deque.pollLast();
      }

      deque.add(i);

      // Add to result
      if (!deque.isEmpty() && i >= k - 1) {
        res[i - k + 1] = nums[deque.peek()];
      }
    }

    return res;
  }
}
