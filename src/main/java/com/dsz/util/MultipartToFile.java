package com.dsz.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 *识别身份证号工具类
 */
public class MultipartToFile {
	
	/**
	 * MultipartFile 转 File
	 * @param file
	 * @throws Exception
	 */
	@SuppressWarnings("unlikely-arg-type")	//抑制警告
	public static File multipartFileToFile(MultipartFile file) throws Exception {
		File toFile = null;
		if ("".equals(file) || file.getSize() <= 0) {
			file = null;
			return toFile;
		} else {
			InputStream ins = null;
			ins = file.getInputStream();
			toFile = new File(file.getOriginalFilename());
			inputStreamToFile(ins, toFile);
			ins.close();
		}
		return toFile;
	}

	/**
	 * 
	 * @param ins
	 * @param file
	 */
	public static void inputStreamToFile(InputStream ins, File file) {
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
