package softwaremaestro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class graph {
    static float[] rateArr;
    static HashMap<Integer, List<Integer>> friendGraph = new HashMap<>();
    static int N;
    static int M;
    static int K;
    public static void bfs(Integer first){
        List<Integer> haveToFill = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        boolean[] isVisit = new boolean[N+1];
        Arrays.fill(isVisit,false);
        float sum = 0.0F;
        float cnt = 0.0F;
        q.offer(first);
        while(!q.isEmpty()){
            int now = q.poll();
            isVisit[now] = true;
            if(rateArr[now]==0.0F){
                haveToFill.add(now);
            }else{
                sum += rateArr[now];
                cnt += 1.0F;
            }
            if(friendGraph.containsKey(now)){
                List<Integer> nowToGo = friendGraph.get(now);
                for(Integer toGo : nowToGo){
                    if(!isVisit[toGo]){
                        q.offer(toGo);
                    }
                }
            }
        }
        float result = 0.0F;
        result = (float) Math.floor(sum/cnt);
        if(sum > 0.0F){
            for(Integer fill : haveToFill){
                rateArr[fill] = result;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        rateArr = new float[N+1];
        Arrays.fill(rateArr, 0.0F);
        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(br.readLine());
            int who = Integer.parseInt(st.nextToken());
            float rate = Float.parseFloat(st.nextToken());
            rateArr[who] = rate;
        }
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(friendGraph.containsKey(a)){
                List<Integer> tmp = friendGraph.get(a);
                tmp.add(b);
                friendGraph.put(a,tmp);
            }else{
                List<Integer> tmp = new ArrayList<>();
                tmp.add(b);
                friendGraph.put(a,tmp);
            }
            if(friendGraph.containsKey(b)){
                List<Integer> tmp = friendGraph.get(b);
                tmp.add(a);
                friendGraph.put(b,tmp);
            }else{
                List<Integer> tmp = new ArrayList<>();
                tmp.add(a);
                friendGraph.put(b,tmp);
            }
        }
        for(int i = 1; i <= N; i++){
            if(rateArr[i] == 0.0F){
                bfs(i);
            }
        }
        float sum = 0.0F;
        float cnt = 0.0F;
        for(int i =1 ; i <= N; i++){
            sum += rateArr[i];
            if(rateArr[i] != 0.0F){
                cnt += 1.0F;
            }
        }
        System.out.println(String.format("%.2f", sum/cnt));
    }
}
