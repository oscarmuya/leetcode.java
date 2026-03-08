package com.oscar.app.hard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestValidParenthesesTest {

  @Test
  public void shouldReturnTwo() {
    assertEquals(2, LongestValidParentheses.longestValidParentheses("(()"));
  }

  @Test
  public void shouldReturnFour() {
    assertEquals(4, LongestValidParentheses.longestValidParentheses(")()())"));
  }

  @Test
  public void shouldReturnZero() {
    assertEquals(0, LongestValidParentheses.longestValidParentheses(""));
  }

}
