<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="main-content" style="overflow-y:scroll; height: 90%;">
  <ul class="create_info_list">
    <li>
      <label class="lab_80">订单号：</label>${orderCode}
      <label class="lab_80">发行渠道：</label>${issueChannel.name}
    </li>
  </ul>
  <form id="addForm" name="addForm" method="post">
    <input type="hidden" id="channelId" name="channelId" value="${channelId}" />
    <table class="table_slist" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <th style="width: 60px;">序号</th>
        <th style="width: 150px;">学院</th>
        <th style="width: 150px;">教材名称</th>
        <th style="width: 100px;">作者</th>
        <th style="width: 150px;">出版社</th>
        <th style="width: 150px;">ISBN</th>
        <th style="width: 70px;">价格</th>
        <th style="width: 70px;">数量</th>
        <th style="width: 100px;">入库数量</th>
        <th></th>
        <th style="width: 60px;">操作</th>
      </tr>
      <c:if test="${empty resultJson}">
        <tr>
          <td colspan="99" align="center" style="color: #ff000c;">没有找到相关数据</td>
        </tr>
      </c:if>
      <c:forEach var="result" items="${resultJson}" varStatus="status">
        <tr>
          <td>${status.index+1}</td>
          <td>${result.deptName}</td>
          <td>${result.tmName}</td>
          <td>${result.author}</td>
          <td>${result.pressName}</td>
          <td>${result.isbn}</td>
          <td>${result.price}</td>
          <td>${result.tmCount}</td>
          <td>${result.storageCount}</td>
          <td>
            <c:if test="${0 == result.state}">
              <input type="hidden" name="ids" value="${result.id}" />
              <input type="text" name="counts" style="width: 50px;" />
            </c:if>
            <c:if test="${1 == result.state}">
              <span style="color:#787878">作废</span>
            </c:if>
          </td>
          <td>
            <c:if test="${0 == result.state}">
              <a href="#" onclick="cancelState('${result.id}')" style="color: #0092DC">作废</a>
            </c:if>
          </td>
        </tr>
      </c:forEach>
    </table>
  </form>
</div>
<a class="com_btn_b" href="#" onclick="storage()" style="margin-top: 10px;"><span>入&nbsp;库</span></a>
<script>
  function storage(){
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/storage/doStorage.htm",
      data:$('#addForm').serialize(),
      async: false,
      success: function(data) {
        if(data.state == 0){
          alert("提交成功！");
          $('#index_iframe').contents().find("#resetBut").click();
          $('#index_iframe').contents().find("#subBut").click();
          closeDialog();
        }else{
          alert(data.msg);
        }
      }
    });
  }

  function cancelState(id){
    if(confirm("您确定要作废该条信息？")){
      $.ajax({
        url:"${pageContext.request.contextPath}/editPurchaseOrderTMStateById/editor.htm",
        method : 'POST',
        async:false,
        data:{"id":id},
        success:function(data){
          if(data.state == 0){
            alert("操作成功！");
            parent.refreshDialog();
          }else {
            alert(data.msg);
          }
        }
      });
    }
  }
</script>