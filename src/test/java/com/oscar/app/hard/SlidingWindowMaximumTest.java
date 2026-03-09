package com.oscar.app.hard;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SlidingWindowMaximumTest {

  @Test
  public void shouldPassOne() {
    int[] nums = new int[] { 1, 3, -1, -3, 5, 3, 6, 7 };
    int k = 3;
    int[] res = new int[] { 3, 3, 5, 5, 6, 7 };

    assertArrayEquals(res, SlidingWindowMaximum.maxSlidingWindow(nums, k));
  }

  @Test
  public void shouldPassTwo() {
    int[] nums = new int[] { 1 };
    int k = 1;
    int[] res = new int[] { 1 };

    assertArrayEquals(res, SlidingWindowMaximum.maxSlidingWindow(nums, k));
  }
}
