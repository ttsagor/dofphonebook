package com.Pisystems.dofphonebook.activity;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.res.AssetManager;
        import android.content.res.ColorStateList;
        import android.graphics.Bitmap;
        import android.graphics.Matrix;
        import android.graphics.Rect;
        import android.graphics.drawable.BitmapDrawable;
        import android.graphics.pdf.PdfRenderer;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.os.Bundle;
        import android.os.ParcelFileDescriptor;
        import android.support.v4.app.Fragment;
        import android.util.Base64;
        import android.view.Gravity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.webkit.WebView;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;
        import com.joanzapata.pdfview.PDFView;
        import com.joanzapata.pdfview.listener.OnPageChangeListener;

        import com.Pisystems.dofphonebook.R;

        import java.io.ByteArrayOutputStream;
        import java.io.File;
        import java.io.InputStream;
        import java.io.OutputStream;
        import java.util.ArrayList;
        import java.util.List;


public class FragmentHelp extends Fragment {

    public FragmentHelp() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    }
    MyDBHandler db ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_help, container, false);
        /*//Bitmap bitmap= FragmentSingleNotice.noticeImg;
        // Desired Bitmap and the html code, where you want to place it
        //Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
        // Bitmap bitmap = YOUR_BITMAP;.
        PDFView pdfView = (PDFView) rootView.findViewById(R.id.pdfview);

        pdfView.fromAsset("help.PDF")
                .pages(0, 1, 2)
                .defaultPage(1)
                .showMinimap(false)
                .enableSwipe(true)
                .swipeVertical(true)
                .load();*/

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onPause() {
        super.onPause();
        Runtime.getRuntime().gc();
    }
}

