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

public class companyAdapter1 extends ArrayAdapter<CompanyModel> {

    public companyAdapter1(Context context, ArrayList<CompanyModel> companyList) {
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
                    R.layout.spinnercompany, parent, false
            );
        }

        ImageView txtColor = convertView.findViewById(R.id.txtColor);
        TextView txtCompany = convertView.findViewById(R.id.txtCompany);

        CompanyModel currentItem = getItem(position);

        if (currentItem != null) {
            txtCompany.setText(currentItem.getText());

            if (txtCompany.getText().toString().equals("ADM")) {
                txtColor.setBackgroundColor(getContext().getResources().getColor(R.color.teal_A400));
                txtColor.setVisibility(View.VISIBLE);

            } else if (txtCompany.getText().toString().equals("ASL")) {
                txtColor.setBackgroundColor(getContext().getResources().getColor(R.color.green_400));
                txtColor.setVisibility(View.VISIBLE);

            } else if (txtCompany.getText().toString().equals("SRPL")) {
                txtColor.setBackgroundColor(getContext().getResources().getColor(R.color.yellow_900));
                txtColor.setVisibility(View.VISIBLE);

            } else if (txtCompany.getText().toString().equals("ULTRA")) {
                txtColor.setBackgroundColor(getContext().getResources().getColor(R.color.black));
                txtColor.setVisibility(View.VISIBLE);

            } else if (txtCompany.getText().toString().equals("GALACTIC")) {
                txtColor.setBackgroundColor(getContext().getResources().getColor(R.color.darkBlue));
                txtColor.setVisibility(View.VISIBLE);

            } else if (txtCompany.getText().toString().equals("PARCOTICS")) {
                txtColor.setBackgroundColor(getContext().getResources().getColor(R.color.red));
                txtColor.setVisibility(View.VISIBLE);

            } else if (txtCompany.getText().toString().equals("PERSONAL")) {
                txtColor.setBackgroundColor(getContext().getResources().getColor(R.color.pista));
                txtColor.setVisibility(View.VISIBLE);

            }
        }

        return convertView;
    }
}