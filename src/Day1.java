import java.util.Arrays;
import java.util.List;

public class Day1 implements AoCDay {
    static String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    private final List<String> input;

    public Day1(List<String> input) {
        this.input = input;
    }

    static int strToNum(String str) {
        return switch (str) {
            case "one" -> 1;
            case "two" -> 2;
            case "three" -> 3;
            case "four" -> 4;
            case "five" -> 5;
            case "six" -> 6;
            case "seven" -> 7;
            case "eight" -> 8;
            case "nine" -> 9;
            default -> -1;
        };
    }

    @Override
    public int part1() {
        int sum = 0;

        for (String s : input) {
            int minPos = Arrays.stream(numbers).limit(9).mapToInt(s::indexOf).filter((index) -> index >= 0).min().getAsInt();
            sum += (s.charAt(minPos) - '0') * 10;
            int maxPos = Arrays.stream(numbers).limit(9).mapToInt(s::lastIndexOf).filter((index) -> index >= 0).max().getAsInt();
            sum += (s.charAt(maxPos) - '0');
        }

        return sum;
    }

    @Override
    public int part2() {
        int sum = 0;

        for (String s : input) {
            int minPos = Arrays.stream(numbers).mapToInt(s::indexOf).filter((index) -> index >= 0).min().getAsInt();
            int value = s.charAt(minPos) - '0';
            if (value < 0 || value > 9) {
                value = strToNum(Arrays.stream(numbers).skip(9).filter((num) -> s.startsWith(num, minPos)).findFirst().get());
            }
            sum += value * 10;

            int maxPos = Arrays.stream(numbers).mapToInt(s::lastIndexOf).filter((index) -> index >= 0).max().getAsInt();
            value = s.charAt(maxPos) - '0';
            if (value < 0 || value > 9) {
                value = strToNum(Arrays.stream(numbers).skip(9).filter((num) -> s.startsWith(num, maxPos)).findFirst().get());
            }
            sum += value;
        }

        return sum;
    }
}
