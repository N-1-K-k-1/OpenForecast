package com.n1kk1.openforecast.ui.listcities

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.n1kk1.openforecast.R
import com.n1kk1.openforecast.model.database.City
import kotlinx.android.synthetic.main.activity_list_cities.*

class ListCitiesActivity : AppCompatActivity(), ListView {

    private lateinit var toolbar: androidx.appcompat.app.ActionBar
    private lateinit var searchManager: SearchManager
    private lateinit var searchItem: MenuItem
    private lateinit var searchView: SearchView
    private lateinit var presenter: ListPresenter
    private val cityAdapter = CityAdapter(
        this,
        onRemoveClicks = { city -> presenter.removeCity(city) },
        onCityClicks = {city ->  presenter.clickOnCity(city)}
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_cities)

        presenter = ListPresenter(this, this as ListView)

        /* Top action bar */
        toolbar = supportActionBar!!
        toolbar.title = getString(R.string.search)

        list_cities.layoutManager = LinearLayoutManager(this)
        list_cities.adapter = cityAdapter

        presenter.getAllCities()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)

        searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchItem = menu?.findItem(R.id.search)!!
        searchView = searchItem.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()
                query?.let {
                    presenter.addCity(it)
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }


        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun showListCities(cities: List<City>) {
        cityAdapter.setItems(cities)
    }

    override fun startWeatherActivity(city: String) {
        TODO("Not yet implemented")
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

}