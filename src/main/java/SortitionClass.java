import java.io.*;
import java.util.*;
import java.lang.IllegalArgumentException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.Logger;


/**
 * Created by Andrii on 10/15/2015.
 */

public class SortitionClass {

    private final static String regexForCountry = "^([a-zA-Z]{2,40})|(([a-zA-Z]){2,40}(\\s)([a-zA-Z]){2,40})|([a-zA-Z]){2,40}(\\s)([a-zA-Z]){2,40}(\\s)([a-zA-Z]){2,40}$";
    private final static Logger LOGGER = Logger.getLogger(SortitionClass.class.getName());

    public static Set<String> readFromFile(String pathFile){
        Set<String> teamsList = new HashSet<String>();
        int counter = 0;
        try {
            File file = new File(pathFile);
            if (file.exists() == false){
                throw new FileNotFoundException();
            }
            String string;
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathFile));
                while ((string = bufferedReader.readLine()) != null){
                    if ((!string.isEmpty()) && (isItCountry(string))){
                        teamsList.add(string);
                        counter++;
                    }
                }
                bufferedReader.close();
            if (counter %2 == 1){
                throw new IllegalArgumentException();
            }
        }catch (FileNotFoundException e){
            LOGGER.warning("File not found! Set new direction!");
        }catch (IOException e){
            LOGGER.warning("We are really sorry, but we couldn't do a toss-up :( Your file has not been found!\n Please, check out path to your file or extension of file, it should be .txt or .text.");
        } catch (IllegalArgumentException e){
            LOGGER.warning("We are really sorry, but we couldn't do a toss-up :(\n You should have even number of teams");
        }
        return teamsList;
    }

    public static boolean isItCountry(String str){
        boolean flag = false;
        Pattern patternForOneWord = Pattern.compile(regexForCountry);
        Matcher matcherOneWord = patternForOneWord.matcher(str);
        if (matcherOneWord.matches()){
            flag = true;
        }
        return flag;
    }

    public static Set<String> doSortiton(Set<String> setTeamList){
        Set<String> sortedTeamList = new HashSet<String>();
        List<String> teamList = new ArrayList<String>();
        Iterator<String> iterator = setTeamList.iterator();
        while (iterator.hasNext()){
            teamList.add(iterator.next());
        }
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
}
