package club.zhcs.agent.chain;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.aop.AuthorizingAnnotationMethodInterceptor;
import org.nutz.integration.shiro.NutShiroProcessor;
import org.nutz.lang.ContinueLoop;
import org.nutz.lang.Each;
import org.nutz.lang.ExitLoop;
import org.nutz.lang.Lang;
import org.nutz.lang.LoopException;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.ActionChain;
import org.nutz.mvc.ActionInfo;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Processor;
import org.nutz.mvc.impl.NutActionChain;
import org.nutz.mvc.impl.processor.ActionFiltersProcessor;

import club.zhcs.agent.ext.shiro.anno.ThunderRequiresPermissions;
import club.zhcs.agent.ext.shiro.anno.ThunderRequiresRoles;
import club.zhcs.agent.ext.shiro.aop.ThunderPermissionAnnotationMethodInterceptor;
import club.zhcs.agent.ext.shiro.aop.ThunderRoleAnnotationMethodInterceptor;
import club.zhcs.titans.nutz.chain.KerboresActionChainMaker;
import club.zhcs.titans.nutz.processor.KerboresFailProcessor;

/**
 * 
 * @author kerbores
 *
 * @email kerbores@gmail.com
 *
 */
public class ThunderChainMaker extends KerboresActionChainMaker {

	private Log log = Logs.get();

	@Override
	public ActionChain eval(final NutConfig config, final ActionInfo ai) {
		List<Processor> list = normalList();

		List<AuthorizingAnnotationMethodInterceptor> interceptors = new ArrayList<AuthorizingAnnotationMethodInterceptor>();

		interceptors.add(new ThunderPermissionAnnotationMethodInterceptor());
		interceptors.add(new ThunderRoleAnnotationMethodInterceptor());

		addBefore(list, ActionFiltersProcessor.class, new NutShiroProcessor(interceptors, ThunderRequiresPermissions.class, ThunderRequiresRoles.class));

		Processor error = new KerboresFailProcessor();
		Lang.each(list, new Each<Processor>() {

			@Override
			public void invoke(int paramInt1, Processor processor, int paramInt2) throws ExitLoop, ContinueLoop, LoopException {
				try {
					processor.init(config, ai);
				} catch (Throwable e) {
					log.error(e);
				}
			}
		});
		try {
			error.init(config, ai);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return new NutActionChain(list, error, ai);
	}
}
