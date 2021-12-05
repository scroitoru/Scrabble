package croitoru.scrabble;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class DictionaryServlet extends HttpServlet {
    ScrabbleDictionary dictionary;

    public DictionaryServlet() throws FileNotFoundException {
        dictionary = new ScrabbleDictionary();
    }

    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        PrintWriter out = response.getWriter();

        String word = request.getParameter("word");
        String definition;
        if (dictionary.wordPresent(word)){
            definition = dictionary.getDefinition(word);
        }else{
            definition = "Sorry, word not found";
        }
        out.println(definition);
    }
}
