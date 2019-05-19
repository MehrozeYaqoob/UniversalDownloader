package com.mindvalley.assignment.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.mindvalley.assignment.R
import com.mindvalley.assignment.adapter.ItemAdapter
import com.mindvalley.assignment.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        TaskLoader.resizeCache(10 * 1024 * 1024)//Set cache size to 10MB

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        initRecyclerView()
        // Get Data from JSON
        fetchData()
        observeFloatingButton()
        observeSwipeRefereshAction()
        observePinBoard()
    }

    private fun initRecyclerView() {
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRecyclerView.adapter = ItemAdapter(this)
    }

    private fun fetchData() {
        viewModel.getDataFromServer()
    }

    private fun observePinBoard() {
        viewModel.repository.pinList.observe(this, Observer {
            (mainRecyclerView.adapter as ItemAdapter).sendList(it!!)
        })
    }

    private fun observeSwipeRefereshAction() {
        viewModel.isRefreshing.observe(this, Observer {
            swiper.isRefreshing = it!!
        })
    }

    private fun observeFloatingButton() {
        fab.setOnClickListener {
            (mainRecyclerView.adapter as ItemAdapter).sendList(viewModel.repository.pinList.value!!.shuffled())
            Toast.makeText(this, "Images shuffled!", Toast.LENGTH_SHORT).show()
        }
    }

}
