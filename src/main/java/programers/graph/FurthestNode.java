package programers.graph;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class FurthestNode {
    static HashMap<Integer, List<Integer>> graph = new HashMap<>();
    static HashMap<Integer, List<Integer>> graph_depth = new HashMap<>();
    static HashMap<Integer, Boolean> is_visit = new HashMap<>();
    public static int solution(int n, int[][] edge) throws InterruptedException {
        int answer = 0;
        for(int[] arr: edge){
            if(!graph.containsKey(arr[0])){
                List<Integer> tmp = new ArrayList<>();
                tmp.add(arr[1]);
                graph.put(arr[0],tmp);
            }else{
                List<Integer> tmp = graph.get(arr[0]);
                tmp.add(arr[1]);
                graph.put(arr[0],tmp);
            }
            if(!graph.containsKey(arr[1])){
                List<Integer> tmp = new ArrayList<>();
                tmp.add(arr[0]);
                graph.put(arr[1],tmp);
            }else{
                List<Integer> tmp = graph.get(arr[1]);
                tmp.add(arr[0]);
                graph.put(arr[1],tmp);
            }
        }
        for(Integer key : graph.keySet()){
            is_visit.put(key,false);
        }

        int result = bfs(1,0);
        List<Integer> a = graph_depth.get(result);
        Set<Integer> b = new HashSet<>(a);
        answer = b.size();
//        System.out.println(graph_depth);
        return answer;
    }
    public static void dfs(int now, int Depth){
        is_visit.put(now, true);
        List<Integer> canGoList = graph.get(now);
        if(graph_depth.containsKey(Depth)){
            List<Integer> tmp = graph_depth.get(Depth);
            tmp.add(now);
            graph_depth.put(Depth,tmp);
        }else{
            List<Integer> tmp = new ArrayList<>();
            tmp.add(now);
            graph_depth.put(Depth,tmp);
        }
        for(Integer node : canGoList.toArray(new Integer[0])){
//            System.out.println("Now Depth : "+ Depth + " can go node : " +node);
            if(!is_visit.get(node)){
                dfs(node, Depth+1);
            }
        }

    }
    public static int bfs(int now, int Depth) throws InterruptedException {
        is_visit.put(now,true);

        List<Integer> now_depth = graph.get(now);
        while(is_visit.containsValue(false)){
//            System.out.println(graph_depth);
            List<Integer> tmp_0 = new ArrayList<>();
            tmp_0.add(now);
            graph_depth.put(Depth,tmp_0);
            Depth += 1;
//            System.out.println("Depth : " + Depth + " now : " + now_depth);

            for(Integer now_loop : now_depth){
                is_visit.put(now_loop,true);
                if(graph_depth.containsKey(Depth)){
                    List<Integer> tmp = graph_depth.get(Depth);
                    tmp.add(now_loop);
                    graph_depth.put(Depth,tmp);
                }else{
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(now_loop);
                    graph_depth.put(Depth,tmp);
                }
            }
//            System.out.println(graph_depth);
            List<Integer> tmp = new ArrayList<>();
            for(Integer now_loop : now_depth){
                List<Integer> tmp2 = graph.get(now_loop);
                for(Integer a : tmp2){
                    if(!is_visit.get(a)){
                        tmp.add(a);
                    }
                }
            }
            now_depth = tmp;
        }
        return Depth;
    }
    public static void main(String[] args) throws InterruptedException {
        int n = 6;
        int[][] vertex = {{3,6},{4,3},{3,2},{1,3},{1,2},{2,4},{5,2}};
        int ans = solution(n,vertex);
        System.out.println(ans);
    }
}
