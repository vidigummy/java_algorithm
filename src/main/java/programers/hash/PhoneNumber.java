package programers.hash;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public class PhoneNumber {
    public static boolean solution(String[] phone_book) {
        boolean answer = true;
        HashMap<String, Integer> hashMap= new HashMap<>();
        for(String phoneNumber : phone_book){
            hashMap.put(phoneNumber, phoneNumber.length());
        }
        for(int i = 0 ; i < phone_book.length; i++){
            for(int j = 0 ; j < phone_book[i].length(); j++){
                if(hashMap.containsKey(phone_book[i].substring(0,j))){
                    return false;
                }
            }
        }
        return answer;
    }
    static public void main(String[] args){
        String[] phone_book = {"123","456","789"};
        boolean a = solution(phone_book);
        System.out.println(a);
    }
}
