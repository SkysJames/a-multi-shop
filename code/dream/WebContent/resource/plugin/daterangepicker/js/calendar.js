(function() {
  var $, Calendar, DAYS, DateRangePicker, MONTHS, TEMPLATE;

  $ = jQuery;

  DAYS = ['日', '一', '二', '三', '四', '五', '六'];

  MONTHS = ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'];

  TEMPLATE = "<div class=\"drp-popup\">\n  <div class=\"drp-timeline\">\n    <ul class=\"drp-timeline-presets\"></ul>\n    <div class=\"drp-timeline-bar\"></div>\n  </div>\n  <div class=\"drp-calendars\">\n    <div class=\"drp-calendar drp-calendar-start\">\n      <div class=\"drp-month-picker\">\n        <div class=\"drp-arrow\"><</div>\n        <div class=\"drp-month-title\"></div>\n        <div class=\"drp-arrow drp-arrow-right\">></div>\n      </div>\n      <ul class=\"drp-day-headers\"></ul>\n      <ul class=\"drp-days\"></ul>\n   <div class=\"calendar-time-left\">时 分 秒 <select class=\"hourselect\"><option value=\"00\" selected=\"selected\">00</option><option value=\"01\">01</option><option value=\"02\">02</option><option value=\"03\">03</option><option value=\"04\">04</option><option value=\"05\">05</option><option value=\"06\">06</option><option value=\"07\">07</option><option value=\"08\">08</option><option value=\"09\">09</option><option value=\"10\">10</option><option value=\"11\">11</option><option value=\"12\">12</option><option value=\"13\">13</option><option value=\"14\">14</option><option value=\"15\">15</option><option value=\"16\">16</option><option value=\"17\">17</option><option value=\"18\">18</option><option value=\"19\">19</option><option value=\"20\">20</option><option value=\"21\">21</option><option value=\"22\">22</option><option value=\"23\">23</option></select> : <select class=\"minuteselect\"><option value=\"00\" selected=\"selected\">00</option><option value=\"01\">01</option><option value=\"02\">02</option><option value=\"03\">03</option><option value=\"04\">04</option><option value=\"05\">05</option><option value=\"06\">06</option><option value=\"07\">07</option><option value=\"08\">08</option><option value=\"09\">09</option><option value=\"10\">10</option><option value=\"11\">11</option><option value=\"12\">12</option><option value=\"13\">13</option><option value=\"14\">14</option><option value=\"15\">15</option><option value=\"16\">16</option><option value=\"17\">17</option><option value=\"18\">18</option><option value=\"19\">19</option><option value=\"20\">20</option><option value=\"21\">21</option><option value=\"22\">22</option><option value=\"23\">23</option><option value=\"24\">24</option><option value=\"25\">25</option><option value=\"26\">26</option><option value=\"27\">27</option><option value=\"28\">28</option><option value=\"29\">29</option><option value=\"30\">30</option><option value=\"31\">31</option><option value=\"32\">32</option><option value=\"33\">33</option><option value=\"34\">34</option><option value=\"35\">35</option><option value=\"36\">36</option><option value=\"37\">37</option><option value=\"38\">38</option><option value=\"39\">39</option><option value=\"40\">40</option><option value=\"41\">41</option><option value=\"42\">42</option><option value=\"43\">43</option><option value=\"44\">44</option><option value=\"45\">45</option><option value=\"46\">46</option><option value=\"47\">47</option><option value=\"48\">48</option><option value=\"49\">49</option><option value=\"50\">50</option><option value=\"51\">51</option><option value=\"52\">52</option><option value=\"53\">53</option><option value=\"54\">54</option><option value=\"55\">55</option><option value=\"56\">56</option><option value=\"57\">57</option><option value=\"58\">58</option><option value=\"59\">59</option></select> : <select class=\"secondselect\"><option value=\"00\">00</option><option value=\"01\">01</option><option value=\"02\">02</option><option value=\"03\">03</option><option value=\"04\">04</option><option value=\"05\" >05</option><option value=\"06\">06</option><option value=\"07\">07</option><option value=\"08\">08</option><option value=\"09\">09</option><option value=\"10\">10</option><option value=\"11\">11</option><option value=\"12\">12</option><option value=\"13\">13</option><option value=\"14\">14</option><option value=\"15\">15</option><option value=\"16\">16</option><option value=\"17\">17</option><option value=\"18\">18</option><option value=\"19\">19</option><option value=\"20\">20</option><option value=\"21\">21</option><option value=\"22\">22</option><option value=\"23\">23</option><option value=\"24\">24</option><option value=\"25\">25</option><option value=\"26\">26</option><option value=\"27\">27</option><option value=\"28\">28</option><option value=\"29\">29</option><option value=\"30\">30</option><option value=\"31\">31</option><option value=\"32\">32</option><option value=\"33\">33</option><option value=\"34\">34</option><option value=\"35\">35</option><option value=\"36\">36</option><option value=\"37\">37</option><option value=\"38\">38</option><option value=\"39\">39</option><option value=\"40\">40</option><option value=\"41\">41</option><option value=\"42\">42</option><option value=\"43\">43</option><option value=\"44\">44</option><option value=\"45\">45</option><option value=\"46\">46</option><option value=\"47\">47</option><option value=\"48\">48</option><option value=\"49\">49</option><option value=\"50\">50</option><option value=\"51\">51</option><option value=\"52\">52</option><option value=\"53\">53</option><option value=\"54\">54</option><option value=\"55\">55</option><option value=\"56\">56</option><option value=\"57\">57</option><option value=\"58\">58</option><option value=\"59\">59</option></select> </div>\n   <div class=\"drp-calendar-date\"></div>\n <div class=\"drp-calendar-date-ok\"><span class='btn btn-primary btn-md btn-block'>确定</span></div>\n   </div>\n    <div class=\"drp-calendar-separator\"></div>\n    <div class=\"drp-calendar drp-calendar-end\">\n      <div class=\"drp-month-picker\">\n        <div class=\"drp-arrow\"><</div>\n        <div class=\"drp-month-title\"></div>\n        <div class=\"drp-arrow drp-arrow-right\">></div>\n      </div>\n      <ul class=\"drp-day-headers\"></ul>\n      <ul class=\"drp-days\"></ul>\n  <div class=\"calendar-time-right\">时 分 秒 <select class=\"hourselect\"><option value=\"00\" >00</option><option value=\"01\">01</option><option value=\"02\">02</option><option value=\"03\">03</option><option value=\"04\">04</option><option value=\"05\">05</option><option value=\"06\">06</option><option value=\"07\">07</option><option value=\"08\">08</option><option value=\"09\">09</option><option value=\"10\">10</option><option value=\"11\">11</option><option value=\"12\">12</option><option value=\"13\">13</option><option value=\"14\">14</option><option value=\"15\">15</option><option value=\"16\">16</option><option value=\"17\">17</option><option value=\"18\">18</option><option value=\"19\">19</option><option value=\"20\">20</option><option value=\"21\">21</option><option value=\"22\">22</option><option value=\"23\" selected=\"selected\">23</option></select> : <select class=\"minuteselect\"><option value=\"00\" >00</option><option value=\"01\">01</option><option value=\"02\">02</option><option value=\"03\">03</option><option value=\"04\">04</option><option value=\"05\">05</option><option value=\"06\">06</option><option value=\"07\">07</option><option value=\"08\">08</option><option value=\"09\">09</option><option value=\"10\">10</option><option value=\"11\">11</option><option value=\"12\">12</option><option value=\"13\">13</option><option value=\"14\">14</option><option value=\"15\">15</option><option value=\"16\">16</option><option value=\"17\">17</option><option value=\"18\">18</option><option value=\"19\">19</option><option value=\"20\">20</option><option value=\"21\">21</option><option value=\"22\">22</option><option value=\"23\">23</option><option value=\"24\">24</option><option value=\"25\">25</option><option value=\"26\">26</option><option value=\"27\">27</option><option value=\"28\">28</option><option value=\"29\">29</option><option value=\"30\">30</option><option value=\"31\">31</option><option value=\"32\">32</option><option value=\"33\">33</option><option value=\"34\">34</option><option value=\"35\">35</option><option value=\"36\">36</option><option value=\"37\">37</option><option value=\"38\">38</option><option value=\"39\">39</option><option value=\"40\">40</option><option value=\"41\">41</option><option value=\"42\">42</option><option value=\"43\">43</option><option value=\"44\">44</option><option value=\"45\">45</option><option value=\"46\">46</option><option value=\"47\">47</option><option value=\"48\">48</option><option value=\"49\">49</option><option value=\"50\">50</option><option value=\"51\">51</option><option value=\"52\">52</option><option value=\"53\">53</option><option value=\"54\">54</option><option value=\"55\">55</option><option value=\"56\">56</option><option value=\"57\">57</option><option value=\"58\">58</option><option value=\"59\" selected=\"selected\">59</option></select> : <select class=\"secondselect\"><option value=\"00\">00</option><option value=\"01\">01</option><option value=\"02\">02</option><option value=\"03\">03</option><option value=\"04\">04</option><option value=\"05\" >05</option><option value=\"06\">06</option><option value=\"07\">07</option><option value=\"08\">08</option><option value=\"09\">09</option><option value=\"10\">10</option><option value=\"11\">11</option><option value=\"12\">12</option><option value=\"13\">13</option><option value=\"14\">14</option><option value=\"15\">15</option><option value=\"16\">16</option><option value=\"17\">17</option><option value=\"18\">18</option><option value=\"19\">19</option><option value=\"20\">20</option><option value=\"21\">21</option><option value=\"22\">22</option><option value=\"23\">23</option><option value=\"24\">24</option><option value=\"25\">25</option><option value=\"26\">26</option><option value=\"27\">27</option><option value=\"28\">28</option><option value=\"29\">29</option><option value=\"30\">30</option><option value=\"31\">31</option><option value=\"32\">32</option><option value=\"33\">33</option><option value=\"34\">34</option><option value=\"35\">35</option><option value=\"36\">36</option><option value=\"37\">37</option><option value=\"38\">38</option><option value=\"39\">39</option><option value=\"40\">40</option><option value=\"41\">41</option><option value=\"42\">42</option><option value=\"43\">43</option><option value=\"44\">44</option><option value=\"45\">45</option><option value=\"46\">46</option><option value=\"47\">47</option><option value=\"48\">48</option><option value=\"49\">49</option><option value=\"50\">50</option><option value=\"51\">51</option><option value=\"52\">52</option><option value=\"53\">53</option><option value=\"54\">54</option><option value=\"55\">55</option><option value=\"56\">56</option><option value=\"57\">57</option><option value=\"58\">58</option><option value=\"59\" selected=\"selected\">59</option></select> </div>    <div class=\"drp-calendar-date\"></div>\n <div class=\"drp-calendar-date-cancel\"><span> </span></div>\n    </div>\n  </div>\n  <div class=\"drp-tip\"></div>\n</div>";

  DateRangePicker = (function(dataset) {
	 var selectTimes = [
		{title:'今天',value:'1',selected:false},
		{title:'最近2天',value:'2',selected:false},
		{title:'最近3天',value:'3',selected:false},
		{title:'最近7天',value:'7',selected:false},
		{title:'最近15天',value:'15',selected:false},
		{title:'最近30天',value:'30',selected:false},
		{title:'自定义',value:'custom',selected:false}
	];
    function DateRangePicker($select) {
		this.isShowTimePicker = false;
		this.initParams($select);
		this.$select = $select;
		this.$dateRangePicker = $(TEMPLATE);
		//this.$select.attr('tabindex', '-1').before(this.$dateRangePicker);
		$('body').append(this.$dateRangePicker);
//		$select.parent().append(this.$dateRangePicker);
		this.isHidden = false;
		this.customOptionIndex = selectTimes.length - 1;
		
		var currenItem = null;
		if(typeof $select.attr('init-index') =='undefined'){
			currenItem = this.initSelected(0);
		}else{
			currenItem = this.initSelected($select.attr('init-index'));
		}
		if(null!=currenItem&&currenItem.value!= 'custom'){
			this.setRange(parseInt(currenItem.value));
		}else{
			this.setRange(1);
		}
		this.initBindings();
		this.initTimePicker();
		this.initPosition();
		this.hide();
		
    }
	
    DateRangePicker.prototype.initBindings = function() {
      var self;
      self = this;
      this.$select.on('keydown',function(e){
//    	  console.log(e);
    	 if(e.charCode != 13){
    		 return false;
    	 }else{
    		 return true;
    	 }
      });
      this.$select.on('focus mousedown', function(e) {
        var $select;
        $select = this;
        setTimeout(function() {
          return $select.blur();
        }, 0);
        return false;
      });
      this.$dateRangePicker.click(function(evt) {
        return evt.stopPropagation();
      });
      $('body').click(function(evt) {
        if (evt.target === self.$select[0] && self.isHidden) {
          return self.show();
        } else if (!self.isHidden) {
          return self.hide();
        }
      });
	  $.each(selectTimes,function(i, item){
		self.$dateRangePicker.find('.drp-timeline-presets').append($("<li class='" + ((item.selected && 'drp-selected') || '') + "'>" + (item.title) + "<div class='drp-button'></div></li>"));
	  });
      return this.$dateRangePicker.find('.drp-timeline-presets li').click(function(evt) {
        var presetIndex;
        $(this).addClass('drp-selected').siblings().removeClass('drp-selected');
        presetIndex = $(this).index();
		self.$select.attr('init-index',presetIndex);			
        self.setRange(self.initSelected(presetIndex).value);
        if (presetIndex === self.customOptionIndex) {
          return self.showCustomDate();
        }else{
			self.$select.attr("value",self.$dateRangePicker.find('.drp-calendar:first-child').find('.drp-calendar-date').text()+" - "+self.$dateRangePicker.find('.drp-calendar:last-child').find('.drp-calendar-date').text())
			self.hide();
		}
//        self.$select.focus();
      });
    };
	
	DateRangePicker.prototype.initParams = function($select){
		if(typeof $select.attr('showTimePicker')!="undefined"&&
			($select.attr('showTimePicker')=='true'||$select.attr('showTimePicker')==true
				||$select.attr('showTimePicker')=='false'||$select.attr('showTimePicker')==false)){
			if($select.attr('showTimePicker')=='true'||$select.attr('showTimePicker')==true){
				this.isShowTimePicker = true;
			}else if($select.attr('showTimePicker')=='false'||$select.attr('showTimePicker')==false){
				this.isShowTimePicker = false;
			}
		}
		if(typeof $select.attr('customLegend')!="undefined"){
			selectTimes = $.parseJSON($select.attr('customLegend'));
		}
	}
	
	DateRangePicker.prototype.initPosition = function(){
		var bodyWidth=this.$dateRangePicker.width();
		this.$dateRangePicker.css('top',this.$select.offset().top+30);
		this.$dateRangePicker.css('left','');
		if(this.$select.offset().left + bodyWidth > $("body").width() ){
			this.$dateRangePicker.css('right',1);
		}else{
			this.$dateRangePicker.css('left',this.$select.offset().left);
		}
//		this.$dateRangePicker.css('top',0+30);
//		this.$dateRangePicker.css('left',0);
	}
	
	DateRangePicker.prototype.initSelected = function(index){
		if(isNaN(parseInt(index))){
			return false;
		}
		var currenItem = null;
		$.each(selectTimes,function(i, item){
			if(i == parseInt(index)){
				item.selected = true;
				currenItem = item;
			}
		 });
		 return currenItem;
	}
	DateRangePicker.prototype.initTimePicker = function(){
		var self;
		self = this;
		
		this.$dateRangePicker.find('.drp-calendar-date-cancel').find('span').click(function(evt){
			self.hide();
		});
		this.$dateRangePicker.find('.drp-calendar-date-ok').find('span').click(function(evt){
			//if(self.$select.attr('init-index')==(""+self.customOptionIndex)){
				self.$select.attr("value",self.$dateRangePicker.find('.drp-calendar:first-child').find('.drp-calendar-date').text()+" - "+self.$dateRangePicker.find('.drp-calendar:last-child').find('.drp-calendar-date').text())
			//}
			self.$select.focus();
			self.hide();
		});
		
		var leftHour = this.$dateRangePicker.find('.calendar-time-left').find('.hourselect');
		leftHour.change(function(evt) {
			self.showCustomDate();
			self.draw();
		});
		var leftMinute = this.$dateRangePicker.find('.calendar-time-left').find('.minuteselect');
		leftMinute.change(function(evt) {
			self.showCustomDate();
			self.draw();
		});
		var leftSecond = this.$dateRangePicker.find('.calendar-time-left').find('.secondselect');
		leftSecond.change(function(evt) {
			self.showCustomDate();
			self.draw();
		});
		var rightHour = this.$dateRangePicker.find('.calendar-time-right').find('.hourselect');
		rightHour.change(function(evt) {
			self.showCustomDate();
			self.draw();
		});
		var rightMinute = this.$dateRangePicker.find('.calendar-time-right').find('.minuteselect');
		rightMinute.change(function(evt) {
			self.showCustomDate();
			self.draw();
		});
		var rightSecond = this.$dateRangePicker.find('.calendar-time-right').find('.secondselect');
		rightSecond.change(function(evt) {
			self.showCustomDate();
			self.draw();
		});
		if(this.isShowTimePicker){
			this.$dateRangePicker.find('.calendar-time-left').css('display','block');
			this.$dateRangePicker.find('.calendar-time-right').css('display','block');
			this.$dateRangePicker.find('.drp-calendars').find('.drp-calendar').addClass('drp-calendar-height');
		}else{
			this.$dateRangePicker.find('.calendar-time-left').css('display','none');
			this.$dateRangePicker.find('.calendar-time-right').css('display','none');
			this.$dateRangePicker.find('.drp-calendars').find('.drp-calendar').removeClass('drp-calendar-height');
		}
	}
	
    DateRangePicker.prototype.hide = function() {
		this.isHidden = true;
			
		return this.$dateRangePicker.hide();
    };

    DateRangePicker.prototype.show = function() {
      this.isHidden = false;
	  var timerArray = this.$select[0].value.split('-');
	  if(timerArray.length>0){
		if(timerArray[0]!=''&& typeof timerArray[0]!="undefined"){//根据输入框中的值初始化日历
			var startDate = new Date(timerArray[0]);
			this.startCalendar = new Calendar(this, this.$dateRangePicker.find('.drp-calendar:first-child'), startDate, true);
			if(this.isShowTimePicker){
				var hours = timerArray[0].substr(11,2);
				this.$dateRangePicker.find('.calendar-time-left').find('.hourselect').val(hours);
				var minutes = timerArray[0].substr(14,2);
				this.$dateRangePicker.find('.calendar-time-left').find('.minuteselect').val(minutes);
				var seconds = timerArray[0].substr(17,2);
				this.$dateRangePicker.find('.calendar-time-left').find('.secondselect').val(seconds);
			}
		}
		if(timerArray[1]!=''&& typeof timerArray[1]!="undefined"){//根据输入框中的值初始化日历
			this.endCalendar = new Calendar(this, this.$dateRangePicker.find('.drp-calendar:last-child'), new Date(timerArray[1]), false);
			if(this.isShowTimePicker){
				var hours = timerArray[1].substr(12,2);
				this.$dateRangePicker.find('.calendar-time-right').find('.hourselect').val(hours);
				var minutes = timerArray[1].substr(15,2);
				this.$dateRangePicker.find('.calendar-time-right').find('.minuteselect').val(minutes);
				var seconds = timerArray[1].substr(18,2);
				this.$dateRangePicker.find('.calendar-time-right').find('.secondselect').val(seconds);
			}
		}
		this.draw();
	  }
	  this.initPosition();
      return this.$dateRangePicker.fadeIn('fast');//.show();
    };

    DateRangePicker.prototype.showCustomDate = function() {
      var text;
      this.$dateRangePicker.find('.drp-timeline-presets li:last-child').addClass('drp-selected').siblings().removeClass('drp-selected');
	  text = this.formatDate(this.startDate()) + ' - ' + this.formatDate(this.endDate());
      this.$select.find('option:last-child').text(text);
	  this.$select.attr('init-index',this.customOptionIndex);
      return this.$select[0].selectedIndex = this.customOptionIndex;
    };

	
    DateRangePicker.prototype.formatDate = function(d) {
      return "" + (d.getMonth() + 1) + "/" + (d.getDate()) + "/" + (d.getFullYear().toString().substr(2, 2));
    };

    DateRangePicker.prototype.setRange = function(daysAgo) {
      var endDate, startDate;
      if (isNaN(daysAgo)) {
        return false;
      }
      daysAgo -= 1;
      endDate = new Date();
      startDate = new Date();
      startDate.setDate(endDate.getDate() - daysAgo);
      this.startCalendar = new Calendar(this, this.$dateRangePicker.find('.drp-calendar:first-child'), startDate, true);
      this.endCalendar = new Calendar(this, this.$dateRangePicker.find('.drp-calendar:last-child'), endDate, false);
      return this.draw();
    };

    DateRangePicker.prototype.endDate = function() {
      return this.endCalendar.date;
    };

    DateRangePicker.prototype.startDate = function() {
      return this.startCalendar.date;
    };

    DateRangePicker.prototype.draw = function() {
      this.startCalendar.draw();
      return this.endCalendar.draw();
    };

    return DateRangePicker;

  })();

  Calendar = (function() {
    function Calendar(dateRangePicker, $calendar, date, isStartCalendar) {
      var self;
      this.dateRangePicker = dateRangePicker;
      this.$calendar = $calendar;
      this.date = date;
      this.isStartCalendar = isStartCalendar;
      self = this;
      this.date.setHours(0, 0, 0, 0);
      this._visibleMonth = this.month();
      this._visibleYear = this.year();
      this.$title = this.$calendar.find('.drp-month-title');
      this.$dayHeaders = this.$calendar.find('.drp-day-headers');
      this.$days = this.$calendar.find('.drp-days');
      this.$dateDisplay = this.$calendar.find('.drp-calendar-date');
      $calendar.find('.drp-arrow').click(function(evt) {
        if ($(this).hasClass('drp-arrow-right')) {
          self.showNextMonth();
        } else {
          self.showPreviousMonth();
        }
        return false;
      });
    }

    Calendar.prototype.showPreviousMonth = function() {
      if (this._visibleMonth === 1) {
        this._visibleMonth = 12;
        this._visibleYear -= 1;
      } else {
        this._visibleMonth -= 1;
      }
      return this.draw();
    };

    Calendar.prototype.showNextMonth = function() {
      if (this._visibleMonth === 12) {
        this._visibleMonth = 1;
        this._visibleYear += 1;
      } else {
        this._visibleMonth += 1;
      }
      return this.draw();
    };

    Calendar.prototype.setDay = function(day) {
      this.setDate(this.visibleYear(), this.visibleMonth(), day);
	  
      return this.dateRangePicker.showCustomDate();
    };

    Calendar.prototype.setDate = function(year, month, day) {
      this.date = new Date(year, month - 1, day);
      return this.dateRangePicker.draw();
    };

    Calendar.prototype.draw = function() {
      var day, _i, _len;
      this.$dayHeaders.empty();
      this.$title.text("" + (this.nameOfMonth(this.visibleMonth())) + " " + (this.visibleYear()));
      for (_i = 0, _len = DAYS.length; _i < _len; _i++) {
        day = DAYS[_i];
        this.$dayHeaders.append($("<li>" + (day.substr(0, 2)) + "</li>"));
      }
      this.drawDateDisplay();
      return this.drawDays();
    };

    Calendar.prototype.dateIsSelected = function(date) {
      return date.getTime() === this.date.getTime();
    };

    Calendar.prototype.dateIsInRange = function(date) {
      return date >= this.dateRangePicker.startDate() && date <= this.dateRangePicker.endDate();
    };

    Calendar.prototype.dayClass = function(day, firstDayOfMonth, lastDayOfMonth) {
      var classes, date;
      date = new Date(this.visibleYear(), this.visibleMonth() - 1, day);
      classes = '';
      if (this.dateIsSelected(date)) {
        classes = 'drp-day-selected';
      } else if (this.dateIsInRange(date)) {
        classes = 'drp-day-in-range';
        if (date.getTime() === this.dateRangePicker.endDate().getTime()) {
          classes += ' drp-day-last-in-range';
        }
      } else if (this.isStartCalendar) {
        if (date > this.dateRangePicker.endDate()) {
          classes += ' drp-day-disabled';
        }
      } else if (date < this.dateRangePicker.startDate()) {
        classes += ' drp-day-disabled';
      }
      if ((day + firstDayOfMonth - 1) % 7 === 0 || day === lastDayOfMonth) {
        classes += ' drp-day-last-in-row';
      }
      return classes;
    };

    Calendar.prototype.drawDays = function() {
      var firstDayOfMonth, i, lastDayOfMonth, self, _i, _j, _ref;
      self = this;
      this.$days.empty();
      firstDayOfMonth = this.firstDayOfMonth(this.visibleMonth(), this.visibleYear());
      lastDayOfMonth = this.daysInMonth(this.visibleMonth(), this.visibleYear());
      for (i = _i = 1, _ref = firstDayOfMonth - 1; _i <= _ref; i = _i += 1) {
        this.$days.append($("<li class='drp-day drp-day-empty'></li>"));
      }
      for (i = _j = 1; _j <= lastDayOfMonth; i = _j += 1) {
        this.$days.append($("<li class='drp-day " + (this.dayClass(i, firstDayOfMonth, lastDayOfMonth)) + "'>" + i + "</li>"));
      }
      return this.$calendar.find('.drp-day').click(function(evt) {
        var day;
        if ($(this).hasClass('drp-day-disabled')) {
          return false;
        }
        day = parseInt($(this).text(), 10);
        if (isNaN(day)) {
          return false;
        }
        return self.setDay(day);
      });
    };

    Calendar.prototype.drawDateDisplay = function() {
		var self;
		self = this;
		/*
		 * 修复选择后有时候时间会不见的bug
		 */
		var formatmonth = this.month();
		var formatday = this.day();
		if(formatmonth < 10){
			formatmonth = '0' + formatmonth;
		}
		if(formatday < 10){
			formatday = '0' + formatday;
		}
		if(this.dateRangePicker.isShowTimePicker){
			var hms = '';
			if(this.$dateDisplay.selector.indexOf('last-child')==-1){	
				var calendarTimeLeft = this.dateRangePicker.$dateRangePicker.find('.calendar-time-left');
				hms = calendarTimeLeft.find('.hourselect')[0].value+":"+calendarTimeLeft.find('.minuteselect')[0].value+":"+calendarTimeLeft.find('.secondselect')[0].value;	
			}else{
				var calendarTimeRight = this.dateRangePicker.$dateRangePicker.find('.calendar-time-right');
				hms = calendarTimeRight.find('.hourselect')[0].value+":"+calendarTimeRight.find('.minuteselect')[0].value+":"+calendarTimeRight.find('.secondselect')[0].value;	
			}
			return this.$dateDisplay.text([this.year(), formatmonth, formatday].join('/')+" "+hms);
		}else{
			return this.$dateDisplay.text([this.year(), formatmonth, formatday].join('/'));
		}     
    };

    Calendar.prototype.month = function() {
      return this.date.getMonth() + 1;
    };

    Calendar.prototype.day = function() {
      return this.date.getDate();
    };

    Calendar.prototype.dayOfWeek = function() {
      return this.date.getDay() + 1;
    };

    Calendar.prototype.year = function() {
      return this.date.getFullYear();
    };

    Calendar.prototype.visibleMonth = function() {
      return this._visibleMonth;
    };

    Calendar.prototype.visibleYear = function() {
      return this._visibleYear;
    };

    Calendar.prototype.nameOfMonth = function(month) {
      return MONTHS[month - 1];
    };

    Calendar.prototype.firstDayOfMonth = function(month, year) {
      return new Date(year, month - 1, 1).getDay() + 1;
    };

    Calendar.prototype.daysInMonth = function(month, year) {
      month || (month = this.visibleMonth());
      year || (year = this.visibleYear());
      return new Date(year, month, 0).getDate();
    };

    return Calendar;

  })();

  $.fn.dateRangePicker = function() {
    return new DateRangePicker(this);
  };
  
  //$('.custom-date').dateRangePicker();
}).call(this);
