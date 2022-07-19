package com.example.myplanner.Company;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myplanner.R;

import java.util.ArrayList;

public class PriorityAdapter extends ArrayAdapter<CompanyModel> {

    public PriorityAdapter(Context context, ArrayList<CompanyModel> companyList) {
        super(context, 0, companyList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinnerpriority, parent, false
            );
        }
        TextView imgText = convertView.findViewById(R.id.imgText);
        ImageView imgSign = convertView.findViewById(R.id.imgSign);
        TextView txtCompany = convertView.findViewById(R.id.txtPriority);

        CompanyModel currentItem = getItem(position);

        if (currentItem != null) {
            txtCompany.setText(currentItem.getText());

            if (txtCompany.getText().toString().equals("Urgent")) {
                imgSign.setVisibility(View.GONE);
                imgText.setVisibility(View.VISIBLE);

            } else if (txtCompany.getText().toString().equals("High")) {
                imgSign.setBackground(getContext().getResources().getDrawable(R.drawable.ic_baseline_arrow_upward_24));
                imgSign.setVisibility(View.VISIBLE);
                imgText.setVisibility(View.GONE);
            } else if (txtCompany.getText().toString().equals("Medium")) {
                imgSign.setBackground(getContext().getResources().getDrawable(R.drawable.ic_baseline_notes_24));
                imgSign.setVisibility(View.VISIBLE);
                imgText.setVisibility(View.GONE);
            } else if (txtCompany.getText().toString().equals("Low")) {
                imgSign.setBackground(getContext().getResources().getDrawable(R.drawable.ic_baseline_arrow_downward_24));
                imgSign.setVisibility(View.VISIBLE);
                imgText.setVisibility(View.GONE);
            }
        }

        return convertView;
    }
}