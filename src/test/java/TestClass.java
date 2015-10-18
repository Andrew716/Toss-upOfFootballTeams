import junit.framework.TestCase;
import org.junit.Test;
import java.util.List;

/**
 * Created by Andrii on 10/18/2015.
 */
public class TestClass extends TestCase {
    @Test
    public void testForReadFromFile() throws Exception{
        WorkWithFile.readFromFile("C:\\Users\\Andrii\\IdeaProjects\\GIT\\Toss-upOfFootballTeams\\src\\test\\java\\asdfasdf.exe");
        WorkWithFile.readFromFile("C:\\Users\\Andrii\\IdeaProjects\\GIT\\Toss-upOfFootballTeams\\src\\test\\java\\sortition.doc");
        WorkWithFile.readFromFile("C:\\Users\\Andrii\\IdeaProjects\\GIT\\Toss-upOfFootballTeams\\src\\test\\java\\emptyFile.txt");
        WorkWithFile.readFromFile(null);
    }

    @Test
    public void testForDoSortion() throws Exception{
        List<String > stringList = null;
        WorkWithFile.doSortiton(stringList);
    }

    @Test
    public void testForPrintSortion(){
        List<String> stringList = null;
        WorkWithFile.printSortition(stringList);
    }
}
