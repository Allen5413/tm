<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="addForm" name="addForm" method="post">
  <div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
    <div class="am-panel-hd am-cf" data-am-collapse="{target: '#spot'}">学习中心信息：<span class="am-icon-chevron-down am-fr"></span></div>
    <div id="spot" class="am-in">
      <div class="am-g am-margin-top">
        <div class="am-u-sm-1 am-text-right"><label >学习中心：</label></div>
        <input type="hidden" id="spotCode" name="spotCode" value="${spotCode}"/>
        <div class="am-u-sm-11">[${spotCode}]${spot.name}</div>
      </div>

      <div class="am-g am-margin-top">
        <div class="am-u-sm-1 am-text-right"><label >邮寄地址：</label></div>
        <div class="am-u-sm-11"><input type="text" id="address" name="address" value="${spot.address}" style="width: 600px;" />&nbsp;&nbsp;&nbsp;&nbsp;*必填</div>
      </div>

      <div class="am-g am-margin-top">
        <div class="am-u-sm-1 am-text-right"><label >收件人：</label></div>
        <div class="am-u-sm-11"><input type="text" id="adminName" name="adminName" value="${spot.adminName}" style="width: 600px;" />&nbsp;&nbsp;&nbsp;&nbsp;*必填</div>
      </div>

      <div class="am-g am-margin-top">
        <div class="am-u-sm-1 am-text-right"><label >手机：</label></div>
        <div class="am-u-sm-11"><input type="text" id="phone" name="phone" value="${spot.phone}" style="width: 600px;" />&nbsp;&nbsp;&nbsp;&nbsp;*必填</div>
      </div>

      <div class="am-g am-margin-top">
        <div class="am-u-sm-1 am-text-right"><label >座机：</label></div>
        <div class="am-u-sm-11"><input type="text" id="tel" name="tel" value="${spot.tel}" style="width: 600px;" /></div>
      </div>

      <div class="am-g am-margin-top">
        <div class="am-u-sm-1 am-text-right"><label >邮编：</label></div>
        <div class="am-u-sm-11"><input type="text" id="postalCode" name="postalCode" value="${spot.postalCode}" style="width: 600px;" /></div>
      </div>
    </div>
  </div>

  <div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
    <div class="am-panel-hd am-cf" data-am-collapse="{target: '#order'}">订单信息：<span class="am-icon-chevron-down am-fr"></span></div>
    <div id="order" class="am-in">
      <p />
      <div class="am-g am-margin-top">
        <div class="am-u-sm-1 am-text-right"><label >入学年：</label></div>
        <div class="am-u-sm-11"><input type="text" id="enYear" name="enYear" style="width: 600px;" />&nbsp;&nbsp;&nbsp;&nbsp;*必填</div>
      </div>
      <div class="am-g am-margin-top">
        <div class="am-u-sm-1 am-text-right"><label >入学季：</label></div>
        <div class="am-u-sm-11">
          <select id="enQuarter" name="enQuarter">
            <option value="0">春季</option>
            <option value="1">秋季</option>
          </select>
        </div>
      </div>
      <div class="am-g am-margin-top">
        <div class="am-u-sm-1 am-text-right"><label >专业：</label></div>
        <div class="am-u-sm-11">
          <select id="specCode" name="specCode" data-am-selected="{maxHeight: 500, searchBox: 1}">
            <c:forEach var="spec" items="${specList}">
              <option value="${spec.code}">[${spec.code}]${spec.name}</option>
            </c:forEach>
          </select>
        </div>
      </div>
      <div class="am-g am-margin-top">
        <div class="am-u-sm-1 am-text-right"><label >层次：</label></div>
        <div class="am-u-sm-11">
          <select id="levelCode" name="levelCode">
            <c:forEach var="level" items="${levelList}">
              <option value="${level.code}">${level.name}</option>
            </c:forEach>
          </select>
        </div>
      </div>
      <div class="am-g am-margin-top">
        <div class="am-u-sm-1 am-text-right"><label >人数：</label></div>
        <div class="am-u-sm-11"><input type="text" id="personNum" name="personNum" style="width: 600px;" />&nbsp;&nbsp;&nbsp;&nbsp;*必填</div>
      </div>
      <p />
    </div>
  </div>

  <div class="am-panel am-panel-primary" style="width:99%; margin-left:4px;">
    <div class="am-panel-hd am-cf" data-am-collapse="{target: '#course'}">课程信息：<span class="am-icon-chevron-down am-fr"></span></div>
    <div id="course" class="am-in">
      <p />
      <div class="am-g am-margin-top">
        <div class="am-u-sm-1 am-text-right"><label >课程编号：</label></div>
        <div class="am-u-sm-11">
          <textarea id="courseCodes" name="courseCodes" cols="96", rows="5"></textarea>
          &nbsp;&nbsp;&nbsp;&nbsp;*必填; 可以用英文输入法的","隔开来输入多个课程编号。例：0001,0002,0003
        </div>
      </div>
      <p />
    </div>
  </div>

  <button type="button" id="searchBtn" class="am-btn am-btn-primary btn-loading-example"
          data-am-loading="{spinner: 'circle-o-notch', loadingText: '提交中...', resetText: '<span class=am-icon-save> 提交'}"
          onclick="sub(this)"><span class="am-icon-save"></span> 提交</button>
</form>
<script>
  $("select").selected();
  function sub(btnObj){
    if($("#spotCode").val() == ""){
      app.msg("请选择学习中心", 1);
      return;
    }
    if($("#address").val() == ""){
      app.msg("请输入邮寄地址", 1);
      return;
    }
    if($("#adminName").val() == ""){
      app.msg("请输入收件人", 1);
      return;
    }
    if($("#phone").val() == "" || $("#phone").val().length < 11){
      app.msg("请输入有效的手机号码", 1);
      return;
    }
    if($("#enYear").val() == ""){
      app.msg("请输入入学年", 1);
      return;
    }
    if(isNaN($("#personNum").val()) || $("#personNum").val() == ""){
      app.msg("请输入正确的人数", 1);
      return;
    }
    if($("#courseCodes").val() == ""){
      app.msg("请输入课程编号", 1);
      return;
    }

    $(btnObj).button('loading');
    setTimeout(function(){
      $.ajax({
        cache: true,
        type: "POST",
        url:"${pageContext.request.contextPath}/addPlaceOrderForSpecAll/add.htm",
        data:$("#addForm").serialize(),
        async: false,
        success: function(data) {
          if(data.state == 0){
            app.msg('提交成功', 0);
            $("a").each(function(){
              var str = $(this).attr("onclick");
              if(typeof(str) != "undefined") {
                if(str.indexOf("/findPlaceOrderPage/openFindPlaceOrderPageList.htm") > -1){
                  $(this).click();
                }
              }
            });
          }else{
            app.msg(data.msg, 1);
          }
        }
      });
      $(btnObj).button('reset');
    }, 100);
  }
</script>