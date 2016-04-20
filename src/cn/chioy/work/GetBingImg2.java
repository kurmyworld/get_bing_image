package cn.chioy.work;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.chioy.util.BingImageLoader;

public class GetBingImg2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Calendar c;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		String pathname = "/tmp/bj.jpg";
		String prefix= "http://s.cn.bing.net";
		System.out.println(pathname);
		BingImageLoader loader = new BingImageLoader();
		loader.setImgUrl(prefix + loader.getImgUrl());
		c = Calendar.getInstance();
		Integer day = null;
		day = (Integer) context.getAttribute("day");
		if (day == c.get(Calendar.DAY_OF_WEEK)) {
			loader.putPicFromCache(response, pathname);
		} else {
			loader.cacheTo(pathname);
		}

	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		c = Calendar.getInstance();
		ServletContext contex = getServletContext();
		contex.setAttribute("day", c.get(Calendar.DAY_OF_WEEK));
	}

}
