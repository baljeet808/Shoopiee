package gauravkumar.com.shoopie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import adapters.ImageLoader;

public class Items extends AppCompatActivity {

    String gender = "",itemSelected = "";
    TextView heading;
    ImageView item1,item2,item3,item4,backButton;
    String url1="",url2="",url3="",url4="";


    protected  MainApplication mainApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        mainApplication = (MainApplication)this.getApplicationContext();
        mainApplication.setCurrentActivity(this);

        gender = mainApplication.selectedGenderIs();
        itemSelected = mainApplication.selectedItemIs();





        if(gender == "Men")
        {
            switch(itemSelected)
            {
                case "Shirts" :
                {
                    url1 = "black_men_shirt.png";
                    url2 = "blue_men_shirt.png";
                    url3 = "grey_men_shirt.png";
                    url4 = "white_men_shirt.png";

                }
                break;
                case "Jeans" :
                {
                    url1 = "black_jeans.png";
                    url2 = "blue_rugged.png";
                    url3 = "denim_washed.png";
                    url4 = "white_jean.png";

                }
                break;
                case "Shoes" :
                {
                    url1 = "adisdas.png";
                    url2 = "boots.png";
                    url3 = "canverse.png";
                    url4 = "timberland.png";

                }
                break;
                case "Accessories" :
                {
                    url1 = "belt_men.png";
                    url2 = "cap.png";
                    url3 = "glasses.png";
                    url4 = "watch_men.png";

                }
                break;
            }
        }
        else{
            switch(itemSelected)
            {
                case "Shirts" :
                {
                    url1 = "blue_women_shirt.png";
                    url2 = "grey_women_shirt.png";
                    url3 = "purple_women_shirt.png";
                    url4 = "white_women_shirt.png";

                }
                break;
                case "Jeans" :
                {
                    url1 = "blue_denim.png";
                    url2 = "jogger_women_jean.png";
                    url3 = "light_women_denim.png";
                    url4 = "skinny_women_jean.png";

                }
                break;
                case "Shoes" :
                {
                    url1 = "black_heals.png";
                    url2 = "red_heels.png";
                    url3 = "running_shoe.png";
                    url4 = "nuke.png";

                }
                break;
                case "Accessories" :
                {
                    url1 = "hat.png";
                    url2 = "makeup.png";
                    url3 = "purse.png";
                    url4 = "purse_yellow.png";

                }
                break;
            }
        }




        heading = (TextView) findViewById(R.id.item_heading_textview);
        heading.setText(itemSelected);

        backButton = (ImageView) findViewById(R.id.back_button_items);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Items.this,Collection.class);
                startActivity(i);
                finish();
            }
        });


        item1  = (ImageView) findViewById(R.id.item1_imageview);
        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainApplication.setChoosenItemName(url1);
                Intent i = new Intent(Items.this,ItemDetail.class);
                startActivity(i);
                finish();
            }
        });

         item2  = (ImageView) findViewById(R.id.item2_imageview);
        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mainApplication.setChoosenItemName(url2);
                Intent i = new Intent(Items.this,ItemDetail.class);
                startActivity(i);
                finish();
            }
        });
  item3  = (ImageView) findViewById(R.id.item3_imageview);
        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainApplication.setChoosenItemName(url3);
                Intent i = new Intent(Items.this,ItemDetail.class);
                startActivity(i);
                finish();
            }
        });

        item4  = (ImageView) findViewById(R.id.item4_imageview);
        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainApplication.setChoosenItemName(url4);
                Intent i = new Intent(Items.this,ItemDetail.class);
                startActivity(i);
                finish();
            }
        });


        try
        {
            ImageLoader.getObject(getApplicationContext()).LoadImageFromUrl(item1,url1);
            ImageLoader.getObject(getApplicationContext()).LoadImageFromUrl(item2,url2);
            ImageLoader.getObject(getApplicationContext()).LoadImageFromUrl(item3,url3);
            ImageLoader.getObject(getApplicationContext()).LoadImageFromUrl(item4,url4);
        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),""+e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
