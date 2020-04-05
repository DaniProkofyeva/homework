package ru.stga.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

  @Test()
  public void testIP() {
    String myGeo = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("178.67.141.116");
    System.out.println(myGeo);
  }
}
