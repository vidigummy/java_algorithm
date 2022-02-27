package programers.heap;

import java.util.TreeMap;

public class DoubleTree {
    public static int[] solution(String[] operations) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(String op : operations){
            String[]args = op.split(" ");
            if(args[0].equals("I")){
                int val = Integer.parseInt(args[1]);
                map.put(val,val);
            }else{
                if(!map.isEmpty()){
                    if(args[1].equals("1")){
                        map.remove(map.lastKey());
                    }else{
                        map.remove(map.firstKey());
                    }
                }
//                System.out.println(map.toString());
            }
        }
        if(map.isEmpty()){
            return new int[]{0,0};
        }else{
            int max = map.lastKey();
            int min = map.firstKey();
            return new int[]{max, min};
        }

    }
    public static void main(String[] args){
//        String[] operations = {"I 10000", "D -1", "I 4", "I 3", "I 2", "I 1"};
//        String[] operations = {"I 1"};
//        String[] operations = {"I -45","I 653", "D 1", "I -652", "I 45", "I 97", "D 1","D -1", "I 333"};
        String[] operations = {"I 16","I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        int[] ans = solution(operations);
        for(int a : ans){
            System.out.println(a);
        }
    }

}
