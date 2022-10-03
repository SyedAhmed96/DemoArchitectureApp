package com.fictivestudios.demoarcitectureapp.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fictivestudios.demoarcitectureapp.R
import com.fictivestudios.demoarcitectureapp.data.model.response.user.User
import com.fictivestudios.demoarcitectureapp.databinding.ItemLayoutBinding

class MainAdapter(
    private val userList :ArrayList<User>
) : RecyclerView.Adapter<DataViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):DataViewHolder {
        val homeCard = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(homeCard)
    }




    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(userList.get(position))
    }

    override fun getItemCount(): Int = userList.size

}

class DataViewHolder(val itembinding: ItemLayoutBinding) : RecyclerView.ViewHolder(itembinding.root) {
    fun bind(user: User) {
        itembinding.imageViewAvatar.setImageResource(R.drawable.ic_launcher_background)

        //Glide.with(itemView.context).load(user.avatar).into(itembinding.imageViewAvatar)
        itembinding.textViewUserName.text = user.firstName +" "+user.lastName
        itembinding.textViewUserEmail.text = user.email


    }
}