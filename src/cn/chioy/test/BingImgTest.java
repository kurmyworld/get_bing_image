package cn.chioy.test;

import org.junit.Test;

import cn.chioy.util.BingImage;

public class BingImgTest {
	private BingImage bingImage;
	@Test
	public void test_getImage() {
		bingImage = new BingImage();
		String url = bingImage.getImageUrl();
		System.out.println(url);
	}
	@Test
	public void test_putImage() {
		bingImage = new BingImage();
		String imageUrl = bingImage.getImageUrl();
		System.out.println(imageUrl);
		bingImage.putImage(imageUrl, "./WebRoot/background.jpg");
	}
}
