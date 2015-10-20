import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Andrii on 10/15/2015.
 */
public class Main {

    private final static Logger LOGGER = Logger.getLogger("Info logging");

    public static void printSortition(Set<String> sortedTeamList){
        Iterator<String> iterator = sortedTeamList.iterator();
        while (iterator.hasNext()){
            LOGGER.info(iterator.next());
        }

    }

    public static void main(String[] args){
        LOGGER.setLevel(Level.FINE);
        LOGGER.info("Welcome to the toss-up program!\n Please, input path to your file which contains teams and you obtain a sortition. Enjoy it!\n ");
        Scanner scanner = new Scanner(System.in);
        String pathFile = scanner.next();
        scanner.close();
        printSortition(SortitionClass.doSortiton(SortitionClass.readFromFile(pathFile)));
    }
}
