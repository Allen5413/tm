<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="searchStudent" class="main-content">
  <form id="pageForm2" name="pageForm2" action="${pageContext.request.contextPath}/copyOnceOrderTM/find.htm" method="post">
    <input type="hidden" id="copy_spotCode" name="copy_spotCode" value="${spotCode}"/>
    <table width="90%">
      <tr height="40">
        <td align="right"><label >学号：</label></td>
        <td><input type="text" id="copy_code" name="copy_code" value="${param.copy_code}" /></td>
        <td align="right"><label >姓名：</label></td>
        <td><input type="text" id="copy_name" name="copy_name" value="${param.copy_name}" /></td>
      </tr>
      <tr>
        <td align="right"><label >入学年：</label></td>
        <td><input type="text" id="copy_year" name="copy_year" value="${param.copy_year}" /></td>
        <td align="right"><label >入学季：</label></td>
        <td>
          <select id="copy_quarter" name="copy_quarter">
            <option value="">请选择</option>
            <option value="0" <c:if test="${param.copy_quarter eq '0'}">selected="selected" </c:if> >春季</option>
            <option value="1" <c:if test="${param.copy_quarter eq '1'}">selected="selected" </c:if> >秋季</option>
          </select>
        </td>
      </tr>
      <tr>
        <td align="right"><label >专业：</label></td>
        <td>
          <select id="copy_specCode" name="copy_specCode">
            <option value="">请选择</option>
            <c:forEach var="spec" items="${specList}">
              <option value="${spec.code}" <c:if test="${param.copy_specCode eq spec.code}">selected="selected" </c:if>>[${spec.code}]${spec.name}</option>
            </c:forEach>
          </select>
        </td>
        <td align="right"><label >层次：</label></td>
        <td>
          <select id="copy_levelCode" name="copy_levelCode">
            <option value="">请选择</option>
            <c:forEach var="level" items="${levelList}">
              <option value="${level.code}" <c:if test="${param.levelCode eq level.code}">selected="selected" </c:if>>${level.name}</option>
            </c:forEach>
          </select>
        </td>
      </tr>
      <tr>
        <td align="right">
          <button type="button" id="searchBtn2" class="am-btn am-btn-primary btn-loading-example"
                  data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
                  onclick="subSearch()"><span class="am-icon-search"></span> 查询</button>
        </Td>
      </tr>
    </table>
  </form>
  <p /><p />
  <form id="editForm" name="editForm" action="${pageContext.request.contextPath}/copyOnceOrderTM/copy.htm" method="post">
    <input type="hidden" id="id" name="id" value="${id}"/>
    <input type="hidden" id="ids" name="ids"/>
    <table width="100%">
      <tr>
        <td width="40%" align="right">
          <select id="student" name="student" multiple size="18" style="width: 80%"></select>
        </td>
        <td width="20%" align="center">
          <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="toRight()"> >></button>
          <br><br><br><br>
          <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="toLeft()"> <<</button>
        </td>
        <td width="40%" align="left">
          <select id="changeStudent" name="changeStudent" multiple size="18" style="width: 80%"></select>
        </td>
      </tr>
    </table>
  </form>
</div>
<script>
  function subSearch(){
    //清除之前查询的数据
    $("#student").find("option").remove();

    //重新查询数据
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/copyOnceOrderTM/find.htm",
      data: $("#pageForm2").serialize(),
      async: false,
      success: function(data) {
        if(data.state == 0){
          if(0 < data.list.length){
            for(var i=0; i<data.list.length; i++){
              var stu = data.list[i];
              var id = stu.id;
              var code = stu.code;
              var name = stu.name;
              $("<option value="+id+">["+code+"]&nbsp;&nbsp;&nbsp;&nbsp;"+name+"</option>").appendTo("#student");
            }
          }else{
            $("<option value=''>没有未确认的学生</option>").appendTo("#student");
          }
        }else{
          alert(data.msg);
        }
      }
    });
  }

  function toRight(){
    $("#student").find("option").each(function(){
      if($(this).is(":selected")){
        $("<option value="+$(this).val()+">"+$(this).text()+"</option>").appendTo("#changeStudent");
        $(this).remove();
      }
    });
  }

  function toLeft(){
    $("#changeStudent").find("option").each(function(){
      if($(this).is(":selected")){
        $("<option value="+$(this).val()+">"+$(this).text()+"</option>").appendTo("#student");
        $(this).remove();
      }
    });
  }
</script>
