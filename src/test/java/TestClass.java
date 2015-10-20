import junit.framework.Assert;
import org.junit.Test;

import java.io.*;

import java.util.HashSet;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Andrii on 10/18/2015.
 */
public class TestClass {

    protected final String pathToEmptyFile = "\\src\\main\\java\\emptyFile.txt";
    protected final String pathToOddNumStrFile = "\\src\\main\\java\\FileWithOddNumberOfStrings.txt";
    protected final String pathToSpaceFile = "\\src\\main\\java\\fileConsistsOfSpace";
    protected final String pathToNumericFile = "\\src\\main\\java\\fileConsistsOfNumeric.txt";
    protected final String pathToDocFile = "\\src\\main\\java\\sortition.doc";
    protected final String pathToExeFile = "\\src\\main\\java\\asdfasdf.exe";
    protected final String pathToGoodFile = "C:\\Users\\Andrii\\IdeaProjects\\GIT\\Toss-upOfFootballTeams\\src\\main\\java\\GoodFile.txt";

    @Test
    public void testReadFromGoodFile(){
        Set<String> expectedList = new HashSet<String>();
        Set<String> realList = SortitionClass.readFromFile(pathToGoodFile);
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


    //TODO
    @Test(expected = FileNotFoundException.class)
    public void testReadFromFileDocFile(){
         SortitionClass.readFromFile(pathToDocFile);
       //throw new FileNotFoundException();
    }


    //TODO
    @Test(expected = FileNotFoundException.class)
    public void testReadFromFileExeFile(){
        SortitionClass.readFromFile(pathToExeFile);
        try {
            throw new FileNotFoundException();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
    public void testIsCountry(){
        boolean flagCountry = SortitionClass.isItCountry("Ukraine");
        Assert.assertTrue(flagCountry);
        flagCountry = SortitionClass.isItCountry("eqrwe12312");
        Assert.assertFalse(flagCountry);
    }
}
