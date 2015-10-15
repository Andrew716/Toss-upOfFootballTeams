import java.util.Scanner;

/**
 * Created by Andrii on 10/15/2015.
 */
public class Main {
    public static void main(String[] args){
        System.out.println("Welcome to the toss-up program!\n Please, input path to your file which contains teams and you obtain a sortition. Enjoy it!\n ");
        Scanner scanner = new Scanner(System.in);
        String pathFile = scanner.next();
        scanner.close();
        WorkWithFile.printSortition(WorkWithFile.doSortiton(WorkWithFile.readFromFile(pathFile)));

    }
}
