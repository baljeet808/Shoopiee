package adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class AlertAdapter {

    View view;
    RelativeLayout holder;
    AlertDialog.Builder builder;
    AlertDialog alert;
    Context context;
    static AlertAdapter alertAdapter;

    public AlertAdapter(Context context)
    {
        this.context = context;
    }

    public static synchronized AlertAdapter getObject(Context context)
    {
        if(alertAdapter==null)
        {
            alertAdapter = new AlertAdapter(context);
        }
        return  alertAdapter;
    }

    public void createAlert()
    {
        builder = new AlertDialog.Builder(context);
        alert = builder.create();
        alert.setTitle("Please Wait..");
        ProgressBar progressBar = new ProgressBar(context);
        alert.setView(progressBar);
        alert.setCancelable(false);
        alert.show();
    }

    public void dismissAlert()
    {
        alert.dismiss();
    }
}
