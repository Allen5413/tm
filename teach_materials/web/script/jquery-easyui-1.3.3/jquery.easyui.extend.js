/**
 * 扩展validatebox的验证规则
 */
var _passwordPolicy = null;
var passwordPolicy = function() {console.log(contextPath);
	if(_passwordPolicy == null){
		
		$.ajax({
		  url: contextPath+"/manage/system/user/passwordPolicy.html",
		  async : false,
		  success : function(data){
			  //console.log("loaded password policy!" + data);
			  _passwordPolicy = data;
		  },
		  error :function(){
			  console.log("load password policy faild!");
		  }
		})
	}
	return _passwordPolicy;
}

$.extend($.fn.validatebox.defaults.rules,{
	/** 只能是正数，最多2位小数 不能输零 */
	positiveTwoPoint:{  
        validator:function(value,param){  
            if (value){  
            	if(value <= 0){
            		return false;
            	}else{
            		if(value.length > param[0]){
            			return false;
            		}else{
            			return /^\d+(\.\d{0,2})?$/.test(value);  
            		}
            	}
            } else {  
                return true;  
            }  
        },  
        message:'请输入最多2位小数的正数,总长度不能超过{0}'  
    }, 
    
    /** 电话号码,包括手机号码和座机号码 */
    localPhone:{
    	validator:function(value){
    		var reg = /^(\d{3,4}-)?(\d{7,8})$/;
    		var regph=/^1[3|4|5|8|9]\d{9}$/;
    		return regph.test(value) || reg.test(value);
    	},
    	message:'请输入正确的电话号码'
    	
    },
    /** 只能是正数，最多2位小数 可以输零 */
	positiveTwoPointMayZero:{  
        validator:function(value,param){  
            if (value){  
            	if(value < 0){
            		return false;
            	}else{
            		if(value.length > param[0]){
            			return false;
            		}else{
            			return /^\d+(\.\d{0,2})?$/.test(value);  
            		}
            	}
            } else {  
                return true;  
            }  
        },  
        message:'请输入最多2位小数的正数,总长度不能超过{0}'  
    },  
	
	/** 只能是正整数 不能输0 */
	positiveInt:{  
        validator:function(value,param){  
            if (value){  
                return /^[0-9]*[1-9][0-9]*$/.test(value);  
            } else {  
                return true;  
            }  
        },  
        message:'只能输入正整数.'  
    },  
    /** 只能是正整数 可以输0 */
	positiveIntMayZero:{  
        validator:function(value,param){  
            if (value){  
            	if(value < 0){
            		return false;
            	}else{
            		return /^-?\d+$/.test(value);  
            	}
            } else {  
                return true;  
            }  
        },  
        message:'只能输入正整数.'  
    },  

	/** byte长度验证 */
	byteLength : {
		validator : function(value, param) {
			var str = value;
			var minlen = param[0];
			var maxlen = param[1];
		    if (str == null) {
		        return false;
		    }
		    var l = str.length;
		    var blen = 0;
		    for (i = 0; i < l; i++) {
		        if ((str.charCodeAt(i) & 65280) != 0) {
		            blen++;
		        }
		        blen++;
		    }
		    if (blen > maxlen || blen < minlen) {
		        return false;
		    }
		    return true;			
			
		},
		message : '输入的数据长度超出范围.'
	},
	// 两个表单相等
	equals : {
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '两次输入不相同.'
	},
	// 验证汉子
	CHS : {
		validator : function(value) {
			return /^[\u0391-\uFFE5]+$/.test(value);
		},
		message : '只能输入汉字'
	},
	// 移动手机号码验证
	mobile : {// value值为文本框中的值
		validator : function(value) {
			var reg = /^1[3|4|5|8|9]\d{9}$/;
			return reg.test(value);
		},
		message : '输入手机号码格式不准确.'
	},
	// 国内邮编验证
	zipcode : {
		validator : function(value) {
			var reg = /^[1-9]\d{5}$/;
			return reg.test(value);
		},
		message : '邮编必须是非0开始的6位数字.'
	},
	// 用户账号验证(只能包括 _ 数字 字母)
	account : {// param的值为[]中值
		validator : function(value, param) {
			if (value.length < param[0]
					|| value.length > param[1]) {
				$.fn.validatebox.defaults.rules.account.message = '用户名长度必须在'
						+ param[0] + '至' + param[1] + '范围';
				return false;
			} else {
				if (!/^[\w]+$/.test(value)) {
					$.fn.validatebox.defaults.rules.account.message = '用户名只能数字、字母、下划线组成.';
					return false;
				} else {
					return true;
				}
			}
		},
		message : ''
	},
	password : {// value值为文本框中的值
		validator : function(value) {
			var vaild = true;					
			var t = /[a-z]/;
			var t2 = /[A-Z]/;
			var t3 = /[0-9]/;

			if(!t.test(value) && !t2.test(value)){
				vaild = false;
			}
			if(!t3.test(value)){
				vaild = false;
			}
			if(value.length < 8 || value.length > 20){
				vaild = false;
			}
			return vaild;
		},
		message : function(value) {
			return passwordPolicy().description;
		}
	},
	regex : {
		validator : function(value, param) {
			 console.log("regex="+param[0]);
			var regex = new RegExp(param[0]);
			return regex.test(value);
		},
		message : '无效的格式.'
	}

});



