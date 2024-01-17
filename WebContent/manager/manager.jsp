<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="javax.servlet.http.Cookie"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>航材仓库管理系统</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">

	<!-- Bootstrap core CSS -->

	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/fonts.css">
	<link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">

	<!-- PAGE LEVEL PLUGINS STYLES -->
	<link rel="stylesheet" href="assets/css/plugins/footable/footable.min.css">
	<!-- REQUIRE FOR SPEECH COMMANDS -->
	<link rel="stylesheet" type="text/css" href="assets/css/plugins/gritter/jquery.gritter.css" />

	<!-- Tc core CSS -->
	<link id="qstyle" rel="stylesheet" href="assets/css/themes/style.css">
	<link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.15.4/dist/bootstrap-table.min.css">

	<!-- Add custom CSS here -->
<!--	样式-->
	<style>
		#title{
			color: #bababa;
			margin-top: 10%;
			margin-left: 10%;
		}
		#formId-input, #application-input, #replenishDate-input, #storage-input, #state-input{
			width: 15%;
			margin-left: 3%;
		}
		#search-button {
			margin-left: 3%;
		}

         #title{
             color: #bababa;
             margin-top: 10%;
             margin-left: 10%;
         }
        #formId-input, #application-input, #replenishDate-input, #storage-input, #state-input{
            width: 15%;
            margin-left: 3%;
        }
        #search-button {
            margin-left: 3%;
        }

	</style>

	<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->

</head>

<body>

<!--布局-->
<div id="wrapper">
	<div id="main-container">
<!--		顶部导航栏-->
		<nav class="navbar-top" role="navigation">
<!--			顶栏标语-->
			<div class="navbar-header">
				<h4 id="title">你好，管理员</h4>
			</div>
<!--			顶栏图标-->
			<div class="nav-top">
				<ul class="nav navbar-right">
					<li>
						<a href="#" class="fa fa-bell-o"></a>
					</li>
					<li>
						<a href="#" class="fa fa-sign-out"></a>
					</li>
				</ul>
			</div>
		</nav>
<!--		侧边导航栏-->
		<nav class="navbar-side" role="navigation">
			<div class="navbar-collapse sidebar-collapse collapse">
				<ul id="side" class="nav navbar-nav side-nav">
					<li>
						<h4>导航</h4>
					</li>
<!--					申请管理-->
					<li class="panel">
						<a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#components">
							<i class="fa fa-cogs"></i> 申请管理 <span class="fa arrow"></span>
						</a>
						<ul class="collapse nav" id="application-management">
							<li>
								<a href="/AMWMS/manager/inApplicationManager.html">
									<i class="fa fa-angle-double-right"></i> 入库申请
								</a>
							</li>
							<li>
								<a href="/AMWMS/manager/outApplicationManager.html">
									<i class="fa fa-angle-double-right"></i> 出库申请
								</a>
							</li>
							<li>
								<a href="/AMWMS/manager/transferApplication.html">
									<i class="fa fa-angle-double-right"></i> 移库申请
								</a>
							</li>
						</ul>
					</li>
<!--					仓库信息-->
					<li class="panel">
						<a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#forms">
							<i class="fa fa-edit"></i> 仓库信息 <span class="fa arrow"></span>
						</a>
						<ul class="collapse nav" id="storage-information">
							<li>
								<a href="/AMWMS/manager/storageList.html">
									<i class="fa fa-angle-double-right"></i> 货位列表
								</a>
							</li>
							<li>
								<a href="/AMWMS/manager/airMaterialList.html">
									<i class="fa fa-angle-double-right"></i> 航材列表
								</a>
							</li>
						</ul>
					</li>
<!--					个人信息-->
					<li class="panel">
						<a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#forms">
							<i class="fa fa-edit"></i> 个人信息 <span class="fa arrow"></span>
						</a>
						<ul class="collapse nav" id="self-information">
							<li>
								<a href="/AMWMS/manager/managerInfomation.html">
									<i class="fa fa-angle-double-right"></i> 基本资料
								</a>
							</li>
						</ul>
					</li>
<!--					数据中心-->
					<li class="panel">
						<a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#charts">
							<i class="fa fa-bar-chart-o"></i> 数据中心 <span class="fa arrow"></span>
						</a>
						<ul class="collapse nav" id="data-protect">
							<li>
								<a href="#">
									<i class="fa fa-angle-double-right"></i> 数据恢复
								</a>
							</li>
							<li>
								<a href="#">
									<i class="fa fa-angle-double-right"></i> 用户日志
								</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</nav>
		<!--内容区域-->
		<div id="page-wrapper">
<!--			顶部文字+图标区域-->
        	<div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumbs">
                        <ul class="breadcrumb">
                            <li>
                                <a href="#">仓库管理</a>
                            </li>
                            <li class="active">入库申请</li>
                        </ul>
						<div class="b-right hidden-xs">
							<ul>
								<li><a href="#" title=""><i class="fa fa-signal"></i></a></li>
								<li><a href="#" title=""><i class="fa fa-comments"></i></a></li>
								<li class="dropdown"><a href="#" title="" data-toggle="dropdown"><i class="fa fa-plus"></i></a>
								</li>
							</ul>
						</div>
					</div>
                </div>
            </div>

	
		</div>
	</div>
</div>

<!-- core JavaScript -->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="assets/js/plugins/pace/pace.min.js"></script>

<!-- PAGE LEVEL PLUGINS JS -->
<script src="assets/js/plugins/footable/footable.min.js"></script>

<script src="assets/js/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="assets/js/plugins/datatables/datatables.js"></script>
<script src="assets/js/plugins/datatables/datatables.responsive.js"></script>

<!-- Themes Core Scripts -->
<script src="assets/js/main.js"></script>

<!-- REQUIRE FOR SPEECH COMMANDS -->
<script src="assets/js/speech-commands.js"></script>
<script src="assets/js/plugins/gritter/jquery.gritter.min.js"></script>

<!-- initial page level scripts for examples -->
<script src="assets/js/plugins/slimscroll/jquery.slimscroll.init.js"></script>
<script src="assets/js/plugins/footable/footable.init.js"></script>
<script src="assets/js/plugins/datatables/datatables.init.js"></script>
<script src="https://unpkg.com/bootstrap-table@1.15.4/dist/bootstrap-table.min.js"></script>

<script>
	// for tables checkbox demo
<%
     String userId = request.getParameter("username");
     session.setAttribute("userId", userId);
%>
		$(function () {
			// 获取航材列表
			window.location.href="/AMWMS/manager/inApplicationManager.html"
		})

</script>
</body>
</html>