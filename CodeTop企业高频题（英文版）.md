# CodeTop企业高频题（英文版）

2022-8-14

### [206. Reverse Linked List](https://leetcode.cn/problems/reverse-linked-list/)

Given the head of a singly linked list, reverse the list, and return the reversed list.

**Example 1:**

![img](http://milessg.oss-cn-beijing.aliyuncs.com/img/rev1ex1.jpg)

```
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
```

method one：Iteration

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
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

method two：Stack

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        ListNode dummy = new ListNode(-1);
        ListNode tmp = dummy;
        while (!stack.isEmpty()) {
            tmp.next = stack.pop();
            tmp = tmp.next;
        }
        tmp.next = null;
        return dummy.next;
    }
}
```



### [3. Longest Substring Without Repeating Characters](https://leetcode.cn/problems/longest-substring-without-repeating-characters/)

![image-20220814101410685](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220814101410685.png)

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



### [146. LRU Cache](https://leetcode.cn/problems/lru-cache/)

![image-20220814103038224](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220814103038224.png)

```java
class LRUCache {

    private LinkedHashMap<Integer, Integer> map;
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

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```



### [215. Kth Largest Element in an Array](https://leetcode.cn/problems/kth-largest-element-in-an-array/)

![image-20220814103849869](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220814103849869.png)

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



### [25. Reverse Nodes in k-Group](https://leetcode.cn/problems/reverse-nodes-in-k-group/)

![image-20220814105521070](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220814105521070.png)

```java
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return head;
        ListNode firstHead = head;
        ListNode firstTail = head;
        for (int i = 0; i < k - 1; i++) {
            firstTail = firstTail.next;
            if (firstTail == null) return head;
        }
        ListNode secondHead = firstTail.next;
        firstTail.next = null;
        reverseList(firstHead);
        firstHead.next = reverseKGroup(secondHead, k);
        return firstTail;
    }

    public void reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
    }
}
```

The diagram is as follows:

![image-20220814110848505](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220814110848505.png)



### [15. 3Sum](https://leetcode.cn/problems/3sum/)

![image-20220814111217889](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220814111217889.png)

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        LinkedList<List<Integer>> res = new LinkedList<>();
        HashSet<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
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
        res.addAll(set);
        return res;
    }
}
```



### [912. Sort an Array](https://leetcode.cn/problems/sort-an-array/)

**Quick Sort：**

```java
class Solution {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        int l = left;
        int r = right;
        int mid = nums[(left + right) / 2];
        int tmp = 0;
        while (l < r) {
            while (nums[l] < mid) {
                l++;
            }
            while (nums[r] > mid) {
                r--;
            }
            if (l >= r) {
                break;
            }
            tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            if (nums[l] == mid) {
                r--;
            }
            if (nums[r] == mid) {
                l++;
            }
        }
        if (l == r) {
            l++;
            r--;
        }
        if (left < r) {
            quickSort(nums, left, r);
        }
        if (l < right) {
            quickSort(nums, l, right);
        }
    }
}
```



### [53. Maximum Subarray](https://leetcode.cn/problems/maximum-subarray/)

![image-20220814125535839](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220814125535839.png)

```java
class Solution {
    public int maxSubArray(int[] nums) {
        // dp[i] represents the sum of the largest subsequences ending with the i subscript
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max((dp[i - 1] + nums[i]), nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
```



### [21. Merge Two Sorted Lists](https://leetcode.cn/problems/merge-two-sorted-lists/)

![image-20220814133245783](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220814133245783.png)

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



### [1. Two Sum](https://leetcode.cn/problems/two-sum/)

Method  1: HashMap

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // key---num; value---index
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[] {0, 0};
    }
}
```

Method 2: brute force solution

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



### [102. Binary Tree Level Order Traversal](https://leetcode.cn/problems/binary-tree-level-order-traversal/)

![image-20220814141026794](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220814141026794.png)

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
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                path.add(node.val);
            }
            res.add(path);
        }
        return res;
    }
}
```



### [121. Best Time to Buy and Sell Stock](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/)

![image-20220814145612360](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220814145612360.png)

```java
class Solution {
    public int maxProfit(int[] prices) {
        /*
        dp[i][j] means to sell on the i-th day, the profit is the largest; 
        the second j is 0 means not holding, j is 1 means holding
        */
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], 0 - prices[i]);
        }
        return dp[prices.length - 1][0];
    }
}
```



### [141. Linked List Cycle](https://leetcode.cn/problems/linked-list-cycle/)

![image-20220814145556209](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220814145556209.png)

