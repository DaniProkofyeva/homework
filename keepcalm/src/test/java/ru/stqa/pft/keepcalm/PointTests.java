package ru.stqa.pft.keepcalm;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  /* Проверка расстояния между точками*/

  public void testArea1() {
    Point p1 = new Point();
    Point p2 = new Point();

    p1.x1 = 13;
    p2.x2 = -3;
    p1.y1 = 4;
    p2.y2 = 7;

    assert p1.distance(p1,p2) == 16.278820596099706;

  }


  @Test
  /* Проверка расстояния между точками с некорректным ожидаемым результатом*/

  public void testArea2() {
    Point p1 = new Point();
    Point p2 = new Point();

    p1.x1 = 13;
    p2.x2 = -3;
    p1.y1 = 4;
    p2.y2 = 7;

    Assert.assertEquals(p1.distance(p1, p2), 17.278820596099706);

  }

  @Test
  /* Проверка расстояния между точками*/

  public void testArea3() {
    Point p1 = new Point();
    Point p2 = new Point();

    p1.x1 = 2;
    p2.x2 = 3;
    p1.y1 = 4;
    p2.y2 = 5;

    Assert.assertEquals(p1.distance(p1, p2), 1.4142135623730951);

  }

}
