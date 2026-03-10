package com.oscar.app.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/*
 * following:
 *        HashMap<followerId, Set<Integer>>
 * tweets:
 *        HashMap<userId, List<time tweetId>>
 * */
class Twitter {
  private Map<Integer, Set<Integer>> follows;
  private Map<Integer, List<Map.Entry<Integer, Integer>>> tweets;
  private int currentTime;

  public Twitter() {
    follows = new HashMap<>();
    tweets = new HashMap<>();
  }

  public void postTweet(int userId, int tweetId) {
    List<Map.Entry<Integer, Integer>> myTweets = tweets.getOrDefault(userId, new ArrayList<>());
    myTweets.add(Map.entry(++currentTime, tweetId));
    tweets.put(userId, myTweets);
  }

  public List<Integer> getNewsFeed(int userId) {
    List<Integer> feed = new ArrayList<>();
    Set<Integer> ids = new HashSet<>(follows.getOrDefault(userId, new HashSet<>()));
    ids.add(userId);

    // new int[]{timestamp, userId, index, tweetId}
    Queue<int[]> heap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

    for (Integer idx : ids) {
      List<Map.Entry<Integer, Integer>> tweet = tweets.get(idx);
      if (tweet != null && tweet.size() > 0) {
        Map.Entry<Integer, Integer> lastItem = tweet.get(tweet.size() - 1);
        heap.offer(new int[] { lastItem.getKey(), idx, tweet.size() - 1, lastItem.getValue() });
      }
    }

    while (!heap.isEmpty() && feed.size() < 10) {
      int[] cnt = heap.poll();
      feed.add(cnt[3]);
      List<Map.Entry<Integer, Integer>> tweet = tweets.get(cnt[1]);
      if (tweet != null && tweet.size() > 0 && cnt[2] - 1 >= 0) {
        Map.Entry<Integer, Integer> nextItem = tweet.get(cnt[2] - 1);
        heap.offer(new int[] { nextItem.getKey(), cnt[1], cnt[2] - 1, nextItem.getValue() });
      }
    }

    return feed;
  }

  public void follow(int followerId, int followeeId) {
    Set<Integer> cnt = follows.getOrDefault(followerId, new HashSet<>());
    cnt.add(followeeId);
    follows.put(followerId, cnt);
  }

  public void unfollow(int followerId, int followeeId) {
    Set<Integer> cnt = follows.getOrDefault(followerId, new HashSet<>());
    cnt.remove(followeeId);
    follows.put(followerId, cnt);
  }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
