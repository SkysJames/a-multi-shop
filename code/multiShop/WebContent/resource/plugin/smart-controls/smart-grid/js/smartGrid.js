/**
 * Grid组件
 * 使用方法(示例：http://ip/smart/smart/business/example/grid/grid-remoteData.jsp)：
 * var config = {...};
 * $('#id').smartGrid(config);
 * 
 * config参数说明：
 * id :  table的Id（可选）
 * url : 如是ajax方式获取数据，为请求url（可选，如何ajax获取时必须）
 * params : 自定义请求参数（附带在request中）
 * remodeDataRoot : ajax方式获取数据，返回的结果属性名（可选，默认为：gridData）
 * data : 本地获取数据（可选，不能和url同时使用）
 * title : 标题（可选）
 * titleCss : 标题样式（可选）
 * width : 宽带（可选，例如：150、'100%'，建议不设置）
 * height : 高度（可选，例如：150、'100%'，建议不设置）
 * colWidthFitable : 是否强制自适应每列的宽度（可选，根据设置的每列宽度比例进行调整充满整个表格，如不设置列宽则默认为150，建议不设置或true）
 * autoExtendCol : 自动扩充列ID（可选，如设置后其他列按设置的显示，colWidthFitable为true时有效）
 * pageSize : 每页的数量（可选，例如：100，默认为100）
 * pageSizeList : 可选的每页数量（可选，例如：'100,200,300,500'，默认为：'100,200,300,500'）
 * multiLineable : 是否多行显示（可选，默认单行[仅翻页模式有效]）
 * rowNumberable : 是否显示序号（可选，默认不显示）
 * rowNumberWidth : 序号的宽度（可选，默认为50）
 * showPaginateable : 是否显示分页器（可选，默认为：true，仅对本地数据有效）
 * bufferViewable : 是否启用不翻页（可选，默认为：true；缓存大小为pageSize）
 * columns : [clumn1, clumn2, ...]（必须，详见《开发说明》）
 * hideColumn : 是否隐藏列头（可选，默认不隐藏）
 * multiselect : 是否显示多选框(true为显示)
 *
 * @author yuxh
 * @date 2013-11-20 17:56:16
 */


