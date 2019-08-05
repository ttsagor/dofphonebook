package com.Pisystems.dofphonebook.activity;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import com.Pisystems.dofphonebook.R;


public class GroupSmsNewMessage extends Fragment {

    TextView GroupSmsNewMessageTotalFarme;
    View rootView;
    public GroupSmsNewMessage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_group_sms_new_message, container, false);


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
        GroupSmsNewMessageTotalFarme=(TextView) rootView.findViewById(R.id.GroupSmsNewMessageTotalFarmer);
        final ImageView GroupSmsNewMessageSend = (ImageView)  rootView.findViewById(R.id.GroupSmsNewMessageSend);
        final EditText GroupSmsNewMessageText = (EditText)  rootView.findViewById(R.id.GroupSmsNewMessageText);
        GroupSmsNewMessageTotalFarme.setText("সর্বমোট প্রাপক " + FragmentGroupSmsEmail.finalRecipient.size());
        final LinearLayout l = (LinearLayout) rootView.findViewById(R.id.feedbackfulllayout);


        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        });
        GroupSmsNewMessageSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setMessage("Are You Sure, You wanted to Send SMS");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                                if (FragmentGroupSmsEmail.finalRecipient.size() > 0 && GroupSmsNewMessageText.getText().length() > 0) {
                                    Intent i = new Intent(getActivity(), GroupSmsSending.class);
                                    i.putExtra("message", GroupSmsNewMessageText.getText().toString());
                                    startActivity(i);
                                }
                            }
                        });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialogBuilder.show();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        Runtime.getRuntime().gc();
    }
}
