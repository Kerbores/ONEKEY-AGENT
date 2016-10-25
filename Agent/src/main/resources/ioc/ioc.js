var ioc = {
	quartz : {
		type : "org.nutz.ioc.impl.PropertiesProxy",
		fields : {
			paths : [ "quartz" ],
			utf8 : false
		}
	},
	sigar : {
		type : "org.nutz.ioc.impl.PropertiesProxy",
		fields : {
			paths : [ "sigar","config/sigar" ],
			utf8 : false
		}
	},
	log : {
		type : "org.nutz.aop.interceptor.LoggingMethodInterceptor"
	},
	$aop : {
		type : "org.nutz.ioc.aop.config.impl.ComboAopConfigration",
		fields : {
			aopConfigrations : [ {
				type : "org.nutz.ioc.aop.config.impl.JsonAopConfigration",
				fields : {
					itemList : [ [ "club\\.zhcs\\..+", ".+", "ioc:log" ], // 日志
					[ "club\\.zhcs\\.agent\\.modules\\..+", "^(?!.*(_)).*$", "ioc:apm" ],// apm
					[ "club\\.zhcs\\.agent\\.modules\\..+", "^(?!.*(_)).*$", "ioc:uaDebug" ],// ua
					[ "club\\.zhcs\\.agent\\.modules\\..+", "^(?!.*(_)).*$", "ioc:aopTimer" ],// 模块执行时间
					[ "club\\.zhcs\\.agent\\.modules\\..+", "^(?!.*(_)).*$", "ioc:txSERIALIZABLE" ] // 模块事务处理
					],
				}
			}, {
				type : "org.nutz.ioc.aop.config.impl.AnnotationAopConfigration"
			} ]
		}
	}
};