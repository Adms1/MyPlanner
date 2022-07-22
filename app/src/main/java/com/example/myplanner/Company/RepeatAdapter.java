package com.example.myplanner.Company;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myplanner.Company.CompanyModel;
import com.example.myplanner.R;

import java.util.ArrayList;

public class RepeatAdapter extends ArrayAdapter<CompanyModel> {

    public RepeatAdapter(Context context, ArrayList<CompanyModel> companyList) {
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
        TextView txtCompany = convertView.findViewById(R.id.txtPriority);
        View addevent_line1 = convertView.findViewById(R.id.addevent_line1);


        CompanyModel currentItem = getItem(position);

        if (currentItem != null) {
            txtCompany.setText(currentItem.getText());
            if (txtCompany.getText().toString().equals("Please Select Repeat Or Not")) {
                addevent_line1.setVisibility(View.GONE);
            }
            else
            {
                addevent_line1.setVisibility(View.VISIBLE);
            }
        }

        return convertView;
    }
}