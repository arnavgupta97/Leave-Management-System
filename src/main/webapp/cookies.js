	var cookie_id = escape(document.loginform.id.value);
    document.cookie = "userid=" + cookie_id + ";";
    var cookie_password = escape(document.loginform.password.value);
    document.cookie = "password=" + cookie_password + ";";