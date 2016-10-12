var ioc = {
    "conf": {
        "type": "club.zhcs.agent.MainConfig",
        "args": ["customer/"]
    },
    config : {
		type : "org.nutz.ioc.impl.PropertiesProxy",
		fields : {
			ignoreResourceNotFound : true,
			utf8 : false,
			paths : 'conf'
		}
	}
};
