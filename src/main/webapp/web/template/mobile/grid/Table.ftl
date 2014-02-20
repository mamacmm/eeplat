<#--定义dataBinding-->
<#assign dataBind = "com.exedosoft.plat.template.BindData2FormModel"?new()/>  
<#assign i18n = "com.exedosoft.plat.template.TPLI18n"?new()>  

<table data-role="table" id="table-custom-2" data-mode="columntoggle" class="ui-body-d ui-shadow table-stripe ui-responsive" data-column-btn-theme="b" data-column-btn-text="Columns to display..." data-column-popup-theme="a">
    <thead>
      <tr class="ui-bar-d">
        <th data-priority="2">Rank</th>
        <th>Movie Title</th>
        <th data-priority="3">Year</th>
        <th data-priority="1"><abbr title="Rotten Tomato Rating">Rating</abbr></th>
        <th data-priority="5">Reviews</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <th>1</th>
        <td><a href="http://en.wikipedia.org/wiki/Citizen_Kane" data-rel="external">Citizen Kane</a></td>
        <td>1941</td>
        <td>100%</td>
        <td>74</td>
      </tr>
    </tbody>
  </table>