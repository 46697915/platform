<!DOCTYPE html>
<html>
<head>
<meta charset=utf-8 />
<title>JS Bin</title>
<script class="jsbin" src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">
<script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
<!--[if IE]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<style>
  article, aside, figure, footer, header, hgroup, 
  menu, nav, section { display: block; }
  .west{
    width:200px;
    padding:10px;
  }
  .north{
    height:100px;
  }
</style>
</head>
<body class="easyui-layout">
  <div region="north" class="north" title="____′↘夏悸  http://easyui.btboys.com">
    <h1>最简单的左右结构实现，及tab的右键菜单实现，右键查看源码</h1>
  </div>
  <div region="center" title="center">
    <div class="easyui-tabs" fit="true" border="false" id="tabs">
      <div title="首页"></div>
    </div>
  </div>
  <div region="west" class="west" title="menu">
    <ul id="tree"></ul>
  </div>
  
  <div id="tabsMenu" class="easyui-menu" style="width:120px;">  
    <div name="close">关闭</div>  
    <div name="Other">关闭其他</div>  
    <div name="All">关闭所有</div>
  </div>  
</body>
</html>