<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<div id="div" class="main-content" style="overflow-y:scroll; height: 200px;">--%>
  <%--<table  id="tmCourseTable" class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
    <%--<tr>--%>
      <%--<th style="width: 20px;"></th>--%>
      <%--<th style="width: 80px;">课程编号</th>--%>
      <%--<th style="width: 150px;">课程名称</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${!empty tmCourseList}">--%>
      <%--<c:forEach var="course" items="${tmCourseList}" varStatus="status">--%>
        <%--<tr>--%>
          <%--<td align="center">--%>
            <%--<a class="com_btn_b" href="#" onclick="del(this, '${course.code}')"><span>删&nbsp;除</span></a>--%>
          <%--</td>--%>
          <%--<td>${course.code}</td>--%>
          <%--<td>${course.name}</td>--%>
        <%--</tr>--%>
      <%--</c:forEach>--%>
    <%--</c:if>--%>
  <%--</table>--%>
<%--</div>--%>
<%--<ul class="create_info_list">--%>
  <%--<li>--%>
    <%--<label class="lab_80">课程编号：</label>--%>
    <%--<input type="text" id="courseCode" class="input_240" style="width: 200px;" />--%>
    <%--<a class="com_btn_b" href="#" onclick="searchCourse();"><span>查&nbsp;询</span></a>--%>
  <%--</li>--%>
<%--</ul>--%>
<%--<div id="div2" class="main-content" style="overflow-y:scroll; height: 350px;">--%>
  <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
    <%--<tr>--%>
      <%--<th style="width: 20px;"></th>--%>
      <%--<th style="width: 80px;">课程编号</th>--%>
      <%--<th style="width: 150px;">课程名称</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty courseList}">--%>
      <%--<tr>--%>
        <%--<td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="course" items="${courseList}">--%>
      <%--<tr>--%>
        <%--<td align="center">--%>
          <%--<a class="com_btn_b" href="#" onclick="add('${course.code}', '${course.name}');"><span>新&nbsp;增</span></a>--%>
        <%--</td>--%>
        <%--<td><a id="${course.code}" name="${course.code}">${course.code}</a></td>--%>
        <%--<td>${course.name}</td>--%>
      <%--</tr>--%>
    <%--</c:forEach>--%>
  <%--</table>--%>
