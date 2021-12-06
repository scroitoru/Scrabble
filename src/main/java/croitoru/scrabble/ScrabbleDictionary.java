package croitoru.scrabble;

import java.io.*;
import java.util.*;

//Dictionary program for a Scrabble game
public class ScrabbleDictionary {

    private final Map<String, String> wordsToDefinitions = new HashMap<>();

    public ScrabbleDictionary() throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream("dictionary.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = reader.readLine()) != null) {
            int index = line.indexOf(" ");
            String word = index == -1 ? line : line.substring(0, index);
            String definition = index > -1 ? line.substring(index + 1) : null;
            wordsToDefinitions.put(word, definition);
        }
//        Scanner sc = new Scanner(new FileReader("dictionary.txt"));
//        while (sc.hasNext()){
//            wordsToDefinitions.put(
//                    sc.next(),
//                    sc.nextLine().trim()
//            );
//        }
    }

    public boolean wordPresent(String word){
        return wordsToDefinitions.containsKey(word.toUpperCase());
    }

    public String getDefinition(String word){
        String definition = wordsToDefinitions.get(word.toUpperCase());
        return definition == null ? "" : definition;
    }

    public int size(){
        return wordsToDefinitions.size();
    }
}