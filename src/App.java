import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        String text = "the cat is in the bag";
        System.out.println(text);
        getFrequency(text).forEach((k,v)-> System.out.println(v+" "+k));
    }

    private static Map<String, Long> getFrequency(String text) {
        Map<String, Long> frequencyMap = new HashMap<>();
        String[] wordList = text.split("\\s");
        Stream.of(wordList)
                .collect(Collectors.groupingBy(k -> k, ()-> frequencyMap,
                        Collectors.counting()));
        return sortMap(frequencyMap);
    }

    private static Map<String, Long> sortMap(Map<String, Long> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
