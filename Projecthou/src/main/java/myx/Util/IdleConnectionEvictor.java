package myx.Util;

import org.apache.http.conn.HttpClientConnectionManager;

/**
 * 定期清理无效连接线程
 */ 
public class IdleConnectionEvictor extends Thread { 
	
	private final HttpClientConnectionManager connMgr; 
	private volatile boolean shutdown; 
	public IdleConnectionEvictor(HttpClientConnectionManager connMgr) { 
		this.connMgr = connMgr; 
		// 自启动 
		this.start(); 
		} 
	
	
	@Override 
	public void run() { 
		try { 
			while (!shutdown) { 
				synchronized (this) { 
					wait(5000); 
					// 关闭失效的连接 
					connMgr.closeExpiredConnections(); 
					} 
				} 
			} catch (InterruptedException ex) { 
				// 结束
			} 
		} 
	 
	
	public void shutdown() { 
		shutdown = true; 
		synchronized (this) { 
			notifyAll(); 
		} 
	} 

}
