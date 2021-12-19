package algorithm;

import jdk.nashorn.internal.runtime.regexp.RegExp;
import sort.Algorithm_Java;

import java.text.AttributedString;
import java.util.*;

public class SwordFingerOffer {
    public static boolean duplicateNumInMatrix(int[] numMatrix) {
        if(numMatrix == null || numMatrix.length == 0) return false;

        Arrays.sort(numMatrix);
        for(int i = 1, len = numMatrix.length; i < len; i++) {
            if(numMatrix[i] == numMatrix[i - 1]) return true;

            return false;
        }
        return false;
    }

    private static int FindElements(int[][] Matrix) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = Matrix.length;
        for(int i = 0, len = Matrix[0].length; i < len; i++) {
            map.put(Matrix[0][i], 1);
        }
        for(int i = 1; i < count; i++) {
            int []item = Matrix[i];
            int len = item.length;
            for(int j = 0; j < len; j++) {
                if(map.get(item[j]) != null && map.get(item[j]) == i) {
                    map.put(item[j], map.get(item[j]) + 1);
                }
            }
        }
        Iterator<Integer> iter = map.keySet().iterator();
        while(iter.hasNext()) {
            Integer value = map.get(iter.next());
            if(value == count) return value;
        }
        return -1;
    }

    public static int add(int a, int b) {
        int result = a ^ b;//无进位加
        int carry  = (a & b) << 1;//计算需要进位的位
        while (carry != 0) {
            int temp = result;
            temp = temp ^ carry;
            carry = (temp & carry) << 1;
            result = temp;
        }
        return result;
    }

    /**
     * @param s: a string
     * @return: return a integer
     */
    public static int longestValidParentheses(String s) {
        if(null == s) return 0;

        int max = 0, start = 0;
        Stack<Integer> stack = new Stack<>();
        int len = s.length();
        for(int index = 0; index < len; index++){
            //遇左括号(，压栈(栈中元素为当前位置所处的下标)
            if('(' == s.charAt(index)){
                stack.push(index);
                continue;
            } else {
                if(stack.isEmpty()){
                    start = index+1;
                    continue;
                } else {
                    stack.pop();
                    if(stack.isEmpty()){
                        max = Math.max(max, index-start+1);
                    } else {
                        max = Math.max(max, index-stack.peek());
                    }
                }
            }
        }
        return max;
    }

    /**
     * 计算出n阶乘中尾部零的个数
     * @param n: An integer
     * @return: An integer, denote the number of trailing zeros in n!
     */
    private static long trailingZeros(long n) {
        long count = n / 5;
        long temp = 0;
        while (count > 0) {
            temp += count;
            count/=5;
        }
        return temp;
    }

    /**
     * 合并两个有序升序的整数数组A和B变成一个新的数组。新数组也为有序数组。
     * @param A: sorted integer array A
     * @param B: sorted integer array B
     * @return: A new sorted integer array
     */
    private static int[] mergeSortedArray(int[] A, int[] B) {
        int [] merge = concat(A, B);
        Algorithm_Java.mergeSort(merge, 0, merge.length);
        return merge;
    }

    /**
     * 合并两个整数数组。
     * @param a
     * @param b
     * @return
     */
    private static int[] concat(int[] a, int[] b) {
        int [] c = new int[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    /**
     * 合并两个泛型数组。
     * @param first
     * @param second
     * @param <T>
     * @return
     */
    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    /**
     * 合并多个数组，支持泛型。
     * @param first
     * @param rest
     * @param <T>
     * @return
     */
    public static <T> T[] concatAll(T[] first, T[]... rest) {
        int totalLength = first.length;
        for (T[] array : rest) {
            totalLength += array.length;
        }
        T[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (T[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

    /**
     * 旋转字符串数组。
     * @param str: An array of char
     * @param offset: An integer
     * @return: nothing
     */
    private static void rotateString(char[] str, int offset) {
        if(str.length == 0) return;
        offset %= str.length;
        if(offset == 0) return;

        char[] temp = new char[str.length];
        System.arraycopy(str, 0, temp, 0, str.length);
        System.arraycopy(temp, str.length - offset, str, 0, offset);
        System.arraycopy(temp, 0, str, offset, str.length - offset);
    }

    /**
     * @param n: An integer
     * @return: A list of strings.
     */
    public static List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            boolean cond0 = i % 3 == 0;
            boolean cond1 = i % 5 == 0;
            if(i % 3 == 0 && i % 5 == 0) result.add("fizz buzz");
            else if(i % 3 == 0) result.add("fizz");
            else if(i % 5 == 0) result.add("buzz");
            else result.add(String.valueOf(i));

//            result.add(cond0 && cond1 ? "fizz buzz" : cond0 ? "fizz" : cond1 ? "buzz" : String.valueOf(i));
        }
        return result;
    }

    /**
     * @param source:
     * @param target:
     * @return: return the index
     */
    public static int strStr(String source, String target) {
        return source.indexOf(target);
    }

    /**
     * @param nums: The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     */
    public static int binarySearch(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    private static int binarySearch(int[] nums, int low, int high, int target) {
        if(low >= high) return nums[low] == target ? low : -1;

        int middle = (low + high) / 2;
        if (nums[middle] < target) {
            return binarySearch(nums, middle + 1, high, target);
        } else {
            return binarySearch(nums, low, middle, target);
        }
    }

    /**
     * 写出一个高效的算法来搜索 m × n矩阵中的值。
     * 这个矩阵具有以下特性：
     * 每行中的整数从左到右是排序的。
     * 每行的第一个数大于上一行的最后一个整数。
     * @param matrix: matrix, a list of lists of integers
     * @param target: An integer
     * @return: a boolean, indicate whether matrix contains target
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) return false;

        for(int i = 0; i < matrix.length; i++) {
            int[] item = matrix[i];
            if(item[0] > target) return false;
            else if(item[item.length - 1] < target) continue;
            else {
                for(int j = 0; j < item.length; j++) {
                    if(item[j] == target) return true;
                }
            }
        }
        return false;
    }

    public ListNode reverse(ListNode head) {
        // write your code here
        ListNode pre = null;
        while(head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * @param nums: An integer array
     * @return: nothing
     */
    public static void recoverRotatedSortedArray(List<Integer> nums) {
        if (nums == null || nums.size() == 0) return;

        int offset = -1;
        for(int i = 1; i < nums.size(); i++) {
            if(nums.get(i) < nums.get(i - 1)) offset = i;
        }
        if(offset == -1) return;

        List<Integer> temp = new ArrayList<>();
        temp.addAll(nums);
        for(int i = 0; i < temp.size() - offset; i++) {
            nums.set(i, temp.get(offset + i));
        }
        for(int i = temp.size() - offset; i < temp.size(); i++) {
            nums.set(i, temp.get(i - offset - 1));
        }
    }

    /**
     * 给定一个整数数组，找到一个具有最大和的子数组，返回其最大和。
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return Integer.MIN_VALUE;

        int max, sum;
        max = sum = nums[0];
        for(int i = 1; i < nums.length; i++) {
            sum = Math.max(sum + nums[i], nums[i]);
            max = Math.max(max, sum);
        }
        return max;
    }

    /**
     * @param nums: a list of integers
     * @return: A integer indicate the sum of minimum subarray
     */
    public int minSubArray(List<Integer> nums) {
        // write your code here
        if (nums == null || nums.size() == 0) return Integer.MAX_VALUE;

        int min, sum;
        min = sum = nums.get(0);
        for(int i = 1; i < nums.size(); i++) {
            sum = Math.min(sum + nums.get(i), nums.get(i));
            min = Math.min(min, sum);
        }
        return min;
    }

    /**
     * 另外一种解法：对列表进行排序，取得len/2位置的元素
     * @param nums: a list of integers
     * @return: find a  majority number
     */
    public static int majorityNumber(List<Integer> nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.size(); i ++) {
            int num = nums.get(i);
            map.put(num, map.get(num) == null ? 1 : map.get(num) + 1);
        }
        int max = Integer.MIN_VALUE;
        int majority = -1;
        Iterator<Integer> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            int num = iter.next();
            if(map.get(num) > max) {
                max = map.get(num);
                majority = num;
            }
        }
        return majority;
    }

    /**
     * @param nums: Given an integers array A
     * @return: A long long array B and B[i]= A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1]
     */
    public static List<Long> productExcludeItself(List<Integer> nums) {
        if(nums == null || nums.size() == 0) return null;

        List<Long> newArr = new ArrayList<>(nums.size());
        for(int i = 0; i < nums.size(); i++) {
            long num = 1;
            for(int j = 0; j < nums.size(); j++) {
                if(j != i) num *= nums.get(j);
            }
            newArr.add(num);
        }
        return newArr;
    }

    /**
     * @param s: A string
     * @return: A string
     */
    public static String reverseWords(String s) {
        if (s == null || s.indexOf(" ") == -1) return s;

        String reverse = "";
        String[] words = s.split("\\s");
        for(int i = words.length - 1; i >= 0; i--) {
            String item = words[i].trim();

            if(!"".equals(item)) {
                if(i != 0) reverse += item + " ";
                else reverse += item;
            }
        }
        return reverse;
    }

    private static boolean isLetter(char ch) {
        int charCode = (int)ch;
        return charCode >= 65 && charCode <= 90 || charCode >= 97 && charCode <= 122;
    }

    /**
     * @param A: A string
     * @param B: A string
     * @return: if string A contains all of the characters in B return true else return false
     */
    public boolean compareStrings(String A, String B) {
        if(A == null || B == null) return false;

        for(int i = 0; i < B.length(); i++) {
            char ch = B.charAt(i);
            if(A.indexOf(ch) == -1) return false;

            A = A.replaceFirst(String.valueOf(ch), "");
        }
        return true;
    }

    /**
     * @param numbers: An array of Integer
     * @param target: target = numbers[index1] + numbers[index2]
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public static int[] twoSum(int[] numbers, int target) {
        if(numbers == null || numbers.length <= 1) return null;

        int[] result = new int[2];
        for(int i = 1; i < numbers.length; i++) {
            for(int j = 0; j < i; j++) {
                if(numbers[i] + numbers[j] == target) {
                    result[0] = j;
                    result[1] = i;
                }
            }
        }
        return result;
    }

//    public int[] twoSum(int[] numbers, int target) {
//        if (numbers == null || numbers.length < 2) {
//            return null;
//        }
//        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//        for (int i = 0; i<numbers.length; i++) {
//            if (map.containsKey(target - numbers[i])) {
//                return new int[]{map.get(target - numbers[i]), i};
//            } else {
//                map.put(numbers[i], i);
//            }
//        }
//        return null;
//    }

    /**
     * @param A: an integer sorted array
     * @param target: an integer to be inserted
     * @return: An integer
     */
    public static int searchInsert(int[] A, int target) {
        return binaryInsert(A, 0, A.length, target);
    }

    private static int binaryInsert(int[] nums, int low, int high, int target) {
        if(low >= high) return low;

        int middle = (low + high) / 2;
        if (nums[middle] < target) {
            return binaryInsert(nums, middle + 1, high, target);
        } else {
            return binaryInsert(nums, low, middle, target);
        }
    }

    /**
     * @param A: sorted integer array A which has m elements, but size of A is m+n
     * @param m: An integer
     * @param B: sorted integer array B which has n elements
     * @param n: An integer
     * @return: nothing
     */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        System.arraycopy(B, 0, A, m, n);
        Arrays.sort(A);
    }
    /**
     * @param root: A Tree
     * @return: Preorder in ArrayList which contains node values.
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return null;

        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode top = stack.pop();
            result.add(top.val);
            if(top.right != null) stack.push(top.right);
            if(top.left != null) stack.push(top.left);
        }
        return result;
    }

    /**
     * @param root: A Tree
     * @return: Inorder in ArrayList which contains node values.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        // write your code here
        if (root == null) return null;

        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (root.left != null) {
            stack.push(root.left);
            root = root.left;
        }
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            result.add(cur.val);
            if(cur.right != null){
                cur = cur.right;
                while(cur != null){
                    stack.push(cur);
                    cur = cur.left;
                }
            }
        }
        return result;
    }

    /**
     * @param root: A Tree
     * @return: Postorder in ArrayList which contains node values.
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return null;

        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode top = stack.peek();
            if(top.left == null && top.right == null) {
                result.add(stack.pop().val);
                continue;
            }
            if(top.right != null) {
                stack.push(top.right);
                top.right = null;
            }
            if(top.left != null) {
                stack.push(top.left);
                top.left = null;
            }
        }
        return result;
    }

     public static class TreeNode {
         public int val;
         public TreeNode left, right;
         public TreeNode(int val) {
             this.val = val;
             this.left = this.right = null;
         }
     }

    /**
     * @param root: A Tree
     * @return: Level order a list of lists of integer
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> item = new ArrayList<>();
            int count = queue.size();
            while (count > 0) {
                TreeNode top = queue.poll();
                item.add(top.val);
                count--;

                if(top.left != null) queue.offer(top.left);
                if(top.right != null) queue.offer(top.right);
            }
            res.add(item);
        }
        return res;
    }

    /**
     * @param nums: A list of integers
     * @return: An integer denotes the middle number of the array
     */
    public static int median(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;

        int midian = len % 2 == 0 ? len/2 - 1 : len/2;
        return nums[midian];
    }

    /**
     * @param A: An integer array
     * @return: An integer
     */
    public int singleNumber(int[] A) {
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < A.length; i++) {
            int num = A[i];
            if(set.contains(num)) set.remove(num);
            else set.add(num);
        }
        int res = -1;
        for(int num : set) res = num;

        return res;
    }

    /**
     * @param root: The root of the binary search tree.
     * @param node: insert this node into the binary search tree
     * @return: The root of the new binary search tree.
     */
    public static TreeNode insertNode(TreeNode root, TreeNode node) {
        if(root == null) return node;

        TreeNode find = root;
        while (find != null) {
            if(find.val < node.val) {
                if(find.right != null) {
                    find = find.right;
                    continue;
                } else {
                    find.right = node;
                    break;
                }
            } else {
                if(find.left != null) {
                    find = find.left;
                    continue;
                } else {
                    find.left = node;
                    break;
                }
            }
        }
        return root;
    }

    /**
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    public boolean isBalanced(TreeNode root) {
        return getDepth(root) != -1;
    }

    private int getDepth(TreeNode root) {
        if(root == null) return 0;

        int left = getDepth(root.left);
        int right = getDepth(root.right);
        if(left == -1 || right == -1 || left - right > 1 || right - left > 1) return -1;

        return Math.max(right, left) + 1;
    }

    /**
     * @param head: The first node of linked list
     * @param x: An integer
     * @return: A ListNode
     */
    public static ListNode partition(ListNode head, int x) {
        if (head == null) return null;

        ListNode linkpoint = null;
        if(head.val < x) {
            linkpoint = head;
            while (linkpoint != null) {
                if(linkpoint.next != null && linkpoint.next.val < x) {
                    linkpoint = linkpoint.next;
                    continue;
                }
                break;
            }
        }
        ListNode pre = linkpoint == null ? head : linkpoint;
        while (pre.next != null) {
            if(pre.next.val < x) {
                ListNode node = pre.next;
                pre.next = pre.next.next;
                if(linkpoint != null) {
                    node.next = linkpoint.next;
                    linkpoint = linkpoint.next = node;
                    continue;
                } else {
                    node.next = head;
                    linkpoint = node;
                    head = node;
                    continue;
                }
            }
            pre = pre.next;
        }
        return head;
    }

    /**
     * @param root: The root of binary tree.
     * @return: An integer
     */
    public static int maxDepth(TreeNode root) {
        if(root == null) return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }

    /**
     * @param nums: An ineger array
     * @return: An integer
     */
    public static int removeDuplicates(int[] nums) {
        if(nums == null) return -1;
        if(nums.length < 2) return nums.length;

        int flag = 0;
        boolean repeat = false;
        for(int i = 0; i < nums.length; i++) {
            if(nums[flag] != nums[i]) {
                nums[++flag] = nums[i];
                repeat = true;
            } else if(i != flag && repeat) {
                nums[++flag] = nums[i];
                repeat = false;
            }
        }
        return flag + 1;
    }

    /**
     * @param grid: a list of lists of integers
     * @return: An integer, minimizes the sum of all numbers along its path
     */
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;
        int [][]arr = new int[m][n];
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i == 0 && j == 0) {
                    //起始位置为传入数组grid起始位置值
                    arr[i][j] = grid[i][j];
                } else if(i == 0) {
                    //第0行，每一列的值为前一列值(arr数组)加上当前列的值(grid数组)
                    arr[i][j] = arr[i][j - 1] + grid[i][j];
                } else if(j == 0) {
                    //第0列，每一行的值为前一行值(arr数组)加上当前行的值(grid数组)
                    arr[i][j] = arr[i - 1][j] + grid[i][j];
                } else {
                    //该位置的值为 该位置(arr[i][j])的左边和上边值的最小值 加上当前位置的值(grid数组)
                    arr[i][j] = Math.min(arr[i - 1][j], arr[i][j - 1]) + grid[i][j];
                }
            }
        }
        return arr[m - 1][n - 1];
    }

    /**
     * @param n: An integer
     * @return: An integer
     */
    public static int climbStairs(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 2;

        int a = 1;
        int b = 2;
        int c = 0;
        for(int i = 3; i < n + 1; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * @param m: positive integer (1 <= m <= 100)
     * @param n: positive integer (1 <= n <= 100)
     * @return: An integer
     */
    public int uniquePaths(int m, int n) {
        if(m == 1 || n == 1) return 1;

        int arr[][] = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 || j == 0) {
                    arr[i][j] = 1;
                } else {
                    arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
                }
            }
        }
        return arr[m - 1][n - 1];
    }

    /**
     * @param obstacleGrid: A list of lists of integers
     * @return: An integer
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid[0][0] == 1) return 0;

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] temp = new int[m][n];
        boolean vertBlock = false;
        boolean horzBlock = false;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0) {
                    if(obstacleGrid[i][j] == 1) horzBlock = true;

                    if(horzBlock) temp[i][j] = 0;
                    else temp[i][j] = 1;
                } else if(j == 0) {
                    if(obstacleGrid[i][j] == 1) vertBlock = true;

                    if(vertBlock) temp[i][j] = 0;
                    else temp[i][j] = 1;
                } else {
                    if(obstacleGrid[i][j] == 1) temp[i][j] = 0;
                    else temp[i][j] = temp[i - 1][j] + temp[i][j - 1];
                }
            }
        }
        return temp[m - 1][n - 1];
    }

    /**
     * @param key: A string you should hash
     * @param HASH_SIZE: An integer
     * @return: An integer
     */
    public static int hashCode(char[] key, int HASH_SIZE) {
        if(key == null || HASH_SIZE <= 0) return -1;

        int len = key.length;
        long res = 0;
        for (int i = 0; i < len; i++) {
            res = res * 33 + key[i];
            res %= HASH_SIZE;
        }
        return (int)res;
    }

    /**
     * @param dictionary: an array of strings
     * @return: an arraylist of strings
     */
    public List<String> longestWords(String[] dictionary) {
        if(dictionary == null) return null;

        int maxLen = 0;
        List<String> res = new ArrayList<>();
        for(String item : dictionary) {
            if(item.length() < maxLen) continue;

            if(item.length() > maxLen) {
                res.clear();
                maxLen = item.length();
            }
            res.add(item);
        }
        return res;
    }

    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> subarraySum(int[] nums) {
        if(nums == null || nums.length == 0) return null;

        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(sumMap.containsKey(sum)) {
                List<Integer> res = new ArrayList<>();
                res.add(sumMap.get(sum) + 1);
                res.add(i);
                return res;
            }
            sumMap.put(sum, i);
        }
        return null;
    }

    /**
     * @param str: A string
     * @return: a boolean
     */
    public boolean isUnique(String str) {
        if(str == null || str.length() == 0) return true;

        Set<Character> set = new HashSet<>();
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(set.contains(ch)) return false;

            set.add(ch);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(hashCode("abcdefghijklmnopqrstuvwxyz".toCharArray(), 2607));
    }
}