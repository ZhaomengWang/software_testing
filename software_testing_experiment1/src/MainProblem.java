import java.util.*;

public class MainProblem {
    public static Map<Double, Integer> getTakeOutSolution(NavigableMap<Double, Integer> denominationsSortedByKeyDesc, double target) {

        Map<Double, Integer> result = new HashMap<Double, Integer>();
        // 获取最大值
        double max = denominationsSortedByKeyDesc.firstKey();
        int inventory = denominationsSortedByKeyDesc.firstEntry().getValue();
        int times = (int) (target / max);
        // 可以直接由最大金额组成，无需往下判断
        if (target % max == 0 && times <= inventory) {
            result.put(max, times);
            return result;
        }
        // 只有一个面额的情况
        if (denominationsSortedByKeyDesc.size() == 1) {
            throw new IllegalArgumentException("can not take out this given number.");
        }
        double min = denominationsSortedByKeyDesc.lastKey();
        // 目标金额比最小面额还小，无法组合
        if (target < min) {
            throw new IllegalArgumentException("can not take out this given number.");
        }
        if (times > inventory) {
            times = inventory;
        }
        for (int i = times; i >= 0; i--) {
            if (i > 0) {
                result.put(max, i);
            }
            try {
                result.putAll(getTakeOutSolution(denominationsSortedByKeyDesc.subMap(max, false, min, true), target - i * max));
                return result;
            } catch (Exception e) {
                result.clear();
                continue;
            }
        }
        throw new IllegalArgumentException("can not take out this given number.");
    }

    public static boolean whetherTakeOut(double given_number){
        NavigableMap<Double, Integer> denominations = new TreeMap<Double, Integer>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                if (o2.doubleValue() > o1.doubleValue()) {
                    return 1;
                } else if (o2.doubleValue() == o1.doubleValue()) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        denominations.put(50d, 1);
        denominations.put(20d, 1);
        denominations.put(10d, 1);
        denominations.put(5d, 2);
        denominations.put(1d, 3);
        if (given_number == 0)
            return true;
        try {
            getTakeOutSolution(denominations,given_number);
        }
        catch (IllegalArgumentException e){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        NavigableMap<Double, Integer> denominations = new TreeMap<Double, Integer>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                if (o2.doubleValue() > o1.doubleValue()) {
                    return 1;
                } else if (o2.doubleValue() == o1.doubleValue()) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        denominations.put(50d, 1);
        denominations.put(20d, 1);
        denominations.put(10d, 1);
        denominations.put(5d, 2);
        denominations.put(1d, 3);

        System.out.println(getTakeOutSolution(denominations, 80));
        //System.out.println(whetherTakeOut(80));
    }
}
