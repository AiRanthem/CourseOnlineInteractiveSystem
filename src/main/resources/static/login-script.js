function loginsubmit()
{
	var username = document.getElementById("login-username");
	var password = document.getElementById("login-password");
	
	if (username.value === "" || password.value === "")
	{
		alert("用户名或密码不能为空！");
		return false;
	}
	
	var form = document.getElementById("loginform");
	form.submit();
	
	/*
	var request;
	if (window.XMLHttpRequest)
	{
		request = new XMLHttpRequest();
	}
	else
	{
		request = new ActiveXObject('Microsoft.XMLHTTP');
	}
	var action_str = "http://127.0.0.1:8080/process_login.jsp";
	var url = action_str + "?username=" + username.value + "&password=" + password.value;
	
	request.onreadystatechange = function()
	{
        if(request.readyState === 4)
		{
            if(request.status === 200)
			{
                return success(request.responseText);
            }
			else
			{
                alert(request.status);
            }
        }
    }
    request.open('post', url);
    request.send();
	*/
    return false;
}
function regsubmit()
{
	var username = document.getElementById("reg-username");
	var password = document.getElementById("reg-password");
	
	if (username.value === "" || password.value === "")
	{
		alert("昵称或密码不能为空！");
		return false;
	}
	
	var RadioValue = GetRadioValue();
	if (RadioValue === null)
	{
		alert("请先选择用户类型！");
		return false;
	}
	
	var form = document.getElementById("regform");
	form.submit();
    return false;
}
function GetRadioValue()
{
	var radio = document.getElementsByName("category");
	for (i = 0; i < radio.length; i++)
	{
		if (radio[i].checked)
		{
			return radio[i].value;
		}
	}
	return null;
}
function success(data)
{
    var answer = document.getElementById("answer");
    var Info = JSON.parse(data);
    if(Info.code === 200)
	{
        answer.innerHTML = '查询成功';
    }
	else
	{
		answer.innerText = Info.msg;
	}
}