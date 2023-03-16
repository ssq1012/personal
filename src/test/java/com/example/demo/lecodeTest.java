package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ssq
 * @create on 2022/10/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class lecodeTest {

    /**
     * 回文数:顺着读反着读都一样
     */
    @Test
    public void testHuiWen() {
        int x = 12321;
        if (x == 0) System.out.println("true");

        if (x < 0 || x % 10 == 0) System.out.println("false");
        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        System.out.println(x == reversed || x == reversed / 10);
    }

    /**
     * 两数相加
     */
    @Test
    public void arrayTest(){
        int nums[] = {3,11,7,15,2};
        int target = 9;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])){
                System.out.println(map.get(target - nums[i]) + "," + i);
            }
            map.put(nums[i],i);
        }
    }

    /**
     * 最长公共前缀
     */
    @Test
    public void longestCommonPrefix(){
        String[] strs = {"flower","flow","flight"};
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            char[] chars = str.toCharArray();
            for (int j = 0; j < chars.length; j++) {

            }
        }
    }

    /**
     * 最长重复字符串
     */
    @Test
    public void duplicateStr(){
        String str = "abcdabcad";

    }

    @Test
    public void romanNum(){
        String str = "MCMXCIV";
        int count = 0;
        Map<String,Integer> map = new HashMap<>();
        map.put("I",1);
        map.put("V",5);
        map.put("X",10);
        map.put("L",50);
        map.put("C",100);
        map.put("D",500);
        map.put("M",1000);
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int num = map.get(String.valueOf(chars[i]));
            if (i < chars.length -1){
                int next = map.get(String.valueOf(chars[i+1]));
                if (next <= num){
                    count = count +num;
                }else {
                    count = count - num;
                }
            }else {
                count += num;
            }
        }
        System.out.println(count);
    }

    @Test
    public void romanToInt() {
        String s = "MCMXCIV";
        Map<String,Integer> map = new HashMap<>();
        map.put("I",1);
        map.put("V",5);
        map.put("X",10);
        map.put("L",50);
        map.put("C",100);
        map.put("D",500);
        map.put("M",1000);
        int sum = 0;
        int preNum = map.get(String.valueOf(s.charAt(0)));
        for(int i = 1;i < s.length(); i ++) {
            int num = map.get(String.valueOf(s.charAt(i)));
            if(preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        System.out.println(sum);
    }


    /**
     * 二分查找算法
     */
    @Test
    public void kuoHao(){
        int[] strs = {1,3,5,7,9,12};
        int target = 3;
        int result = -1;
        int low = 0;
        int hight = strs.length -1;
        while (hight >= low){
            int mid = (hight - low )/2 + low;
            //右
                if (target > strs[mid]){
                    low = mid + 1;
                }else if (target == strs[mid]){
                    result = mid;
                    break;
                    //左
                }else if (target < strs[mid]){
                    hight = mid -1;
                }
            }
        if (result != -1){
            System.out.println("找到了，在->" + result);
        }else {
            System.out.println(low);
        }
    }

    @Test
    public void firstBadVersion() {
        int n = 1;
        int low = 1;
        int badVerson = 0;
        int hight = n;
        while (hight >= low){
            int mid = (hight - low )/2 + low;
            if (isBadVersion(mid)){
                hight = mid -1;
                badVerson = mid;
            }else{
                low = mid + 1;
            }
        }
        System.out.println(badVerson);
    }

    private boolean isBadVersion(int version){
        if (version == 1){
            return true;
        }
        return false;
    }

    @Test
    public void outPut(){
        System.out.println(1^2^1);
//        System.out.println(isPerfectSquare(808201));
    }

    public void outPut( int[] nums){
        int max = nums[0];
        int min = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max){
                max = nums[i];
            }
            if (nums[i] < min){
                min = nums[i];
            }
            sum += nums[i];
        }
        int avg = (sum - max - min)/ (nums.length -2);
        System.out.println(avg);
    }

    public int mySqrt(int x) {
        long low = 1;
        long hight = x - 1;
        while(hight >= low){
            long mid = ( hight - low ) / 2 + low ;
            //右边
            if(x > (mid*mid)){
                low = mid + 1;
            }else if(x < (mid*mid)){
                //左边
                hight = mid - 1;
            }else{
                //相等
                return (int)mid;
            }
        }
        return (int)low;
    }

    public boolean isPerfectSquare(int num) {
        if(1== num) return true;
        long low = 1;
        long hight = num - 1;
        while(hight >= low){
            long mid = ( hight - low ) / 2 + low ;
            //右边
            if(num > (mid*mid)){
                low = mid + 1;
            }else if(num <  (mid*mid)){
                //左边
                hight = mid - 1;
            }else{
                //相等
                return true;
            }
        }
        return false;
    }

    @Test
    public void testHappyNum(){
        int num =  195;
        Set<Integer> seen = new HashSet<>();
        while (num != 1 && !seen.contains(num)) {
            seen.add(num);
            num = getNext(num);
        }
        System.out.println(num == 1);
    }


    @Test
    public void testDoublePoints(){
        int[] nums = {-10,-6,-1,1,2,3,4,5};
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0, j = n - 1, pos = n - 1; i <= j;) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                ans[pos] = nums[i] * nums[i];
                ++i;
            } else {
                ans[pos] = nums[j] * nums[j];
                --j;
            }
            --pos;
        }
        System.out.println(ans);
    }

    public int getNext(int n){
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    /**
     * 滑动窗口算法
     */
    @Test
    public void testSlideWindow(){
        String str = "abcabcb";

    }

}
