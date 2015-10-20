import junit.framework.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii on 10/18/2015.
 */
public class TestClass {

    protected final String pathToEmptyFile = "C:\\Users\\Andrii\\IdeaProjects\\GIT\\Toss-upOfFootballTeams\\src\\test\\java\\emptyFile.txt";
    protected final String pathToOddNumStrFile = "C:\\Users\\Andrii\\IdeaProjects\\GIT\\Toss-upOfFootballTeams\\src\\test\\java\\FileWithOddNumberOfStrings.txt";
    protected final String pathToSpaceFile = "C:\\Users\\Andrii\\IdeaProjects\\GIT\\Toss-upOfFootballTeams\\src\\test\\java\\fileConsistsOfSpace";
    protected final String pathToNumericFile = "C:\\Users\\Andrii\\IdeaProjects\\GIT\\Toss-upOfFootballTeams\\src\\test\\java\\fileConsistsOfNumeric.txt";
    protected final String pathToDocFile = "C:\\Users\\Andrii\\IdeaProjects\\GIT\\Toss-upOfFootballTeams\\src\\test\\java\\sortition.doc";
    protected final String pathToExeFile = "C:\\Users\\Andrii\\IdeaProjects\\GIT\\Toss-upOfFootballTeams\\src\\test\\java\\asdfasdf.exe";

    @Test(expected = NullPointerException.class)
    public void testReadFromFileNullPath(){
        WorkWithFile.readFromFile(null);
    }

    @Test(expected = FileNotFoundException.class)
    public void testReadFromFileDocFile(){
        WorkWithFile.readFromFile(pathToDocFile);
    }

    @Test(expected = FileNotFoundException.class)
    public void testReadFromFileExeFile(){
        WorkWithFile.readFromFile(pathToExeFile);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testForReadFromFileWithOddNumberOfStrings(){
        WorkWithFile.readFromFile(pathToOddNumStrFile);
    }

    @Test
    public void testForReadFromFileConsistsOfNumerics(){
        List<String> expectedList = new ArrayList<String>();
        List<String> listForCompare = WorkWithFile.readFromFile(pathToNumericFile);
        Assert.assertEquals(expectedList, listForCompare);
    }

    @Test
    public void testForReadFromFileSpaceFile(){
        List<String> expectedList = new  ArrayList<String>();
        List<String> realList = WorkWithFile.readFromFile(pathToSpaceFile);
        Assert.assertEquals(expectedList, realList);
    }

    @Test
    public void testForReadFromFileEmptyFile(){
        List<String> listForCompare = WorkWithFile.readFromFile(pathToEmptyFile);
        List<String> trueList = new ArrayList<String>();
        Assert.assertEquals(trueList,listForCompare);
    }

    @Test(expected = NullPointerException.class)
    public void testForDoSortion(){
        WorkWithFile.doSortiton(null);
        throw new NullPointerException();
    }

    @Test()
    public void testIsCountry(){
        boolean flagCountry = WorkWithFile.isItCountry("Ukraine");
        Assert.assertTrue(flagCountry);
        flagCountry = WorkWithFile.isItCountry("eqrwe12312");
        Assert.assertFalse(flagCountry);
    }
}
