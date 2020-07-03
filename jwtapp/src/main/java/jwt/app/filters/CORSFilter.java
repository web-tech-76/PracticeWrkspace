package jwt.app.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

@Configuration
@Primary
@Order(1)
public class CORSFilter implements Filter {

	public CORSFilter() {
		// TODO Auto-generated constructor stub
		super();
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse response = (HttpServletResponse) res;
		//HttpServletRequest request= (HttpServletRequest) req;

		
		/*
		 * System.out.println("request Method" + request.getMethod());
		 * 
		 * if (request.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.toString())) {
		 * response.addHeader("Access-Control-Allow-Origin", "*");
		 * response.setStatus(HttpStatus.NO_CONTENT.value());
		 * 
		 * }
		 */
				
		response.setHeader("Access-Control-Allow-Headers",
				"X-Requested-With,Access-Control-Allow-Origin, Content-Type,Authorization,Origin,Accept,Access-Control-Request-Method,Access-Control-Request-Headers "
				+ ", DNT,X-CustomHeader,Keep-Alive,User-Agent,If-Modified-Since,Cache-Control,Content-Range,Range");
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
		response.setHeader("Access-Control-Max-Age", "1728000");

		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

}
