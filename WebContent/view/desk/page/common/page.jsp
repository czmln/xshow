<%@ page language="java"  pageEncoding="utf-8"%>
<%@ page import="com.sg.syj.entity.vo.PagePo"%>
<%@ page import="java.util.Map"%>

<%

    int nopage=1;//当前页
    int tpage=1;//总页数
    long trecord=0;//总记录数
    int pageSize=0;
    Object obj=request.getAttribute("page");
	 if (obj != null) {
		 PagePo pageInfo=(PagePo)obj;
		  nopage=pageInfo.getPageNumber();
		  tpage=pageInfo.getTotalPage(); 
		  trecord=pageInfo.getTotal();
		  pageSize=pageInfo.getPageSize();
	 }
	
%>
<script type="text/javascript">
	function gopage(obj) {
		var me=$(obj),
		_pagenum=me.attr("value"),
		_pageform=$("#page-form");
		_pageform.find("input[name=pageIndex]").val(_pagenum);
		_pageform.submit();
	}

</script>
<style>
 .fenye {

    text-align: center;
    margin: 20px 0;
  }
  .fenye ul {
     display: inline-block;
     margin-bottom: 0;
     margin-left: 0;
     border-radius: 4px;
     box-shadow: 0 1px 2px rgba(0,0,0,0.05);
  }
  .fenye li {
	display: inline;
  }

  
  .fenye-disable{
     color: #000;
     cursor: default;
     background-color: transparent;
  }
  
  .fenye-invalid{
     color: #999;
     cursor: default;
     background-color: transparent;
     border: 1px solid #ddd;
  }
  
  .fenye-disable:hover{
      color: #000;
     
  }
  
   .fenye-invalid:hover{
      color: #999;
  }
  .fenye-activity{
     color: #08c; 
     border: 1px solid #ddd;
  }
  .fenye-activity:hover{
     background: #f2f8ff;
     border: 1px solid #38f;
  }
  .fenye li  a{
   float: left;
   padding: 4px 12px;
   line-height: 20px;
   text-decoration: none;
   background-color: #fff;
   margin-left: 5px;
  }

</style>
 <div class="fenye">
    
 
	<%-- <ul>

		<li style="margin-top:2px; margin-left:7px">
			<a href="#">
				上一页
			</a>
		</li>

		


		<li style="margin-top:2px; margin-left:7px">
			<a href="#">下一页</a>
		</li>


	</ul> --%>
	
	<ul >
	     <!-- <li class="disabled"><a href="javascript:void(0)">上一页</a></li>
	     <li value="1" class="active"><a href="javascript:void(0)">1</a></li>
	     <li value="2"><a href="javascript:void(0)">2</a></li>
	     <li value="2"><a href="javascript:void(0)">下一页</a></li> -->
	     <li ><a href="javascript:void(0)" class='fenye-invalid' style="border: 0">共<%=tpage%>页/<%=trecord%>条数据</a></li>
	     <li class="disabled"><a href="javascript:void(0)"  <%
	       if(nopage!=1){
	    	   out.print("class='fenye-activity' value='"+(nopage-1)+"' onclick='gopage(this)' ");
	       }else{
	    	   out.print("class='fenye-invalid' ");
	       }
	     %>
	     
	     >上一页</a></li>
	     <%
	  
	        int startPage=nopage-2;
	        int endPage=nopage+2;
	       if(startPage<1){
	    	   startPage=1;
	         }
	       if(endPage>tpage){
	        	endPage=tpage;
	       }
	       if(startPage<3&&tpage>4){
	    	   
	    	  
	    	   endPage=startPage+4;
	        }
	       int timePart=endPage-startPage;
           if(timePart<4&&tpage==endPage){
        	   startPage=endPage-4;
           } 
           if(timePart<4&&startPage<4){
        	   endPage=tpage;
	         }
           if(startPage<1){
	    	   startPage=1;
	         }
	       if(endPage>tpage){
	        	endPage=tpage;
	       }
	      // if(endPage-startPage<)
	       
           for(int i=startPage;i<endPage+1;i++){
                
	       %>
		  <li ><a href="javascript:void(0)" 
		  <%if(i==nopage){
			  out.write("class='fenye-disable'");
		    }else{
		      out.write("class='fenye-activity' value='"+i+"' onclick='gopage(this)' ");
		    }
		  %>
		  
		  ><%=i%></a></li>
	 
	      <%} %>

		  <li ><a href="javascript:void(0)" 
		  <% if(nopage<tpage){
	    	   out.print("class='fenye-activity' value='"+(nopage+1)+"' onclick='gopage(this)' ");
	        }else{
	    	   out.print("class='fenye-invalid' ");
	        }
		  %>   
		  >下一页</a></li> 
	</ul>
	
	<form action="" style="display: none" id="page-form" method="post">
	   <input name="pageIndex">
	      
	   <input name="pageSize" value="<%=pageSize%>">
	</form>
</div>



<%-- <%
	}
%> --%>