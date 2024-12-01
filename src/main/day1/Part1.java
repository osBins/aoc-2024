package main.day1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws Exception {
        List<String> lines;
        lines = Files.readAllLines(Paths.get("src/resources/day1.txt"));
        int totalDistance = 0;

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<Integer> distancesList = new ArrayList<>();

        for (String line : lines) {
            String[] distances = line.split(" {3}");
            list1.add(distances[0]);
            list2.add(distances[1]);
        }



        for (int x = list1.size(); x > 0; x--) {
            int smallestIdx1 = 0;
            int smallestIdx2 = 0;
            int smallest1 = Integer.parseInt(list1.get(0));
            int smallest2 = Integer.parseInt(list2.get(0));

            for (int i = 0; i < x - 1; i++) {
                if (Integer.parseInt(list1.get(i + 1)) < smallest1) {
                    smallestIdx1 = i + 1;
                    smallest1 = Integer.parseInt(list1.get(i + 1));
                }
            }

            for (int i = 0; i < x - 1; i++) {
                if (Integer.parseInt(list2.get(i + 1)) < smallest2) {
                    smallestIdx2 = i + 1;
                    smallest2 = Integer.parseInt(list2.get(i + 1));
                }
            }
            list1.remove(smallestIdx1);
            list2.remove(smallestIdx2);

            distancesList.add(Math.abs(smallest1 - smallest2));
        }
        int sum = distancesList.stream()
                        .mapToInt(a -> a)
                                .sum();
        System.out.println(sum);
    }
}
