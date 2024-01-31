package javaTest;

import java.util.ArrayList;
import java.util.List;

public class Topic_15_Generic {
    public static void main(String[] args) {
        // List chi chua du lieu string:
        // E T V K L
        List<String> students = new ArrayList<String>();
        students.add("John");
        students.add("Mary");
        students.add("Peter");

        // List co the chua nhieu kieu du lieu, goi la non-Generic, khong nen dung
        List addresses = new ArrayList<>();
        addresses.add("123 Main St."); // String
        addresses.add(15); // Integer
        addresses.add(true); // Boolean
        addresses.add(15.56); // Float



    }
}
