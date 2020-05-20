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

import gauravkumar.com.shoopie.AddressBook;
import gauravkumar.com.shoopie.Cart;
import gauravkumar.com.shoopie.Checkout;
import gauravkumar.com.shoopie.HomePage;
import gauravkumar.com.shoopie.ItemDetail;
import gauravkumar.com.shoopie.Login;
import gauravkumar.com.shoopie.MainApplication;
import gauravkumar.com.shoopie.SavedItem;
import gauravkumar.com.shoopie.SignUp;
import gauravkumar.com.shoopie.UserDetail;

public class WebApiAdapter {

    StringRequest request;
    static Map map;
    String url;
    MainApplication mainApplication;
    SignUp signUp;
    Login login;
    UserDetail userDetail;
    AddressBook addressBook;
    int requestCode =0;
    boolean ifFirst = true;

    static Context con;
    static WebApiAdapter apiAdapter;
    String sid;

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
            }
            break;
            case 2 :{
                map.put("password",bundle.getString("password"));
                map.put("email",bundle.getString("email"));
                url = "https://shoppie808.000webhostapp.com/shoppie/SignIn.php";
            }
            break;
            case 3 :
            {
                map.put("UID",bundle.getString("UID"));
                map.put("fname",bundle.getString("fname"));
                map.put("lname",bundle.getString("lname"));
                map.put("password",bundle.getString("password"));
                map.put("email",bundle.getString("email"));
               // Toast.makeText(con, "email = "+bundle.getString("email"), Toast.LENGTH_SHORT).show();
                url = "https://shoppie808.000webhostapp.com/shoppie/UpdateUserDetail.php";
            }
            break;
            case 4 :
            {

                map.put("UID",bundle.getString("UID"));
                map.put("address",bundle.getString("address"));
                map.put("country",bundle.getString("country"));
                //Toast.makeText(con, "email = "+bundle.getString("country"), Toast.LENGTH_SHORT).show();
                url = "https://shoppie808.000webhostapp.com/shoppie/UpdateAddress.php";

            }break;
            case 5 :
            {
                url = "https://shoppie808.000webhostapp.com/shoppie/GetAllItems.php";
            }
            break;
            case 6 :
            {
                map.put("UID",SharedPreps.getStaticObject(con).getUID());
                url = "https://shoppie808.000webhostapp.com/shoppie/GetSavedItemIds.php";
            }break;
            case 7 :
            {
                map.put("UID",bundle.getString("UID"));
                map.put("itemID",bundle.getString("itemID"));
                url = "https://shoppie808.000webhostapp.com/shoppie/saveItem.php";
            }
            break;
            case 8 :
            {
                map.put("sid",bundle.getString("sid"));
                sid = bundle.getString("sid");
                url = "https://shoppie808.000webhostapp.com/shoppie/deletSavedItem.php";
            }break;
            case 9 :
            {
                map.put("UID",bundle.getString("UID"));
                map.put("orderNumber",bundle.getString("orderNumber"));
                url="https://shoppie808.000webhostapp.com/shoppie/placeOrder.php";
            }break;
            case 10 :
            {
                map.put("sid",bundle.getString("sid"));
                sid = bundle.getString("sid");
                map.put("orderNumber",bundle.getString("orderNumber"));
                map.put("iid",bundle.getString("iid"));
                url = "https://shoppie808.000webhostapp.com/shoppie/orderSavedItem.php";
            }
            break;
            case 11 :
            {
                map.put("UID",SharedPreps.getStaticObject(con).getUID());
                url = "https://shoppie808.000webhostapp.com/shoppie/GetSavedItemIds.php";
            }break;
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
             login.onResponseReceive(response);
         }
         break;
         case 3 :
         {
             userDetail = (UserDetail)con;
             userDetail.onResponseReceive(response);
         }
         break;
         case 4 :
         {
             addressBook = (AddressBook)con;
             addressBook.onResponseReceive(response);
         }
         break;
         case 5 :
         {
              HomePage homePage = (HomePage)con;
             homePage.onResponseReceive(response);
         }break;
         case 6 :
         {
             Cart cart = (Cart)con;
             cart.onResponseReceive(response);
         }
         break;
         case 7 :
         {
             ItemDetail itemDetail = (ItemDetail)con;
             itemDetail.onResponseReceive(response);
         }
         break;
         case 8 :
         {
             if(mainApplication.getCurrentActivity().getLocalClassName().equals("Cart")==true) {
                 Cart cart = (Cart) con;
                 cart.refreshCart(response, sid);
             }
             else{
                 ItemsAdapter.getObject(con).deleteSavedItem(sid);
             }
         }
         break;
         case 9 :
         {
             Checkout checkout= (Checkout)con;
             checkout.OnResponseByOrderTable(response);
         }break;
         case 10 :
         {
            // Toast.makeText(con, "saved item "+sid+" deleting", Toast.LENGTH_SHORT).show();
            ItemsAdapter.getObject(con).deleteSavedItem(sid);
         }
         break;
         case 11 :
         {
             SavedItem item = (SavedItem)con;
             item.onResponse(response);
         }break;
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
                    sendBroadcastToSpecificClass(response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              /* if(requestCode!=5 || requestCode!=6 || requestCode!=7){ AlertAdapter.getObject(con).dismissAlert();}
              */  Log.e("\n\nerror: ",error.toString()+"\n\n\n");
                try {
                    AlertAdapter.getObject(con).createMessageAlert(error.getMessage().toUpperCase());
                    sendBroadcastToSpecificClass("error");
                }catch (Exception e)
                {
                    AlertAdapter.getObject(con).createMessageAlert("Response error ");
                    sendBroadcastToSpecificClass("error");
                }

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
