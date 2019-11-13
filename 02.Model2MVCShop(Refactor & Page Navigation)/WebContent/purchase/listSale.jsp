<%@page import="com.model2.mvc.common.SearchVO"%>
<%@page import="com.model2.mvc.service.purchase.vo.PurchaseVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%
	HashMap<String,Object> map = (HashMap)request.getAttribute("map");
    SearchVO search = (SearchVO)request.getAttribute("searchVO");
	ArrayList<PurchaseVO> list = null;
	
	String buyerId = (String)map.get("buyerId");

	
	int total = 0;
	if(map != null){
	list = (ArrayList)map.get("list");
	total = ( (Integer)map.get("count") ).intValue();
	}
	
	int totalPage=0;
	totalPage = total/search.getPageUnit();
	if( total%search.getPageUnit() == 1 || total%search.getPageUnit() ==2 ){
		totalPage +=1;
	}
	
	int currentPage = search.getPage();
		
%>
<!DOCTYPE html>

<html>
<head>
<title>�ǸŸ��</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">


</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">


<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">�Ǹ� ���</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">��ü <%=total %> �Ǽ�, ����  <%=currentPage %>������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ��ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��ȭ��ȣ</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��ǰ��ȣ</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�ֹ���</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	
<% 		int no=list.size();
    
	for(int i=0; i<list.size(); i++) {
	PurchaseVO purchase = (PurchaseVO)list.get(i);
%>	


	<tr class="ct_list_pop">
		<td align="center">
			<a href="/getPurchase.do?tranNo=<%=purchase.getTranNo() %>"><%=i +1 %></a>
		</td>
		<td></td>
		<td align="left">
			<a href="/getUser.do?userId=<%=buyerId %>"><%=buyerId %></a>
		</td>
		<td></td>
		<td align="left"><%=purchase.getReceiverName() %></td>
		<td></td>
		<td align="left"><%=purchase.getReceiverPhone() %></td>
		<td></td>
		<td align="left"><%=purchase.getTranNo() %> </td>
		<td></td>
		<td align="left"><%=purchase.getOrderDate() %></td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
<% } %>	
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
		 <% 
		 for(int i=1; i<=totalPage; i++){
		 %>
		 
			<a href="/listSale.do?page=<%=i %>"><%=i %></a> 
			
		<% } %>
		</td>
	</tr>
</table>

<!--  ������ Navigator �� -->
</form>

</div>

</body>
</html>