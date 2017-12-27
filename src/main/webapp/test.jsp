<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="static/css/bootstrap.css">
	<!-- 引入JQuery  bootstrap.js-->
	<script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="static/js/bootstrap.js"></script>
	<link href="static/js/plugins/treeview/bootstrap-treeview.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="static/js/plugins/treeview/bootstrap-treeview.js"></script>
<script type="text/javascript">
$(function(){
	var defaultData = [ 
	                       { 
	                           text: 'Parent 1', 
	                           href: '#parent1', 
	                           tags: ['4'], 
	                           nodes: [ 
	                               { 
	                                   text: 'Child 1', 
	                                   href: '#child1', 
	                                   tags: ['2'], 
	                   					state:{
	                   						checked:true	
	                   					},
	                                   nodes: [ 
	                                       { 
	                                           text: 'Grandchild 1', 
	                                           href: '#grandchild1', 
	                                           tags: ['0'] 
	                                       }, 
	                                       { 
	                                           text: 'Grandchild 2', 
	                                           href: '#grandchild2', 
	                                           tags: ['0'] 
	                                       } 
	                                   ] 
	                               }, 
	                               { 
	                                   text: 'Child 2', 
	                                   href: '#child2', 
	                                   tags: ['0'] 
	                               } 
	                           ] 
	                       }, 
	                       { 
	                           text: 'Parent 2', 
	                           href: '#parent2', 
	                           tags: ['0'] 
	                       }, 
	                       { 
	                           text: 'Parent 3', 
	                           href: '#parent3', 
	                           tags: ['0'] 
	                       }, 
	                       { 
	                           text: 'Parent 4', 
	                           href: '#parent4', 
	                           tags: ['0'] 
	                       }, 
	                       { 
	                           text: 'Parent 5', 
	                           href: '#parent5', 
	                           tags: ['0'] 
	                       } 
	                   ];
	                   $('#treeview1').treeview({ 
	                       data: defaultData ,
	                   showCheckbox: true,
	                   highlightSelected: true,    //是否高亮选中
	                    //nodeIcon: 'glyphicon glyphicon-user',    //节点上的图标
	                    nodeIcon: 'glyphicon glyphicon-globe',
	                    emptyIcon: '',    //没有子节点的节点图标

	                   });
})

</script>
</head>
<body>
<div id="treeview1"></div>
</body>
</html>