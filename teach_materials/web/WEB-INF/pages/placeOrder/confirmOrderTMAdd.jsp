<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<form id="addTMForm" name="addTMForm" method="post">--%>
  <%--<input type="hidden" id="spotCode" name="spotCode" value="${spot.code}" />--%>
  <%--<br />--%>
  <%--学习中心：(${spot.code})${spot.name}&nbsp;&nbsp;&nbsp;&nbsp;--%>
  <%--邮寄地址：<input type="text" id="address" name="address" value="${spot.address}" style="width: 400px;" /><br /><br />--%>
  <%--收件人：<input type="text" id="adminName" name="adminName" value="${spot.adminName}" style="width: 150px;" />&nbsp;&nbsp;--%>
  <%--手机：<input type="text" id="phone" name="phone" value="${spot.phone}" style="width: 150px;" />&nbsp;&nbsp;--%>
  <%--座机：<input type="text" id="tel" name="tel" value="${spot.tel}" style="width: 150px;" />&nbsp;&nbsp;--%>
  <%--邮编：<input type="text" id="postalCode" name="postalCode" value="${spot.postalCode}" style="width: 150px;" />--%>
  <%--<br /><br />--%>
  <%--<table id="change_tm_table" class="table_slist" cellpadding="0" cellspacing="0" width="100%">--%>
    <%--<tr>--%>
      <%--<th style="width: 60px;">序号</th>--%>
      <%--<th style="width: 150px;">教材名称</th>--%>
      <%--<th style="width: 100px;">作者</th>--%>
      <%--<th style="width: 150px;">出版社</th>--%>
      <%--<th style="width: 150px;">ISBN</th>--%>
      <%--<th style="width: 70px;">价格</th>--%>
      <%--<th style="width: 70px;">数量</th>--%>
      <%--<th style="width: 50px;">操作</th>--%>
    <%--</tr>--%>
  <%--</table>--%>
<%--</form>--%>
<%--<table width="100%">--%>
  <%--<tr>--%>
    <%--<td align="right">--%>
      <%--<a class="com_btn_b" href="#" onclick="openSearchTMDiv()" style="margin-top: 10px;"><span>添加教材</span></a>&nbsp;&nbsp;--%>
      <%--<a class="com_btn_b" href="#" onclick="subAddTM()"><span>提&nbsp;&nbsp;交</span></a>--%>
    <%--</td>--%>
  <%--</tr>--%>
<%--</table>--%>
<%--<script>--%>
  <%--function openSearchTMDiv(){--%>
    <%--$("#addOrderTM_div").hide();--%>
    <%--$("#searchTM").show();--%>
  <%--}--%>

  <%--function del(obj){--%>
    <%--$(obj).parent().parent().remove();--%>
  <%--}--%>

  <%--function subAddTM(){--%>
    <%--var valid = true;--%>

    <%--if($("#spotCode").val() == ""){--%>
      <%--alert("请选择学习中心");--%>
      <%--valid = false;--%>
      <%--return;--%>
    <%--}--%>
    <%--if(trim($("#address").val()) == ""){--%>
      <%--alert("请输入邮寄地址");--%>
      <%--valid = false;--%>
      <%--return;--%>
    <%--}--%>
    <%--if(trim($("#adminName").val()) == ""){--%>
      <%--alert("请输入收件人");--%>
      <%--valid = false;--%>
      <%--return;--%>
    <%--}--%>
    <%--if(trim($("#phone").val()) == "" && trim($("#tel").val()) == ""){--%>
      <%--alert("手机和座机不能都为空");--%>
      <%--valid = false;--%>
      <%--return;--%>
    <%--}--%>
    <%--if(trim($("#postalCode").val()) == ""){--%>
      <%--alert("请输入邮编");--%>
      <%--valid = false;--%>
      <%--return;--%>
    <%--}--%>
    <%--if($("#change_tm_table").find("input[type=text]").length < 1 && valid){--%>
      <%--alert("请添加教材");--%>
      <%--valid = false;--%>
      <%--return;--%>
    <%--}--%>
    <%--$("#change_tm_table").find("input[type=text]").each(function(){--%>
      <%--if(isNaN($.trim($(this).val())) && valid){--%>
        <%--alert("请输入正确的数量");--%>
        <%--valid = false;--%>
        <%--return;--%>
      <%--}--%>
      <%--if($.trim($(this).val()) < 1 && valid){--%>
        <%--alert("请至少输入1本教材");--%>
        <%--valid = false;--%>
        <%--return;--%>
      <%--}--%>
    <%--});--%>
    <%--if(valid){--%>
      <%--$.ajax({--%>
        <%--cache: true,--%>
        <%--type: "POST",--%>
        <%--url:"${pageContext.request.contextPath}/addPlaceOrderTM/add.htm",--%>
        <%--data:$('#addTMForm').serialize(),--%>
        <%--async: false,--%>
        <%--success: function(data) {--%>
          <%--if(data.state == 0){--%>
            <%--parent.parent.$('#index_iframe').contents().find("#subBut").click();--%>
            <%--alert("提交成功！");--%>
            <%--Dialog.close();--%>
          <%--}else{--%>
            <%--alert(data.msg);--%>
          <%--}--%>
        <%--}--%>
      <%--});--%>
    <%--}--%>
  <%--}--%>
<%--</script>--%>


