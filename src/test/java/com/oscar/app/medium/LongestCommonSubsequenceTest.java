package com.oscar.app.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class LongestCommonSubsequenceTest {

  LongestCommonSubsequence solver = new LongestCommonSubsequence();

  @Test
  void example1() {
    assertEquals(3, solver.longestCommonSubsequence("abcde", "ace"));
  }

  @Test
  void example2() {
    assertEquals(3, solver.longestCommonSubsequence("abc", "abc"));
  }

  @Test
  void example3() {
    assertEquals(0, solver.longestCommonSubsequence("abc", "def"));
  }

  @Test
  void emptyStrings() {
    assertEquals(0, solver.longestCommonSubsequence("", ""));
  }

  @Test
  void oneEmpty() {
    assertEquals(0, solver.longestCommonSubsequence("abc", ""));
  }

  @Test
  void repeatedChars() {
    assertEquals(2, solver.longestCommonSubsequence("aab", "azb"));
  }

  @Test
  void longCase() {
    assertEquals(4, solver.longestCommonSubsequence("AGGTAB", "GXTXAYB"));
  }
}
