package com.oscar.app.hard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SlidingWindowMedianTest {

  @Test
  void example1() {
    SlidingWindowMedian swm = new SlidingWindowMedian();

    int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
    int k = 3;

    double[] expected = { 1.0, -1.0, -1.0, 3.0, 5.0, 6.0 };

    assertArrayEquals(expected, swm.medianSlidingWindow(nums, k));
  }

  @Test
  void singleWindow() {
    SlidingWindowMedian swm = new SlidingWindowMedian();

    int[] nums = { 5 };
    int k = 1;

    double[] expected = { 5.0 };

    assertArrayEquals(expected, swm.medianSlidingWindow(nums, k));
  }

  @Test
  void evenWindow() {
    SlidingWindowMedian swm = new SlidingWindowMedian();

    int[] nums = { 1, 2, 3, 4 };
    int k = 2;

    double[] expected = { 1.5, 2.5, 3.5 };

    assertArrayEquals(expected, swm.medianSlidingWindow(nums, k));
  }

  @Test
  void negatives() {
    SlidingWindowMedian swm = new SlidingWindowMedian();

    int[] nums = { -1, -2, -3, -4 };
    int k = 2;

    double[] expected = { -1.5, -2.5, -3.5 };

    assertArrayEquals(expected, swm.medianSlidingWindow(nums, k));
  }

  @Test
  void duplicates() {
    SlidingWindowMedian swm = new SlidingWindowMedian();

    int[] nums = { 2, 2, 2, 2 };
    int k = 3;

    double[] expected = { 2.0, 2.0 };

    assertArrayEquals(expected, swm.medianSlidingWindow(nums, k));
  }

  @Test
  void largeValues() {
    SlidingWindowMedian swm = new SlidingWindowMedian();

    int[] nums = { 2147483647, 2147483647 };
    int k = 2;
    double[] expected = { 2147483647.0 };

    assertArrayEquals(expected, swm.medianSlidingWindow(nums, k));
  }
}
