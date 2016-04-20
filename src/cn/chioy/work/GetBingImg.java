package cn.chioy.work;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.chioy.util.BingImage;

public class GetBingImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BingImage bingImage = new BingImage();
		String imageUrl = bingImage.getImageUrl();
		bingImage.putImage("http://s.cn.bing.net"+imageUrl, response);
	}
}
