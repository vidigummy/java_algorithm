package programers.greedy;

import java.util.HashMap;

public class Joystick {
    static HashMap<String, Integer> alphaIndex = new HashMap<>();
    static int nameLen;

    public static int solution(String name) {
        int answer = 0;
        nameLen = name.length();
        char[] nowString = new char[nameLen];
        char[] nowStringB = new char[nameLen];
        String[] alphaArr = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        for (int i = 0; i < alphaArr.length; i++) {
            alphaIndex.put(alphaArr[i], Integer.min(i, 26 - i));
        }
        for (int i = 0; i < nameLen; i++) {
            nowString[i] = 'A';
            nowStringB[i] = 'A';
        }
//        System.out.println(alphaIndex);
        answer = Integer.min(change(nowString, name, 0, 0, 0), change(nowStringB, name, 0, 0, 1) );
        return answer;
    }

    public static int change(char[] now, String finalString, int cnt, int index, int flag) {

//        System.out.println(String.valueOf(now)+" cnt : "+cnt+" index : "+index+ " flag : "+flag);
        int new_cnt = cnt;
        if (String.valueOf(now).equals(finalString)) {
//            System.out.println("madeIt! cnt = "+new_cnt);
            return new_cnt;
        } else {
            if (now[index] != finalString.charAt(index)) {
//                System.out.println(now[index] + " != " + finalString.charAt(index));
                now[index] = finalString.charAt(index);
                new_cnt += alphaIndex.get(now[index] + "");
                if(String.valueOf(now).equals(finalString)){
                    return new_cnt;
                }
                if (flag == 0) {
                    if (index < nameLen - 1) {
                        int new_index = index+1;
                        new_cnt = new_cnt+1;
                        return change(now, finalString, new_cnt, new_index, flag);
                    } else {
                        return change(now, finalString, new_cnt, index, flag);
                    }
                } else {
                    if (index == 0) {
                        new_cnt = new_cnt+1;
                        return change(now, finalString, new_cnt, nameLen - 1, flag);
                    } else if (index != 1) {
                        int new_index = index-1;
                        new_cnt = new_cnt+1;
                        return change(now, finalString, new_cnt, new_index, flag);
                    } else {
                        return change(now, finalString, new_cnt, index, flag);
                    }
                }
            }else{
//                System.out.println(now[index] + " == " + finalString.charAt(index));
                if (flag == 0) {
                    if (index < nameLen - 1) {
                        int new_index = index+1;
                        new_cnt+=1;
                        return change(now, finalString, new_cnt, new_index, flag);
                    } else {
                        return new_cnt;
                    }
                } else {
                    if (index == 0) {
                        new_cnt += 1;
                        return change(now, finalString, new_cnt, nameLen - 1, flag);
                    } else if (index != 1) {
                        int new_index = index-1;
                        new_cnt +=1;
                        return change(now, finalString, new_cnt, new_index, flag);
                    } else {
                        return new_cnt;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String name = "AABAAAAABBB";
        int ans = solution(name);
        System.out.println(ans);
    }
}
