package forPro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Baekjoon7578 {
    static class IndexedTree{
        long[] tree;
        long[] values;
        int depth;
        int leafSize;

        IndexedTree(int n){
            values = new long[n];
            for(int i = 0; i < n; i++){
                values[i] = 0l;
            }
            depth = (int)Math.ceil(Math.log(n)/Math.log(2));
            leafSize = (int)Math.pow(2, depth);
            tree = new long[leafSize*2];
            for(int i = 0; i < leafSize*2; i++){
                tree[i] = 0;
            }
        }

        public long query(int index, int start, int end, int q_s, int q_e){
            if(q_e < start || end < q_s){
                return 0;
            }
            if(q_s <= start && end <= q_e){
                return tree[index];
            }
            int mid = (start + end)/2;
            return query(index*2,start,mid,q_s,q_e)+query(index*2+1, mid+1, end,q_s,q_e);
        }

        public void update(int valueIndex, long newValue){
            int startIndex = (int)Math.pow(2,depth)+valueIndex-1;
            int nowIndex = startIndex;

            while(nowIndex>0){
                if(nowIndex==startIndex){
                    tree[nowIndex] = newValue;
                }else{
                    tree[nowIndex] = tree[nowIndex*2]+tree[nowIndex*2+1];
                }

                nowIndex = (nowIndex%2==1)?(nowIndex-1)/2:nowIndex/2;
            }
        }

        public void printTree() {
            int nowDepth = 0;
            List<Integer> goTo = new ArrayList<>();
            goTo.add(1);
            while (!goTo.isEmpty() && nowDepth <= depth) {
                nowDepth++;
                for (int i : goTo) {
                    try {
                        System.out.printf("%d ", tree[i]);
                    } catch (Exception e) {
                        break;
                    }
                }
                List<Integer> newGoTo = new ArrayList<>();
                int goToLen = goTo.size();
                for (int i = 0; i < goToLen; i++) {
                    int tmp = goTo.get(0);
                    goTo.remove(0);
                    newGoTo.add(tmp * 2);
                    newGoTo.add(tmp * 2 + 1);
                }
                goTo = newGoTo;
                System.out.println("");
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        IndexedTree it = new IndexedTree(N);
        int ls = it.leafSize;
        int[] AArray = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <=N; i++){
            AArray[i] = Integer.parseInt(st.nextToken());
        }
        long ans = 0l;
        Map<Integer, Integer> BMap = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            BMap.put(Integer.parseInt(st.nextToken()), i);
        }

        for(int i = 1; i <= N; i++){
            int now = AArray[i];
            int go = BMap.get(now);
            int left=0;
            int right =0;
            if(go < i){
                left = go;
                right = i;
            }else{
                right = go;
                left = i;
            }
            ans += it.query(1,1, ls, left,right);

            it.update(go, 1l);

        }
        bw.write(Long.toString(ans)+"\n");
        bw.flush();
    }
}
