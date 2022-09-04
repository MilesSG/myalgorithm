# 🔥 LeetCode 热题 HOT 100

2022-08-03

### [1. 两数之和](https://leetcode.cn/problems/two-sum/)

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {0, 0};
    }
}
```

一开始打算先排序，再用双指针得解法，发现是不行的，因为这题要返回数组得下标，所以排序会打乱下标得顺序，只能使用双层for循环。



### [2. 两数相加](https://leetcode.cn/problems/add-two-numbers/)

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        ListNode headA = l1;
        ListNode headB = l2;
        // count为进位
        int count = 0;
        while (headA != null && headB != null) {
            int sum = headA.val + headB.val + count;
            if (sum >= 10) {
                sum -= 10;
                count = 1;
            } else {
                count = 0;
            }
            ListNode tmp = new ListNode(sum);
            pre.next = tmp;
            pre = pre.next;
            headA = headA.next;
            headB = headB.next;
        }

        // 下面考虑单个链表提前结束的情况
        /* 
        1. 第二行链表提前结束，
        类似于：
        2 - 3 - 7 - 9 - 9 - 9
        1 - 2 - 5
        */
        while (headA != null) {
            int sum = headA.val + count;
            if (sum >= 10) {
                sum -= 10;
                count = 1;
            } else {
                count = 0;
            }
            ListNode tmp = new ListNode(sum);
            pre.next = tmp;
            pre = pre.next;
            headA = headA.next;
        }

        /* 
        2. 第一行链表提前结束，
        类似于：
        1 - 2 - 5
        2 - 3 - 7 - 9 - 9 - 9
        */
        while (headB != null) {
            int sum = headB.val + count;
            if (sum >= 10) {
                sum -= 10;
                count = 1;
            } else {
                count = 0;
            }
            ListNode tmp = new ListNode(sum);
            pre.next = tmp;
            pre = pre.next;
            headB = headB.next;
        }

        // 还差最后一步：防止一直进1位的情况 比如 9 9 9 加到最后要进一位
        if (count == 1) {
            pre.next = new ListNode(1);
        }

        return dummy.next;
    }
}
```

参考题解：[子烁爱学习](https://www.bilibili.com/video/BV16p4y1p7jC?spm_id_from=333.337.search-card.all.click&vd_source=35aeaee52b15e78b11967f5ef3ce655a)



### [3. 无重复字符的最长子串](https://leetcode.cn/problems/longest-substring-without-repeating-characters/)

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int left = 0;
        int right = 0;
        int maxLen = 0;
        while (right < s.length()) {
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

再次简化:

* while循环不需要判断left小于字符串s的长度，因为right始终在left的右边
* maxLen初始化为0，则不需要对s进行判空的操作



### [4. 寻找两个正序数组的中位数](https://leetcode.cn/problems/median-of-two-sorted-arrays/)



### [5. 最长回文子串](https://leetcode.cn/problems/longest-palindromic-substring/)

```java
class Solution {
    public String longestPalindrome(String s) {
        int maxLen = 1;
        int startIndex = 0;
        for (int i = 1; i < s.length(); i++) {
            int left = i - 1;
            int right = i + 1;
            int curLen = 1;
            while (left >= 0 && s.charAt(i) == s.charAt(left)) {
                left--;
                curLen++;
            }
            while (right < s.length() && s.charAt(i) == s.charAt(right)) {
                right++;
                curLen++;
            }
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                curLen += 2;
            }
            // 易忘
            if (curLen > maxLen) {
                maxLen = curLen;
                startIndex = left + 1;
            }
        }
        return s.substring(startIndex, startIndex + maxLen);
    }
}
```



### [10. 正则表达式匹配](https://leetcode.cn/problems/regular-expression-matching/)



### [11. 盛最多水的容器](https://leetcode.cn/problems/container-with-most-water/)

```java
class Solution {
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int res = 0;
        while (i < j) {
            if (height[i] < height[j]) {
                res = Math.max(res, (j - i) * height[i]);
                i++;
            } else {
                res = Math.max(res, (j - i) * height[j]);
                j--;
            }
        }
        return res;
    }
}
```



### [15. 三数之和](https://leetcode.cn/problems/3sum/)

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        HashSet<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // 注意left是i + 1
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] == 0) {
                    set.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        // 注意API是addAll
        res.addAll(set);
        return res;
    }
}
```

注意：

* 内循环的left是i + 1
* API是addAll



### [17. 电话号码的字母组合](https://leetcode.cn/problems/letter-combinations-of-a-phone-number/)

```java
class Solution {

