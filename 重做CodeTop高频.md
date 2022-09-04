# 重做CodeTop高频

2022-6-27 开始

截至7-2，已经刷完近100道高频题目

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

**先移除再++**



### [146. LRU 缓存](https://leetcode.cn/problems/lru-cache/)

```java
class LRUCache {

    LinkedHashMap<Integer, Integer> map;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new LinkedHashMap<>(capacity, 0.75f, true);
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
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

题目要是get和put时间复杂度是O(1)，只有LinkedHashMap满足要求



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

优先队列存放由小到大的数，端口位置是队列的最大值，要是对头元素小于刚遍历到的num，则将num"吸"进优先队列，再进行保持对头元素最大的优先队列。



### [25. K 个一组翻转链表](https://leetcode.cn/problems/reverse-nodes-in-k-group/)

```java
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // 必须加，否则空指针异常
        if (head == null) return head;
        ListNode firstHead = head;
        ListNode firstTail = head;
        // 注意这里是 k - 1
        for (int i = 0; i < k - 1; i++) {
            firstTail = firstTail.next;
            if (firstTail == null) return firstHead;
        }
        ListNode secondHead = firstTail.next;
        firstTail.next = null;
        reverse(firstHead);
        firstHead.next = reverseKGroup(secondHead, k);
        return firstTail;
    }
	
    // 206. 反转链表
    private ListNode reverse(ListNode head) {
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

牢记下面的图：

* 先得到secondHead
* 再断开
* 反转前面一节链表
* 将前面一节链表的尾节点指向后面一节链表
* 返回

![image-20220625204402680](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220625204402680.png)



### [15. 三数之和](https://leetcode.cn/problems/3sum/)

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        HashSet<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 注意left是i + 1
            int left = i + 1;
            int right = nums.length - 1;
            // 注意这里有个while循环
            while (left < right) {
                if (nums[left] + nums[right] + nums[i] == 0) {
                    // 是加入set，而不是加入res
                    set.add(Arrays.asList(nums[left], nums[right], nums[i]));
                    left++;
                    right--;
                } else if (nums[left] + nums[right] + nums[i] < 0) {
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



### [912. 排序数组](https://leetcode.cn/problems/sort-an-array/)

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

**快速排序**

如果面试官要手写，则加上主函数：

```java
 public static void main(String[] args) {
        int[] nums = {3, 5, 1, 6, 9, 3};
        System.out.println(Arrays.toString(nums));
        quickSorting(nums,0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
```



### [53. 最大子数组和](https://leetcode.cn/problems/maximum-subarray/)

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            res = Math.max(res, sum);
        }
        return res;
    }
}
```

**连续子数组**的最大和



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



### [1. 两数之和](https://leetcode.cn/problems/two-sum/)

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[] {0, 0};
    }
}
```

不要先new出集合，要根据具体是否有元素再new

内循环的j从i + 1开始



### [102. 二叉树的层序遍历](https://leetcode.cn/problems/binary-tree-level-order-traversal/)

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return res;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> path = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                path.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(path);
        }
        return res;
    }
}
```

[程序员吴师兄](https://www.bilibili.com/video/BV1U34y1D7MN?spm_id_from=333.337.search-card.all.click&vd_source=35aeaee52b15e78b11967f5ef3ce655a)的图解很好理解

反复练习，熟能生巧



### [121. 买卖股票的最佳时机](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/)

```java
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        // 先假设第一个元素是最小值,在循环体中进行更新
        int minPrice = prices[0];
        // profit是最大利润
        int profit = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            profit = Math.max(profit, prices[i] - minPrice);
        }
        return profit;
    }
}
```

上面先假设第一天是股票价值最低的点，后面在循环体中进行更新。

为例了买卖股票这一专题的复用性，这题再使用动态规划模板进行解答:

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
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], 0 - prices[i]);
        } 
        return dp[prices.length - 1][0];
    }
}
```

二维数组的boolean值为1表示持有股票，0表示不持有股票

* 第i天持没有股票 = `max(前i-1天也没有持有股票，前i-1天持有股票，并在第i天卖掉)`

* 第i天持持有股票 = `max(前i-1天也持有股票，前i-1天没有持有股票，并在第i天买掉)`

（注意：第i天持持有股票这种情况，因为只能交易一次，所以这题在第i天卖出股票为-prices[i]）

### [122. 买卖股票的最佳时机 II](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/)

```java
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[len - 1][0];
    }
}
```

和上面一题的区别就是算第i天时无所谓可以交易几次。

当然也可以使用贪心：

```java 
class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += (prices[i] - prices[i - 1]);
            }
        }
        return profit;
    }
}
```

[K神题解](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/solution/best-time-to-buy-and-sell-stock-ii-zhuan-hua-fa-ji/)





### [20. 有效的括号](https://leetcode.cn/problems/valid-parentheses/)

```java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (Character c : chars) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
              // 这步表示只有右括号如"]}"的情况
            } else if (stack.isEmpty()) {
                return false;
            } else if (stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
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

HashSet里面存放的是ListNode



### [103. 二叉树的锯齿形层序遍历](https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/)

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        // 这题如果不判空，会出现空指针异常
        if (root == null) return res;
        queue.add(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> path =new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (flag) {
                    path.addLast(node.val);
                } else {
                    path.addFirst(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            flag = !flag;
            res.add(path);
        }
        return res;
    }
}
```



### ==[33. 搜索旋转排序数组](https://leetcode.cn/problems/search-in-rotated-sorted-array/)==

```java
class Solution {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[0] <= nums[mid]) {
                // target在[nums[0], nums[mid]]
                if (nums[0] <= target && target <= nums[mid]) {
                    r--;
                } else {
                    l++;
                }
            } else {
                // target在[nums[mid], nums[r]]
                if (nums[mid] <= target && target <= nums[r]) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return -1;
    }
}

```

二分法，三段式比较，第二段是`nums[0] <= nums[mid]`



### ==[5. 最长回文子串](https://leetcode.cn/problems/longest-palindromic-substring/)==

```java
class Solution {
    public String longestPalindrome(String s) {
        // 全局变量， 默认最大长度为1
        int maxLen = 1;
        // 全局变量，默认起始点为0
        int startIndex = 0;
        for (int i = 1; i < s.length(); i++) {
            int left = i - 1;
            int right = i + 1;
            int curLen = 1;
            // 先找出中间所有相同的元素，比如abcccccbd，先找出ccccc
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                left--;
                curLen++;
            }
            while (right <= s.length() - 1 && 
                   s.charAt(right) == s.charAt(i)) {
                right++;
                curLen++;
            }
            // 然后分别左右拱一步
            while (left >= 0 && right <= s.length() - 1 && 
                   s.charAt(left) == s.charAt(right)) {
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



### [236. 二叉树的最近公共祖先](https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/)

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return root;
        if (root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right == null) return left;
        if (left == null && right != null) return right;
        if (left != null && right != null) return root;
        return null;
    }
}
```



