package gauravkumar.com.shoopie;

import android.app.Activity;
import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import adapters.CustomBroadcastReciever;

public class MainApplication  extends Application{

    CustomBroadcastReciever customBroadcastReciever = new CustomBroadcastReciever();

    private Activity currentActivity = null;
    private boolean firstTimeSplashFlag = true;
    private String gender = "Women";
    private String slectedItem = "Shirt";
    private String choosenItemName="";
    private String volleyResponse = "";
    private Bundle itemAttributes = new Bundle();

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(customBroadcastReciever,intentFilter);
    }

    public boolean IsSplashRunningForFirstTime()
    {
        return firstTimeSplashFlag;
    }

    public void setSplashRunnigForTheFirstTimeFalse()
    {
        firstTimeSplashFlag=false;
    }

    public Activity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(Activity currentActivity)
    {
        this.currentActivity = currentActivity;
    }

    public String selectedGenderIs()
    {
        return  gender;
    }
    public void selectedGender(String gender)
    {
        this.gender = gender;
    }
    public void setSelctedItem(String slectedItem)
    {
        this.slectedItem = slectedItem;
    }

    public String selectedItemIs()
    {
        return slectedItem;
    }

    public void setVolleyResponse(String response)
    {
        volleyResponse = response;
    }
    public String getVolleyResponse()
    {
        return volleyResponse;
    }

    public void setChoosenItemName(String url)
    {
        choosenItemName = url;
    }
    public String getChoosenItemName()
    {
        return choosenItemName;
    }

    public void saveItemattributes(Bundle  bundle)
    {
        this.itemAttributes = bundle;
    }
    public Bundle getSaveAttributes()
    {
        return itemAttributes;
    }

    public MainApplication() {
        super();
    }



}
