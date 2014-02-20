<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/>  
<div class="list_box">
    <ul>
    
    <#if (needlogin) >
       <script>
    		judgeShareID();
       </script>
    <#elseif (data?size == 0)>
         	无法连接EEPlat AppShare服务器！请联系您的系统管理员或者直接联系<a href="mailto:contact@eeplat.com">EEPlat团队(contact@eeplat.com)</a>。
    </#if>
    
    
    
	<#list data as ins>

        <li class="list_item">
            <div class="left">
                <a href="">
                    <img  border=0  width="72px" height="72px" src="${ins.map.image_path?default("web/default/images/180.jpg")}" ></a>
            </div>
            <dl class="list_content">
                <dt>
                    <span class="font14 list_title">
                        <a href="" >
                           ${ins.map.app_name!}</a></span>
                    <#if (ins.map.app_version??) >       
                    	<span class="font12 list_version">(${ins.map.app_version})</span>
                    </#if>
                    <span class="star_bg star_3half_s list_star"></span>
                    <div class="official_tips_list"></div>
                </dt>
                <dd>
                    <div class="font12 list_describe">
                        ${ins.map.app_desc!}
                        <span class="font12 box_ensoft"></span>
                        <span class="eng_font"></span>
                    </div>
                    <div class="font12">
                        <span class="sub_seach_time">
                            ${ins.map.share_date?substring(0,ins.map.share_date?index_of(" "))} 发布</span>
                        <span class="sub_right_9">提供者：${ins.map.auth_user_name!}</span>
                    </div>
                </dd>
            </dl>
            <div class="list_r_w">
	              	<#if (model.rightOutGridFormLinks?size > 0) > 
						<#list model.rightOutGridFormLinks as item> 
						   <#if '${dataBind(ins,item)}' ==''>  ${item.htmlValue} </#if> &nbsp; 
						</#list>
					</#if>
            </div>
            <div class="z">
            </div>
        </li>
 	</#list>
    </ul>
</div>