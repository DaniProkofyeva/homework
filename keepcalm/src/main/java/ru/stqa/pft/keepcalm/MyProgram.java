package ru.stqa.pft.keepcalm;

public class MyProgram {

    public static void main(String[] args) {
      hi("All");
      hi("user");
      hi("Alexei");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(4,6);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
    }
    public static void hi (String somebody){
        System.out.println("Hi " + somebody);
    }
}
