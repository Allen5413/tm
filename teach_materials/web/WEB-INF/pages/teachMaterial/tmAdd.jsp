<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="am-form" id="addForm" name="addForm" method="post">
  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >ISBN：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input class="am-input-sm" type="text" placeholder="输入ISBN" name="isbn" />
    </div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >教材名称：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input type="text" placeholder="输入教材名称" id="add_name" name="name"/>
    </div>
    <div class="am-u-sm-4">*必填</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >出版社：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <select id="add_pressId" name="pressId" data-am-selected="{searchBox: 1, maxHeight: 500}">
        <c:forEach var="press" items="${pressList}">
          <option value="${press.id}">${press.name}</option>
        </c:forEach>
      </select>
    </div>
    <div class="am-u-sm-4">*必选</div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >类别：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <select id="add_tmTypeId" name="teachMaterialTypeId">
        <c:forEach var="tmType" items="${teachMaterialTypeList}">
          <option value="${tmType.id}">${tmType.name}</option>
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
          <input type="radio" name="state" value="0"> 启用
        </label>
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="state" value="1"> 停用
        </label>
      </div>
    </div>
    <div class="am-u-sm-4"></div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >价格：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input type="number" placeholder="输入价格" name="price"/>
    </div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >版次：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input type="text" placeholder="输入版次" name="revision"/>
    </div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >编著者：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input type="text" placeholder="输入编著者" name="author"/>
    </div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >出版年：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input type="text" placeholder="输入出版年" name="pressYear"/>
    </div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >重量：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input type="text" placeholder="输入重量" name="weight"/>
    </div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >是否套教材：</label></div>
    <div class="am-u-sm-6">
      <div class="am-btn-group" data-am-button="">
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="isSet" value="0" checked > 否
        </label>
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="isSet" value="1"> 是
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
          <input type="radio" name="isSpotSend" value="0" checked > 否
        </label>
        <label class="am-btn am-btn-primary am-btn-s">
          <input type="radio" name="isSpotSend" value="1"> 是
        </label>
      </div>
    </div>
    <div class="am-u-sm-4"></div>
  </div>

  <div class="am-g am-margin-top">
    <div class="am-u-sm-2 am-text-right"><label >关联课程编号：</label></div>
    <div class="am-u-sm-4" style="float:left">
      <input type="text" placeholder="输入课程编号" name="courseCode"/>
    </div>
  </div>

  <div class="am-g am-margin-top-sm">
    <div class="am-u-sm-2 am-text-right"><label >备注：</label></div>
    <div class="am-u-sm-10">
      <textarea rows="6" placeholder="输入备注" name="remark"></textarea>
    </div>
  </div>
</form>
<script>
  $("select").selected();
</script>