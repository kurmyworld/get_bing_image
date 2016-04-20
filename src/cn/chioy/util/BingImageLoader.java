package cn.chioy.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

public class BingImageLoader {
	private static String imgurl;

	public BingImageLoader() {
		super();
		imgurl = getUrl();
	}
	public void setImgUrl(String url){
		imgurl = url;
	}
	public String getImgUrl() {
		return imgurl;
	}

	private static byte[] getUrlFileData() throws Exception {
		URL url = new URL(imgurl);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.connect();
		InputStream cin = httpConn.getInputStream();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = cin.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		cin.close();
		byte[] fileData = outStream.toByteArray();
		outStream.close();
		return fileData;
	}

	private static byte[] getCacheFileData(String pathname) throws Exception {
		FileInputStream fis = new FileInputStream(pathname);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) != -1) {
			outputStream.write(buffer, 0, len);
		}
		fis.close();
		byte[] fileData = outputStream.toByteArray();
		return fileData;
	}

	private String getUrl() {
		System.out.println("getting image url...");
		String addr = "http://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1";
		String json_str = JsonHelper.pub(addr, JsonHelper.SUBMIT_METHOD_GET);
		String pattern = "url\":(.*)\\.jpg";
		// 创建 Pattern 对象
		Pattern r = Pattern.compile(pattern);
		// 现在创建 matcher 对象
		Matcher m = r.matcher(json_str);
		String img_url = "";
		if (m.find()) {
			img_url = m.group(0).replace("url\":\"", "");
		}
		return img_url;
	}

	public void putPicFromUrl(HttpServletResponse response) {
		System.out.println("putting from url...");
		try {
			byte[] data = getUrlFileData();
			response.setContentLength(data.length);
			response.setContentType("image/jpeg");
			OutputStream out = response.getOutputStream();
			out.write(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void putPicFromCache(HttpServletResponse response, String pathname) {
		try {
			if (!hasCache(pathname))
				this.cacheTo(pathname);
			byte[] data = getCacheFileData(pathname);
			response.setContentLength(data.length);
			response.setContentType("image/jpeg");
			OutputStream out = response.getOutputStream();
			out.write(data);
			System.out.println("putting from cache...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean hasCache(String pathname) {
		System.out.println("Checking has cached...");
		boolean b = false;
		try {
			FileInputStream fis = new FileInputStream(pathname);
			fis.close();
			b = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			b = false;
		}
		return b;
	}

	public void cacheTo(String pathname) {
		System.out.println("Cacheing...");
		try {
			byte[] data = getUrlFileData();
			FileOutputStream fos = new FileOutputStream(new File(pathname));
			fos.write(data);
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Cached!");
	}

}