var formatDate = function(value) {
	return value;
	var date = new Date(value);
	alert(value);
	return date.getFullYear() + '-' + formatDatePart(date.getMonth() + 1) + '-'
			+ formatDatePart(date.getDate()) + " " + formatDatePart(date.getHours()) + ":" + formatDatePart(date.getMinutes())
			+ ":" + formatDatePart(date.getSeconds());
}

var formatDatePart = function(datePart){
	if(datePart < 10){ return datePart;}else{return datePart;}
}

/*
 * formatString功能 使用方法：formatString('字符串{0}字符串{0}字符串{1}','第一个变量','第二个变量');
 * @returns 格式化后的字符串
 */
var formatString = function(str) {
	for ( var i = 0; i < arguments.length - 1; i++) {
		eval("var re = /\\{" + i + "\\}/g;");
		str = str.replace(re, arguments[i + 1]);
	}
	return str;
};

var formatIcon = function(value){
	return "<span style='vertical-align:middle;display:inline-block; width:16px; height:16px;' class='"+value+"'></span>"
}

var formatRefrence = function(datagrid,filed,value){
	try {
		return $("#"+datagrid).datagrid('getData').data[filed][value];
	} catch (e) {
		return value;
	}
	
}

var formatAction = function(actionContainer,value,row){
	return formatString($('#'+actionContainer).html(),row.id);
}


/**
 * 
 * 接收一个以逗号分割的字符串，返回List，list里每一项都是一个字符串
 * 
 * @returns list
 */
stringToList = function(value) {
	if (value != undefined && value != '') {
		var values = [];
		var t = value.split(',');
		for ( var i = 0; i < t.length; i++) {
			values.push('' + t[i]);/* 避免他将ID当成数字 */
		}
		return values;
	} else {
		return [];
	}
};

function mapToOptions(map, forSearch) {
	var mapJson = "[";
	if (forSearch) {
		mapJson += "{id:'',text:'所有'}";
	}
	var i = 0;
	for ( var key in map) {
		if (i == 0 && !forSearch) {
			mapJson += "{id:" + key + "," + "text:'" + map[key] + "'}";
		} else {
			mapJson += ",{id:" + key + "," + "text:'" + map[key] + "'}";
		}
		i++;
	}
	mapJson += "]";
	return eval(mapJson);
}

/**
 * 使panel和datagrid在加载时提示
 */
$.fn.panel.defaults.loadingMessage = '加载中....';
$.fn.datagrid.defaults.loadMsg = '加载中....';

/**
 * @requires jQuery,EasyUI panel关闭时回收内存，主要用于layout使用iframe嵌入网页时的内存泄漏问题
 */
