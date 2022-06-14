package com.example.project_;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest{
    //서버 URL 설정 (PHP 파일 연동)
    final static private String URL = "http://10.0.2.2/test/login.php";
    private Map<String, String> map;

    public LoginRequest(String ID, String Pass,Response.Listener<String> listener) {
        super(Method.POST,URL,listener,null);

        map = new HashMap<>();
        map.put("ID",ID);
        map.put("Pass",Pass);
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() {
        return map;
    }
}
