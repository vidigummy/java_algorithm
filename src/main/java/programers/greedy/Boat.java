package programers.greedy;

import java.util.*;

public class Boat {
    public static int solution(int[] people, int limit) throws InterruptedException {
        int answer = 0;
        int nowLen = people.length;
        Arrays.sort(people);
        List<Integer> arr = new ArrayList<>();
        for(Integer person:people){
            arr.add(person);
        }
        while (!arr.isEmpty()){
//            System.out.println(arr.toString());
            List<Integer> remove_arr = new ArrayList<>();
            int nowWeight = arr.get(0);

            remove_arr.add(0);
            for(int i= nowLen-1; i >0; i--){
                if(arr.get(i)+nowWeight <= limit){
                    nowWeight+= arr.get(i);
                    remove_arr.add(i);
                }
            }
//            System.out.println(remove_arr.toString());
            remove_arr.sort(Collections.reverseOrder());
            for(int i =0 ; i<remove_arr.size(); i++){
                int remove_index = remove_arr.get(i);
                arr.remove(remove_index);
                nowLen--;
            }
            answer++;
        }
        return answer;

    }
    public static void main(String[] args) throws InterruptedException {
        int[] people = {70, 80, 50};
        int limit = 100;
        int ans = solution(people,limit);
        System.out.println(ans);
    }
}
