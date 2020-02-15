package ru.stqa.pft.keepcalm;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  /* Проверка расстояния между точками*/

  public void testDistance1() {

    Point p1 = new Point(13, 4);
    Point p2 = new Point(-3, 7);

    assert p1.distance(p2) == 16.278820596099706;

  }


  @Test
  /* Проверка расстояния между точками с некорректным ожидаемым результатом*/

  public void testDistance2() {

    Point p1 = new Point(13, 4);
    Point p2 = new Point(-3, 7);


    Assert.assertEquals(p1.distance(p2), 17.278820596099706);

  }

  @Test
  /* Проверка расстояния между точками*/

  public void testDistance3() {

    Point p1 = new Point(3, 5);
    Point p2 = new Point(4, 6);

    Assert.assertEquals(p1.distance(p2), 1.4142135623730951);

  }

}
