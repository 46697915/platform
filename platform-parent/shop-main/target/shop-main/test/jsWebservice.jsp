<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>

	<script type="text/javascript" src="/shop/test/webService.js"></script>
	
	<script type="text/javascript">
function HashMap() 
{ 
    // members 
    this.keyArray = new Array(); // Keys 
    this.valArray = new Array(); // Values 
        
    // methods 
    this.put = put; 
    this.get = get; 
    this.size = size;  
    this.clear = clear; 
    this.keySet = keySet; 
    this.valSet = valSet; 
    this.showMe = showMe;   // returns a string with all keys and values in map. 
    this.findIt = findIt; 
    this.remove = remove; 
}

function put( key, val ) 
{ 
    var elementIndex = this.findIt( key ); 
    
    if( elementIndex == (-1) ) 
    { 
        this.keyArray.push( key ); 
        this.valArray.push( val ); 
    } 
    else 
    { 
        this.valArray[ elementIndex ] = val; 
    } 
}

function get( key ) 
{ 
    var result = null; 
    var elementIndex = this.findIt( key );

    if( elementIndex != (-1) ) 
    {   
        result = this.valArray[ elementIndex ]; 
    }  
    
    return result; 
}

function remove( key ) 
{ 
    var result = null; 
    var elementIndex = this.findIt( key );

    if( elementIndex != (-1) ) 
    { 
        this.keyArray = this.keyArray.removeAt(elementIndex); 
        this.valArray = this.valArray.removeAt(elementIndex); 
    }  
    
    return ; 
}

function size() 
{ 
    return (this.keyArray.length);  
}

function clear() 
{ 
    for( var i = 0; i < this.keyArray.length; i++ ) 
    { 
        this.keyArray.pop(); this.valArray.pop();   
    } 
}

function keySet() 
{ 
    return (this.keyArray); 
}

function valSet() 
{ 
    return (this.valArray);   
}

function showMe() 
{ 
    var result = ""; 
    
    for( var i = 0; i < this.keyArray.length; i++ ) 
    { 
        result += "Key: " + this.keyArray[ i ] + "\tValues: " + this.valArray[ i ] + "\n"; 
    } 
    return result; 
}

function findIt( key ) 
{ 
    var result = (-1);

    for( var i = 0; i < this.keyArray.length; i++ ) 
    { 
        if( this.keyArray[ i ] == key ) 
        { 
            result = i; 
            break; 
        } 
    } 
    return result; 
}

function removeAt( index ) 
{ 
  var part1 = this.slice( 0, index); 
  var part2 = this.slice( index+1 );

  return( part1.concat( part2 ) ); 
} 
Array.prototype.removeAt = removeAt; 
	
document.domain="10.40.125.134";
	var URL = "http://10.40.125.134:7003/yqybjk/services/YBJK";
	
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
		alert(1);
		web_map=createXmlhttpObject(data);
		
		alert(2);
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
			xmlhttp.SetRequestHeader("Content-Type","application/x-www-form-urlencoded");
			xmlhttp.SetRequestHeader ("Content-Length",data.length);
			xmlhttp.Send(data);   
			alert(3);
			if(xmlhttp.status==200){ 
				var responseText=xmlhttp.responseText;
				alert(4);
				//alert(responseText);
				//document.getElementById("data1").innerHTML = responseText;
				return createXmlDocObject(responseText);
			}else{
				alert(xmlhttp.status);
				return null;
			}
		}catch (e){
			alert(e);
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
	
	common_card_checkstate_old();
	
	</script>
</head>
<body>
asdfas
</body>
</html>