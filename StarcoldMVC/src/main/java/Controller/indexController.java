/**
 * author: starcold
 * createTime: 2020/08/04
 * context: indexController
 * updateTime:
 * updateContext:
 */

package Controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class indexController {

    @RequestMapping(value = {"/index","/index2"}, method = RequestMethod.GET) //访问index、index2接口，返回的东西都一样
    //@GetMapping
    public String helloWorld(Model model){
        model.addAttribute("message", "xinghan");
        return "index";
    }

    @RequestMapping("update/{userid}") //访问index、index2接口，返回的东西都一样
    public String helloWorld2(@PathVariable Integer userid){
        System.out.println(userid);
        return "index";
    }
}
