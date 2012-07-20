package com.lvl.au.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.lvl.au.exception.NotFoundException;

public class FileService {

	private static final String DIR = "temp";

	private static File directory;

	public int save(String name, InputStream is) throws IOException {
		File fileOut = getOutputFile(name);
		FileOutputStream fos = new FileOutputStream(fileOut);
		int bytes = 0;
		try {
			byte[] buf = new byte[2048];
			int len;
			do {
				len = is.read(buf);
				if (len == -1) {
					break;
				}
				fos.write(buf, 0, len);
				bytes += len;
			} while (len > 0);
		} finally {
			fos.flush();
			fos.close();
			is.close();
		}
		return bytes;
	}

	public FileOutput get(String filename) throws NotFoundException {
		File in = getOutputFile(filename);
		if (!in.exists()) {
			throw new NotFoundException(filename);
		}
		try {
			FileInputStream fis = new FileInputStream(in);
			return new FileOutput(fis);
		} catch (FileNotFoundException e) {
			throw new NotFoundException(filename);
		}
	}
	private File getOutputFile(String filename) {
		if (directory == null) {
			directory = new File(DIR);
			if (!directory.exists()) {
				directory.mkdirs();
			}
		}
		return new File(directory, filename);
	}
}
