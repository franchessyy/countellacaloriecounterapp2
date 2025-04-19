package com.example.countellacaloriecounterapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.countellacaloriecounterapp.R

class MainActivity : AppCompatActivity() {

    private var totalCalories = 0
    private val calorieGoal = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Views for input fields and text displays
        val foodInput = findViewById<EditText>(R.id.foodInput)
        val calorieInput = findViewById<EditText>(R.id.calorieInput)
        val addButton = findViewById<Button>(R.id.addButton)
        val totalCaloriesText = findViewById<TextView>(R.id.totalCaloriesText)
        val statusText = findViewById<TextView>(R.id.statusText)
        val exerciseButton = findViewById<Button>(R.id.exerciseButton)

        // Adding calories based on user input
        addButton.setOnClickListener {
            val calorieText = calorieInput.text.toString()

            if (calorieText.isNotEmpty()) {
                val calories = calorieText.toIntOrNull()

                if (calories != null) {
                    totalCalories += calories
                    totalCaloriesText.text = getString(R.string.total_calories, totalCalories)

                    // Update status based on total calories
                    statusText.text = if (totalCalories < calorieGoal) {
                        getString(R.string.calorie_deficit)
                    } else {
                        getString(R.string.calorie_goal_reached)
                    }

                    foodInput.text.clear()
                    calorieInput.text.clear()
                } else {
                    Toast.makeText(this, getString(R.string.invalid_calories), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, getString(R.string.empty_field), Toast.LENGTH_SHORT).show()
            }
        }

        // Exercise button to deduct calories
        exerciseButton.setOnClickListener {
            val exerciseCalorieInput = findViewById<EditText>(R.id.exerciseInput)
            val exerciseCaloriesText = exerciseCalorieInput.text.toString()

            if (exerciseCaloriesText.isNotEmpty()) {
                val exerciseCalories = exerciseCaloriesText.toIntOrNull()

                if (exerciseCalories != null) {
                    // Deduct calories based on exercise
                    totalCalories -= exerciseCalories

                    totalCaloriesText.text = getString(R.string.total_calories, totalCalories)

                    // Show updated status after deducting exercise calories
                    statusText.text = if (totalCalories < calorieGoal) {
                        getString(R.string.calorie_deficit)
                    } else {
                        getString(R.string.calorie_goal_reached)
                    }

                    exerciseCalorieInput.text.clear() // Clear the exercise input field
                } else {
                    Toast.makeText(this, getString(R.string.invalid_exercise_calories), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, getString(R.string.empty_field), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
