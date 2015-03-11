package cn.fuego.smart.home.device.listenser.mina;

//Download by http://www.codefans.net
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.SystemConfigInfo;
import cn.fuego.misp.service.MISPException;

public class MinaServer
{

	private final FuegoLog log = FuegoLog.getLog(getClass());

	private int listenPort = 8600;

	private IoAcceptor acceptor;
	
	private static MinaServer instance;
	
	private MinaServer()
	{
		
	}
	public static synchronized MinaServer getInstance()
	{
		if (null == instance)
		{
			instance = new MinaServer();
		}
		return instance;
	}

	public void init(int port)
	{
		listenPort = port;
	}

	public void start()
	{
		// 创建服务端监控线程
		acceptor = new NioSocketAcceptor();
		// 设置日志记录器
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		// 设置编码过滤器
//		acceptor.getFilterChain().addLast(
//				"codec",
//				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
//						.forName("UTF-8"))));
		
		FuegoKeepAliveFilter keepAlive =  new FuegoKeepAliveFilter();
		keepAlive.setForwardEvent(true);
		
		keepAlive.setRequestInterval(SystemConfigInfo.getHeartBeatTime());  
		
		acceptor.getFilterChain().addLast("keep-alive",keepAlive);
		// 指定业务逻辑处理器
		acceptor.setHandler(new MinaServerHandler());
		// 设置端口号
		acceptor.setDefaultLocalAddress(new InetSocketAddress(listenPort));
		// 启动监听线程
		try
		{
			acceptor.bind();
		} catch (IOException e)
		{
			log.error("bind port failed");
			throw new MISPException();
		}
	}

	public int getOnlineNum()
	{

		int num = acceptor.getManagedSessionCount();
		System.out.println("num:" + num);
		return num;
	}
	
	public IoSession getClientSessionBy(String ip,int port)
	{
        Map conMap = acceptor.getManagedSessions();  
        
        IoSession session;  

        Iterator iter = conMap.keySet().iterator();  
        while (iter.hasNext()) 
        {  
            Object key = iter.next();  
            session = (IoSession)conMap.get(key);  
            String clientIP = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
    		int clientPort = ((InetSocketAddress)session.getRemoteAddress()).getPort();

            if(clientIP.equals(ip) && port == clientPort)
            {
            	return session;
            }
              
        } 
        log.warn("the client is not on line,the ip is" + ip);
        return null;
	}
	
	public  void sendConMessage()
	{  
        
        IoSession session;  
          
        Map conMap = acceptor.getManagedSessions();  
          
        Iterator iter = conMap.keySet().iterator();  
        while (iter.hasNext()) 
        {  
            Object key = iter.next();  
            session = (IoSession)conMap.get(key);  
            session.write("" + key.toString());  
        }  
    }  
 

}
