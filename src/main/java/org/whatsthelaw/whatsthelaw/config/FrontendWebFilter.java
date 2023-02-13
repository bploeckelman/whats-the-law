package org.whatsthelaw.whatsthelaw.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//@Component
public class FrontendWebFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var path = request.getRequestURI();
        var pathIsNotApi = !path.startsWith("/api");
        var pathHasNoDot = !path.contains(".");
        var pathIsValid  = path.matches("/(.*)");
        if (pathIsNotApi && pathHasNoDot && pathIsValid) {
            var baseRequestDispatcher = request.getRequestDispatcher("/");
            baseRequestDispatcher.forward(request, response);
        }
        filterChain.doFilter(request, response);
    }

}
