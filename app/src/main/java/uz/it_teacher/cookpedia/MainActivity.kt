package uz.it_teacher.cookpedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import uz.it_teacher.cookpedia.adapter.CookAdapter
import uz.it_teacher.cookpedia.databinding.ActivityMainBinding
import uz.it_teacher.cookpedia.model.Cook

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var list = mutableListOf<Cook>()

        for (i in 1..20) {
            list.add(Cook("Salad", "12 450 recipes", R.drawable.img1))
            list.add(Cook("Burger", "8 450 recipes", R.drawable.img2))
            list.add(Cook("Pizza", "15 450 recipes", R.drawable.img3))
        }

        val adapter = CookAdapter(this, list)

        val touchHelper = object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = UP or DOWN or START or END
                val swipeFlags = START or END
                return makeMovementFlags(dragFlags, swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.onItemDismiss(viewHolder.adapterPosition)
            }

        }

        val itemTouchHelper = ItemTouchHelper(touchHelper)
        itemTouchHelper.attachToRecyclerView(binding.rv)
        binding.rv.adapter = adapter
    }
}