<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<p />
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findTeachMaterialPage/findTeachMaterialPageByWhere.htm" method="post">
  <input type="hidden" name="method" value="search"/>
  <input type="hidden" id="rows" name="rows" />
  <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
  <table width="90%">
    <tr height="40">
      <td align="right"><label >ISBN：</label></td>
      <td>
        <input type="text" id="isbn" name="isbn" value="${param.isbn}" />
      </td>
      <td align="right"><label >教材名称：</label></td>
      <td>
        <input type="text" id="name" name="name" value="${param.name}" />
      </td>
      <td align="right"><label >课程编号：</label></td>
      <td>
        <input type="text" id="courseCode" name="courseCode" value="${param.courseCode}" />
      </td>
    </tr>
    <tr height="40">
      <td align="right"><label >出版社：</label></td>
      <td>
        <select class="select" name="pressId" id="pressId" data-am-selected="{maxHeight: 500, searchBox: 1}" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <c:forEach var="press" items="${pressList}">
            <option value="${press.id}" <c:if test="${param.pressId == press.id}">selected="selected"</c:if>>${press.name}</option>
          </c:forEach>
      </select>
      </td>
      <td align="right"><label >教材类型：</label></td>
      <td>
        <select class="select" name="typeId" id="typeId" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <c:forEach var="tmType" items="${teachMaterialTypeList}">
            <option value="${tmType.id}" <c:if test="${param .typeId == tmType.id}">selected="selected"</c:if>>${tmType.name}</option>
          </c:forEach>
        </select>
      </td>
      <td align="right"><label >状态：</label></td>
      <td>
        <select class="select" name="state" id="state" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <option value="0" <c:if test="${param .state eq '0'}">selected="selected"</c:if>>启用</option>
          <option value="1" <c:if test="${param .state eq '1'}">selected="selected"</c:if>>停用</option>
        </select>
      </td>
    </tr>
    <tr height="40">
      <td align="right"><label >是否关联课程：</label></td>
      <td>
        <select class="select" name="isGlCourse" id="isGlCourse" data-am-selected="{maxHeight: 500, searchBox: 1}" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <option value="0" <c:if test="${param.isGlCourse eq '0'}">selected="selected"</c:if>>否</option>
          <option value="1" <c:if test="${param.isGlCourse eq '1'}">selected="selected"</c:if>>是</option>
        </select>
      </td>
      <td align="right"><label ></label></td>
      <td>
      </td>
      <td align="right"><label ></label></td>
      <td>
      </td>
    </tr>
    <tr>
      <td colspan="99" style="padding-left:20px;">
        <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
                data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
                onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
        <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="down()"><span class="am-icon-download"></span> 下载</button>
      </td>
    </tr>
  </table>
</form>
<p /><p />
<c:if test="${'search' eq param.method}">
  <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
    <tr>
      <td colspan="999" style="background-color:#FFF">
        <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="add()"><span class="am-icon-plus"></span> 新增</button>
        <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="downExcel()"><span class="am-icon-upload"></span> 上传批量库存</button>
      </td>
    </tr>
    <tr class="am-primary">
      <th style="width: 4%;">序号</th>
      <th style="width: 8%;">ISBN</th>
      <th style="width: 20%;">教材名称</th>
      <th style="width: 6%;">教材类型</th>
      <th style="width: 15%;">出版社</th>
      <th style="width: 8%;">作者</th>
      <th style="width: 4%;">版次</th>
      <th style="width: 5%;">单价</th>
      <th style="width: 5%;">状态</th>
      <th style="width: 5%;">是否套教材</th>
      <th style="width: 5%;">操作人</th>
      <th style="width: 10%;">操作时间</th>
      <th>操作</th>
    </tr>
    <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
      <tr>
        <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
      </tr>
    </c:if>
    <c:forEach var="tm" items="${pageInfo.pageResults}" varStatus="status">
      <tr>
      <td align="center">${status.index+1}</td>
      <td>${tm.isbn}</td>
      <td>${tm.name}</td>
      <td>${tm.tmTypeName}</td>
      <td>${tm.pressName}</td>
      <td>${tm.author}</td>
      <td>${tm.revision}</td>
      <td>${tm.price}</td>
      <td>
        <c:choose>
          <c:when test="${tm.state == 0}">启用</c:when>
          <c:when test="${tm.state == 1}">停用</c:when>
          <c:otherwise>未知</c:otherwise>
        </c:choose>
      </td>
      <td>
        <c:choose>
          <c:when test="${tm.isSet == 0}">否</c:when>
          <c:when test="${tm.isSet == 1}">是</c:when>
          <c:otherwise>未知</c:otherwise>
        </c:choose>
      </td>
      <td>${tm.operator}</td>
      <td>${tm.operateTime}</td>
      <td>
        <div class="doc-dropdown-justify-js">
          <div class="am-dropdown am-dropdown-up" data-am-dropdown="" id="operateBtn_${status.index+1}">
            <button class="am-btn am-btn-default am-btn-sm am-dropdown-toggle" data-am-dropdown-toggle=""><span class="am-icon-cog"></span> <span class="am-icon-caret-up"></span></button>
            <ul class="am-dropdown-content">
              <li><a href="#" onClick="edit(${tm.id})"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-pencil-square-o"></span> 修改</a></li>
              <li><a href="#" onClick="searchStock(${tm.id}, '${tm.name}')"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-inbox"></span> 库存</a></li>
              <li><a href="#" onClick="revision(${tm.id})"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-fire"></span> 版次管理</a></li>
              <li><a href="#" onClick="tmCourse(${tm.id})"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-gear"></span> 关联课程</a></li>
              <li><a href="#" onClick="searchStockLog(${tm.id})"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-th-list"></span> 库存调拨记录</a></li>
            </ul>
          </div>
        </div>
      </td>
      </tr>
    </c:forEach>
  </table>
  <%@ include file="/common/page2.jsp"%>
