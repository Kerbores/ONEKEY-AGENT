package club.zhcs.agent.modules;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public abstract class BaseModule {

    @Inject("refer:dao")
    protected Dao dao;
}
