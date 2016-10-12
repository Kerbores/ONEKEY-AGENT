package club.zhcs.agent;

import org.apache.log4j.PropertyConfigurator;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

public class MainSetup implements Setup {

    private final static Log log = Logs.get();

    @Override
    public void init(NutConfig nc) {
    	PropertyConfigurator.configure("config/log4j.properties");
        MainConfig conf = nc.getIoc().get(MainConfig.class, "conf");
        nc.setAttribute("rs", conf.getAppRs());
        nc.setAttribute("appnm", conf.get("app-name", "Demo"));
        log.infof("Demo version %s", Agent.VERSION);
    }

    @Override
    public void destroy(NutConfig nc) {}
}
