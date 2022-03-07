package programers.xfs;

import java.util.*;

public class ChangeWordSecond {
    static HashMap<Integer, Set<Integer>> graph = new HashMap<>();
    static HashMap<Integer, Boolean> isVisit = new HashMap<>();
    static int targetIndex = 0;
    static int min = Integer.MAX_VALUE;
    public static int solution(String begin, String target, String[] words) {
        int len = words.length;
        List<String> input_words = Arrays.asList(words);
        List<String> words_result = new ArrayList<>();
        words_result.add(begin);
        words_result.addAll(input_words);
//        System.out.println(words_result);
        len = words_result.size();
        for(int i = 0; i < len; i++){
            if(words_result.get(i).equals(target)){
                targetIndex = i;
            }
            isVisit.put(i, false);
            for(int j = 0; j < len; j++){
                if(compare(words_result.get(i),words_result.get(j))){
                    if(graph.containsKey(i)){
                        Set<Integer> tmp = graph.get(i);
                        tmp.add(j);
                        graph.put(i,tmp);
                    }else{
                        Set<Integer> tmp = new HashSet<>();
                        tmp.add(j);
                        graph.put(i,tmp);
                    }
                    if(graph.containsKey(j)){
                        Set<Integer> tmp = graph.get(j);
                        tmp.add(i);
                        graph.put(j, tmp);
                    }else{
                        Set<Integer> tmp = new HashSet<>();
                        tmp.add(i);
                        graph.put(j,tmp);
                    }
                }
            }
        }
//        System.out.println(graph);
        bfs(0,0);
        int ans = min;
        return ans;
    }

    public static boolean compare(String a, String b){
        int cnt = 0;
        for(int i =0; i < a.length(); i++){
            if(a.charAt(i) != b.charAt(i)){
                cnt++;
            }
        }
        if(cnt > 1){
            return false;
        }else{
            return true;
        }
    }

    public static void bfs(int now, int depth){
//        System.out.println("bfs start");
        if(targetIndex==0){
            min = 0;
            return ;
        }
        isVisit.put(now, true);
        Set<Integer> nowToGo = graph.get(now);
        for(Integer a: nowToGo){
            if(a.equals(targetIndex)){
                min = 1;
                return;
            }
        }
        while(isVisit.containsValue(false)){
            depth += 1;
//            System.out.println("Now Depth : " + depth + " and have to Go : " + nowToGo);
            Set<Integer> nextToGo = new HashSet<>();
            for(Integer nowGo: nowToGo){
                isVisit.put(nowGo, true);
                Set<Integer> toGo = graph.get(nowGo);
                for(Integer next : toGo){
                    if(next.equals(targetIndex) && !next.equals(0)){
                        min = Math.min(depth+1,min);
                        return ;
                    }else{
                        if(!isVisit.get(next)){
                            nextToGo.add(next);
                        }
                    }
                }
            }
            nowToGo.clear();
            nowToGo = nextToGo;
        }
        min = 0;
    }
    public static void main(String[] args){
        String begin = "hit";
        String target = "cog";
        String[] words  = {"hot", "dot", "dog", "lot", "log"};
        int ans = solution(begin,target,words);
        System.out.println(ans);
    }
}
