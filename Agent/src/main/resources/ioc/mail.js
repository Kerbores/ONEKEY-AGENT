var ioc = {
	emailAuthenticator : {
		type : "org.apache.commons.mail.DefaultAuthenticator",
		args : [ {
			java : "$config.get('mail.UserName')"
		}, {
			java : "$config.get('mail.Password')"
		} ]
	},
	htmlEmail : {
		type : "org.apache.commons.mail.ImageHtmlEmail",
		singleton : false,
		fields : {
			hostName : {
				java : "$config.get('mail.HostName')"
			},
			smtpPort : {
				java : "$config.get('mail.SmtpPort')"
			},
			authenticator : {
				refer : "emailAuthenticator"
			},
			SSLOnConnect : {
				java : "$config.get('mail.SSLOnConnect')"
			},
			from : {
				java : "$config.get('mail.From')"
			},
			charset : {
				java : "$config.get('mail.charset', 'UTF-8')"
			}
		}
	}
};