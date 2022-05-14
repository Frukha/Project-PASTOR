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
        retrieveJoke()
        val button = findViewById<Button>(R.id.button_id)
        button.setOnClickListener{retrieveJoke()}
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }


    /*object Jokes {
        val list_jokes = listOf<String>(
            "Chuck Norris can run so fast, he can actually shoot an apple off of his own head",
            "Chuck Norris doesn't feel your pain... he causes it",
            "Chuck Norris uses pepper spray to spice up his steaks.",
            "god said let there be light Chuck Norris said say please",
            "when Chuck Norris is asleep the sheep count him",
            "You can see Chuck Norris's charisma from space.",
            "Chuck Norris' shadow follows him from a safe distance.",
            "There is no theory of evolution, just a list of creatures Chuck Norris allows to live.",
            "Chuck Norris never wet his bed as a child. The bed wet itself out of fear.",
            "Chuck Norris doesn't wear sunscreen when he is in the sun. The Sun wears Chuck Norris Screen."
        )
    }*/

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

    val adapter = JokeAdapter()


}