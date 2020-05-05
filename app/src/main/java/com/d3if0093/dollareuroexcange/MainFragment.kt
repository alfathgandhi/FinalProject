package com.d3if0093.dollareuroexcange


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.*
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

import com.d3if0093.dollareuroexcange.`object`.Rates
import com.d3if0093.dollareuroexcange.adapter.MyAdapter


import com.d3if0093.dollareuroexcange.databinding.FragmentMainBinding
import com.d3if0093.dollareuroexcange.viewmodel.ExchangeViewModel
import com.d3if0093.dollareuroexcange.viewmodel.ExchangeViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher


/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {
private lateinit var binding:FragmentMainBinding
private lateinit var viewModel:ExchangeViewModel



    private lateinit var rates: Rates



//    private val postApi= retrofit.create(RequestApi::class.java)
//    val respone = postApi.getAll()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_main, container, false)



        val application = requireNotNull(this.activity).application


        val viewModelFactory = ExchangeViewModelFactory(

            application
        )

       viewModel=ViewModelProviders.of(this,viewModelFactory).get(ExchangeViewModel::class.java)




        binding.setLifecycleOwner(this)



//        ApiRespone()

        setHasOptionsMenu(true)
        if(isNetworkConnected(activity=MainActivity())){
            viewModel.refresh()


        }else{
            Toast.makeText(activity,"Internet is not found. Showing cached data list", Toast.LENGTH_LONG).show()
        }



        return binding.root }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        viewModel.dataNya?.observe(viewLifecycleOwner, Observer{

                var adapter = MyAdapter()
                binding.list.adapter =adapter
                it?.let{
                    adapter.data = it

                }

        })














        super.onViewCreated(view, savedInstanceState)
    }


//    private fun ApiRespone() {
//
//        respone.enqueue(object : Callback<Kurs> {
//            override fun onFailure(call: Call<Kurs>, t: Throwable) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onResponse(call: Call<Kurs>, response: Response<Kurs>) {
//                val mRes=response.body()
//
//                 rates=mRes!!.rates!!
//                Log.i("HEYY","{${rates.USD.toString()}}")
//
//                adapter = MyAdapter()
//
//                binding.list.adapter =adapter
//
//                viewModel.dataNya?.observe(viewLifecycleOwner, Observer{
//                    it?.let{
//                        adapter.data = it
//
//                    }
//
//                })
//            }
//        })
//    }

    fun isNetworkConnected(activity: MainActivity):Boolean
    {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork=cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.hapus ->{
//                diaryViewModel.onClickHapus()
//                return true
//            }
//        }

        return NavigationUI.onNavDestinationSelected(item!!,view!!.findNavController()) || super.onOptionsItemSelected(item)
    }


}



