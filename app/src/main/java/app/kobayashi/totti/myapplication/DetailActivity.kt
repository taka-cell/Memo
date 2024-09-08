package app.kobayashi.totti.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.kobayashi.totti.myapplication.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val id = intent.getLongExtra("id", -1L)
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")

        binding.memoTitle.text = title
        binding.memoContent.text = content

        binding.editButton.setOnClickListener {
            val editIntent = Intent(this, EditActivity::class.java).apply {
                putExtra("title", title)
                putExtra("content", content)
            }
            startActivityForResult(editIntent, 2)
        }

        binding.deleteButton.setOnClickListener {
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra("id", id)
                putExtra("delete", true)
            })
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            val editedTitle = data?.getStringExtra("title")
            val editedContent = data?.getStringExtra("content")

            binding.memoTitle.text = editedTitle
            binding.memoContent.text = editedContent

            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra("id", intent.getLongExtra("id", -1L))
                putExtra("title", editedTitle)
                putExtra("content", editedContent)
            })
            finish()
        }
    }
}

