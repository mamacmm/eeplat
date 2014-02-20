<#include "TFormBase.ftl">


	<input id="${model.objUid}" name="${model.objUid}.id" value="" type="hidden">
	<input name="${model.objUid}.orgName" type="text" suggestFields="orgName" suggestUrl="[{id:'1', orgName:'技术部', orgNum:'1001'},{id:'2', orgName:'人事部', orgNum:'1002'},{id:'3', orgName:'销售部', orgNum:'1003'}]" lookupGroup="${model.objUid}"/>
				
