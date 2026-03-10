package com.oscar.app.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CoinChangeTest {

  CoinChange solver = new CoinChange();

  @Test
  void example1() {
    assertEquals(3, solver.coinChange(new int[] { 1, 2, 5 }, 11));
  }

  @Test
  void example2() {
    assertEquals(-1, solver.coinChange(new int[] { 2 }, 3));
  }

  @Test
  void example3() {
    assertEquals(0, solver.coinChange(new int[] { 1 }, 0));
  }

  @Test
  void singleCoinExact() {
    assertEquals(1, solver.coinChange(new int[] { 7 }, 7));
  }

  @Test
  void multipleCoins() {
    assertEquals(2, solver.coinChange(new int[] { 1, 3, 4 }, 6));
  }

  @Test
  void impossibleCase() {
    assertEquals(-1, solver.coinChange(new int[] { 5, 10 }, 3));
  }

  @Test
  void largeAmount() {
    assertEquals(20, solver.coinChange(new int[] { 1, 2, 5 }, 100));
  }
}
