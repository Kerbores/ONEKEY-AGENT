package club.zhcs.agent.modules;

import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.GET;
import org.nutz.mvc.annotation.Ok;

public class HomeModule extends BaseModule {

    private static final Log log = Logs.get();

    @At("demo/home/index")
    @Ok("jsp:jsp.home.index")
    @GET
    public void index() {
        log.info("HomeModule#index");
    }
}
