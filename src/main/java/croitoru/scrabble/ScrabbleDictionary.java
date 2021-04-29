package croitoru.scrabble;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

//Dictionary program for a Scrabble game
public class ScrabbleDictionary {

    private final Map<String, String> wordsToDefinitions = new HashMap<>();

    public ScrabbleDictionary() throws FileNotFoundException{
        Scanner sc = new Scanner(new FileReader("dictionary.txt"));
        while (sc.hasNext()){
            wordsToDefinitions.put(
                    sc.next(),
                    sc.nextLine().trim()
            );
        }
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