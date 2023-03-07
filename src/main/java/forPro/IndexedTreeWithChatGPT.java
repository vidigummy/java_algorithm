package forPro;

public class IndexedTreeWithChatGPT {
    static class IndexedTree {
        int[] tree;
        int n;

        public IndexedTree(int[] nums) {
            n = nums.length;
            tree = new int[n + 1];
            for (int i = 0; i < n; i++) {
                update(i, nums[i]);
            }
        }

        public void update(int i, int val) {
            i++;
            while (i <= n) {
                tree[i] += val;
                i += (i & -i);
            }
        }

        public int sum(int i) {
            int res = 0;
            i++;
            while (i > 0) {
                res += tree[i];
                i -= (i & -i);
            }
            return res;
        }

        public int sumRange(int i, int j) {
            return sum(j) - sum(i - 1);
        }
    }
    public static void main(String args[]){
        int numbers[] = {0,8,3,26,1,7,2,4,10};
        IndexedTree it = new IndexedTree(numbers);
        System.out.println(it.sumRange(2,5));
    }
}
