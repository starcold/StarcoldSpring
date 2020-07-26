/**
 * author: starcold
 * createTime: 2020/07/10
 * context: bean信息定义
 * updateTime:
 * updateContext:
 */
package ioc.support;
public class BeanDefinition {
    private String id;
    private Class clazz;

    /**
     * @name BeanDefinition
     * @param id String
     * @param clazz Class
     * @return null
     * @description：构造函数
     * */
    public BeanDefinition(String id, Class clazz){
        this.id = id;
        this.clazz = clazz;
    }

    /**
     * @name getInstance
     * @param null
     * @return Object
     * @description：获取实例
     * */
    public Object getInstance(){
        try{
            return clazz.newInstance();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //region Getter && Setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    //endregion
}
