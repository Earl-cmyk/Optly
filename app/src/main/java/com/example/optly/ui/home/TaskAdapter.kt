package com.example.optly.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.optly.R
import com.example.optly.data.Home // <-- your entity class

class TaskAdapter(
    private var tasks: MutableList<Home> = mutableListOf()
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskTitle: TextView = itemView.findViewById(R.id.textTaskTitle)
        val taskDescription: TextView = itemView.findViewById(R.id.textTaskDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.taskTitle.text = task.title
        holder.taskDescription.text = task.description
    }

    override fun getItemCount(): Int = tasks.size

    // Helper functions
    fun addTask(task: Home) {
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)
    }

    fun removeTask(position: Int) {
        if (position in tasks.indices) {
            tasks.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun setTasks(newTasks: List<Home>) {
        tasks.clear()
        tasks.addAll(newTasks)
        notifyDataSetChanged()
    }
}
