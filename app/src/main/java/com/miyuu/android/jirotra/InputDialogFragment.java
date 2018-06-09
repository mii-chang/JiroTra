package com.miyuu.android.jirotra;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class InputDialogFragment extends DialogFragment {
    AlertDialog dialog;
    AlertDialog.Builder builder;

    String selectCall;
    int call;
    int selectPosition;

    public interface OnDialogButtonClickListener {
        void onPositiveClick(int callType, String item, int position);

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

        switch (callType) {
            case 0:
                this.call = callType;
                callList = args.getStringArray("menList");
                break;
            default:
                this.call = callType;
                callList = args.getStringArray("masiList");
                break;
        }

        ArrayList<String> arrayList = new ArrayList<>();
        for (int i =0;i<callList.length;i++){
            arrayList.add(callList[i]);
        }


        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_input, null);
        Button positiveButton = dialogView.findViewById(R.id.positive_button);
        Button negativeButton = dialogView.findViewById(R.id.negative_button);

        ListView listView = dialogView.findViewById(R.id.listVIew);
        ListAdapter adapter = new ListAdapter(this.getActivity());
        adapter.setList(arrayList);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), R.layout.item_input, callList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView list = (ListView) parent;
                Log.d("posi", position + "");
                selectCall = (list.getItemAtPosition(position).toString());
                selectPosition = position;
            }
        });

        builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);


        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onPositiveClick(call, selectCall, selectPosition);
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
