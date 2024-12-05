package main.day5;

import main.InputReader;

import java.io.IOException;
import java.util.*;

public class Part1 {
    public static boolean checkBeforeAfter(List<Integer> update, Map<Integer, List<Set<Integer>>> beforeAfterMap) {
        for (int i = 0; i < update.size(); i++) {
            List<Integer> before = update.subList(0, i);
            List<Integer> after = update.subList(i + 1, update.size());
            for (int j = 0; j < before.size(); j++) {
                if (!beforeAfterMap.get(update.get(i)).get(0).contains(before.get(j))) {
                    return false;
                }
                if (beforeAfterMap.get(update.get(i)).get(1).contains(before.get(j))) {
                    return false;
                }
            }
            for (int j = 0; j < after.size(); j++) {
                if (!beforeAfterMap.get(update.get(i)).get(1).contains(after.get(j))) {
                    return false;
                }
                if (beforeAfterMap.get(update.get(i)).get(0).contains(after.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }


    public static int solveQuestion(Map<Integer, List<Set<Integer>>> beforeAfterMap, List<List<Integer>> updatesList) {
        int ans = 0;
        for (List<Integer> update : updatesList) {
            boolean flag = false;
            if (checkBeforeAfter(update, beforeAfterMap)) {
                int mid = update.size() / 2;
                ans = ans + update.get(mid);
            }
        }
        return ans;
    }
    public static Map<Integer, List<Set<Integer>>> createBeforeAfterMap(List<String> beforeAfter) {
        Map<Integer, List<Set<Integer>>> beforeAfterMap = new HashMap<>();
        for (String line : beforeAfter) {
            String[] split = line.split("\\|");
            for (String s : split) {
                List<Set<Integer>> beforeAfterLists = new ArrayList<>();
                if (!beforeAfterMap.containsKey(Integer.valueOf(s))) {
                    Set<Integer> newSetBefore = new HashSet<>();
                    Set<Integer> newSetAfter = new HashSet<>();
                    beforeAfterLists.add(newSetBefore);
                    beforeAfterLists.add(newSetAfter);
                    beforeAfterMap.putIfAbsent(Integer.valueOf(s), beforeAfterLists);
                }
            }
        }

        for (String line : beforeAfter) {
            String[] split = line.split("\\|");

            int beforeNum = Integer.parseInt(split[0]);
            int afterNum = Integer.parseInt(split[1]);

            beforeAfterMap.get(beforeNum).get(1).add(afterNum);
            beforeAfterMap.get(afterNum).get(0).add(beforeNum);
        }
        return beforeAfterMap;
    }

    public static void main(String[] args) throws IOException {
        List<String> input = InputReader.readInputFile("input/day5.txt");
        List<List<Integer>> updatesList = new ArrayList<>();
        List<String> beforeAfterList = new ArrayList<>();
        boolean updates = false;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).isEmpty()) {
                updates = true;
                continue;
            }
            if (updates) {
                List<Integer> row = new ArrayList<>();
                for (String value : input.get(i).split(",")) {
                    row.add(Integer.parseInt(value.trim()));
                }
                updatesList.add(row);
            }
            else {
                beforeAfterList.add(input.get(i));
            }
        }
        Map<Integer, List<Set<Integer>>> beforeAfterMap = createBeforeAfterMap(beforeAfterList);

        System.out.println(solveQuestion(beforeAfterMap, updatesList));
    }
}

