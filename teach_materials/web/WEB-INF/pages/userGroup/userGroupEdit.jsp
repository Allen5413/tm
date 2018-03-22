<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<form id="editForm" method="post">--%>
  <%--<input type="hidden" name="id" value="${userGroup.id}" />--%>
  <%--<input type="hidden" name="version" value="${userGroup.version}" />--%>
  <%--<input type="hidden" name="" id="resourceIds" />--%>
  <%--<div class="main-content">--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80">用户组名称：</label>--%>
        <%--<input type="text" class="input_240" name="name" value="${userGroup.name}" maxlength="50" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80 left">说明：</label>--%>
        <%--<textarea class="textArea left" name="remark" maxlength="500" style="width: 245px;">${userGroup.remark}</textarea>--%>
        <%--<div class="clear"></div>--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80 left">关联资源：</label>--%>
      <%--</li>--%>
    <%--</ul>--%>
  <%--</div>--%>
  <%--<div class="easyui-layout" data-options="fit:true, border:false" style="padding-left: 50px; overflow-y:scroll; height: 280px;">--%>
    <%--<ul id="menuTree" checkbox="true" ></ul>--%>
  <%--</div>--%>
  <%--<br /><br />--%>
  <%--<div class="main-content" style="padding-left: 50px;">--%>
    <%--<ul>--%>
      <%--<li>--%>
        <%--<label class="lab_80 left">--%>
          <%--<a class="com_btn_b" href="#" onclick="sub()"><span>提&nbsp;交</span></a>--%>
        <%--</label>--%>
      <%--</li>--%>
    <%--</ul>--%>
  <%--</div>--%>
<%--</form>--%>
<%--<script>--%>
  <%--$(function(){--%>
    <%--var height = getWindowHeightSize();--%>
    <%--$(".easyui-layout").height(height * 0.28);--%>

    <%--//对数据的检测--%>
    <%--$("#editForm").validate({--%>
      <%--rules : {--%>
        <%--name : {--%>
          <%--required : true--%>
        <%--}--%>
      <%--},--%>
      <%--messages : {--%>
        <%--name : {--%>
          <%--required : '请输入菜单名称'--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>

    <%--//初始化菜单树--%>
    <%--MenuTree.contextPath = "${pageContext.request.contextPath}";--%>
    <%--MenuTree.treeId = "#menuTree";--%>

    <%--var menuData = [];--%>
    <%--<c:forEach var="menu" items="${menuList}">--%>
      <%--var menu = {--%>
        <%--'id':'${menu.id}',--%>
        <%--'text':'${menu.name}',--%>
        <%--'state': 'closed',--%>
        <%--'attributes': {--%>
          <%--'isOpen': false--%>
        <%--}--%>
      <%--};--%>
      <%--menuData.push(menu);--%>
    <%--</c:forEach>--%>
    <%--MenuTree.initMenuTree(menuData);--%>

    <%--//复选框填值--%>
    <%--var resourceData = [];--%>
    <%--var resourceIds = "";--%>
    <%--<c:forEach var="resource" items="${resourceList}">--%>
      <%--var resource = {--%>
        <%--'id':'${resource.id}',--%>
        <%--'menuId':'${resource.menuId}'--%>
      <%--};--%>
      <%--resourceData.push(resource);--%>
      <%--resourceIds += ${resource.id}+",";--%>
    <%--</c:forEach>--%>
    <%--$("#resourceIds").val(resourceIds.substring(0, resourceIds.length-1));--%>
    <%--MenuTree.setMenuTreeCheckbox(resourceData);--%>
  <%--});--%>

  <%--function sub(){--%>
    <%--if($("#editForm").valid()){--%>
      <%--$.ajax({--%>
        <%--cache: true,--%>
        <%--type: "POST",--%>
        <%--url:"/editUserGroup/userGroupEdit.htm",--%>
        <%--data:$('#editForm').serialize(),--%>
        <%--async: false,--%>
        <%--success: function(data) {--%>
          <%--if(data.state == 0){--%>
            <%--alert("提交成功！");--%>
            <%--$('#index_iframe').contents().find("#subBut").click();--%>
            <%--closeDialog();--%>
          <%--}else{--%>
            <%--alert(data.msg);--%>
          <%--}--%>
        <%--}--%>
      <%--});--%>
    <%--}--%>
  <%--}--%>
<%--</script>--%>

<form class="am-form" id="editForm" method="post">
  <input type="hidden" name="id" value="${userGroup.id}" />
  <input type="hidden" name="version" value="${userGroup.version}" />
  <input type="hidden" name="resourceIds" id="resourceIds" />

  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >用户组名称：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="text" placeholder="输入用户组名称" required id="edit_name" name="name" value="${userGroup.name}" />
    </div>
    <div class="am-u-sm-5">*必填，不可重复</div>
  </div>

  <div class="am-g am-margin-top-sm">
    <div class="am-u-sm-3 am-text-right"><label >备注：</label></div>
    <div class="am-u-sm-9">
      <textarea rows="6" placeholder="输入备注" name="remark">${userGroup.remark}</textarea>
    </div>
  </div>

  <div class="am-g am-margin-top-sm">
    <div class="am-u-sm-3 am-text-right"><label >关联资源：</label></div>
    <div class="am-u-sm-9">
      <div class="zTreeDemoBackground left">
        <ul id="myTree" class="ztree"></ul>
      </div>
    </div>
  </div>
</form>
<script>
  $(function(){
    var menuData = [];
    <c:forEach var="menu" items="${menuList}">
      var menu = { id:"${menu.ztreeId}", pId:"${menu.pId}", name:"${menu.name}", rId:"${menu.rId}", checked:${menu.isCheck}, open:true}
      menuData.push(menu);
    </c:forEach>
    app.tree($('#myTree'), menuData);

  });
</script>