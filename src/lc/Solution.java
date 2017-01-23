package lc;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public boolean isMatch(String s, String p)
	{
		if(s.matches(p))
		return true;
		else return false;
	}
	public int myAtoi(String str) {
        String s=str.trim(),rs="";int f=1,i=0;
        for(i=0;i<s.length();i++)
        {
            char c =s.charAt(i);
            if(!(c>='0'&&c<='9'||c=='-'||c=='+'))
            {
                if(f==1)
                return 0;
                else return handle(rs);
            }
            
            if(c=='-'||c=='+')
            {   if(f==1)
                {   f=0;
                    //if(!(s.charAt(i+1)>='0'&&s.charAt(i+1)<='9'))return 0;
                    rs+=c;
                }
                else return 0;
            }
            if(c>='0'&&c<='9')
            {
                rs+=c;
                if(f==1)f=0;
            }
        }
        return handle(rs);
    }
    int handle(String str)
    {
        if(str.length()<=0)return 0;
        if(str.equals("+")||str.equals("-")) return 0;
        int sign=1,len=str.length(),start=0;
        if(str.charAt(0)=='-'){sign=-1;len--;start=1;}
        if(str.charAt(0)=='+'){sign=1;len--;start=1;}
        
        int i=0;//pw=pow(len-1;
        long rs=0,pw=1;
        
        for(i=str.length()-1;i>=start;i--)
        {
            int t=str.charAt(i)-'0';
            
            if((rs+t*pw)*sign>2147483647)return 2147483647;
            if((rs+t*pw)*sign<-2147483648)return -2147483648;
            rs+=t*pw;
            
            //System.out.println(rs+"---"+pw);
            pw=pw*10;
        }
        
        return (int)rs*sign;
        
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null)return l2;
        if(l2==null)return l1;
    	ListNode l3=null,head=new ListNode(0),curr=head;

    	//return fakeHead.next;
    	int flag=1;
        while(l1!=null&& l2!=null)
        {
            
            if(l1.val<l2.val)
            {
                curr.next=l1;
                l1=l1.next;
            }
            else
            {
                curr.next=l2;
                l2=l2.next;
            }
            //l3=t;
            //System.out.println(l3.val);
            curr=curr.next;
        }
        
        if(l1!=null)
        {
            curr.next=l1;
        }
        if(l2!=null)
        {
            curr.next=l2;
        }
        return head.next;
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    	if(nums1.length==0) return MedofArray(nums2);
    	if(nums2.length==0)	return MedofArray(nums1);
		int n=nums1.length,m=nums2.length;
		if(n>m)return findMedianSortedArrays(nums2,nums1);
		int L1=0,L2=0,R1=0,R2=0,c1=0,c2=0,lo=0,hi=2*n;
		while(lo <= hi)   //二分
        {
            c1 = (lo+hi)/2;  //c1是二分的结果
            c2 = m+n- c1;
            L1 = (c1 == 0)?Integer.MIN_VALUE:nums1[(c1-1)/2];
            		//INT_MIN:nums1[(c1-1)/2];   //map to original element
            R1 = (c1 == 2*n)?Integer.MAX_VALUE:nums1[c1/2];
            L2 = (c2 == 0)?Integer.MIN_VALUE:nums2[(c2-1)/2];
            R2 = (c2 == 2*m)?Integer.MAX_VALUE:nums2[c2/2];

            if(L1 > R2)
                hi = c1-1;
            else if(L2 > R1)
                lo = c1+1;
            else
                break;
        }
        return (Integer.max(L1,L2)+ Integer.min(R1,R2))/2.0;
    	
    }
    double MedofArray(int[] nums)
    {
        if(nums.length == 0)    return -1;
        return (nums[nums.length/2]+nums[(nums.length-1)/2])/2.0;
    }
    public String frequencySort(String s) {
        if(s.length() < 3)
            return s;
        int max = 0;
        int[] map = new int[256];
        for(char ch : s.toCharArray()) {
            map[ch]++;
            max = Math.max(max,map[ch]);
        }
        String[] buckets = new String[max + 1]; // create max buckets
        for(int i = 0 ; i < 256; i++) { // join chars in the same bucket
            String str = buckets[map[i]];
            if(map[i] > 0)
                buckets[map[i]] = (str == null) ? "" + (char)i : (str + (char) i);
        }
        StringBuilder strb = new StringBuilder();
        for(int i = max; i >= 0; i--) { // create string for each bucket.
            if(buckets[i] != null)
                for(char ch : buckets[i].toCharArray())
                    for(int j = 0; j < i; j++)
                        strb.append(ch);
        }
        return strb.toString();
    }
    public int numSum(String str){
    	if(str==null) return 0;
    	int cur=0, res=0, num=0;
    	boolean pos = true;
    	for(int i=0;i<str.length();i++)
    	{
    		cur = str.charAt(i)-'0';
    		if(cur < 0 || cur>9)
    		{
    			res+=num;
    			num=0;//
    			if(str.charAt(i)=='-')
    			{
    				if(i>0 && str.charAt(i-1)=='-')
    					pos = !pos;
    				else pos = false;
    			}
    		}
    		else
    			num=num*10+(pos?cur:-cur);
    	}
    	//if(cur >=0 && cur<=9)
    	res+=num;
    	return res;
    }
    public boolean isRotate(String a, String b)
    {
    	if(a.length()!=b.length()) return false;
    	/*
    	int i=0;String rotate="";
    	for(;i<a.length();i++)
    	{
    		rotate=a.substring(i+1)+a.substring(0,i+1);
    		if(rotate.equals(b)) return true;
    	}
    	return false;*/
    	String b2 = b+b;
    	if(b2.indexOf(a)!=-1) return true;
    	else return false;
    	//KMP
    }
    public String removeKZeros(String str, int k)
    {
    	if(k==0) return str;
    	StringBuilder res=new StringBuilder();int count=0;
    	char cur;
    	for(int i=0;i<str.length();i++)
    	{
    		cur = str.charAt(i);
    		if(cur=='0')
    		{
    			count++;
    		}
    		else
    		{	//res.append(cur);
    			if(count==k)
    			{	res.append(cur);
    			}
    			else res.append(str.substring(i-count, i+1));
    			count=0;
    		}
    	}
    	if(count!=k)
    		res.append(str.substring(str.length()-count));
    	return res.toString();
    }
    public List<Integer> findDuplicates(int[] nums) {
    	List<Integer> rs=new ArrayList<Integer>();
        int i=0,left=0,right=nums.length-1;
        int []twice=new int[nums.length+1];
        for(;i<nums.length;i++)
        {
            twice[nums[i]]+=1;
        }
        for(i=1;i<twice.length;i++)
        {
            if(twice[i]==2)
                rs.add(i);
        }
        return rs;
    }
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ret = new ArrayList<Integer>();
        
        for(int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]) - 1;
            if(nums[val] > 0) {
                nums[val] = -nums[val];
            }
        }
        
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) {
                ret.add(i+1);
            }
        }
        return ret;
    }
    public boolean canConstruct(String ransomNote, String magazine) {
        if(magazine.length()==0&& ransomNote.length()!=0) return false;
        if(ransomNote.length()==0) return true;
        int i=0,j=0,flag=0;
        int[] use=new int[magazine.length()];
        for(i=0;i<magazine.length();i++) use[i]=0;
        for(i=0;i<ransomNote.length();i++)
        {
            flag=0;
            for(j=0;j<magazine.length();j++)
            {
                if(ransomNote.charAt(i)==magazine.charAt(j) && use[j]==0)
                {use[j]=1;flag=1;
                System.out.println(ransomNote.charAt(i)+"  "+magazine.charAt(j));
                break;}
            }
            
            if(flag==0)return false;
        }
        return true;
        
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sl=new Solution();
		/*ListNode l1=new ListNode(2);
		ListNode l2=new ListNode(-1);
		l2.next=new ListNode(1);
		l2.next.next=new ListNode(3);
		ListNode l3= sl.mergeTwoLists(l1, l2);
		while(l3!=null)
		{System.out.println(l3.val);
		l3=l3.next;
		}*/
		String a="1ab2",b="ab12";
		System.out.println(sl.isRotate(a,b));
		//System.out.println(sl.canConstruct(ran, mag));
		//System.out.println(sl.myAtoi("     10522545459"));
	}

}
