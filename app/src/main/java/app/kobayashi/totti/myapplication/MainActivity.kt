package app.kobayashi.totti.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.kobayashi.totti.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var previousTextViewId: Int = R.id.textViewNotes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply{setContentView(this.root)}

        binding.buttonAdd.setOnClickListener {
            val memo = binding.editTextNote.text.toString()

            if (memo.isNotBlank()) {


                val newTextView = TextView(this).apply {
                    id = ViewCompat.generateViewId()
                    text = memo
                    textSize = 24f
                    setTextColor(resources.getColor(android.R.color.black, null))
                }

                binding.main.addView(newTextView)

                val constraintSet = ConstraintSet()
                constraintSet.clone(binding.main)
                constraintSet.connect(newTextView.id, ConstraintSet.TOP, previousTextViewId, ConstraintSet.BOTTOM, 16)
                constraintSet.connect(newTextView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 16)
                constraintSet.applyTo(binding.main)

                previousTextViewId = newTextView.id

                binding.editTextNote.text.clear()
            }
        }
    }
}