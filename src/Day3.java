import java.util.ArrayList;
import java.util.List;

public class Day3 implements AoCDay {
    private final ArrayList<Position> symbols;
    private final ArrayList<Position> numbers;

    public Day3(List<String> input) {
        symbols = new ArrayList<>();
        numbers = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            byte[] bytes = input.get(i).getBytes();
            for (int j = 0; j < bytes.length; j++) {
                if (bytes[j] >= '0' && bytes[j] <= '9') {
                    int end = j;
                    while (end < bytes.length && bytes[end] >= '0' && bytes[end] <= '9') {
                        end++;
                    }
                    String number = input.get(i).substring(j, end);
                    numbers.add(new Position(j, end - 1, i, number));
                    if (end != bytes.length) {
                        j = end - 1;
                    } else {
                        break;
                    }
                } else if (bytes[j] != '.') {
                    symbols.add(new Position(j, j, i, input.get(i).substring(j, j + 1)));
                }
            }
        }
    }

    @Override
    public int part1() {
        int sum = 0;
        for (Position number : numbers) {
            for (Position symbol : symbols) {
                if (symbol.nextTo(number)) {
                    sum += Integer.parseInt(number.value);
                    break;
                }
            }
        }
        return sum;
    }

    @Override
    public int part2() {
        int sum = 0;

        outer:
        for (Position symbol : symbols) {
            if (symbol.value.equals("*")) {
                int count = 0;
                int[] gearNum = new int[2];
                for (Position number : numbers) {
                    if (symbol.nextTo(number)) {
                        if (count == 2) {
                            continue outer;
                        }
                        gearNum[count] = Integer.parseInt(number.value);
                        count += 1;
                    }
                }
                if (count == 2) {
                    sum += gearNum[0] * gearNum[1];
                }
            }
        }
        return sum;
    }

    private static class Position {
        int x1, x2, y;
        String value;

        public Position(int x1, int x2, int y, String value) {
            this.x1 = x1;
            this.y = y;
            this.x2 = x2;
            this.value = value;
        }

        public boolean nextTo(Position p) {
            return x1 >= p.x1 - 1 && x2 <= p.x2 + 1 && p.y >= y - 1 && p.y <= y + 1;
        }

        @Override
        public String toString() {
            return value;
        }
    }

}