```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode cur = head;
        HashSet<ListNode> set = new HashSet<>();
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



### 📌[33. Search in Rotated Sorted Array](https://leetcode.cn/problems/search-in-rotated-sorted-array/)

![image-20220814145718179](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220814145718179.png)

```java
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                // target at [nums[0], nums[mid]]
                if (nums[0] <= target && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[0] > nums[mid]){
                // target at [nums[mid], nums[nums.length - 1]]
                if (nums[mid] <= target && target <= nums[nums.length - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
```



### [103. Binary Tree Zigzag Level Order Traversal](https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/)

![image-20220814153834426](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220814153834426.png)

```java
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return res;
        queue.add(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> path = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (flag) {
                    path.addLast(node.val);
                } else {
                    path.addFirst(node.val);
                }
            }
            flag = !flag;
            res.add(path);
        }
        return res;
    }
}
```



### [200. Number of Islands](https://leetcode.cn/problems/number-of-islands/)

![image-20220814185958320](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220814185958320.png)

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

    private void  dfs(char[][] grid, int i, int j) {
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



### [20. Valid Parentheses](https://leetcode.cn/problems/valid-parentheses/)

![image-20220814155011396](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220814155011396.png)

```java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty()) { // For example, the original string is a right parenthesis, such as: ")]}", then nothing will be pushed in the stack, that is, the stack is empty
                return false;
            } else if (c != stack.pop()) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
```



### [5. Longest Palindromic Substring](https://leetcode.cn/problems/longest-palindromic-substring/)

![image-20220814205734693](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220814205734693.png)

```java
class Solution {
    public String longestPalindrome(String s) {
        int maxLen = 1;
        int startIndex = 0;
        for (int i = 1; i < s.length(); i++) {
            int left = i - 1;
            int right = i + 1;
            int curLen = 1;
            while (right <= s.length() - 1 && s.charAt(i) == s.charAt(right)) {
                right++;
                curLen++;
            }
            while (left >= 0 && s.charAt(i) == s.charAt(left)) {
                left--;
                curLen++;
            }
            while (left >= 0 && right <= s.length() - 1 && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                curLen += 2;
            }
            if (curLen > maxLen) {
                maxLen = curLen;
                startIndex = left + 1;
            }
        }
        return s.substring(startIndex, startIndex + maxLen);
    }
}
```



### [236. Lowest Common Ancestor of a Binary Tree](https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/)

![image-20220814211350197](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220814211350197.png)

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return  root;
    }
}
```



### [88. Merge Sorted Array](https://leetcode.cn/problems/merge-sorted-array/)

![image-20220814212232058](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220814212232058.png)

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }
}
```



### [160. Intersection of Two Linked Lists](https://leetcode.cn/problems/intersection-of-two-linked-lists/)

![image-20220814215443817](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220814215443817.png)

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



### [46. Permutations](https://leetcode.cn/problems/permutations/)

全排列

![image-20220815204527133](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220815204527133.png)

方法一：

```java
class Solution {
    // 方法一：视频22分钟左右 https://www.bilibili.com/video/BV1Yt4y1t7dK?spm_id_from=333.1007.top_right_bar_window_history.content.click&vd_source=35aeaee52b15e78b11967f5ef3ce655a
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();
    boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        dfs(nums, 0);
        return res;
    }

    private void dfs(int[] nums, int start) {
        if (path.size() == nums.length) {
            res.add(new LinkedList<>(path));
        }
        for (int i = 0; i < nums.length; i++) {
            // 已经用过的就不能再用了
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            dfs(nums, i + 1);
            path.removeLast();
            used[i] = false;
        }
    }
}
```

方法二：

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
            // 已经用过的就不能再用了
            if (path.contains(nums[i])) {
                continue;
            }
            path.add(nums[i]);
            dfs(nums, i + 1);
            path.removeLast();
        }
    }
}
```

子集/组合的for循环从start开始; 排列的for循环从0开始

如果元素可以重复使用，则dfs再从i开始（元素如果不可重复使用就从i+1开始）



### [23. Merge k Sorted Lists](https://leetcode.cn/problems/merge-k-sorted-lists/)

![image-20220815205734734](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220815205734734.png)

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res = null;
        for (ListNode list : lists) {
            res = merge(res, list);
        }
        return res;
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return (head1 == null) ? head2 : head1;
        }
        if (head1.val < head2.val) {
            head1.next = merge(head1.next, head2);
            return head1;
        } else {
            head2.next = merge(head2.next, head1);
            return head2;
        }
    }
}
```



### [54. Spiral Matrix](https://leetcode.cn/problems/spiral-matrix/)

```java
class Solution {

    List<Integer> res = new LinkedList<>();

    public List<Integer> spiralOrder(int[][] matrix) {
        int left = 0;
        int top = 0;
        int bottom = matrix.length - 1;
        int right = matrix[0].length - 1;
        while (top < bottom && left < right) {
            for (int i = left; i < right; i++) {
                res.add(matrix[top][i]);
            }
            for (int i = top; i < bottom; i++) {
                res.add(matrix[i][right]);
            }
            for (int i = right; i > left; i--) {
                res.add(matrix[bottom][i]);
            }
            for (int i = bottom; i > top; i--) { 
                res.add(matrix[i][left]);
            }
            left++;
            top++;
            bottom--;
            right--;
        }
        
        // 剩余单行列
        if (top == bottom) {
            // 直接left ---> right遍历完
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
        } else if (left == right){
            // 直接top ---> bottom遍历完
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
        }
        return res;
    }
}
```

[Problem solving video](https://www.bilibili.com/video/BV1jK411H7XJ?spm_id_from=333.337.search-card.all.click&vd_source=35aeaee52b15e78b11967f5ef3ce655a)

































































































































