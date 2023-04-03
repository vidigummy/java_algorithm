package forPro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon1517 {
    static long[] tree;
    static long[] values;
    static int depth;
    static int leafSize;
//    static long minValue
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N  = Integer.parseInt(br.readLine());
//        연산을 위한 값 저장 부분(하나는 그냥 순서대로, 하나는 set으로 중복 제거 된 상태)
        values = new long[N];
        Set<Long> notDuplicateValues = new HashSet<>();

//        set과 순서대로 넣는 부분 순차적으로 넣는다.
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            values[i] = Long.parseLong(st.nextToken());
            notDuplicateValues.add(values[i]);
        }
//        세그먼트 트리 생성. 부분합 Tree
        init(N);

//        중복 제거 값 정렬 및 좌표 압축
        List<Long> sortedValues = new ArrayList<>(notDuplicateValues);
        Collections.sort(sortedValues);
        
//        좌표 압축
        HashMap<Long, Integer> map = new HashMap<>();
        int cnt = 1;
        for(Long a : sortedValues){
            map.put(a,cnt++);
        }


//        뒤에서부터 시작한다. 먼저 등록된 나보다 작은 숫자가 있으면 언젠가 스왑을 해야한다.
        long ans = 0l;
        for(int i = N-1; i >= 0; i--){
            int index = map.get(values[i]);
//  나보다 작은 것 찾기
            ans += query(1,1,leafSize,1, index-1);
            update(index);
        }
        System.out.println(ans);
    }

//    0으로 통일~
    static void init(int N){
        depth = (int)Math.ceil(Math.log(N)/Math.log(2));
        leafSize = (int)Math.pow(2, depth);
        tree = new long[leafSize*2];
        for(int i = 0; i < leafSize*2; i++){
            tree[i] = 0l;
        }
    }
//    중복을 제거했음에도, 한 수의 개수가 몇개인지에 대한 정보는 있어야 한다.
    static void update(int valueIdx){
        int startIndex = (int)Math.pow(2,depth)+valueIdx-1;
        int nowIndex = startIndex;
        while(nowIndex>0){
            if(nowIndex==startIndex){
                tree[nowIndex] += 1l;
            }else{
                tree[nowIndex] = tree[nowIndex*2]+tree[nowIndex*2+1];
            }
            nowIndex = (nowIndex%2==1)?(nowIndex-1)/2:nowIndex/2;
        }
    }

    static long query(int index, int start, int end, int q_s, int q_e){
        if(q_e < start || end < q_s){
            return 0;
        }
        if(q_s <= start && end <= q_e){
            return tree[index];
        }
        int mid = (start+end)/2;
        return query(index*2, start, mid, q_s, q_e)+query(index*2+1, mid+1, end, q_s, q_e);
    }


}
