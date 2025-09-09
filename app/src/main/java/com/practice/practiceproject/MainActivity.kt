package com.practice.practiceproject

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
    private val userViewmodel : UserViewModel by viewModels()

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
            UserData("Vijay",25,"B.Tech", "male"),
            UserData("Rahul",22,"M.Tech","male"),
            UserData("Pratik",25,"BSC","male"),
            UserData("Sai",85,"BSC","female"),
            UserData("Prasad",23,"MBBS","male"),
            UserData("Ravi",24,"MD","male"),
            UserData("SaiBai",85,"BSC","female"),
            UserData("Pawan",22,"B.Com","male"),
        )
        userAdapter= UserAdapter()
        binding.recyclerView.adapter=userAdapter
        binding.recyclerView.layoutManager= LinearLayoutManager(this)
        // binding.recyclerView.layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        // binding.recyclerView.layoutManager= GridLayoutManager(this, 2)
       // binding.recyclerView.layoutManager=  GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)


        userAdapter.submitList(userList)

         Handler(Looper.getMainLooper()).postDelayed({
            val newList = userAdapter.currentList.toMutableList()
            newList.add(UserData("Pooja", 23, "12th","female"))
             userAdapter.submitList(newList)
        }, 2000)

        Handler(Looper.getMainLooper()).postDelayed({
            val newList = userAdapter.currentList.toMutableList()
            newList.add(UserData("Babu", 43, "6th","female"))
            userAdapter.submitList(newList)
        }, 5000)

        userViewmodel.count.observe(this@MainActivity){ countValue ->
            binding.countTv.text= countValue.toString()
        }
        userViewmodel.increaseCounter()

    }
}