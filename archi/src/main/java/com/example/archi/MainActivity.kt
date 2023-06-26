package com.example.archi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.archi.data.api.RetrofitBuilder
import com.example.archi.data.model.User
import com.example.archi.databinding.ActivityMainBinding
import com.example.archi.intent.intent.MainIntent
import com.example.archi.intent.state.MainState
import com.example.archi.intent.viewmodel.MainViewModel
import com.example.archi.intent.viewmodel.ViewModelFactory
import com.example.archi.intent.viewmodel.ViewModelProviders
import com.example.archi.ui.adapter.MainAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private var adapter = MainAdapter(arrayListOf())

    private var vBinding:ActivityMainBinding?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBinding = ActivityMainBinding.inflate(LayoutInflater.from(this),window.decorView.findViewById(android.R.id.content),false)
        setContentView(vBinding?.root)
        setupUI()
        setupViewModel()
        observeViewModel()
        setupClicks()
    }

    private fun setupClicks() {
        vBinding?.buttonFetchUser?.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchUser)
            }

//            RetrofitBuilder.apiService.getUsers2().enqueue(object: Callback<ResponseBody> {
//                override fun onResponse(
//                    call: Call<ResponseBody>,
//                    response: Response<ResponseBody>
//                ) {
//                    var i =0
//                }
//
//                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                    var i =0
//                }
//
//            })
        }
    }


    private fun setupUI() {
        vBinding?.recyclerView?.layoutManager = LinearLayoutManager(this)
        vBinding?.recyclerView?.run {
            addItemDecoration(
                DividerItemDecoration(
                    vBinding?.recyclerView?.context,
                    (vBinding?.recyclerView?.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        vBinding?.recyclerView?.adapter = adapter
    }


    private fun setupViewModel() {
//        mainViewModel = ViewModelProviders.of(
//            this,
//            ViewModelFactory(
////                ApiHelperImpl(
//                    RetrofitBuilder.apiService
////                )
//            )
//        ).get(MainViewModel::class.java)

        mainViewModel = ViewModelFactory(

            RetrofitBuilder.apiService

        ).create(MainViewModel::class.java)
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            mainViewModel.state.collect {
                when (it) {
                    is MainState.Idle -> {

                    }
                    is MainState.Loading -> {
                        vBinding?.buttonFetchUser?.visibility = View.GONE
                        vBinding?.progressBar?.visibility = View.VISIBLE
                    }

                    is MainState.Users -> {
                        vBinding?.progressBar?.visibility = View.GONE
                        vBinding?.buttonFetchUser?.visibility = View.GONE
                        renderList(it.user)
                    }
                    is MainState.Error -> {
                        vBinding?.progressBar?.visibility = View.GONE
                        vBinding?.buttonFetchUser?.visibility = View.VISIBLE
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun renderList(users: List<User>) {
        vBinding?.recyclerView?.visibility = View.VISIBLE
        users.let { listOfUsers -> listOfUsers.let { adapter.addData(it) } }
        adapter.notifyDataSetChanged()
    }
}

