package club.zhcs.agent.modules;

import javax.servlet.http.HttpServletRequest;

import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.View;
import org.nutz.mvc.ViewModel;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Attr;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import club.zhcs.agent.Agent.SessionKeys;
import club.zhcs.agent.model.bean.acl.User;
import club.zhcs.titans.nutz.captcha.JPEGView;
import club.zhcs.titans.utils.codec.DES;
import club.zhcs.titans.utils.db.Result;

public class HomeModule extends BaseModule {

	/**
	 * 首页
	 * 
	 * @return
	 */
	@At("/")
	@Filters
	@Ok("re:beetl:pages/login/login.html")
	public String index(@Attr(SessionKeys.USER_KEY) User user, HttpServletRequest request, ViewModel model) {
		if (user != null) {
			return ">>:/system/main";
		}
		String cookie = _getCookie("kerbores");
		if (!Strings.isBlank(cookie)) {
			NutMap data = Lang.map(DES.decrypt(cookie));
			request.setAttribute("loginInfo", data);
		}
		return null;
	}

	/**
	 * 验证码
	 * 
	 * @param length
	 *            验证码长度,默认4位
	 * @return
	 */
	@At
	@Filters
	public View captcha(@Param(value = "length", df = "4") int length) {
		return new JPEGView(null, length);
	}

	/**
	 * 安装
	 * 
	 * @return
	 */
	@At
	public Result install() {
		return Result.success();
	}
}
