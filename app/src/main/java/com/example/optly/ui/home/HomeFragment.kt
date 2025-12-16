package com.example.optly.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.optly.R
import com.example.optly.data.AppDatabase
import com.example.optly.data.Home
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var adapterImportantUrgent: TaskAdapter
    private lateinit var adapterImportantNotUrgent: TaskAdapter
    private lateinit var adapterNotImportantUrgent: TaskAdapter
    private lateinit var adapterNotImportantNotUrgent: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Init adapters (use Home entity type)
        adapterImportantUrgent = TaskAdapter(mutableListOf())
        adapterImportantNotUrgent = TaskAdapter(mutableListOf())
        adapterNotImportantUrgent = TaskAdapter(mutableListOf())
        adapterNotImportantNotUrgent = TaskAdapter(mutableListOf())

        // RecyclerView setup
        view.findViewById<RecyclerView>(R.id.list_important_urgent).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterImportantUrgent
        }
        view.findViewById<RecyclerView>(R.id.list_important_not_urgent).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterImportantNotUrgent
        }
        view.findViewById<RecyclerView>(R.id.list_not_important_urgent).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterNotImportantUrgent
        }
        view.findViewById<RecyclerView>(R.id.list_not_important_not_urgent).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterNotImportantNotUrgent
        }

        // Buttons → Add tasks
        view.findViewById<Button>(R.id.btn_add_important_urgent).setOnClickListener {
            showAddTaskDialog(1, adapterImportantUrgent)
        }
        view.findViewById<Button>(R.id.btn_add_important_not_urgent).setOnClickListener {
            showAddTaskDialog(2, adapterImportantNotUrgent)
        }
        view.findViewById<Button>(R.id.btn_add_not_important_urgent).setOnClickListener {
            showAddTaskDialog(3, adapterNotImportantUrgent)
        }
        view.findViewById<Button>(R.id.btn_add_not_important_not_urgent).setOnClickListener {
            showAddTaskDialog(4, adapterNotImportantNotUrgent)
        }

        return view
    }

    // Dialog → Save new task
    private fun showAddTaskDialog(category: Int, adapter: TaskAdapter) {
        val input = EditText(requireContext())
        input.hint = "Enter task"

        AlertDialog.Builder(requireContext())
            .setTitle("Add Task")
            .setView(input)
            .setPositiveButton("Save") { _, _ ->
                val taskTitle = input.text.toString()
                if (taskTitle.isNotBlank()) {
                    // Map category → isImportant / isUrgent
                    val (important, urgent) = when (category) {
                        1 -> true to true   // Important + Urgent
                        2 -> true to false  // Important + Not Urgent
                        3 -> false to true  // Not Important + Urgent
                        4 -> false to false // Not Important + Not Urgent
                        else -> false to false
                    }

                    val task = Home(
                        title = taskTitle,
                        description = null,
                        isImportant = important,
                        isUrgent = urgent
                    )

                    lifecycleScope.launch {
                        val db = AppDatabase.getDatabase(requireContext())
                        db.homeDao().insert(task)
                        adapter.addTask(task)
                    }
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

}

