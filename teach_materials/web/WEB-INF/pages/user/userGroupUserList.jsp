<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<div id="div" class="main-content" style="overflow-y:scroll; height: 30%;">--%>
  <%--<c:if test="${param.type == 1}">--%>
    <%--学习中心用户：--%>
    <%--<c:forEach items="${eduwestUserList}" var="user">--%>
      <%--${user.pin}&nbsp;&nbsp;&nbsp;&nbsp;--%>
    <%--</c:forEach>--%>
    <%--<br /><br />--%>
  <%--</c:if>--%>
  <%--<table  id="userGroupUserTable" class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
    <%--<tr>--%>
      <%--<th style="width: 20px;"></th>--%>
      <%--<th style="width: 150px;">用户组名称</th>--%>
      <%--<th style="width: 150px;">说明</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${!empty userGroupUserList}">--%>
      <%--<c:forEach var="userGroup" items="${userGroupUserList}" varStatus="status">--%>
        <%--<tr>--%>
          <%--<td align="center">--%>
            <%--<a class="com_btn_b" href="#" onclick="del(this, '${userGroup.id}')"><span>删&nbsp;除</span></a>--%>
          <%--</td>--%>
          <%--<td>${userGroup.name}</td>--%>
          <%--<td>${userGroup.remark}</td>--%>
        <%--</tr>--%>
      <%--</c:forEach>--%>
    <%--</c:if>--%>
  <%--</table>--%>
<%--</div>--%>
<%--<br />--%>
<%--<div id="div2" class="main-content" style="overflow-y:scroll; height: 60%;">--%>
  <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
    <%--<tr>--%>
      <%--<th style="width: 20px;"></th>--%>
      <%--<th style="width: 150px;">用户组名称</th>--%>
      <%--<th style="width: 150px;">说明</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty userGroupList}">--%>
      <%--<tr>--%>
        <%--<td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="userGroup" items="${userGroupList}">--%>
      <%--<tr>--%>
        <%--<td align="center">--%>
          <%--<a class="com_btn_b" href="#" onclick="add('${userGroup.id}', '${userGroup.name}', '${userGroup.remark}')"><span>新&nbsp;增</span></a>--%>
        <%--</td>--%>
        <%--<td>${userGroup.name}</td>--%>
        <%--<td>${userGroup.remark}</td>--%>
      <%--</tr>--%>
    <%--</c:forEach>--%>
  <%--</table>--%>
