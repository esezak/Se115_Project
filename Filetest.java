import java.util.Scanner;
import java.nio.file.Paths;
import java.util.Formatter;
import java.io.FileWriter;
public class Filetest {
    public static void main(String[] args) {
        Scanner reader = null;
        Scanner sc = null;
        Formatter formatter = null;
        FileWriter writer = null;
        String[] input = new String[2];
        String[] names = new String[11];
        String[] scores = new String[11];
        try {
            sc = new Scanner(System.in);
            System.out.print("Enter name: ");
            input[0] = sc.nextLine();
            System.out.print("Enter score: ");
            input[1] = sc.nextLine();
            Integer.parseInt(input[1]);
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        //---------------------read file-----------------
        try {
            reader = new Scanner(Paths.get("scores.txt"));
            int counter = 0;
            while (reader.hasNextLine()) {
                String[] info = reader.nextLine().split(",");
                names[counter] = info[0].trim();
                scores[counter] = info[1].trim();
                counter++;
            }
        } catch (Exception e) {
            System.out.println("It seems this is the first time playing.");
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        //------------------compare values and relist------------------
        for (int i = 0; i < names.length; i++) {
            if (scores[i] != null) {
                if (Integer.parseInt(scores[i]) < Integer.parseInt(input[1])) {
                    for (int j = names.length - 1; j > i; j--) {
                        names[j] = names[j - 1];
                        scores[j] = scores[j - 1];
                    }
                    names[i] = input[0];
                    scores[i] = input[1];
                    break;
                }
            } else if (scores[i] == null) {// if empty add the value
                scores[i] = input[1];
                names[i] = input[0];
                break;
            }
        }


        //------------------write file -------------

        try {
            writer = new FileWriter("scores.txt", false);
            for(int i=0; i< 10;i++){
                formatter = new Formatter(writer);
                if(scores[i]!=null) {
                    formatter.format("%s,%s\n", names[i], scores[i]);
                }
            }
            } catch (Exception e) {
                System.out.println("Something went wrong.");
        }finally{
            formatter.close();
        }




            //-------------Display Score list------------------
        for (int i = 0; i < names.length; i++) {
            if (names[i] == null) {
                System.out.println("Name: empty   Score: empty");
            } else {
                System.out.println("Name: " + names[i] + "   Score: " + scores[i]);
            }
        }
    }
}
