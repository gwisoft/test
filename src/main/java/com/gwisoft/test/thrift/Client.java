package com.gwisoft.test.thrift;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class Client {

	public static void main(String[] args) {
		//start1(args);
		//start2(args);
		start3(args);
	}
	
	public static void start1(String[] args){
		TTransport transport;
		try {
			transport = new TSocket("localhost", 8002);
			TProtocol protocol = new TBinaryProtocol(transport);
			ApiServer.Client client = new ApiServer.Client(protocol);
			transport.open();
			client.submitTopology(null);
			transport.close();
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
	}
	
	public static void start2(String[] args){
		TTransport transport;
		try{
			transport = new TFramedTransport(new TSocket("localhost", 8003));
			
			TProtocol protocol = new TCompactProtocol(transport);
			
			ApiServer.Client client = new ApiServer.Client(protocol);
			transport.open();
			client.submitTopology(null);
			transport.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void start3(String[] args){
		TTransport transport = null;
		try {
			transport = new TFramedTransport(new TSocket("localhost",
					8004, 1000));
			// 协议要和服务端一致
			TProtocol protocol = new TCompactProtocol(transport);
			ApiServer.Client client = new ApiServer.Client(
					protocol);
			transport.open();
			byte[] data = "123".getBytes();//getFileByte();
			System.out.println(DigestUtils.md5Hex(data));
			ByteBuffer byteBuffer = ByteBuffer.wrap(data);
			client.submitTopology(byteBuffer);
			
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		} finally {
			if (null != transport) {
				transport.close();
			}
		}
	}
	
	public static byte[] getFileByte(){
		File file = new File("F:\\work\\eclipse-java-neon_work\\jkubernetes-all\\jkubernetes-core\\src\\test\\java\\org\\jkubernetes\\core\\kubectl\\test.yaml");
		byte[] data;
		try {
			data = FileUtils.readFileToByteArray(file);
			return data;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
