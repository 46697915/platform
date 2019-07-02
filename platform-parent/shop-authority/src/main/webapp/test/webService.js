	var strHref = document.location.href;
	var URL = "http://10.40.125.134:7003/yqybjk/services/YBJK";
	//10.40.64.235-237
	if (strHref.indexOf('117')>=0||strHref.indexOf('118')>=0){
		//URL = "http://192.168.2.121:7001/YlzinfoESB/esbproxy";//定点访问地址
		URL = "http://10.40.64.237:7011/YlzinfoESB/esbproxy";//中心访问地址
	}else{
		URL = "http://10.40.64.237:7011/YlzinfoESB/esbproxy";//中心访问地址
	}
	var paraUsr = 'yqyb';
	var paraPwd = '8FEDB95914FA9906';
	var paraSid = 'common_card_ins_info2';
	
	var newCardInfo = null;
	//校验卡是否有效（状态）
	function common_card_checkstate(BCC002,CERTID,CARDSERIAL,BCE013,BAE140,callBackFn) {
		newCardInfo = null;
		//拼接请求调用的XML数据流格式    
		Ext.Ajax.request({
			url:_ctx+'/webServiceController.sc?method=webService',   
			async: true,
			params:{
				postUrl: URL,
				paraUsr: paraUsr,
				paraPwd: paraPwd,
				paraSid: paraSid,
				BCC002: BCC002,
				CERTID : CERTID,
				CARDSERIAL: CARDSERIAL,
				BCE013 : BCE013,
				BAE140 : BAE140
			},   
			failure:function(o){
				Ext.Msg.alert("提示",Ext.decode(o.responseText).message);
				return ;
			},
			success:function(o){
				if(!Ext.decode(o.responseText).success){
					Ext.Msg.alert("提示",Ext.decode(o.responseText).message,function(){});
					return ;
				}
				var message = Ext.decode(o.responseText).message;
				var bean = Ext.decode(message);
				getWebServiceinfo(bean,callBackFn);
			}
		});
	}
	function getWebServiceinfo(webServiceinfo,callBackFn){
		if(webServiceinfo == null){
			newCardInfo = "没有查询到此卡有效信息！",""+","+""+","+""+","+""+","+"";
			callBackFn();
			return ;
		}
		if (webServiceinfo.errorMsg != null && webServiceinfo.errorMsg != ''){
			//alert(webServiceinfo);
			newCardInfo = "卡平台："+webServiceinfo.errorMsg+","+""+","+""+","+""+","+""+","+"";
			callBackFn();
			return ;
		}
		newCardInfo = ""+","+webServiceinfo.AAC002+","+BCE003.get(webServiceinfo.BCE003)+","
					  +AAE140.get(webServiceinfo.AAE140)+","+AAB301.get(webServiceinfo.AAB301)+","+webServiceinfo.BCC001;
		callBackFn();
	}
	function getWebInfo(){
		return newCardInfo;
	}
	
	//校验卡是否有效（状态）  20130329
	function common_card_checkstate_old() {
		  //拼接请求调用的XML数据流格式    
		var data='';
		data = data + '<?xml version="1.0" encoding="UTF-8"?>';
		data = data + '<SstRequest><userid>ymzy</userid><passwd>1234</passwd><signature></signature><funid>yq_hq_zydjxx_qq</funid>';
		data = data + '<RequestSet>';
		data = data + '  <zdbh>ybck</zdbh><yyjgdm>0303310132</yyjgdm>';		
		data = data + '  <brbsh></brbsh><kahao></kahao>';
		data = data + '   <zyh>644211-3</zyh><klxbh>1</klxbh>';
		data = data + '   <fzxbh>ybck</fzxbh><sfcy>0</sfcy>';
		data = data + '</RequestSet></SstRequest>';

		var web_map=new HashMap();
		
		web_map=createXmlhttpObject(data);
		
		//alert(map.toString());
		//alert(map.get("msg"));
		if(web_map==null){
			return "没有查询到此卡有效信息！",""+","+""+","+""+","+""+","+"";
		}
		if(web_map.get("msg")!=null){
			return "卡平台返回异常："+web_map.get("msg")+","+""+","+""+","+""+","+""+","+"";
		}
		
		//document.getElementById("data1").innerHTML = ""+","+web_map.get("BCC001")+","+BCE003.get(web_map.get("BCE003"));
		return ""+","+web_map.get("AAC002")+","+BCE003.get(web_map.get("BCE003"))+","+AAE140.get(web_map.get("AAE140"))
			+","+AAB301.get(web_map.get("AAB301"))+","+web_map.get("BCC001");
	}

	function createXmlhttpObject(data){
		try{
			//创建异步对象            
			var xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			xmlhttp.Open("POST", URL, false); 
			xmlhttp.Send(data);   
			
			if(xmlhttp.status==200){ 
				var responseText=xmlhttp.responseText;
				//alert(responseText);
				//document.getElementById("data1").innerHTML = responseText;
				return createXmlDocObject(responseText);
			}else{
				return null;
			}
		}catch (e){
			var err_map=new HashMap();
			//err_map.put('msg',e.message);
			err_map.put('msg','访问远程卡平台异常，请查看网络！');
			//err_map.put('msg','访问远程卡平台异常，请查看网络！\n\n'+'err.message:'+e.message+'\n'+e.name+'错误' + e.message + '发生在' +   e.lineNumber + '行');
			return err_map;
			//alert(e.name+'错误' + e.message + '发生在' +   e.lineNumber + '行');   
		}
	/*
	e.name 错误类型,  
	e.message 错误的详细信息.  
	 
	Error.name的六种值对应的信息：  
	1. EvalError：eval()的使用与定义不一致   
	2. RangeError：数值越界   
	3. ReferenceError：非法或不能识别的引用数值   
	4. SyntaxError：发生语法解析错误   
	5. TypeError：操作数类型错误   
	6. URIError：URI处理函数使用不当  
	 */
	}
	
	function createXmlDocObject(data){
		var web_map=new HashMap();
		var xmlDoc = new ActiveXObject("MSXML.DOMDocument");
	  	xmlDoc.loadXML(data);
		var x=xmlDoc.documentElement.childNodes;
		getvalueff(x);
		
		return web_map;
		
		function getvalueff(node){
			for(var i =0;i<node.length;i++){
				if(node[i].hasChildNodes()){
					//alert(node[i].nodeName+":"+node[i].nodeValue);
					//判断当前节点是否拥有属性。
					//if(node[i].hasAttributes()){
						var attrNodes=node[i].attributes;
						if(attrNodes.length>0){
			                 for(j=0;j<attrNodes.length;j++){
			                 	web_map.put(attrNodes[j].name,attrNodes[j].value);
			                 }
				        }else{
				        	web_map.put(node[i].nodeName,node[i].childNodes[0].nodeValue);
				        }
					//}
					getvalueff(node[i].childNodes);
				}else{
					var attrNodes=node[i].attributes;
					if(attrNodes!=null&&attrNodes.length>0){
		                 for(j=0;j<attrNodes.length;j++){
		                 	//alert(attrNodes[j].name+"==========="+attrNodes[j].value);
		                 	web_map.put(attrNodes[j].name,attrNodes[j].value);
		                 }
			        }
				}
			}
		}
	}

	function getvalueff_bak(node){
		var returnText='';
		for(var i =0;i<node.length;i++){
			if(node[i].hasChildNodes()){
				//alert(node[i].nodeName+":"+node[i].nodeValue);
				//判断当前节点是否拥有属性。
				//if(node[i].hasAttributes()){
					var attrNodes=node[i].attributes;
					if(attrNodes.length>0){
			            returnText=returnText+node[i].nodeName+"-(";
			                 for(j=0;j<attrNodes.length;j++){
			                 	returnText=returnText+attrNodes[j].name+":"+attrNodes[j].value;
			                 }
			           returnText=returnText+")-"+node[i].childNodes[0].nodeValue+"<br>";
			        }else{
			        	returnText=returnText+node[i].nodeName+"-(null)-"+node[i].childNodes[0].nodeValue+"<br>";
			        }
				//}
				returnText=returnText+getvalueff(node[i].childNodes);
			}else{
				//alert(node[i].nodeName+"::"+node[i].nodeValue);
				//var nodeName=node[i].nodeName=='#text'?node[i].parentNode.nodeName:node[i].nodeName;//节点名称
				//alert(nodeName+":"+node[i].nodeValue);
				
				var attrNodes=node[i].attributes;
				if(attrNodes!=null&&attrNodes.length>0){
		            returnText=returnText+node[i].nodeName+"(";
		                 for(j=0;j<attrNodes.length;j++){
		                 	returnText=returnText+attrNodes[j].name+":"+attrNodes[j].value+",";
		                 }
		            returnText=returnText+")"+node[i].nodeValue+"<br>";
		        }
			}
		}
		
		return returnText;
	}