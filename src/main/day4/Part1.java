package main.day4;

import main.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static boolean isValid(int x, int y, List<List<Character>> charArray) {
        return x >= 0 && x < charArray.size() && y >= 0 && y < charArray.get(x).size();
    }

    public static boolean matchXmas(int x, int y, String xmas, int index, List<List<Character>> charArray, int dirX, int dirY) {
        if (index == xmas.length()) {
            return true;
        }

        if (isValid(x, y, charArray) && xmas.charAt(index) == charArray.get(x).get(y)) {
            return matchXmas(x + dirX, y + dirY, xmas, index + 1, charArray, dirX, dirY);
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        List<String> input = InputReader.readInputFile("input/day4.txt");
        List<List<Character>> charArray = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            charArray.add(new ArrayList<>());
            for (int j = 0; j < input.get(i).length(); j++) {
                charArray.get(i).add(input.get(i).charAt(j));
            }
        }
        List<List<Integer>> directions = List.of(List.of(0, 1), List.of(1, 0), List.of(1, 1), List.of(0, -1), List.of(-1, 0), List.of(-1, -1), List.of(1, -1), List.of(-1, 1));
        int solution = 0;
        for (int i = 0; i < charArray.size(); i++) {
            for (int j = 0; j < charArray.get(i).size(); j++) {
                for (int k = 0; k < directions.size(); k++) {
                    if (matchXmas(i, j, "XMAS", 0, charArray, directions.get(k).get(0), directions.get(k).get(1))) {
                        solution++;
                    }

                }
            }
        }
        System.out.println(solution);
    }
}
