package com.itmo.siaod;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> a = new ArrayList<>();
        a.set(10, 15);
        System.out.println(a.get(10));
    }
}