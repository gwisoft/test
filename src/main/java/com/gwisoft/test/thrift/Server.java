package com.gwisoft.test.thrift;


import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import com.gwisoft.test.thrift.ApiServer.Processor;

public class Server {

	public static void main(String[] args) {
		//start1(args);
		//start2(args);
		start3(args);
	}
	
	public static void start1(String[] args){
		try {

            TServerSocket serverTransport = new TServerSocket(8002);

            ApiServer.Processor process = new Processor(new ServiceHandler());

            Factory portFactory = new TBinaryProtocol.Factory(true, true);

            Args args1 = new Args(serverTransport);
            args1.processor(process);
            args1.protocolFactory(portFactory);

            TServer server = new TThreadPoolServer(args1);
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
	}
	
	public static void start2(String[] args){
		try {
			TNonblockingServerSocket nbServerSocket = new TNonblockingServerSocket(8003);
			
			THsHaServer.Args args1 = new THsHaServer.Args(nbServerSocket);
			
			ServiceHandler serviceHandler = new ServiceHandler();
	        args1.processor(new ApiServer.Processor<ApiServer.Iface>(serviceHandler));
	        args1.transportFactory(new TFramedTransport.Factory());
	        args1.protocolFactory(new TCompactProtocol.Factory());
	        
	        THsHaServer thriftServer = new THsHaServer(args1);
	        thriftServer.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}
	
	public static void start3(String[] args){
		try {
			TNonblockingServerSocket nbServerSocket = new TNonblockingServerSocket(8004);
			
			TNonblockingServer.Args args1 = new TNonblockingServer.Args(nbServerSocket);
			
			ServiceHandler serviceHandler = new ServiceHandler();
	        args1.processor(new ApiServer.Processor<ApiServer.Iface>(serviceHandler));
	        args1.transportFactory(new TFramedTransport.Factory());
	        args1.protocolFactory(new TCompactProtocol.Factory());
	        
	        TServer thriftServer = new TNonblockingServer(args1);
	        thriftServer.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}
}
