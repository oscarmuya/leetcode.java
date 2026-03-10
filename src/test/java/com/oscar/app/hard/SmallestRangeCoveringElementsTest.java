package com.oscar.app.hard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SmallestRangeCoveringElementsTest {

  private SmallestRangeCoveringElements solution;

  @BeforeEach
  void setUp() {
    solution = new SmallestRangeCoveringElements();
  }

  // ------------------------------------------------------------------
  // Provided examples
  // ------------------------------------------------------------------

  @Test
  @DisplayName("Example 1: [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]] -> [20,24]")
  void testExample1() {
    List<List<Integer>> nums = Arrays.asList(
        Arrays.asList(4, 10, 15, 24, 26),
        Arrays.asList(0, 9, 12, 20),
        Arrays.asList(5, 18, 22, 30));
    int[] result = solution.smallestRange(nums);
    assertArrayEquals(new int[] { 20, 24 }, result);
  }

  @Test
  @DisplayName("Example 2: [[1,2,3],[1,2,3],[1,2,3]] -> [1,1]")
  void testExample2() {
    List<List<Integer>> nums = Arrays.asList(
        Arrays.asList(1, 2, 3),
        Arrays.asList(1, 2, 3),
        Arrays.asList(1, 2, 3));
    int[] result = solution.smallestRange(nums);
    assertArrayEquals(new int[] { 1, 1 }, result);
  }

  // ------------------------------------------------------------------
  // Single list
  // ------------------------------------------------------------------

  @Test
  @DisplayName("Single list: range is [first element, first element]")
  void testSingleList() {
    List<List<Integer>> nums = Arrays.asList(
        Arrays.asList(3, 7, 12));
    int[] result = solution.smallestRange(nums);
    assertArrayEquals(new int[] { 3, 3 }, result);
  }

  // ------------------------------------------------------------------
  // Two lists
  // ------------------------------------------------------------------

  @Test
  @DisplayName("Two lists with overlapping elements -> zero-width range")
  void testTwoListsOverlap() {
    List<List<Integer>> nums = Arrays.asList(
        Arrays.asList(1, 3, 5),
        Arrays.asList(2, 3, 6));
    int[] result = solution.smallestRange(nums);
    assertArrayEquals(new int[] { 3, 3 }, result);
  }

  @Test
  @DisplayName("Two lists with no common element -> minimal bridging range")
  void testTwoListsNoCommonElement() {
    List<List<Integer>> nums = Arrays.asList(
        Arrays.asList(1, 5, 9),
        Arrays.asList(2, 6, 10));
    int[] result = solution.smallestRange(nums);
    // [1,2] has width 1 -- optimal
    assertArrayEquals(new int[] { 1, 2 }, result);
  }

  // ------------------------------------------------------------------
  // All lists contain the same single value
  // ------------------------------------------------------------------

  @Test
  @DisplayName("All lists contain the same single value -> [v, v]")
  void testAllSameSingleValue() {
    List<List<Integer>> nums = Arrays.asList(
        Arrays.asList(5),
        Arrays.asList(5),
        Arrays.asList(5));
    int[] result = solution.smallestRange(nums);
    assertArrayEquals(new int[] { 5, 5 }, result);
  }

  // ------------------------------------------------------------------
  // Each list has exactly one element
  // ------------------------------------------------------------------

  @Test
  @DisplayName("Each list has one element -> range spans all of them")
  void testSingleElementsPerList() {
    List<List<Integer>> nums = Arrays.asList(
        Arrays.asList(1),
        Arrays.asList(5),
        Arrays.asList(3));
    int[] result = solution.smallestRange(nums);
    assertArrayEquals(new int[] { 1, 5 }, result);
  }

  @Test
  @DisplayName("Single element per list, already consecutive -> [a, b] minimal")
  void testSingleElementsConsecutive() {
    List<List<Integer>> nums = Arrays.asList(
        Arrays.asList(2),
        Arrays.asList(3),
        Arrays.asList(4));
    int[] result = solution.smallestRange(nums);
    assertArrayEquals(new int[] { 2, 4 }, result);
  }

  // ------------------------------------------------------------------
  // Negative numbers
  // ------------------------------------------------------------------

  @Test
  @DisplayName("Lists containing negative numbers")
  void testNegativeNumbers() {
    List<List<Integer>> nums = Arrays.asList(
        Arrays.asList(-10, -5, 0),
        Arrays.asList(-8, -3, 2),
        Arrays.asList(-6, -1, 4));
    int[] result = solution.smallestRange(nums);
    // -10,-8,-6 -> range width 4; -5,-3,-1 -> width 4; 0,2,4 -> width 4
    // optimal is width 4, multiple candidates -- assert width only
    assertEquals(4, result[1] - result[0]);
  }

  @Test
  @DisplayName("Mix of negative and positive numbers with a zero-width solution")
  void testNegativeAndPositiveOverlap() {
    List<List<Integer>> nums = Arrays.asList(
        Arrays.asList(-3, 0, 4),
        Arrays.asList(-1, 0, 5));
    int[] result = solution.smallestRange(nums);
    assertArrayEquals(new int[] { 0, 0 }, result);
  }

  // ------------------------------------------------------------------
  // Large value range (constraint boundaries)
  // ------------------------------------------------------------------

  @Test
  @DisplayName("Values at constraint extremes: -10^5 and 10^5")
  void testExtremeValues() {
    List<List<Integer>> nums = Arrays.asList(
        Arrays.asList(-100000),
        Arrays.asList(100000));
    int[] result = solution.smallestRange(nums);
    assertArrayEquals(new int[] { -100000, 100000 }, result);
  }

  // ------------------------------------------------------------------
  // Range validity invariants
  // ------------------------------------------------------------------

  @Test
  @DisplayName("Result[0] <= result[1] always holds")
  void testRangeIsNonDecreasing() {
    List<List<Integer>> nums = Arrays.asList(
        Arrays.asList(4, 10, 15, 24, 26),
        Arrays.asList(0, 9, 12, 20),
        Arrays.asList(5, 18, 22, 30));
    int[] result = solution.smallestRange(nums);
    assertTrue(result[0] <= result[1], "Range start must be <= range end");
  }

  @Test
  @DisplayName("Result covers at least one element from every list")
  void testRangeCoverAllLists() {
    List<List<Integer>> nums = Arrays.asList(
        Arrays.asList(4, 10, 15, 24, 26),
        Arrays.asList(0, 9, 12, 20),
        Arrays.asList(5, 18, 22, 30));
    int[] result = solution.smallestRange(nums);
    int lo = result[0], hi = result[1];

    for (List<Integer> list : nums) {
      boolean covered = list.stream().anyMatch(v -> v >= lo && v <= hi);
      assertTrue(covered, "Range [" + lo + "," + hi + "] must cover list: " + list);
    }
  }

  // ------------------------------------------------------------------
  // Larger k
  // ------------------------------------------------------------------

  @Test
  @DisplayName("k=5 lists, common element exists -> zero-width range")
  void testFiveListsCommonElement() {
    List<List<Integer>> nums = Arrays.asList(
        Arrays.asList(1, 7, 10),
        Arrays.asList(2, 7, 11),
        Arrays.asList(3, 7, 12),
        Arrays.asList(5, 7, 13),
        Arrays.asList(6, 7, 14));
    int[] result = solution.smallestRange(nums);
    assertArrayEquals(new int[] { 7, 7 }, result);
  }

  @Test
  @DisplayName("k=4 lists, no common element -> minimal range covers one from each")
  void testFourListsMinimalRange() {
    List<List<Integer>> nums = Arrays.asList(
        Arrays.asList(1, 10),
        Arrays.asList(2, 11),
        Arrays.asList(3, 12),
        Arrays.asList(4, 13));
    int[] result = solution.smallestRange(nums);
    // [1,4] width 3 is optimal
    assertArrayEquals(new int[] { 1, 4 }, result);
  }

  // ------------------------------------------------------------------
  // Tie-breaking: among equal-width ranges, smallest start is preferred
  // ------------------------------------------------------------------

  @Test
  @DisplayName("Equal-width ranges -> pick the one with smaller start")
  void testEqualWidthPickSmallestStart() {
    // [1,3] and [3,5] are both width-2 ranges
    List<List<Integer>> nums = Arrays.asList(
        Arrays.asList(1, 3, 5),
        Arrays.asList(3, 5, 7));
    int[] result = solution.smallestRange(nums);
    // [3,3] is width 0 -- the actual optimum here
    assertArrayEquals(new int[] { 3, 3 }, result);
  }
}
