package com.example.systemserver.ui.app_list

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.systemserver.databinding.FragmentAppListBinding
import com.example.systemserver.ui.home.HomeViewModel

// Fragment da listagem de Aplicativos instalados
class AppListFragment : Fragment() {

    // binding para o processo de inflate
    private var _binding: FragmentAppListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Log.d("AppListFragment", "onCreateView chamado")

        // Processo de inflate
        _binding = FragmentAppListBinding.inflate(inflater, container, false)
        var appList : List<String> = listOf<String>() // Lista para RecyclerView

        val context = activity?.applicationContext

        val activity = getActivity()

        if(activity != null){
            // Inicialização do PackageManager
            val packageManager = activity.packageManager
            val NO_FLAGS = 0

            // Obtenção dos Apps instalados conforme as permissões do aplicativo
            val installedApps = packageManager.getInstalledPackages(NO_FLAGS)

            // Iteração sobre todos os apps instalados
            for (appInfo in installedApps) {

                // Obtenção do nome do app
                val applicationInfo = packageManager.getApplicationInfo(appInfo.packageName, PackageManager.GET_META_DATA)
                val appName = packageManager.getApplicationLabel(applicationInfo).toString()

                // Inclusão na lista para display na RecyclerView
                appList = appList.plus(appName)
            }
        }

        if (context != null) {

            // Inicialização do Adapter para RecyclerView
            val adapter = AppListAdapter(
                context = context,
                apps = appList
            )

            // binding do adapter da RecyclerView
            binding.recyclerView.adapter = adapter
        }

        // Interação com a ViewModel para configurar a TextView
        val appListViewModel = ViewModelProvider(this).get(AppListViewModel::class.java)

        // Binding da TextView
        val textView: TextView = binding.recyclerViewText

        // Configuração da propriedade text
        appListViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return binding.root
    }

    override fun onDestroyView() {
        Log.d("AppListFragment", "onDestroyView chamado")

        super.onDestroyView()
        _binding = null
    }
}