<form id="addTMForm" name="addTMForm" method="post">
  <input type="hidden" id="add_spotCode" name="spotCode" value="${spot.code}" />
  <div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
    <div class="am-panel-hd am-cf" data-am-collapse="{target: '#spot'}">学习中心信息：<span class="am-icon-chevron-down am-fr"></span></div>
    <div id="spot" class="am-in">
      <div class="am-g am-margin-top">
        <div class="am-u-sm-1 am-text-right"><label >学习中心：</label></div>
        <div class="am-u-sm-11">[${spot.code}]${spot.name}</div>
      </div>

      <div class="am-g am-margin-top">
        <div class="am-u-sm-1 am-text-right"><label >邮寄地址：</label></div>
        <div class="am-u-sm-11"><input type="text" id="add_address" name="address" value="${spot.address}" style="width: 600px;" /></div>
      </div>

      <div class="am-g am-margin-top">
        <div class="am-u-sm-1 am-text-right"><label >收件人：</label></div>
        <div class="am-u-sm-11"><input type="text" id="add_adminName" name="adminName" value="${spot.adminName}" style="width: 600px;" /></div>
      </div>

      <div class="am-g am-margin-top">
        <div class="am-u-sm-1 am-text-right"><label >手机：</label></div>
        <div class="am-u-sm-11"><input type="text" id="add_phone" name="phone" value="${spot.phone}" style="width: 600px;" /></div>
      </div>

      <div class="am-g am-margin-top">
        <div class="am-u-sm-1 am-text-right"><label >座机：</label></div>
        <div class="am-u-sm-11"><input type="text" id="add_tel" name="tel" value="${spot.tel}" style="width: 600px;" /></div>
      </div>

      <div class="am-g am-margin-top">
        <div class="am-u-sm-1 am-text-right"><label >邮编：</label></div>
        <div class="am-u-sm-11"><input type="text" id="add_postalCode" name="postalCode" value="${spot.postalCode}" style="width: 600px;" /></div>
      </div>
    </div>
  </div>

  <div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
    <div class="am-panel-hd am-cf" data-am-collapse="{target: '#tm'}">教材信息：<span class="am-icon-chevron-down am-fr"></span></div>
    <div id="tm" class="am-in">
      <p /><p />
      <input type="hidden" id="rows" name="rows" />
      <input type="hidden" id="currentPage" name="page" value="${pageInfo.currentPage}"/>
      <label >课程编号：</label>
      <input type="text" id="courseCode" name="courseCode" />&nbsp;&nbsp;&nbsp;&nbsp;
      <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example" onclick="subSearch()"><span class="am-icon-search"></span> 查询</button>
      *必填; 可以用英文输入法的","隔开来输入多个课程编号。例：0001,0002,0003
      <p /><p />

      <table id="tm_table" class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
      </table>
    </div>
  </div>
</form>
<script>
  function subSearch(){
    if(1 > $.trim($("#courseCode").val()).length && 1 > $.trim($("#name").val()).length){
      app.msg("请输入查询条件", 1);
    }else{
      //清除之前查询的数据
      $("#tm_table").html("");
      var html = "<tr class='am-primary'>";
      html += "<th style=\"width: 250px;\">课程名称</th>";
      html += "<th style=\"width: 150px;\">教材名称</th>";
      html += "<th style=\"width: 100px;\">作者</th>";
      html += "<th style=\"width: 150px;\">出版社</th>";
      html += "<th style=\"width: 150px;\">ISBN</th>";
      html += "<th style=\"width: 70px;\">价格</th>";
      html += "<th style=\"width: 70px;\">数量</th>";
      html += "<th>操作</th>";
      html += "</tr>";
      $("#tm_table").html(html);

      //重新查询数据
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/findTeachMaterialByNameOrCourseCode/find.htm",
        data:{
          name:$.trim($("#name").val()),
          courseCode:$.trim($("#courseCode").val())
        },
        async: false,
        success: function(data) {
          if(data.state == 0){
            var tab = $("#tm_table");
            if(0 < data.resultList.length){
              for(var i=0; i<data.resultList.length; i++){
                var tm = data.resultList[i];
                var tr = $("<tr></tr>");
                var td2 = $("<td id='"+tm.id+"_courseCode'>["+tm.courseCode+"]"+tm.courseName+"</td>");
                var td3 = $("<td id='"+tm.id+"_name'>"+tm.name+"</td>");
                var td4 = $("<td  id='"+tm.id+"_author'>"+tm.author+"</td>");
                var td5 = $("<td  id='"+tm.id+"_pressName'>"+tm.pressName+"</td>");
                var td6 = $("<td  id='"+tm.id+"_isbn'>"+tm.isbn+"</td>");
                var td7 = $("<td  id='"+tm.id+"_price'>"+tm.price+"</td>");
                var td8 = $("<td>" +
                          "<input type='hidden' name='tmIds' value='"+tm.id+"'>" +
                          "<input type='text' id='tm_"+tm.id+"_count' name='tmCounts' value='1' style='width: 40px;'>" +
                          "</td>");
                var td9 = $("<td>" +
                              "<div class=\"am-btn-group\">" +
                              "<button type=\"button\" class=\"am-btn am-btn-primary am-round\" onClick=\"del(this)\"><span class=\"am-icon-trash-o\"></span> 删除</button> " +
                              "</div>" +
                            "</td>");
                tr.append(td2).append(td3).append(td4).append(td5).append(td6).append(td7).append(td8).append(td9);
                tab.append(tr);
              }
            }else{
              var tr = $("<tr></tr>");
              var td = $("<td align='center' colspan='99' style='color: red'>没有找到教材数据</td>");
              tab.append(tr).append(td);
            }
          }else{
            alert(data.msg);
          }
        }
      });
    }
  }

  function del(obj){
    $(obj).parent().parent().parent().remove();
  }
</script>