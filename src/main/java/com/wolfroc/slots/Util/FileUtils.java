/**
 * Copyright (c) 2013-2014 by WolfRoc Inc. 
 * @author Create by Garfunkel
 * @Date 2015-9-21
 * @Description 
 */

package com.wolfroc.slots.Util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

public class FileUtils {
		private static final Logger logger = Logger.getLogger(FileUtils.class.getName());

		/**
	 * 以字节为单位读取文件，常用于读二进制文件，如图片、声音�?影像等文件�? 分一次读�?��字节/�?��读多个字�?
	 * 
	 * @param filePath
	 * @return
	 */
	public static String readFileByPerBytes(String filePath) {
		InputStream in = null;

		try {
			in = FileUtils.class.getClassLoader().getResourceAsStream(filePath);

			BufferedInputStream bs = new BufferedInputStream(in);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(bs));

			StringBuilder sb = new StringBuilder();

			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString);
				// line++;
			}

			// int tempbyte;
			// while ((tempbyte = in.read()) != -1) {
			// // System.out.write(tempbyte);
			//
			// if (((char) tempbyte) != '\r') {
			// sb.append((char)tempbyte);
			// }
			// }

			return sb.toString();

		} catch (IOException e) {
			logger.error("读取文件io错误!", e);
		} catch (Exception e) {
			logger.error("读取文件错误!", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("关闭文件错误!",e);
				}
			}
		}

		return null;
	}

	public static String readFileByMultiBytes(String filePath) {
		InputStream in = null;

		try {
			in = FileUtils.class.getClassLoader().getResourceAsStream(filePath);

			StringBuilder sb = new StringBuilder();

			int count = 32;
			byte[] b = new byte[count];
			int readCount = 0; // 已经成功读取的字节的个数
			while (readCount < count) {
				readCount += in.read(b, readCount, count - readCount);

				// sb.append(b.toString());
				sb.append(new String(b));
			}

			return sb.toString();
		} catch (IOException e) {
			logger.error("读取文件io错误!", e);
		} catch (Exception e) {
			logger.error("读取文件错误!", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("关闭文件错误!",e);
				}
			}
		}

		return null;
	}

	/**
	 * 以字符为单位读取文件，常用于读文本，数字等类型的文件 �?��读一个字�?读入多个字符到字符数组中，charread为一次读取字符数
	 * 
	 * @param filePath
	 * @return
	 */
	public static String readFileByChars(String filePath) {
		return null;
	}

	/**
	 * 以行为单位读取文�?常用于读取面向行的格式化文件
	 * 
	 * @param filePath
	 * @return
	 */
	public static String readFileByLines(String filePath) {
		BufferedReader reader = null;

		try {
			InputStream ins = FileUtils.class.getClassLoader()
					.getResourceAsStream(filePath);

			BufferedInputStream bs = new BufferedInputStream(ins);
			reader = new BufferedReader(new InputStreamReader(bs));

			StringBuilder sb = new StringBuilder();

			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString);
			}

			return sb.toString();
		} catch (IOException e) {
			logger.error("读取文件io错误!", e);
		} catch (Exception e) {
			logger.error("读取文件错误!", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("关闭文件错误!",e);
				}
			}
		}

		return null;
	}

	public static String readFile(String filePath) {
		BufferedReader reader = null;
		try {
			URL url = new URL(filePath);
//				File file = new File(url.toURI().getSchemeSpecificPart());
//				reader = new BufferedReader(new FileReader(isr));
			
			InputStreamReader isr = new InputStreamReader(new FileInputStream(url.toURI().getSchemeSpecificPart()), "utf-8");
			reader = new BufferedReader(isr);
			
			String tempString = null;
			// �?��读入�?��，直到读入null为文件结�?
			StringBuilder sb = new StringBuilder();
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString);
			}
			
			return sb.toString();
		} catch (MalformedURLException e) {
			logger.error("读取文件MalformedURL错误!", e);
		} catch (IOException e) {
			logger.error("读取文件io错误!", e);
		} catch (Exception e) {
			logger.error("读取文件错误!", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("关闭文件错误!",e);
				}
			}
		}

		return null;
	}
	
	public static String readFile_AbsoluteDirectory(String filePath) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filePath));
			
			String tempString = null;
			// �?��读入�?��，直到读入null为文件结�?
			StringBuilder sb = new StringBuilder();
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString);
			}
			
			return sb.toString();
		} catch (MalformedURLException e) {
			logger.error("读取文件MalformedURL错误!", e);
		} catch (IOException e) {
			logger.error("读取文件io错误!", e);
		} catch (Exception e) {
			logger.error("读取文件错误!", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("关闭文件错误!",e);
				}
			}
		}

		return null;
	}

	public static File[] readDirectory(String filePath)
	{
		URL url;
		try {
			url = new URL(filePath);
			File file = new File(url.toURI().getSchemeSpecificPart());

			if(!file.isDirectory())
			{
				return null;
			}
			
			File[] listFiles = file.listFiles();
			
			return listFiles;
			
		} catch (MalformedURLException e) {
			logger.error("读取目录MalformedURL错误!", e);
		} catch (URISyntaxException e)
		{
			logger.error("读取目录URISyntax错误!", e);	
		} catch (Exception e)
		{
			logger.error("读取目录错误!", e);	
		}
		
		return null;
	}

	public static String[] readDirectoryFiles(String filePath)
	{
		File[] fileList = readDirectory(filePath);
		String[] stringList=new String[fileList.length];
		int i=0;
		for(File file:fileList)
		{
			if(file.getName().equals(".svn"))
			{
				continue;
			}
			
			String readFile = readFile(filePath+file.getName());
			stringList[i]=readFile;
			
			i++;
		}
		
		return stringList;
	}

	public static void writeFile(ReentrantLock fileCreateLock,
			String pathName, String fileName, String content) {
		File file = null;
		File filePath;
		try {
			file = new File(pathName + fileName);
			if (!file.exists()) {
				fileCreateLock.lock();
				try {
					if (!file.exists()) { // 创建目录
						filePath = new File(pathName);
						if (!filePath.exists()) {
							filePath.mkdirs();
						}
						// 创建文件
						file.createNewFile();
					}
				} finally {
					fileCreateLock.unlock();
				}
			}
		} catch (Exception e) {
			logger.error("writeFile error!",e);
		}

		try {
			FileOutputStream fileStream = new FileOutputStream(file, false);
			PrintWriter print = new PrintWriter(fileStream);
			print.print(content);
			print.flush();
		} catch (FileNotFoundException e) {
			logger.error("writeFile error!",e);
		}
	}
	
	public static void writeFile(String pathName, String fileName, String content) {
		File file = null;
		try {
			URL url = new URL(pathName+fileName);
			file = new File(url.toURI().getSchemeSpecificPart());
			
			if (!file.exists()) {
				file.createNewFile();
			}
			
			FileOutputStream fileStream = new FileOutputStream(file, false);
			PrintWriter print = new PrintWriter(fileStream);
			print.print(content);
			print.flush();
			
		} catch (Exception e) {
			logger.error("writeFile error!",e);
		}
	}

	public static boolean isExist(String filePath)
	{
		File f=new File(filePath);
		if(f.exists())
		{
			return true;
		}
		
		return false;
	}
}
