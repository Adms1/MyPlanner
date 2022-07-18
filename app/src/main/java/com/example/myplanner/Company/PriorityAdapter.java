/*
package com.example.myplanner.Company

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

import com.example.myplanner.R

import java.util.ArrayList

class PriorityAdapter(context:Context?, companyList:ArrayList<CompanyModel>) :
        ArrayAdapter<CompanyModel>(
        context!!,0,companyList!!
        ){
        override fun getView(position:Int,convertView:View?,parent:ViewGroup):View{
        return initView(position,convertView!!,parent)
        }

        override fun getDropDownView(position:Int,convertView:View,parent:ViewGroup):View{
        return initView(position,convertView,parent)
        }

private fun initView(position:Int,convertView:View,parent:ViewGroup):View{
        var convertView=convertView
        if(convertView==null){
        convertView=LayoutInflater.from(context).inflate(
        R.layout.spinnercompany,parent,false
        )
        }
        val txtColor=convertView.findViewById<ImageView>(R.id.txtColor)
        val txtCompany=convertView.findViewById<TextView>(R.id.txtCompany)
        val currentItem=getItem(position)
        if(currentItem!=null){
        txtCompany.text=currentItem.text
        if(txtCompany.text.toString()=="ADM"){
        txtColor.setBackgroundColor(context.resources.getColor(R.color.teal_A400))
        }else if(txtCompany.text.toString()=="ASL"){
        txtColor.setBackgroundColor(context.resources.getColor(R.color.green_400))
        }else if(txtCompany.text.toString()=="SRPL"){
        txtColor.setBackgroundColor(context.resources.getColor(R.color.yellow_A100))
        }else if(txtCompany.text.toString()=="ULTRA"){
        txtColor.setBackgroundColor(context.resources.getColor(R.color.black))
        }else if(txtCompany.text.toString()=="GALACTIC"){
        txtColor.setBackgroundColor(context.resources.getColor(R.color.darkBlue))
        }else if(txtCompany.text.toString()=="PARCOTICS"){
        txtColor.setBackgroundColor(context.resources.getColor(R.color.red))
        }else if(txtCompany.text.toString()=="PERSONAL"){
        txtColor.setBackgroundColor(context.resources.getColor(R.color.pista))
        }
        }else if(txtCompany.text.toString()=="Please Select Company"){
        txtColor.visibility=View.GONE
        }
        return convertView
        }
        }*/
