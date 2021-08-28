package com.example.Cartit.ui.cart

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.productit.ui.home.CartListItemViewHolder
import com.example.shopit.R
import com.example.shopit.data.cart.CartProductDataClass

class CartListAdapter : RecyclerView.Adapter<CartListItemViewHolder>() {
    var data = mutableListOf<CartProductDataClass>()
    var ctx: Context? = null

    var didClickCartAtPosition: ((Int) -> Int)? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartListItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var view = layoutInflater.inflate(R.layout.cart_list_item, parent, false)
        ctx = parent.context
        return CartListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartListItemViewHolder, position: Int) {
        holder.product = this.data[position]

        holder.itemView.setOnClickListener {
            Log.d(TAG, "Clicked Cart[$position]")

            clickedCartPosition = position
            this.didClickCartAtPosition?.let { f -> f(position) }
        }
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    fun update(modelList: MutableList<CartProductDataClass>) {
        data = modelList
        this.notifyDataSetChanged()
    }

    companion object {
        var clickedCartPosition: Int = 0
        private const val TAG = "CartIt-HomeListAdapter"
    }
}