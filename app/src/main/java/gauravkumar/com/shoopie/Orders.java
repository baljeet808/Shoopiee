package gauravkumar.com.shoopie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

public class Orders extends AppCompatActivity {

    protected  MainApplication mainApplication;

    ListView orderListView;
    ImageView backButton;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        mainApplication = (MainApplication)this.getApplicationContext();
        mainApplication.setCurrentActivity(this);

        progressBar = (ProgressBar) findViewById(R.id.order_loadingBar);

        backButton = (ImageView) findViewById(R.id.back_button_order_layout);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        orderListView = (ListView) findViewById(R.id.order_listview);

    }
}
