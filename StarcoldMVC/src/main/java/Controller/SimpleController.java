package Controller;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SimpleController implements Controller {
    public ModelAndView getName(HttpServletRequest requset, HttpServletResponse response){
        ModelAndView mv = new ModelAndView("userView");
        mv.addObject("name", "xinghanhan");
        if(requset.getParameter("error") != null){
            throw new RuntimeException(requset.getParameter("error"));//抛出异常->doDispatch();
        }
        return mv;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        return getName(httpServletRequest, httpServletResponse);
    }
}
