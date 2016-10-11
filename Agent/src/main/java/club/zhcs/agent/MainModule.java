package club.zhcs.agent;

import org.beetl.ext.nutz.BeetlViewMaker;
import org.nutz.integration.shiro.ShiroSessionProvider;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.SessionBy;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.annotation.UrlMappingBy;
import org.nutz.mvc.annotation.Views;
import org.nutz.mvc.ioc.provider.ComboIocProvider;
import org.nutz.plugins.apidoc.ApidocUrlMapping;
import org.nutz.plugins.apidoc.annotation.Api;
import org.nutz.plugins.apidoc.annotation.ApiMatchMode;

import club.zhcs.titans.nutz.module.base.AbstractBaseModule;

@Ok("json")
@Fail("http:500")
@Modules(scanPackage = true)
@SetupBy(MainSetup.class)
@Views({ BeetlViewMaker.class })
@SessionBy(ShiroSessionProvider.class)
@UrlMappingBy(ApidocUrlMapping.class)
// @ChainBy(type = ThunderChainMaker.class, args = {})
// @Filters({ @By(type = CheckSession.class, args = { SessionKeys.USER_KEY, "/"
// }) })
@Api(name = "Thunder nop api", description = "nop开放平台接口示例", match = ApiMatchMode.NONE)
@IocBy(type = ComboIocProvider.class, args = { "*anno", "club.zhcs", "*tx", "*js", "ioc", "*async", "*quartz", "quartz", "*sigar", "sigar" })
public class MainModule extends AbstractBaseModule {

}
