package com.example.projectchucknorris

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Single
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.list_)
        recyclerView.adapter = adapter
        if(savedInstanceState == null)
            retrieveJoke()
        //val button = findViewById<Button>(R.id.button_id)
        //button.setOnClickListener{retrieveJoke()}

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    override fun onSaveInstanceState(outState: Bundle) { // Here You have to save count value
        super.onSaveInstanceState(outState)
        //Log.d("MyTag", "onSaveInstanceState")
        val listOfJokeSerialized = Json.encodeToString(adapter.listOfJoke)
        outState.putString(key, listOfJokeSerialized)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) { // Here You have to restore count value
        super.onRestoreInstanceState(savedInstanceState)
        //Log.d("MyTag", "onRestoreInstanceState")
        val savedString = savedInstanceState.getString(key)!!
        val listOfJokeDeserialized: List<Joke> = Json.decodeFromString(savedString)
        adapter.listOfJoke = listOfJokeDeserialized
    }

    private fun retrieveJoke(){
        val progressBar = findViewById<ProgressBar>(R.id.progressBar_id)
        progressBar.visibility = View.VISIBLE
        jokeService.subscribeOn(Schedulers.io()).repeat(10).delay(1000, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
            onError = { Log.d("error", "error detected")
                progressBar.visibility = View.INVISIBLE},
            onNext = {
                Log.d("success", "The joke is: ${it.value}")
                adapter.addJoke(it)
                progressBar.visibility = View.INVISIBLE
            },
            onComplete = {
                Log.d("finished", "10 jokes were shown")
            }
        ).also { compositeDisposable.add(it) }
    }

    val compositeDisposable = CompositeDisposable()

    val jokeService: Single<Joke> = JokeApiServiceFactory.builderService().giveMeAJoke()

    val adapter = JokeAdapter(onBottomReached = {retrieveJoke()})

    val key = "KeyToList"

}