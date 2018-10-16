package com.linli.consumer.utils;

import com.linli.consumer.domain.Shop;

import java.util.Comparator;

/**
 * Created by hasee on 2017/9/8.
 */

public class DistanceComparator implements Comparator {
    public int compare(Object o1,Object o2){
        Shop shop1 = (Shop) o1;
        Shop shop2 = (Shop) o2;
        if (shop1.getDistance_d() < shop2.getDistance_d()){
            return -1;
        }else {
            return 1;
        }
    }
}