<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<div id="div" class="main-content" style="overflow-y:scroll; height: 200px;">--%>
  <%--<table  id="stmTMTable" class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
    <%--<tr>--%>
      <%--<th style="width: 20px;"></th>--%>
      <%--<th style="width: 80px;">ISBN</th>--%>
      <%--<th style="width: 150px;">教材名称</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${!empty stmTMList}">--%>
      <%--<c:forEach var="tm" items="${stmTMList}" varStatus="status">--%>
        <%--<tr>--%>
          <%--<td align="center">--%>
              <%--<a class="com_btn_b" href="#" onclick="del(this, '${tm.id}')"><span>删&nbsp;除</span></a>--%>
          <%--</td>--%>
          <%--<td>${tm.isbn}</td>--%>
          <%--<td>${tm.name}</td>--%>
        <%--</tr>--%>
      <%--</c:forEach>--%>
    <%--</c:if>--%>
  <%--</table>--%>
<%--</div>--%>
<%--<br />--%>
<%--<li>--%>
  <%--<label class="lab_80">ISBN：</label>--%>
  <%--<input type="text" id="isbn" class="input_240" style="width: 200px;" />--%>
  <%--<a class="com_btn_b" href="#" onclick="searchTM();"><span>查&nbsp;询</span></a>--%>
<%--</li>--%>
<%--<br />--%>
<%--<div id="div2" class="main-content" style="overflow-y:scroll; height: 350px;">--%>
  <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
    <%--<tr>--%>
      <%--<th style="width: 20px;"></th>--%>
      <%--<th style="width: 80px;">ISBN</th>--%>
      <%--<th style="width: 150px;">教材名称</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty tmList}">--%>
      <%--<tr>--%>
        <%--<td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="tm" items="${tmList}">--%>
      <%--<tr>--%>
        <%--<td align="center">--%>
          <%--<a class="com_btn_b" href="#" onclick="add('${tm.id}', '${tm.isbn}', '${tm.name}')"><span>新&nbsp;增</span></a>--%>
        <%--</td>--%>
        <%--<td><a id="${tm.isbn}" name="${tm.isbn}">${tm.isbn}</a></td>--%>
        <%--<td>${tm.name}</td>--%>
      <%--</tr>--%>
    <%--</c:forEach>--%>
  <%--</table>--%>
