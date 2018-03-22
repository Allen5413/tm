<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="create_info_list">
  <li>
    <label class="lab_80">课程编号：</label>
    <input type="text" id="courseCode" name="courseCode">
    <label class="lab_80">教材名称：</label>
    <input type="text" id="name" name="name">
    &nbsp;&nbsp;
    <a class="com_btn_b" href="#" onclick="subSearch()" style="margin-top: 10px;"><span>查&nbsp;询</span></a>
  </li>
</ul>
<table id="tm_table" class="table_slist" cellpadding="0" cellspacing="0" width="100%">
  <tr>
    <th style="width: 60px;">
      <a href="#" onclick="checkAll('cb')" style="color: #0092DC">全选</a>|
      <a href="#" onclick="checkNall('cb')" style="color: #0092DC">反选</a>
    </th>
    <th style="width: 150px;">教材名称</th>
    <th style="width: 100px;">作者</th>
    <th style="width: 150px;">出版社</th>
    <th style="width: 150px;">ISBN</th>
    <th style="width: 70px;">价格</th>
    <th style="width: 70px;">数量</th>
  </tr>
</table>
<a class="com_btn_b" href="#" onclick="openAddDiv()" style="margin-top: 10px;"><span>返&nbsp;回</span></a>
<script>
  function subSearch(){
    if(1 > $.trim($("#courseCode").val()).length && 1 > $.trim($("#name").val()).length){
      alert("请输入查询条件");
    }else{
      //清除之前查询的数据
      $("#tm_table").html("");
      var html = "<tr>";
      html += "<th style='width: 60px;'>";
      html += "<a href=\"#\" onclick=\"checkAll('cb')\" style=\"color: #0092DC\">全选</a>|";
      html += "<a href=\"#\" onclick=\"checkNall('cb')\" style=\"color: #0092DC\">反选</a>";
      html += "</th>";
      html += "<th style=\"width: 150px;\">教材名称</th>";
      html += "<th style=\"width: 100px;\">作者</th>";
      html += "<th style=\"width: 150px;\">出版社</th>";
      html += "<th style=\"width: 150px;\">ISBN</th>";
      html += "<th style=\"width: 70px;\">价格</th>";
      html += "<th style=\"width: 70px;\">数量</th>";
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
                var td = $("<td align='center'><input type='checkbox' name='cb' value="+tm.id+" onchange='selectTR()'></td>");
                var td2 = $("<td id='"+tm.id+"_name'>"+tm.name+"</td>");
                var td3 = $("<td  id='"+tm.id+"_author'>"+tm.author+"</td>");
                var td4 = $("<td  id='"+tm.id+"_pressName'>"+tm.pressName+"</td>");
                var td5 = $("<td  id='"+tm.id+"_isbn'>"+tm.isbn+"</td>");
                var td6 = $("<td  id='"+tm.id+"_price'>"+tm.price+"</td>");
                var td7 = $("<td><input type='text' id='"+tm.id+"_count' name='"+tm.id+"_count' style='width: 40px;'></td>");
                tr.append(td).append(td2).append(td3).append(td4).append(td5).append(td6).append(td7);
                tab.append(tr);
                $("#"+tm.id+"_count").val(1);
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

  function openAddDiv(){
    var valid = true;
    $("[name=cb]").each(function(){
      if($(this).prop("checked")){
        var id = $(this).val();
        if(isNaN($.trim($("#"+id+"_count").val())) && valid){
          alert("请输入正确的数量");
          valid = false;
        }
        if($.trim($("#"+id+"_count").val()) < 1 && valid){
          alert("请至少输入1本教材");
          valid = false;
        }
      }
    });

    if(valid) {
      //清除之前查询的数据
      $("#change_tm_table").html("");
      var html = "<tr>";
      html += "<th style=\"width: 60px;\">序号</th>";
      html += "<th style=\"width: 150px;\">教材名称</th>";
      html += "<th style=\"width: 100px;\">作者</th>";
      html += "<th style=\"width: 150px;\">出版社</th>";
      html += "<th style=\"width: 150px;\">ISBN</th>";
      html += "<th style=\"width: 70px;\">价格</th>";
      html += "<th style=\"width: 70px;\">数量</th>";
      html += "<th style=\"width: 50px;\">操作</th>";
      html += "</tr>";
      $("#change_tm_table").html(html);

      var tab = $("#change_tm_table");
      var num = 1;
      $("[name=cb]").each(function() {
        if ($(this).prop("checked")) {
          var id = $(this).val();
          var tr = $("<tr></tr>");
          var td = $("<td align='center'>"+num+"</td>");
          var td2 = $("<td>"+$("#"+id+"_name").html()+"</td>");
          var td3 = $("<td>"+$("#"+id+"_author").html()+"</td>");
          var td4 = $("<td>"+$("#"+id+"_pressName").html()+"</td>");
          var td5 = $("<td>"+$("#"+id+"_isbn").html()+"</td>");
          var td6 = $("<td>"+$("#"+id+"_price").html()+"</td>");

          var td7 = $("<td>" +
          "<input type='hidden' name='tmIds' value='"+id+"'>" +
          "<input type='text' id='tm_"+id+"_count' name='tmCounts' style='width: 40px;' value='"+$("#"+id+"_count").val()+"'>" +
          "</td>");
          var td8 = $("<td><a href=\"#\" onclick=\"del(this)\" style=\"color: #0092DC\">删除</a></td>");
          tr.append(td).append(td2).append(td3).append(td4).append(td5).append(td6).append(td7).append(td8);
          tab.append(tr);
          num++;
        }
      });

      $("#addOrderTM_div").show();
      $("#searchTM").hide();
    }
  }
</script>