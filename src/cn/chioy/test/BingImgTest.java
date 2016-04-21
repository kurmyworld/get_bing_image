package cn.chioy.test;

import java.util.Calendar;

import org.junit.Test;

import cn.chioy.img.BingImageLoader;

public class BingImgTest {
	@Test
	public void test_cache(){
		BingImageLoader b = new BingImageLoader();
		System.out.println(b.getImgURL());
		
	}
	@Test
	public void test_calendar(){
		Calendar c = Calendar.getInstance();
		System.out.println(c.get(Calendar.DAY_OF_YEAR));
		System.out.println(c.get(Calendar.DAY_OF_MONTH));
		System.out.println(c.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		System.out.println(c.get(Calendar.DAY_OF_WEEK));
	}
}
