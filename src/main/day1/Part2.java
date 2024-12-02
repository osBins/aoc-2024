package main.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part2 {
    public static void main(String[] args) throws IOException {
        List<String> lines;
        lines = Files.readAllLines(Paths.get("src/resources/input/day1.txt"));
        int totalDistance = 0;

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<Integer> distancesList = new ArrayList<>();

        for (String line : lines) {
            String[] distances = line.split(" {3}");
            list1.add(distances[0]);
            list2.add(distances[1]);
        }
        List<Integer> similarityList = new ArrayList<>();
        for (String dist1 : list1) {
            int multiplier = 0;
            for (String dist2 : list2) {
                if (dist1.equals(dist2)) {
                    multiplier++;
                }
            }
            similarityList.add(multiplier * Integer.parseInt(dist1));
        }
        int similarityScore = similarityList.stream().mapToInt(Integer::intValue).sum();
        System.out.println(similarityScore);
    }
}
