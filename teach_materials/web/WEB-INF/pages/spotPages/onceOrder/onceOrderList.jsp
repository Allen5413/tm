<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<form id="pageForm" name="pageForm" action="${pageContext.request.contextPath}/findOnceOrderPageByWhere/find.htm" method="post">
  <input type="hidden" id="rows" name="rows" />
  <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
  <table width="90%">
    <tr height="40">
      <td align="right"><label >学习中心：</label></td>
      <td>[${spot.code}]${spot.name}</td>
      <td align="right"><label >入学年：</label></td>
      <td><input type="text" id="enterYear" name="enterYear" value="${param.enterYear}" /></td>
      <td align="right"><label >入学季：</label></td>
      <td>
        <select id="quarter" name="quarter" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <option value="0" <c:if test="${param.quarter eq '0'}">selected="selected" </c:if> >春季</option>
          <option value="1" <c:if test="${param.quarter eq '1'}">selected="selected" </c:if> >秋季</option>
        </select>
      </td>
    </tr>
    <tr height="40">
      <td align="right"><label >学期：</label></td>
      <td>
        <select id="semesterId" name="semesterId" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <c:forEach var="semester" items="${semesterList}">
            <option value="${semester.id}" <c:if test="${(empty param.semesterId && semester.isNowSemester == 0) || (!empty param.semesterId && param.semesterId == semester.id)}">selected="selected" </c:if> >${semester.semester}</option>
          </c:forEach>
        </select>
      </td>
      <td align="right"><label >状态：</label></td>
      <td>
        <select id="state" name="state" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <option value="0" <c:if test="${param.state eq '0'}">selected="selected" </c:if> >未确认</option>
          <option value="1" <c:if test="${param.state eq '1'}">selected="selected" </c:if> >已确认</option>
          <option value="2" <c:if test="${param.state eq '2'}">selected="selected" </c:if> >采购中</option>
          <option value="3" <c:if test="${param.state eq '3'}">selected="selected" </c:if> >分拣中</option>
          <option value="4" <c:if test="${param.state eq '4'}">selected="selected" </c:if> >已打包</option>
          <option value="5" <c:if test="${param.state eq '5'}">selected="selected" </c:if> >已发出</option>
          <option value="6" <c:if test="${param.state eq '6'}">selected="selected" </c:if> >已签收</option>
        </select>
      </td>
      <td align="right"><label >教材数量：</label></td>
      <td>
        <select id="tmCount" name="tmCount">
          <option value="0" <c:if test="${param.tmCount eq '0'}">selected="selected" </c:if> >大于0</option>
          <option value="1" <c:if test="${param.tmCount eq '1'}">selected="selected" </c:if> >所有</option>
        </select>
      </td>
    </tr>
    <tr height="40">
      <td align="right"><label >专业：</label></td>
      <td>
        <select id="specCode" name="specCode" data-am-selected="{maxHeight: 500, searchBox: 1}" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <c:forEach var="spec" items="${specList}">
            <option value="${spec.code}" <c:if test="${param.specCode eq spec.code}">selected="selected" </c:if>>[${spec.code}]${spec.name}</option>
          </c:forEach>
        </select>
      </td>
      <td align="right"><label >层次：</label></td>
      <td>
        <select id="levelCode" name="levelCode" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <c:forEach var="level" items="${levelList}">
            <option value="${level.code}" <c:if test="${param.levelCode eq level.code}">selected="selected" </c:if> >${level.name}</option>
          </c:forEach>
        </select>
      </td>
      <td align="right"><label >订单号：</label></td>
      <td><input type="text" id="orderCode" name="orderCode" value="${param.orderCode}" /></td>
    </tr>
    <tr height="40">
      <td align="right"><label >学号：</label></td>
      <td><input type="text" id="studentCode" name="studentCode" value="${param.studentCode}" /></td>
      <td align="right"><label >姓名：</label></td>
      <td><input type="text" id="studentName" name="studentName" value="${param.studentName}" /></td>
      <td align="right"><label >是否领书：</label></td>
      <td>
        <select id="studentSign" name="studentSign" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <option value="0" <c:if test="${param.studentSign eq '0'}">selected="selected" </c:if> >是</option>
          <option value="1" <c:if test="${param.studentSign eq '1'}">selected="selected" </c:if> >否</option>
        </select>
      </td>
    </tr>
    <tr height="40">
      <td align="right"><label >是否快递学生：</label></td>
      <td>
        <select id="isSendStudent" name="isSendStudent" onchange="app.changeSelect(this)">
          <option value=""></option>
          <option value="null">全部</option>
          <option value="0" <c:if test="${param.isSendStudent eq '0'}">selected="selected" </c:if> >否</option>
          <option value="1" <c:if test="${param.isSendStudent eq '1'}">selected="selected" </c:if> >是</option>
        </select>
      </td>
    </tr>
    <tr>
      <td colspan="99" style="padding-left:20px;">
        <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
                data-am-loading="{spinner: 'circle-o-notch', loadingText: '查询中...', resetText: '查询超时'}"
                onclick="app.searchFormPage($('#pageForm'), $('#pageForm').attr('action'), this)"><span class="am-icon-search"></span> 查询</button>
      </td>
    </tr>
  </table>
