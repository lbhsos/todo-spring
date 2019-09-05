<!DOCTYPE html>
<html lang="ko">

  <head>
      <meta charset="utf-8">
      <meta name="description" content="text/html; charset=UTF-8">
      <title>PLAN</title>
      <link href="./css/style.css" rel="stylesheet">
  </head>

  <body>
    <div id="container">
      <header>TODO</header>
      <!-- FORM -->
      <form id="todoForm" name="todoForm" enctype="application/json">
      	<table id="input_table">
      	<tr>
        <td width="40%" class="title_form">
        TITLE: <input type="text" id="title" name="title" style="width:300px;">
        </td>
        <td width="20%" class="priority_form">
        PRIORITY: <select name="priority" id="priority">
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
        </select>
        </td>
        <td width="30%" class="deadline_form">
        DEADLINE: <input type="date" id="deadline" name="deadline">
        </td>
        <td width="5%" class="top_btn">
        <input type="submit" value="REGISTER" id="submit_btn" onclick="formSubmit()">
        </td>
        <td width="5%" class="top_btn">
        <input type="reset" value="CANCEL">
        </td>
        </tr>
        </table>
      </form>
	
	<!-- TABLE -->
      	<div class="table_title">UNDO</div>
        <table id="todo_table">
          <tr>
            <th width="10%">PRIORITY</th>
            <th width="30%">TITLE</th>
            <th width="30%">DEADLINE</th>
            <th width="10%">MODIFY</th>
            <th width="10%">DELETE</th>
            <th width="10%">COMPLETE</th> 
          </tr>
        </table>

      	<div class="table_title">DONE</div>
      	<table id="done_table">
          <tr>
            <th width="10%">PRIORITY</th>
            <th width="30%">TITLE</th>
            <th width="30%">DEADLINE</th>
            <th width="10%">MODIFY</th>
            <th width="10%">DELETE</th>
            <th width="10%">COMPLETE</th> 
          </tr>

        </table>

    	<div class="table_title">OVERDUE</div>
      	<table id="overdue_table">
          <tr>
            <th width="10%">PRIORITY</th>
            <th width="30%">TITLE</th>
            <th width="30%">DEADLINE</th>
            <th width="10%">MODIFY</th>
            <th width="10%">DELETE</th>
            <th width="10%">COMPLETE</th> 
          </tr>
        </table>
      </div>

    
    <script>
    	
    	function appendPlan(HTML,table){
    		var parNode = document.querySelector(table);
    		console.log(parNode);
    		parNode.append(HTML);
    		
    	} 
       	
    	//DELETE
    	function sendAjaxState(state, id){
    		var oReq = new XMLHttpRequest();
    		oReq.addEventListener("load", function(){
    			if (state === "delete")
    				deleteData(id+"_tr");
    			else{
    				completeData(id+"_tr");
    			}
    			console.log(id+"_tr");
    			
    		});
    		oReq.open(state,"/planner/api/"+id);
    		oReq.send();
    	}
    	function deleteData(id){
    		var node = document.getElementById(id);
    		node.remove(); 
    	} 
    	
    	function completeData(id){
    		var node = document.getElementById(id);
    		var dupNode = node.cloneNode(true);
    		node.remove();
    		appendPlan(dupNode, "#done_table");
    	}
    	
    	//GET DATA
    	document.addEventListener("DOMContentLoaded", function(){
    		sendAjax();
    	});
    	function sendAjax(){
    		var oReq = new XMLHttpRequest();
    		oReq.addEventListener("load", function(){
    			var data = JSON.parse(oReq.responseText);
    			makeTableTemplate(data);
    		});
    		oReq.open("GET","/planner/api");
    		oReq.send();
    	}
    	
    	function makeTableTemplate(data){
    		var todoHTML = makeList(data['todoList']);
    		var doneHTML = makeList(data['doneList']);
    		var overdueHTML = makeList(data['overdueList']);
    		document.querySelector("#todo_table").innerHTML += todoHTML;
    		document.querySelector("#done_table").innerHTML += doneHTML;
    		document.querySelector("#overdue_table").innerHTML += overdueHTML;
    		
    	}
    	
    	function makeList(list){
    		var resultHTML ="";
    		for(var i=0; i<list.length; i++){
    			resultHTML += makeData(list[i]);
    		}
    		return resultHTML;
    	}
    	
    	function makeData(data){
    		var html = document.getElementById("planList").innerHTML;
    		tempHTML = "";
    		tempHTML = html.replace("{priority}", data.priority)
							.replace("{title}", data.title)
							.replace("{deadline}", data.deadline)
							.replace(/{id}/gi, data.id);
    		return tempHTML;
    	}
    </script>
    
	<script id="planList" type="text/template">
		<tr id={id}_tr>
			<td width="10%">{priority}</td>
            <td width="30%">{title}</td>
            <td width="30%">{deadline}</td>
            <td width="10%"><button type="button" class="modify_btn"><img src="./img/modify.ico" style="width:20px; height:20px; border:none;"></button></td>
            <td width="10%""><button type="button" class="delete_btn" onclick="sendAjaxState('delete', {id});"><img src="./img/delete.ico" style="width:20px; height:20px; border:none;"></button></td>
			<td width="10%"><button type="button" class="complete_btn" onclick="sendAjaxState('put', {id});"><img src="./img/complete.ico" style="width:20px; height:20px; border:none;"></button></td>
		</tr>
	</script>
	
	
	
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
	<script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
	<!-- <script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js"></script> -->
	<script>
	function formSubmit(){
			 $("form[name='todoForm']").validate({
	     				  rules: {
	     						title: {
	     							required:true,
	     						},
	     						deadline: {
	     							required: true,
	     							afterToday: true
	     						}
	     					},
	     					messages: {
	     						title: "Please enter your plan title",
	     					},
	     				    invalidHandler: function (form, validator) {
	     				            var errors = validator.numberOfInvalids();
	     				            if (errors) {
	     				                alert(validator.errorList[0].message);
	     				                validator.errorList[0].element.focus();
	     				            }
	     				    },
	     					submitHandler: function(form){
	     						
	     						debugger;
	     						var data = $(form).serializeArray();
	     						console.log(data);
	     						console.log("");
 				     			var json_data = {}
 				     			for (var i=0; i<data.length; i++){
 				     				json_data[data[i]['name']] = data[i]['value'];
 				     			}
	     				        $.ajax({
	     				             url: 'api/todo',
	     				             type: 'post',
	     				             header:{
	     				            	 "Content-Type":"application/json"
	     				             },
	     				             data: JSON.stringify(json_data),
	     				             dataType: "json",
	     				             contentType:"application/json",
	     				             success: function() {
	     				         		 resultHTML = makeData(json_data);
	     				         		document.querySelector("#todo_table").innerHTML += resultHTML;
	     				             },
	     				             error: function(request, status, error){
	     				            	  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	     				                  var err=JSON.parse(request.responseText);
	     				                  alert(err.resData[0].errorMsg);
	     				                  if(errorFunction!=undefined){
	     				                      errorFunction();
	     				                  }
	     				                  alert(request);
	     				             }
	     				         });  
	     					}

		});
		 $.validator.addMethod("afterToday", function(value, element) {
			 	var today = new Date();
			 	var flag = false;
				var input_date = new Date(value).getTime();
				if (input_date >= today) flag = true;

			 return flag}, "Please specify the validate date for plan");
	
	
	}
	</script>
  </body>
</html>