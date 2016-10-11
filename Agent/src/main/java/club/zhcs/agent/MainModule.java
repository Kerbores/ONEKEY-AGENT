package club.zhcs.agent;

import org.hyperic.sigar.Sigar;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.ChainBy;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;
import org.nutz.plugin.sigar.gather.CPUGather;
import org.nutz.plugin.sigar.gather.MemoryGather;

@Modules(scanPackage = true, packages = "club.zhcs.demo.demo.modules")
@IocBy(type = ComboIocProvider.class, args = { "*js", "ioc", "*anno", "club.zhcs.demo.demo.modules", "*tx", "*async" })
@SetupBy(MainSetup.class)
@ChainBy(args = { "chain" })
@Ok("json")
public class MainModule {

	@At
	public NutMap cpu() {
		return NutMap.NEW().addv("cpu", CPUGather.gather(new Sigar()));
	}

	@At
	public NutMap mem() {
		return NutMap.NEW().addv("cpu", MemoryGather.gather(new Sigar()));
	}

}
