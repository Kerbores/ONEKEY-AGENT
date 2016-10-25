/**
 * Created by kerbores on 2016/10/8.
 */
var Common = {
	init : function() {
		console.log("common.js is runnning...");
	},
	getRootPath : function() {
		return contextPath;
	},
	validationFail : function(msg, dom) {
		layer.tips(msg, dom);
	},
	openUrl : function(url, title, width, height, closeBtn) {
		layer.open({
			type : 2,
			shadeClose : true,
			title : title,
			closeBtn : closeBtn,
			area : [ width + 'px', height + 'px' ], // 宽高
			content : contextPath + url
		});
	},
	closePopWindow : function() {
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	},
	showMessage : function(msg, title) {
		if (layer) {
			layer.msg(msg, {
				icon : 1,
				title : title || false
			});
		} else {
			alert(msg);
		}
	}
}

$(function() {
	$(document).on('click', '.search-btn', function() { //搜索
		if ($('.search-form').find('input').validation(function(status, dom, errorMsg, defaultValue) {
				status ? $.noop() : function() {
					Common.validationFail(errorMsg, $(dom).next()[0]);
				}.call();
			})) {
			$('.search-form').submit();
		}
	}).on('click', '.btn-pop', function() { //pop弹出
		layer.open({
			type : 2,
			title : $(this).data('title'),
			shadeClose : false,
			closeBtn : false,
			shade : 0.8,
			area : [ $(this).data('width') + 'px', $(this).data('height') + 'px' ],
			content : Common.getRootPath() + $(this).data('url')
		});
	}).on('click', '.btn-back', function() { //返回按钮
		history.go(-1);
	}).on('click', '.btn-delete', function() { //删除按钮
		var url = $(this).data('url');
		var id = $(this).data('id');
		layer.confirm('确认删除这条数据 ?', {
			icon : 3,
			title : '删除提示'
		}, function(index) {
			$.post(Common.getRootPath() + url, {
				id : id
			}, function(result) {
				layer.close(index); // 关闭弹窗
				if (result.operationState == 'SUCCESS') {
					location.reload(); // 刷新页面
				} else {
					Common.showMessage(result.data.reason);
				}
			}, 'json');

		});
	}).on('click', '.btn-dialog-undo', Common.closePopWindow()).on('click', '.pagination .disabled a', function() { //分页条按钮禁用
		return false;
	})
})

Date.prototype.format = function(format) {
	var date = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S+" : this.getMilliseconds()
	};
	if (/(y+)/i.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
	}
	for (var k in date) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
		}
	}
	return format;
}