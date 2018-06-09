package com.miyuu.android.jirotra;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import java.util.ArrayList;

public class InputDialogFragment extends DialogFragment {

    public interface OnDialogButtonClickListener {
        void onPositiveClick(String item);
        void onNegativeClick();
    }

    private OnDialogButtonClickListener mListener;

    public static InputDialogFragment newInstance(String title, String message, ArrayList menList,ArrayList masiList,int callType) {
        InputDialogFragment fragment = new InputDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("message", message);
        bundle.putStringArrayList("menList", menList);
        bundle.putStringArrayList("masiList", masiList);
        bundle.putInt("callType", callType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnDialogButtonClickListener) getActivity();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
        String title = args.getString("title");
        String message = args.getString("message");
        ArrayList menList = args.getStringArrayList("menList");
        ArrayList masiList = args.getStringArrayList("masiList");
        int callType = args.getInt("callType");


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onPositiveClick("a");
                        dismiss();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                        mListener.onNegativeClick();
                        dismiss();
                    }
                });
        return builder.create();
    }
}
