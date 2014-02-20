<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/toolbar/core.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/toolbar/toolbar.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/fileuploader/uploadify.css"    type="text/css" />  
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/treetable/jquery.treeTable.css" type="text/css" /> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/facebook/jquery.neosmart.fb.wall.css" type="text/css" /> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/facebook/status.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/ztree/zTreeStyle.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/combox/combox.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/js/jquery-plugin/pagination/pagination.css" type="text/css"/>

<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/css/xtree2.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/css/estop/estop.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/css/nav/exedo_nav.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/css/redmond/jquery-ui-1.8.16.custom.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/css/main/main_lan.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/web/default/workbench/workbench_style.css" type="text/css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/CodeMirror/codemirror.css"
	type="text/css" />	
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/CodeMirror/default.css"
	type="text/css" />	
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
	<title>Digg Header by CSS-Tricks</title>
	
	<link rel="stylesheet" type="text/css" href="simplelayoutstyle.css" />
	
	<script type="text/javascript" src="js/jquery.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
		   $("#zone-bar li>a").click(function() {
		   		var hidden = $(this).parents("li").children("ul").is(":hidden");
		   		
				$("#zone-bar>ul>li>ul").hide()        
			   	$("#zone-bar>ul>li>a").removeClass();
			   		
			   	if (hidden) {
			   		$(this)
				   		.parents("li").children("ul").toggle()
				   		.parents("li").children("a").addClass("zoneCur");
				   	} 
			   });
		});
	</script>
	
</head>

