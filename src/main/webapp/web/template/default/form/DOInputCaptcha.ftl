<#include "TFormBase.ftl">
<input name="randcode" id="${model.objUid}" title="验证码" required="true" 
type="text" "="" class="input" style="height:22px; width:80px; margin:0;padding:0">
&nbsp;
<img src="${imageurl}? + Math.random()" height="25px" 
style="cursor:pointer;margin:0;padding:0;vertical-align: middle;" onclick="this.src='${imageurl}? + Math.random()'" border="0">