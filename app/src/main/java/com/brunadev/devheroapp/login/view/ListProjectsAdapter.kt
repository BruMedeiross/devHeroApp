package com.brunadev.devheroapp.login.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brunadev.devheroapp.R
import com.brunadev.devheroapp.login.data.model.Projects
import kotlinx.android.synthetic.main.item_projects.view.*

class ListProjectsAdapter(private val listProjects: List<Projects>, val onClick: (Projects) -> Unit) : RecyclerView.Adapter<ListProjectsAdapter.VideoHolder>() {
    private var characterList = emptyList<Projects>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder =
        VideoHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_projects,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = listProjects.size

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.bind(listProjects[position])
    }

    fun update(list: List<Projects>) {
        characterList = list
        notifyDataSetChanged()
    }

    inner class VideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(list: Projects) {
            with(itemView) {
                setOnClickListener {
                    onClick.invoke(list)
                }
                name_company.text = list.title
                email_company.text = list.desc
                hours_company.text = list.priority.toString()
                data_project.text = list.data.toString()
            }
        }
    }
}
