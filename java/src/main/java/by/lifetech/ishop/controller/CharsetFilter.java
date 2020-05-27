package by.lifetech.ishop.controller;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {

    private static final String CHARACTER_ENCODING_ATTR = "characterEncoding";
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter(CHARACTER_ENCODING_ATTR);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
