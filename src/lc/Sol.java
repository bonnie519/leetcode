package lc;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sol{
	/*
	 * public String longestPalindrome(String s) {
   char[] chars = s.toCharArray();
   int len = s.length();
   while (len >= 0) {
       for (int i = 0; i + len - 1 < chars.length; i++) {
           int left = i;
           int right = i + len - 1;
           boolean good = true;
           while (left < right) {
               if (chars[left] != chars[right]) {
                   good = false;
                   break;
               }
               left++;
               right--;
           }
           if (good)
               return s.substring(i, i + len);
       }
       --len;
   }
   return "";
}
	 * */
	private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
	
	public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(k);
        for(int i : nums){
            if(q.size() < k){
                q.offer(i);//add to queue
            }
            else{
                if(i > q.peek()){
                    q.remove();
                    q.offer(i);
                }
            }
        }
        return q.peek();
    }
	
	public ListNode delDupList(ListNode head)
	{
		ListNode p=null,c = new ListNode(head.val),n=c;
		while(c!=null)
		{
			n = c.next;
			c.next = p;
			p = c;
			c = n;
		}
		
		ListNode r=null;
		Set<Integer> hs = new HashSet<Integer>();
		ListNode dummy = new ListNode(0);
		ListNode pre=dummy,cur=head,next=head;
		dummy.next = cur;
		while(cur!=null)
		{
			if(!hs.add(head.val))
			{
				next = cur.next;
				pre.next = cur.next;
				cur = next;
			}
			else cur = cur.next;
		}
		
		return dummy.next;
	}
	
	public void delDupArr(int[] arr)
	{
		Set<Integer> hs = new HashSet<Integer>();
		for(int i=0;i<arr.length;i++)
		{
			if(!hs.add(arr[i]))
			{
				for(int j=i;j<arr.length-1;j++)
				{
					arr[j]=arr[j+1];
				}
				i--;
			}
		}
		
		for(int i=arr.length-1;i>=0;i++)
		{
			if(!hs.add(arr[i]))
			{
				for(int j=i;j>0;j--)
				{
					arr[j]=arr[j-1];
				}
				i++;
			}
		}
	}
	
	//nth fibonacci
	public int climbStairs(int n) {
        if(n == 1){
            return 1;
        }
        int pre = 1;
        int cur = 1;
        int m = 0;
        
        //nth fibonacci
        
        /*
        for(int i = 1; i < n; i++){
            m = pre + cur;
            pre = cur;
            cur = m;
        }*/
        //< n
        int i=1;
        while(i<n)
        {
        	i=pre+cur;
        	pre = cur;
        	cur = i;
        }
        return pre;
    }
	public int nthFibo1(int n)
	{
		if(n == 1 || n==2){
            return 1;
        }
        int pre = 1;
        int cur = 1;
        int m = 0;
        
        //nth fibonacci
        for(int i = 1; i < n; i++){
            m = pre + cur;
            pre = cur;
            cur = m;
        }
        //< n
        /*int i=1;
        while(i<n)
        {
        	i = pre+cur;
        	pre = cur;
        	cur = i;
        }
        return pre;*/
        return m;
	}
	
	public int nthFibo(int n)
	{
		if(n == 1 || n == 2) return 1;
		return nthFibo(n-1)+nthFibo(n-2);
	}
	
	public int Fibo(int pre, int cur, int n)
	{
		if(n<=2) return 1;
		if(cur < n && (cur+pre >= n) )
			return cur;
		return Fibo(cur,pre+cur,n);
	}
	
	public int sumOfLeftLeaves(TreeNode root) {
        sumLeft(root,0);
        return sum;
    }
	private int sum = 0; 
	
	public int[] messy(int[] arr)
	{
		Random rd = new Random();
		int len = arr.length;
	    int[] res = arr.clone();
	    
		for(int i=0;i<len;i++)
	    {
			int j = rd.nextInt(len);//生成随机数
	        //res[i]=arr[j];
	        //res[j]=arr[i];
			int temp = res[i];//交换
	        res[i]=res[j];
	        res[j]=temp;
	    }
		/*if(!Arrays.equals(res, arr))
		{
			System.out.println("now different");
		}*/
	    return res;//arr;
	}
	
    public void sumLeft(TreeNode root, int side)
    {
        
    	if(root == null) {System.out.println(sum);return;}
        if(side == 1&& root!=null && root.left==null&&root.right==null)
        {   sum += root.val;
        }
        System.out.println("now:"+root.val);
        sumLeft(root.left,1);
        sumLeft(root.right,0);
    }
    
    public String reverseString(String s, int m, int n)
    {
    	int left = m, right = n;
    	if(m>=n) return s;
    	char[] temp = s.toCharArray();
    	while(left<right)
    	{
    		char t = temp[left];
    		temp[left] = temp[right];
    		temp[right] = t;
    		left++;right--;
    	}
    	return temp.toString();
    }
    
    public List<Character> oddMostChar(String s)
    {
    	List<Character> lst = new ArrayList<Character>();
    	int [] hs = new int[26];
    	
    	for(int i=0;i<s.length();i++)
    	{
    		int c = s.charAt(i)-'a';
    			hs[c]+=1;
    	}
    	int max = -1;
    	for(int i=0;i<26;i++)
    	{
    		if(hs[i]%2!=0 && hs[i]>max)
    		{	
    			max=hs[i];
    			lst.clear();
    			lst.add((char) ('a'+i));
    		}
    		else if(hs[i]==max)
    		{
    			lst.add((char) ('a'+i));
    		}
    	}
    	return lst;
    }
    
    public int maxDepth(TreeNode root)
    {
    	if(root == null) return 0;
    	int l = maxDepth(root.left);
    	int r = maxDepth(root.right);
    	return Math.max(l, r)+1;
    }
    
    public int compareVersion(String version1, String version2) {
        //String[] v = version1.split("\\.");
        //System.out.println(v.length);
    	/*
    	 * String[] levels1 = version1.split("\\.");
	    String[] levels2 = version2.split("\\.");
	    
	    int length = Math.max(levels1.length, levels2.length);
	    for (int i=0; i<length; i++) {
	    	Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
	    	Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
	    	int compare = v1.compareTo(v2);
	    	if (compare != 0) {
	    		return compare;
	    	}
	    }
	    return 0;
    	 * */
    	String[] v1=null, v2=null;
    	if(version1.indexOf(".")!=-1)
        	v1 = version1.split("\\.");
        else v1 =new String[]{version1};
    	if(version2.indexOf(".")!=-1)
        	v2 = version2.split("\\.");
    	else
    		v2=new String[]{version2};
    	int num = v1.length< v2.length ? v1.length:v2.length;
        System.out.println(v1.length+" "+v2.length+" "+num+" segments");
        int i=0;
        while(i<num)
        {
            int t1 = Integer.parseInt(v1[i]), t2 = Integer.parseInt(v2[i]);
            System.out.println("compare:"+t1+" "+t2);
            if(t1 < t2)
                return -1;
            else if(t1 > t2)
                return 1;
            else
                i++;
        }
        if(v1.length == v2.length) return 0;
        if(v1.length > v2.length)
        {
        	for(int j=num;j<v1.length;j++)
        	{
        		if(Integer.parseInt(v1[j])!=0)
        			return 1;
        	}
        	return 0;
        }
        else
        {
        	for(int j=num;j<v2.length;j++)
        	{
        		if(Integer.parseInt(v2[j])!=0)
        			return -1;
        	}
        	return 0;
        }
            
    }
    
    public TreeNode buildTree(String exp){  
        TreeNode root = null;
        int paren=0;
        //System.out.println(exp);
        if(exp.length()==0||exp == null) return root;
        String str = exp.substring(1, exp.length()-1);
        //System.out.println(str);
        if(str.indexOf("(")== -1) 
        {	
        	root = new TreeNode(Integer.parseInt(str));
        	return root;
        }
        String rootv = str.substring(0, str.indexOf("("));
        int val = Integer.parseInt(rootv);
        //System.out.println("root:"+val);
        List<String> substr = new ArrayList<String>();
        int start =0;
        for(int i=0;i<str.length();i++)
        {
        	if(str.charAt(i)=='(')
        	{	
        		if(paren==0)
        		{
        			start = i;
        		}
        		paren++;
        	}
        	else if(str.charAt(i)==')')
        	{	
        		if(paren==1)
        		{	//System.out.println(paren+" )"+i);
        			substr.add(str.substring(start, i+1));
        		}
        		paren--;
        	}
        }
        root = new TreeNode(val);  
    	
        if(substr.size()>0)
        	root.left = buildTree(substr.get(0));
        if(substr.size()>1)
        	root.right = buildTree(substr.get(1));
           
    	return root;
    }
    public TreeNode buildT(String s)
    {
    	List<String> lst = new ArrayList<String>();
    	Pattern pattern = Pattern.compile("(?<=\\()(.+?)(?=\\))");
    	Matcher matcher = pattern.matcher(s);

    	while(matcher.find())
    		lst.add(matcher.group());
    	String[] rnodes = lst.get(0).split(",");
    	TreeNode root = Build(rnodes[0],lst); 
    	return root;
    }
	
    public String reverseWord(String s) {
        StringBuilder sb = new StringBuilder();
    	List<String> words = new ArrayList<String>();
    	
    	char [] schar = s.trim().toCharArray();
    	
    	for(int i=0;i<schar.length;i++)
    	{
    		if(schar[i]!=' ')
    			sb.append(schar[i]);
    		else{
    			if(schar[i-1]==' ') continue;
    			words.add(sb.toString());
    			sb.delete(0,sb.length());
    		}
    	}
    	if(!sb.toString().equals(""))
    		words.add(sb.toString());
    	System.out.println("total words: "+words.size());
    	sb.delete(0, sb.length());
    	for(int i=words.size()-1;i>=0;i--)
    	{
    		sb.append(words.get(i));
    		if(i!=0)
    		sb.append(" ");
    		
    	}
    	/*for(int i=0;i<words.size();i++)
    	 * {
    	 * 		String tw = words.get(i);	
    	 * 		reverse(tw.char,0,tw.length()-1);
    	 * 		sb.append(tw);
    	 * 		if(i!=words.size()-1)
    			sb.append(" ");
    	 * }
    	 * */
    	return sb.toString();
    }
    
    public String reverseWord2(String s)
    {
    		if (s == null)
    			return null;

    		char[] str = s.toCharArray();
    		int start = 0, end = str.length - 1;

    		// Trim start of string
    		while (start <= end && str[start] == ' ')
    			start++;

    		//Trim end of string
    		while (end >= 0 && str[end] == ' ')
    			end--;

    		if (start > end)
    			return new String("");

    		int i = start;
    		while (i <= end) {
    			if (str[i] != ' ') {
    				// case when i points to a start of word -  find the word reverse it
    				int j = i + 1;
    				while (j <= end && str[j] != ' ')
    					j++;
    				reverse(str, i, j - 1);
    				i = j;
    			} else {
    				if (str[i - 1] == ' ') {
    					//case when prev char is also space - shift char to left by 1 and decrease end pointer
    					int j = i;
    					while (j <= end - 1) {
    						str[j] = str[j + 1];
    						j++;
    					}
    					end--;
    				} else
    					// case when there is just single space
    					i++;
    			}
    		}
    		//Now that all words are reversed, time to reverse the entire string pointed by start and end - This step reverses the words in string
    		//reverse(str, start, end);
    		// return new string object pointed by start with len = end -start + 1
    		return new String(str, start, end - start + 1);
    	
    }
    private static void reverse(char[] str, int begin, int end) {
		while (begin < end) {
			char temp = str[begin];
			str[begin] = str[end];
			str[end] = temp;
			begin++;
			end--;
		}
	}
   
    
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        res[0] = 1;
        for (int i = 1; i < len; i++) {
            res[i] = res[i-1] * nums[i-1];
        }
        int r = 1;
        for (int i = len - 1; i >= 0; i--) {
            res[i] *= r;
            r *= nums[i];
        }
        return res;

    }
    public TreeNode Build(String rootv, List<String> lst)
    {
    	TreeNode root = new TreeNode(rootv);
    	for(String entry:lst)
    	{
    		String[] nodes = entry.split(",");
    		if(nodes[0].equals(rootv))
    		{
    			if(!nodes[1].equals("")&& root.left==null)
    			{	
    				//root.left = new TreeNode(nodes[1]);
    				root.left = Build(nodes[1],lst);
    			}
    			if(nodes.length==3 && root.right==null)
    			{	
    				root.right = Build(nodes[2],lst);
    			}
    		}
    	}
    	return root;
    }
    
    public TreeNode buildBT(String s)
    {
    	s= s.trim();
    	String[] v1={"1","2"};
    	
    	if(s.charAt(0)!='(' && s.length()==1) return new TreeNode(Integer.parseInt(s)); 
    	TreeNode root = null;//new TreeNode(0);
    	TreeNode cur=null, par =null;
    	Stack<Character> st = new Stack<Character>();
    	StringBuilder sb = new StringBuilder();
    	int i=0;
    	//(a()())
    	while(i<s.length())
    	{
    		char c = s.charAt(i);
    		if(c=='(')
    		{	
    			if(root == null)
    			{	root = new TreeNode(0);
    			}
    			else
    			{	
    				root.left = new TreeNode(0);
    				cur = root.left;
    				par = root;
    				//root= root.left;
    			}//st.push(c);
    		}
    		
    		else if(c!='('&& c!=')')
    		{
    			sb.append(c);
    			/*int j = i;
    			
    			while(c!=')'&& c!='(')
    			{
    				sb.append(c);
    				j++;
    			}
    			root.val = Integer.parseInt(sb.toString());
    			i=j;*/
    		}
    		if(c==')')
    		{
    			root.left.val = Integer.parseInt(sb.toString());
    			sb.delete(0, sb.length());
    			
    		}
    		i++;
    	}
    	return root;
    }
    
    public List<String> levelOrder(TreeNode root)
	{
		 // 队列
	    LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
	    // 模拟数据
	    //TreeNode root = new TreeNode("1");
	    //TreeNode root2 = new TreeNode("2");
	    //root.leftTree = root2;
	    // 将头节点加入队列
	    queue.add(root);
	    TreeNode temp = null;
	    // 收集结果
	    List<String> resultArray = new ArrayList<String>();
	    // 通过while循环，将队列内容全部取出
	    while (!queue.isEmpty()) {
	        // 取出队列第一个节点
	        temp = queue.poll();
	        // 该节点若有左子树，则添加至队列尾部
	        if (temp.left != null) {
	            queue.add(temp.left);
	        }
	        // 该节点若有右子树，则添加至队列尾部
	        if (temp.right != null) {
	            queue.add(temp.right);
	        }
	        // 保存结果
	        resultArray.add(temp.sv);
	    }
	    // 输出结果
	    //for (String str : resultArray) {
	      //  System.out.println(str);
	    //}
	    return resultArray;
	}
    public int searchRotated(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end){
            int mid = (start + end) / 2;
            if (nums[mid] == target)
                return mid;
        
            if (nums[start] <= nums[mid]){
                 if (target < nums[mid] && target >= nums[start]) 
                    end = mid - 1;
                 else
                    start = mid + 1;
            } 
        
            if (nums[mid] <= nums[end]){
                if (target > nums[mid] && target <= nums[end])
                    start = mid + 1;
                 else
                    end = mid - 1;
            }
        }
        return -1;
    }

    public double mypower(int base, int up)
    {
    	if(up == 0) return 1.0;
    	else if(up == 1) return (double)base;
    	else if(up > 1) return mypower(base,up-1)*(double)base;
    	else return 1.0/mypower(base,-up);
    }
    
	public int sumLeftLeaves(TreeNode root)
	{
		/*if(root == null) return 0;
		int left=0,right=0;
		
		if(root.left!=null && root.left.left==null && root.left.right==null)
		{
			left= root.left.val;
		}
		else
		{
			left = sumLeftLeaves(root.left);
		}
		right = sumLeftLeaves(root.right);
		return left+right;*/
		TreeNode node = root;
		Stack<TreeNode> stack = new Stack<>();
		int sum = 0;
		while (node != null || !stack.isEmpty()) {

		   while (node != null) {
		        stack.push(node);
		        if (node.left != null && node.left.left == null 
		            && node.left.right == null) {
		                sum += node.left.val;
		            }
		        node = node.left;
		   }

		   if (!stack.isEmpty()) {
		       node = stack.pop().right;
		   }
		 }
		 return sum;
		/*层序遍历
	public int sumOfLeftLeaves(TreeNode root) {
    if (root == null) return 0;
    Queue<TreeNode> queue = new ArrayDeque<>();
    int sum = 0;
    queue.offer(root);
    while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        if (node.left != null && node.left.left == null 
            && node.left.right == null) {
            sum += node.left.val;
        }
        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
    }
    return sum;
}*/
	}
	
	int maxPathSum(TreeNode root) {
        maxPathSumCore(root);
        return MAX;
    }
	
    int maxPathSumCore(TreeNode node) {
        if(null == node) return 0;
        int a = maxPathSumCore(node.left);
        int b = maxPathSumCore(node.right);
        if((a+b+node.val) > MAX) MAX = (a+b+node.val);
        if((a+node.val) > MAX) MAX = (a+node.val);
        if((b+node.val) > MAX) MAX = (b+node.val);
        if(node.val > MAX) MAX = node.val;

        int maxViaThisNode = ((a + node.val) > node.val ? (a + node.val) : node.val);
        return (maxViaThisNode > (b + node.val) ? maxViaThisNode : (b + node.val));
    }
    int maxPathSumCoreBST(TreeNode node) {
        if(null == node) return 0;
        int a = maxPathSumCore(node.left);
        int b = maxPathSumCore(node.right);
        if(a+b+node.val>MAX) MAX =a+b+node.val;
        if(node.val>MAX) MAX = node.val;
        if(b+node.val>MAX) MAX = b+node.val;
        
        return (node.val > (b + node.val) ? node.val : (b + node.val));
    }
    
    private int MAX= Integer.MIN_VALUE;

	
	public int findBottomLeftValue(TreeNode root) {
        /*if(root.left == null && root.right==null) return root.val;
        Queue<TreeNode> q=new LinkedList<>();
        q.add(root);//将s作为起始顶点加入队列
        HashMap<TreeNode, Integer> dist = new HashMap<TreeNode,Integer>();
        dist.put(root, 0);
        int d,pre=-1,res=root.val;//how to save the first element in every level
        while(!q.isEmpty())
        {
            TreeNode node = q.poll();//取出队首元素
            
            if(dist.get(node)>pre){pre=dist.get(node);res=node.val; }
            System.out.println("e:"+node.val+" dist:"+dist.get(node));
            d=dist.get(node)+1;//得出其周边还未被访问的节点的距离
            if(node.left!=null) 
            {
                dist.put(node.left,d);
                q.add(node.left);
            }
            if(node.right!=null) 
            {
                dist.put(node.right,d);
                q.add(node.right);
            }
        }
        return res;*/
		if (root == null) return 0;
        
        int result = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) result = node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        
        return result;

    }
	public List<String> binaryTreePaths(TreeNode root) {
	    List<String> res = new ArrayList<String>();
	    if(root == null) return res;
	    String path = "";
	        
	    searchBT(root,path,res);
	    return res;
	}
	private void searchBT(TreeNode root, String path, List<String> res) {
	      if (root.left == null && root.right == null) res.add(path + root.val);
	      if (root.left != null) searchBT(root.left, path + root.val + "->", res);
	      if (root.right != null) searchBT(root.right, path + root.val + "->", res);
	}
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (true) {
            if (root.val > p.val && root.val > q.val)
                root = root.left;
            else if (root.val < p.val && root.val < q.val)
                root = root.right;
            else
                return root;
        }
    }
	
	public TreeNode invertTree(TreeNode root) {
        /*if(root == null) return null;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;*/
		if(root == null) return null;
		List<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while(!queue.isEmpty())
		{
			TreeNode node = queue.get(0);
			queue.remove(0);
			TreeNode tmp = node.left;
			node.left = node.right;
			node.right = tmp;
			if(node.left != null)
				queue.add(node.left);
			if(node.right != null)
				queue.add(node.right);
		}
		return root;
    }
	public int minDepth(TreeNode root) {
        if(root == null) return 0;
       
        if(root.left!=null && root.right !=null)
		return Math.min(minDepth(root.left)+1,minDepth(root.right)+1);
        else if(root.left!=null)
        return minDepth(root.left)+1;
        else
        return minDepth(root.right)+1;
		  
		/*if(root == null) return 0;
        List<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int depth=-1;
        while(!queue.isEmpty())
        {
            TreeNode node = queue.get(0);
            queue.remove(0);
            if(node.left ==null && node.right == null) return depth+1;
            if(node.left != null)
               
                queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
            depth++;
        }
        return depth;*/
    }
	
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums.length<2) return false;
        long dif = 0;
        if(nums.length==2)
        {
            dif = Math.abs((long)nums[0]-nums[1]);
            System.out.println("dif: "+dif+" t:"+t);
            if(dif <= (long)t && 1<=k)
                return true;
            else return false;
        }
        
        for(int i=0;i<nums.length-1;i++)
        {
            for(int j=i+1;j<nums.length;j++)
            {
                dif = Math.abs((long)nums[i]-nums[j]);
                if(dif <= (long)t && (j-i<=k)  )
                    return true;
            }
        }
        return false;
    }
	
	public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.left == null && root.right == null && sum == root.val) 
        	return true;
        return hasPathSum(root.left,sum-root.val)||hasPathSum(root.right,sum-root.val);
        //List<TreeNode> queue = new LinkedList<TreeNode>();
        //queue.add(root);
       /* int mysum = 0;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        //stack.add(root);
        
        while (node != null || !stack.isEmpty()) {

 		   while (node != null) {
 		        stack.push(node);
 		        if(mysum+node.val==sum) return true;
 		        mysum += node.val;
 		        node = node.left;
 		   }
 		   
 		   if (!stack.isEmpty()) {
 		       node = stack.pop().right;
 		   }
 		 }
        /*
        while(!queue.isEmpty())
        {
            TreeNode node = queue.get(0);
            queue.remove(0);
            System.out.println(mysum+" "+node.val);
            //mysum += node.val;
            if(mysum+ node.val == sum) return true;
            else
            {
                mysum+= node.val;
                if(node.left != null)
                    queue.add(node.left);
                if(node.right !=null)
                    queue.add(node.right);
            }
        }*/
        //return false;
    }
	
	
	public ListNode reverseListRecursiveVersion(ListNode current)  
	 {  
	     /*//initialization  
	    Node previousNode = null;  
	    Node nextNode = null;  
	      
	    while (current != null) {  
	        //save the next node  
	        nextNode = current.next;  
	        //update the value of "next"  
	        current.next = previousNode;  
	        //shift the pointers  
	        previousNode = current;  
	        current = nextNode;           
	    }  
	    return previousNode;  
	      * */
		
		 if (current == null || current.next == null) return current;  
	     ListNode nextNode = current.next;  
	     current.next = null;  
	     ListNode reverseRest = reverseListRecursiveVersion(nextNode);  
	     nextNode.next = current;  
	     return reverseRest;  
	     
	     //递归法利用递归走到链表的末端，然后再更新每一个node的next值。
	     //在上面的代码中， reverseRest 的值没有改变，为该链表的最后一个node，
	     //所以，反转后，我们可以得到新链表的head。
	 }  
	
	public boolean isPalidrome(int low, int high, String str)
	{
		if(str.length()==1 || str.length()==0) return true;
		if(str.charAt(low)!=str.charAt(high)) return false;
		return isPalidrome(low+1,high-1,str);
		//O(n/2)
	}
	
	private void swap(int[] nums,int i,int j)
	{
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
	public String longestPalindrome(String s) {
        String T = preProcess(s);
        int n = T.length();
        int[] p = new int[n];
        int center = 0, right = 0;
        for (int i = 1; i < n - 1; i++) {
            int j = 2 * center - i;  //j and i are symmetric around center
            p[i] = (right > i) ? Math.min(right - i, p[j]) : 0;
            
            // Expand palindrome centered at i
            while (T.charAt(i + 1 + p[i]) == T.charAt(i - 1 - p[i]))
                p[i]++;
            
            // If palindrome centered at i expand past right,
            // then adjust center based on expand palindrome
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
        }
        
        //  Find the longest palindrome
        int maxLength = 0, centerIndex = 0;
        for (int i = 1; i < n - 1; i++) {
            if (p[i] > maxLength) {
                maxLength = p[i];
                centerIndex = i;
            }
        }
        
        centerIndex = (centerIndex - 1 - maxLength) / 2;
        return s.substring(centerIndex, centerIndex + maxLength);
    }
    
    // preProcess the original string s.
    // For example, s = "abcdefg", then the rvalue = "^#a#b#c#d#e#f#g#$"
    private String preProcess(String s) {
        if (s == null || s.length() == 0)  return "^$";
        StringBuilder rvalue = new StringBuilder("^");
        for (int i = 0; i < s.length(); i++)
            rvalue.append("#").append(s.substring(i, i+1));
        rvalue.append("#$");
        return rvalue.toString();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*char	ch	=	'a';
		char	print	=	ch;
		for(int	j	=	0;	j	<=4;	j++)
		{	
			System.out.print((print++));}
		System.out.println("");*///}
		Sol sol = new Sol();
		int [] nums0={1,2,3,3,3,3,4},
				nums1={3,3,3,3,3,3,3},
				nums2={1,2,3,4,5,6,-1},
				nums3={0,1,2,3,4,5,6};
		/*System.out.println(sol.findKthLargest(nums0,3));
		System.out.println(sol.findKthLargest(nums1,3));
		System.out.println(sol.findKthLargest(nums2,3));
		System.out.println(sol.findKthLargest(nums3,3));
		
		//System.out.println(sol.climbStairs(100));
		System.out.println(sol.nthFibo(10));
		System.out.println(sol.Fibo(1, 1, 100));*/
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		
		root.left.left = new TreeNode(4);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(6);
		root.right.left.left = new TreeNode(7);
		System.out.println(sol.findBottomLeftValue(root));
		
		int [] arr = {2147483647,-2147483647};
		double x = 2.0*2147483647;
		int[] res = sol.messy(new int[]{1,2,3,4,5});
		for(int entry:res)
			System.out.println(entry);
		System.out.println(sol.mypower(2, -6));
		
		String str = "acdedadacc";
		List<Character> lst = new ArrayList<Character>();
		lst = sol.oddMostChar(str);
		for(Character c:lst)
		{
			System.out.println(c);
		}
		
		System.out.println(sol.compareVersion("1.0", "1"));
		
		String exp = "(12(2(3))(4))";
		/*TreeNode r = sol.buildTree(exp);
		List<Integer> order = sol.levelOrder(r);
		for(Integer entry: order)
		{
			System.out.print(entry+" ");
		}*/
		//System.out.println("\n"+sol.minDepth(r));
		String reg ="(a,b,c),(c,,d),(b,e,),(f,g,),(d,f,)";
		TreeNode myroot = sol.buildT(reg);
		List<String> order = sol.levelOrder(myroot);
		System.out.println(order.size());
		for(String entry: order)
		{
			System.out.print(entry+" ");
		}
		
		String rev = "I like rain.";
		System.out.println(sol.reverseWord(rev));
		//System.out.println("x: "+ x);
		//System.out.println(sol.containsNearbyAlmostDuplicate(arr, 1, 2147483647));
		/*
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		root.right.right.right = new TreeNode(1);*/
		//System.out.println(sol.hasPathSum(root, 22));
		/*List<Integer> order = sol.levelOrder(root);
		for(Integer entry: order)
		{
			System.out.println(entry+" ");
		}
		sol.invertTree(root);
		order = sol.levelOrder(root);
		System.out.println("after:");
		for(Integer entry: order)
		{
			System.out.println(entry+" ");
		}
		*/
		//System.out.println("final:"+sol.sumLeftLeaves(root));
		//sol.levelOrder(root);
//		System.out.println(sol.findKthLargest(nums4,3));
//		System.out.println(sol.findKthLargest(nums,3));
	}

}