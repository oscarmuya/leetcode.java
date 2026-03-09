package com.oscar.app.hard;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MedianFinder {
  private PriorityQueue<Integer> lowerHalf;
  private PriorityQueue<Integer> upperHalf;

  public MedianFinder() {
    lowerHalf = new PriorityQueue<>(Collections.reverseOrder());
    upperHalf = new PriorityQueue<>();
  }

  public void addNum(int num) {
    lowerHalf.add(num);
    upperHalf.add(lowerHalf.poll());
    if (upperHalf.size() > lowerHalf.size()) {
      lowerHalf.add(upperHalf.poll());
    }
  }

  public double findMedian() {
    if (lowerHalf.size() > upperHalf.size())
      return lowerHalf.peek();
    else
      return (lowerHalf.peek() + upperHalf.peek()) / 2.0;
  }
}

class MedianFinderMultiSet {
  private TreeMap<Integer, Integer> lowerHalf;
  private TreeMap<Integer, Integer> upperHalf;
  private int lowerSize = 0, upperSize = 0;

  public MedianFinderMultiSet() {
    lowerHalf = new TreeMap<>(Collections.reverseOrder());
    upperHalf = new TreeMap<>();
  }

  public int removeFirst(TreeMap<Integer, Integer> multiset) {
    int key = multiset.firstKey();
    int cnt = multiset.get(key) - 1;
    if (cnt == 0)
      multiset.remove(key);
    else
      multiset.put(key, cnt);
    return key;
  }

  public void addNum(int num) {
    lowerHalf.put(num, lowerHalf.getOrDefault(num, 0) + 1);
    int max = removeFirst(lowerHalf);
    upperHalf.put(max, upperHalf.getOrDefault(max, 0) + 1);
    upperSize++;

    if (upperSize > lowerSize) {
      int min = removeFirst(upperHalf);
      lowerHalf.put(min, lowerHalf.getOrDefault(min, 0) + 1);
      upperSize--;
      lowerSize++;
    }
  }

  public double findMedian() {
    if (lowerSize > upperSize)
      return lowerHalf.firstKey();
    return (lowerHalf.firstKey() + upperHalf.firstKey()) / 2.0;
  }
}
