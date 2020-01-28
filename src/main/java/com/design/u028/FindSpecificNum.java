package com.design.u028;

/**
 * 在面试中，我经常会让候选人写完代码之后，列举几个测试用例，以此来考察候选人考虑问题是否全面，特别是针对一些边界条件的处理。所以，今天的另一个课堂讨论话题就是：写一个二分查找的变体算法，查找递增数组中第一个大于等于某个给定值的元素（应该是指下标），并且为你的代码设计完备的单元测试用例。
 */

public class FindSpecificNum {
    public static int findSpecificNum(int[] nums, int specificNum) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == specificNum) {
                return i;
            }
        }
        return -1;
    }
}
