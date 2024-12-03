package main.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part2 {
    public static boolean isSafe(List<Integer> report) {
        List<Integer> tempReport = new ArrayList<>();
        List<Integer> tempReport2 = new ArrayList<>();
        tempReport.addAll(report);
        tempReport2.addAll(report);

        Collections.sort(tempReport);
        Collections.sort(tempReport2);
        Collections.reverse(tempReport2);

        if (!tempReport.equals(report) && !tempReport2.equals(report)) {
            return false;
        } else {
            for (int i = 0; i < report.size() - 1; i++) {
                if (report.get(i) == report.get(i + 1)) {
                    return false;
                }
                int difference = report.get(i + 1) - report.get(i);
                if (difference == 0 || difference < -3 || difference > 3) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        List<String> lines;
        lines = Files.readAllLines(Paths.get("src/resources/input/day2.txt"));
        List<List<Integer>> fullReport = new ArrayList<>();

        for (String line : lines) {
            List<Integer> singleReport = new ArrayList<>();
            String[] split = line.split(" ");
            for (String s : split) {
                singleReport.add(Integer.parseInt(s));
            }
            fullReport.add(singleReport);
        }

        int unsafe = 0;
        for (List<Integer> report : fullReport) {
            boolean isSafe = isSafe(report);
            if (!isSafe) {
                for (int i = 0; i < report.size(); i++) {
                    int removed = report.remove(i);
                    if (isSafe(report)) {
                        break;
                    }
                    report.add(i, removed);
                    if (i == report.size() - 1) {
                        unsafe++;
                    }
                }
            }
        }

        System.out.println(fullReport.size() - unsafe);
    }
}