    //设置全局列表存储最后的结果
    List<String> list = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return list;
        }
        //初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        //迭代处理
        backTracking(digits, numString, 0);
        return list;

    }

    //每次迭代获取一个字符串，所以会设计大量的字符串拼接，所以这里选择更为高效的 StringBuild
    StringBuilder temp = new StringBuilder();

    //比如digits如果为"23",num 为0，则str表示2对应的 abc
    public void backTracking(String digits, String[] numString, int num) {
        //遍历全部一次记录一次得到的字符串
        if (num == digits.length()) {
            list.add(temp.toString());
            return;
        }
        //str 表示当前num对应的字符串
        String str = numString[digits.charAt(num) - '0'];
        for (int i = 0; i < str.length(); i++) {
            temp.append(str.charAt(i));
            //c
            backTracking(digits, numString, num + 1);
            //剔除末尾的继续尝试
            temp.deleteCharAt(temp.length() - 1);
        }
    }
}
```



### [19. 删除链表的倒数第 N 个结点](https://leetcode.cn/problems/remove-nth-node-from-end-of-list/)

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        // fast快指针先向右移动n步
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        // 这个不要忘记判断
        if (fast == null) return head.next;
        // 注意是head.next
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
```



### [20. 有效的括号](https://leetcode.cn/problems/valid-parentheses/)

```java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty()) {
                return false;
            } else if (c != stack.pop()) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
```



### [21. 合并两个有序链表](https://leetcode.cn/problems/merge-two-sorted-lists/)

```java
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return (list1 == null) ? list2 : list1;
        }
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list2.next, list1);
            return list2;
        }
    }
}
```



### [22. 括号生成](https://leetcode.cn/problems/generate-parentheses/)

```java
class Solution {

    List<String> res = new LinkedList<>();
    int pair = 0;
    public List<String> generateParenthesis(int n) {
        pair = n;
        if (n == 0) return res;
        dfs("", 0, 0);
        return res;
    }

    // leftCount表示左括号的数量，rightCount表示右括号的数量
    private void dfs(String path, int leftCount, int rightCount) {
        if (path.length() == pair * 2) {
            res.add(path);
            return;
        }
        // 1. 左括号少于总括号对数
        if (leftCount < pair) {
            dfs(path + "(", leftCount + 1, rightCount);
        }
        // 2. 右括号数少于左括号数
        if (rightCount < leftCount) {
            dfs(path + ")", leftCount, rightCount + 1);
        }
    }
}
```



