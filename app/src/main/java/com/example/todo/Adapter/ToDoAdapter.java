package com.example.todo.Adapter;

import android.app.assist.AssistStructure;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.AddNewTask;
import com.example.todo.MainActivity;
import com.example.todo.R;
import com.example.todo.Utils.DataBaseHelper;
import com.example.todo.model.TODOModel;

import java.util.ArrayList;

public class ToDoAdapter extends RecyclerView.Adapter <ToDoAdapter.MyViewHolder> {

    private ArrayList<TODOModel> mList;
    private MainActivity activity;
    private DataBaseHelper myDB;
    public ToDoAdapter(DataBaseHelper myDB,MainActivity activity){
        this.activity= activity;
        this.myDB= myDB;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()) .inflate(R.layout.task_layout,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final TODOModel item= (TODOModel) mList.get(position);
        holder.checkBox.setText(item.getTask());
        holder.checkBox.setChecked(toBoolean(item.getStatus()));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                AssistStructure.ViewNode compoundButton = null;
                if(compoundButton.isChecked()){

                    myDB.updateStatus(item.getId(),1);

                }else {
                    myDB.updateStatus(item.getId(), 0);
                }
            }
        });
    }
    public boolean toBoolean(int num){
         return num != 0;

    }

    public Context getContext(){
        return activity;
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }
    public void setTasks(ArrayList<TODOModel> mList){

        this.mList=mList;
        notifyDataSetChanged();
    }

    public void deleteTask(int position){
        TODOModel item = (TODOModel) mList.get(position);
        myDB.deleteTask(item.getId());

        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItems(int position){
        TODOModel item= (TODOModel) mList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("Id", item.getId());
        bundle.putString("task",item.getTask());


        AddNewTask task = new AddNewTask();
        task.setArguments(bundle);
        task.show(activity.getSupportFragmentManager(),task.getTag());
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        CheckBox checkBox;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
