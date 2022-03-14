package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MakingTree1991 {
    static String[] tree;
    static int size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int h = (int)Math.ceil(Math.log(N)/Math.log(2));
        size = (int)Math.pow(2,h+1) -1;
        tree = new String[size*2+2];
        for(int i =0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            if(i == 0){
                String parent = st.nextToken();
                tree[0] = parent;
                String leftChild = st.nextToken();
                tree[1] = leftChild;
                String rightChild = st.nextToken();
                tree[2] = rightChild;
            }else{
                String parent = st.nextToken();
                int nowIndex = Arrays.asList(tree).indexOf(parent)+1;
                String leftChild = st.nextToken();
                String rightChild = st.nextToken();
                if(nowIndex*2 > size-1){
                    continue;
                }
                if(!(leftChild.equals(".")&&rightChild.equals("."))){
                    tree[nowIndex*2-1] = leftChild;
                    tree[nowIndex*2] = rightChild;
                }
            }
        }
//        for(String i : tree){
//            System.out.print(i+" ");
//        }
        System.out.println("");
        preFix(0);
        System.out.println("");
        centerFix(0);
        System.out.println("");
        postFix(0);
    }
    static void preFix(int now){
        if(now > size-1){
            return;
        }
        if(tree[now] == null){
            return;
        }
        if(tree[now].equals(".")){
            return;
        }
        System.out.print(tree[now]);
        preFix((now+1)*2-1);
        preFix((now+1)*2);
    }
    static void centerFix(int now){
        if(now > size-1){
            return;
        }
        if(tree[now] == null){
            return;
        }
        if(tree[now].equals(".")){
            return;
        }
        centerFix((now+1)*2-1);
        System.out.print(tree[now]);
        centerFix((now+1)*2);
    }
    static void postFix(int now){
        if(now > size-1){
            return;
        }
        if(tree[now] == null){
            return;
        }
        if(tree[now].equals(".")){
            return;
        }
        postFix((now+1)*2-1);
        postFix((now+1)*2);
        System.out.print(tree[now]);

    }
}
