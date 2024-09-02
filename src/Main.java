import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Main <day>");
            return;
        }

        int day;
        try {
            day = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }

        List<String> input = getInputLines(day);
        System.out.println(input);
    }

    private static List<String> getInputLines(int day) {
        String file = "./resources/day" + day + ".txt";
        try {
            return Files.readAllLines(FileSystems.getDefault().getPath(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}