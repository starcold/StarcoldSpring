package Controller;


        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.servlet.ModelAndView;

@Controller
public class FreemarkController {
    @RequestMapping
    public ModelAndView getName(String name){
        ModelAndView mv = new ModelAndView("/WEB-INF/pages/userView2.ftl");
        mv.addObject("name", "hello:" + name);
        return mv;
    }
}
