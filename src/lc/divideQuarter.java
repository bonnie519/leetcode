package lc;
/**
**author:Bei Gao
**USC ID:6863049908
**InvertedIndx.java
**Assignment 3
**/
import java.util.HashMap;
import java.util.Map;

public class divideQuarter{
	
    static boolean resolve(int[] nums) {
    	if (nums.length < 7) {
            return false;
        }
        int [] sum= new int[nums.length];
        sum[0] = nums[0]; 
        for (int i = 1; i < nums.length; ++i) {
            sum[i] += sum[i - 1] + nums[i]; 
        }
        int low = 0; 
        int high = nums.length - 1;
        int firstSum = nums[low++]; 
        int lastSum = nums[high--];
        while (low + 4 <= high) {
            if (firstSum < lastSum) {
                firstSum += nums[low++]; 
            }
            else if (firstSum > lastSum) {
                lastSum += nums[high--];
            }
            else {
                int l = low + 1;
                int h = high - 1;
                while (l <= h) {
                    int m = (l + h) >> 1; 
                	System.out.println(l+" "+h+" "+m);
                    int secSum = sum[m - 1] - sum[low];
                    int thdSum = sum[high - 1] - sum[m];
                    if (secSum > thdSum) {
                        h = m - 1;
                    }
                    else if (secSum < thdSum) {
                        l = m + 1;
                    }
                    else {
                        if (secSum == firstSum) {
                            return true;//{low, m, high};
                        }
                        else {
                            firstSum += nums[low++];
                            break;
                        }
                    }
                }
            }
        }
        return false; 
    }

    public static void main(String[] args) {
        // int[] a = { 1, 1, 1, 1, 5, 4, 6, 4, 5, 1, 3 };
        // int[] a = { 1, 1, 1, 4, 4, 6, 4, 1, 3 };
        // int[] a = { 2, 5, 1, 1, 1, 1, 4, 1, 7, 3, 7 }; // true
        //int[] a = { 2, 5, 1, 7, 1, 7, 3, 7 }; // true
    	int[] a = { 3, 6, 7, 2, 1, 8, 5, 4 };
        int[] a1 = { 10, 2, 13, 1, 1, 1, 1, 1 }; // false
        int[] a2 = { 10, 1, 1, 11, 1, 11, 1, 1, 10 }; // true

        System.out.println(resolve(a));
    }

	
	
}