var SmartGrid = function(containerEl, grid, options) {
	this.containerEl = containerEl;
	this.grid = grid;
	this.options = options;
};
SmartGrid.prototype = {
	// 重新设置grid大小
	resizeGrid : function() {
		if (this.options.autoHeight) {
			var tableHeight = this.containerEl.height() || $(window).height();
			if (this.options.title) {
				tableHeight -= 40;
			}
			
			if (this.options.showPaginateable) {
				tableHeight -= 45;
			}
			
			if (true !== this.options.hideColumn) {
				tableHeight -= 42
			}
			this.grid.jqGrid('setGridHeight', tableHeight);
		}
		if (this.containerEl.width() > 0) {
			this.grid.jqGrid('setGridWidth', this.containerEl.width(), this.options.shrinkToFit);
		}
	},
	
	/**
	 * 重新加载
	 * @param config {} url：请求url(可选)、params：自定义请求参数(可选)
	 */
	reload : function(config) {
		if (config) {
			if (config.url) {
				this.grid.jqGrid('setGridParam', {url : config.url});
			}
			
			// 重新加载，翻到第一页
			this.grid.jqGrid('setGridParam', {page : 1});
			
			if (config.params) {
				delete config.params.params;
				config.params.params = $.toJSON(config.params);
				var postData = this.grid.jqGrid('getGridParam', 'postData');
				$.extend(postData, config.params);
			}
		}
		
		this.grid.trigger('reloadGrid');
	},
	
	/**
	 * 设置是否多行显示
	 */
	displayMultiLine : function(multiLineable) {
		// 仅支持翻页模式
		var scroll = this.grid.jqGrid('getGridParam', 'scroll');
		if (!scroll) {
			if (multiLineable) {
				this.grid.addClass('smartGrid-multiLine');
			} else {
				this.grid.removeClass('smartGrid-multiLine');
			}
		}
	},
	
	/**
	 * 增加行
	 * @param record 行数据（支持一个或数组多个）
	 * @param isAddFirst 是否添加在第一行，否则添加在最后一行 
	 */
	addRecord : function(record, isAddFirst) {
		if (record) {
			if (!$.isArray(record)) {
				record = [record];
			}
			
			for (var i = 0; i < record.length; i++) {
				this.grid.jqGrid('addRowData', undefined, record[i], (isAddFirst ? 'first' : 'last'));
			}
		}
	},
	
	/**
	 * 获取一行数据
	 */
	getRowData : function(rowId){
		return this.grid.jqGrid('getRowData',rowId);
	},
	
	/**
	 * 获取选中行的id(multiselect : true)    
	 */
	getSelectedIds : function(){
		return this.grid.jqGrid('getGridParam',"selarrrow");
	},
	
	/**
	 * 获取总数
	 * @returns
	 */
	getRowNum : function(){
		return this.grid.jqGrid('getGridParam','records');
	},
	
	/**
	 * 设置每页行数
	 */
	setRowNumPerPage : function(count){
		this.grid.jqGrid('setGridParam', {rowNum : count});
		this.grid.trigger('reloadGrid');
	},
	
	/**
	 * 获取分页信息
	 */
	getPageInfo : function() {
		// 当前页码(以1开始)
		var pageNo = this.grid.jqGrid('getGridParam', 'page');
		// 总页数
		var totalPageNo = this.grid.jqGrid('getGridParam', 'lastpage');
		// 总行数
		var totalRows = this.grid.jqGrid('getGridParam', 'records');
		
		var perPage = this.grid.jqGrid('getGridParam', 'rowNum');
		
		var pageInfo = {
			'pageNo' : pageNo,
			'totalPageNo' : totalPageNo,
			'totalRows' : totalRows,
			'perPage' : perPage
		};
		
		return pageInfo;
	},
	
	/*
	 * 翻页：首页
	 */
	pageFirst : function() {
		this.containerEl.find('.smartGrid-pg-first').click();
		
		return this.getPageInfo();
	},
	/*
	 * 翻页：上一页
	 */
	pagePrev : function() {
		this.containerEl.find('.smartGrid-pg-prev').click();
		
		return this.getPageInfo();
	},
	/*
	 * 翻页：下一页
	 */
	pageNext : function() {
		this.containerEl.find('.smartGrid-pg-next').click();
		
		return this.getPageInfo();
	},
	/*
	 * 翻页：末页
	 */
	pageLast : function() {
		this.containerEl.find('.smartGrid-pg-last').click();
		
		return this.getPageInfo();
	},
	
	/**
	 * 获取columns数据
	 * @returns
	 */
	getColModel : function(){
		return this.grid.jqGrid('getGridParam','colModel');
	},
	
	/**
	 * 获取当前表格的宽度
	 * @returns
	 */
	getCurrentTableWidth : function(){
		return this.grid.jqGrid('getGridParam','width');
	},
	
	/**
	 * 展开子行
	 * @param rowid
	 */
	expandSubGridRow : function(rowid){
		return this.grid.jqGrid('expandSubGridRow',rowid);
	},
	
	/**
	 * 关闭子行
	 * @param rowid
	 */
	collapseSubGridRow : function(rowid){
		return this.grid.jqGrid('collapseSubGridRow',rowid);
	},
	
	/**
	 * 打开和关闭子行
	 * @param rowid
	 */
	expandOrCollapseRow : function(rowid){
		var rc = this.grid.jqGrid("getInd",rowid,true);
		if(rc) {
			var sgc = $("td.sgcollapsed",rc)[0];
			if(sgc) {
				$(sgc).trigger("click");
			}else{
				sgc = $("td.sgexpanded",rc)[0];
				if(sgc) {
					$(sgc).trigger("click");
				}
			}
		}
	}
	


}