<body>

	<div id="page-wrap">

		<div id="top-bar">
			<img src="images/logo.png" alt="DZone" class="floatleft" />
			<div id="right-side">
				<img src="images/usericon.jpg" alt="user icon" />&ensp;
				<a href="#" class="first">User Name</a>&ensp;
				<a href="#">Submit New</a>&ensp;
				<a href="#">Logout</a> &emsp;
			
			</div>
		</div>
		
		<div id="zone-bar">
			<ul>
				<li>
					<a href="#"><span>
						Technology &nbsp;
						<em class="opener-technology">
							<img src="images/zonebar-downarrow.png" alt="dropdown" />
						</em>
					</span></a>
					<ul class="technologysublist">
						<li><a href="javascript:alert('1111111')">Apple</a></li>
						<li><a href="#">Design</a></li>
						<li><a href="#">Gadgets</a></li>
						<li><a href="#">Hardware</a></li>
						<li><a href="#">Industry News</a></li>
						<li><a href="#">Linux/Unix</a></li>
						<li><a href="#">Microsoft</a></li>
						<li><a href="#">Mods</a></li>
						<li><a href="#">Programming</a></li>
						<li><a href="#">Security</a></li>
						<li><a href="#">Software</a></li>
					</ul>
				</li>
				<li>
					<a href="#"><span>
						World &amp; Business &nbsp;
						<em class="opener-world">
							<img src="images/zonebar-downarrow.png" alt="dropdown" />
						</em>
					</span></a>
					<ul class="worldsublist">
						<li><a href="#">Business &amp; Finance</a></li>
						<li><a href="#">World News</a></li>
						<li><a href="#">Political News</a></li>
						<li><a href="#">Political Opinion</a></li>
						<li><a href="#">2008 Elections</a></li>
					</ul>
				</li>
				<li>
					<a href="#"><span>
						Science &nbsp;
						<em class="opener-science">
							<img src="images/zonebar-downarrow.png" alt="dropdown" />
						</em>
					</span></a>
					<ul class="sciencesublist">
						<li><a href="#">Enviornment</a></li>
						<li><a href="#">General Sciences</a></li>
						<li><a href="#">Space</a></li>
					</ul>
				</li>
				<li>
					<a href="#"><span>
						Gaming &nbsp;
						<em class="opener-gaming">
							<img src="images/zonebar-downarrow.png" alt="dropdown" />
						</em>
					</span></a>
					<ul class="gamingsublist">
						<li><a href="#">Industry News</a></li>
						<li><a href="#">PC Games</a></li>
						<li><a href="#">Playable Web Games</a></li>
						<li><a href="#">Nintendo</a></li>
						<li><a href="#">PlayStation</a></li>
						<li><a href="#">XBox</a></li>
					</ul>
				</li>
				<li>
					<a href="#"><span>
						Lifestyle &nbsp;
						<em class="opener-lifestyle">
							<img src="images/zonebar-downarrow.png" alt="dropdown" />
						</em>
					</span></a>
					<ul class="lifestylesublist">
						<li><a href="#">Arts &amp; Culture</a></li>
						<li><a href="#">Autos</a></li>
						<li><a href="#">Educational</a></li>
						<li><a href="#">Food &amp; Drink</a></li>
						<li><a href="#">Health</a></li>
						<li><a href="#">Travel &amp; Places</a></li>
					</ul>
				</li>
				<li>
					<a href="#"><span>
						Entertainment &nbsp;
						<em class="opener-entertainment">
							<img src="images/zonebar-downarrow.png" alt="dropdown" />
						</em>
					</span></a>
					<ul class="entertainmentsublist">
						<li><a href="#">Celebrity</a></li>
						<li><a href="#">Movies</a></li>
						<li><a href="#">Music</a></li>
						<li><a href="#">Television</a></li>
						<li><a href="#">Comics &amp; Animation</a></li>
					</ul>
				</li>
				<li>
					<a href="#"><span>
						Sports &nbsp;
						<em class="opener-sports">
							<img src="images/zonebar-downarrow.png" alt="dropdown" />
						</em>
					</span></a>
					<ul class="sportssublist">
						<li><a href="#">Baseball</a></li>
						<li><a href="#">Basketball</a></li>
						<li><a href="#">Extreme</a></li>
						<li><a href="#">Football</a></li>
						<li><a href="#">Golf</a></li>
						<li><a href="#">Hockey</a></li>
						<li><a href="#">Motorsport</a></li>
						<li><a href="#">Soccer</a></li>
						<li><a href="#">Tennis</a></li>
						<li><a href="#">Other Sports</a></li>
					</ul>
				</li>
				<li>
					<a href="#"><span>
						Offbeat &nbsp;
						<em class="opener-offbeat">
							<img src="images/zonebar-downarrow.png" alt="dropdown" />
						</em>
					</span></a>
					<ul class="offbeatsublist">
						<li><a href="#">Comedy</a></li>
						<li><a href="#">Odd Stuff</a></li>
						<li><a href="#">People</a></li>
						<li><a href="#">Pets &amp; Animals</a></li>
					</ul>
				</li>
			</ul>
		</div>
		
		<div id="main-content">
			<div id="feature-content">
				
				<div id="feature-left">
					<h1><a href="#">Ten Ways To Improve Your Code</a></h1>
					<p>
						Ut nulla. Vivamus bibendum, nulla ut congue fringilla, lorem ipsum ultricies risus, ut rutrum velit tortor vel purus. In hac habitasse platea dictumst. Duis fermentum, metus sed congue gravida, arcu dui ornare urna, ut imperdiet enim odio dignissim ipsum. Nulla facilisi. Cras magna ante, bibendum sit amet, porta vitae, laoreet ut, justo. Nam tortor sapien, pulvinar nec, malesuada in, ultrices in, tortor. Cras ultricies placerat eros. Quisque odio eros, feugiat non, iaculis nec, lobortis sed, arcu. Pellentesque sit amet sem et purus pretium consectetuer.Ut nulla. Vivamus bibendum, nulla ut congue fringilla, lorem ipsum ultricies risus, ut rutrum velit tortor vel purus. In hac habitasse platea dictumst. Duis fermentum, metus sed congue gravida, arcu dui ornare urna, ut imperdiet enim odio dignissim ipsum. Nulla facilisi. Cras magna ante, bibendum sit amet, porta vitae, laoreet ut, justo. Nam tortor sapien, pulvinar nec, malesuada in, ultrices in, tortor. Cras ultricies placerat eros. Quisque odio eros, feugiat non, iaculis nec, lobortis sed, arcu. Pellentesque sit amet sem et purus pretium consectetuer.
					</p>
				</div>
				
				<div id="feature-right">
					<div class="feature-mini">
						<h2><a href="#">Ten Ways To Improve Your Code</a></h2>
						<p>
							Ut nulla. Vivamus bibendum, nulla ut congue fringilla, lorem ipsum ultricies risus, ut rutrum velit tortor vel purus. In hac habitasse platea dictumst. Duis fermentum, metus sed congue gravida, arcu dui ornare urna, ut imperdiet enim odio dignissim ipsum. Nulla facilisi. Cras magna ante, bibendum sit amet, porta vitae, laoreet ut, justo. Nam tortor sapien, pulvinar nec, malesuada in, ultrices in, tortor. Cras ultricies placerat eros. Quisque odio eros, feugiat non, iaculis nec, lobortis sed, arcu. Pellentesque sit amet sem et purus pretium consectetuer.
						</p>
					</div>
					<div class="feature-mini">
						<h2><a href="#">Ten Ways To Improve Your Code</a></h2>
						<p>
							Ut nulla. Vivamus bibendum, nulla ut congue fringilla, lorem ipsum ultricies risus, ut rutrum velit tortor vel purus. In hac habitasse platea dictumst. Duis fermentum, metus sed congue gravida, arcu dui ornare urna, ut imperdiet enim odio dignissim ipsum. Nulla facilisi. Cras magna ante, bibendum sit amet, porta vitae, laoreet ut, justo. Nam tortor sapien, pulvinar nec, malesuada in, ultrices in, tortor. Cras ultricies placerat eros. Quisque odio eros, feugiat non, iaculis nec, lobortis sed, arcu. Pellentesque sit amet sem et purus pretium consectetuer.
						</p>
					</div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		
		<div id="footer">
			&copy;2008 <a href="http://css-tricks.com">CSS-Tricks</a>
		</div>
		
	</div>
	
</body>

</html>