### [88. 合并两个有序数组](https://leetcode.cn/problems/merge-sorted-array/)

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i1 = m - 1;
        int i2 = n - 1;
        int i = m + n - 1;
        while (i1 >= 0 && i2 >= 0) {
            // 按照题目要求：将nums2合并到nums1中
            if (nums1[i1] >= nums2[i2]) {
                nums1[i] = nums1[i1];
                i--;
                i1--;
            } else {
                nums1[i] = nums2[i2];
                i--;
                i2--;
            }
        }
        // 将剩余的nums2元素全部加入nums1
        while (i2 >= 0) {
            nums1[i] = nums2[i2];
            i--;
            i2--;
        } 
    }
}
```

初始循环体的条件是`i1 >= 0 && i2 >= 0`



### [200. 岛屿数量](https://leetcode.cn/problems/number-of-islands/)

````java
class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
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
        int m = grid.length;
        int n = grid[0].length;
        if (i >= m || j >= n || i < 0 || j < 0 || grid[i][j] == '0') {
            return;
        }
        // 将它变成水(即置成'0')
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
}
````

dfs辅助函数的形参是`i和j`



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
        // 至此，curA和curB在上下两行的统一起跑线,只要一同前进，找到相交点即可。
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    // 获取链表长度
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

diff不能等于0，只能大于0



### [46. 全排列](https://leetcode.cn/problems/permutations/)

```java
class Solution {

    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) return res;
        dfs(nums, 0);
        return res;
    }

    private void dfs(int[] nums, int start) {
        if (path.size() == nums.length) {
            res.add(new LinkedList<>(path));
        }
        for (int i = start; i < nums.length; i++) {
            if (path.contains(nums[i])) {
                continue;
            }
            path.add(nums[i]);
            dfs(nums, start);
            path.removeLast();
        }
    }
}
```

或

```java
class Solution {

    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) return res;
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



### [23. 合并K个升序链表](https://leetcode.cn/problems/merge-k-sorted-lists/)

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res = null;
        for (ListNode list : lists) {
            res = merge(res, list);
        }
        return res;
    }
	
    // 合并链表
    private ListNode merge(ListNode node1, ListNode node2) {
        if (node1 == null || node2 == null) {
            return (node1 == null) ? node2 : node1;
        }
        if (node1.val < node2.val) {
            node1.next = merge(node1.next, node2);
            return node1;
        } else {
            node2.next = merge(node2.next, node1);
            return node2;
        }
    }
}
```

注意初始化：`ListNode res = null;`



### [54. 螺旋矩阵](https://leetcode.cn/problems/spiral-matrix/)

```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int left = 0;
        int right = matrix[0].length - 1;
        int up = 0;
        int down = matrix.length - 1;
        while (left <= right && up <= down) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[up][i]);
            }
            up++;
            for (int i = up; i <= down; i++) {
                res.add(matrix[i][right]);
            }
            right--;
            for (int i = right; i >= left && up <= down; i--) {
                res.add(matrix[down][i]);
            }
            down--;
            for (int i = down; i >= up && left <= right; i--) {
                res.add(matrix[i][left]);
            }
            left++;
        }
        return res;
    }
}
```

循环大前提：`left <= right && up <= down`

![image-20220627170116795](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220627170116795.png)



### ==[415. 字符串相加](https://leetcode.cn/problems/add-strings/)==

```java
class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int n1 = (i >= 0) ? num1.charAt(i) - '0' : 0;
            int n2 = (j >= 0) ? num2.charAt(j) - '0' : 0;
            int tmp = n1 + n2 + carry;
            carry = tmp / 10;
            sb.append(tmp % 10);
            i--;
            j--;
        }
        if (carry == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }
}
```



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
        return cur;
    }
}
```

HashSet里面存放的是ListNode

