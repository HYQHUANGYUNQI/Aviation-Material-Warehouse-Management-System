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
    <!--    样式-->
    <style>
        #title{
            color: #bababa;
            margin-top: 10%;
            margin-left: 10%;
        }
        #serial-input, #materialName-input, #commodityNumber-input, #partsTypeId-input,#supplierId-input{
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
        #search-button {
            margin-left: 3%;
        }
    </style>

    <!-- End custom CSS here -->

    <!--[if lt IE 9]>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div id="wrapper">
    <div id="main-container">
    <!--		顶部导航栏-->
    <nav class="navbar-top" role="navigation">
    <!--			顶栏标语-->
    <div class="navbar-header">
    <h4 id="title">你好，普通用户</h4>
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
    <i class="fa  fa-arrows"></i> 航材备案 <span class="fa arrow"></span>
    </a>
    <ul class="collapse nav" id="application-management">
    <li>
    <a href="/AMWMS/user/airmaterial.jsp">
    <i class="fa fa-angle-double-right"></i> 备案列表
    </a>
    </li>
    <li>
    <a href="/AMWMS/user/addMaterial.html">
    <i class="fa fa-angle-double-right"></i> 备案添加
    </a>
    </li>
    <li>
    <a href="/AMWMS/user/myAirMaterial.html">
    <i class="fa fa-angle-double-right"></i> 我的备案
    </a>
    </li>
    </ul>
    </li>
    <!--					仓库申请-->
    <li class="panel">
    <a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#forms">
    <i class="fa fa-comments"></i> 仓库申请 <span class="fa arrow"></span>
    </a>
    <ul class="collapse nav" id="storage-information">
    <li>
    <a href="/AMWMS/user/searchStorage.html">
    <i class="fa fa-angle-double-right"></i> 仓库查询
    </a>
    </li>
    <li>
    <a href="/AMWMS/user/inApplication.html">
    <i class="fa fa-angle-double-right"></i> 入库申请
    </a>
    </li>
    <li>
    <a href="/AMWMS/user/outApplication.html">
    <i class="fa fa-angle-double-right"></i> 出库申请
    </a>
    </li>
    <li>
    <a href="/AMWMS/user/myApplication.html">
    <i class="fa fa-angle-double-right"></i> 我的申请
    </a>
    </li>
    </ul>
    </li>
    <!--					个人信息-->
    <li class="panel">
    <a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#forms">
    <i class="fa fa-tty"></i> 个人信息 <span class="fa arrow"></span>
    </a>
    <ul class="collapse nav" id="self-information">
    <li>
    <a href="/AMWMS/user/userinfomation.html">
    <i class="fa fa-angle-double-right"></i> 基本资料
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
                                <a href="#">航材备案</a>
                            </li>
                            <li class="active">航材列表</li>
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
            <!--			搜索框组-->
            <div class="row">
                <div class="col-lg-12">
                    <div class="page-header title">
                        <div class="input-group inline">
                            <input type="text" class="form-control" placeholder="#序列号" id="serial-input" name="serial" value="">
                            <input type="text" class="form-control" placeholder="#航材名称" id="materialName-input" name="materialName" value="">
                            <input type="text" class="form-control" placeholder="#件号" id="commodityNumber-input" name="commodityNumber" value="">
                            <input type="text" class="form-control" placeholder="#备件类型" id="partsTypeId-input" name="partsTypeId" value="">
                            <input type="text" class="form-control" placeholder="#供应商" id="supplierId-input" name="supplierId" value="">
                            <button id="search-button" class="button fa fa-search fa-2x" style="border: none;background: none"></button>
                        </div>
                    </div>
                </div>
            </div>
            <!--			表单内容-->
            <div class="row">
                <div class="col-lg-12">
                    <div class="portlet">
                        <div class="portlet-heading dark">
                            <div class="portlet-title">
                                <h4>航材信息<small class="text-white"></small></h4>
                            </div>
                            <div class="portlet-widgets">
                                <a data-toggle="collapse" data-parent="#accordion" href="#basic"><i class="fa fa-chevron-down"></i></a>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div id="basic" class="panel">
                            <div class="portlet-body no-padding">
                                <table id="airMaterial-table"></table>
                            </div>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="javax.servlet.http.Cookie"%>
	
    // for tables checkbox demo
    $(function () {
    	$.post("/AMWMS/user",
                function(res){
                    var json = JSON.parse(res);
                    var userId = json.userId;
                    var userName = json.userName;
                    document.getElementById("title").innerHTML = "你好，"+ userName;
                });
    	
        $('#airMaterial-table').bootstrapTable({
            url: '/AMWMS/view/airMaterialView',
            method: 'post',
            striped: true,
            pagination: true,
            dataType: 'json',
            pageNumber: 1,
            pageSize: 10,
            onClickRow:function(row, $element) {
                var temp = row['serial'];
                window.location.href = '/AMWMS/user/airMaterialDetail.html?serial='+temp;
            },//每页的记录行数
            columns: [{
                field: 'serial',
                title: '序列号'
            }, {
                field: 'materialName',
                title: '航材名称'
            }, {
                field: 'commodityNumber',
                title: '件号'
            }, {
                field: 'partsTypeId',
                title: '备件类型'
            }, {
                field: 'supplierId',
                title: '供应商'
            }, {
                field: 'purchaseDate',
                title: '采购日期'
            }]
        })
        $('#search-button').click(function () {
            var temp1 = document.getElementById("serial-input").value;
            var temp2 = document.getElementById("materialName-input").value;//"五角螺丝" //
            var temp3 = document.getElementById("commodityNumber-input").value;//"NAS15803R3"//
            var temp4 = document.getElementById("partsTypeId-input").value;//1//
            var temp5 = document.getElementById("supplierId-input").value;//"1002001SCR"//
            $.ajax({

                type:"POST",
                url:"/AMWMS/search/searchAirMaterial?serial=" + temp1 + "&&materialName=" + temp2 + "&&commodityNumber=" + temp3 + "&&partsTypeId=" + temp4 + "&&supplierId=" + temp5,
                success:function (json) {
                    var param={
                        url:"/AMWMS/search/searchAirMaterial?serial=" + temp1 + "&&materialName=" + temp2 + "&&commodityNumber=" + temp3 + "&&partsTypeId=" + temp4 + "&&supplierId=" + temp5,
                    }
                    $('#airMaterial-table').bootstrapTable('refresh', param);
                }
            })
        })
    })

</script>
</body>
</html>