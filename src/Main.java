import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Main <day>");
            return;
        }

        int dayVal;
        try {
            dayVal = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }

        AoCDay day;
        try {
            day = getDay(dayVal);

            if (day == null) {
                return;
            }

            System.out.println("Part 1: " + day.part1());
            System.out.println("Part 2: " + day.part2());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static AoCDay getDay(int day) throws IOException {
        String file = "./resources/day" + day + ".txt";

        List<String> input = Files.readAllLines(FileSystems.getDefault().getPath(file));

        return switch (day) {
            case 1 -> new Day1(input);
            case 2 -> new Day2(input);
            default -> null;
        };
    }
}