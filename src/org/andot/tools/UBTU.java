package org.andot.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class UBTU {

	public static void main(String[] args){
		String filePath = "resources/filepath.txt";
		String encoding="UTF-8";
		try {
            File file = new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                		new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int i = 0;
                while((lineTxt = bufferedReader.readLine()) != null){
                	lineTxt = lineTxt.replace("[ERROR] /", "");
                	lineTxt = lineTxt.split(".java:")[0]+".java";
                	if(i % 2 == 0){
                		System.out.println(lineTxt);
                    	String allstr = bomToUTF8Read(lineTxt);    // "D://1/"+ lineTxt.substring(lineTxt.lastIndexOf("/"), lineTxt.length())
                    	bomToUTF8Write(allstr, lineTxt);
                	}else{
                		System.out.println("地址重复已经跳过"+lineTxt);
                	}
                	i++;
                }
                read.close();
	    }else{
	        System.out.println("找不到指定的文件");
	    }
	    } catch (Exception e) {
	        System.out.println("读取文件内容出错");
	        e.printStackTrace();
	    }
	}
	
	public static String bomToUTF8Read(String bomFilePath){
		BufferedReader br=null;
		String allstr = "";
		try {
			File file=new File(bomFilePath);
			
			br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			int i=0;
			String str="";
			while((str=br.readLine())!=null){
				if(i==0){//读取第一行，将前三个字节去掉，重新new个String对象
					byte[] bytes=str.getBytes("UTF-8");
					allstr += new String(bytes,3,bytes.length-3, "UTF-8") + "\r\n";
				}else{
					byte[] bytes=str.getBytes("UTF-8");
					allstr += new String(bytes, "UTF-8") + "\r\n";
				}
				i++;
			}
			br.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return allstr;
	}
	
	public static void bomToUTF8Write(String allstr, String utfFilePath){
		BufferedWriter bw=null;
		try {
			File targetFile=new File(utfFilePath);
			if(!targetFile.exists()){
				targetFile.createNewFile();
			}
			bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile),"UTF-8"));
			bw.write(allstr);
			bw.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
}
