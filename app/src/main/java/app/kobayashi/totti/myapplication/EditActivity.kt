package app.kobayashi.totti.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.kobayashi.totti.myapplication.databinding.ActivityEditBinding
import app.kobayashi.totti.myapplication.databinding.ActivityMainBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        binding.checkButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()

            val resultIntent = Intent().apply {
                putExtra("title", title)
                putExtra("content", content)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
