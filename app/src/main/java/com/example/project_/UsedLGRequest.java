package com.example.project_;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UsedLGRequest extends StringRequest {
    final static private String URL = "http://10.0.2.2/test/randomLG.php";
    private Map<String, String> map;

    public UsedLGRequest(Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}