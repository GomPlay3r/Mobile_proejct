package com.example.project_;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Reset_Pass_Request extends StringRequest {
    final static private String URL = "http://10.0.2.2/test/testpass.php";
    private Map<String, String> parameters;

    public Reset_Pass_Request(String ID, String Pass, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("ID", ID);
        parameters.put("Pass", Pass);
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}