package cn.chioy.work;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.impl.SimpleLog;

import cn.chioy.img.BingImageLoader;

public class GetBingImg2 extends HttpServlet {
	private static final String WINDOWS_FILE_PATH = "d:/bj.jpg";
	private static final String LINUX_FILE_PATH = "/tmp/bj.jpg";
	private static final String LINUX_PREFIX = "http://s.cn.bing.net";
	private static final long serialVersionUID = 1L;
	private static File mFile;
	private static boolean mOnLinux;
	private static SimpleLog mLog = new SimpleLog("FromCache");

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		BingImageLoader loader = new BingImageLoader();

		String url = loader.getImgURL();
		if (mOnLinux)
			url = createURL(loader);
		loader.fixImgURL(url);

		mLog.info(loader.getImgURL());

		Integer day = (Integer) context.getAttribute("day");
		Calendar c = Calendar.getInstance();
		if (day == c.get(Calendar.DAY_OF_WEEK)) {
			loader.putPicFromCache(response, mFile);
		} else {
			loader.cacheTo(mFile);
			loader.putPicFromCache(response, mFile);
			context.setAttribute("day", c.get(Calendar.DAY_OF_WEEK));
		}

	}

	private String createURL(BingImageLoader b) {
		// TODO Auto-generated method stub
		return LINUX_PREFIX + b.getImgURL();
	}

	@Override
	public void init() throws ServletException {
		super.init();

		ServletContext contex = getServletContext();
		if (contex.getRealPath("").charAt(0) == '/') {
			mOnLinux = true;// running on linux
			mFile = new File(LINUX_FILE_PATH);
		} else {
			mOnLinux = false;// running on windows
			mFile = new File(WINDOWS_FILE_PATH);
		}

		Calendar c = Calendar.getInstance();
		contex.setAttribute("day", c.get(Calendar.DAY_OF_WEEK));
		
		BingImageLoader b = new BingImageLoader();
		b.cacheTo(mFile);
		mLog.info(mFile);

	}

}
