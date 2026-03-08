package com.oscar.app.hard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LargestRectangleInHistogramTest {
  @Test
  public void shouldEqualTen() {
    int[] heights = new int[] { 2, 1, 5, 6, 2, 3 };
    assertEquals(10, LargestRectangleInHistogram.largestRectangleArea(heights));

  }

  @Test
  public void shouldEqualFour() {
    int[] heights = new int[] { 2, 4 };
    assertEquals(4, LargestRectangleInHistogram.largestRectangleArea(heights));
  }

  @Test
  public void shouldEqualThree() {
    int[] heights = new int[] { 2, 1, 2 };
    assertEquals(3, LargestRectangleInHistogram.largestRectangleArea(heights));
  }

  @Test
  public void shouldEqualThirtyFour() {
    int[] heights = new int[] { 4, 3, 5, 5, 9, 2, 8, 4, 7, 2, 3, 8, 3, 5, 4, 7, 9 };
    assertEquals(34, LargestRectangleInHistogram.largestRectangleArea(heights));
  }
}
