package com.oscar.app.hard;

import java.util.Collections;
import java.util.TreeMap;

public class SlidingWindowMedian {
  private TreeMap<Integer, Integer> lower = new TreeMap<>(Collections.reverseOrder());
  private TreeMap<Integer, Integer> upper = new TreeMap<>();
  private int lowerSize = 0, upperSize = 0;

  private int removeFirst(TreeMap<Integer, Integer> multiset) {
    int key = multiset.firstKey();
    int cnt = multiset.get(key) - 1;
    if (cnt == 0)
      multiset.remove(key);
    else
      multiset.put(key, cnt);
    return key;
  }

  public double[] medianSlidingWindow(int[] nums, int k) {
    double[] res = new double[nums.length - k + 1];

    for (int i = 0; i < nums.length; i++) {
      lower.put(nums[i], lower.getOrDefault(nums[i], 0) + 1);
      int max = removeFirst(lower);
      upper.put(max, upper.getOrDefault(max, 0) + 1);
      upperSize++;

      if (upperSize > lowerSize) {
        int min = removeFirst(upper);
        lower.put(min, lower.getOrDefault(min, 0) + 1);
        lowerSize++;
        upperSize--;
      }

      // push to res
      if (i >= k - 1) {
        double median = lowerSize > upperSize
            ? (double) lower.firstKey()
            : ((long) lower.firstKey() + upper.firstKey()) / 2.0;
        res[i - k + 1] = median;
        // remove item outside the window
        int item = nums[i - k + 1];
        if (upper.containsKey(item)) {
          int cnt = upper.get(item) - 1;
          if (cnt == 0)
            upper.remove(item);
          else
            upper.put(item, cnt);
          upperSize--;

        } else {
          int cnt = lower.get(item) - 1;
          if (cnt == 0)
            lower.remove(item);
          else
            lower.put(item, cnt);
          lowerSize--;
        }
      }
    }

    return res;
  }
}