### [23. 合并K个升序链表](https://leetcode.cn/problems/merge-k-sorted-lists/)

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) return null;
        ListNode res = null;
        for (ListNode node : lists) {
            res = merge(res, node);
        }
        return res;
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return (list1 == null) ? list2 : list1;
        }
        if (list1.val < list2.val) {
            list1.next = merge(list1.next, list2);
            return list1;
        } else {
            list2.next = merge(list2.next, list1);
            return list2;
        }
    }
}
```



### [31. 下一个排列](https://leetcode.cn/problems/next-permutation/)

```java
class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        // 逆向寻找，从后向前递减的第一个排列
        for (int i = n - 1; i >= 0; i--) {
            if (i == 0) {
                // 说明所有元素全部升序，如[3, 2, 1]，则返回[1, 2, 3]即可
                Arrays.sort(nums);
                return;
            } else {
                // 找到了第一个递减的排列，比如[1, 5, 4, 3]中找到了5 -> 1这个递减排列，那么只要在1后面找到一个比1大的最小的数，也就是3，再将1和3交换，得到[3, 1, 4, 5]
                if (nums[i] > nums[i - 1]) {
                    // i到n进行排序，为的就是找到比1大的最小的一个数，也就是3
                    Arrays.sort(nums, i, n);
                    for (int j = i; j < n; j++) {
                        if (nums[j] > nums[i - 1]) {
                            int tmp = nums[i - 1];
                            nums[i - 1] = nums[j];
                            nums[j] = tmp;
                            return;
                        }
                    }
                }
            }
        }
    }
}
```

[程序员刀刀](https://www.bilibili.com/video/BV1SE411e7gk?spm_id_from=333.337.search-card.all.click&vd_source=35aeaee52b15e78b11967f5ef3ce655a)

![image-20220804141750837](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220804141750837.png)





### [32. 最长有效括号](https://leetcode.cn/problems/longest-valid-parentheses/)

```java
class Solution {
    public int longestValidParentheses(String s) {
        // dp表示以某个括号结尾的字符串的最大有效括号长度
        int[] dp = new int[s.length()];
        Arrays.fill(dp, 0);
        int res = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = 2 + dp[i - 1] + (i - dp[i - 1] - 2 > -1 ? dp[i - dp[i - 1] - 2] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
```



### [33. 搜索旋转排序数组](https://leetcode.cn/problems/search-in-rotated-sorted-array/)

```java
class Solution {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid  = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // target在[nums[0], nums[mid]]
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target <= nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                // target在[nums[mid], nums[nums.length - 1]]
                if (nums[mid] <= target && target <= nums[nums.length - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}
```



### [34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/)

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = serchFirst(nums, target);
        int right = searchLast(nums, target);
        int[] res = new int[]{left, right};
        return res;
    }

    // 搜索第一个出现的数
    private int serchFirst(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                // 第一个数、中间的数!=中间靠左的数(即表示不是重复元素) 则直接返回
                if (mid == 0 || nums[mid] != nums[mid - 1]) {
                    return mid;
                    // 否则就左移
                } else {
                    right = mid - 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    // 搜索最后一个出现的数
    private int searchLast(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                // 是最后一个数、中间的数!=中间靠右的数(即表示不是重复元素) 则直接返回
                if (mid == nums.length - 1 || nums[mid] != nums[mid + 1]) {
                    return mid;
                } else {
                    // 否则就右移
                    left = mid + 1;
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
```

时间复杂度要求是O(log n)，所以只能是binary research 

 [程序员刀刀](https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/solution/ren-zhe-suan-fa-quan-wang-zui-qing-xi-yi-f8up/)



### [39. 组合总和](https://leetcode.cn/problems/combination-sum/)

```java
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates, target, 0);
        return res;
    }

    public void dfs(int[] candidates, int target, int start) {
        // 这个可以不加，仅用于优化，见下图
        if (target < 0) return;
        if (target == 0) {
            res.add(new LinkedList(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                path.add(candidates[i]);
                // 因为可以重复使用，所以还是i，比如虽然只有一个2，但可以用几次
                dfs(candidates, target - candidates[i], i);
                path.removeLast(); 
            }
        }
    }
}
```

对于样例：candidates [2,3,6,7], target为7

for循环的i如果从0开始，则本题的结果是：[[2,2,3],[2,3,2],[3,2,2],[7]]，不符合题意

但for循环的i如果从start开始，则本题的结果是：[[2,2,3],[7]]，去重了元素，复合题意



### [42. 接雨水](https://leetcode.cn/problems/trapping-rain-water/)



### [46. 全排列](https://leetcode.cn/problems/permutations/)

```java
class Solution {

    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        dfs(nums, 0);
        return res;
    }

    private void dfs(int[] nums, int start) {
        if (path.size() == nums.length) {
            res.add(new LinkedList<>(path));
        }
        for (int i = 0; i < nums.length; i++) {
            if (path.contains(nums[i])) {
                continue;
            }
            path.add(nums[i]);
            dfs(nums, i);
            path.removeLast();
        }
    }
}
```

对于样例：[1,2,3]

如果for循环的i从start开始，则最终的返回结果的[[1,2,3]]，相当于去了重，不符合题意

但如果for循环的i从0开始，则最终的返回结果的[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]，符合题意



### [48. 旋转图像](https://leetcode.cn/problems/rotate-image/)

```java
class Solution {
    public void rotate(int[][] matrix) {
        int m = matrix.length;
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        for (int[] lie : matrix) {
            reverse(lie);
        }
    }

    // 一维数组中交换arr[i]和arr[j]
    private void reverse(int[] arr) {
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
    }
}
```



### [49. 字母异位词分组](https://leetcode.cn/problems/group-anagrams/)

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        //1.创建一个哈希表
        Map<String,List> map = new HashMap<String, List>();
        for (String s: strs) {
            //将字符串转化为字符数组
            char[] chars = s.toCharArray();
            //对字符数组按照字母顺序排序
            Arrays.sort(chars);
            //将排序后的字符串作为哈希表中的key值
            String key = String.valueOf(chars);
            //2.判读哈希表中是否有该key值
            if (!map.containsKey(key)){
                //若不存在，则为新的异位词语，在map中创建新的键值对
                map.put(key,new ArrayList());
            }
            //3.将该字符串放在对应key的list中
            map.get(key).add(s);
        }
        //返回map中所有键值对象构成的list
        return new ArrayList(map.values());
    }
}
```

[官方题解](https://www.bilibili.com/video/BV1Yf4y1e7gJ?spm_id_from=333.337.search-card.all.click&vd_source=35aeaee52b15e78b11967f5ef3ce655a) 

HashMap那边的判断和之前不一样，这题需要将所有的值都add到map中，所以`map.get(key).add(s);`不能像之前那样出现在else if当中。



### [53. 最大子数组和](https://leetcode.cn/problems/maximum-subarray/)

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
```



### [55. 跳跃游戏](https://leetcode.cn/problems/jump-game/)

```java
class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        // farthest代表能够跳到的最远的距离
        int farthest = 0;
        for (int i = 0; i < n - 1; i++) {
            // 更新最远距离farthest
            farthest = Math.max(farthest, i + nums[i]);
            if (farthest <= i) {
                return false;
            }
        }
        // 看最远的距离是否能够达到最后一位
        return farthest >= n - 1;
    }
}
```



### [56. 合并区间](https://leetcode.cn/problems/merge-intervals/)

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> res = new LinkedList<>();
        // 按区间的 start 升序排列
        Arrays.sort(intervals, (a, b) -> {return a[0] - b[0];});
        // cur相当于[[1, 3],[4, 6],[8, 10],[15, 18]]中的[1, 3]
        int[] cur = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            // 没有重叠，i从1开始，所以相当于在[[1, 3],[4, 6],[8, 10],[15, 18]]中，[4, 6]中的4大于[1, 3]中的3，直接将[1,3]加入res
            if (intervals[i][0] > cur[1]) {
                res.add(cur);
                // 更新cur为[8, 10]
                cur = intervals[i];
            } else {
                // 有重叠，比如在[[1, 3],[2, 6],[8, 10],[15, 18]]中取二维数组的第一个的第二位即更新[1, 3]种cur[1] = 3
                cur[1] = Math.max(cur[1], intervals[i][1]);
            }
        }
        res.add(cur);
        // 因为返回的是二维数组，所以整理一下结果
        int[][] ans = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
```



### [62. 不同路径](https://leetcode.cn/problems/unique-paths/)

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
```

`dp[i][j]`表示到达行i列j的所有路径，所以外围的边界为1，因为只能一直向右或向下。



### [64. 最小路径和](https://leetcode.cn/problems/minimum-path-sum/)

```java
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        // 初始化dp[0][0]要注意不是0，而是grid[0][0]
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
```



### [70. 爬楼梯](https://leetcode.cn/problems/climbing-stairs/)

```java
class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
```



### [72. 编辑距离](https://leetcode.cn/problems/edit-distance/)

```java
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(); // 行
        int n = word2.length(); // 列
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        for (int i = 1; i < m + 1; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i < n + 1; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
```





### [75. 颜色分类](https://leetcode.cn/problems/sort-colors/)

```java
class Solution {
    public void sortColors(int[] nums) {
        // 初始化出0、1、2的频率
        int numOf0 = 0;
        int numOf1 = 0;
        int numOf2 = 0;
        for (int num : nums) {
            if (num == 0) {
                numOf0++;
            } else if (num == 1) {
                numOf1++;
            } else if (num == 2) {
                numOf2++;
            }
        }
        
        for (int i = 0; i < numOf0; i++) {
            nums[i] = 0;
        }
        for (int i = numOf0; i < numOf0 + numOf1; i++) {
            nums[i] = 1;
        }
        for (int i = numOf0 + numOf1; i < nums.length; i++) {
            nums[i] = 2;
        }
        return;
    }
}
```

统计出0、1、2的频率，并将原数组进行覆盖，[力扣上我的的题解已经发布](https://leetcode.cn/problems/sort-colors/solution/by-milessg-4bkk/)

自己努力思考，总会有很大收获



### [76. 最小覆盖子串](https://leetcode.cn/problems/minimum-window-substring/)





### [78. 子集](https://leetcode.cn/problems/subsets/)

```java
class Solution {

    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, 0);
        return res;
    }

    private void dfs(int[] nums, int start) {
        res.add(new LinkedList<>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, i + 1);
            path.removeLast();
        }
    }
}
```

for循环中的i从start开始遍历，如果从0开始的话，会超时



### [79. 单词搜索](https://leetcode.cn/problems/word-search/)

```java
class Solution {
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, words, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
	// i和j表示第i行第j列 k表示访问到单词的第几个字符
    private boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length 
        || board[i][j] != word[k]) return false;
        // 全部匹配完毕
        if(k == word.length - 1) return true;
        // 用特殊字符记录board[i][j]位置，表示已经访问过，目的就是防止被重复访问
        board[i][j] = '*';
        boolean res = dfs(board, word, i + 1, j, k + 1) 
            || dfs(board, word, i - 1, j, k + 1)
        	|| dfs(board, word, i, j + 1, k + 1) 
            || dfs(board, word, i, j - 1, k + 1);
        // 没找到的话，要回退
        board[i][j] = word[k];
        return res;
    }
}
```



### [84. 柱状图中最大的矩形](https://leetcode.cn/problems/largest-rectangle-in-histogram/)





### [85. 最大矩形](https://leetcode.cn/problems/maximal-rectangle/)



### [94. 二叉树的中序遍历](https://leetcode.cn/problems/binary-tree-inorder-traversal/)

```java
class Solution {

    List<Integer> res = new LinkedList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return res;
        }
        dfs(root);
        return res;
    }

    // 中序：左根右
    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        res.add(root.val);
        dfs(root.right);
    }
}
```



### ==[96. 不同的二叉搜索树](https://leetcode.cn/problems/unique-binary-search-trees/)==

```java
class Solution {
    public int numTrees(int n) {
        // 初始化 dp 数组
        int[] dp = new int[n + 1];
        // 初始化0个节点和1个节点的情况
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                //对于第i个节点，需要考虑1作为根节点直到i作为根节点的情况，所以需要累加
                //一共i个节点，对于根节点j时,左子树的节点个数为j-1，右子树的节点个数为i-j
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
```



### [98. 验证二叉搜索树](https://leetcode.cn/problems/validate-binary-search-tree/)

```java
class Solution {

    List<Integer> res = new LinkedList<>();

    public boolean isValidBST(TreeNode root) {
        dfs(root);
        // 前面的比后面的大就返回false
        for (int i = 1; i < res.size(); i++) {
            if (res.get(i - 1) >= res.get(i)) {
                return false;
            }
        }
        return true;
    }

    // 中序遍历：左根右
    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        res.add(root.val);
        dfs(root.right);
    }
}
```



### [101. 对称二叉树](https://leetcode.cn/problems/symmetric-tree/)

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return help(root.left, root.right);
    }

    private boolean help(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return help(left.left, right.right) && help(left.right, right.left);
    }
}
```



### [102. 二叉树的层序遍历](https://leetcode.cn/problems/binary-tree-level-order-traversal/)

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return res;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> path = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                // 向path中加入节点值
                path.add(node.val);
                if (node.left != null) {
                    // 向队列中加左节点
                    queue.add(node.left);
                }
                if (node.right != null) {
                    // 向队列中加右节点
                    queue.add(node.right);
                }
            }
            res.add(path);
        }
        return res;
    }
}
```



### [104. 二叉树的最大深度](https://leetcode.cn/problems/maximum-depth-of-binary-tree/)

```java
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
}
```



### [105. 从前序与中序遍历序列构造二叉树](https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)

```java
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        TreeNode root = new TreeNode(preorder[0]);
        int rootIndexInorder = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[0]) {
                rootIndexInorder = i;
                break;
            }
        }
        int[] leftPreorder = Arrays.copyOfRange(preorder, 1, rootIndexInorder + 1);
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, rootIndexInorder);
        root.left = buildTree(leftPreorder, leftInorder);
        
        int[] rightPreorder = Arrays.copyOfRange(preorder, rootIndexInorder + 1, inorder.length);
        int[] rightInorder = Arrays.copyOfRange(inorder, rootIndexInorder + 1, inorder.length);
        root.right = buildTree(rightPreorder, rightInorder);

        return root;
    }
}
```





### [114. 二叉树展开为链表](https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/)

```java
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.left);
        flatten(root.right);
        
        TreeNode left = root.left;
        TreeNode right = root.right;
        
		root.left = null;
        root.right = left;

        while (root.right != null) {
            root = root.right;
        }
        root.right = right;
    }
}
```

[跑马拉松的程序员](https://www.bilibili.com/video/BV15Z4y1R7Zo?spm_id_from=333.337.search-card.all.click&vd_source=35aeaee52b15e78b11967f5ef3ce655a)

<img src="https://labuladong.github.io/algo/images/二叉树系列/2.jpeg" alt="img" style="zoom:50%;" />





### [121. 买卖股票的最佳时机](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/)

```java
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            // 第i天不持有 = 第i-1天不持有加上第i-1天持有然后第i天卖掉
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]); 
            // 第i天持有 = 第i-1天持有加上第i-1天(这题是第0天)持有然后第i天买入
            // dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], 0 - prices[i]); 
        }
        return dp[prices.length - 1][0];
    }
}
```



### [124. 二叉树中的最大路径和](https://leetcode.cn/problems/binary-tree-maximum-path-sum/)

```java
class Solution {
    int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        // 从root、root + left、root + right三者中选出临时的最大值tmp
        int tmp = Math.max(root.val, Math.max(root.val + left, root.val + right));
        // 再加入root + left + right 与临时变量tmp进行比较
        res = Math.max(res, Math.max(tmp, root.val + left + right));
        return tmp;
    }
}
```





### [128. 最长连续序列](https://leetcode.cn/problems/longest-consecutive-sequence/)

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int maxLen = 0;
        // 存放已经使用过的元素
        Set<Integer> used = new HashSet<>();
        for (int num : set) {
            if (used.contains(num)) {
                continue;
            }
            int curLen = 1;
            while (set.contains(num + 1)) {
                curLen++;
                num++;
                used.add(num + 1);
            }
            maxLen = Math.max(maxLen, curLen);
        }
        return maxLen;
    }
}
```













### [136. 只出现一次的数字](https://leetcode.cn/problems/single-number/)

```java
class Solution {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (int num : map.keySet()) {
            if (map.get(num) == 1) {
                return num;
            }
        }
        return -1;
    }
}
```



### [141. 环形链表](https://leetcode.cn/problems/linked-list-cycle/)

```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur)) {
                return true;
            } else {
                set.add(cur);
            }
            cur = cur.next;
        }
        return false;
    }
}
```

不知道为什么哈希里面不可以存Integer，只能存ListNode



### [142. 环形链表 II](https://leetcode.cn/problems/linked-list-cycle-ii/)

```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur)) {
                return cur;
            } else {
                set.add(cur);
            }
            cur = cur.next;
        }
        return null;
    }
}
```



### [146. LRU 缓存](https://leetcode.cn/problems/lru-cache/)

```java
class LRUCache {

