package forPro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Baekjoon2517
{
    static class IndexedTree{
        int[] tree;
        int[] values;
        int depth;
        int leafSize;

        IndexedTree(int N){
            values = new int[N];
            for(int i =0 ; i < N; i++){
                values[i] = 0;
            }
            depth = (int)Math.ceil(Math.log(values.length)/Math.log(2));
            leafSize = (int)Math.pow(2, depth);
            tree = new int[leafSize*2];
            makeTree(1,0,leafSize-1);
        }

        private int makeTree(int index, int start, int end){
            if(start == end){
                tree[index] = (start < values.length)?values[start]:0;
                return tree[index];
            }
            int mid = (start+end)/2;
            tree[index] = makeTree(index*2,start, mid)+makeTree(index*2+1,mid+1, end);
            return tree[index];
        }

        public int query(int index, int start, int end, int q_s, int q_e){
            if(q_e < start || end < q_s){
                return 0;
            }
            if(q_s <= start && end <= q_e){
                return tree[index];
            }
            int mid = (start+end)/2;
            return query(index*2, start, mid, q_s, q_e)+query(index*2+1, mid+1, end, q_s, q_e);
        }

        public void update(int now){
            int startIndex = (int)Math.pow(2,depth)+now-1;
            int nowIndex = startIndex;
            while(nowIndex>0){
                if(nowIndex == startIndex){
                    tree[nowIndex] += 1;
                }else{
                    tree[nowIndex] = tree[nowIndex*2]+tree[nowIndex*2+1];
                }
                nowIndex = (nowIndex%2==1)?(nowIndex-1)/2: nowIndex/2;
            }
        }

    }

    public static List<Integer> zipArray(int[] nums){
        int[] sort = nums.clone();
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(sort);
        int index = 1;
        for (int n : sort)
            if (!map.containsKey(n))
                map.put(n, index++);
        for(int n : nums){
            ans.add(map.get(n));
        }
        return ans;
    }

    public static void main(String[] main) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] now = new int[N];
        for(int i=0; i <N; i++){
            now[i] = Integer.parseInt(br.readLine());
        }

        IndexedTree it = new IndexedTree(N);
        List<Integer> zipArr = zipArray(now);
        int max = N;
        StringBuilder sb = new StringBuilder();
        for(int num : zipArr){
            if(num != max){
                int result = it.query(1,1,it.leafSize, num+1,N)+1;
                sb.append(result+"\n");
            }else{
                sb.append("1\n");
            }
            it.update(num);
        }
        System.out.println(sb.toString());
    }
}
