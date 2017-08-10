<!DOCTYPE HTML>
 <head>
     <base href="<%=basePath%>">
     <title>IJ-charge</title>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

     <link rel="stylesheet" type="text/css" href="../static/css/bootstrap.css">
     <link rel="stylesheet" type="text/css" href="../static/css/bootstrap.css.map">
     <link rel="stylesheet" type="text/css" href="../static/css/bootstrap.min.css">
     <link rel="stylesheet" type="text/css" href="../static/css/bootstrap-theme.css">
     <link rel="stylesheet" type="text/css" href="../static/css/bootstrap-theme.css.map">
     <link rel="stylesheet" type="text/css" href="../static/css/bootstrap-theme.min.css">

     <style type="text/css">

         a:link {
             color: #0f0f0f;
             text-decoration: none;
         }

     </style>

     <script src="../static/js/jquery.min.js"></script>
     <script src="../static/js/vue.js"></script>
     <script src="../static/js/bootstrap.min.js"></script>
     <script src="../static/js/bootstrap.js"></script>
     <script src="../static/js/jquery.cookie.js"></script>
     <script src="../static/js/monitor.js"></script>
     <script src="../static/js/npm.js"></script>
     <script type="text/javascript" src="../static/js/shortcut.js"></script>
     <script type="text/javascript" src="../static/js/lhgdialog/lhgdialog.min.js?self=true&skin=chrome"></script>

     <script>


     </script>


 </head>

 <body >
 <!-- 头部查询条件 -->
 <div class="matter">
     <form action="<%=path%>/gontext/topage.do" class="form-horizontal" method="post" name="frm" id="frm">
         <div class="widget">
             <div class="widget-head">
                 <div class="pull-left">收费确认和退款</div>
                 <div class="clearfix"></div>
             </div>
             <div class="widget-content">

                 <div class="padd">
                     <table class="ie_style table-condensed">
                         <tr>
                             <td style="padding-left: 50px;"><label>辅收订单号:</label></td>
                             <td>
                                 <input type="text" id="orderNum" name="orderNum" class="form-control"
                                         placeholder="例:12346789">
                             </td>
                             <td style="padding-left: 50px;"><label>航班号:</label></td>
                             <td>
                                 <input type="text" id="fi" name="fi" class="form-control"
                                         placeholder="例:9C8573">
                             </td>


                             <td><input id="C" type="" value="C 取消" class="btn btn-primary"/></td>
                         </tr>

                         <tr>
                             <td style="padding-left: 50px;"><label>姓名:</label></td>
                             <td>
                                 <input type="text" id="name" name="name" class="form-control"
                                         placeholder="例:ZHANGSAN">
                             </td>
                             <td style="padding-left: 50px;"><label>证件号:</label></td>
                             <td>
                                 <input type="text" id="licenseNo" name="licenseNo" class="form-control"
                                         placeholder="例:8008208820">
                             </td>


                             <td><input id="Y" type="" value="Y 确认" class="btn btn-primary"/></td>
                         </tr>

                         <tr>
                             <td style="padding-left: 50px;"><label>数量合计:</label></td>
                             <td>
                                 <input type="text" id="oa" name="oa" class="form-control"/>
                             </td>
                             <td style="padding-left: 50px;"><label>应收账款:</label></td>
                             <td>
                                 <input type="text" id="da" name="da" class="form-control"
                                        placeholder="例:2000">
                             </td>


                             <td><input id="D" type="" value="D 退款" class="btn btn-primary"/></td>
                         </tr>

                         <tr>
                             <td style="padding-left: 50px;"><label>实收账款:</label></td>
                             <td>
                                 <input type="text" id="oa" name="oa" class="form-control"
                                        maxlength="3" placeholder="例:2000">
                             </td>
                             <td style="padding-left: 50px;"><label>订单状态:</label></td>
                             <td>
                                 <input type="text" id="da" name="da" class="form-control" maxlength="3"
                                         placeholder="例:N">
                             </td>


                             <td style="padding-left: 50px;"><label>付款方式:</label></td>
                             <td>

                             </td>
                         </tr>


                     </table>

                 </div>
             </div>
         </div>
 </div>
 </body>
 </html>