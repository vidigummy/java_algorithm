package forPro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon2243 {


        static int tree[];
        static int N, S, A, B, C;

        public static void main(String[] args) throws Exception {
            BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;

            //포화 이진트리 생성을 위한 2^n>1000000인 S(=2^n) 찾기
            S=1;
            while(S<1000000) {
                S*=2;
            }
            tree= new int[S*2];

            N= Integer.parseInt(br.readLine());
            StringBuilder sb= new StringBuilder();
            for(int i=0; i<N; i++) {
                st= new StringTokenizer(br.readLine());
                A= Integer.parseInt(st.nextToken());
                if(A==1) {
                    B= Integer.parseInt(st.nextToken());
                    //사탕 꺼내기, B번째
                    int index= pickup(1, S, 1, B);
                    sb.append(index+"\n");
                    update(1, S, 1, index, -1);
                }else {
                    B= Integer.parseInt(st.nextToken());
                    C= Integer.parseInt(st.nextToken());
                    //사탕 넣기 B맛 C개(양수+, 음수-)
                    update(1, S, 1, B, C);
                }
            }
            System.out.println(sb.toString());
        }//main

        //left, right는 범위(1~S), node: 현재 위치한 노드의 인덱스, target: 목표 노드
        public static int pickup(int left, int right, int node, int target) {
            if(left==right) return left;

            int mid= (left+right)/2;
            if(tree[node*2]>=target) { //왼쪽 노드이면 (target그대로)
                return pickup(left, mid, node*2, target);
            }else { //오른쪽 노드이면 (target-왼쪽 노드)
                return pickup(mid+1, right, node*2+1, target-tree[node*2]);
            }
        }

        //(left, right, node, target, diff: 더하거나 뺄 사탕개수)
        public static void update(int left, int right, int node, int target, int diff) {
            //타겟이 범위값 벗어나면 종료
            if(target<left||right<target) return;

            tree[node]+=diff;
            if(left!=right) {
                int mid= (left+right)/2;
                update(left, mid, node*2, target, diff);
                update(mid+1, right, node*2+1, target, diff);
            }
        }
}

