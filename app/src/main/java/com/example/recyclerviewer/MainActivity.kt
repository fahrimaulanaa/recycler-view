package com.example.recyclerviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MainActivity : AppCompatActivity() {

    private var rvHeroes: RecyclerView? = null
    private var list: ArrayList<Hero>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvHeroes = findViewById(R.id.rv_hero)
        rvHeroes?.setHasFixedSize(true)

        list = arrayListOf()
        list?.addAll(HeroesData.listData)
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(itemId: Int) {
        when (itemId) {
            R.id.action_list -> {
                showRecyclerList()
            }
            R.id.action_grid -> {
                showRecyclerGrid()
            }
            R.id.action_cardview -> {
                showRecyclerCardView()
            }
        }

    }

    private fun showRecyclerCardView() {
        rvHeroes?.layoutManager = LinearLayoutManager(this)
        val cardViewHeroAdapter = CardViewHeroAdapter(list!!)
        rvHeroes?.adapter = cardViewHeroAdapter
    }

    private fun showRecyclerGrid() {
        rvHeroes?.layoutManager = GridLayoutManager(this, 2)
        val gridHeroAdapter = GridHeroAdapter(list!!)
        rvHeroes?.adapter = gridHeroAdapter
    }

    private fun showRecyclerList() {
        rvHeroes?.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list!!)
        rvHeroes?.adapter = listHeroAdapter
    }

}

class CardViewHeroAdapter(list: ArrayList<Hero>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listHero = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_hero, parent, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CardViewViewHolder).bind(listHero[position])
    }

    override fun getItemCount(): Int {
        return listHero.size
    }

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvFrom: TextView = itemView.findViewById(R.id.tv_item_from)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)

        fun bind(hero: Hero) {
            tvName.text = hero.name
            tvFrom.text = hero.from
            Glide.with(itemView.context)
                .load(hero.photo)
                .apply(RequestOptions().override(350, 550))
                .into(imgPhoto)
        }
    }

}

class GridHeroAdapter(list: ArrayList<Hero>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listHero = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_hero, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GridViewHolder).bind(listHero[position])
    }

    override fun getItemCount(): Int {
        return listHero.size
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvFrom: TextView = itemView.findViewById(R.id.tv_item_from)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)

        fun bind(hero: Hero) {
            tvName.text = hero.name
            tvFrom.text = hero.from
            Glide.with(itemView.context)
                .load(hero.photo)
                .apply(RequestOptions().override(350, 550))
                .into(imgPhoto)
        }
    }


}