</c:if>
<script>
  $("select").selected();
  $(function() {
    for(var i=1; i<=${fn:length(pageInfo.pageResults)}; i++) {
      $('#operateBtn_'+i).dropdown({justify: '.doc-dropdown-justify-js'});
    }
  });

  function add(){
    var url = '${pageContext.request.contextPath}/addTeachMaterial/openAddTeachMaterial.htm';
    app.openDialog(url, '新增教材', 800, 0.9, function(index){
      var pressId = $("#add_pressId").val().trim();
      var name = $("#add_name").val().trim();
      var tmTypeId = $("#add_tmTypeId").val().trim();
      if(pressId == ""){
        app.msg("请选择出版社", 1);
        return;
      }
      if(name == ""){
        app.msg("请输入教材名称", 1);
        return;
      }
      if(tmTypeId == ""){
        app.msg("请选择教材类别", 1);
        return;
      }
      app.add("${pageContext.request.contextPath}/addTeachMaterial/teachMaterialAdd.htm", $('#addForm').serialize(), index);
    });
  }

  function edit(id){
    var url = '${pageContext.request.contextPath}/editTeachMaterial/openEditTeachMaterialPage.htm?id='+id;
    app.openDialog(url, '编辑教材', 800, 0.9, function(index){
      var pressId = $("#edit_pressId").val().trim();
      var name = $("#edit_name").val().trim();
      var tmTypeId = $("#edit_tmTypeId").val().trim();
      if(pressId == ""){
        app.msg("请选择出版社", 1);
        return;
      }
      if(name == ""){
        app.msg("请输入教材名称", 1);
        return;
      }
      if(tmTypeId == ""){
        app.msg("请选择教材类别", 1);
        return;
      }
      app.edit("${pageContext.request.contextPath}/editTeachMaterial/teachMaterialEdit.htm", $('#editForm').serialize(), index);
    });
  }

  function searchStock(id, tmName){
    app.openOneBtnDialog('${pageContext.request.contextPath}/findtmStockBytmId/doFindtmStockBytmId.htm?tmId='+id, '查看 '+tmName+' 库存', 600, 0.6);
  }

  function revision(id){
    app.openOneBtnDialog('${pageContext.request.contextPath}/findTeachMaterialRevisionBytmId/doFindTeachMaterialRevisionBytmId.htm?tmId='+id, '版次管理', 1200, 0.8);
  }

  function searchStockLog(id){
    app.openOneBtnDialog('${pageContext.request.contextPath}/findStockAllotLogListBytmId/doFindStockAllotLogListBytmId.htm?tmId='+id, '库存调拨日志记录', 1000, 0.9);
  }

  function tmCourse(id){
    var url = '${pageContext.request.contextPath}/findCourseBytmId/doFindCourseBytmId.htm?tmId='+id;
    app.openDialog(url, '关联课程', 800, 0.7, function(index){
      app.add("${pageContext.request.contextPath}/addTMCourse/tmCourseAdd.htm", {"tmId":id, "courseCodes": $("#courseCodes").val()}, index);
    });
  }

  function down(){
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/downTeachMaterial/down.htm",
      data:$("#pageForm").serialize(),
      async: false,
      success: function(data) {
        open("${pageContext.request.contextPath}"+data);
      }
    });
  }
</script>

