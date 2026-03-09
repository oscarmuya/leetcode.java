package com.oscar.app.hard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MedianFinderTest {

  @Test
  void example1() {
    MedianFinder medianFinder = new MedianFinder();

    medianFinder.addNum(1);
    medianFinder.addNum(2);
    assertEquals(1.5, medianFinder.findMedian());

    medianFinder.addNum(3);
    assertEquals(2.0, medianFinder.findMedian());
  }

  @Test
  void singleElement() {
    MedianFinder medianFinder = new MedianFinder();

    medianFinder.addNum(10);
    assertEquals(10.0, medianFinder.findMedian());
  }

  @Test
  void evenCount() {
    MedianFinder medianFinder = new MedianFinder();

    medianFinder.addNum(5);
    medianFinder.addNum(15);
    medianFinder.addNum(1);
    medianFinder.addNum(3);

    assertEquals(4.0, medianFinder.findMedian());
  }

  @Test
  void oddCount() {
    MedianFinder medianFinder = new MedianFinder();

    medianFinder.addNum(2);
    medianFinder.addNum(3);
    medianFinder.addNum(4);

    assertEquals(3.0, medianFinder.findMedian());
  }

  @Test
  void negatives() {
    MedianFinder medianFinder = new MedianFinder();

    medianFinder.addNum(-1);
    medianFinder.addNum(-2);
    medianFinder.addNum(-3);

    assertEquals(-2.0, medianFinder.findMedian());
  }
}
