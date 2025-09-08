package com.practice.practiceproject

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.practiceproject.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val userList : MutableList<UserData> =
        mutableListOf(
            UserData("Vijay",25,"B.Tech"),
            UserData("Rahul",22,"M.Tech"),
            UserData("Pratik",25,"BSC"),
            UserData("Prasad",23,"MBBS"),
            UserData("Ravi",24,"MD"),
            UserData("Pawan",22,"B.Com"),
        )
        userAdapter= UserAdapter()
        binding.recyclerView.adapter=userAdapter
        binding.recyclerView.layoutManager= LinearLayoutManager(this)
        // binding.recyclerView.layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        // binding.recyclerView.layoutManager= GridLayoutManager(this, 2)
       // binding.recyclerView.layoutManager=  GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)

//        lifecycleScope.launch {
//            delay(2000)
//            userList.add(UserData("Pooja",23, "12th"))
//            userAdapter.updateList(userList)
//        }
//        Handler(Looper.getMainLooper()).postDelayed({
//            userList.add(UserData("Pooja",23, "12th"))
//            userAdapter.updateList(userList)
//        },2000)
//        Handler(Looper.getMainLooper()).postDelayed({
//            userAdapter.addUser(UserData("Pooja", 23, "12th"))
//        }, 2000)
        userAdapter.submitList(userList)

         Handler(Looper.getMainLooper()).postDelayed({
            val newList = userAdapter.currentList.toMutableList()
            newList.add(UserData("Pooja", 23, "12th"))
             userAdapter.submitList(newList)
        }, 2000)

        Handler(Looper.getMainLooper()).postDelayed({
            val newList = userAdapter.currentList.toMutableList()
            newList.add(UserData("Babu", 43, "6th"))
            userAdapter.submitList(newList)
        }, 5000)

    }
}