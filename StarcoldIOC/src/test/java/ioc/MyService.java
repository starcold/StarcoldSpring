package ioc; /**
 * author: starcold
 * createTime: 2020/07/13
 * context: 测试Service
 * updateTime:
 * updateContext:
 */
import ioc.annotation.Component;

@Component("myservice")
public class MyService {

    /**
     * @name print
     * @return null
     * @description：打印输入的字符
     * */
    public void print(String s){
        System.out.println(s);
    }
}
