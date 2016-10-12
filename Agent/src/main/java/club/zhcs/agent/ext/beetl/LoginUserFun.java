package club.zhcs.agent.ext.beetl;

import org.beetl.core.Context;
import org.beetl.core.Function;
import org.nutz.mvc.Mvcs;

import club.zhcs.agent.Agent.SessionKeys;

/**
 * 
 * @author kerbores
 *
 * @email kerbores@gmail.com
 *
 */
public class LoginUserFun implements Function {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.beetl.core.Function#call(java.lang.Object[],
	 * org.beetl.core.Context)
	 */
	@Override
	public Object call(Object[] paras, Context ctx) {
		return Mvcs.getReq().getSession().getAttribute(SessionKeys.USER_KEY);
	}

}
