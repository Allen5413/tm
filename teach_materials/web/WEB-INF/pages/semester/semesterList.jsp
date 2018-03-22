<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<p />
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findSemesterPage/findSemesterPageByWhere.htm" method="post">
</form>
<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr class="am-primary">
    <th style="width: 10%;">序号</th>
    <th style="width: 10%;">年份</th>
    <th style="width: 10%;">季度</th>
    <th style="width: 10%;">是否当前学期</th>
    <th style="width: 20%;">操作人</th>
    <th style="width: 20%;">操作时间</th>
    <th style="width: 20%;">操作</th>
  </tr>
  <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
    <tr>
      <td colspan="7" align="center" style="color: red;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="semester" items="${pageInfo.pageResults}" varStatus="status">
    <tr>
      <td align="center">${status.index+1}</td>
      <td>${semester.year}</td>
      <td>
        <c:if test="${semester.quarter == 1}">秋季</c:if>
        <c:if test="${semester.quarter == 0}">春季</c:if>
      </td>
      <td>
        <c:if test="${semester.isNowSemester == 1}">否</c:if>
        <c:if test="${semester.isNowSemester == 0}">是</c:if>
      </td>
      <td>${semester.operator}</td>
      <td><fmt:formatDate value="${semester.operateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
      <td>
        <div class="am-btn-group">
          <c:if test="${semester.isNowSemester == 1}">
            <button type="button" class="am-btn am-btn-primary am-round btn-loading-example"
                    data-am-loading="{spinner: 'circle-o-notch', loadingText: '执行中...', resetText: '<span class='am-icon-cog'></span> 设置为当前学期'}"
                    onClick="setCurrentSemester(${semester.id}, this)"><span class="am-icon-cog"></span> 设置为当前学期</button>

          </c:if>

            <button type="button" class="am-btn am-btn-primary am-round btn-loading-example"
                    data-am-loading="{spinner: 'circle-o-notch', loadingText: '执行中...', resetText: '<span class='am-icon-cog'></span> 设置课程教材对照表'}"
                    onClick="setCourseTM(${semester.id}, this)"><span class="am-icon-cog"></span> 设置课程教材对照表</button>

        </div>
      </td>
    </tr>
  </c:forEach>
</table>
<script>
  function setCurrentSemester(id, btnObj){
    app.confirm("您确定要设置为当前学期？", function(){
      $(btnObj).button('loading');
      $.ajax({
        url:"${pageContext.request.contextPath}/setNowSemester/setSemester.htm",
        method : 'POST',
        async:false,
        data:{"id":id},
        success:function(data){
          if(data.state == 0){
            app.msg("设置成功！", 0);
            app.searchFormPage(null, "${pageContext.request.contextPath}/findSemesterPage/findSemesterPageByWhere.htm", null);
          }else {
            app.msg(data.msg, 1);
            $(btnObj).button('reset');
          }
        }
      });
    });
  }

  function setCourseTM(id, btnObj){
    $(btnObj).button('loading');
    setTimeout(function(){
      $.ajax({
        url:"${pageContext.request.contextPath}/addCourseTeachMaterial/add.htm",
        method : 'POST',
        async:false,
        data:{"semesterId":id},
        success:function(data){
          if(data.state == 0){
            app.msg("设置成功！", 0);
            app.searchFormPage(null, "${pageContext.request.contextPath}/findSemesterPage/findSemesterPageByWhere.htm", null);
          }else {
            app.msg(data.msg, 1);
            $(btnObj).button('reset');
          }
        }
      });
    }, 100);
  }
</script>