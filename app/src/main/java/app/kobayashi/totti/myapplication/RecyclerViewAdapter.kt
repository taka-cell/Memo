package app.kobayashi.totti.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.kobayashi.totti.myapplication.databinding.ItemMemoCellBinding

class RecyclerViewAdapter(private val onItemClicked: (Memo) -> Unit) : ListAdapter<Memo, MemoViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val view = ItemMemoCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        val memo = getItem(position)
        holder.binding.memoTitle.text = memo.title
        holder.binding.memoContent.text = memo.content

        holder.itemView.setOnClickListener {
            onItemClicked(memo)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Memo>() {
            override fun areItemsTheSame(oldItem: Memo, newItem: Memo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Memo, newItem: Memo): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class MemoViewHolder(val binding: ItemMemoCellBinding) : RecyclerView.ViewHolder(binding.root)
