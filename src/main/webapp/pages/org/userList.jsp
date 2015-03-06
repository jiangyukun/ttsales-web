<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>人员列表</title>
<jsp:include page="/pages/common/resources.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/resources/template_admin/assets/scripts/custom/table-managed.js"></script>
<script>
	jQuery(document).ready(function() {
		TableManaged.init();
	});
</script>
</head>
<body>
	<div class="portlet box light-grey">
		<div class="portlet-title">
			<div class="caption">
				<i class="fa fa-user"></i>人员列表
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"> </a> <a
					href="javascript:;" class="reload"> </a> <a href="javascript:;"
					class="remove"> </a>
			</div>
		</div>
		<div class="portlet-body">
			<div class="table-toolbar">
				<div class="btn-group">
					<button id="sample_editable_1_new" class="btn green">
						Add New <i class="fa fa-plus"></i>
					</button>
				</div>
				<div class="btn-group pull-right">
					<button class="btn dropdown-toggle" data-toggle="dropdown">
						人员操作 <i class="fa fa-angle-down"></i>
					</button>
					<ul class="dropdown-menu pull-right">
						<li><a href="#"> 添加人员 </a></li>
						<li><a href="#"> 批量审核 </a></li>
						<li><a href="#"> 批量删除 </a></li>
						<li><a href="#"> 批量邀请 </a></li>
					</ul>
				</div>
			</div>
			<table class="table table-striped table-bordered table-hover"
				id="sample_1">
				<thead>
					<tr>
						<th class="table-checkbox"><input type="checkbox"
							class="group-checkable" data-set="#sample_1 .checkboxes" /></th>
						<th>Username</th>
						<th>Email</th>
						<th>Points</th>
						<th>Joined</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>shuxer</td>
						<td><a href="mailto:shuxer@gmail.com"> shuxer@gmail.com </a>
						</td>
						<td>120</td>
						<td class="center">12 Jan 2012</td>
						<td><span class="label label-sm label-success">
								Approved </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>looper</td>
						<td><a href="mailto:looper90@gmail.com">
								looper90@gmail.com </a></td>
						<td>120</td>
						<td class="center">12.12.2011</td>
						<td><span class="label label-sm label-warning">
								Suspended </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>userwow</td>
						<td><a href="mailto:userwow@yahoo.com"> userwow@yahoo.com
						</a></td>
						<td>20</td>
						<td class="center">12.12.2012</td>
						<td><span class="label label-sm label-success">
								Approved </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>user1wow</td>
						<td><a href="mailto:userwow@gmail.com"> userwow@gmail.com
						</a></td>
						<td>20</td>
						<td class="center">12.12.2012</td>
						<td><span class="label label-sm label-default">
								Blocked </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>restest</td>
						<td><a href="mailto:userwow@gmail.com"> test@gmail.com </a></td>
						<td>20</td>
						<td class="center">12.12.2012</td>
						<td><span class="label label-sm label-success">
								Approved </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>foopl</td>
						<td><a href="mailto:userwow@gmail.com"> good@gmail.com </a></td>
						<td>20</td>
						<td class="center">19.11.2010</td>
						<td><span class="label label-sm label-success">
								Approved </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>weep</td>
						<td><a href="mailto:userwow@gmail.com"> good@gmail.com </a></td>
						<td>20</td>
						<td class="center">19.11.2010</td>
						<td><span class="label label-sm label-success">
								Approved </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>coop</td>
						<td><a href="mailto:userwow@gmail.com"> good@gmail.com </a></td>
						<td>20</td>
						<td class="center">19.11.2010</td>
						<td><span class="label label-sm label-success">
								Approved </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>pppol</td>
						<td><a href="mailto:userwow@gmail.com"> good@gmail.com </a></td>
						<td>20</td>
						<td class="center">19.11.2010</td>
						<td><span class="label label-sm label-success">
								Approved </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>test</td>
						<td><a href="mailto:userwow@gmail.com"> good@gmail.com </a></td>
						<td>20</td>
						<td class="center">19.11.2010</td>
						<td><span class="label label-sm label-success">
								Approved </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>userwow</td>
						<td><a href="mailto:userwow@gmail.com"> userwow@gmail.com
						</a></td>
						<td>20</td>
						<td class="center">12.12.2012</td>
						<td><span class="label label-sm label-default">
								Blocked </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>test</td>
						<td><a href="mailto:userwow@gmail.com"> test@gmail.com </a></td>
						<td>20</td>
						<td class="center">12.12.2012</td>
						<td><span class="label label-sm label-success">
								Approved </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>goop</td>
						<td><a href="mailto:userwow@gmail.com"> good@gmail.com </a></td>
						<td>20</td>
						<td class="center">12.11.2010</td>
						<td><span class="label label-sm label-success">
								Approved </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>weep</td>
						<td><a href="mailto:userwow@gmail.com"> good@gmail.com </a></td>
						<td>20</td>
						<td class="center">15.11.2011</td>
						<td><span class="label label-sm label-default">
								Blocked </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>toopl</td>
						<td><a href="mailto:userwow@gmail.com"> good@gmail.com </a></td>
						<td>20</td>
						<td class="center">16.11.2010</td>
						<td><span class="label label-sm label-success">
								Approved </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>userwow</td>
						<td><a href="mailto:userwow@gmail.com"> userwow@gmail.com
						</a></td>
						<td>20</td>
						<td class="center">9.12.2012</td>
						<td><span class="label label-sm label-default">
								Blocked </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>tes21t</td>
						<td><a href="mailto:userwow@gmail.com"> test@gmail.com </a></td>
						<td>20</td>
						<td class="center">14.12.2012</td>
						<td><span class="label label-sm label-success">
								Approved </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>fop</td>
						<td><a href="mailto:userwow@gmail.com"> good@gmail.com </a></td>
						<td>20</td>
						<td class="center">13.11.2010</td>
						<td><span class="label label-sm label-warning">
								Suspended </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>kop</td>
						<td><a href="mailto:userwow@gmail.com"> good@gmail.com </a></td>
						<td>20</td>
						<td class="center">17.11.2010</td>
						<td><span class="label label-sm label-success">
								Approved </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>vopl</td>
						<td><a href="mailto:userwow@gmail.com"> good@gmail.com </a></td>
						<td>20</td>
						<td class="center">19.11.2010</td>
						<td><span class="label label-sm label-success">
								Approved </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>userwow</td>
						<td><a href="mailto:userwow@gmail.com"> userwow@gmail.com
						</a></td>
						<td>20</td>
						<td class="center">12.12.2012</td>
						<td><span class="label label-sm label-default">
								Blocked </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>wap</td>
						<td><a href="mailto:userwow@gmail.com"> test@gmail.com </a></td>
						<td>20</td>
						<td class="center">12.12.2012</td>
						<td><span class="label label-sm label-success">
								Approved </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>test</td>
						<td><a href="mailto:userwow@gmail.com"> good@gmail.com </a></td>
						<td>20</td>
						<td class="center">19.12.2010</td>
						<td><span class="label label-sm label-success">
								Approved </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>toop</td>
						<td><a href="mailto:userwow@gmail.com"> good@gmail.com </a></td>
						<td>20</td>
						<td class="center">17.12.2010</td>
						<td><span class="label label-sm label-success">
								Approved </span></td>
					</tr>
					<tr class="odd gradeX">
						<td><input type="checkbox" class="checkboxes" value="1" /></td>
						<td>weep</td>
						<td><a href="mailto:userwow@gmail.com"> good@gmail.com </a></td>
						<td>20</td>
						<td class="center">15.11.2011</td>
						<td><span class="label label-sm label-success">
								Approved </span></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>