<%--</div>--%>
<%--<br />--%>
<%--<input type="hidden" id="tmIds" />--%>
<%--<a class="com_btn_b" href="#" onclick="sub()"><span>提&nbsp;交</span></a>--%>
<%--<script>--%>
  <%--$(function(){--%>
    <%--<c:if test="${!empty stmTMList}">--%>
    <%--var tmIds = "";--%>
    <%--<c:forEach var="tm" items="${stmTMList}">--%>
      <%--tmIds += "${tm.id},";--%>
    <%--</c:forEach>--%>
    <%--$("#tmIds").val(tmIds);--%>
    <%--</c:if>--%>

    <%--var height = getWindowHeightSize();--%>
    <%--$("#div").height(height * 0.2);--%>
    <%--$("#div2").height(height * 0.35);--%>
  <%--});--%>

  <%--function add(id, isbn, name){--%>
    <%--var tmIds = $("#tmIds").val();--%>
    <%--if(-1 < tmIds.indexOf(id)){--%>
      <%--alert(isbn+"教材已经关联");--%>
    <%--}else {--%>
      <%--var table = $("#stmTMTable");--%>
      <%--var tr = $("<tr></tr>");--%>
      <%--var td = $("<td align='center'></td>");--%>
      <%--var td2 = $("<td></td>");--%>
      <%--var td3 = $("<td></td>");--%>
      <%--td.append("<a class=\"com_btn_b\" href=\"#\" onclick=\"del(this, "+id+")\"><span>删&nbsp;除</span></a>");--%>
      <%--td2.append(isbn);--%>
      <%--td3.append(name);--%>
      <%--tr.append(td);--%>
      <%--tr.append(td2);--%>
      <%--tr.append(td3);--%>
      <%--table.append(tr);--%>
      <%--$("#tmIds").val(tmIds + id + ",");--%>
    <%--}--%>
  <%--}--%>

  <%--function del(obj, id){--%>
    <%--$(obj).parent().parent().remove();--%>
    <%--var tmIds = $("#tmIds").val();--%>
    <%--$("#tmIds").val(tmIds.replace(id+",", ""));--%>
  <%--}--%>

  <%--function sub(){--%>
    <%--$.ajax({--%>
      <%--cache: true,--%>
      <%--type: "POST",--%>
      <%--url:"/addsetTM/stmTMAdd.htm",--%>
      <%--data:{--%>
        <%--"stmId":${param.stmId},--%>
        <%--"tmIds": $("#tmIds").val()--%>
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

  <%--function searchTM(){--%>
    <%--location.href = '#'+$("#isbn").val();--%>
  <%--}--%>
<%--</script>--%>

<input type="hidden" id="tmIds" />
<div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
  <div class="am-panel-hd am-cf" data-am-collapse="{target: '#tm'}">已关联教材<span class="am-icon-chevron-down am-fr"></span></div>
  <div id="tm" class="am-in">
    <table id="tmTable" class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
      <tr class="am-primary">
        <th style="width: 30%;">ISBN</th>
        <th style="width: 60%;">教材名称</th>
        <th style="width: 10%;">操作</th>
      </tr>
      <c:if test="${!empty stmTMList}">
        <c:forEach var="tm" items="${stmTMList}" varStatus="status">
          <tr>
            <td>${tm.isbn}</td>
            <td>${tm.name}</td>
            <td align="center">
              <button type="button" class="am-btn am-btn-primary am-round" onclick="delTM(this, '${tm.id}')"><span class="am-icon-trash-o"></span> 删除</button>
            </td>
          </tr>
        </c:forEach>
      </c:if>
    </table>
  </div>
</div>

<div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
  <div class="am-panel-hd am-cf" data-am-collapse="{target: '#notCourse'}">所有教材<span class="am-icon-chevron-down am-fr"></span></div>
  <div id="notCourse" class="am-in">
    <select id="allTM" name="allTM" multiple  data-am-selected="{searchBox: 1, maxHeight: 200, btnWidth: 700}">
      <option value=""></option>
      <c:forEach var="tm" items="${tmList}">
        <option value="${tm.id}_${tm.isbn}_${tm.name}">[${tm.isbn}]${tm.name}</option>
      </c:forEach>
    </select>
    <button type="button" class="am-btn am-btn-primary am-round" onclick="addTM()"><span class="am-icon-plus"></span> 添加</button>
  </div>
</div>


<script>
  $("select").selected();

  <c:if test="${!empty stmTMList}">
    var tmIds = "";
    <c:forEach var="tm" items="${stmTMList}">
      tmIds += "${tm.id},";
    </c:forEach>
    $("#tmIds").val(tmIds);
  </c:if>

  function addTM(){
    var tmIds = $("#tmIds").val();
    var selectVal = $("#allTM").val();
    if(selectVal == ""){
      app.msg("请选择要添加的教材", 1);
      return;
    }
    for(var i=0; i<selectVal.length; i++){
      var id_isbn_name = selectVal[i];
      var id = id_isbn_name.split("_")[0];
      var isbn = id_isbn_name.split("_")[1];
      var name = id_isbn_name.split("_")[2];
      if(0 > tmIds.indexOf(id)){
        var table = $("#tmTable");
        var tr = $("<tr></tr>");
        var td = $("<td>"+isbn+"</td>");
        var td2 = $("<td>"+name+"</td>");
        var td3 = $("<td><button type=\"button\" class=\"am-btn am-btn-primary am-round\" onclick=\"delTM(this, "+id+")\"><span class=\"am-icon-trash-o\"></span> 删除</button></td>");
        tr.append(td);
        tr.append(td2);
        tr.append(td3);
        table.append(tr);
        tmIds += id+",";
      }
    }
    $("#tmIds").val(tmIds);
    $("#allTM").val("");
    $(".am-selected-status").html("点击选择...");
  }

  function delTM(obj, id){
    $(obj).parent().parent().remove();
    var tmIds = $("#tmIds").val();
    $("#tmIds").val(tmIds.replace(id+",", ""));
  }

</script>