jQuery.fn.extend({
	smartGrid : function(baseConfig) {
		var containerEl = $(this);
		
		// 可选的每页数量
		var rowList;
		var pageSizeList = baseConfig.pageSizeList;
		if (pageSizeList) {
			rowList = pageSizeList.split(',');
		} else {
			rowList = [50, 100, 200, 300, 500, 1000, 3000, 5000];
		}
		
		// 列宽度是否自适应
		var shrinkToFit = true;
		
		/*************** 列信息 start ***************/
		var colModel = []; // 列信息
		
		var defaultSortConfigs = []; // 默认排序
		
		var columns = baseConfig.columns;
		if (columns) {
			for (var i = 0; i < columns.length; i++) {
				// 列信息
				colModel.push(
					{
						'name' : columns[i].id,
						'index' : columns[i].id,
						'label' : columns[i].header || '',
						'width' : columns[i].width || undefined,
						'align' : columns[i].align || 'left',
						'valign' : columns[i].valign,
						'classes' : columns[i].css || undefined,
						'sortable' : (undefined === columns[i].sortable) ? true : columns[i].sortable,
						'resizable' : (false !== columns[i].resizable),
						'hidden' : (true === columns[i].hidden),
						'formatter' : columns[i].renderer
					}
				);
				
				// 默认排序
				if (columns[i].defaultSort) {
					defaultSortConfigs.push([columns[i].id, columns[i].defaultSort]);
				}
				
				if (shrinkToFit && undefined !== columns[i].width) {
					shrinkToFit = false;
				}
			}
		}
		/*************** 列信息 end ***************/
		
		/*************** 是否自适应 start **************/
		if (undefined === baseConfig.colWidthFitable) {
			if (baseConfig.autoExtendCol) {
				shrinkToFit = true;
			}
		} else {
			shrinkToFit = !!baseConfig.colWidthFitable;
		}
		baseConfig.shrinkToFit = shrinkToFit;
		/*************** 是否自适应 end **************/
		
		/*************** 其他设置 start ***************/
		baseConfig.id = baseConfig.id || 'table_' + new Date().getTime();
		var pagerId = baseConfig.id + '_pager'; // 翻页栏
		
		baseConfig.showPaginateable = (false !== baseConfig.showPaginateable);
		baseConfig.remodeDataRoot = baseConfig.remodeDataRoot || 'gridData';
		/*************** 其他设置 end ***************/
		
		var gridConfig = {
			'url' : baseConfig.url,
			'postData' : baseConfig.params || {}, // 自定义请求参数
			'data' : baseConfig.data,
			'datatype' : (!!baseConfig.url ? 'json' : 'local'),
			'mtype' : 'POST',
			'colModel' : colModel, // 列信息
			//'width' : '100%', // 数字 & 'auto','100%'
			//'height' : 'auto', 
			'rowNum' : baseConfig.pageSize || 100, // 默认每页记录数
			'rowList' : rowList, // 更改显示记录数选项
			'pager' : pagerId, // 分页标签divID 
			'viewrecords' : true, // 显示记录数信息，如果这里设置为false,下面的不会显示 recordtext: "第{0}到{1}条, 共{2}条记录" scroll: false, //滚动翻页，设置为true时翻页栏将不显示
			'rownumbers' : !!baseConfig.rowNumberable, // 是否显示行号
			'rownumWidth' : baseConfig.rowNumberWidth || 50, // 设置显示序号的宽度
			'multiselect' : !!baseConfig.multiselect, // 多选框
			'multiboxonly' : (false !== baseConfig.multiboxonly), // 在点击表格row时只支持单选，只有当点击checkbox时才多选，需要multiselect=true是有效
			'altRows' : true,
			'autowidth' : !baseConfig.width, // 自动宽度
			'scroll' : !!baseConfig.bufferViewable, // 是否不翻页
			'shrinkToFit' : shrinkToFit, // 列宽度是否自适应
			'autoExtendCol' : baseConfig.autoExtendCol, // 自动扩充列ID（设置后，colWidthFitable自动改成true）
			'prmNames' : { // 如当前查询实体为ware，这些可以在查询对象的superObject中设定   
				'page' : 'pageNo',
				'rows' : 'pageSize',
				'sort' : 'sortName',
				'order' : 'sortType',
				'search' : 'grid_search',
				'nd' : 'grid_nd'
			},
			'jsonReader' : { // server返回Json解析设定   
				'root' : baseConfig.remodeDataRoot + '.data', // 对于json中数据列表   
				'page' : baseConfig.remodeDataRoot + '.pageNo', // 当前页
				'total' : baseConfig.remodeDataRoot + '.totalPageNo', // 总页数
				'records' : baseConfig.remodeDataRoot + '.totalRows', // 查询出的记录数
				'repeatitems' : false,
				'userdata' : baseConfig.remodeDataRoot + '.extraData' // 额外数据
			},
			'pagerpos' : 'right', // 翻页位置
			'recordpos' : 'left', // 页面信息位置
			'subGrid' : baseConfig.subGrid,
			'subGridRowExpanded' : baseConfig.subGridRowExpanded,
			'subGridRowColapsed' : baseConfig.subGridRowColapsed
		};
		
		// 默认排序
		if (defaultSortConfigs && defaultSortConfigs.length > 0) {
			gridConfig['sortname'] = defaultSortConfigs[0][0];
			gridConfig['sortorder'] = defaultSortConfigs[0][1];
		}
		
		// 标题
		if (baseConfig.title) {
			gridConfig['caption'] = baseConfig.title;
		}
		
		// 宽度
		if (baseConfig.width) {
			gridConfig['width'] = baseConfig.width;
		}
		
		// 高度
		if (baseConfig.height) {
			gridConfig['height'] = baseConfig.height;
		} else {
			var tableHeight = containerEl.height();
			
			// iframe自适应高度
			if (0 == tableHeight) {
				tableHeight = $(window).height();
				
				baseConfig.autoHeight = true;
			}
			
			if (baseConfig.title) {
				tableHeight -= 40;
			}
			
			if (baseConfig.showPaginateable) {
				tableHeight -= 45;
			}
			
			if (true !== baseConfig.hideColumn) {
				tableHeight -= 42
			}
			
			if (tableHeight > 0 ) {
				gridConfig['height'] = tableHeight;
			}
		}
		
		// 事件
		if (baseConfig.listeners) {
			var listeners = baseConfig.listeners;
			// 单击
			if (listeners.cellclick) {
				gridConfig['onCellSelect'] = function(rowid, iCol, cellcontent, e) {
					var colModel = $(this).jqGrid('getGridParam', 'colModel');
					var colName = colModel[iCol].name;
					
					if ('rn' == colName) {return;}
					
					var record = $(this).jqGrid('getRowData', rowid);
					var colValue = record[colName];
					
					baseConfig.listeners.cellclick(colName, colValue, record, e);
				}
			}
			
			//选中一行
			if(listeners.selectRow){
				gridConfig['onSelectRow'] = function(rowid, stat, e) {
					var record = $(this).jqGrid('getRowData', rowid);
					baseConfig.listeners.selectRow(rowid, stat,record, e);
				}
			}
			
			// 双击
			if (listeners.celldblclick) {
				gridConfig['ondblClickRow'] = function(rowid, iRow, iCol, e) {
					var colModel = $(this).jqGrid('getGridParam', 'colModel');
					var colName = colModel[iCol].name;
					
					if ('rn' == colName) {return;}
					
					var record = $(this).jqGrid('getRowData', rowid);
					var colValue = record[colName];
					
					baseConfig.listeners.celldblclick(colName, colValue, record, e);
				}
			}
			
			// 右键
			if (listeners.cellcontextmenu) {
				gridConfig['onRightClickRow'] = function(rowid, iRow, iCol, e) {
					var colModel = $(this).jqGrid('getGridParam', 'colModel');
					var colName = colModel[iCol].name;
					
					if ('rn' == colName) {return;}
					
					var record = $(this).jqGrid('getRowData', rowid);
					var colValue = record[colName];
					
					baseConfig.listeners.cellcontextmenu(colName, colValue, record, e);
				}
			}
			
			// 完成加载
			if (listeners.afterComplete) {
				gridConfig['gridComplete'] = listeners.afterComplete;
			}
			
			// 列宽度调整
			if (listeners.colresize) {
				gridConfig['resizeStop'] = function(newWidth, index) {
					var colModel = $(this).jqGrid('getGridParam', 'colModel');
					var colName = colModel[index].name;
					
					var colIndex = index;
					var rowNumberable = $(this).jqGrid('getGridParam', 'rownumbers');
					if (rowNumberable) {
						colIndex -= 1;
					}
					
					baseConfig.listeners.colresize(newWidth, colName, colIndex);
				}
			}
		}
		
		var tableHtml = '<table id="' + baseConfig.id + '"></table>';
		var pagerHtml = '<div id="' + pagerId + '"';
		
		if (!baseConfig.showPaginateable) {
			pagerHtml += ' style="display: none;"';
		}
		
		pagerHtml += '></div>';
		
		var tableEl = $(tableHtml);
		
		containerEl.append(tableEl);
		containerEl.append(pagerHtml);
		
		var oGrid = tableEl.jqGrid(gridConfig);
		
		var smartGridObj = new SmartGrid(containerEl, oGrid, baseConfig);
		smartGridObj.displayMultiLine(baseConfig.multiLineable);
		
		// 是否隐藏列头
		if (true === baseConfig.hideColumn) {
			oGrid.closest('.ui-jqgrid-view').find('.ui-jqgrid-hdiv').hide();
		}
		
		// 标题
		if (baseConfig.title && baseConfig.titleCss) {
			containerEl.find('.ui-jqgrid-titlebar').addClass(baseConfig.titleCss);
		}
		
		
		// 显示翻页按钮
		var replacement = {
			'ui-icon-seek-first' : 'fa fa-angle-double-left bigger-140',
			'ui-icon-seek-prev' : 'fa fa-angle-left bigger-140',
			'ui-icon-seek-next' : 'fa fa-angle-right bigger-140',
			'ui-icon-seek-end' : 'fa fa-angle-double-right bigger-140',
			'ui-icon-plus' : 'fa fa-plus'
		};
		containerEl.find('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
			var icon = $(this);
			var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
			
			if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
		})
		
		// 显示操作按钮
		if (baseConfig.showPaginateable) {
			$('#' + baseConfig.id).jqGrid('navGrid', '#' + pagerId,
				{ 	//navbar options
					edit: false,
					editicon : 'fa fa-pencil blue',
					add: false,
					addicon : 'fa fa-plus-sign purple',
					del: false,
					delicon : 'fa fa-trash red',
					search: false,
					searchicon : 'fa fa-search orange',
					refresh: true,
					refreshicon : 'fa fa-refresh green',
					view: false,
					viewicon : 'fa fa-search-plus grey'
				});
		}
		
		$(window).resize(function() {
			smartGridObj.resizeGrid();
		});
		
		return smartGridObj;
	}
});

$.jgrid.extend({
	setSubGrid : function () {
		return this.each(function (){
			var $t = this, cm, i,
			suboptions = {
				plusicon : "fa fa-plus fa-no-margin-right",
				minusicon : "fa fa-minus fa-no-margin-right",
				//openicon: "fa fa-arrows-v",
				expandOnLoad:  false,
				delayOnLoad : 50,
				selectOnExpand : false,
				selectOnCollapse : false,
				reloadOnExpand : true
			};
			$t.p.subGridOptions = $.extend(suboptions, $t.p.subGridOptions || {});
			$t.p.colNames.unshift("");
			$t.p.colModel.unshift({name:'subgrid',width: $.jgrid.cell_width ?  $t.p.subGridWidth+$t.p.cellLayout : $t.p.subGridWidth,sortable: false,resizable:false,hidedlg:true,search:false,fixed:true});
			cm = $t.p.subGridModel;
			if(cm[0]) {
				cm[0].align = $.extend([],cm[0].align || []);
				for(i=0;i<cm[0].name.length;i++) { cm[0].align[i] = cm[0].align[i] || 'left';}
			}
		});
	}
});
