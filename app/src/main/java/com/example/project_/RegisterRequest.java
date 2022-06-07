package com.example.project_;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest{
    //서버 URL 설정 (PHP 파일 연동)
    final static private String URL = "http://10.0.2.2/test/register.php";
    private Map<String, String> map;

    public RegisterRequest(String ID, String Pass, String Email, String Name, String NN, Response.Listener<String> listener) {
        super(Method.POST,URL,listener,null);

        map = new HashMap<>();
        map.put("ID",ID);
        map.put("Pass",Pass);
        map.put("Email",Email);
        map.put("Name",Name);
        map.put("NN",NN);
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
