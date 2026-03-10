# LeetCode Solutions

A Java implementation of various LeetCode problems, organized by difficulty.

## Project Structure

```
src/
├── main/java/com/oscar/app/
│   ├── easy/           # Easy difficulty problems
│   ├── medium/         # Medium difficulty problems
│   └── hard/           # Hard difficulty problems
└── test/java/          # JUnit 5 test cases
```

## Solutions Overview

| # | Problem | Difficulty | LeetCode # | Solution File |
|---|---------|------------|------------|---------------|
| 1 | Design Twitter | Hard | 355 | `hard/Twitter.java` |
| 2 | Sliding Window Maximum | Hard | 239 | `hard/SlidingWindowMaximum.java` |
| 3 | Find Median from Data Stream | Hard | 295 | `hard/MedianFinder.java` |
| 4 | Longest Valid Parentheses | Hard | 32 | `hard/LongestValidParentheses.java` |
| 5 | Largest Rectangle in Histogram | Hard | 84 | `hard/LargestRectangleInHistogram.java` |
| 6 | Smallest Range Covering Elements from K Lists | Hard | 632 | `hard/SmallestRangeCoveringElements.java` |
| 7 | Sliding Window Median | Hard | 480 | `hard/SlidingWindowMedian.java` |
| 8 | Longest Common Subsequence | Medium | 1143 | `medium/LongestCommonSubsequence.java` |
| 9 | Coin Change | Medium | 322 | `medium/CoinChange.java` |

## Problem Details

### Medium

| Problem | Description | Key Concepts |
|---------|-------------|--------------|
| **Longest Common Subsequence** | Find the length of the longest common subsequence between two strings | Dynamic Programming |
| **Coin Change** | Find the minimum number of coins needed to make up a given amount | Dynamic Programming, BFS |

### Hard

| Problem | Description | Key Concepts |
|---------|-------------|--------------|
| **Design Twitter** | Design a simplified Twitter feed with post, follow, unfollow, and news feed features | Heap, HashMap, HashSet |
| **Sliding Window Maximum** | Find the maximum element in each sliding window of size k | Deque, Monotonic Queue |
| **Find Median from Data Stream** | Median finder that supports adding numbers and finding median in O(log n) | Two Heaps, Balanced BST |
| **Longest Valid Parentheses** | Find the length of the longest valid (well-formed) parentheses substring | Stack, Dynamic Programming |
| **Largest Rectangle in Histogram** | Find the largest rectangular area in a histogram | Stack, Monotonic Stack |
| **Smallest Range Covering Elements** | Find the smallest range that contains at least one element from each of k lists | Heap, Sliding Window |
| **Sliding Window Median** | Find the median of each sliding window of size k | TreeMap, Balanced BST |

## Progress

- **Easy**: 0 problems
- **Medium**: 2 problems  
- **Hard**: 7 problems
- **Total**: 9 problems

## Adding New Solutions

1. Create the solution class in the appropriate difficulty folder
2. Add corresponding test class in `src/test/java/`
3. Run tests to verify correctness


## Technologies

- **Language**: Java 17
- **Build Tool**: Maven
- **Testing**: JUnit 5

## Build & Run

```bash
# Compile the project
mvn compile

# Run tests
mvn test

# Run a specific test class
mvn test -Dtest=TwitterTest

# Package the project
mvn package
```

