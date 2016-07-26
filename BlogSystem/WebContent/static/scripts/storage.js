function Storage(localStorage) {
	this.save = function(key, val) {
		// 用 localStorage 存储时，需要先把对象转换为字符串
		localStorage[key] = JSON.stringify(val); //
	}
	this.remove = function(key) {
		//localStorage[key] = JSON.stringify();   // 或者尝试下 undefined
		localStorage.removeItem(key);
	}
	this.get = function(key) {
	  if (JSON.parse(localStorage[key] != undefined)){  
			return  JSON.parse(localStorage[key]);
		}
		else{ 
			return undefined;
		}
	}
	this.clear = function() {
		localStorage.clear();
	}

}

var storage = new Storage(window.localStorage);

function autoSava() {
	// 获取title和content实时更新的内容
	var title = document.getElementById("title").value;
	var content = document.getElementById("content").value;
	//alert("title:"+title);
	//alert("content:"+content);
	// 将内容实时存储到 localStorage 中
	storage.save('title', title);
	storage.save('content', content);
}

function show_confirm() {
	var a = confirm("确定提交吗？");
	if (a) {
		// 模拟保存成功的场景,保存成功后应当清空localStorage
		storage.clear();
	} else {
		// 模拟保存出错的场景
		var para = document.createElement("p");
		para.setAttribute("style","color:red");
		var testdiv = document.getElementById("testdiv");
		testdiv.appendChild(para);
		var text = document.createTextNode("取消保存");
		setTimeout(para.appendChild(text), 2000);
	}
}

// 用户在进入文章创建页面时，要加载localStorage里的内容
function getStorage() {
	document.getElementById("title").value = storage.get('title');
	document.getElementById("content").value = storage.get('content');

	// 获取 localStorage 中的内容
	alert(storage.get('content'));
}


window.onload = getStorage(); 