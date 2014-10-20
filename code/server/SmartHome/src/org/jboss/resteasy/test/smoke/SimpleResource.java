/**
 *  ������: RestEasy���Գ���
 */
package org.jboss.resteasy.test.smoke;

import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

public class SimpleResource {
	// ���ʵ�ַΪ:http://localhost:8080/RestEasy/rest/hello
	@GET
	@Path("/hello")
	@Produces("application/json")  
	public String getWild() {
		return "hello resteasy";
	}

	@GET
	@Path("basic")
	@Produces("application/json")  
	public String getBasic() {
		System.out.println("getBasic()");
		return "basic";
	}

	@PUT
	@Path("basic")
 
	public void putBasic(String body) {
		System.out.println(body);
	}

	@GET
	@Path("queryParam")
 
	public String getQueryParam(@QueryParam("param") String param) {
		return param;
	}

	@GET
	@Path("matrixParam")
 
	public String getMatrixParam(@MatrixParam("param") String param) {
		return param;
	}

	@GET
	@Path("uriParam/{param}")
 
	public int getUriParam(@PathParam("param") int param) {
		return param;
	}
}