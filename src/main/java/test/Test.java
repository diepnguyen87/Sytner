package test;

import java.util.*;

public class Test {

    public static void addNew(Map<String, List<Integer>> productMap, String key, int value){
        List<Integer> iList = new ArrayList<>();
        iList.add(value);
        productMap.put(key, iList);
    }

    public static void replace(Map<String, List<Integer>> productMap, String key, int value){
        List<Integer> existedList = productMap.get(key);
        existedList.add(value);
        productMap.replace(key, existedList);
    }


    public static int numDuplicates(List<String> nameList, List<Integer> priceList, List<Integer> weightList) {
        int dup = 0;

        Map<String, List<Integer>> productMap = new HashMap<>();

        for (int i = 0; i < nameList.size(); i++) {
            String name = nameList.get(i);
            if (i == 0) {
                addNew(productMap, name, i);
                continue;
            }

            boolean isDupName = false;
            boolean isDupPrice = false;
            boolean isDupWeight = false;

            for (Map.Entry<String, List<Integer>> entry : productMap.entrySet()) {
                if (name.equals(entry.getKey())) {
                    List<Integer> indexList = entry.getValue();
                    for (Integer index : indexList) {
                        if (priceList.get(i) == priceList.get(index)) {
                            isDupPrice = true;
                            if (weightList.get(i) == weightList.get(index)) {
                                ++dup;
                                isDupWeight = true;
                                break;
                            }
                        }
                    }

                    if(isDupPrice == false || isDupWeight == false){
                        replace(productMap, name, i);
                        isDupName = true;
                        break;
                    }
                }
            }

            if(isDupName == false){
                addNew(productMap, name, i);
                continue;
            }
        }
        return dup;
    }

    public static void main(String[] args) {
        List<String> name = Arrays.asList("ball", "bat", "glove", "glove", "glove");
        List<Integer> price = Arrays.asList(2, 3, 1, 1, 2);
        List<Integer> weight = Arrays.asList(2, 5, 1, 1, 1);
        System.out.println(Test.numDuplicates(name, price, weight));
    }
}
