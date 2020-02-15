package ru.stqa.pft.keepcalm;

public class PointClass{

    public static void main(String[] args) {

      Point p1 = new Point(13, 4);
      Point p2 = new Point(-3, 7);

      System.out.println("Расстояние между точками " + p1.distance(p2));

    }

  }