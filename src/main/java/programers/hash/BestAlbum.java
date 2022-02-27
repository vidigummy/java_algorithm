package programers.hash;

import java.util.*;

public class BestAlbum {
    public static int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        List<Integer> result = new ArrayList<>();
        HashMap<String,Integer> top_genre = new HashMap<>();
        for(int i=0; i < genres.length;i++){
            if(top_genre.containsKey(genres[i])){
                top_genre.put(genres[i],top_genre.get(genres[i])+ plays[i]);
            }else{
                top_genre.put(genres[i],plays[i]);
            }
        }
        Comparator<Map.Entry<String, Integer>> comparator1 = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        };
        Comparator<Map.Entry<Integer, Integer>> comparator = new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        };
        while(!top_genre.isEmpty()){
            Map.Entry<String, Integer> top_genre_entry = Collections.max(top_genre.entrySet(), comparator1);
            int cnt  = 0;
            String top_genre_name = top_genre_entry.getKey();
            HashMap<Integer,Integer> in_genre_songs = new HashMap<>();
            for(int i = 0; i < genres.length; i++){
                if(genres[i].equals(top_genre_name)){
                    in_genre_songs.put(i, plays[i]);
                }
            }
            top_genre.remove(top_genre_name);
//            System.out.println(top_genre_name);
//            System.out.println(in_genre_songs.toString());
            while(!in_genre_songs.isEmpty() && cnt < 2){
                cnt++;
                Map.Entry<Integer, Integer> top_song_entry = Collections.max(in_genre_songs.entrySet(), comparator);
//                System.out.println(top_song_entry.getKey());
                result.add(top_song_entry.getKey());
                in_genre_songs.remove(top_song_entry.getKey());
            }

        }
        answer = new int[result.size()];
        int i = 0;
        for(Integer a:result){
            answer[i] = a;
            i++;
        }
        return answer;
    }

    public static void main(String[] args){
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        int[] ans = solution(genres,plays);
        for(int i : ans){
            System.out.print(i+" ");
        }
    }
}
