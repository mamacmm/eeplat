var EELang =
{
loading				: "正在加载,请稍后......",

nameRequired		: "名称不可以为空!",
saveSuccess			: "保存成功!",
browseFile 			: "browse-files.gif",
dealDaemon			: "后台处理,请稍候......",
notDefineGoal		: "没有定义目标面板,请检查相关配置!",
accountRequired		: "请填写账号!",
pwdRequired			: "请填写密码!",
verificationRequired: "请填写验证码!",
clickRepeat		    : "请不要重复点击！",
infoDialog			: "信息窗口",
newDialog			: "新窗口",
noDefinedTaegetPane : "没有定义目标面板,请检查相关配置!",
noDialogName		: "您没有定义弹出窗口的名称",
onlySelectFile		: "只能选择图像类文件(*.jpg;*.gif;*.bmp)",
required			: "不能为空!",
vEmail				: "E-MAIL 地址格式不正确！",
vUrl				: "Url 地址格式不正确！",
vInt				: " 必须为整数类型!",
vNumber				: " 必须为数字类型!",
vMobile				: " 必须为手机录入格式!",
vPhone				: "必须为固定电话录入格式!",
vSBC				: "不能含有汉字!",
vSBCDOT				: "不能含有全角字符和点(.)!",
vLeng				: "的长度大于",
vLeng2				: "个字符!",
confirmDelete		: "你确定要删除吗",
confirmModfiy       : "你确定要修改吗？",
confirmShare		: "你确定要发布到AppShare吗？",
confirmSetup        : "你确定要安装吗",
confirmCopy			: "你确定要复制吗",
confirmInit			: "你确定要初始化吗",
confirmGenerate		: "你确定要生成关联配置吗",
confirmServiceToRule: "你确定要把该服务发布为规则吗",
confirmImport		: "你确定导入吗",
confirmRemove		: "你确定要移除吗",
noData              : "没有记录",
browse              : "浏 览",
more                : "更多",
less                : "更少",
logoEcho            : "图片尺寸不合法！",
wizard              : "向导"
};

var settings = {
	    labels: {
	        current: "current step:",
	        pagination: "Pagination",
	        finish: "完成",
	        next: "向后",
	        previous: "向前",
	        loading: "正在加载 ..."
	    }
};
var cnmsg = {  

	    required: "不能为空",   

	    remote: "不合法，请修改",   

	    email: "请输入正确格式的电子邮件",   

	    url: "请输入合法的网址",  

	    date: "请输入合法的日期",   

	    dateISO: "请输入合法的日期 (ISO).",  

	    number: "请输入合法的数字",   

	    digits: "只能输入整数",   

	    creditcard: "请输入合法的信用卡号",   

	    equalTo: "请再次输入相同的值",   

	    accept: "请输入拥有合法后缀名的字符串",   

	    maxlength: jQuery.format("请输入一个长度最多是 {0} 的字符串"),   

	    minlength: jQuery.format("请输入一个长度最少是 {0} 的字符串"),   

	    rangelength: jQuery.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),   

	    range: jQuery.format("请输入一个介于 {0} 和 {1} 之间的值"),   

	    max: jQuery.format("请输入一个最大为 {0} 的值"),  

	    min: jQuery.format("请输入一个最小为 {0} 的值")

};
jQuery.extend(jQuery.validator.messages, cnmsg); 
