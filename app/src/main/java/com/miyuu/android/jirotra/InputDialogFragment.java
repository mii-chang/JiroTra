package com.miyuu.android.jirotra;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class InputDialogFragment extends DialogFragment {


    String selectCall;
    int call;
    int selectPosition;
    int nowPosition;

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

        Dialog dialog = new Dialog(getActivity());
//        Dialog.Builder builder;

        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.dialog_input);
        Bundle args = getArguments();

        String title = args.getString("title");
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
        for (int i = 0; i < callList.length; i++) {
            arrayList.add(callList[i]);
        }

        Button positiveButton = dialog.findViewById(R.id.positive_button);
        Button negativeButton = dialog.findViewById(R.id.negative_button);
        TextView textView = dialog.findViewById(R.id.titleTextView);
        textView.setText(title);

        ListView listView = dialog.findViewById(R.id.listView);
//        ListAdapter adapter = new ListAdapter(this.getActivity(), R.layout.item_input, callList);
//        adapter.setList(arrayList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, callList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView list = (ListView) parent;
//                CheckBox checkBox = parent.findViewById(R.id.itemCheckBox);
//                checkBox.setChecked(true);
                Log.d("posi", position + "");
                selectCall = (list.getItemAtPosition(position).toString());
                selectPosition = position;
            }
        });

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
        return dialog;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Dialog dialog = getDialog();

        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int dialogWidth = (int) (metrics.widthPixels * 0.8);
        int dialogHeight = (int) (metrics.heightPixels * 0.6);

        lp.width = dialogWidth;
        lp.height = dialogHeight;
        dialog.getWindow().setAttributes(lp);
    }
}