    LinkedHashMap<Integer, Integer> map;
    int capacity;

    public LRUCache(int capacity) {
        map = new LinkedHashMap<>(capacity, 0.75f, true);
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        return map.get(key);
    }
    
    public void put(int key, int value) {
        map.put(key, value);
        if (map.size() > capacity) {
            Iterator it = map.keySet().iterator();
            map.remove(it.next());
        }
    }
}
```

LinkedHashMap数据结构



### [160. 相交链表](https://leetcode.cn/problems/intersection-of-two-linked-lists/)

```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = getLen(headA);
        int lenB = getLen(headB);
        if (lenA < lenB) {
            int diff = lenB - lenA;
            while (diff > 0) {
                headB = headB.next;
                diff--;
            }
        } else {
            int diff = lenA - lenB;
            while (diff > 0) {
                headA = headA.next;
                diff--;
            }
        }
        // 判断条件是两个头指针不相等
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    private int getLen(ListNode head) {
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        return len;
    }
}
```



### [169. 多数元素](https://leetcode.cn/problems/majority-element/)

```java
class Solution {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (int num : map.keySet()) {
            if (map.get(num) > nums.length / 2) {
                return num;
            }
        }
        return -1;
    }
}
```

这题是[136. 只出现一次的数字](https://leetcode.cn/problems/single-number/)的姊妹题



### [198. 打家劫舍](https://leetcode.cn/problems/house-robber/)

```java
class Solution {
    public int rob(int[] nums) {
        if (nums.length <= 1) return (nums.length == 0) ? 0 : nums[0];
        int res = Math.max(nums[0], nums[1]);
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
```





### [200. 岛屿数量](https://leetcode.cn/problems/number-of-islands/)

```java
class Solution {
    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }    
}
```



### [206. 反转链表](https://leetcode.cn/problems/reverse-linked-list/)

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }
}
```





### [215. 数组中的第K个最大元素](https://leetcode.cn/problems/kth-largest-element-in-an-array/)

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            if (queue.size() < k) {
                queue.add(num);
            } else if (queue.peek() < num) {
                queue.poll();
                queue.add(num);
            }
        }
        return queue.peek();
    }
}
```



### [226. 翻转二叉树](https://leetcode.cn/problems/invert-binary-tree/)

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode tmp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(tmp);
        return root;
    }
}
```



