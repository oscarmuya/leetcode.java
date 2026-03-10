package com.oscar.app.hard;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class SmallestRangeCoveringElements {

  public int[] smallestRange(List<List<Integer>> nums) {
    boolean allFull = true;
    int[] res = new int[2];

    while (allFull) {
      PriorityQueue<Integer> upper = new PriorityQueue<>();
      PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());
      int min = nums.get(0).get(0);
      int minIdx = 0;

      for (int i = 0; i < nums.size(); i++) {
        if (nums.get(i).size() == 0) {
          allFull = false;
          break;
        } else if (nums.get(i).get(0) < min) {
          min = nums.get(i).get(0);
          minIdx = i;
          lower.add(min);
          upper.add(min);
        }
      }

      if (!upper.isEmpty())
        res[0] = upper.peek();
      if (!lower.isEmpty())
        res[1] = lower.peek();
      if (allFull)
        nums.get(minIdx).remove(0);

    }
    return res;
  }
}
