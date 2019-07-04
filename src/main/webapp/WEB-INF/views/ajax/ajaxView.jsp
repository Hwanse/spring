<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>

$(document).ready(function(){
	
	console.log("ready");	
	
	// requestData 클릭시 이벤트 헨들러
	$("#requestData").on("click", function(){
		$.ajax({
			url: "/ajax/requestData",
			method : "post",
			success: function(data){
				// pageVO.page, pageVO.pageSize
				$("#page").text(data.pageVO.page);
				$("#pageSize").text(data.pageVO.pageSize);
				
			},
			error : function(status){
				alert("상태 : " + status);
			}
			
		});
		
	});

	$("#requestDataResponseBody").on("click", function(){
		$.ajax({
			url: "/ajax/requestDataResponseBody",
			method : "post",
			success: function(data){
				// data : {page:5, pageSize:10} 
				// data.pageVO : {pageVO : {page : 5, pageSize : 10}}
				$("#pageResponseBody").text(data.page);
				$("#pageSizeResponseBody").text(data.pageSize);
				
			},
			error : function(status){
				alert("상태 : " + status);
			}
			
		});
		
	});
	
	// user클릭시 이벤트 헨들러
	$("#user").on("click", function(){
		$.ajax({
			url: "/ajax/user",
			method : "post",
			data : "userId=" + $("#userId").val(), // 파라미터 값 전달
			success : function(data){
				console.log(data);
// 				$("#name").val(data.userVO.name);
// 				$("#alias").val(data.userVO.alias);
// 				$("#birth").val(data.userVO.birthStr);
				
				var html = "";
				html += "name : <input type=\"text\" id=\"name\" readonly value=\"" + data.userVO.name+"\"/>";
				html += "alias : <input type=\"text\" id=\"alias\" readonly value=\"" + data.userVO.alias+"\"/>";
				html += "birth : <input type=\"text\" id=\"birth\" readonly value=\"" + data.userVO.birthStr+"\"/>";
				
				$("#userJsonInfo").html(html);
			},
			error: function(status){
				alert("상태 : " +status);
			}
			
		})
		
	})
	
	$("#userHtml").on("click", function(){
		$.ajax({
			url: "/ajax/userHtml",
			method : "post",
			data : $("#frm").serialize(), // serialize() : form태그 데이터 전송
			success : function(data){
				console.log(data);
				// document.getElementById('userInfo').innerHTML(data);
				// document.getElementById('userInfo').innerTEXT(data);
				$("#userInfo").html(data);
			},
			error: function(status){
				console.log(status);
			}
		
		})
	})
	/* 
	public class UserVO{
		private String userId;
		private String pass;
		public String userIf();
		
	}
	UserVO user = new UserVO();
	user.setUserId("brown");
	user.setPass("brown1234");
	 */
	
	 // 전송할 json객체를 준비
	var user = {userId:"brown",pass:"brown1234"}; // var user2 = new Object();

	// JSON.stringify() : 자바스크립트 객체를 json문자열로 생성
	// JSON.parse("json문자열") : json 문자열을 자바스크립트 객체로 변경
	$("#userFormString").text("userId=brown&pass=brown1234");
	$("#userJsonString").text(JSON.stringify(user));
	
	$("#userJsonStringBtn").on("click",function(){
		$.ajax({
			url : "/ajax/requestBody",
			method : "post",
			contentType : "application/json",	// ajax를 통해서 보내는 데이터 형식이 json임을 알려준다.
// 			dataType : "xml",
			dataType : "json",
			data : JSON.stringify(user),
			success : function(data){
				console.log(data);
				
				// json
				$(".userId").text(data.userId);
				$(".pass").text(data.pass);

				// xml
// 				$(".userId").text(data.getElementsByTagName("userId")[0].childNodes[0].textContent);
// 				$(".pass").text(data.getElementsByTagName("pass")[0].childNodes[0].textContent);
			}
		})
		
	})

	
	
});
</script>
<h2>ajax json 데이터 요청</h2>
<a id="requestData">데이터 가져오기</a><br>
page : <span id="page"></span><br>
pageSize: <span id="pageSize"></span><br>


<h2>ajax json 데이터 요청(ResponseBody)</h2>
<a id="requestDataResponseBody">데이터 가져오기</a><br>
page : <span id="pageResponseBody"></span><br>
pageSize: <span id="pageSizeResponseBody"></span><br>

<h2>ajax json 데이터 요청2</h2>
<a id="user">데이터 가져오기</a><br>
userId : <input id="userId" type="text" value="brown"/>
<!-- name : <input id="name" type="text" readonly/> -->
<!-- alias : <input id="alias" type="text" readonly/> -->
<!-- birth : <input type="text" id="birth" readonly/> -->
<div id="userJsonInfo"></div>

<h2>ajax html 데이터 요청2</h2>
<a id="userHtml">데이터 가져오기</a><br>
<form id="frm">
	userId : <input id="userIdHtml" type="text" name="userId" value="brown"/>
</form>
<div id="userInfo"></div>

<h2> ajax json데이터 보내기</h2>
<a id="userJsonStringBtn">데이터보내기</a><br>
요청 보내는 데이터(기존) <div id="userFormString"></div>
요청 보내는 데이터 <div id="userJsonString"></div>
받는 데이터		 
<div id="userJsonResult">
	userId : <span class="userId"></span><br>
	pass : <span class="pass"></span>
</div>

