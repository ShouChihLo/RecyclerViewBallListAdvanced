package com.example.recyclerviewballlistadvanced

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewballlistadvanced.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // based on view binding to load the main layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // configure the recyclerView
        val layoutManager = LinearLayoutManager(this)
        //val layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = BallAdapter() { ball ->
            Toast.makeText(this, ball.name, Toast.LENGTH_SHORT).show()
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recyclerView.setHasFixedSize(true)

        // observe any changes on the data source
        viewModel.ballList.observe(this) {
            it?.let {
                adapter.submitList(it)
            }
        }

    }
}