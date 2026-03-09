package com.oscar.app.hard;

import java.util.Collections;
import java.util.PriorityQueue;

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
