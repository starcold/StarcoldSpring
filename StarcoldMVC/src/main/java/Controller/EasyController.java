package Controller;

import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by starcold on 2020/8/7.
 * */
public class EasyController implements HttpRequestHandler {

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        //HttpRequestHandler 本来用来返回静态资源的，所以没有用ModelAndView
        httpServletResponse.getWriter().append("hello easy mvc");
        httpServletResponse.getWriter().flush();
    }
}
