package adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import gauravkumar.com.shoopie.Login;
import gauravkumar.com.shoopie.MainApplication;
import gauravkumar.com.shoopie.SignUp;

public class WebApiAdapter {

    StringRequest request;
    static Map map;
    String url;
    MainApplication mainApplication;
    SignUp signUp;
    Login login;
    int requestCode =0;

    static Context con;
    static WebApiAdapter apiAdapter;

    public WebApiAdapter (Context con)
    {
        this.con = con;
        mainApplication = (MainApplication)con.getApplicationContext();
    }

    public static synchronized WebApiAdapter getObject(Context context)
    {
        con = context;
        if(apiAdapter==null)
        {
            apiAdapter = new WebApiAdapter(con);
        }
        map = new HashMap<>();
        return apiAdapter;
    }

    public void fireServerApi(int requestCode,Bundle bundle)
    {
        this.requestCode = requestCode;
        switch(requestCode)
        {
            case 1 : {
                    map.put("fname",bundle.getString("fname"));
                    map.put("lname",bundle.getString("lname"));
                    map.put("password",bundle.getString("password"));
                    map.put("email",bundle.getString("email"));
                    url = "https://shoppie808.000webhostapp.com/shoppie/RegisterUser.php";
                    try{

                    }
                    catch (Exception e)
                    {
                        Toast.makeText(con,"Signup object - "+e.getMessage(),Toast.LENGTH_LONG).show();
                    }
            }
            break;
            case 2 :{
                map.put("password",bundle.getString("password"));
                map.put("email",bundle.getString("email"));
                url = "https://shoppie808.000webhostapp.com/shoppie/signIn.php";
            }
            break;
        }
        performWebOperation();

    }
    public void sendBroadcastToSpecificClass(String response)
    {
     switch (requestCode)
     {
         case 1 :
         {
             signUp=(SignUp)con;
             signUp.onResponseReceive(response);
         }
         break;
         case 2 :
         {
             login = (Login)con;

         }
         break;
     }
    }

    public void performWebOperation()
    {
        request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                if(response!=null)
                {
                    mainApplication.setVolleyResponse(response);
                    AlertAdapter.getObject(con).dismissAlert();
                   // Toast.makeText(con,""+response,Toast.LENGTH_LONG).show();
                    sendBroadcastToSpecificClass(response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertAdapter.getObject(con).dismissAlert();
                 }
        }
        )
        {
            @Override
            protected Map getParams() throws AuthFailureError {
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(con);
        queue.add(request);

    }

}
