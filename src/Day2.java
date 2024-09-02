import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2 implements AoCDay {
    private static final int MAX_RED = 12;
    private static final int MAX_GREEN = 13;
    private static final int MAX_BLUE = 14;

    private final List<Game> games;

    public Day2(List<String> input) {
        this.games = new ArrayList<>();

        for (String line : input) {
            this.games.add(new Game(line));
        }
    }

    @Override
    public int part1() {
        int sum = 0;

        outer:
        for (Game game : games) {
            for (int i = 0; i < game.counts.length; i++) {
                for (int j = 0; j < game.counts[i].length; j++) {
                    int max = 0;
                    switch (game.colors[i][j]) {
                        case Red -> max = MAX_RED;
                        case Green -> max = MAX_GREEN;
                        case Blue -> max = MAX_BLUE;
                    }
                    if (game.counts[i][j] > max) {
                        continue outer;
                    }
                }
            }
            sum += game.id;
        }

        return sum;
    }

    @Override
    public int part2() {
        int sum = 0;

        for (Game game : games) {
            int maxRed = 0;
            int maxGreen = 0;
            int maxBlue = 0;
            for (int i = 0; i < game.counts.length; i++) {
                for (int j = 0; j < game.counts[i].length; j++) {
                    switch (game.colors[i][j]) {
                        case Red -> maxRed = Math.max(maxRed, game.counts[i][j]);
                        case Green -> maxGreen = Math.max(maxGreen, game.counts[i][j]);
                        case Blue -> maxBlue = Math.max(maxBlue, game.counts[i][j]);
                    }
                }
            }
            sum += maxRed * maxGreen * maxBlue;
        }

        return sum;
    }

    private static class Game {
        int id;
        int[][] counts;
        Color[][] colors;

        public Game(String game) {
            id = Integer.parseInt(game.split(":")[0].substring(5));
            String setsStr = game.substring(game.indexOf(':') + 2);
            String[] sets = setsStr.split(";");

            counts = new int[sets.length][];
            colors = new Color[sets.length][];
            for (int i = 0; i < sets.length; i++) {
                String set = sets[i].trim();
                String[] setInfo = set.split(",");
                counts[i] = new int[setInfo.length];
                colors[i] = new Color[setInfo.length];
                for (int j = 0; j < setInfo.length; j++) {
                    String info = setInfo[j].trim();
                    String[] infoSplit = info.split(" ");
                    counts[i][j] = Integer.parseInt(infoSplit[0]);
                    colors[i][j] = Color.fromString(infoSplit[1]);
                }
            }
        }

        public String toString() {
            return "ID: " + id + "\n" + Arrays.deepToString(counts) + "\n" + Arrays.deepToString(colors) + "\n" + "\n";
        }

        private enum Color {
            Green, Red, Blue;

            public static Color fromString(String color) {
                return switch (color) {
                    case "green" -> Color.Green;
                    case "red" -> Color.Red;
                    case "blue" -> Color.Blue;
                    default -> null;
                };
            }

            @Override
            public String toString() {
                return switch (this) {
                    case Green -> "green";
                    case Red -> "red";
                    case Blue -> "blue";
                };
            }
        }
    }
}
