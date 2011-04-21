package com.lvl.au.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

public class FileOutput implements StreamingOutput {

	private FileInputStream inputStream;

	public FileOutput(FileInputStream inputStream) {
		super();
		this.inputStream = inputStream;
	}

	@Override
	public void write(OutputStream output) throws IOException, WebApplicationException {
		int bytes = 0;
		try {
			byte[] buf = new byte[2048];
			int len;
			do {
				len = inputStream.read(buf);
				if (len == -1) {
					break;
				}
				output.write(buf, 0, len);
				bytes += len;
			} while (len > 0);
		} finally {
			output.flush();
			output.close();
			inputStream.close();
		}
	}

}
