package main.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> lines;
        lines = Files.readAllLines(Paths.get("src/resources/input/day2.txt"));
        int unsafe = 0;
        List<List<Integer>> fullReport = new ArrayList<>();

        for (String line : lines) {
            List<Integer> singleReport = new ArrayList<>();
            String[] split = line.split(" ");
            for (String s : split) {
                singleReport.add(Integer.parseInt(s));
            }
            fullReport.add(singleReport);
        }

        for (List<Integer> singleReport : fullReport) {
            int first = singleReport.get(0);
            int second = singleReport.get(1);
            if (first == second) {
                unsafe++;
                continue;
            }

            if (first > second) {
                boolean flag = true;
                int difference = first - second;
                if (difference > 0 && difference <= 3) {
                    for (int i = 1; i < singleReport.size() - 1; i++) {
                        int tempFirst = singleReport.get(i);
                        int tempSecond = singleReport.get(i + 1);
                        int tempDifference = tempFirst - tempSecond;
                        if (tempDifference <= 0 || tempDifference > 3) {
                            flag = false;
                            unsafe++;
                            break;
                        }
                    }
                }
                else {
                    unsafe++;
                }
            }
            else {
                boolean flag = true;
                int difference = second - first;
                if (difference > 0 && difference <= 3) {
                    for (int i = 1; i < singleReport.size() - 1; i++) {
                        int tempFirst = singleReport.get(i);
                        int tempSecond = singleReport.get(i + 1);
                        int tempDifference = tempSecond - tempFirst;
                        if (tempDifference <= 0 || tempDifference > 3) {
                            flag = false;
                            unsafe++;
                            break;
                        }
                    }
                }
                else {
                    unsafe++;
                }
            }
        }
        System.out.println(fullReport.size() - unsafe);
    }
}
