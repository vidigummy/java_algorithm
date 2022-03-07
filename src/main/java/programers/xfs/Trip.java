package programers.xfs;

import java.util.*;

public class Trip {
//    모든 공항은 알파벳 대문자 3글자로 이루어집니다.
//    주어진 공항 수는 3개 이상 10,000개 이하입니다.
//    tickets의 각 행 [a, b]는 a 공항에서 b 공항으로 가는 항공권이 있다는 의미입니다.
//    주어진 항공권은 모두 사용해야 합니다.
//    만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다.
//    모든 도시를 방문할 수 없는 경우는 주어지지 않습니다.
    static HashMap<String, List<String>> airports = new HashMap<>();
    static HashMap<String, Boolean> isVisit = new HashMap<>();
    static List<String> trip = new ArrayList<>();
    public static String[] solution(String[][] tickets) {
        String[] answer = {};
        for(String[] ticket : tickets){
            String departure = ticket[0];
            String destination = ticket[1];
            isVisit.put(departure,false);
            isVisit.put(destination,false);
            if(airports.containsKey(departure)){
                List<String> destinations = airports.get(departure);
                destinations.add(destination);
                Collections.sort(destinations);
                airports.put(departure, destinations);
            }else{
                List<String> destinations = new ArrayList<>();
                destinations.add(destination);
                airports.put(departure, destinations);
            }
            if(!airports.containsKey(destination)){
                List<String> tmp = new ArrayList<>();
                airports.put(destination, tmp);
            }
        }
//        System.out.println(airports);
        dfs("ICN");
        answer = trip.toArray(new String[0]);
        return answer;
    }

    public static void dfs(String now){
        isVisit.put(now, true);
        trip.add(now);
//        System.out.print(now+ " ");
        while(!airports.get(now).isEmpty()){

            List<String> now_tmp = airports.get(now);
            String tmp = now_tmp.get(0);
            if(!airports.get(tmp).isEmpty()){
                now_tmp.remove(0);
                airports.put(now,now_tmp);
                dfs(tmp);
            }else{
                now_tmp.remove(0);
                now_tmp.add(tmp);
                airports.put(now,now_tmp);
            }
//            System.out.println(airports);
        }
        return;
    }
    public static void main(String[] args){
        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
//        String[][] tickets = {{"ICN", "JFK"},{"ICN","ATL"},{"SFO","ATL"},{"ATL","ICN"},{"ATL","SFO"}};

        String[] ans = solution(tickets);
        System.out.println("ans");
        for(String a : ans){
            System.out.print(a+" ");
        }
    }

}
