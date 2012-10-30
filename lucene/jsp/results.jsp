<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>

<%@ page import="java.io.IOException"%>
<%@ page import="org.apache.lucene.document.Document"%>
<%@ page import="org.apache.lucene.index.CorruptIndexException"%>
<%@ page import="org.apache.lucene.index.Term"%>
<%@ page import="org.apache.lucene.search.Hits"%>
<%@ page import="org.apache.lucene.search.IndexSearcher"%>
<%@ page import="org.apache.lucene.search.Query"%>
<%@ page import="org.apache.lucene.search.TermQuery"%>
<%@ page import="com.bjsxt.lucene.util.*"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

int start = 0;
try{
start = Integer.parseInt(request.getParameter("start"));
}catch(Exception e){}
int max = 10;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'results.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="styles.css">
	

  </head>
    <script type="text/javascript">
  function check(){
  	if(document.forms[0].key.value==""){
  		alert("�ؼ��ʲ���Ϊ��");
	  	return false;
  	}
  	return true;
  }
  </script>
  <body>

<%
	String index = "D:\\share\\0400_Servlet_JSP\\soft\\apache-tomcat-5.5.17\\apache-tomcat-5.5.17\\index_cn";
	IndexSearcher searcher=null;
	searcher = new IndexSearcher(index);
	Query query = new TermQuery(new Term("title",request.getParameter("key")));
	Hits hits = searcher.search(query);
	System.out.println(hits.length());
	if(hits.length()>0){

 %>  
	<form action="results.jsp" method="get" name=searchForm onsubmit="return check()">
    	�ؼ��ʣ�<input name=key value="<%=request.getParameter("key") %>">
    	<input name=start value="<%=start%>">
    	<input type=submit value="����">
    </form>
  һ���ҵ���<%=hits.length() %>�����
  <div id=gridData>
  <table border=0 cellpadding="3" cellspacing="1">
	  <tr>
		  <td>���</td>
		  <td>����</td>
		  <!-- <td>ժҪ</td> -->
	  </tr>
  <%
  
	for (int i = start; i < start+max; i++) {
		if(i>=hits.length()){
			break;
		}
		Document doc = hits.doc(i);
		String _title = doc.get("title");
		String _summary = doc.get("summary");
		String _path = doc.get("path");
		//D:\share\0100_J2SE\soft\html_zh_CN\html\zh_CN\api\java\lang\String.html
		//http://127.0.0.1/api/java/lang/String.html

		
		_path = _path.replaceAll("D:\\\\share\\\\0100_J2SE\\\\soft\\\\html_zh_CN\\\\html\\\\zh_CN\\\\","");
		_path = _path.replace('\\','/');
		_path = "http://127.0.0.1/"+_path;
		System.out.println(_path);
		
%>
	  <tr>
		  <td><%=i+1 %></td>
		  <td><a href="<%=_path %>"><%=_title %></a></td>
		  <!-- <td><%=com.bjsxt.lucene.util.DataFilter.getHTML(_summary)%></td> -->
	  </tr>
		 
<%	}
	searcher.close(); 

  %>
  
  
  
    </table>
<a href="javascript:first();">��ҳ</a>
<%if(start-max>=0){ %>
	<a href="javascript:previous();">��һҳ</a>
<%}else{ %>
	��һҳ
<%} %>
<%if(start+max<hits.length()){ %>
	<a href="javascript:next();">��һҳ</a>
<%}else{ %>
	��һҳ
<%} %>
<a href="javascript:last();">βҳ</a>
</div>

<script type="text/javascript">
function first(){
	searchForm.start.value=0;
	searchForm.submit();
}
function previous(){
	searchForm.start.value=<%=start-max%>;
	searchForm.submit();
}
function next(){
	searchForm.start.value=<%=start+max%>;
	searchForm.submit();
}
function last(){
	searchForm.start.value=<%=hits.length()/max*max%>;
	searchForm.submit();
}

</script>
<%}else{ %>

	�Բ���û�ҵ�������������趨��������<a href=javascript:history.back()>����</a>

<%} %>
     <br>
  </body>
</html>
