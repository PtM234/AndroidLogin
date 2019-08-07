package com.android.tapia.login;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL="http://192.168.0.15/Register.php";
    private Map<String, String> params;
    public  RegisterRequest (String name, String user, String pass, int age, Response.Listener<String> listenr){
        super (Method.POST, REGISTER_REQUEST_URL, listenr, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("user", user);
        params.put("pass", pass);
        params.put("age", age+"");

    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}
