package app.kobayashi.totti.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import app.kobayashi.totti.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerViewAdapter
    private val list: MutableList<Memo> = mutableListOf()
    private var memoIdCounter = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        adapter = RecyclerViewAdapter { memo ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("id", memo.id)
                putExtra("title", memo.title)
                putExtra("content", memo.content)
            }
            startActivityForResult(intent, 3)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.addMemo.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                1 -> {
                    val title = data?.getStringExtra("title")
                    val content = data?.getStringExtra("content")
                    if (title != null && content != null) {
                        val newMemo = Memo(memoIdCounter++, title, content)
                        list.add(newMemo)
                        adapter.submitList(list.toMutableList())
                    }
                }
                3 -> {
                    val id = data?.getLongExtra("id", -1L) ?: return
                    val delete = data.getBooleanExtra("delete", false)
                    val title = data.getStringExtra("title")
                    val content = data.getStringExtra("content")

                    if (delete) {
                        list.removeAll { it.id == id }
                        adapter.submitList(list.toMutableList())
                    } else if (title != null && content != null) {
                        list.find { it.id == id }?.let { memoToEdit ->
                            memoToEdit.title = title
                            memoToEdit.content = content
                            adapter.submitList(list.toMutableList())
                        }
                    }
                }
            }
        }
    }
}
