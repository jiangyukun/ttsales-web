/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 * @filename FileUtil.java
 * @package com.ratan.util
 * @author dandyzheng
 * @date 2012-6-7
 */
package cn.ttsales.work.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author dandyzheng
 * 
 */
public class FileUtil {
	/**
	 * File exist check
	 * 
	 * @param sFileName
	 *            File Name
	 * @return boolean true - exist<br>
	 *         false - not exist
	 */
	public static boolean checkExist(String sFileName) {
		boolean result = false;
		try {
			File f = new File(sFileName);
			if (f.exists() && f.isFile()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void mkdir(String sFileName){
		File f = new File(sFileName);
		if (!f.exists()) {
			f.mkdir();
		}
	}

	/**
	 * Get File Size
	 * 
	 * @param sFileName
	 *            File Name
	 * @return long File's size(byte) when File not exist return -1
	 */
	public static long getFileSize(String sFileName) {
		long lSize = -1;
		try {
			File f = new File(sFileName);
			if (f.exists()) {
				if (f.isFile() && f.canRead()) {
					lSize = f.length();
				}
			}
		} catch (Exception e) {
			lSize = -1;
			e.printStackTrace();
		}
		return lSize;
	}

	/**
	 * File Delete
	 * 
	 * @param sFileName
	 *            File Nmae
	 * @return boolean true - Delete Success<br>
	 *         false - Delete Fail
	 */
	public static boolean deleteFromName(String sFileName) {

		boolean bReturn = true;
		try {
			File oFile = new File(sFileName);
			// exist
			if (oFile.exists()) {
				// Delete File
				boolean bResult = oFile.delete();
				// Delete Fail
				if (bResult == false) {
					bReturn = false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			bReturn = false;
		}

		return bReturn;
	}

	public static InputStream getInputStreamFromBytes(byte[] bytes) {
		InputStream is = null;
		if (bytes.length != 0) {
			is = new ByteArrayInputStream(bytes);
		}
		return is;
	}
	
	
	public static byte[] getBytesFromUrl(String url){
		if(url == null || url == ""){return null;}
		byte[] buffer = null;
		BufferedInputStream bis = null;
		ByteArrayOutputStream bos = null;
		try {
			URL uri = new URL(url);
			bis = new BufferedInputStream(uri.openStream());
			byte[] bytes = new byte[1024];
			bos = new ByteArrayOutputStream(1024);
			int len;
			while ((len = bis.read(bytes)) > 0) {
				bos.write(bytes, 0, len);
			}
			bis.close();
			bos.flush();
			bos.close();
			buffer = bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				bis = null;
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				bos = null;
			}
		} finally{
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				bis = null;
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				bos = null;
			}
		}
		return buffer;
	}

	public static byte[] getBytesFromFile(File file) {
		byte[] buffer = null;
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		try {
			fis = new FileInputStream(file);
			bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				fis = null;
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				bos = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	public static void writeByte2File(byte[] bytes, String filePath) {
		FileOutputStream outf = null;
		BufferedOutputStream bufferout = null;
		try {
			outf = new FileOutputStream(filePath);
			bufferout = new BufferedOutputStream(outf);
			bufferout.write(bytes);
			bufferout.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferout != null) {
				try {
					bufferout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				bufferout = null;
			}
			if (outf != null) {
				try {
					outf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				outf = null;
			}
		}
	}

	/**
	 * File Unzip
	 * 
	 * @param sToPath
	 *            Unzip Directory path
	 * @param sZipFile
	 *            Unzip File Name
	 */
	public static void releaseZip(String sToPath, String sZipFile)
			throws Exception {

		if (null == sToPath || ("").equals(sToPath.trim())) {
			File objZipFile = new File(sZipFile);
			sToPath = objZipFile.getParent();
		}
		ZipFile zfile = new ZipFile(sZipFile);
		Enumeration<?> zList = zfile.entries();
		ZipEntry ze = null;
		byte[] buf = new byte[1024];
		while (zList.hasMoreElements()) {

			ze = (ZipEntry) zList.nextElement();
			if (ze.isDirectory()) {
				continue;
			}

			OutputStream os = new BufferedOutputStream(new FileOutputStream(
					getRealFileName(sToPath, ze.getName())));
			InputStream is = new BufferedInputStream(zfile.getInputStream(ze));
			int readLen = 0;
			while ((readLen = is.read(buf, 0, 1024)) != -1) {
				os.write(buf, 0, readLen);
			}
			is.close();
			os.close();
		}
		zfile.close();
	}

	/**
	 * getRealFileName
	 * 
	 * @param baseDir
	 *            Root Directory
	 * @param absFileName
	 *            absolute Directory File Name
	 * @return java.io.File Return file
	 */
	private static File getRealFileName(String baseDir, String absFileName)
			throws Exception {

		File ret = null;

		List<String> dirs = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(absFileName,
				System.getProperty("file.separator"));
		while (st.hasMoreTokens()) {
			dirs.add(st.nextToken());
		}

		ret = new File(baseDir);
		if (dirs.size() > 1) {
			for (int i = 0; i < dirs.size() - 1; i++) {
				ret = new File(ret, (String) dirs.get(i));
			}
		}
		if (!ret.exists()) {
			ret.mkdirs();
		}
		ret = new File(ret, (String) dirs.get(dirs.size() - 1));
		return ret;
	}

	/**
	 * copyFile
	 * 
	 * @param srcFile
	 *            Source File
	 * @param targetFile
	 *            Target file
	 */
	static public void copyFile(String srcFile, String targetFile)
			throws IOException {

		FileInputStream reader = new FileInputStream(srcFile);
		FileOutputStream writer = new FileOutputStream(targetFile);

		byte[] buffer = new byte[4096];
		int len;

		try {
			reader = new FileInputStream(srcFile);
			writer = new FileOutputStream(targetFile);

			while ((len = reader.read(buffer)) > 0) {
				writer.write(buffer, 0, len);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (writer != null)
				writer.close();
			if (reader != null)
				reader.close();
		}
	}

	/**
	 * renameFile
	 * 
	 * @param srcFile
	 *            Source File
	 * @param targetFile
	 *            Target file
	 */
	static public void renameFile(String srcFile, String targetFile)
			throws IOException {
		try {
			copyFile(srcFile, targetFile);
			deleteFromName(srcFile);
		} catch (IOException e) {
			throw e;
		}
	}

	public static void write(String tivoliMsg, String logFileName) {
		try {
			byte[] bMsg = tivoliMsg.getBytes();
			FileOutputStream fOut = new FileOutputStream(logFileName, true);
			fOut.write(bMsg);
			fOut.close();
		} catch (IOException e) {
			// throw the exception
		}

	}

	/**
	 * This method is used to log the messages with timestamp,error code and the
	 * method details
	 * 
	 * @param errorCd
	 *            String
	 * @param className
	 *            String
	 * @param methodName
	 *            String
	 * @param msg
	 *            String
	 */
	public static void writeLog(String logFile, String batchId,
			String exceptionInfo) {

		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT,
				DateFormat.DEFAULT, Locale.JAPANESE);

		Object args[] = { df.format(new Date()), batchId, exceptionInfo };

		String fmtMsg = MessageFormat.format("{0} : {1} : {2}", args);

		try {

			File logfile = new File(logFile);
			if (!logfile.exists()) {
				logfile.createNewFile();
			}

			FileWriter fw = new FileWriter(logFile, true);
			fw.write(fmtMsg);
			fw.write(System.getProperty("line.separator"));

			fw.flush();
			fw.close();

		} catch (Exception e) {
		}
	}

	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "/n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
