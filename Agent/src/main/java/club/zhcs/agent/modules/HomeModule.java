package club.zhcs.agent.modules;

import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import club.zhcs.titans.nutz.captcha.JPEGView;
import club.zhcs.titans.utils.db.Result;

public class HomeModule extends BaseModule {

	/**
	 * 首页
	 * 
	 * @return
	 */
	@At("/")
	@Filters
	@Ok("beetl:pages/login/login.html")
	public Result index() {
		return Result.success();
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