<%--</div>--%>
<%--<br /><br />--%>
<%--<input type="hidden" id="courseCodes" />--%>
<%--<a class="com_btn_b" id="sub_s" href="#" onclick="sub()"><span>提&nbsp;交</span></a>--%>
<%--<a class="com_btn_b_disable" id="sub_h" style="display: none"><span>提&nbsp;交</span></a>--%>
<%--<script>--%>
  <%--$(function(){--%>
    <%--<c:if test="${!empty tmCourseList}">--%>
      <%--var courseCode = "";--%>
      <%--<c:forEach var="course" items="${tmCourseList}" varStatus="status">--%>
        <%--courseCode += "${course.code},";--%>
      <%--</c:forEach>--%>
      <%--$("#courseCodes").val(courseCode);--%>
    <%--</c:if>--%>

    <%--var height = getWindowHeightSize();--%>
    <%--$("#div").height(height * 0.1);--%>
    <%--$("#div2").height(height * 0.42);--%>
  <%--});--%>
  <%--function add(code, name){--%>
    <%--var courseCodes = $("#courseCodes").val();--%>
    <%--if(-1 < courseCodes.indexOf(code)){--%>
      <%--alert(code+"课程已经关联");--%>
    <%--}else {--%>
      <%--var table = $("#tmCourseTable");--%>
      <%--var tr = $("<tr></tr>");--%>
      <%--var td = $("<td align='center'></td>");--%>
      <%--var td2 = $("<td></td>");--%>
      <%--var td3 = $("<td></td>");--%>
      <%--td.append("<a class=\"com_btn_b\" href=\"#\" onclick=\"del(this, '"+code+"')\"><span>删&nbsp;除</span></a>");--%>
      <%--td2.append(code);--%>
      <%--td3.append(name);--%>
      <%--tr.append(td);--%>
      <%--tr.append(td2);--%>
      <%--tr.append(td3);--%>
      <%--table.append(tr);--%>
      <%--$("#courseCodes").val(courseCodes + code + ",");--%>
    <%--}--%>
  <%--}--%>

  <%--function del(obj, code){--%>
    <%--$(obj).parent().parent().remove();--%>
    <%--var courseCodes = $("#courseCodes").val();--%>
    <%--$("#courseCodes").val(courseCodes.replace(code+",", ""));--%>
  <%--}--%>

  <%--function sub(){--%>
    <%--$("#sub_s").hide();--%>
    <%--$("#sub_h").show();--%>
    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:"${pageContext.request.contextPath}/addTMCourse/tmCourseAdd.htm",--%>
      <%--data:{--%>
        <%--"tmId":${param.tmId},--%>
        <%--"courseCodes": $("#courseCodes").val()--%>
      <%--},--%>
      <%--async: false,--%>
      <%--success: function(data) {--%>
        <%--if(data.state == 0){--%>
          <%--alert("提交成功！");--%>
          <%--closeDialog();--%>
        <%--}else{--%>
          <%--alert(data.msg);--%>
          <%--$("#sub_s").show();--%>
          <%--$("#sub_h").hide();--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>

  <%--function searchCourse(){--%>
    <%--location.href = '#'+$("#courseCode").val();--%>
  <%--}--%>

<%--</script>--%>

<input type="hidden" id="courseCodes" />
<div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
  <div class="am-panel-hd am-cf" data-am-collapse="{target: '#course'}">已关联课程<span class="am-icon-chevron-down am-fr"></span></div>
  <div id="course" class="am-in">
    <table id="courseTable" class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
      <tr class="am-primary">
        <th style="width: 30%;">课程编号</th>
        <th style="width: 60%;">课程名称</th>
        <th style="width: 10%;">操作</th>
      </tr>
      <c:if test="${!empty tmCourseList}">
        <c:forEach var="course" items="${tmCourseList}" varStatus="status">
          <tr>
            <td>${course.code}</td>
            <td>${course.name}</td>
            <td align="center">
              <button type="button" class="am-btn am-btn-primary am-round" onclick="delCourse(this, '${course.code}')"><span class="am-icon-trash-o"></span> 删除</button>
            </td>
          </tr>
        </c:forEach>
      </c:if>
    </table>
  </div>
</div>

<div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
  <div class="am-panel-hd am-cf" data-am-collapse="{target: '#notCourse'}">所有课程<span class="am-icon-chevron-down am-fr"></span></div>
  <div id="notCourse" class="am-in">
    <select id="tm_courseCode" name="courseCode" multiple  data-am-selected="{searchBox: 1, maxHeight: 200, btnWidth: 700}">
      <option value=""></option>
      <c:forEach var="course" items="${courseList}">
        <option value="${course.code}_${course.name}">[${course.code}]${course.name}</option>
      </c:forEach>
    </select>
    <button type="button" class="am-btn am-btn-primary am-round" onclick="addCourse()"><span class="am-icon-plus"></span> 添加</button>
  </div>
</div>


<script>
  $("select").selected();

  <c:if test="${!empty tmCourseList}">
    var courseCode = "";
    <c:forEach var="course" items="${tmCourseList}" varStatus="status">
      courseCode += "${course.code},";
    </c:forEach>
    $("#courseCodes").val(courseCode);
  </c:if>

  function addCourse(){
    var courseCodes = $("#courseCodes").val();
    var selectVal = $("#tm_courseCode").val();
    if(selectVal == ""){
      app.msg("请选择要添加的课程", 1);
      return;
    }
    for(var i=0; i<selectVal.length; i++){
      var code_name = selectVal[i];
      var code = code_name.split("_")[0];
      var name = code_name.split("_")[1];
      if(0 > courseCodes.indexOf(code)){
        var table = $("#courseTable");
        var tr = $("<tr></tr>");
        var td = $("<td>"+code+"</td>");
        var td2 = $("<td>"+name+"</td>");
        var td3 = $("<td><button type=\"button\" class=\"am-btn am-btn-primary am-round\" onclick=\"delCourse(this, "+code+")\"><span class=\"am-icon-trash-o\"></span> 删除</button></td>");
        tr.append(td);
        tr.append(td2);
        tr.append(td3);
        table.append(tr);
        courseCodes += code+",";
      }
    }
    $("#courseCodes").val(courseCodes);
    $("#tm_courseCode").val("");
    $(".am-selected-status").html("点击选择...");
  }

  function delCourse(obj, code){
    $(obj).parent().parent().remove();
    var courseCodes = $("#courseCodes").val();
    $("#courseCodes").val(courseCodes.replace(code+",", ""));
  }

</script>
