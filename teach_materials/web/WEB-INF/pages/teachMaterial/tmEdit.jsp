<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<form id="editForm" method="post">--%>
  <%--<input type="hidden" name="id" value="${teachMaterial.id}" />--%>
  <%--<input type="hidden" name="version" value="${teachMaterial.version}" />--%>
  <%--<div class="main-content">--%>
    <%--<ul class="create_info_list">--%>
      <%--<li>--%>
        <%--<label class="lab_80 left">ISBN：</label>--%>
        <%--<input type="text" class="input_240" name="isbn" value="${teachMaterial.isbn}" maxlength="50" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">教材名称：</label>--%>
        <%--<input type="text" class="input_240" name="name" value="${teachMaterial.name}" maxlength="50" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80 left">出版社：</label>--%>
        <%--<select class="select" name="pressId" id="pressId">--%>
          <%--<option value="">请选择</option>--%>
          <%--<c:forEach var="press" items="${pressList}">--%>
            <%--<option value="${press.id}" <c:if test="${teachMaterial.pressId == press.id}">selected="selected"</c:if>>${press.name}</option>--%>
          <%--</c:forEach>--%>
        <%--</select>--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80 left">类别：</label>--%>
        <%--<select class="select" name="teachMaterialTypeId" id="teachMaterialTypeId">--%>
          <%--<option value="">请选择</option>--%>
          <%--<c:forEach var="tmType" items="${teachMaterialTypeList}">--%>
            <%--<option value="${tmType.id}" <c:if test="${teachMaterial .teachMaterialTypeId == tmType.id}">selected="selected"</c:if>>${tmType.name}</option>--%>
          <%--</c:forEach>--%>
        <%--</select>--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">状态：</label>--%>
        <%--<input type="radio" name="state" value="0" <c:if test="${teachMaterial .state == 0}">checked</c:if> />启用--%>
        <%--<input type="radio" name="state" value="1" <c:if test="${teachMaterial .state == 1}">checked</c:if> />停用--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">编著者：</label>--%>
        <%--<input type="text" class="input_240" name="author" value="${teachMaterial.author}" maxlength="50" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">出版年：</label>--%>
        <%--<input type="text" class="input_240" name="pressYear" value="${teachMaterial.pressYear}" maxlength="50" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">重量：</label>--%>
        <%--<input type="text" class="input_240" name="weight" value="${teachMaterial.weight}" maxlength="50" />--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">是否套教材：</label>--%>
        <%--<input type="radio" name="isSet" value="0" <c:if test="${teachMaterial.isSet == 0}">checked</c:if> />否--%>
        <%--<input type="radio" name="isSet" value="1" <c:if test="${teachMaterial.isSet == 1}">checked</c:if> />是--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80">是否允许学习中心发放：</label>--%>
        <%--<input type="radio" name="isSpotSend" value="0" <c:if test="${teachMaterial.isSpotSend == 0}">checked</c:if> />否--%>
        <%--<input type="radio" name="isSpotSend" value="1" <c:if test="${teachMaterial.isSpotSend == 1}">checked</c:if> />是--%>
      <%--</li>--%>
      <%--<li>--%>
        <%--<label class="lab_80 left">备注：</label>--%>
        <%--<textarea class="textArea left" name="remark" maxlength="500">${teachMaterial.remark}</textarea>--%>
        <%--<div class="clear"></div>--%>
      <%--</li>--%>
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
    <%--//对数据的检测--%>
    <%--$("#editForm").validate({--%>
      <%--rules : {--%>
        <%--name : {--%>
          <%--required : true--%>
        <%--},--%>
        <%--pressId : {--%>
          <%--required : true--%>
        <%--},--%>
        <%--teachMaterialTypeId : {--%>
          <%--required : true--%>
        <%--}--%>
      <%--},--%>
      <%--messages : {--%>
        <%--name : {--%>
          <%--required : '请输入教材名称'--%>
        <%--},--%>
        <%--pressId : {--%>
          <%--required : '请选择出版社'--%>
        <%--},--%>
        <%--teachMaterialTypeId : {--%>
          <%--required : '请选择教材类型'--%>
        <%--}--%>
      <%--}--%>
    <%--});--%>
  <%--});--%>

  <%--function sub(){--%>
    <%--if($("#editForm").valid()){--%>
      <%--$.ajax({--%>
        <%--cache: true,--%>
        <%--type: "POST",--%>
        <%--url:"${pageContext.request.contextPath}/editTeachMaterial/teachMaterialEdit.htm",--%>
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


<form class="am-form" id="editForm" name="editForm" method="post">
  <input type="hidden" name="id" value="${teachMaterial.id}" />
  <input type="hidden" name="version" value="${teachMaterial.version}" />
  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >ISBN：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input class="am-input-sm" type="text" placeholder="输入ISBN" name="isbn" value="${teachMaterial.isbn}" />
    </div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >教材名称：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input type="text" placeholder="输入教材名称" id="edit_name" name="name" value="${teachMaterial.name}" />
    </div>
    <div class="am-u-sm-4">*必填</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >出版社：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <select id="edit_pressId" name="pressId" data-am-selected="{searchBox: 1, maxHeight: 500}">
        <c:forEach var="press" items="${pressList}">
          <option value="${press.id}" <c:if test="${teachMaterial.pressId == press.id}">selected="selected"</c:if>>${press.name}</option>
        </c:forEach>
      </select>
    </div>
    <div class="am-u-sm-4">*必选</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >类别：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <select id="edit_tmTypeId" name="teachMaterialTypeId">
        <c:forEach var="tmType" items="${teachMaterialTypeList}">
          <option value="${tmType.id}" <c:if test="${teachMaterial .teachMaterialTypeId == tmType.id}">selected="selected"</c:if>>${tmType.name}</option>
        </c:forEach>
      </select>
    </div>
    <div class="am-u-sm-4">*必选</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >状态：</label></div>
    <div class="am-u-sm-6">
      <div class="am-btn-group" data-am-button="">
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="state" value="0" <c:if test="${teachMaterial .state == 0}">checked</c:if>> 启用
        </label>
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="state" value="1" <c:if test="${teachMaterial .state == 1}">checked</c:if>> 停用
        </label>
      </div>
    </div>
    <div class="am-u-sm-4"></div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >编著者：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input type="text" placeholder="输入编著者" name="author" value="${teachMaterial.author}"/>
    </div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >出版年：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input type="text" placeholder="输入出版年" name="pressYear" value="${teachMaterial.pressYear}"/>
    </div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >重量：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input type="text" placeholder="输入重量" name="weight" value="${teachMaterial.weight}"/>
    </div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >是否套教材：</label></div>
    <div class="am-u-sm-6">
      <div class="am-btn-group" data-am-button="">
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="isSet" value="0" <c:if test="${teachMaterial.isSet == 0}">checked</c:if> > 否
        </label>
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="isSet" value="1" <c:if test="${teachMaterial.isSet == 1}">checked</c:if>> 是
        </label>
      </div>
    </div>
    <div class="am-u-sm-4"></div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >是否允许学习中心发放：</label></div>
    <div class="am-u-sm-6">
      <div class="am-btn-group" data-am-button="">
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="isSpotSend" value="0" <c:if test="${teachMaterial.isSpotSend == 0}">checked</c:if> > 否
        </label>
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="isSpotSend" value="1" <c:if test="${teachMaterial.isSpotSend == 1}">checked</c:if>> 是
        </label>
      </div>
    </div>
    <div class="am-u-sm-4"></div>
  </div>

  <div class="am-g am-margin-top-sm">
    <div class="am-u-sm-2 am-text-right"><label >备注：</label></div>
    <div class="am-u-sm-10">
      <textarea rows="6" placeholder="输入备注" name="remark">${teachMaterial.remark}</textarea>
    </div>
  </div>
</form>
<script>
  $("select").selected();
</script>