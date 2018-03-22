<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<form class="am-form" id="editForm" method="post">
  <input type="hidden" name="semesterId" value="${param.semesterId}" />
  <input type="hidden" name="spotCodes" value="${param.spotCodes}" />
  <input type="hidden" name="studentCode" value="${param.studentCode}" />
  <input type="hidden" name="tmId" value="${param.tmId}" />
  <input type="hidden" name="beginDate" value="${param.beginDate}" />
  <input type="hidden" name="endDate" value="${param.endDate}" />
  <input type="hidden" name="price" value="${param.price}" />
  <div class="am-g am-margin-top">
    <div class="am-u-sm-3 am-text-right"><label >数量：</label></div>
    <div class="am-u-sm-4">
      <input class="am-input-sm" type="number" placeholder="输入数量" required id="edit_newCount" name="newCount" value="0" />
    </div>
    <div class="am-u-sm-5">*必填</div>
  </div>
</form>