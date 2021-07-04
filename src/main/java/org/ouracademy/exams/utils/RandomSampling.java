package org.ouracademy.exams.utils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomSampling {

    private RandomSampling() {}

    public static <T> List<T> getNElements(int n, List<T> list) {
        Collections.shuffle(list);
        return list.stream().limit(n).collect(Collectors.toList());
    }
    
    public static <T> T getElement(List<T> list) {
        return getNElements(1, list).get(0);
    }

    public static <T> List<T> ofAll(List<T> list) {
        Collections.shuffle(list);
        return list;
    }
}