$.fn.panel.defaults.onBeforeDestroy = function() {
	var frame = $('iframe', this);
	try {
		if (frame.length > 0) {
			for ( var i = 0; i < frame.length; i++) {
				frame[i].contentWindow.document.write('');
				frame[i].contentWindow.close();
			}
			frame.remove();
			if ($.browser.msie) {
				CollectGarbage();
			}
		}
	} catch (e) {
	}
};

/**
 * 扩展datagrid默认的loadFilter,添加错误处理
 */
$.fn.datagrid.defaults.loadFilter = function(data, parent) {
	if(!data.success && data.message){
		  $.messager.alert('提示', data.message);
	      return null;
	  }
	  return data; 
}


/**
 * 通用异常处理
 */
var commonErrorFunction = function(XMLHttpRequest) {
	$.messager.progress('close');
	$.messager.alert('错误', XMLHttpRequest.responseText);
};

$.fn.datagrid.defaults.onLoadError = commonErrorFunction;
$.fn.treegrid.defaults.onLoadError = commonErrorFunction;
$.fn.tree.defaults.onLoadError = commonErrorFunction;
$.fn.combogrid.defaults.onLoadError = commonErrorFunction;
$.fn.combobox.defaults.onLoadError = commonErrorFunction;
$.fn.form.defaults.onLoadError = commonErrorFunction;

/**
 * 改变jQuery的AJAX默认属性和方法
 */
$.ajaxSetup({
	type : 'POST',
	error : commonErrorFunction
});

/**
 * 防止panel/window/dialog组件超出浏览器边界
 * 
 * @param left
 * @param top
 */
var easyuiPanelOnMove = function(left, top) {
	var l = left;
	var t = top;
	if (l < 1) {
		l = 1;
	}
	if (t < 1) {
		t = 1;
	}
	var width = parseInt($(this).parent().css('width')) + 14;
	var height = parseInt($(this).parent().css('height')) + 14;
	var right = l + width;
	var buttom = t + height;
	var browserWidth = $(window).width();
	var browserHeight = $(window).height();
	if (right > browserWidth) {
		l = browserWidth - width;
	}
	if (buttom > browserHeight) {
		t = browserHeight - height;
	}
	$(this).parent().css({/* 修正面板位置 */
		left : l,
		top : t
	});
};
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.panel.defaults.onMove = easyuiPanelOnMove;

/**
 * 
 * 更换EasyUI主题的方法
 * 
 * @param themeName
 *            主题名称
 */
var changeTheme = function(themeName) {
	var $easyuiTheme = $('#easyuiTheme');
	var url = $easyuiTheme.attr('href');
	var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName
			+ '/easyui.css';
	$easyuiTheme.attr('href', href);

	var $iframe = $('iframe');
	if ($iframe.length > 0) {
		for ( var i = 0; i < $iframe.length; i++) {
			var ifr = $iframe[i];
			$(ifr).contents().find('#easyuiTheme').attr('href', href);
		}
	}
	$.cookie('easyuiThemeName', themeName, {
		expires : 7
	});
	selectMenu("layout_menu_theme", themeName);
};

/**
 * 将form表单元素的值序列化成对象
 * 
 * @returns object
 */
serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};

/**
 * 从表单的父容器中序列化表单
 */
serializeObjectFromContainer = function(container) {
	var queryParams = {};
	$("input[name^='search']", container).each(function() {
		if (queryParams[this['name']]) {
			queryParams[this['name']] = queryParams[this['name']] + ","
					+ this['value'];
		} else {
			queryParams[this['name']] = this['value'];
		}
	});
	return queryParams;
};




/**
 * 判断对象是否为空对象
 */
isEmptyObject = function(obj) {
	for ( var name in obj) {
		return false;
	}
	return true;
};

var onDatagridDblClickCell = function(index, field, value) {
	$(this).datagrid("beginEdit", index);
	var ed = $(this).datagrid("getEditor", {
		index : index,
		field : field
	});
	$(ed.target).val(value);
};

