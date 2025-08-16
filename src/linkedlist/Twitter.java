package linkedlist;

import java.util.*;

/**
 * 355. 设计推特
 * https://leetcode.cn/problems/design-twitter/description/
 *
 * @author : ljke
 * @date : Created in 18:16 2025/8/3
 */
public class Twitter {

    int currentTimestamp;

    Map<Integer, User> userMap;

    class User {
        int userId;

        Set<Integer> followed;

        Tweet head;

        public User(int userId) {
            this.userId = userId;
            this.followed = new HashSet<>();
            this.head = null;
            // 关注自己
            follow(userId);
        }

        public void follow(int userId) {
            followed.add(userId);
        }

        public void unfollow(int userId) {
            if (userId != this.userId) {
                followed.remove(userId);
            }
        }

        public void post(int tweetId) {
            Tweet tweet = new Tweet(tweetId, currentTimestamp);
            currentTimestamp++;
            tweet.next = head;
            head = tweet;
        }
    }

    class Tweet {
        int tweetId;

        int timestamp;

        Tweet next;

        public Tweet(int tweetId, int timestamp) {
            this.tweetId = tweetId;
            this.timestamp = timestamp;
            this.next = null;
        }
    }

    public Twitter() {
        currentTimestamp = 0;
        userMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        //userId不存在新建
        userMap.putIfAbsent(userId, new User(userId));
        userMap.get(userId).post(tweetId);
    }

    public void follow(int followerId, int followeeId) {
        //userId不存在新建
        userMap.putIfAbsent(followerId, new User(followerId));
        userMap.putIfAbsent(followeeId, new User(followeeId));
        userMap.get(followerId).follow(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        //userId不存在不处理
        if (userMap.containsKey(followerId)) {
            userMap.get(followerId).unfollow(followeeId);
        }
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        if (!userMap.containsKey(userId)) {
            return res;
        }
        Set<Integer> users = userMap.get(userId).followed;
        PriorityQueue<Tweet> pq = new PriorityQueue<>(users.size(), (a, b) -> b.timestamp - a.timestamp);
        for (int id : users) {
            Tweet t = userMap.get(id).head;
            if (t != null) {
                pq.add(t);
            }
        }
        while (!pq.isEmpty()) {
            if (res.size() >= 10) {
                break;
            }
            // 弹出 timestamp 值最⼤的（最近发表的）
            Tweet t = pq.poll();
            res.add(t.tweetId);
            // 将下⼀篇 Tweet 插⼊进⾏排序
            if (t.next != null) {
                pq.add(t.next);
            }
        }
        return res;
    }
}
