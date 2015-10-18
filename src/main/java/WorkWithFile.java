import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.IOException;
import java.lang.IllegalArgumentException;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.Logger;
import java.lang.NullPointerException;

/**
 * Created by Andrii on 10/15/2015.
 */
public class WorkWithFile {

    private final static String regex = "^([a-zA-Z]{2,40})|(([a-zA-Z]){2,40}(\\s)([a-zA-Z]){2,40})|([a-zA-Z]){2,40}(\\s)([a-zA-Z]){2,40}(\\s)([a-zA-Z]){2,40}$";
    private final static Logger LOGGER = Logger.getLogger(WorkWithFile.class.getName());

    public static List<String> readFromFile(String pathFile){
        try {
            if (pathFile == null){
                throw new NullPointerException();
            }
        }catch (NullPointerException e){
            LOGGER.warning("NullPointerException has been just generated!!! Check out your input data!!!");
        }
        List<String> teamsList = new ArrayList<String>();
        LOGGER.setLevel(Level.INFO);
        int counter = 0;
        try {
            String string;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathFile));
            try {
                while ((string = bufferedReader.readLine()) != null){
                    if ((!string.isEmpty()) && (isItCountry(string))){
                        teamsList.add(string);
                        counter++;
                    }
                }
            } finally {
                bufferedReader.close();
            }
        } catch (IOException e){
            LOGGER.warning("We are really sorry, but we couldn't do a toss-up :( Your file has not been found!\n Please, check out path to your file or extension of file, it should be .txt or .text.");
        }
        try {
            if (counter %2 == 1){
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e){
            LOGGER.warning("We are really sorry, but we couldn't do a toss-up :(\n You should have even number of teams");
        }
        return teamsList;
    }

    private static boolean isItCountry(String str){
        boolean flag = false;
        String regexForCountry = regex;
        Pattern patternForOneWord = Pattern.compile(regexForCountry);
        Matcher matcherOneWord = patternForOneWord.matcher(str);
        if (matcherOneWord.matches()){
            flag = true;
        }
        return flag;
    }

    public static List<String> doSortiton(List<String> teamList){
        try {
            if (teamList == null){
                throw new NullPointerException();
            }
        }catch (NullPointerException e){
            LOGGER.warning("NullPointerException has been just generated!!! Check out you input data!!!");
        }
        List<String> sortedTeamList = new ArrayList<String>();
        Random random = new Random();
        int numberForSolvingWhoWillPlayFirstMatchAtHome = 1000;
        while (!teamList.isEmpty()){
            int firstGameAtHome = random.nextInt(teamList.size()-1);
            int firstGameAtGuests = random.nextInt(teamList.size()-1);
            if (firstGameAtHome != firstGameAtGuests){
                sortedTeamList.add(teamList.get(firstGameAtHome) + "  -  " + teamList.get(firstGameAtGuests));
                if (firstGameAtHome > firstGameAtGuests){
                    teamList.remove(firstGameAtHome);
                    teamList.remove(firstGameAtGuests);
                }else {
                    teamList.remove(firstGameAtGuests);
                    teamList.remove(firstGameAtHome);
                }
            }
            if (teamList.size() == 2){
                int temp = random.nextInt(numberForSolvingWhoWillPlayFirstMatchAtHome);
                if (temp >= numberForSolvingWhoWillPlayFirstMatchAtHome/2){
                    sortedTeamList.add(teamList.get(1) + "  -  " + teamList.get(0));
                    teamList.remove(1);
                    teamList.remove(0);
                }else {
                    sortedTeamList.add(teamList.get(0) + "  -  " + teamList.get(1));
                    teamList.remove(1);
                    teamList.remove(0);
                }
            }
        }
        return sortedTeamList;
    }

    public static void printSortition(List<String> sortedTeamList){
        try {
            if (sortedTeamList == null){
                throw new NullPointerException();
            }
        }catch (NullPointerException e){
            LOGGER.warning("NullPointerException has been just generated!!! Check out your input data");
        }
        for (int i = 0; i < sortedTeamList.size(); i++){
            System.out.println(sortedTeamList.get(i));
        }
    }
}
