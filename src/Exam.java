import java.util.ArrayList;
import java.util.List;

abstract class A {
    private String m1() {
        return "A1";
    }
    protected String m2() {
        return "A2";
    }
    public String m3() {
        return "A3";
    }
    public abstract String m4();
}
class B extends A {
    public String m4() {
        return "B4";
    }
    public String m5() {
        return "B5";
    }
}
class C extends B {
    public String m3() {
        return "C3";
    }
    public String m6() {
        return "C6";
    }
}
public class Exam {
    public static void main(String[] args) {
        B b = new B();
        C c = new C();
        System.out.print(b.m2() + ", ");
        System.out.print(b.m3() + ", ");
        System.out.print(b.m4() + ", ");
        System.out.print(b.m5() + ", ");
        System.out.print(c.m3() + ", ");
        System.out.print(c.m4() + ", ");
        System.out.print(c.m5() + ", ");
        System.out.print(c.m6()+ ".");
    }
}