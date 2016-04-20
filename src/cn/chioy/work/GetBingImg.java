package cn.chioy.work;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.chioy.util.BingImageLoader;

public class GetBingImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String prefix = "http://s.cn.bing.net";
		BingImageLoader loader = new BingImageLoader();
		loader.setImgUrl(prefix + loader.getImgUrl());
		//String url = loader.getImgUrl();
		//response.getWriter().print(url);
		loader.putPicFromUrl(response);
	}
}
