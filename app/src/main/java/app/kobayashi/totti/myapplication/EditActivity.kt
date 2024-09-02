package app.kobayashi.totti.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.kobayashi.totti.myapplication.databinding.ActivityEditBinding
import app.kobayashi.totti.myapplication.databinding.ActivityMainBinding

class EditActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater).apply{setContentView(this.root)}


    }
}