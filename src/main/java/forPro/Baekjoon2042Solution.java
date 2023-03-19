package forPro;

import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;
        import java.util.StringTokenizer;

public class Baekjoon2042Solution {
    private static int N, M, K, leafCnt;
    private static long[] indexedTree;
    //-2^63보다 크거나 같고, 2^63-1보다 작거나 같은 정수 => long
    //-2^31보다 크거나 같고, 2^31-1보다 작거나 같은 정수 => int
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        leafCnt = 1;
        while (leafCnt < N) {
            leafCnt *= 2;
        }
        //for문 쓰려면 이렇게 : for (leafCnt = 1; leafCnt < N; leafCnt *= 2);

        indexedTree = new long[leafCnt*2];
        leafCnt = leafCnt - 1;
        /*이렇게 해두면 i 번째 리프노트의 값을 구할 때,
        indexedTree[leafCnt + i] 로 구할 수 있다
        즉 리프 노드 중 세 번째의 값을 원하면 indexedTree[leafCnt + 3]을 하면 됨 */

        for (int i = 1; i <= N; i++) {
            indexedTree[leafCnt + i] = Long.parseLong(br.readLine());
        }

        init(leafCnt+1, N);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if(a == 1) {
                update(leafCnt + b, c);
            }else{
                long sum = query(leafCnt + b, (int) (leafCnt + c));
                bw.write(sum + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
    //인덱스트리
    public static void init(int start, int end) {
        for (int i = start; i < start + end; i++) {
            int idx = i / 2;
            while (idx != 0) {
                indexedTree[idx] += indexedTree[i];
                idx /= 2;
            }
        }
    }

    public static void update(int idx, long val) {
        // 기존 값과 새로운 값의 차이만큼 더해준다.
        val -= indexedTree[idx];
        while (idx != 0) {
            indexedTree[idx] += val;
            idx /= 2;
            //비트 연산 : idx >>= 1
        }
    }
    public static long query(int start, int end) {
        long result = 0;
        while (start < end) {
            //start가 홀수 : right 노드
            if(start % 2 == 1) result += indexedTree[start];
            //end가 짝수 : left 노드
            if(end % 2 == 0) result += indexedTree[end];
            //상위 노드로 이동
            start = (start + 1) / 2;
            end = (end - 1) / 2;
            //비트 연산 사용하려면 : start = (start + 1) >> 1;
        }
        if(start == end) result += indexedTree[start];
        return result;
    }
}
