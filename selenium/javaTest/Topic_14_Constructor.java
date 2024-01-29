package javaTest;

import java.util.Date;

public class Topic_14_Constructor {

    // 1 class neu k define cai Constructor cu the thi no se co 1 Constructor rỗng (default)
    // Constructor la 1 ham cung ten voi class
    // Ko co du lieu tra ve
    // Trong 1 class co the co nhieu Constructor
    // Neu minh define thi khi khoi tao no bat buoc phai goi toi Constructor  ma minh da define

    public Topic_14_Constructor(String name) {
        System.out.println(name);
    }
    public static void main(String[] args) {
        Topic_14_Constructor topic = new Topic_14_Constructor("Automation FC");
    }
}
