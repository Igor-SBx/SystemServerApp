package com.example.systemserver.ui.app_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.systemserver.databinding.AppItemBinding

// Classe que configura a RecyclerView
class AppListAdapter(private val context: Context, private val apps: List<String>): RecyclerView.Adapter<AppListAdapter.ViewHolder>() {

    // ViewHolder representa cada item dentro da RecyclerView
    class ViewHolder(private val appItemBinding: AppItemBinding): RecyclerView.ViewHolder(appItemBinding.root) {

        // A partir da lista de apps, obtém o nome e alterar o ViewHolder para mostrar na tela
        fun bind(app: String) {
            appItemBinding.appName.text = app
        }

    }

    // Configura a criação do ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AppItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    // O RecyclerView é otimizado para mostrar grandes volumes de dados, esse parâmetro ajuda na renderização eficiente dos items
    // conforme o usuário interage com o scroll
    override fun getItemCount(): Int = apps.size

    // Realiza o Bind para cada ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val app = apps[position]
        holder.bind(app)
    }

}