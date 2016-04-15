package com.ftsafe.iccd;

import com.axis.common.Log;
import com.axis.common.log.LogWrapper;
import com.ftsafe.iccd.personalize.socket.TcpServer;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final LogWrapper LOG = Log.get();
	private static final int port = 3030;
	
    public static void main( String[] args ) throws Exception
    {
    	LOG.info("Server start on port {}", port);
    	new TcpServer(port).run();
    }
}
