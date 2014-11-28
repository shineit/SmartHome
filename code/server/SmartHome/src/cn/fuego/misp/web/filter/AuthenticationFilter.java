package cn.fuego.misp.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.fuego.common.log.FuegoLog;
import cn.fuego.common.util.SystemConfigInfo;
import cn.fuego.common.util.validate.ValidatorUtil;
import cn.fuego.misp.web.constant.SessionAttrNameConst;
import cn.fuego.misp.web.model.user.UserModel;

 
/**
 * 
* @ClassName: AuthenticationFilter 
* @Description: TODO
* @author Tang Jun
* @date 2014-3-13 上午01:09:55 
*
 */
public class AuthenticationFilter implements Filter
{

	private FuegoLog log = FuegoLog.getLog(getClass());

	private static final String LOGIN_URL_FLAG = "login";
 

	//private static final  String LOGIN_PAGE = "login/login!home.action";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		 HttpServletRequest httpRequest = (HttpServletRequest)request;
		 
		 HttpServletResponse httpResponse = (HttpServletResponse)response;
		 HttpSession  session = httpRequest.getSession();
		 String url = httpRequest.getRequestURL().toString();
		 log.info("url is " + url);
		 //the url does not contains login url, we should check login or not
		 if(!url.endsWith(httpRequest.getContextPath()) 
			&& !url.endsWith(httpRequest.getContextPath()+"/")
			&& url.toLowerCase().indexOf(LOGIN_URL_FLAG)<0 && !isExcludePath(url))
		 {
			 log.info("the url need verify");
		     UserModel loginUser = (UserModel) session.getAttribute(SessionAttrNameConst.LOGIN_USER);
			 if(null == loginUser || ValidatorUtil.isEmpty(loginUser.getUserName()))
			 {
				 log.warn("the url have been redirect. url is " + url);
				 httpResponse.sendRedirect(httpRequest.getContextPath() );
				 return;
			 }
		 }
		 
 
		chain.doFilter(request, httpResponse);
	}

	private boolean isExcludePath(String url)
	{
		 String exPath = SystemConfigInfo.getConfigItem("EXCLUDLE_PATH");
		 if(ValidatorUtil.isEmpty(exPath))
		 {
			 log.warn("the exclude path is empty");
			 return false;
		 }

		 String[] pathArry = exPath.split(",");
		 for(int i=0;i<pathArry.length;i++)
		 {
			 if(pathArry[i].startsWith("."))
			 {
				 if(url.endsWith(pathArry[i]))
				 {
					 return true;
				 }
			 }
			 else
			 {
				 if(url.contains(pathArry[i]))
				 {
					 return true;
				 }
			 }
			 
		 }
		 return false;
	}
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy()
	{
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException
	{
 		
	}
 
}
