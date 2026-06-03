package pl.kinga.kolekcjegeneryki.collectionpuzzle;

import java.util.*;

public class CollectionPuzzle {
    public static void main(String[] args){
        //#Ex A
        Map<String, Integer> scores = new LinkedHashMap<>();
        scores.put("Anna", 10);
        scores.put("Kinga", 20);
        scores.put("Zofia", 30);
        scores.put("Anna", 40);

        System.out.println(scores.size()); //3
        System.out.println(scores.get("Anna")); //40

        for (String name : scores.keySet()) {
            System.out.print(name + " "); // Anna, Kinga, Zofia
        }

        //Ex B
        String sentence = "to be or not to be that is the question";
        Set<String> characters = new HashSet<>(List.of(sentence.split(" ")));
        System.out.println("Amount of unique words: " + characters.size());

        //EX C
        Queue<String> queue = new LinkedList<>();
        queue.offer("first");
        queue.offer("second");
        queue.offer("third");

        Deque<String> deque = new ArrayDeque<>();
        for (String q : queue){
            deque.push(q);
        }

        while (!deque.isEmpty()){
            System.out.println(deque.pop());
        }







  }
}
