package forPro;

import java.util.Arrays;

public class IndexedTreeWithChatGPT {
//    static class IndexedTree {
//        int[] tree;
//        int n;
//
//        public IndexedTree(int[] nums) {
//            n = nums.length;
//            tree = new int[n + 1];
//            for (int i = 0; i < n; i++) {
//                update(i, nums[i]);
////                System.out.println("index : "+Integer.toString(i)+" | value : "+Integer.toString(nums[i]));
////                System.out.println(Arrays.toString(tree));
//            }
//        }
//
//        public void update(int i, int val) {
//            i++;
//            while (i <= n) {
////                System.out.println(Integer.toString(i)+" index update");
//                tree[i] += val;
////                System.out.println(i);
//                i += (i & -i);
////                System.out.println(Integer.toString(i & -i));
////                System.out.println(i);
//            }
//        }
//
//        public int sum(int i) {
//            int res = 0;
//            i++;
//            while (i > 0) {
//                res += tree[i];
//                i -= (i & -i);
//            }
//            return res;
//        }
//
//        public int sumRange(int i, int j) {
//            return sum(j) - sum(i - 1);
//        }
//
//        public void printTree(){
//            System.out.println(Arrays.toString(tree));
//        }
//    }
//    public static void main(String args[]){
//        int numbers[] = {0,8,3,26,1,7,2,4,10};
//        IndexedTree it = new IndexedTree(numbers);
//        it.printTree();
//        System.out.println(it.sumRange(5,6));
//    }
}
