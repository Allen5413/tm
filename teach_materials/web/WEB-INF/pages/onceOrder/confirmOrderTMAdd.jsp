<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="addTMForm" name="addTMForm" method="post">
  <input type="hidden" id="add_studentCode" name="studentCode" />
  <div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
    <div class="am-panel-hd am-cf" data-am-collapse="{target: '#orderStudentCode'}">订单学号：<span class="am-icon-chevron-down am-fr"></span></div>
    <div id="orderStudentCode" class="am-in">
      <div class="am-g am-margin-top">
        <div class="am-u-sm-1 am-text-right"><label >学号：</label></div>
        <div class="am-u-sm-11"><input type="text" id="studentCodeText" /></div>
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

      <table id="change_tm_table" class="am-table am-table-bordered am-table-striped am-table-hover" style="width:100%;">
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
      $("#change_tm_table").html("");
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
      $("#change_tm_table").html(html);

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
            var tab = $("#change_tm_table");
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
                "<input type='hidden' name='courseCodes' value='"+tm.courseCode+"'>" +
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