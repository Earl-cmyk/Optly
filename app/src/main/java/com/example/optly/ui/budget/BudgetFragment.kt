package com.example.optly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.PieChart

class BudgetFragment : Fragment(R.layout.fragment_budget) {

    private lateinit var editFundsIn: EditText
    private lateinit var pieChart: PieChart
    private lateinit var editAmount: EditText
    private lateinit var spinnerAction: Spinner
    private lateinit var buttonLog: Button
    private lateinit var spinnerGoals: Spinner
    private lateinit var recyclerGoals: RecyclerView
    private lateinit var buttonAddGoal: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Bind views
        editFundsIn = view.findViewById(R.id.editFundsIn)
        pieChart = view.findViewById(R.id.pieChart)
        editAmount = view.findViewById(R.id.editAmount)
        spinnerAction = view.findViewById(R.id.spinnerAction)
        buttonLog = view.findViewById(R.id.buttonLog)
        spinnerGoals = view.findViewById(R.id.spinnerGoals)
        recyclerGoals = view.findViewById(R.id.recyclerGoals)
        buttonAddGoal = view.findViewById(R.id.buttonAddGoal)

        // Example: Show goals dropdown only when "Save" is selected
        spinnerAction.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selected = parent?.getItemAtPosition(position).toString()
                if (selected.equals("Save", ignoreCase = true)) {
                    spinnerGoals.visibility = View.VISIBLE
                } else {
                    spinnerGoals.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Example: Log button click
        buttonLog.setOnClickListener {
            val amount = editAmount.text.toString().toDoubleOrNull()
            if (amount != null) {
                Toast.makeText(requireContext(), "Logged $amount", Toast.LENGTH_SHORT).show()
                // TODO: update pieChart & recyclerGoals here
            }
        }

        // Example: Add goal button
        buttonAddGoal.setOnClickListener {
            Toast.makeText(requireContext(), "Add Goal clicked", Toast.LENGTH_SHORT).show()
            // TODO: open dialog to add goal
        }
    }
}
