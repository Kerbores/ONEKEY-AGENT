package club.zhcs.agent.modules;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;

import club.zhcs.titans.nutz.module.base.AbstractBaseModule;

public abstract class BaseModule extends AbstractBaseModule {

	@Inject("refer:dao")
	protected Dao dao;
}
