package cn.chioy.work;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.impl.SimpleLog;

import cn.chioy.img.BingImageLoader;

public class GetURL extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String LINUX_PREFIX = "http://s.cn.bing.net";
	private static SimpleLog mLog = new SimpleLog("FromURL");
	private static boolean mOnLinux;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BingImageLoader loader = new BingImageLoader();
		String url = loader.getImgURL();
		if (mOnLinux)
			url = createURL(loader);
		loader.fixImgURL(url);
		response.getWriter().print(loader.getImgURL());
	}

	private String createURL(BingImageLoader b) {
		return LINUX_PREFIX + b.getImgURL();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();

		ServletContext contex = getServletContext();
		if (contex.getRealPath("").charAt(0) == '/') {
			mOnLinux = true;// running on linux
			mLog.info("Run on Linux");
		} else {
			mOnLinux = false;// running on windows
			mLog.info("Run on Windows");
		}

	}

}
