package programers.xfs;
import java.util.*;

public class ChangeWord {
    static HashMap<Integer,List<Integer>> word_graph = new HashMap<>();
    static HashMap<Integer,Boolean> is_visit = new HashMap<>();
    static Integer min = Integer.MAX_VALUE;
    static Integer target_index = 0;
    public static int solution(String begin, String target, String[] words) {
        int words_len = words.length;

        for(int i = 0; i < words_len;i++){
            is_visit.put(i,false);
            if(words[i].equals(target)){
                target_index = i;
            }
            for(int j =0; j < words_len; j++){
                if(compare(words[i],words[j])){
                    if(word_graph.containsKey(i)){
                        List<Integer> tmp = word_graph.get(i);
                        tmp.add(j);
                        word_graph.put(i,tmp);
                    }else{
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(j);
                        word_graph.put(i,tmp);
                    }
                    if(word_graph.containsKey(j)){
                        List<Integer> tmp = word_graph.get(j);
                        tmp.add(i);
                        word_graph.put(j,tmp);
                    }else{
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(i);
                        word_graph.put(j,tmp);
                    }
                }
            }
        }
        bfs(0,0);
        return -1;
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
        if(depth==0){
            is_visit.put(now,true);
        }

        List<Integer> tmp = new ArrayList<>();
        tmp.add(now);
        while(!is_visit.containsValue(false)){
            depth++;
            Set<Integer> toGo = new HashSet<>();
            for(Integer visit : tmp){
                is_visit.put(visit, true);
                List<Integer> next_nodes = word_graph.get(visit);
                for(Integer node: next_nodes){
                    if(!is_visit.get(node)){
                        if(node.equals(target_index)){
                            min = Math.min(depth, min);
                        }
                        toGo.add(node);
                    }
                }
            }
            tmp.clear();
            tmp.addAll(toGo);
        }
    }
    public static void main(String[] args){
        String begin = "hit";
        String target = "cog";
        String[] words  = {"hot", "dot", "dog", "lot", "log", "cog"};
    }
}
