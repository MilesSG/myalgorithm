package com.company.meituan;

/**
 * 小美的仓库整理
 * https://leetcode.cn/leetbook/read/meituan/oh4ykh/
 */
public class Test2 {
    public static void main(String[] args) {

    }


    /** 计算去除了index下标所对应的元素的int[] weight的重量的最大值
     *
     * @param weight 货物的重量
     * @param index 取出的编号
     */
    private static int getWeight(int[] weight, int index) {
        index = index - 1;
        int max = 0;
        for (int i = 0; i < index; i++) {
            max = getSum(weight, index);
        }
        for (int i = index; i < weight.length; i++) {
            max = Math.max(max, getLeftSum(weight, index, weight.length));
        }
        return max;
    }

    // 获取[0, index)元素的和
    private static int getSum(int[] nums, int index) {
        int sum = 0;
        for (int i = 0; i < index; i++) {
            sum += nums[i];
        }
        return sum;
    }

    // 获取[left, right)元素的和
    private static int getLeftSum(int[] nums, int left, int right) {
        int sum = 0;
        for (int i = left; i < right; i++) {
            sum += nums[i];
        }
        return sum;
    }
}
