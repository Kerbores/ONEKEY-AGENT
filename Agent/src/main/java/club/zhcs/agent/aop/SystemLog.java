package club.zhcs.agent.aop;  
  
import java.lang.annotation.*;  
  
/**
 *  
 * @author kerbores
 *
 * @email kerbores@gmail.com
 *
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public  @interface SystemLog {  
  
	String description()  default "";  //
	String methods()  default "";  //新增用户
    String module()  default "";  //模块名称 系统管理-用户管理－列表页面
  
  
}  
  
