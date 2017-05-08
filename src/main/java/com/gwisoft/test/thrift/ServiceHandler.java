package com.gwisoft.test.thrift;

import java.nio.ByteBuffer;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gwisoft.test.thrift.ApiServer.Iface;


public class ServiceHandler implements Iface {

	private static final Logger logger = LoggerFactory.getLogger(ServiceHandler.class);
	
	public final static int MIN_THREAD_NUM = 1;
	public final static int MAX_THREAD_NUM = 10;
	
	
	@Override
	public void submitTopology(ByteBuffer yaml) throws TException {
		byte[] bytes = new byte[yaml.limit() - yaml.position()];
		yaml.get(bytes);
		System.out.println(DigestUtils.md5Hex(bytes));
		System.out.println(new String(bytes));
	}


	@Override
	public void submitTopologyStr(String yaml) throws TException {
		System.out.println(DigestUtils.md5Hex(yaml));
		
	}

}