</form>
<p /><p />
<c:if test="${method eq 'search'}">
  <table class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
    <tr>
      <td colspan="999" style="background-color:#FFF">
        <button class="am-btn am-btn-primary am-btn-sm btn-loading-example" type="button"
                data-am-loading="{spinner: 'circle-o-notch', loadingText: '确认中...', resetText: '批量确认'}"
                onClick="batchConfirm(this)"><span class="am-icon-cog"></span> 批量确认</button>&nbsp;&nbsp;
        <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="addOrder()"><span class="am-icon-plus"></span> 新增订单</button>&nbsp;&nbsp;
        <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="printOrderBatch()"><span class="am-icon-print"></span> 打印</button>&nbsp;&nbsp;
        <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="takeBookBatch()"><span class="am-icon-edit"></span> 领书</button>&nbsp;&nbsp;
        <button class="am-btn am-btn-primary am-btn-sm" type="button" onClick="downTakeBook()"><span class="am-icon-download"></span> 下载领书表</button>
      </td>
    </tr>
    <tr class="am-primary">
      <th style="width: 5%;">
        <a href="#" onclick="app.checkAll('ids');">全选</a>|
        <a href="#" onclick="app.checkNAll('ids');">反选</a>
      </th>
      <th style="width: 8%;">订单号</th>
      <th style="width: 7%;">学号</th>
      <th style="width: 5%;">姓名</th>
      <th style="width: 14%;">专业</th>
      <th style="width: 5%;">层次</th>
      <th style="width: 7%;">电话</th>
      <th style="width: 5%;">教材数量</th>
      <th style="width: 4%;">金额</th>
      <th style="width: 5%;">状态</th>
      <th style="width: 5%;">大包编号</th>
      <th style="width: 4%;">顺序号</th>
      <th style="width: 4%;">已领书</th>
      <th style="width: 5%;">操作人</th>
      <th style="width: 8%;">操作时间</th>
      <th>操作</th>
    </tr>
    <c:if test="${empty pageInfo || empty pageInfo.pageResults}">
      <tr>
        <td colspan="20" align="center" style="color: red;">没有找到相关数据</td>
      </tr>
    </c:if>
    <c:forEach var="json" items="${pageInfo.pageResults}" varStatus="status">
      <tr>
        <td>
          <c:if test="${json.state == 0 || (json.state >= 5 && (json.studentSign == 1 || empty json.studentSign))}">
            <input type="checkbox" name="ids" value="${json.id}" onclick="changeColor(this)">
          </c:if>
        </td>
        <td>${json.orderCode}</td>
        <td>${json.studentCode}</td>
        <td>${json.name}</td>
        <td>${json.specName}</td>
        <td>${json.levelName}</td>
        <td>${json.homeTel}</td>
        <td>${json.tmCount}</td>
        <td>${json.totalPrice}</td>
        <td>
          <c:choose>
            <c:when test="${json.state == 0}">未确认</c:when>
            <c:when test="${json.state == 1}">已确认</c:when>
            <c:when test="${json.state == 2}">处理中</c:when>
            <c:when test="${json.state == 3}">分拣中</c:when>
            <c:when test="${json.state == 4}">已打包</c:when>
            <c:when test="${json.state == 5}">已发出</c:when>
            <c:when test="${json.state == 6}">已签收</c:when>
            <c:otherwise>未知</c:otherwise>
          </c:choose>
        </td>
        <td>
          <c:if test="${!empty json.sort}">
            第${json.sort}件
          </c:if>
        </td>
        <td>${json.printSort}</td>
        <td>
          <c:choose>
            <c:when test="${json.studentSign == 0}">已领书</c:when>
            <c:otherwise>未领书</c:otherwise>
          </c:choose>
        </td>
        <td>${json.operator}</td>
        <td>${json.operateTime}</td>
        <td>
          <div class="doc-dropdown-justify-js">
            <div class="am-dropdown" data-am-dropdown="" id="operateBtn_${status.index+1}">
              <button class="am-btn am-btn-default am-btn-sm am-dropdown-toggle" data-am-dropdown-toggle=""><span class="am-icon-cog"></span> <span class="am-icon-caret-down"></span></button>
              <ul class="am-dropdown-content">
                <c:if test="${json.state == 0}">
                  <li><a href="#" onClick="confirmOrder(${json.id})"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-cog"></span> 确认</a></li>
                </c:if>
                <c:if test="${json.state > 0}">
                  <li><a href="#" onClick="copy(${json.id}, '${json.studentCode}')"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-copy"></span> 复制</a></li>
                </c:if>
                <c:if test="${json.state == 1}">
                  <li><a href="#" onClick="cancelOrder(${json.id})"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-remove"></span> 取消确认</a></li>
                </c:if>
                <c:if test="${json.state >= 5 && json.studentSign == 1}">
                  <li><a href="#" onClick="printOrder(${json.id})"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-print"></span> 打印</a></li>
                  <li><a href="#" onClick="takeBook(${json.id})"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-edit"></span> 领书</a></li>
                </c:if>
                <li><a href="#" onClick="detail('${json.orderCode}',${json.state},${json.id},'${json.studentCode}')"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-th-list"></span> 查看明细</a></li>
                <c:if test="${0 < json.state && json.state < 3}">
                  <li><a href="#" onClick="isSendStudent('${json.studentCode}', ${json.id})"><span style="margin-left:10px; margin-top:5px; margin-bottom:5px;" class="am-icon-cog"></span> 快递发学生</a></li>
                </c:if>
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

  function printOrderBatch(){
    var chk_value =[];
    $('input[name="ids"]:checked').each(function(){
      chk_value.push($(this).val());
    });
    if(chk_value.length < 1){
      app.msg("请选择要打印的订单", 1);
      return false;
    }
    $("#ids_print").val(chk_value);
    open("${pageContext.request.contextPath}/findOnceOrderTMPrintByOrderId/find.htm?ids="+chk_value);
  }

  function takeBookBatch(){
    var chk_value =[];
    $('input[name="ids"]:checked').each(function(){
      chk_value.push($(this).val());
    });
    if(chk_value.length < 1){
      app.msg("请选择要领书的订单", 1);
      return false;
    }
    app.edit("${pageContext.request.contextPath}/editOnceOrderForStudentSign/editor.htm?ids="+chk_value, {});
  }

  function downTakeBook(){
    var spotCode = $("#spotCode").val();
    if(spotCode == ""){
      app.msg("请选择学习中心", 1);
      return false;
    }
    $.ajax({
      cache: true,
      type: "POST",
      url:"${pageContext.request.contextPath}/downOnceForTakeBookBySpotCode/down.htm?spotCode="+spotCode,
      data:{},
      async: false,
      success: function(data) {
        open("${pageContext.request.contextPath}"+data);
      }
    });
  }

  function printOrder(id){
    open("${pageContext.request.contextPath}/findOnceOrderTMPrintByOrderId/find.htm?ids="+id);
  }

  function confirmOrder(id){
    app.confirm("您确定要确认该订单？", function(){
      app.edit("${pageContext.request.contextPath}/confirmOnceOrder/confirm.htm", {"id":id});
    });
  }

  function batchConfirm(btnObj){
    var ids = "";
    $('input[name="ids"]:checked').each(function(){
      ids += $(this).val()+",";
    });
    ids = ids.substr(0, ids.length - 1);
    if(ids.length < 1){
      app.msg("请选择要确认的订单", 1);
      return;
    }
    app.openOneBtnDialog('${pageContext.request.contextPath}/confirmOnceOrder/batchConfirm.htm?ids='+ids, '订单确认中', 400, 280);
  }


  function copy(id, studentCode){
    app.openDialog('${pageContext.request.contextPath}/copyOnceOrderTM/open.htm?id='+id+'&studentCode='+studentCode, '复制订单明细', 800, 0.6, function(index){
      var ids = "";
      var isChange = false;
      $("#changeStudent").find("option").each(function(){
        isChange = true
        ids += $(this).val()+",";
      });
      if(!isChange){
        app.msg("请选择要复制的学生", 1);
      }else{
        if("" != ids && 0 < ids.length){
          $("#ids").val(ids.substr(0, ids.length-1))
        }
        $.ajax({
          cache: true,
          type: "POST",
          url:"${pageContext.request.contextPath}/copyOnceOrderTM/copy.htm",
          data:$('#editForm').serialize(),
          async: false,
          success: function(data) {
            if(data.state == 0){
              app.msg("提交成功！", 0);
              $("#searchBtn").click();
              layer.close(index);
            }else{
              app.msg(data.msg, 1);
            }
          }
        });
      }
    });
  }

  function cancelOrder(id){
    app.confirm("您确定要取消该订单？", function(){
      app.edit("${pageContext.request.contextPath}/cancelOnceOrder/cancel.htm", {"id":id});
    });
  }

  function detail(code, state, orderId, studentCode){
    open('${pageContext.request.contextPath}/findOnceOrderTMListByOrderCode/find.htm?orderCode='+code+'&state='+state+'&orderId='+orderId+'&studentCode='+studentCode);
  }

  function isSendStudent(studentCode, orderId){
    app.openDialog("${pageContext.request.contextPath}/editOnceOrderForIsSendStudent/open.htm?studentCode="+studentCode+"&orderId="+orderId, "修改订单是否邮寄给学生", 500, 0.6, function(index){
      var mobile = $("#mobile").val().trim();
      var postalCode = $("#postalCode").val().trim();
      var province = $("#province").val().trim();
      var city = $("#city").val().trim();
      var sendAddress = $("#sendAddress").val().trim();
      var isSendStudent = $('input:radio:checked').val();
      if(0 != isSendStudent && 1 != isSendStudent){
        app.msg("请选择是否快递给学生", 1);
        return false;
      }
      if(!vaild.vaildPhone(mobile)){
        app.msg("请输入正确的手机号码", 1);
        return false;
      }
      if(postalCode == ""){
        app.msg("请输入邮政编码", 1);
        return false;
      }
      if(province == "" || province.length < 1){
        app.msg("请输入省市", 1);
        return false;
      }
      if(city == "" || city.length < 1){
        app.msg("请输入市区", 1);
        return false;
      }
      if(sendAddress == "" || sendAddress.length < 1){
        app.msg("请输入详细地址", 1);
        return false;
      }
      $("#sendAddress").val(province+"|"+city+"|"+sendAddress);
      app.edit("${pageContext.request.contextPath}/editOnceOrderForIsSendStudent/editor.htm", $('#editForm').serialize(), index);
    });
  }

  function addOrder(){
    app.openDialog('${pageContext.request.contextPath}/addOnceOrder/open.htm', '新增学生订单', 1100, 0.6, function(index){
      var valid = true;
      var url;

      //如果传了订单号，就是添加订单的教材；如果没传，就是新增订单
      <c:if test="${empty param.orderCode}">
      url = "${pageContext.request.contextPath}/addOnceOrder/add.htm";
      if($("#studentCodeText").val().trim().length < 1){
        app.msg("请输入学号", 1);
        valid = false;
      }else{
        $("#add_studentCode").val($("#studentCodeText").val().trim());
      }
      </c:if>
      <c:if test="${!empty param.orderCode}">
      url = "${pageContext.request.contextPath}/addStudentBookOrderTM/add.htm";
      </c:if>


      if($("#change_tm_table").find("input[type=text]").length < 1 && valid){
        app.msg("请添加教材", 1);
        valid = false;
      }
      $("#change_tm_table").find("input[type=text]").each(function(){
        if(isNaN($(this).val().trim()) && valid){
          app.msg("请输入正确的数量", 1);
          valid = false;
        }
        if($(this).val().trim() < 1 && valid){
          app.msg("请至少输入1本教材", 1);
          valid = false;
        }
      });
      if(valid){
        $.ajax({
          cache: true,
          type: "POST",
          url:url,
          data:$('#addTMForm').serialize(),
          async: false,
          success: function(data) {
            if(data.state == 0){
              app.msg("提交成功！", 0);
              $("#searchBtn").click();
              layer.close(index);
            }else{
              app.msg(data.msg, 1);
            }
          }
        });
      }
    });
  }
</script>