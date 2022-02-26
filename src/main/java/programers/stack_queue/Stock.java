package programers.stack_queue;

import java.util.*;
//시간 초과 남... ㅅㅂ 무슨 알고리즘이지

public class Stock {
    public static int[] solution(int[] prices) {
        List<Integer> answer_real = new ArrayList<>();
        Queue<HashMap<Integer,Integer>> price_queue = new LinkedList<>();
        for(int i = 0; i < prices.length; i++){
            HashMap<Integer,Integer> tmp = new HashMap<>();
            tmp.put(prices[i],i+1);
            price_queue.add(tmp);
        }
        for(int i = 0 ; i < prices.length; i++){
//            System.out.println(price_queue.toString());
            Queue<HashMap<Integer,Integer>> next_price_queue = new LinkedList<>();
            Iterator<HashMap<Integer, Integer>> iterator_A = price_queue.iterator();
            int cnt = 0;
            boolean isIt = false;
            if(iterator_A.hasNext()){
                HashMap<Integer,Integer> first = iterator_A.next();
                Iterator<Integer> first_price_it = first.keySet().iterator();
                Integer first_price = first_price_it.next();
                while(iterator_A.hasNext()){
                    HashMap<Integer, Integer> nth = iterator_A.next();
                    Iterator<Integer> nth_price_it = nth.keySet().iterator();
                    Integer nth_price = nth_price_it.next();
                    if((first_price > nth_price) && !isIt){
                        cnt = nth.get(nth_price)-first.get(first_price);
//                        answer_real.add(cnt);
                        isIt = true;
                        answer_real.add(cnt);
//                        System.out.println(cnt);
                        next_price_queue.add(nth);
                    }else{
                        next_price_queue.add(nth);
                    }
                }
                if(!isIt){
//                    System.out.println(prices.length-first.get(first_price));
                    answer_real.add(prices.length-first.get(first_price));
                }
            }

            price_queue = next_price_queue;

        }
//        System.out.println("answer :" + answer_real.toString());
        int[] answer = new int[answer_real.size()];
        int i = 0;
        for(int a : answer_real){
            answer[i] = a;
            i++;
        }

        return answer;
    }

    public static void main(String[] args){
        int[] prices = {1, 2, 3, 2, 3};
        int[] answer = solution(prices);

    }
}