与[141. 环形链表](https://leetcode.cn/problems/linked-list-cycle/)的区别就在于返回值的不同，别的一模一样。



### [92. 反转链表 II](https://leetcode.cn/problems/reverse-linked-list-ii/)

```java
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        // 第 2 步：从 pre 再走 right - left + 1 步，来到 right 节点
        ListNode rightNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rightNode = rightNode.next;
        }

        // 第 3 步：切断出一个子链表（截取链表）
        // leftNode就是图中黄色部分的第一个元素；rightNode就是图中黄色元素的最后一个
        // cur就是如图所示的rightNode的下一个元素
        ListNode leftNode = pre.next;
        ListNode cur = rightNode.next;

        // 注意：切断链接
        pre.next = null;
        rightNode.next = null;

        // 第 4 步：同第 206 题，反转链表的子区间
        reverse(leftNode);

        // 第 5 步：接回到原来的链表中
        pre.next = rightNode;
        leftNode.next = cur;
        return dummy.next;
    }

    // 206. 反转链表
    private void reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return;
    }
}
```

题解来源对[官方题解](https://leetcode.cn/problems/reverse-linked-list-ii/solution/fan-zhuan-lian-biao-ii-by-leetcode-solut-teyq/)的改编

使用「206. 反转链表」的解法，反转 left 到 right 部分以后，再拼接起来。我们还需要记录 left 的前一个节点，和 right 的后一个节点。如图所示：**pre是前驱节点，curr是后驱节点**

![image-20220706092346358](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220706092346358.png)



### [25. K 个一组翻转链表](https://leetcode.cn/problems/reverse-nodes-in-k-group/)

```java
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // 必须加，否则空指针异常
        if (head == null) return head;
        ListNode firstHead = head;
        ListNode firstTail = head;
        // 注意这里是 k - 1
        for (int i = 0; i < k - 1; i++) {
            firstTail = firstTail.next;
            if (firstTail == null) return firstHead;
        }
        ListNode secondHead = firstTail.next;
        firstTail.next = null;
        reverse(firstHead);
        firstHead.next = reverseKGroup(secondHead, k);
        return firstTail;
    }
	
    // 206. 反转链表
    private ListNode reverse(ListNode head) {
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

牢记下面的图：

* 先得到secondHead
* 再断开
* 反转前面一节链表
* 将前面一节链表的尾节点指向后面一节链表
* 返回

![image-20220625204402680](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220625204402680.png)



### [300. 最长递增子序列](https://leetcode.cn/problems/longest-increasing-subsequence/)

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
```

[题解](https://www.bilibili.com/video/BV19b4y1R7K3?spm_id_from=333.1007.top_right_bar_window_history.content.click&vd_source=35aeaee52b15e78b11967f5ef3ce655a)

注意内循环是 `j < i`



### [143. 重排链表](https://leetcode.cn/problems/reorder-list/)

```java
class Solution {
    public void reorderList(ListNode head) {
        // 将所有元素全部压入栈中
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        ListNode tmp = head;
        while (tmp != null) {
            // 暂存链表尾部的节点
            ListNode tail = stack.pop();
            // 暂存链表前面的节点
            ListNode former = tmp.next;
            // 链表的节点树数为奇数或者偶数均适用
            if (tail == former || tail.next == former) {
                tail.next = null;
                return;
            }
            tmp.next = tail;
            tail.next = former;
            tmp = former;
        }
    }
}
```

<img src="http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220627202349164.png" alt="image-20220627202349164" style="zoom:150%;" />



### [124. 二叉树中的最大路径和](https://leetcode.cn/problems/binary-tree-maximum-path-sum/)

```java
class Solution {

    // 定义全局的最大值
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
        // root、root+left、root+right这三者比较，得出临时的最大值
        int tmp = Math.max(root.val, Math.max(root.val + left, root.val + right));
        // 再加入root + left + right 进行比较
        res = Math.max(res, Math.max(tmp, root.val + left + right));
        return tmp;
    }
}
```

有下面四种情况出现最大路径和：

* root单节点
* root.val + left
* root.val  + right
* root.val + left + right

本题先在前三个里面找出最大值，再加入最后一个得出最大值res，但注意**dfs返回值是前三者的最大值**

**在dfs时，有一个副作用，就是更新res值，也是我们想要的，我们并不期待dfs的返回值tmp，但我们又不得不让dfs一定要有返回值**这个思想类似于[543. 二叉树的直径](https://leetcode.cn/problems/diameter-of-binary-tree/)

​	

### [704. 二分查找](https://leetcode.cn/problems/binary-search/)

```java
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left++;
            } else {
                right--;
            }
        }
        return -1;
    }
}
```

mid定义在内部，而且是`int mid = (left + right) / 2;`



### [94. 二叉树的中序遍历](https://leetcode.cn/problems/binary-tree-inorder-traversal/)

```java
class Solution {
    List<Integer> res = new LinkedList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return res;
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        res.add(root.val);
        dfs(root.right);
    }
}
```



### [199. 二叉树的右视图](https://leetcode.cn/problems/binary-tree-right-side-view/)

```java
class Solution {
    List<Integer> res = new LinkedList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return res;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null) {	
                    queue.add(node.right);
                }
                if (i == size - 1) {
                    res.add(node.val);
                }
            }
        }
        return res;
    }
}
```

记住与层序遍历的区别在于不用new path和下面的判别条件：

```java
if (i == size - 1) {
    res.add(node.val);
}
```



### [232. 用栈实现队列](https://leetcode.cn/problems/implement-queue-using-stacks/)

```java
class MyQueue {

    Stack<Integer> inStack;
    Stack<Integer> outStack;

    public MyQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }
    
    public void push(int x) {
        inStack.push(x);
    }
    
    public int pop() {
        while (outStack.isEmpty()) {
            in2out();
        }
        return outStack.pop();
    }
    
    public int peek() {
        while (outStack.isEmpty()) {
            in2out();
        }
        return outStack.peek();
    }
    
    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    // 辅助函数
    private void in2out() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
    }
}
```

记住辅助函数：A栈不为空，则将A栈栈顶元素加进B栈

* **push：**直接压入A栈
* **pop：**pop出B栈（注意判空）
* **peek：**peek出B栈（注意判空）
* **empty：**A栈和B栈同时不为空才true



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
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
```

两个注意点：

1. 不要忘记判空： `if (fast == null) return head.next;`
2. while循环体中是`fast.next != null`



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
            // 没有重叠，相当于在[[4, 6],[8, 10],[15, 18]]中，[4, 6]中的4大于[1, 3]中的第二个元素，也就是3，直接将[1,3]加入res
            if (intervals[i][0] > cur[1]) {
                res.add(cur);
                // 更新cur为[8, 10]
                cur = intervals[i];
            } else {
                // 有重叠，比如在[[1, 3],[2, 6],[8, 10],[15, 18]]中取二维数组的第二个（因为从0开始算的）的第二位即更新[2, 6]中cur[1] = 3
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



### [69. x 的平方根 ](https://leetcode.cn/problems/sqrtx/)

```java
class Solution {
    public int mySqrt(int x) {
        int left = 1;
        // 加速，将x中部位置加1作为右节点
        int right = x / 2 + 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid * mid == x) {
                return mid;
            } else if (mid > x / mid){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}
```

两个注意点：

* 初始值right定义在 `int right = x / 2 + 1;`
* 中途判断时：是`right = mid - 1;` 而不是 `right--`,因为会造成超时，同理 `left = mid  +1`



### [82. 删除排序链表中的重复元素 II](https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/)

```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        Map<Integer, Integer> map = new HashMap<>();
        ListNode cur = head;
        while (cur != null) {
            if (map.containsKey(cur.val)) {
                map.put(cur.val, map.get(cur.val) + 1);
            } else {
                map.put(cur.val, 1);
            }
            cur = cur.next;
        }
        ListNode dummy = new ListNode(-1);
        ListNode tmp = dummy;
        cur = head;
        while (cur != null) {
            if (map.get(cur.val) == 1) {
                // 从map中获得到唯一元素的话，就直接构造新的节点进行连接
                tmp.next = new ListNode(cur.val);
                tmp = tmp.next;
            }
            // cur是无论如何都要移动的
            cur = cur.next;
        }
        return dummy.next;
    }
}
```

第二次遍历，注意tmp、cur的运用

* tmp表示dummy的下一个节点

* cur依旧用来遍历链表



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
        for (int j = 1; j < n + 1; j++) {
            dp[0][j] = j;
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



### [148. 排序链表](https://leetcode.cn/problems/sort-list/)

```java
class Solution {
    public ListNode sortList(ListNode head) {
        return sort(head, null);
    }

    // 排序链表(两个形参)
    private ListNode sort(ListNode start, ListNode end) {
        if (start == end) return start;
        ListNode slow = start;
        ListNode fast = start;
        // fast移动到队尾，slow移到队中
        while (fast != end && fast.next != end) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        ListNode l2 = sort(slow.next, end);
        slow.next = null;
        ListNode l1 = sort(start, slow);
        return merge(l1, l2);
    }

    // 合并链表(两个形参)
    private ListNode merge(ListNode node1, ListNode node2) {
        if (node1 == null || node2 == null) {
            return (node1 == null) ? node2 : node1;
        }
        if (node1.val < node2.val) {
            node1.next = merge(node1.next, node2);
            return node1;
        } else {
            node2.next = merge(node2.next, node1);
            return node2;
        }
    }
}
```

归并排序的思想：将先练先拆分，分别**排序**，再**合并**



### [2. 两数相加](https://leetcode.cn/problems/add-two-numbers/)

```java
class Solution {

    private ListNode dummyHead = new ListNode(-1);
    private ListNode tail = dummyHead;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode iterator1 = l1, iterator2 = l2;
        int carry = 0;
        // 自左向右求和进位
        while (iterator1 != null && iterator2 != null) {
            int val = (iterator1.val + iterator2.val + carry) % 10;
            carry = (iterator1.val + iterator2.val + carry) / 10;
            appendNode(new ListNode(val));
            iterator1 = iterator1.next;
            iterator2 = iterator2.next;
        }

        while (iterator1 != null) {
            int val = (iterator1.val + carry) % 10;
            carry = (iterator1.val + carry) / 10;
            appendNode(new ListNode(val));
            iterator1 = iterator1.next;
        }

        while (iterator2 != null) {
            int val = (iterator2.val + carry) % 10;
            carry = (iterator2.val + carry) / 10;
            appendNode(new ListNode(val));
            iterator2 = iterator2.next;
        }

        if (carry != 0) {
            appendNode(new ListNode(carry));
        }
        return dummyHead.next;
    }

    //  将节点加入链表
    private void appendNode(ListNode node) {
        tail.next = node;
        tail = node;
    }
}
```



### [剑指 Offer 22. 链表中倒数第k个节点](https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/)

```java
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
```



### [8. 字符串转换整数 (atoi)](https://leetcode.cn/problems/string-to-integer-atoi/)

```java
class Solution {
    public int myAtoi(String s) {
        if (s == null) return 0;
        s = s.trim();
        if (s.isEmpty()) {
            return 0;
        }
        int index = 0;
        int sign = 1;
        char firstChar = s.charAt(index);
        if (firstChar == '+') {
            index++;
        } else if (firstChar == '-') {
            sign = -1;
            index++;
        } else if (!Character.isDigit(firstChar)){
            return 0;
        }
        long num = 0;
        long res = num * sign;
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            num = num * 10 + Integer.parseInt(String.valueOf(s.charAt(index)));
            res = num * sign;
            if (res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (res < Integer.MIN_VALUE) return Integer.MIN_VALUE;
            index++;
        }
        return (int)(num * sign);
    }
}
```

[书森学院](https://www.bilibili.com/video/BV1bz4y1Q7Gq?spm_id_from=333.337.search-card.all.click&vd_source=35aeaee52b15e78b11967f5ef3ce655a)

common case特别多的一道题



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
                // 找到了第一个递减的排列，比如[1, 5, 4, 3]中找到了5 -> 1这个递减排列，那么只要在1后面找到一个比1大的最小的数，也就是3，再将1和3交换，得到[3, 5, 4, 1]
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

[程序员刀刀](https://www.bilibili.com/video/BV1SE411e7gk?spm_id_from=333.1007.top_right_bar_window_history.content.click)

自右向左逆序找出第一个**递减序列**，然后将其与后面的最小元素交换。



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

[忍者算法](https://www.bilibili.com/video/BV1th411x7tS?spm_id_from=333.337.search-card.all.click&vd_source=35aeaee52b15e78b11967f5ef3ce655a)

回溯+剪枝

dfs分两种情况：

* 左括号少于总括号对数；
* 右括号数少于左括号数



### [1143. 最长公共子序列](https://leetcode.cn/problems/longest-common-subsequence/)

```java
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        // 初始化
        dp[0][0] = 0;
        for (int i = 1; i < m; i++) {
            dp[i][0] = 0;
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
```



### [151. 颠倒字符串中的单词](https://leetcode.cn/problems/reverse-words-in-a-string/)

```java
class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        s = s.trim();
        String[] strs = s.split(" ");
        for (int i = strs.length - 1; i >= 0; i--) {
            if (strs[i].equals("")) {
                continue;
            } else {
                sb.append(strs[i] + " ");
            }
        }
        return sb.toString().trim();
    }
}
```

```java
if (strs[i].equals("")) {
    continue;
}
```

这个操作是为了防止输入两个空格，如：`"a good   example"`,我们预计输出为 `"example good a"`, 但有可能输出 `"example   good a"` 的情况的发生。

6月22号阿里本地生活二面做到类似这题: **翻转所有的字符**：

```java
class Solution {
    public static void main(String[] args) {
        String s = "abc def ghi xyz ";
        s = reverse(s);
        System.out.println(s);
    }

    private static String reverse(String s) {
        StringBuilder sb =new StringBuilder();
        s = s.trim();
        char[] chars = s.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                sb.append(" ");
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
```



### [144. 二叉树的前序遍历](https://leetcode.cn/problems/binary-tree-preorder-traversal/)

```java
class Solution {
    List<Integer> res = new LinkedList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return res;
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        res.add(root.val);
        dfs(root.left);
        dfs(root.right);
    }
}
```



### [239. 滑动窗口最大值](https://leetcode.cn/problems/sliding-window-maximum/)

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 双端队列(注意：存的是下标，而不是元素)
        Deque<Integer> queue = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        for (int i = 0 ; i < nums.length; i++) {
            // 清理超期元素(利用下标来判别,判别的是左侧元素)
            if (!queue.isEmpty() && i - k == queue.peekFirst()) {
                queue.removeFirst();
            }
            // 删除所有比新入队元素小的旧元素(判别的是右侧的元素, 其中nums[i]就是新入队的元素)
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) {
                queue.removeLast();
            }
            // 尾部元素(其实是指针入队)入队
            queue.add(i);
            // 获取滑动窗口的最大值(比如k为3时，只有当i>=2时才进行添加操作)
            if (i >= k - 1) {
                res[index++] = nums[queue.peek()];
            }
        }
        return res;
    }
}
```

[育树霖疯](https://www.bilibili.com/video/BV1wb4y1h79v?spm_id_from=666.7.top_right_bar_window_history.content.click)

利用**双端队列**，注意存的不是元素，而是元素的**下标**



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

递归 [跑马拉松的程序员](https://www.bilibili.com/video/BV1Bq4y1b7Eu?spm_id_from=333.337.search-card.all.click&vd_source=35aeaee52b15e78b11967f5ef3ce655a)



### [110. 平衡二叉树](https://leetcode.cn/problems/balanced-binary-tree/)

```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(getDepth(root.left) - getDepth(root.right)) <= 1 
            && isBalanced(root.left) && isBalanced(root.right);
    }

    private int getDepth(TreeNode root) {
        if (root == null) return 0;
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        return Math.max(left, right) + 1;
    }
}
```



### [129. 求根节点到叶节点数字之和](https://leetcode.cn/problems/sum-root-to-leaf-numbers/)

```java
class Solution {
    int res = 0;
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode root, int tmp) {
        if (root == null) return;
        // tmp表示这层结束后，所能得到的值
        tmp = tmp * 10 + root.val;
        // 遍历到叶子节点，将其加入res
        if (root.left == null && root.right == null) {
            res += tmp;
        }
        dfs(root.left, tmp);
        dfs(root.right, tmp);
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



### [322. 零钱兑换](https://leetcode.cn/problems/coin-change/)

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp  = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                // 只有金钱总数i大于等于金币面值1、2、5才进行dp
                if (coins[j] <= i) {
                    // 比如dp[i]为11元，那么dp[i - coins[i]]就等于10或9或者6，这三个都是只要加一个硬币就能得到11
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }  
            }
        }
        return (dp[amount] == Integer.MAX_VALUE / 2) ? -1 : dp[amount];
    }
}
```



### [155. 最小栈](https://leetcode.cn/problems/min-stack/)

```java
class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> min_stack;
    public MinStack() {
        stack = new Stack<>();
        min_stack = new Stack<>();
    }
    public void push(int x) {
        stack.push(x);
        if(min_stack.isEmpty() || x <= min_stack.peek())
            min_stack.push(x);
    }
    public void pop() {
        if(stack.pop().equals(min_stack.peek()))
            min_stack.pop();
    }
    public int top() {
        return stack.peek();
    }
    public int getMin() {
        return min_stack.peek();
    }
}
```



### [543. 二叉树的直径](https://leetcode.cn/problems/diameter-of-binary-tree/)

```java
class Solution {

    int max = Integer.MIN_VALUE;

    public int diameterOfBinaryTree(TreeNode root) {
        getDepth(root);
        return max;
    }

    // 计算二叉树的深度
    private int getDepth(TreeNode root) {
        if (root == null) return 0;
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        // left + right + 1表示所有的node节点数量，减1表示比如一共7个节点，经历6步，也就是直径是6
        max = Math.max(max, left + right + 1 - 1);
        // 加1代表root节点
        return Math.max(left, right) + 1;
    }
}
```

[书森学院](https://www.bilibili.com/video/BV12L411V7Zf?spm_id_from=333.337.search-card.all.click&vd_source=35aeaee52b15e78b11967f5ef3ce655a)的最后几句很有深意，意思如下：

在计算树的最大深度时，有一个副作用，就是更新max值，也是我们想要的；

也就是说用不到辅助函数getDepth的返回值，而是用到这个辅助函数来更新max值。这个也就类似于[124. 二叉树中的最大路径和](https://leetcode.cn/problems/binary-tree-maximum-path-sum/)这题



### [113. 路径总和 II](https://leetcode.cn/problems/path-sum-ii/)

```java
class Solution {

    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) return res;
        dfs(root, targetSum);
        return res;
    }

    private void dfs(TreeNode root, int targetSum) {
        if (root == null) return;
        path.add(root.val);
        // 到达根节点
        if (root.left == null && root.right == null && root.val == targetSum) {
            res.add(new LinkedList<>(path));
            path.removeLast();
            return;
        }
        // 递归左右子树
        dfs(root.left, targetSum - root.val);
        dfs(root.right, targetSum - root.val);
        path.removeLast();
    }
}
```



### [78. 子集](https://leetcode.cn/problems/subsets/)

```java
class Solution {

    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null) return res;
        dfs(nums, 0);
        return res;
    }

    private void dfs(int[] nums, int start) {
        if (nums == null) return;
        res.add(new LinkedList<>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, i + 1);
            path.removeLast();
        }
    }
}
```



### [32. 最长有效括号](https://leetcode.cn/problems/longest-valid-parentheses/)

```java
class Solution {
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        Arrays.fill(dp, 0);
        int res = 0;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
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

动态规划 关键是确定状态转移方程

[子烁爱学习](https://www.bilibili.com/video/BV1Ct4y197M3?spm_id_from=333.337.search-card.all.click&vd_source=35aeaee52b15e78b11967f5ef3ce655a)



### [98. 验证二叉搜索树](https://leetcode.cn/problems/validate-binary-search-tree/)

```java
class Solution {

    LinkedList<Integer> res = new LinkedList<>(); 

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

利用**中序遍历**：组成**左根右**的结构，然后遍历集合，**前面比后面大的就直接返回false**。



### [101. 对称二叉树](https://leetcode.cn/problems/symmetric-tree/)

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return help(root.left, root.right);
    }

    private boolean help(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val != root2.val) return false;
        return help(root1.left, root2.right) && help(root1.right, root2.left);
    }
}
```



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
        for (int j = 1; j < n; j++){
            dp[0][j] = dp[0][j - 1] + grid[0][j];
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

状态转移方程：`dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];`



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
        // 重新将cur定义到开始的节点
        cur = head;
        while (cur != null) {
            if (stack.pop() != cur.val) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }
}
```



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

因为可以重复使用，所以还是i，比如虽然只有一个2，但可以用几次

所以回溯带入的是i，不是i + 1



### [169. 多数元素](https://leetcode.cn/problems/majority-element/)

```java
class Solution {
    public int majorityElement(int[] nums) {
        // 计数器
        int count = 0;
        // 假设第一个元素是多数元素
        int tmp = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == tmp) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    tmp = nums[i + 1];
                }
            }
        }
        return tmp;
    }
}
```



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



### [112. 路径总和](https://leetcode.cn/problems/path-sum/)

```java
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            return (root.val - targetSum) == 0;
        }
        return hasPathSum(root.left, targetSum - root.val) 
        || hasPathSum(root.right, targetSum - root.val);
    }
}
```



### [718. 最长重复子数组](https://leetcode.cn/problems/maximum-length-of-repeated-subarray/)

```java
class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int res = 0;
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        for (int i = 0; i < m; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}
```



### [34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/)

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int l = 0;
        int r = nums.length - 1; //二分范围
        while (l < r)                    //查找元素的开始位置
        {
            int mid = (l + r) / 2;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (nums[r] != target) {
            return new int[]{-1, -1}; //查找失败
        }
        int L = r;
        l = 0;
        r = nums.length - 1;     //二分范围
        while (l < r)                    //查找元素的结束位置
        {
            int mid = (l + r + 1) / 2;
            if (nums[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return new int[]{L, r};
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



### [221. 最大正方形](https://leetcode.cn/problems/maximal-square/)

```java
class Solution {
    public int maximalSquare(char[][] matrix) {
        int max = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        if (matrix == null || m == 0 || n == 0) {
            return max;
        }
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    }
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }
}
/**
    dp[i][j]表示以第i行第j列为右下角所能构成的最大正方形边长, 则递推式为: 
    dp[i][j] = 1 + min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]);
**/
```

[程序员刀刀](https://www.bilibili.com/video/BV14J411v7Ks?spm_id_from=333.337.search-card.all.click&vd_source=35aeaee52b15e78b11967f5ef3ce655a)



### [162. 寻找峰值](https://leetcode.cn/problems/find-peak-element/)

```java
class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

```



### [14. 最长公共前缀](https://leetcode.cn/problems/longest-common-prefix/)

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null) return "";
        String tmp = strs[0];
        for (int i = 1; i < strs.length; i++) {
            tmp = compare(tmp, strs[i]);
        }
        return tmp;
    }

    private String compare(String str1, String str2) {
        int len = Math.min(str1.length(), str2.length());
        for (int i = 0; i < len;) {
            if (str1.charAt(i) == str2.charAt(i)) {
                i++;
            } else {
                return str1.substring(0, i);
            }
        }
        return str1.substring(0, len);
    }
}
```



### [240. 搜索二维矩阵 II](https://leetcode.cn/problems/search-a-2d-matrix-ii/)

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0;
        int j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
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



### [83. 删除排序链表中的重复元素](https://leetcode.cn/problems/remove-duplicates-from-sorted-list/)

```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}
```



### [82. 删除排序链表中的重复元素 II](https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/)

```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        Map<Integer, Integer> map = new HashMap<>();
        ListNode cur = head;
        while (cur != null) {
            if (map.containsKey(cur.val)) {
                map.put(cur.val, map.get(cur.val) + 1);
            } else {
                map.put(cur.val, 1);
            }
            cur = cur.next;
        }
        ListNode dummy = new ListNode(-1);
        ListNode tmp = dummy;
        cur = head;
        while (cur != null) {
            if (map.get(cur.val) == 1) {
                // 从map中获得到唯一元素的话，就直接构造新的节点进行连接
                tmp.next = new ListNode(cur.val);
                tmp = tmp.next;
            }
            // cur是无论如何都要移动的
            cur = cur.next;
        }
        return dummy.next;
    }
}
```

第二次遍历，注意tmp、cur的运用

* tmp表示dummy的下一个节点

* cur依旧用来遍历链表



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

[题解](https://www.bilibili.com/video/BV1eF411s71H?spm_id_from=333.337.search-card.all.click&vd_source=35aeaee52b15e78b11967f5ef3ce655a)

先去重，否则可能超时



### [394. 字符串解码](https://leetcode.cn/problems/decode-string/)

[题解](https://leetcode.cn/problems/decode-string/solution/java-di-gui-si-lu-qing-xi-dai-ma-yi-dong-t6mn/)

能力不足，以后再看



### [695. 岛屿的最大面积](https://leetcode.cn/problems/max-area-of-island/)

```java
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfs(grid, i, j));
                }
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        return dfs(grid, i + 1, j) + dfs(grid, i - 1, j) 
            + dfs(grid, i, j + 1) + dfs(grid, i, j - 1) + 1;
    }
}
```

这里是感染成数字的0，而不是字符'0'



### [152. 乘积最大子数组](https://leetcode.cn/problems/maximum-product-subarray/)

```java
class Solution {
    public int maxProduct(int[] nums) {
        int len = nums.length;
        int[] dpMax = new int[len];
        int[] dpMin = new int[len];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < len; i++) {
            dpMax[i] = Math.max(nums[i], Math.max(dpMin[i - 1] * nums[i], dpMax[i -1] * nums[i]));
            dpMin[i] = Math.min(nums[i], Math.min(dpMin[i - 1] * nums[i], dpMax[i -1] * nums[i]));
            if (max < dpMax[i]) {
                max = dpMax[i];
            }
        }
        return max;
    }
}
```

因为要考虑负数，所以有两个dp，分别是前i项的最大值和最小值，所以需要比较三个地方的值来确定： `nums[i]`   `nums[i] * dpMax[i - 1]`   `nums[i] * dpMin[i - 1]`

但最后的那个max和dp[i]比较大小，我没看明白

[跑马拉松的程序员](https://www.bilibili.com/video/BV1PP4y1M7hD?spm_id_from=333.337.search-card.all.click&vd_source=35aeaee52b15e78b11967f5ef3ce655a)



### [153. 寻找旋转排序数组中的最小值](https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/)

```java
class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 如果中值 > 右值，则最小值在右半边，可以收缩左边界。
            if (nums[mid] > nums[right]) {           
                left++;
            } else {                                
                right--;
            }
        }
        return nums[left];
    }
}
```

二分法

[题解](https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/solution/er-fen-cha-zhao-wei-shi-yao-zuo-you-bu-dui-cheng-z/)



### [24. 两两交换链表中的节点](https://leetcode.cn/problems/swap-nodes-in-pairs/)

```java
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }
}
```

[忍者算法](https://leetcode.cn/problems/swap-nodes-in-pairs/solution/ren-zhe-suan-fa-quan-wang-zui-qing-xi-yi-lb4w/) 

递归图解：

![image-20220702165019689](http://milessg.oss-cn-beijing.aliyuncs.com/img/image-20220702165019689.png)



### [662. 二叉树最大宽度](https://leetcode.cn/problems/maximum-width-of-binary-tree/)

```java
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        // 避免异常的产生
        if (root == null) {
            return 0;
        }
        // 创建一个队列来进行广度优先遍历,注意这个地方就不要使用Queue<TreeNode> queue=new LinkedList<TreeNode>();
        // 因为父类不能调用子类的方法：getLast getFirst
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        // 创建一个变量来保存最大宽度
        int res = 0;
        // 将根节点入队列
        queue.add(root);
        while (!queue.isEmpty()) {
            // 记录当前队列中的个数
            int size = queue.size();
            //创建一个变量来计算每层的宽度
            int width = queue.getLast().val - queue.getFirst().val + 1;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                    node.left.val = node.val * 2 + 1;
                }
                if (node.right != null) {
                    queue.add(node.right);
                    node.right.val = node.val * 2 + 2;
                }
            }
            // 求出每一层的宽度
            // 通过比较找除最大宽度
            if (width > res) {
                res = width;
            }
        }
        return res;
    }
}
```

BFS 广度优先遍历

水平有限，下次再来看（题目答案[来自](https://leetcode.cn/problems/maximum-width-of-binary-tree/solution/bfswan-quan-er-cha-shu-xing-zhi-kan-wan-qmguc/)）



### [122. 买卖股票的最佳时机 II](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/)

```java
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[len - 1][0];
    }
}
```

和上面一题的区别就是算第i天时无所谓可以交易几次。

当然也可以使用贪心：

```java 
class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i - 1] > 0) {
                profit += (prices[i] - prices[i - 1]);
            }
        }
        return profit;
    }
}
```

[K神题解](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/solution/best-time-to-buy-and-sell-stock-ii-zhuan-hua-fa-ji/)



### [198. 打家劫舍](https://leetcode.cn/problems/house-robber/)

```java
class Solution {
    public int rob(int[] nums) {
        if (nums.length <= 1) return (nums.length == 0) ? 0 : nums[0];
        int res = Math.max(nums[0], nums[1]);
        // dp[i]表示截至第i家为止，能够拿到的最大的钱数
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

### [213. 打家劫舍 II](https://leetcode.cn/problems/house-robber-ii/)

和上一题的区别是这题是**带环**的，即首位相连的，所以将其分为三种情况讨论，分好类以后，进行和第一题一模一样的操作

````java
class Solution {
    public int rob(int[] nums) {
        /*
        情况分类：
            1. 不抢头，不强尾
            2. 抢头，不强尾
            3. 不抢头，抢尾
        */
        if (nums.length <= 1) return (nums.length == 0) ? 0 : nums[0];
        // 1. 不抢头，不强尾
        int[] nums1 = Arrays.copyOfRange(nums, 1, nums.length - 1);
        // 2. 抢头，不强尾
        int[] nums2 = Arrays.copyOfRange(nums, 0, nums.length - 1);
        // 3. 不抢头，抢尾
        int[] nums3 = Arrays.copyOfRange(nums, 1, nums.length);

        return Math.max(Math.max(help(nums1),help(nums2)),help(nums3));
    }

    // 下面就是纯纯复刻打家劫舍第一题的代码了
    private int help(int[] nums) {
        if (nums.length <= 1) return (nums.length == 0) ? 0 : nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        int res = Math.max(nums[0], nums[1]); // 最终结果
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
````

**Arrays.copyOfRange() 方法用于复制，左闭右开**



### [136. 只出现一次的数字](https://leetcode.cn/problems/single-number/)

```java
class Solution {
    public int singleNumber(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        Set<Integer> set = map.keySet();
        for (int num : set) {
            if (map.get(num) == 1) {
                return num;
            }
        }
        return -1;
    }
}
```



### [209. 长度最小的子数组](https://leetcode.cn/problems/minimum-size-subarray-sum/)

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int right = 0;
        int windowSum = 0;
        int res = Integer.MAX_VALUE;
        while (right < nums.length) {
            // 扩大窗口
            windowSum += nums[right];
            right++;
            while (windowSum >= target && left < right) {
                // 窗口内的值超过target时，就更新答案res，并且缩小窗口
                res = Math.min(res, right - left);
                windowSum -= nums[left];
                left++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
```



### [26. 删除有序数组中的重复项](https://leetcode.cn/problems/remove-duplicates-from-sorted-array/)

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }
}
```

李文捷滴滴一面



### [287. 寻找重复数](https://leetcode.cn/problems/find-the-duplicate-number/)

```java
class Solution {
    public int findDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return nums[i];
            } else {
                set.add(nums[i]);
            }
        }
        return -1;
    }
}
```





# 剑指offer

### [剑指 Offer 03. 数组中重复的数字](https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/)

```java
class Solution {
    public int findRepeatNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            } else {
                set.add(num);
            }
        }
        return -1;
    }
}
```

根据题意，返回一个num即可



### [剑指 Offer 04. 二维数组中的查找](https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/)

```java
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length;
        int n = matrix[0].length;
        // 从右上角开始
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }
}
```

从**右上角**开始遍历，即`(0, n - 1)`开始



### [剑指 Offer 05. 替换空格](https://leetcode.cn/problems/ti-huan-kong-ge-lcof/)

```java
class Solution {
    public String replaceSpace(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (Character c : chars) {
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
```



### [剑指 Offer 06. 从尾到头打印链表](https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/)

```java
class Solution {
    public int[] reversePrint(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        
        int[] res = new int[stack.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = stack.pop().val;
        }
        return res;
    }
}
```



### [剑指 Offer 07. 重建二叉树](https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/)



### [剑指 Offer 09. 用两个栈实现队列](https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)

```jaVA
class CQueue {

    Stack<Integer> inStack;
    Stack<Integer> outStack;

    public CQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }
    
    public void appendTail(int value) {
        inStack.push(value);
    }
    
    public int deleteHead() {
        while (inStack.isEmpty() && outStack.isEmpty()) {
            return -1;
        }
        // 以下两个不可交换
        while (!outStack.isEmpty()) {
            return outStack.pop();
        }
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
        return outStack.pop();
    }
}
```



### [剑指 Offer 10- I. 斐波那契数列](https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof/)

```java
class Solution {
    public int fib(int n) {
        if (n == 0) return 0;
        int[] dp = new int[1000];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return dp[n];
    }
}
```



### [剑指 Offer 10- II. 青蛙跳台阶问题](https://leetcode.cn/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/)

```java
class Solution {
    public int numWays(int n) {
        int[] dp = new int[2000];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000007;
        }
        return dp[n];
    }
}
```



### [剑指 Offer 11. 旋转数组的最小数字](https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/)

```java
class Solution {
    public int minArray(int[] numbers) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            int m = (i + j) / 2;
            if (numbers[m] > numbers[j]) {
                i = m + 1;
            } else if (numbers[m] > numbers[j]) {
                j = m;
            } else {
                j--;
            }
        }
        return numbers[j];
    }
}
```

`numsbers[m]和numbers[j]`进行比较



### [剑指 Offer 12. 矩阵中的路径](https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof/)

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

[程序员吴师兄](https://www.bilibili.com/video/BV1544y147oT?spm_id_from=333.337.search-card.all.click&vd_source=35aeaee52b15e78b11967f5ef3ce655a)

回溯 全文背诵



### [剑指 Offer 13. 机器人的运动范围](https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/)



### [剑指 Offer 14- I. 剪绳子](https://leetcode.cn/problems/jian-sheng-zi-lcof/)

```java
class Solution {
    public int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int a = n / 3, b = n % 3;
        if (b == 0) {
            return (int) Math.pow(3, a);
        }
        if (b == 1) {
            return (int) Math.pow(3, a - 1) * 4;
        }
        return (int) Math.pow(3, a) * 2;
    }
}
```

### [剑指 Offer 14- II. 剪绳子 II](https://leetcode.cn/problems/jian-sheng-zi-ii-lcof/)

```java
class Solution {
    public int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int b = n % 3, p = 1000000007;
        long rem = 1, x = 3;
        for (int a = n / 3 - 1; a > 0; a /= 2) {
            if (a % 2 == 1) {
                rem = (rem * x) % p;
            }
            x = (x * x) % p;
        }
        if (b == 0) {
            return (int) (rem * 3 % p);
        }
        if (b == 1) {
            return (int) (rem * 4 % p);
        }
        return (int) (rem * 6 % p);
    }
}
```



### [剑指 Offer 15. 二进制中1的个数](https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/)

```java
public class Solution {
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res += (n & 1);
            n >>>= 1;
        }
        return res;
    }
}
```

`>>>` 无符号右移



### [剑指 Offer 16. 数值的整数次方](https://leetcode.cn/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/)

```java
class Solution {
    public double myPow(double x, int n) {
        if(x == 0) return 0;
        long b = n;
        double res = 1.0;
        if(b < 0) {
            x = 1 / x;
            b = -b;
        }
        while(b > 0) {
            if((b & 1) == 1) res *= x;
            x *= x;
            b >>= 1;
        }
        return res;
    }
}
```



### [剑指 Offer 17. 打印从1到最大的n位数](https://leetcode.cn/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/)

```java
class Solution {
    public int[] printNumbers(int n) {
        int[] res = new int[(int)(Math.pow(10, n) - 1)];
        for (int i = 0; i < res.length; i++) {
            res[i] = i + 1;
        }
        return res;
    }
}
```



### [剑指 Offer 18. 删除链表的节点](https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/)

```java
class Solution {
    public ListNode deleteNode(ListNode head, int val) {
        ListNode slow = head;
        ListNode fast = head.next;
        // 这个base case要加
        if (slow.val == val) { 
           return head.next;  
        }
        while (fast != null) {
            if (fast.val == val) {
                slow.next = fast.next;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return head;
    }
}
```

**删除链表用双指针**，这题的fast比slow的初始位置就快一位，



### [剑指 Offer 19. 正则表达式匹配](https://leetcode.cn/problems/zheng-ze-biao-da-shi-pi-pei-lcof/)



### [剑指 Offer 21. 调整数组顺序使奇数位于偶数前面](https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/)

```java
class Solution {
    public int[] exchange(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            // 向右查找：遇到奇数的话就直接i++，直到遇见偶数
            if ((i < j) && nums[i] % 2 == 1) {
                i++;
            }
            // 向左查找：遇到偶数的话就直接j--，直到遇见奇数
            if ((i < j) && nums[j] % 2 == 0) {
                j--;
            }
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        return nums;
    }
}
```

注意内层循环也要加上`i < j`的判断



### [剑指 Offer 22. 链表中倒数第k个节点](https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/)

```java
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
```



### [剑指 Offer 24. 反转链表](https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/)

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



### [剑指 Offer 25. 合并两个排序的链表](https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/)

```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return (l1 == null) ? l2 : l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l2.next, l1);
            return l2;
        }
    }
}
```



### [剑指 Offer 26. 树的子结构](https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/)

```java
class Solution {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (dfs(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    private boolean dfs(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null) return false;
        if (A.val != B.val) return false;
        return dfs(A.left, B.left) && dfs(A.right, B.right);
    }
}
```

**dfs辅助函数**遇到如下三种情况返回：

* 当节点 B为空：说明树 B已匹配完成（越过叶子节点），因此返回 true ；
* 当节点 A为空：说明已经越过树 A 叶子节点，即匹配失败，返回 false；
* 当节点 A和 B 的值不同：说明匹配失败，返回 false ；

**主函数：**

**特例处理：** 当 树 A 为空 或 树 B 为空 时，直接返回 false ；
**返回值：** 若树 BB 是树 AA 的子结构，则必满足以下三种情况之一，因此用或 || 连接；

* 以 节点 A 为根节点的子树 包含树 B ，对应 recur(A, B)；
* 树 B 是 树 A 左子树 的子结构，对应 isSubStructure(A.left, B)；
* 树 B 是 树 A 右子树 的子结构，对应 isSubStructure(A.right, B)；



### [剑指 Offer 27. 二叉树的镜像](https://leetcode.cn/problems/er-cha-shu-de-jing-xiang-lcof/)

```java
class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return root;
        TreeNode tmp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tmp);
        return root;
    }
}
```

将`root.left`作为临时tmp节点



### [剑指 Offer 28. 对称的二叉树](https://leetcode.cn/problems/dui-cheng-de-er-cha-shu-lcof/)

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return dfs(root.left, root.right);
    }
    // 判断左右节点是否对称
    private boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }
}
```



### [剑指 Offer 29. 顺时针打印矩阵](https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/)

```java
class Solution {
    public int[] spiralOrder(int[][] matrix) {
        List<Integer> tmp = new LinkedList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int left = 0;
        int right = matrix[0].length - 1;
        int up = 0;
        int down = matrix.length - 1;
        while (left <= right && up <= down) {
            for (int i = left; i <= right; i++) {
                tmp.add(matrix[up][i]);
            }
            up++;
            for (int i = up; i <= down; i++) {
                tmp.add(matrix[i][right]);
            }
            right--;
            for (int i = right; i >= left && up <= down; i--) {
                tmp.add(matrix[down][i]);
            }
            down--;
            for (int i = down; i >= up && left <= right; i--) {
                tmp.add(matrix[i][left]);
            }
            left++;
        }
        int[] res = new int[tmp.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = tmp.get(i);
        }
        return res;
    }
}
```



### [剑指 Offer 30. 包含min函数的栈](https://leetcode.cn/problems/bao-han-minhan-shu-de-zhan-lcof/)

```java
class MinStack {

    // 记录栈中的所有元素
    Stack<Integer> stack = new Stack<>();
    // 阶段性记录栈中的最小元素
    Stack<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        // 维护 minStk 栈顶为全栈最小元素
        if (minStack.isEmpty() || x <= minStack.peek()) {
            // 新插入的这个元素就是全栈最小的
            minStack.push(x);
        } else {
            // 插入的这个元素比较大
            minStack.push(minStack.peek());
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
```



### [剑指 Offer 31. 栈的压入、弹出序列](https://leetcode.cn/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/)

```java
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (int num : pushed) {
            stack.push(num);
            while(!stack.isEmpty() && stack.peek() == popped[i]) { // 循环判断与出栈
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }
}
```



### [剑指 Offer 32 - I. 从上到下打印二叉树](https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/)

```java
class Solution {
    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[0];

        Queue<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();

        queue.add(root); // 先将root加入队列

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll(); // 将root移出队列，并准备将左右节点加入队列
            res.add(node.val); // 将元素加入list中，最后是要遍历整个list的
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }

        int[] ans = new int[res.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = res.get(i);
        }

        return ans;  
    }
}
```

[程序员吴师兄](https://www.bilibili.com/video/BV1U34y1D7MN?spm_id_from=333.337.search-card.all.click&vd_source=35aeaee52b15e78b11967f5ef3ce655a)的图解很好理解



### [剑指 Offer 32 - II. 从上到下打印二叉树 II](https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/)

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return res;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> path = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                path.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(path);
        }
        return res;
    }
}
```

这个是真正的**层序遍历**，与上面一题的区别就是返回值不同。



### [剑指 Offer 32 - III. 从上到下打印二叉树 III](https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/)

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        // 这题如果不判空，会出现空指针异常
        if (root == null) return res;
        queue.add(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> path =new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (flag) {
                    path.addLast(node.val);
                } else {
                    path.addFirst(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            flag = !flag;
            res.add(path);
        }
        return res;
    }
}
```

这个就是**锯齿型层序遍历**



### [剑指 Offer 33. 二叉搜索树的后序遍历序列](https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/)



# [LeetCode 精选数据库 70 题](https://leetcode.cn/problem-list/qgq7m9e/?page=1)

### [511. 游戏玩法分析 I](https://leetcode.cn/problems/game-play-analysis-i/)

```sql
SELECT player_id, min(event_date) AS first_login FROM Activity
GROUP BY player_id;
```

group by 列名： 按照列进行分类

### [512. 游戏玩法分析 II](https://leetcode.cn/problems/game-play-analysis-ii/)

```sql
SELECT player_id, device_id FROM Activity
WHERE (player_id, event_date) IN (
    SELECT player_id, min(event_date) 
    FROM Activity GROUP BY player_id
)
```

1. 先找出时间最早的选手；

2. 根据上面的结果再去找设备

### [534. 游戏玩法分析 III](https://leetcode.cn/problems/game-play-analysis-iii/)















































































































































































































































































