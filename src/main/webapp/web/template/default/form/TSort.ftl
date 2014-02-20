 <#assign i18n = "com.exedosoft.plat.template.TPLI18n"?new()> 
<img border='0' src='${contextPath}images/icon_up.gif' title="${i18n('向上')}" style="cursor:pointer" onclick="sortUp(this,'${model.linkService.bo.name}','${model.linkService.name}')">
<img border='0' src='${contextPath}images/icon_down.gif' title="${i18n('向下')}" style="cursor:pointer" onclick="sortDown(this,'${model.linkService.bo.name}','${model.linkService.name}')" >
																														  