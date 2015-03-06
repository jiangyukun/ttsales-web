function initTable() {
	$('#scheme_table').DataTable().destroy();
	$('#scheme_table')
			.DataTable(
					{
						"ordering" : true,// 排序
						"info" : true,// 左下显示条数/总数
						"paging" : true, // 是否显示分页器
						"filter" : false, // 是否启用客户端过滤器
						"lengthChange" : false, // 是否显示每页大小的下拉框
						"ajax" : $.sas.common.getRootPath()
								+ "/sys/schemedispense/getProProductSchemes.do?title=&summary=&state=",
						"language" : $.sas.common.getBootStrapTableLanguage(), // 多语言配置
						"columns" : [
								{
									"data" : function(source) {
										return "<input type='checkbox' name='select_scheme_id' value='"
												+ source.schemeId + "'  />";
									}
								},
								{
									"data" : "title"
								},
								{
									"data" : function(source) {
										return source.isRequireStore == '1' ? '是'
												: '否';
									}
								},
								{
									"data" : function(source) {
										return source.startTime
												.substring(0, 10);
									}
								}, {
									"data" : function(source) {
										return source.endTime.substring(0, 10);
									}
								}, {
									"data" : function(source) {
										if (source.state == '01') {
											return '推广中';
										} else if (source.state == '02') {
											return '推广结束';
										} else {
											return '已删除';
										}
									}
								} ]
					});
	jQuery('#scheme_table .group-checkable').change(function() {
		var checked = jQuery(this).is(":checked");
		var checkboxs = $("[name='select_scheme_id']");

		for (var s = 0; s < checkboxs.length; s++) {
			$(checkboxs[s]).attr('checked', checked);
		}
		jQuery.uniform.update(checkboxs);
	});
}