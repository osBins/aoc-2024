package main.day3;

import main.InputReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// mul(123,234)
 // mul(1,1)
public class Part1and2 {
    public static void main(String[] args) throws IOException {
        List<String> input = InputReader.readInputFile("input/day3.txt");
        String regex = "mul\\([0-9]{1,3},[0-9]{1,3}\\).*";
        String doRegex = "^do().*";
        String dontRegex = "^don't().*";
        Pattern mulPattern = Pattern.compile(regex);
        Pattern doPattern = Pattern.compile(doRegex);
        Pattern dontPattern = Pattern.compile(dontRegex);
        List<String> solutionList = new ArrayList<>();
        boolean doEnabled = true;
        for (String line : input) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == 'd') {
                    int end = Integer.min(7, line.length() - i);
                    String substring = line.substring(i, i + end);
                    if (dontPattern.matcher(substring).matches()) {
                        doEnabled = false;
                    }
                    else if (doPattern.matcher(substring).matches()) {
                        doEnabled = true;
                    }
                }
                if (doEnabled) {
                    if (line.charAt(i) == 'm') {
                        int end = Integer.min(12, line.length() - i);
                        String substring = line.substring(i, i + end);
                        Matcher m = mulPattern.matcher(substring);
                        if (m.matches()) {
                            String[] kaamKiString = substring.split("\\)");
                            solutionList.add(kaamKiString[0]);
                        }
                    }
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < solutionList.size(); i++) {
            String numbers = solutionList.get(i).split("\\(")[1];
            String[] multipliers = numbers.split(",");
            sum = sum + Integer.parseInt(multipliers[0]) * Integer.parseInt(multipliers[1]);
        }
        System.out.println(sum);
    }
}