<%--</div>--%>
<%--<input type="hidden" id="userGroupIds" />--%>
<%--<a class="com_btn_b" href="#" onclick="sub()" style="margin-top: 10px;"><span>提&nbsp;交</span></a>--%>
<%--<script>--%>
  <%--$(function(){--%>
    <%--<c:if test="${!empty userGroupUserList}">--%>
      <%--var userGroupIds = "";--%>
      <%--<c:forEach var="userGroup" items="${userGroupUserList}">--%>
        <%--userGroupIds += "${userGroup.id},";--%>
      <%--</c:forEach>--%>
      <%--$("#userGroupIds").val(userGroupIds);--%>
    <%--</c:if>--%>
  <%--});--%>

  <%--function add(id, name, remark){--%>
    <%--var userGroupIds = $("#userGroupIds").val();--%>
    <%--if(0 < userGroupIds.length){--%>
      <%--for(var i=0; i<userGroupIds.split(",").length; i++){--%>
        <%--if(userGroupIds.split(",")[i] == id){--%>
          <%--alert(name+"用户组已经关联");--%>
          <%--return;--%>
        <%--}--%>
      <%--}--%>
    <%--}--%>

      <%--var table = $("#userGroupUserTable");--%>
      <%--var tr = $("<tr></tr>");--%>
      <%--var td = $("<td align='center'></td>");--%>
      <%--var td2 = $("<td></td>");--%>
      <%--var td3 = $("<td></td>");--%>
      <%--td.append("<a class=\"com_btn_b\" href=\"#\" onclick=\"del(this, "+id+")\"><span>删&nbsp;除</span></a>");--%>
      <%--td2.append(name);--%>
      <%--td3.append(remark);--%>
      <%--tr.append(td);--%>
      <%--tr.append(td2);--%>
      <%--tr.append(td3);--%>
      <%--table.append(tr);--%>
      <%--$("#userGroupIds").val(userGroupIds + id + ",");--%>

  <%--}--%>

  <%--function del(obj, id){--%>
    <%--$(obj).parent().parent().remove();--%>
    <%--var userGroupIds = $("#userGroupIds").val();--%>
    <%--$("#userGroupIds").val(userGroupIds.replace(id+",", ""));--%>
  <%--}--%>

  <%--function sub(){--%>
    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:"${pageContext.request.contextPath}/addUserGroupUser/userGroupUserAdd.htm",--%>
      <%--data:{--%>
        <%--"loginNames" : "${loginNames}",--%>
        <%--"userGroupIds" : $("#userGroupIds").val(),--%>
        <%--"type" : ${type}--%>
      <%--},--%>
      <%--async: false,--%>
      <%--success: function(data) {--%>
        <%--if(data.state == 0){--%>
          <%--alert("提交成功！");--%>
          <%--closeDialog();--%>
        <%--}else{--%>
          <%--alert(data.msg);--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--}--%>
<%--</script>--%>



<input type="hidden" id="userGroupIds" />
<input type="hidden" id="loginNames" value="${loginNames}" />
<c:if test="${param.type == 1}">
  <div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
    <div class="am-panel-hd am-cf" data-am-collapse="{target: '#spot'}">学习中心用户<span class="am-icon-chevron-down am-fr"></span></div>
    <div id="spot" class="am-in">
      <c:forEach items="${eduwestUserList}" var="user">
        ${user.pin}&nbsp;&nbsp;&nbsp;&nbsp;
      </c:forEach>
    </div>
  </div>
</c:if>

<div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
  <div class="am-panel-hd am-cf" data-am-collapse="{target: '#group'}">已关联用户组<span class="am-icon-chevron-down am-fr"></span></div>
  <div id="group" class="am-in">
    <table id="groupTable" class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
      <tr class="am-primary">
        <th style="width: 30%;">用户组名称</th>
        <th style="width: 60%;">说明</th>
        <th style="width: 10%;">操作</th>
      </tr>
      <c:if test="${!empty userGroupUserList}">
        <c:forEach var="userGroup" items="${userGroupUserList}" varStatus="status">
          <tr>
            <td>${userGroup.name}</td>
            <td>${userGroup.remark}</td>
            <td align="center">
              <button type="button" class="am-btn am-btn-primary am-round" onclick="delGroup(this, '${userGroup.id}')"><span class="am-icon-trash-o"></span> 删除</button>
            </td>
          </tr>
        </c:forEach>
      </c:if>
    </table>
  </div>
</div>
<div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
  <div class="am-panel-hd am-cf" data-am-collapse="{target: '#notGroup'}">所有用户组<span class="am-icon-chevron-down am-fr"></span></div>
  <div id="notGroup" class="am-in">
    <select id="allGroup" name="allGroup" multiple  data-am-selected="{searchBox: 1, maxHeight: 200, btnWidth: 700}">
      <option value=""></option>
      <c:forEach var="userGroup" items="${userGroupList}">
        <option value="${userGroup.id}_${userGroup.name}_${userGroup.remark}">${userGroup.name}</option>
      </c:forEach>
    </select>
    <button type="button" class="am-btn am-btn-primary am-round" onclick="addGroup()"><span class="am-icon-plus"></span> 添加</button>
  </div>
</div>


<script>
  $("select").selected();

  <c:if test="${!empty userGroupUserList}">
    var userGroupIds = "";
    <c:forEach var="userGroup" items="${userGroupUserList}">
      userGroupIds += "${userGroup.id},";
    </c:forEach>
    $("#userGroupIds").val(userGroupIds);
  </c:if>

  function addGroup(){
    var userGroupIds = $("#userGroupIds").val();
    var selectVal = $("#allGroup").val();
    if(selectVal == ""){
      app.msg("请选择要添加的用户组", 1);
      return;
    }
    for(var i=0; i<selectVal.length; i++){
      var id_name_remark = selectVal[i];
      var id = id_name_remark.split("_")[0];
      var name = id_name_remark.split("_")[1];
      var remark = id_name_remark.split("_")[2];
      if(0 > userGroupIds.indexOf(id)){
        var table = $("#groupTable");
        var tr = $("<tr></tr>");
        var td = $("<td>"+name+"</td>");
        var td2 = $("<td>"+remark+"</td>");
        var td3 = $("<td><button type=\"button\" class=\"am-btn am-btn-primary am-round\" onclick=\"delGroup(this, "+id+")\"><span class=\"am-icon-trash-o\"></span> 删除</button></td>");
        tr.append(td);
        tr.append(td2);
        tr.append(td3);
        table.append(tr);
        userGroupIds += id+",";
      }
    }
    $("#userGroupIds").val(userGroupIds);
    $("#allGroup").val("");
    $(".am-selected-status").html("点击选择...");
  }

  function delGroup(obj, id){
    $(obj).parent().parent().remove();
    var userGroupIds = $("#userGroupIds").val();
    $("#userGroupIds").val(userGroupIds.replace(id+",", ""));
  }

</script>
