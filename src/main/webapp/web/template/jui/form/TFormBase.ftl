<#macro JudgeStyle a>

	<#if (a.isOutGridAction?exists && ( a.isOutGridAction == 1)) >
		class='ctlBtn btn'   <#compress><@JudgeMobileIcon a/> </#compress>
	<#elseif (a.cssClass?exists ) >
		class='${a.cssClass} btn'   <#compress><@JudgeMobileIcon a/> </#compress>
	<#elseif  (a.l10n?exists && (a.l10n?contains('新增') || a.l10n?contains('新建') || a.l10n?contains('创建')
	                            || a.l10n?lower_case?contains('create') ||  a.l10n?lower_case?contains('new') ||  a.l10n?contains('insert')
	       ) ) >
		class='add'   <#compress><@JudgeMobileIcon a/> </#compress>
	<#elseif (a.l10n?exists && (a.l10n?contains('修改') || a.l10n?contains('编辑')
		                            || a.l10n?lower_case?contains('modify') ||  a.l10n?lower_case?contains('edit')
	
	  	)) >
		class='edit'  <#compress><@JudgeMobileIcon a/> </#compress>
	<#elseif (a.l10n?exists &&( a.l10n?contains('删除') 
							|| a.l10n?lower_case?contains('delete') ||  a.l10n?lower_case?contains('remove')
		)) >
		class='delete'  <#compress><@JudgeMobileIcon a/> </#compress>
	<#elseif (a.l10n?exists &&( a.l10n?contains('复制') 
							|| a.l10n?lower_case?contains('copy') 
		)) >
		class='default'  <#compress><@JudgeMobileIcon a/> </#compress>	
	<#elseif (a.l10n?exists && (a.l10n?contains('查看') 
							|| a.l10n?lower_case?contains('browse') 
	)) >
		class='default'  <#compress><@JudgeMobileIcon a/> </#compress>
	<#elseif (a.linkPaneModel?exists && (a.linkPaneModel.cssClass)?exists ) >
		class='${a.linkPaneModel.cssClass} btn'  <#compress><@JudgeMobileIcon a/> </#compress>
	<#else>
	    class='role-setup btn'	  <#compress><@JudgeMobileIcon a/> </#compress>
	</#if>
</#macro>


<#macro JudgeMobileIcon a>
	<#if (a.image?exists )>
		data-icon='${a.image}' data-theme='c' 
	<#elseif  (a.l10n?exists &&( a.l10n?contains('新增') || a.l10n?contains('新建')
	                          || a.l10n?lower_case?contains('create') ||  a.l10n?lower_case?contains('new') ||  a.l10n?contains('insert')
	       ) ) >
		 data-icon='plus'  data-theme='b'
	<#elseif (a.l10n?exists &&(a.l10n?contains('修改')   
						|| a.l10n?lower_case?contains('modify') ||  a.l10n?lower_case?contains('edit')
	  	)) >
		 data-icon='gear' data-theme='c'
	<#elseif (a.l10n?exists &&(a.l10n?contains('删除')
						|| a.l10n?lower_case?contains('delete') ||  a.l10n?lower_case?contains('remove')
		)) >
		 data-icon='delete' data-theme='c'
	<#elseif (a.l10n?exists &&(a.l10n?contains('查看') 
		|| a.l10n?lower_case?contains('browse') 
		)) >
		 data-icon='info' data-theme='c'
	<#elseif (a.l10n?exists &&(a.l10n?contains('查询')
		|| a.l10n?lower_case?contains('search') 
		)) >
		 data-icon='search' data-theme='b'
	<#elseif (a.l10n?exists &&(a.l10n?contains('保存')
		|| a.l10n?lower_case?contains('save') 
		|| a.l10n?lower_case?contains('store') 
	)) >
		 data-icon='gear' data-theme='b'
	<#else>
	   data-icon='gear' data-theme='c'
	</#if>
</#macro>




<#macro JudgeBootIcon a>
	<#if (a.image?exists )>
		data-icon='${a.image}' data-theme='c' 
	<#elseif  (a.l10n?exists &&( a.l10n?contains('新增') || a.l10n?contains('新建')
	                          || a.l10n?lower_case?contains('create') ||  a.l10n?lower_case?contains('new') ||  a.l10n?contains('insert')
	       ) ) >
		 <i class="icon-plus"></i>
	<#elseif (a.l10n?exists &&(a.l10n?contains('修改')   
						|| a.l10n?lower_case?contains('modify') ||  a.l10n?lower_case?contains('edit')
	  	)) >
		  <i class="icon-edit"></i>
	<#elseif (a.l10n?exists &&(a.l10n?contains('删除')
						|| a.l10n?lower_case?contains('delete') ||  a.l10n?lower_case?contains('remove')
		)) >
		  <i class="icon-remove"></i>
	<#elseif (a.l10n?exists &&(a.l10n?contains('查看') 
		|| a.l10n?lower_case?contains('browse') 
		)) >
		  <i class="icon-info-sign"></i>
	<#elseif (a.l10n?exists &&(a.l10n?contains('查询')
		|| a.l10n?lower_case?contains('search') 
		)) >
		 <i class="icon-search"></i>
	<#elseif (a.l10n?exists &&(a.l10n?contains('保存')
		|| a.l10n?lower_case?contains('save') 
		|| a.l10n?lower_case?contains('store') 
	)) >
		  <i class="icon-ok"></i>
 
	<#elseif (a.l10n?exists &&(a.l10n?contains('复制')
		|| a.l10n?lower_case?contains('copy') 
	)) >
		  <i class="icon-camera"></i>
	<#else>   
	    
	</#if>
</#macro>


