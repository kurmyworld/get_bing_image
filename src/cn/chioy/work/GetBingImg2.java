package cn.chioy.work;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.weld.context.ApplicationContext;

import cn.chioy.util.BingImage;

public class GetBingImg2 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		Date date = new Date();
		Integer day = null;
		day = (Integer) context.getAttribute("day");
		if (day == date.getDay()) {
			request.getRequestDispatcher("pics/bg.jpg").forward(request, response);
		} else {
			init();
		}

	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		ServletContext contex = getServletContext();
		contex.setAttribute("day", new Date().getDay());
		BingImage img = new BingImage();
		String prefix = "http://s.cn.bing.net";
		//String prefix = "";
		String realPath = contex.getRealPath("")+"pics\\bg.jpg";
		String url = prefix + img.getImageUrl(); 
		System.out.println(realPath);
		System.out.println(url);
		img.putImage(url,realPath);
	}

}
