package main.day4;

import main.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part2 {
    public static boolean isValid(int x, int y, List<List<Character>> charArray) {
        return x >= 0 && x < charArray.size() && y >= 0 && y < charArray.get(x).size();
    }

    public static boolean matchMas(int x, int y, String xmas, int index, List<List<Character>> charArray, int dirX, int dirY) {
        if (index == xmas.length()) {
            return true;
        }

        if (isValid(x, y, charArray) && xmas.charAt(index) == charArray.get(x).get(y)) {
            return matchMas(x + dirX, y + dirY, xmas, index + 1, charArray, dirX, dirY);
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        List<String> input = InputReader.readInputFile("test/day4.txt");
        List<List<Character>> charArray = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            charArray.add(new ArrayList<>());
            for (int j = 0; j < input.get(i).length(); j++) {
                charArray.get(i).add(input.get(i).charAt(j));
            }
        }
        String MAS = "MAS";
        List<List<Integer>> directions = List.of(List.of(1, 1), List.of(-1, -1), List.of(1, -1), List.of(-1, 1));
        Set<List<Integer>> solutionSet = new HashSet<>();
        for (int i = 0; i < charArray.size(); i++) {
            for (int j = 0; j < charArray.size(); j++) {
                for (int k = 0; k < directions.size(); k++) {
                    if (i == 2 && j == 1) {
                        int p = 1;
                    }
                    if (k == 0) {
                        if (matchMas(i, j, MAS, 0, charArray, directions.get(k).get(0), directions.get(k).get(1))) {
                            if (matchMas(i, j + 2, MAS, 0, charArray, directions.get(2).get(0), directions.get(2).get(1))) {
                                solutionSet.add(List.of(i, j));
                            }
                            if (matchMas(i + 2, j, MAS, 0, charArray, directions.get(3).get(0), directions.get(3).get(1))) {
                                solutionSet.add(List.of(i, j));
                            }
                        }
                    }
                    if (k == 1) {
                        if (matchMas(i, j, MAS, 0, charArray, directions.get(k).get(0), directions.get(k).get(1))) {
                            if (matchMas(i - 2, j, MAS, 0, charArray, directions.get(2).get(0), directions.get(2).get(1))) {
                                solutionSet.add(List.of(i, j));
                            }
                            if (matchMas(i, j - 2, MAS, 0, charArray, directions.get(3).get(0), directions.get(3).get(1))) {
                                solutionSet.add(List.of(i, j));
                            }
                        }
                    }
                }
            }
        }
        System.out.println(solutionSet);
        System.out.println(solutionSet.size());
    }
}

