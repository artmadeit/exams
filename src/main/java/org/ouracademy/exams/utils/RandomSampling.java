package org.ouracademy.exams.utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

// From
// https://stackoverflow.com/questions/4702036/take-n-random-elements-from-a-liste
public class RandomSampling {

    private RandomSampling() {}

    public static <T> List<T> getNUniqueElements(int n, List<T> list) {
        return shuffle(list).stream().limit(n).collect(Collectors.toList());
    }
    
    public static <T> T getElement(List<T> list) {
        return list.get(new Random().nextInt(list.size()));
    }

    public static <T> List<T> ofAll(List<T> list) {
        return shuffle(list);
    }

    public static <T> List<T> shuffle(List<T> list) {
        var copy = new ArrayList<>(list);
        Collections.shuffle(copy);
        return copy;
    }
}
