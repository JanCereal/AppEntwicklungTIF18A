package com.example.AppEntwicklungTIF18A

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MemberAdapter(memberList: ArrayList<String>, parent: ViewGroup?, categoryName : String) : RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {
    private var members = memberList
    private var context = parent?.context
    private var categoryName = categoryName

    inner class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var btnDelete = itemView.findViewById<ImageView>(R.id.imgDeleteMember)
        var categoryMember = itemView.findViewById<TextView>(R.id.txtMemberName)

        init {
            // Löschen eines Wortes
            btnDelete.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    IOClass.deleteWordFromCategory(context, categoryName, members[adapterPosition])
                    members.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.template_member, parent, false)
        return MemberViewHolder(view)
    }

    override fun getItemCount(): Int {
        return members.size
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.categoryMember.text = members[position]
    }
}