### [234. 回文链表](https://leetcode.cn/problems/palindrome-linked-list/)

```java
class Solution {
    public boolean isPalindrome(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        cur = head;
        while (!stack.isEmpty()) {
            if (stack.pop() != cur.val) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }
}
```

不知道为什么Stack中只能存Integer，不能存ListNode，但[141. 环形链表](https://leetcode.cn/problems/linked-list-cycle/)只能使用存入ListNode这种数据结构



### [236. 二叉树的最近公共祖先](https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/)

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return root;
        if (root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right != null) return right;
        if (left != null && right == null) return left;
        if (left != null && right != null) return root;
        return null;
    }
}
```



### [283. 移动零](https://leetcode.cn/problems/move-zeroes/)

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
```





### [287. 寻找重复数](https://leetcode.cn/problems/find-the-duplicate-number/)

```java
class Solution {
    public int findDuplicate(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (int num : map.keySet()) {
            if (map.get(num) >= 2) {
                return num;
            }
        }
        return -1;
    }
}
```



### [560. 和为 K 的子数组](https://leetcode.cn/problems/subarray-sum-equals-k/)

```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        /**
         扫描一遍数组, 使用map记录出现同样的和的次数, 对每个i计算累计和sum并判断map内是否有sum-k
         **/
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, ret = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                ret += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);

        }
        return ret;
    }
}
```

