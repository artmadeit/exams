package org.ouracademy.exams.utils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Sampling {

    private Sampling() {}

    public static <T> List<T> getNRandomElements(int n, List<T> list) {
        Collections.shuffle(list);
        return list.stream().limit(n).collect(Collectors.toList());
    }
    
}
