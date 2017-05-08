package com.gwisoft.test.other;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//readFile();
        //k();
		compose();
	}
	
	public static void compose(){
		
		ArrayList<List<Integer>> temp = new ArrayList();
		List<Integer> subs = new ArrayList<Integer>();
		subs.add(2);
		temp.add(subs);
		
		List<Integer> subs1 = new ArrayList<Integer>();
		subs1.add(1);
		temp.add(subs1);
		Object a = subs1;
		subs1.toString();
		
		Collections.sort(temp,new Comparator<List<Integer>>() {

			@Override
			public int compare(List<Integer> o1, List<Integer> o2) {
				if(o1.get(0).intValue() > o2.get(0).intValue()){
					return 0;
				}else{
					return 1;
				}
				
				
			}
			
		});
	}
	
	public static void swop(Integer a ,Integer b){
		a = 2;
		b = 1;
	}
	
	public static void k(){
		String a = "asdf";
		String b = "asdf";
		System.out.println(a == b);
	}
	
	public static void a(Object... arr){
		if(arr[0] instanceof Long){
			//long k = (long)arr[0];
		}
	}
	
	public static void readFile(){
		File file = new File("F:\\work\\eclipse-java-neon_work\\Test\\src\\com\\ailk\\ims\\test\\a.txt");
		ByteOutputStream out = new ByteOutputStream();
		
		try {
			FileOutputStream outFileb = new FileOutputStream(
					new File("F:\\work\\eclipse-java-neon_work\\Test\\src\\com\\ailk\\ims\\test\\b.txt"));
			
			FileOutputStream outFilec = new FileOutputStream(
					new File("F:\\work\\eclipse-java-neon_work\\Test\\src\\com\\ailk\\ims\\test\\c.txt"));
			
			FileInputStream fileInputStream = new FileInputStream(file);
			byte[] tmp = new byte[1024];
			try {
//				while(true){
//					
//					int i = fileInputStream.read(tmp);
//					if(i == -1){
//						System.out.println("------------------------" + tmp.length);
//						System.out.println(new String(tmp,"UTF-8"));
//						System.out.println("-------------------------------------------------");
//						break;
//					} 
//					out.write(tmp,0,i);
//				}
				int i;
				while((i = fileInputStream.read(tmp)) != -1){
					out.write(tmp,0,i);
				}
				
				String context = out.toString();
				
				System.out.println("**********************************************");
				System.out.println(context);
				System.out.println("**********************************************");
				//C文件输出
				System.out.println(context.getBytes().length);
				System.out.println(context.length());
				outFilec.write(context.getBytes());
				
				//b文件输出
				String str[] = context.split("\r\n");
				for(String st: str){

					System.out.println(st);
					byte[] b = st.getBytes();
					outFileb.write(b);
					outFileb.write("\r\n".getBytes());
				}
				outFileb.close();
				outFilec.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
