package com.pedroroig.fragmentssampleapp.features.users


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pedroroig.fragmentssampleapp.R
import kotlinx.android.synthetic.main.user_list_row_item.view.*

/**
 * [RecyclerView.Adapter] that can display a user and makes a call to the
 * specified [clickListener].
 */
class UserListRecyclerViewAdapter(
    private var values: List<String>,
    private val clickListener: (String) -> Unit
) : RecyclerView.Adapter<UserListRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = values[position]
        holder.mIdView.text = user

        with(holder.rootView) {
            setOnClickListener {
                clickListener(user)
            }
        }
    }

    override fun getItemCount(): Int = values.size

    fun updateValues(newValues: List<String>) {
        values = newValues
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rootView = view
        val mIdView: TextView = view.text_user_item_name
    }
}
