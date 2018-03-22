<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<div class="main-content">--%>
  <%--<table class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
    <%--<tr>--%>
      <%--<th style="width: 50px;">序号</th>--%>
      <%--<th style="width: 150px;">发行渠道</th>--%>
      <%--<th style="width: 100px;">库存</th>--%>
      <%--<th>操作</th>--%>
    <%--</tr>--%>
    <%--<c:if test="${empty teachMaterialStocks}">--%>
      <%--<tr>--%>
        <%--<td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>--%>
      <%--</tr>--%>
    <%--</c:if>--%>
    <%--<c:forEach var="teachMaterialStock" items="${teachMaterialStocks}" varStatus="status">--%>
      <%--<tr>--%>
        <%--<td align="center">${status.index+1}</td>--%>
        <%--<td>${teachMaterialStock.name}</td>--%>
        <%--<td>--%>
          <%--${teachMaterialStock.stock}--%>
          <%--<a href="#" onclick="editStock(${teachMaterialStock.id})"><div class="editDivIcon"></div></a>--%>
        <%--</td>--%>
        <%--<td>--%>
          <%--<a href="#" onclick="allot(${teachMaterialStock.issueChannelId})" style="color: #0092DC">调拨</a>--%>
        <%--</td>--%>
      <%--</tr>--%>
    <%--</c:forEach>--%>
  <%--</table>--%>
  <%--<br><br>--%>
  <%--<a class="com_btn_b" href="#" onclick="addStock()"><span>新增库存</span></a>--%>
<%--</div>--%>
<%--<div id="dialogDiv2"></div>--%>
<%--<script>--%>
  <%--function allot(oldIssueChannelId){--%>
    <%--parent.openD($("#dialogDiv2"), "调拨库存", 0.2, 0.2, '${pageContext.request.contextPath}/addStockAllotLog/openAddStockAllotLog.htm?tmId=${param.tmId}&oldIssueChannelId='+oldIssueChannelId);--%>
  <%--}--%>

  <%--function editStock(id){--%>
    <%--parent.openD($("#dialogDiv2"), "修改库存", 0.3, 0.2, '${pageContext.request.contextPath}/editTeachMaterialStock/open.htm?id='+id);--%>
  <%--}--%>

  <%--function addStock(){--%>
    <%--parent.openD($("#dialogDiv2"), "新增库存", 0.3, 0.3, '${pageContext.request.contextPath}/addTeachMaterialStock/open.htm?tmId=${param.tmId}');--%>
  <%--}--%>
<%--</script>--%>

<table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
  <tr>
    <td colspan="999" style="background-color:#FFF">
      <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="addStock()"><span class="am-icon-plus"></span> 新增库存</button>
    </td>
  </tr>
  <tr class="am-primary">
    <th style="width: 10%;">序号</th>
    <th style="width: 40%;">发行渠道</th>
    <th style="width: 30%;">库存</th>
    <th>操作</th>
  </tr>
  <c:if test="${empty teachMaterialStocks}">
    <tr>
      <td colspan="20" align="center" style="color: #ff000c;">没有找到相关数据</td>
    </tr>
  </c:if>
  <c:forEach var="teachMaterialStock" items="${teachMaterialStocks}" varStatus="status">
    <tr>
      <td align="center">${status.index+1}</td>
      <td>${teachMaterialStock.name}</td>
      <td>
        ${teachMaterialStock.stock}
        <a href="#" onclick="editStock(${teachMaterialStock.id})"><span class="am-icon-edit"></span></a>
      </td>
      <td>
        <div class="am-btn-group">
          <button type="button" class="am-btn am-btn-primary am-round" onClick="allot(${teachMaterialStock.issueChannelId})"><span class="am-icon-edit"></span> 调拨</button>
        </div>
      </td>
    </tr>
  </c:forEach>
</table>
<script>
  function allot(oldIssueChannelId){
    app.openDialog('${pageContext.request.contextPath}/addStockAllotLog/openAddStockAllotLog.htm?tmId=${param.tmId}&oldIssueChannelId='+oldIssueChannelId, '调拨库存', 500, 0.3, function(index){
      var newIssueChannelId = $("#newIssueChannelId").val();
      var stock = $("#add_stock").val().trim();
      if(newIssueChannelId == ""){
        app.msg("请选择发行渠道", 1);
        return false;
      }
      if(stock == ""){
        app.msg("请输入库存", 1);
        return false;
      }
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/addStockAllotLog/atockAllotLogAdd.htm",
        data:$('#addForm').serialize(),
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg("提交成功！", 0);
            layer.closeAll();
            app.openOneBtnDialog('${pageContext.request.contextPath}/findtmStockBytmId/doFindtmStockBytmId.htm?tmId=${param.tmId}', '查看库存', 600, 0.6);
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
    });
  }

  function editStock(id){
    app.openDialog('${pageContext.request.contextPath}/editTeachMaterialStock/open.htm?id='+id, '修改库存', 500, 0.35, function(index){
      var stock = $("#edit_stock").val().trim();
      if(stock == ""){
        app.msg("请输入库存", 1);
        return false;
      }
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/editTeachMaterialStock/stockEdit.htm",
        data:$('#editForm').serialize(),
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg("提交成功！", 0);
            layer.closeAll();
            app.openOneBtnDialog('${pageContext.request.contextPath}/findtmStockBytmId/doFindtmStockBytmId.htm?tmId=${param.tmId}', '查看库存', 600, 0.6);
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
    });
  }

  function addStock(){
    app.openDialog('${pageContext.request.contextPath}/addTeachMaterialStock/open.htm?tmId=${param.tmId}', '新增库存', 500, 0.35, function(index){
      var stock = $("#add_stock").val().trim();
      if(stock == ""){
        app.msg("请输入库存", 1);
        return false;
      }
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/addTeachMaterialStock/stockAdd.htm",
        data:$('#addForm').serialize(),
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg("提交成功！", 0);
            layer.closeAll();
            app.openOneBtnDialog('${pageContext.request.contextPath}/findtmStockBytmId/doFindtmStockBytmId.htm?tmId=${param.tmId}', '查看库存', 600, 0.6);
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
    });
  }
</script>