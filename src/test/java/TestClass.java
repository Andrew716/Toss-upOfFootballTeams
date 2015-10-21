import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.io.File;
import java.util.regex.Pattern;

/**
 * Created by Andrii on 10/18/2015.
 */
public class TestClass {

    protected String pathToEmptyFile;
    protected String pathToOddNumStrFile;
    protected String pathToSpaceFile;
    protected String pathToNumericFile;
    protected String pathToDocFile;
    protected String pathToExeFile;
    protected  String pathToGoodFile;

    @Before
    public void setUp(){
        this.pathToOddNumStrFile = new File("src\\main\\resources\\files\\FileWithOddNumb.txt").getAbsolutePath();
        this.pathToSpaceFile = new File("src\\main\\resources\\files\\fileConsistsOfSpace.txt").getAbsolutePath();
        this.pathToNumericFile = new File("src\\main\\resources\\files\\fileConsistsOfNumeric.txt").getAbsolutePath();
        this.pathToDocFile = new File("src\\main\\resources\\files\\docFile.doc").getAbsolutePath();
        this.pathToExeFile = new File("src\\main\\resources\\files\\exeFile.exe").getAbsolutePath();
        this.pathToEmptyFile = new File("src\\main\\resources\\files\\emptyFile.txt").getAbsolutePath();
        this.pathToGoodFile = new File("src\\main\\resources\\files\\GoodFile.txt").getAbsolutePath();
    }

    @Test
    public void testReadFromGoodFile(){
        Set<String> expectedList = new HashSet<String>();
        Set<String> realList = SortitionClass.readFromFile(pathToGoodFile);
        System.out.println(pathToGoodFile);
        try {
            int counter = 0;
            String string;
            String regexForCountry = "^([a-zA-Z]{2,40})|(([a-zA-Z]){2,40}(\\s)([a-zA-Z]){2,40})|([a-zA-Z]){2,40}(\\s)([a-zA-Z]){2,40}(\\s)([a-zA-Z]){2,40}$";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToGoodFile));
            while ((string = bufferedReader.readLine()) != null) {
                boolean flag = false;
                Pattern patternForOneWord = Pattern.compile(regexForCountry);
                Matcher matcherOneWord = patternForOneWord.matcher(string);
                if (matcherOneWord.matches()){
                    flag = true;
                }
                if ((!string.isEmpty()) && flag) {
                    expectedList.add(string);
                    counter++;
                }
            }
            bufferedReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        Assert.assertEquals(expectedList, realList);
    }

    @Test(expected = NullPointerException.class)
    public void testReadFromFileNullPath(){
        SortitionClass.readFromFile(null);
        throw new NullPointerException();
    }

    @Test(expected = FileNotFoundException.class)
    public void testReadFromFileDocFile() throws FileNotFoundException{
        SortitionClass.readFromFile(pathToDocFile);
        throw new FileNotFoundException();
    }

    @Test(expected = FileNotFoundException.class)
    public void testReadFromFileExeFile() throws FileNotFoundException{
        SortitionClass.readFromFile(pathToExeFile);
       throw new FileNotFoundException();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadFromFileWithOddNumberOfStrings(){
        SortitionClass.readFromFile(pathToOddNumStrFile);
        throw new IllegalArgumentException();
    }

    @Test
    public void testReadFromFileConsistsOfNumerics(){
        Set<String> expectedList = new HashSet<String>();
        Set<String> listForCompare = SortitionClass.readFromFile(pathToNumericFile);
        Assert.assertEquals(expectedList, listForCompare);
    }

    @Test
    public void testReadFromFileSpaceFile(){
        Set<String> expectedList = new  HashSet<String>();
        Set<String> realList = SortitionClass.readFromFile(pathToSpaceFile);
        Assert.assertEquals(expectedList, realList);
    }

    @Test
    public void testReadFromFileEmptyFile(){
        Set<String> listForCompare = SortitionClass.readFromFile(pathToEmptyFile);
        Set<String> trueList = new HashSet<String>();
        Assert.assertEquals(trueList,listForCompare);
    }

    @Test(expected = NullPointerException.class)
    public void testDoSortion(){
        SortitionClass.doSortiton(null);
        throw new NullPointerException();
    }

    @Test()
    public void testIsCountry() {
        boolean flagCountry = SortitionClass.isItCountry("Ukraine");
        Assert.assertTrue(flagCountry);
        flagCountry = SortitionClass.isItCountry("eqrwe12312");
        Assert.assertFalse(flagCountry);
    }

    @After
    public void tearDown(){

    }
}
