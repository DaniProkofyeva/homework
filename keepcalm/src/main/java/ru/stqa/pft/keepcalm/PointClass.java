package ru.stqa.pft.keepcalm;

public class PointClass{

    public static double x1;
    public static double x2;
    public static double y1;
    public static double y2;

    public static void main (String[] args) {

      Point p1 = new Point();
      Point p2 = new Point();

      p1.x1 = 13;
      p2.x2 = -3;
      p1.y1 = 4;
      p2.y2 = 7;

      System.out.println("Расстояние между точками " + p1.distance(p1,p2));
    }

  }