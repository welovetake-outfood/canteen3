package com.example1.mycanteen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


public class UTF8Test {
	public static void main(String[] args) throws IOException {
		File f  = new File("./utf.txt");
		FileInputStream in = new FileInputStream(f);
		// ָ����ȡ�ļ�ʱ��UTF-8�ĸ�ʽ��ȡ
//		BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		BufferedReader br = new BufferedReader(new UnicodeReader(in, Charset.defaultCharset().name()));
		
		String line = br.readLine();
		while(line != null)
		{
			System.out.println(line);
			line = br.readLine();
		}
	}
}
