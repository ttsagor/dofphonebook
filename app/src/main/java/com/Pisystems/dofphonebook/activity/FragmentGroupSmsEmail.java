package com.Pisystems.dofphonebook.activity;


        import android.app.Activity;
        import android.content.Intent;
        import android.graphics.Typeface;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.Pisystems.dofphonebook.R;

        import java.util.ArrayList;
        import java.util.List;


public class FragmentGroupSmsEmail extends Fragment {

    public FragmentGroupSmsEmail() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public static List<String>  finalRecipient =  new ArrayList<String>();
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.group_sms_email, container, false);
        // Inflate the layout for this fragment
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
    public void onResume() {
        super.onResume();
        final MyDBHandler db = new MyDBHandler(getActivity(),null,null,1);

        TextView groupsmsemailtile = (TextView) rootView.findViewById(R.id.groupsmsemailtile);

        final Button groupsmsemailarea = (Button) rootView.findViewById(R.id.groupsmsemailarea);
        final Button groupsmsemaildeg = (Button) rootView.findViewById(R.id.groupsmsemaildeg);
        final Button groupsmsemailfav = (Button) rootView.findViewById(R.id.groupsmsemailfav);

        final Animation animationFadeInr = AnimationUtils.loadAnimation(getActivity(), R.anim.tanslater);
        final Animation animationFadeInlrr = AnimationUtils.loadAnimation(getActivity(), R.anim.tanslatelrr);
        final Animation animationFadeIntbr = AnimationUtils.loadAnimation(getActivity(), R.anim.translatetbr);
        final Animation animationFadeInbtr = AnimationUtils.loadAnimation(getActivity(), R.anim.translatebtr);


        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);

        groupsmsemailarea.setTypeface(tf);
        groupsmsemaildeg.setTypeface(tf);
        groupsmsemailfav.setTypeface(tf);
        groupsmsemailtile.setTypeface(tf);

        groupsmsemailfav.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (db.numberOfRowsFavouriteall() > 0) {

                    groupsmsemailfav.startAnimation(animationFadeInr);
                    //searchView.startAnimation(animationFadeInlrr);
                    groupsmsemaildeg.startAnimation(animationFadeInlrr);

                    groupsmsemailarea.startAnimation(animationFadeInr);
                    //home.setVisibility(LinearLayout.GONE);
                    animationFadeInr.setAnimationListener(new Animation.AnimationListener() {
                        public void onAnimationStart(Animation animation) {
                        }

                        public void onAnimationRepeat(Animation animation) {
                        }

                        public void onAnimationEnd(Animation animation) {
                            //home.setVisibility(LinearLayout.GONE);
                            Intent i = new Intent(getContext(), MainActivity.class);
                            i.putExtra("page_name", "groupsmsemailfavlist");
                            i.putExtra("favourite", 0);
                            //i.putExtra("favourite", 0);
                            startActivity(i);
                        }
                    });

                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "ফেবারিট লিস্টে কোন নাম্বার উপস্তিত নেই", Toast.LENGTH_LONG).show();
                }

            }
        });

        groupsmsemaildeg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                groupsmsemailfav.startAnimation(animationFadeInr);
                groupsmsemaildeg.startAnimation(animationFadeInlrr);

                groupsmsemailarea.startAnimation(animationFadeInr);
                animationFadeInr.setAnimationListener(new Animation.AnimationListener() {
                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        //home.setVisibility(LinearLayout.GONE);
                        Intent i = new Intent(getContext(), MainActivity.class);
                        i.putExtra("page_name", "groupsmsemaildesignwise");
                        startActivity(i);
                    }
                });
            }
        });

        groupsmsemailarea.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                groupsmsemailfav.startAnimation(animationFadeInr);
                groupsmsemaildeg.startAnimation(animationFadeInlrr);

                groupsmsemailarea.startAnimation(animationFadeInr);
                animationFadeInr.setAnimationListener(new Animation.AnimationListener() {
                    public void onAnimationStart(Animation animation) {
                    }

                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationEnd(Animation animation) {
                        //home.setVisibility(LinearLayout.GONE);
                        Intent i = new Intent(getContext(), MainActivity.class);
                        i.putExtra("page_name", "groupsmsemailareawise");
                        i.putExtra("area_id", 0);
                        startActivity(i);
                    }
                });
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        Runtime.getRuntime().gc();
    }
}
