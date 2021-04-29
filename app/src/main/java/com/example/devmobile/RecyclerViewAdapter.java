/*package com.example.devmobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Student> studentList;
    private OnItemClickListener listener;

    /*public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public RecyclerViewAdapter(List<Student> students, OnItemClickListener listener) {
        this.studentList = students;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return this.studentList.size();
    }

    public Student getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);
        return new RecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Student student = studentList.get(position);

        holder.image.setImageResource(R.drawable.ic_person);
        holder.firstName.setText(student.firstName);
        holder.lastName.setText(student.lastName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, position);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View itemView;
        final TextView firstName;
        final TextView lastName;
        final ImageView image;

        ViewHolder(View view) {
            super(view);
            itemView = view;
            firstName = view.findViewById(R.id.student_firstname);
            lastName = view.findViewById(R.id.student_lastname);
            image = view.findViewById(R.id.student_image);
        }
    }
}*/
