import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day4 implements AoCDay {

    private final List<Card> cards;

    public Day4(List<String> input) {
        cards = new ArrayList<>(input.size());
        for (String line : input) {
            String numbers = line.split(":")[1].trim();
            String[] numbersSplit = numbers.split("\\|");
            int[] winningNumbers = Arrays.stream(numbersSplit[0].trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int[] ownedNumbers = Arrays.stream(numbersSplit[1].trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();

            cards.add(new Card(winningNumbers, ownedNumbers));
        }
    }

    @Override
    public int part1() {
        return cards.stream().mapToInt(Card::getPoints).sum();
    }

    @Override
    public int part2() {
        int[] winningCounts = cards.stream().mapToInt(Card::getMatchingCount).toArray();
        int[] counts = new int[winningCounts.length];
        Arrays.fill(counts, 1);
        for (int i = 0; i < winningCounts.length; i++) {
            for (int j = 0; j < winningCounts[i] && i + j + 1 < counts.length; j++) {
                counts[i + j + 1] += counts[i];
            }
        }
        return Arrays.stream(counts).sum();
    }

    static class Card {
        int[] winningNumbers;
        int[] ownedNumbers;

        public Card(int[] winningNumbers, int[] ownedNumbers) {
            this.winningNumbers = winningNumbers;
            this.ownedNumbers = ownedNumbers;
        }

        public int getPoints() {
            int points = 0;
            for (int winningNumber : winningNumbers) {
                if (Arrays.stream(ownedNumbers).anyMatch(i -> i == winningNumber)) {
                    if (points == 0) {
                        points = 1;
                    } else {
                        points *= 2;
                    }
                }
            }
            return points;
        }

        public int getMatchingCount() {
            int count = 0;
            for (int winningNumber : winningNumbers) {
                if (Arrays.stream(ownedNumbers).anyMatch(i -> i == winningNumber)) {
                    count += 1;
                }
            }
            return count;
        }
    }
}
