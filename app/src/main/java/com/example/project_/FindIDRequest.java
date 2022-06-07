package com.example.project_;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class FindIDRequest extends StringRequest {
    final static private String URL = "http://10.0.2.2/test/findid.php";
    private Map<String, String> parameters;

    public FindIDRequest(String Name, String Email, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("Name", Name);
        parameters.put("Email", Email);
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}