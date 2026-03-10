package com.oscar.app.hard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TwitterTest {

  private Twitter twitter;

  @BeforeEach
  void setUp() {
    twitter = new Twitter();
  }

  @Test
  @DisplayName("LeetCode example: post, follow, getNewsFeed, unfollow")
  void leetcodeExample() {
    twitter.postTweet(1, 5);
    assertEquals(List.of(5), twitter.getNewsFeed(1));

    twitter.follow(1, 2);
    twitter.postTweet(2, 6);
    assertEquals(List.of(6, 5), twitter.getNewsFeed(1));

    twitter.unfollow(1, 2);
    assertEquals(List.of(5), twitter.getNewsFeed(1));
  }

  // -------------------------------------------------------------------------
  // getNewsFeed
  // -------------------------------------------------------------------------

  @Test
  @DisplayName("getNewsFeed returns empty list for user with no tweets and no followees")
  void newsFeedEmptyForNewUser() {
    assertTrue(twitter.getNewsFeed(99).isEmpty());
  }

  @Test
  @DisplayName("getNewsFeed returns own tweets when user follows nobody")
  void newsFeedOwnTweetsOnly() {
    twitter.postTweet(1, 10);
    twitter.postTweet(1, 20);
    assertEquals(List.of(20, 10), twitter.getNewsFeed(1));
  }

  @Test
  @DisplayName("getNewsFeed returns at most 10 tweets")
  void newsFeedCappedAtTen() {
    for (int i = 1; i <= 15; i++) {
      twitter.postTweet(1, i);
    }
    List<Integer> feed = twitter.getNewsFeed(1);
    assertEquals(10, feed.size());
    // Most recent 10: tweetIds 15 down to 6
    assertEquals(List.of(15, 14, 13, 12, 11, 10, 9, 8, 7, 6), feed);
  }

  @Test
  @DisplayName("getNewsFeed merges own and followee tweets in chronological order")
  void newsFeedMergesChronologically() {
    twitter.postTweet(1, 1);
    twitter.postTweet(2, 2);
    twitter.postTweet(1, 3);
    twitter.postTweet(2, 4);

    twitter.follow(1, 2);

    // Order: 4, 3, 2, 1 (most recent first)
    assertEquals(List.of(4, 3, 2, 1), twitter.getNewsFeed(1));
  }

  @Test
  @DisplayName("getNewsFeed of user who follows many users respects cap of 10")
  void newsFeedWithManyFolloweesRespectsCap() {
    // Users 2-6 each post 3 tweets => 15 total visible to user 1
    for (int user = 2; user <= 6; user++) {
      twitter.follow(1, user);
      for (int t = 0; t < 3; t++) {
        twitter.postTweet(user, user * 100 + t);
      }
    }
    List<Integer> feed = twitter.getNewsFeed(1);
    assertEquals(10, feed.size());
  }

  // -------------------------------------------------------------------------
  // follow / unfollow
  // -------------------------------------------------------------------------

  @Test
  @DisplayName("Following a user exposes their tweets in the news feed")
  void followExposesTweets() {
    twitter.postTweet(2, 42);
    assertTrue(twitter.getNewsFeed(1).isEmpty());

    twitter.follow(1, 2);
    assertEquals(List.of(42), twitter.getNewsFeed(1));
  }

  @Test
  @DisplayName("Unfollowing a user removes their tweets from the news feed")
  void unfollowRemovesTweets() {
    twitter.postTweet(2, 42);
    twitter.follow(1, 2);
    assertEquals(List.of(42), twitter.getNewsFeed(1));

    twitter.unfollow(1, 2);
    assertTrue(twitter.getNewsFeed(1).isEmpty());
  }

  @Test
  @DisplayName("Unfollowing a user not followed has no effect")
  void unfollowNonFolloweeSafe() {
    twitter.postTweet(1, 7);
    assertDoesNotThrow(() -> twitter.unfollow(1, 99));
    assertEquals(List.of(7), twitter.getNewsFeed(1));
  }

  @Test
  @DisplayName("Following yourself does not duplicate own tweets in feed")
  void followSelfDoesNotDuplicateTweets() {
    twitter.postTweet(1, 5);
    twitter.follow(1, 1);
    assertEquals(List.of(5), twitter.getNewsFeed(1));
  }

  @Test
  @DisplayName("Re-following an already-followed user is idempotent")
  void followIdempotent() {
    twitter.postTweet(2, 8);
    twitter.follow(1, 2);
    twitter.follow(1, 2); // duplicate
    assertEquals(List.of(8), twitter.getNewsFeed(1));
  }

  // -------------------------------------------------------------------------
  // postTweet
  // -------------------------------------------------------------------------

  @Test
  @DisplayName("Multiple users can post tweets independently")
  void multipleUsersPostIndependently() {
    twitter.postTweet(1, 100);
    twitter.postTweet(2, 200);

    assertEquals(List.of(100), twitter.getNewsFeed(1));
    assertEquals(List.of(200), twitter.getNewsFeed(2));
  }

  @Test
  @DisplayName("Tweets appear in recency order within a single user's feed")
  void tweetsOrderedByRecency() {
    twitter.postTweet(1, 1);
    twitter.postTweet(1, 2);
    twitter.postTweet(1, 3);
    assertEquals(List.of(3, 2, 1), twitter.getNewsFeed(1));
  }

  // -------------------------------------------------------------------------
  // Edge cases
  // -------------------------------------------------------------------------

  @Test
  @DisplayName("Feed after unfollow then re-follow reflects tweets posted during both periods")
  void unfollowAndRefollow() {
    twitter.follow(1, 2);
    twitter.postTweet(2, 10);
    twitter.unfollow(1, 2);
    twitter.postTweet(2, 20);
    twitter.follow(1, 2);

    List<Integer> feed = twitter.getNewsFeed(1);
    // Both tweets should now be visible
    assertTrue(feed.contains(10));
    assertTrue(feed.contains(20));
    // More recent tweet first
    assertTrue(feed.indexOf(20) < feed.indexOf(10));
  }

  @Test
  @DisplayName("Feed interleaves tweets from multiple followees correctly")
  void feedInterleavesTweetsFromMultipleFollowees() {
    twitter.follow(1, 2);
    twitter.follow(1, 3);

    twitter.postTweet(2, 101); // oldest
    twitter.postTweet(3, 201);
    twitter.postTweet(2, 102);
    twitter.postTweet(3, 202); // newest

    assertEquals(List.of(202, 102, 201, 101), twitter.getNewsFeed(1));
  }
}
