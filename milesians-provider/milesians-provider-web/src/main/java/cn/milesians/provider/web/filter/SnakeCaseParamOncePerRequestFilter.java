package cn.milesians.provider.web.filter;

import com.google.common.base.CaseFormat;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author milesians
 * @date 2023/4/7
 * @since 1.0
 */
public class SnakeCaseParamOncePerRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        final Map<String, String[]> parameters = new ConcurrentHashMap<>(request.getParameterMap().size());

        for (String param : request.getParameterMap().keySet()) {
            String camelCaseParam = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, param);

            parameters.put(camelCaseParam, request.getParameterValues(param));
            parameters.put(param, request.getParameterValues(param));
        }

        filterChain.doFilter(new HttpServletRequestWrapper(request) {
            @Override
            public String getParameter(final String name) {
                return parameters.containsKey(name) ? parameters.get(name)[0] : null;
            }

            @Override
            public Map<String, String[]> getParameterMap() {
                return parameters;
            }

            @Override
            public Enumeration<String> getParameterNames() {
                return Collections.enumeration(parameters.keySet());
            }

            @Override
            public String[] getParameterValues(final String name) {
                return parameters.get(name);
            }
        }, response);
    }
}
