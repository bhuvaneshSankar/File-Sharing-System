<%@ page import="Demo.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import = "java.util.Iterator"%>
<%@page import = "java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Directory Management Using JNI</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <style>
  	.saveButton{
  		margin-top: 10px;

  		float: right;
  	}
    .filedisplay{
      display: flex;
    }
    .headerdata{
      display: flex;
    }
  </style>
  <script>
  	function setdataDir(uname, prevDirec, abspath){
    
      
      console.log(uname);
  		document.getElementById("hiduname").value = uname;
      document.getElementById("hidprevdirec").value = prevDirec;
      document.getElementById("hidabspathdirc").value = abspath;
      console.log(uname);
   //   console.log(path);
  	}
    function setdataFile(uname, val, abspath){
      document.getElementById("hidunamefile").value = uname;
      document.getElementById("newfileParentName").value = val;
      document.getElementById("hidabspathfilec").value = abspath;
    }
    function setdelDir(uname, val, abspath){
      document.getElementById("deldiruname").value = uname;
      document.getElementById("deldirname").value = val;
      document.getElementById("hidabspathdird").value = abspath;
    }
    function setdelFile(uname){
      document.getElementById("delfileuname").value = uname;
    }
    function shareFileDatas(fname, abspath){
      document.getElementById("sharefname").value = fname;
      document.getElementById("sharepath").value = abspath;
      console.log(abspath);
    }
  </script>
  <script type="text/javascript">
    $(document).ready(function(){
      $('#saveFileButton').click(function(){
        var fname = $('#sbfname').val();
        var uname = $('#sbuname').val();
        var filenamealone = $('#sbfnalone').val();
        var contentsOfFile = $('#exampleFormControlTextarea1').val();
        $.ajax({
          type: 'GET', 
          async: true,
          data: {
            filename: fname,
            username: uname,
            filenamealone: filenamealone,
            contents: contentsOfFile
          },
          url: 'savefileajax',
          success: function(){
            document.getElementById('#exampleFormControlTextarea1').innerHTML = contents;
            console.log("success");
          //  $('#exampleFormControlTextarea1').html(contents);
          }
        });
      });
    });
  </script>
