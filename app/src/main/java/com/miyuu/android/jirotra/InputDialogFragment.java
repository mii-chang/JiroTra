package com.miyuu.android.jirotra;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class InputDialogFragment extends DialogFragment {
    AlertDialog dialog;
    AlertDialog.Builder builder;

    public interface OnDialogButtonClickListener {
        void onPositiveClick(String item);

        void onNegativeClick();
    }

    private OnDialogButtonClickListener mListener;

    public static InputDialogFragment newInstance(String title, String message, String[] menList, String[] masiList, int callType) {
        InputDialogFragment fragment = new InputDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("message", message);
        bundle.putStringArray("menList", menList);
        bundle.putStringArray("masiList", masiList);
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
        String[] callList;
        int callType = args.getInt("callType");

        if (callType == 0) {
            //麺
            callList = args.getStringArray("menList");
        } else {
            callList = args.getStringArray("masiList");
        }
        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_input, null);
        Button positiveButton = dialogView.findViewById(R.id.positive_button);
        Button negativeButton = dialogView.findViewById(R.id.negative_button);

        ListView listView = dialogView.findViewById(R.id.listVIew);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, callList);
        listView.setAdapter(adapter);

        builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);

        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Listから選んだItemをListenerにわたす
//                mListener.onPositiveClick(totalPrice);
                dismiss();
            }
        });

        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                mListener.onNegativeClick();
                dismiss();
            }
        });

        builder.setView(dialogView);

        dialog = builder.create();
        dialog.show();
        return dialog;
    }
}
