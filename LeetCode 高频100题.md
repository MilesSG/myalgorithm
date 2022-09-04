# 👨‍💻 LeetCode 精选 TOP 面试题

2022-7-11

### [1. 两数之和](https://leetcode.cn/problems/two-sum/)

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {0, 0};
    }
}
```



### [2. 两数相加](https://leetcode.cn/problems/add-two-numbers/)



### [3. 无重复字符的最长子串](https://leetcode.cn/problems/longest-substring-without-repeating-characters/)

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        int left = 0;
        int right = 0;
        int maxLen = 1;
        HashSet<Character> set = new HashSet<>();
        while (left < s.length() && right < s.length()) {
            if (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            } else {
                set.add(s.charAt(right));
                right++;
            }
            int curLen = right - left;
            maxLen = Math.max(maxLen, curLen);
        }
        return maxLen;
    }
}
```

初始化时，left和right都为0



### [5. 最长回文子串](https://leetcode.cn/problems/longest-palindromic-substring/)

```java
class Solution {
    public String longestPalindrome(String s) {
        int maxLen = 1;
        int start = 0;
        for (int i = 1; i < s.length(); i++) {
            int left = i - 1;
            int right = i + 1;
            int curLen = 1;
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                left--;
                curLen++;
            }
            while (right <= s.length() - 1 && s.charAt(right) == s.charAt(i)) {
                right++;
                curLen++;
            }
            while (left >= 0 && right <= s.length() - 1 && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                curLen += 2;
            }
            if (curLen > maxLen) {
                maxLen = curLen;
                start = left + 1;
            }
        }
        return s.substring(start, start + maxLen);
    }

    
}
```

curLen > maxLen时更新maxLen



### [7. 整数反转](https://leetcode.cn/problems/reverse-integer/)

```java
class Solution {
    public int reverse(int x) {
        boolean flag = true;
        if (x < 0) {
            x = -x;
            flag = false;
        }
        if (x == 0) {
            return 0;
        }
        long result = 0;
        while (x > 0) {
            result = result * 10 + x % 10;
            if(result > Integer.MAX_VALUE){
                return 0;
            }
            x /= 10;
        }
        if (flag) {
            return (int)result;
        } else {
            return -(int)result;
        }
    }
}
```



















