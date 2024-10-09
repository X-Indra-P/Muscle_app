package com.saxony.muscleapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    //Menu untuk mengubah layout menjadi list atau grid
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvMuscle.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvMuscle.layoutManager = GridLayoutManager(this, 2)
            }
            R.id.about_page-> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    //Membuat menu pada bagian atas app bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    //inisialisasi
    private lateinit var rvMuscle : RecyclerView
    private val list = ArrayList<Muscle>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Inisialisasi RecyclerView dan atur ukuran
        rvMuscle = findViewById(R.id.rv_muscle)
        rvMuscle.setHasFixedSize(true)
        // Menampilkan semua otot di RecyclerView.
        list.addAll(getListMuscle())
        showRecyclerList()
    }
    //Mengambil data pada array dari resource
    private fun getListMuscle(): ArrayList<Muscle> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listMuscle = ArrayList<Muscle>()
        for (i in dataName.indices) {
            val muscle = Muscle(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listMuscle.add(muscle)
        }
        return listMuscle
    }
    // Menampilkan RecyclerView dengan daftar otot dan fungsi item click
    private fun showRecyclerList() {
        rvMuscle.layoutManager = LinearLayoutManager(this)
        val listMuscleAdapter = ListMuscleAdapter(list)
        rvMuscle.adapter = listMuscleAdapter

        listMuscleAdapter.setOnItemClickCallback(object : ListMuscleAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Muscle) {
                val moveIntent = Intent(this@MainActivity, DetailActivity::class.java)
                moveIntent.putExtra("extra_muscle", data)
                startActivity(moveIntent)
            }
        })
    }
    //Menampilkan dialog otot yang di pilih
    private fun showSelectedMuscle(muscle: Muscle) {
        Toast.makeText(this, "Kamu Memilih " + muscle.name, Toast.LENGTH_SHORT).show()
    }
}
