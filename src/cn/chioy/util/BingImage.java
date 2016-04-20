package cn.chioy.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class BingImage {
	private static String img_url = null;
	private static byte[] getUrlFileData(String fileUrl) throws Exception  
    {  
        URL url = new URL(fileUrl);  
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
	public String getImageUrl() {
		String addr = "http://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1";
		String json_str = JsonHelper.pub(addr, JsonHelper.SUBMIT_METHOD_GET);
		String pattern = "url\":(.*)\\.jpg";
		// 创建 Pattern 对象
		Pattern r = Pattern.compile(pattern);
		// 现在创建 matcher 对象
		Matcher m = r.matcher(json_str);
		if (m.find()) {
			img_url = m.group(0).replace("url\":\"", "");
		}
		return img_url;
	}

	public void putImage(String imageUrl,HttpServletResponse response) {
		try {
			byte[] data = getUrlFileData(imageUrl);
			response.setContentLength(data.length);
			response.setContentType("image/jpeg");
			ServletOutputStream out = response.getOutputStream();
			out.write(data);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void putImage(String imageUrl,String pathname) {
		try {
			byte[] data = getUrlFileData(imageUrl);
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
	}

}
