package club.zhcs.agent.modules.admin.acl;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Attr;
import org.nutz.mvc.annotation.Ok;

import club.zhcs.agent.Agent;
import club.zhcs.agent.model.bean.acl.User;
import club.zhcs.agent.model.biz.acl.UserService;
import club.zhcs.titans.nutz.module.base.AbstractBaseModule;
import club.zhcs.titans.utils.db.Result;

@At("setting")
public class SettingModule extends AbstractBaseModule {

	@Inject
	UserService userService;
	
	@At
	@Ok("beetl:pages/admin/auth/user/detail.html")
	public Result profile(@Attr(Agent.SessionKeys.USER_KEY)User user) {
		return Result.success().setTitle("个人信息").addData("user", userService.fetch(user.getId()));
	}
}
