package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.System.*;

public class zipzip18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(out));
        HashMap<Integer, Integer> hmm = new HashMap<>();
        Set<Integer> test = new HashSet<>();
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> input = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int a = Integer.parseInt(st.nextToken());
            input.add(a);
            hmm.put(a,0);
            test.add(a);
        }
        ArrayList<Integer> sortedList = (ArrayList<Integer>) new ArrayList<>(test);
        Collections.sort(sortedList);
        for(int i = 0; i < sortedList.size(); i++){
            hmm.put(sortedList.get(i), i);
        }
        for(Integer i : input){
            wr.write(hmm.get(i)+ " ");
        }
        wr.flush();
        wr.close();
    }
}
