package com.example.systemserver

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.systemserver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    // Evento de criação da Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate chamado")

        // Binding para o processo de inflate
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Configura o conteúdo da Activity View
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        // Configuração da navegação através do NavController
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_app_list
            ), drawerLayout
        )

        // Configura o NavController com a barra de navegação
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    // Configura as opções padrão da Activity View
    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        Log.d("MainActivity", "onCreateOptionsMenu chamado")

        // Inflater do menu
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    // Método executado toda vez que um evento de navegação ocorre
    override fun onSupportNavigateUp(): Boolean {
        Log.d("MainActivity", "onSupportNavigateUp chamado")

        // Referencia o NavController com o NavHost
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart chamado")
    }

    override fun onResume(){
        super.onResume()
        Log.d("MainActivity", "onResume chamado")
    }

    override fun onPause(){
        super.onPause()
        Log.d("MainActivity", "onPause chamado")
    }

    override fun onStop(){
        super.onStop()
        Log.d("MainActivity", "onStop chamado")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d("MainActivity", "onDestroy chamado")
    }

}