package programers.hash;

import javax.lang.model.type.NullType;
import java.util.HashMap;
import java.util.Iterator;

public class Marathon {
    public static String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> participant_people = new HashMap<String, Integer>();
        for(String participant_person : participant){
            if(participant_people.containsKey(participant_person)){
                participant_people.put(participant_person , participant_people.get(participant_person)+1);
            }
            else{
                participant_people.put(participant_person, 1);
            }
        }

        for(String complete_person : completion){
            participant_people.put(complete_person, participant_people.get(complete_person)-1);
        }
        Iterator<String> keys = participant_people.keySet().iterator();

        while(keys.hasNext()){
            String key = keys.next();
            if(participant_people.get(key) >0 ){
                answer = key;
                break;
            }
        }
        return answer;
    }
    public static void main(String[] args){
//        System.out.println("hihi");
        String[] participant = {"mislav", "stanko", "mislav", "ana"};
        String[] completion = {"stanko", "ana", "mislav"};
        System.out.println(solution(participant, completion));
    }
}
