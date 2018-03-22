<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p />
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findSpecCoursePage/find.htm" method="post">
  <input type="hidden" id="rows" name="rows" />
  <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
  <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
    <tr>
      <td colspan="999" style="background-color:#FFF">
        <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="downZip()"><span class="am-icon-download"></span> 下载全部</button>
      </td>
    </tr>
    <tr class="am-primary">
      <th style="width: 10%;">序号</th>
      <th style="width: 10%;">专业编号</th>
      <th style="width: 30%;">专业名称</th>
      <th style="width: 10%;">层次</th>
      <th>操作</th>
    </tr>
    <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
      <tr>
        <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
      </tr>
    </c:if>
    <c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">
      <tr>
        <td align="center">${status.index+1}</td>
        <td>${json.specCode}</td>
        <td>${json.specName}</td>
        <td>${json.levelName}</td>
        <td>
          <div class="am-btn-group">
            <button type="button" id="searchBtn" class="am-btn am-btn-primary am-round btn-loading-example"
                    data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '<span class=am-icon-th-list></span> 查看课程'}"
                    onclick="searchCourse('${json.specCode}', '${json.levelCode}', '${json.specName}', '${json.levelName}', this)"><span class="am-icon-th-list"></span> 查看课程</button>
            <button type="button" class="am-btn am-btn-primary am-round btn-loading-example"
                    onclick="down('${json.specCode}', '${json.levelCode}')"><span class="am-icon-download"></span> 下载</button>
          </div>
        </td>
      </tr>
    </c:forEach>
  </table>
  <div id="aa"></div>
  <%@ include file="/common/page2.jsp"%>
</form>
<script>
  function searchCourse(specCode, levelCode, specName, levelName, btnObj){
    $(btnObj).button('loading');
    setTimeout(function(){
      app.openOneBtnDialog('${pageContext.request.contextPath}/findSpecCourseTMBySpecAndLevel/find.htm?specCode='+specCode+'&levelCode='+levelCode, specName+"    "+levelName, 1400, 0.9);
      $(btnObj).button('reset');
    }, 100);
  }

  function down(specCode, levelCode){
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/downSpecourseTMBySpecAndLevel/down.htm?specCode="+specCode+"&levelCode="+levelCode,
      data:{},
      async: false,
      success: function(data) {
        open("${pageContext.request.contextPath}"+data);
      }
    });
  }

  function downZip(){
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/downSpecourseTMBySpecAndLevel/downZip.htm",
      data:{},
      async: false,
      success: function(data) {
        open("${pageContext.request.contextPath}/excelDown/kcdzb.zip");
      }
    });
  }
</script>