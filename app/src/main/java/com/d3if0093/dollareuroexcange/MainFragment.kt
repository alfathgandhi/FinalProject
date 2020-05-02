package com.d3if0093.dollareuroexcange


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.isVisible

import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

import com.d3if0093.dollareuroexcange.`object`.Kurs
import com.d3if0093.dollareuroexcange.`object`.Rates
import com.d3if0093.dollareuroexcange.adapter.MyAdapter


import com.d3if0093.dollareuroexcange.database.ListNegaraDatabase

import com.d3if0093.dollareuroexcange.databinding.FragmentMainBinding
import com.d3if0093.dollareuroexcange.viewmodel.ExchangeViewModel
import com.d3if0093.dollareuroexcange.viewmodel.ExchangeViewModelFactory


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
        val dataSource=ListNegaraDatabase.getInstance(application).ListNegaraDAO

        val viewModelFactory = ExchangeViewModelFactory(
            dataSource,
            application
        )

       viewModel=ViewModelProviders.of(this,viewModelFactory).get(ExchangeViewModel::class.java)




        binding.setLifecycleOwner(this)



//        ApiRespone()

        setHasOptionsMenu(true)

        return binding.root }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.gagal.observe(viewLifecycleOwner, Observer {

            if(it==true){
                binding.us.visibility=View.VISIBLE
            }
        })

        viewModel.dataApi.observe(viewLifecycleOwner, Observer {
        kurs->
            var adapter = MyAdapter(kurs)
            binding.list.adapter =adapter



            viewModel.dataNya?.observe(viewLifecycleOwner, Observer{
                it?.let{
                    adapter.data = it

                }

            })




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



