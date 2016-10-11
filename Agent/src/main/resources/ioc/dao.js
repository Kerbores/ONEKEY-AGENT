var ioc = {
    "dataSource": {
        "type": "com.alibaba.druid.pool.DruidDataSource",
        "events": {
            "create": "init",
            "depose": "close"
        },
        "fields": {
            "driverClassName": {java:"$conf.get('db.driver')"},
            "url": {java:"$conf.get('db.url')"},
            "username": {java:"$conf.get('db.username')"},
            "password": {java:"$conf.get('db.password')"},
            "validationQuery": {java:"$conf.get('db.validationQuery')"},
            "maxActive": {java:"$conf.get('db.maxActive')"},
            "testWhileIdle" : true,
            "defaultAutoCommit": false
        }
    },
    "dao": {
        "type": "org.nutz.dao.impl.NutDao",
        "args": [{
            "refer": "dataSource"
        }]
    }
};
