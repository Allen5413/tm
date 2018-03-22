<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p />
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/spotReplacePay/find.htm" method="post">
  <table width="90%">
    <tr height="40">
      <td align="right"><label >学习中心：</label></td>
      <td>
        <select id="spotCode" name="spotCode" data-am-selected="{maxHeight: 500, searchBox: 1}" onchange="app.changeSelect(this)">
          <c:forEach var="spot" items="${spotList}">
            <option value="${spot.code}" <c:if test="${param.spotCode eq spot.code}">selected="selected" </c:if> >[${spot.code}]${spot.name}</option>
          </c:forEach>
        </select>
      </td>
      <td align="right"><label >入学年：</label></td>
      <td>
        <input type="text" id="year" name="year" />
      </td>
      <td align="right"><label >入学季：</label></td>
      <td>
        <select id="quarter" name="quarter">
          <option value="">--请选择-</option>
          <option value="0">春季</option>
          <option value="1">秋季</option>
        </select>
      </td>
    </tr>
    <tr height="40">
      <td align="right"><label >专业：</label></td>
      <td>
        <select id="specCode" name="specCode" data-am-selected="{maxHeight: 500, searchBox: 1}">
          <option value="">--请选择-</option>
          <c:forEach var="spec" items="${specList}">
            <option value="${spec.code}" <c:if test="${param.specCode == spec.code}">selected="selected" </c:if> >[${spec.code}]${spec.name}</option>
          </c:forEach>
        </select>
      </td>
      <td align="right"><label >层次：</label></td>
      <td>
        <select id="levelCode" name="levelCode">
          <option value="">--请选择--</option>
          <c:forEach var="level" items="${levelList}">
            <option value="${level.code}" <c:if test="${param.levelCode == level.code}">selected="selected" </c:if> >${level.name}</option>
          </c:forEach>
        </select>
      </td>
      <td align="right"><label >学号：</label></td>
      <td>
        <input type="text" id="stuCode" name="stuCode" />
      </td>
    </tr>
    <tr>
      <td align="right"><label >姓名：</label></td>
      <td>
        <input type="text" id="name" name="name"/>
        <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
                data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询'}"
                onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
      </td>
    </tr>
  </table>
</form>
<p /><p />
<c:if test="${!empty pagInfo.pageResults}">
  <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
    <tr>
      <td colspan="999" style="background-color:#FFF">
        <label >交费金额：</label>
        <input type="text" id="money"/>
        <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="add()"><span class="am-icon-plus"></span> 交费</button>
      </td>
    </tr>
    <tr class="am-primary">
      <th>
        <a href="#" onClick="app.checkAll('cb')">全选</a>|<a href="#" onClick="app.checkNAll('cb')">反选</a>
      </th>
      <th>姓名</th>
      <th>学习中心</th>
      <th>入学年季</th>
      <th>学号</th>
      <th>专业</th>
      <th>层次</th>
    </tr>
    <c:if test="${empty pagInfo || empty pagInfo.pageResults}">
      <tr>
        <td colspan="8" align="center" style="color: red;">没有找到相关数据</td>
      </tr>
    </c:if>
    <c:forEach var="json" items="${pagInfo.pageResults}" varStatus="status">
      <tr class="datTr">
        <td>
          <label class="am-checkbox am-secondary" style="margin-top:5px; margin-left:24px;">
            <input type="checkbox" name="cb" value="${json.stuCode}" onclick="changeColor(this)" data-am-ucheck>
          </label>
        </td>
        <td>${json.stuName}</td>
        <td>${json.spoCode}</td>
        <td>${json.toStuFie}</td>
        <td>${json.stuCode}</td>
        <td>${json.spe}</td>
        <td>${json.level}</td>
      </tr>
    </c:forEach>
  </table>
</c:if>
<script>
  $("select").selected();

  function add(){
    var spotCode = $("#spotCode").val();
    if("" == spotCode){
      app.msg("请选择学习中心");
      return;
    }

    var money = $("#money").val();
    if(!vaild.vaildMoney(money)){
      app.msg("请输入正确的交费金额");
      return;
    }

    var codes = "";
    var isCheck = false;
    $("[name=cb]").each(function(){
      if($(this).is(':checked')){
        isCheck = true;
        codes += $(this).val()+",";
      }
    });
    if(!isCheck){
      app.msg("请选择学生");
      return;
    }
    codes = codes.substring(0, codes.length-1);
    app.openDialog("${pageContext.request.contextPath}/addSpotReplacePay/open.htm?codes="+codes+"&money="+money+"&spotCode="+spotCode, "交费", 800, 0.4, function(index){
      app.addForFile('${pageContext.request.contextPath}/addSpotReplacePay/add.htm', "doNextForm", index);
    });
  }
</script>
