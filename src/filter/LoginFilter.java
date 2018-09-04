package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (((HttpServletRequest) servletRequest).getSession() == null){
            ((HttpServletResponse) servletResponse).sendRedirect("user/preLogin.action");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
