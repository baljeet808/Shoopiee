package adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import gauravkumar.com.shoopie.R;

public class ImageLoader {

    Context context;
    static ImageLoader imageLoader;

    public ImageLoader (Context context)
    {
        this.context = context;
    }

    public static synchronized ImageLoader getObject(Context context)
    {
        if(imageLoader==null)
        {
            imageLoader = new ImageLoader(context);
        }
        return imageLoader;
    }

    public void LoadImageFromUrl(ImageView view,String url)
    {
        Picasso.with(context).load("https://shoppie808.000webhostapp.com/shopieRES/"+url).placeholder(R.drawable.logo).error(R.drawable.logo).into(view, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
    }
}