</head>
<body>
  <%
    
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);

  

    if(session.getAttribute("username")==null){
        out.println("null");
        response.sendRedirect("/");
  }
    else{ %>
    
	<div class="container">
		<center><h2>Directory Management</h2></center>
    
    <div class="headerdata">
    <form action="logout" method="get">
        <button type="submit" class="btn btn-primary">Logout</button>
    </form>
    
    </div>    
		<div class="row">
      
        
      <div class="col-sm-3 col-md-6 col-lg-4">
       <% Object uname = request.getSession().getAttribute("username");%>
       <h3><%= uname.toString()%></h3>
       <h3>Directory</h3>
       <button type="button" class="btn btn-primary btn-sm"  onclick="setdataDir('<%=uname.toString()%>', 'none', 'none')"  data-toggle="modal"  data-target="#createDirectoryModal"> <span class="glyphicon glyphicon-folder-open"></span></button>
            <button type="button" class="btn btn-primary btn-sm" onclick="setdataFile('<%=uname.toString()%>','none', 'none')"data-toggle="modal"  data-target="#createFileModal"><span class="glyphicon glyphicon-open-file"></span></button>
            
			 <% Example obj = new Example();
			  
			 ArrayList<File> direc = Example.getDir(uname.toString());
			 Iterator itr = direc.iterator();  %>
			 <ul class="list-group">
     <%  while(itr.hasNext()){  
				  File f = (File)itr.next(); %>
        
          <%! String abspath = ""; %>
           <%   abspath = f.getAbsolutePath(); 
              
          %>
           
      <%   if(f.isDirectory()) {%>
          <li><% out.println(f.getName()); %>            
          <%  if( (!f.getName().equals("received-files")) && (!f.getName().equals("logs")) ){%>

          
            <button type="button" class="btn btn-primary btn-sm"  onclick="setdataDir('<%=uname.toString()%>','<%=f.getName()%>', '<%=Example.escapePath(abspath)%>')"  data-toggle="modal"  data-target="#createDirectoryModal"> <span class="glyphicon glyphicon-folder-open"></span></button>
            <button type="button" class="btn btn-primary btn-sm" onclick="setdataFile('<%=uname.toString()%>','<%=f.getName()%>', '<%=Example.escapePath(abspath)%>')"data-toggle="modal"  data-target="#createFileModal"><span class="glyphicon glyphicon-open-file"></span></button>
            
            <button type="button" class="btn btn-primary btn-sm btn-danger" onclick="setdelDir('<%=uname.toString()%>','<%=f.getName() %>', '<%=Example.escapePath(abspath)%>')" data-toggle="modal"  data-target="#deletedirectory"><span class="glyphicon glyphicon-trash"></span></button>
            <%}%>
          </li>
        <% } %>  <!-- end of if --> 

        <% if(!f.isDirectory()){  %>
            <div class="filedisplay">
            <form class="form-inline" action="accessfile" method="get" >
              <input type="hidden" name="username" value="<%=uname.toString()%>">
              <input type="hidden" name="file_name" value="<%=f.getName()%>">
              <input type="hidden" name="parent_name" value="<%=f.getParentFile().getName()%>">
              <input type="hidden" name="absolute_path" value="<%=f.getAbsolutePath()%>">
              <button type="submit"  class="btn btn-sm"> <%out.println("|");
                    out.println("-"+f.getName());  %> </button> 
    
            </form>
            <%if( !((f.getParentFile().getName()).equals("received-files")) ){%>
             <form class="form-inline" action="deletefile" method="get" >
              <input type="hidden" id="delfileuname" name="username" value="<%=uname.toString()%>">
              <input type="hidden" name="file_name" value="<%=f.getName()%>">
              <input type="hidden" name="parent_name" value="<%=f.getParentFile().getName()%>">
              <input type="hidden" name="absolute_path" value="<%=f.getAbsolutePath()%>">
              <button type="submit" class="btn btn-sm btn-danger" ><span class="glyphicon glyphicon-trash"></span></button>
            </form>
            <button class="btn btn-sm btn-info" onclick="shareFileDatas('<%=f.getName()%>','<%=Example.escapePath(abspath)%>')" data-toggle="modal"  data-target="#shareFile"><span class="glyphicon glyphicon-upload"></span>
            </button>
            <%}%>
            </form>
             <form class="form-inline" action="logofafile" method="get" >
              <input type="hidden" name="username" value="<%=uname.toString()%>">
              <input type="hidden" name="filename" value="<%=f.getName()%>">
              <input type="hidden" name="location" value="<%=f.getAbsolutePath()%>">
              <input type="hidden" name="parent_name" value="<%=f.getParentFile().getName()%>">
              <button type="submit" class="btn btn-sm btn-info" ><span class="glyphicon glyphicon-globe"></span></button>
            </form>
  
          </div>  <!-- end of filedisplay div -->



        <% } %>  <!-- end of if -->

        <% } %>  <!-- end of while -->
    </ul>
				 
  </div>

    <div class="col-sm-9 col-md-6 col-lg-8">
        <h3>Editor</h3>
          <form class="form-inline" action="" method="" >
              <input type="hidden" id="sbfname" name="file_name" value="<%=Example.getFileName()%>"  >
              <input type="hidden" id="sbuname" name="username" value="<%=uname.toString()%>"  >
              <input type="hidden" id="sbfnalone" name="file_name_alone" value="<%=Example.getFileNameAlone()%>">
              <textarea class="form-control rounded-0" id="exampleFormControlTextarea1" rows="10" cols="100" name="text"><%=Example.getFileContents() %></textarea>
              <button  class=" submit btn btn-primary btn-sm" id="saveFileButton">Save</button>
        </form>   
          
      </div>
				 
				 	
		</div>    <!-- row -->

                          <!-- create Directory -->
    <div class="modal fade" id="createDirectoryModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Create Directory</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
              <form class="form-inline" action="createDirectoryNew" method="get">
              <input type="hidden" id="hiduname" name="username" value="" >
              <input type="hidden" id="hidprevdirec" name="prev_directory_name" value="" >
              <input type="hidden" id="hidabspathdirc" name="absolute_path" value="">
              <div class="form-group">
                <input type="text" class="form-control"  placeholder="Directory name" name="directory_name" value="">
              </div>
              <button type="submit" class="btn btn-primary btn-sm">+</button>
          </form>
      </div>
    </div>
  </div>
</div>        <!-- create Directory -->

      <!-- create file Modal -->
<div class="modal fade" id="createFileModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Create File</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <form class="form-inline" action="createFileNew" method="get">
              <div class="form-group">
                <input type="hidden" id="hidunamefile" name="username" value = "">
                <input type="hidden" id="newfileParentName" name="directory_name" value = "">
                <input type="hidden" id="hidabspathfilec" name="absolute_path" value="">
                <input type="text" class="form-control"  placeholder="file name" name="file_name">

              </div>
              <button type="submit" class="btn btn-primary btn-sm">+</button>
      </form>         
      </div>
    </div>
  </div>
</div>        <!-- create file -->
              
              <!-- delete directory -->
      <div class="modal fade" id="deletedirectory" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Delete Directory</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
              <form class="form-inline"  action="deletedirectory" method="get" >
                <input type="hidden" id="deldiruname" name="username" value=""  >
                <input type="hidden" id="deldirname" name="directory_name" value=""  >
                <input type="hidden" id = "hidabspathdird" name="absolute_path" value="">
                <button type="submit" class="btn  btn-sm">Delete</button>
          </form>
      </div>
      
    </div>
  </div>
</div>  <!-- delete directory -->

                    <!-- share file -->

     <div class="modal fade" id="shareFile" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Share File</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
              <form class="form-inline" action="sharefile" method="get">
              <input type="hidden" id="shareuname" name="user_name" value="<%=uname.toString()%>" >
              <input type="hidden" id="sharefname" name="file_name" value="" >
              <input type="hidden" id="sharepath" name="file_path" value="">
              <div class="form-group">
                <label for="fileaccessmode">Access Mode</label>
                <select multiple class="form-control" id="fileaccessmode" name="access_mode">
                  <option value="readonly">Read only</option>
                  <option value="write">Read - Write</option>
                </select>
              </div>
              <div class="form-group">
                <label for="receiver">Receiver Username</label>
                <select multiple class="form-control" id="receiver" name="receiver_name">
                  <% ArrayList<String> usersList = UserManipulations.getUserNamesList();
                    for(int i=0; i<usersList.size(); i++){  %>
                      <% if(!usersList.get(i).equals(uname.toString())){ %>
                      <option value="<%=usersList.get(i) %>"><%=usersList.get(i)%></option>
                      <%}%>
                  <% }  %>
                  %>
                </select>
              </div>
              <button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-open-file"></span></button>
          </form>
      </div>
    </div>
  </div>
</div>


	</div>      <!-- container -->

  <%}%>
</body>
</html>