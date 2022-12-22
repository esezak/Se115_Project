import java.io.*;
import java.util.Scanner;
public class HiScoreTracker {
    private File scoresFile;
    private String[] names;
    private int[] points;

    public HiScoreTracker() {
        scoresFile = new File("scores.txt");
        names = new String[10];
        points = new int[10];
    }


}
