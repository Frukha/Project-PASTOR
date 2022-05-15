package com.example.projectchucknorris

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView


class JokeAdapter(val onBottomReached : () -> Unit) : RecyclerView.Adapter <JokeAdapter.JokeViewHolder>() {
    /*var list_jokes_adapter = listOf < String > ("Chuck Norris can run so fast, he can actually shoot an apple off of his own head.",
        "Chuck Norris doesn't feel your pain... he causes it.",
        "Chuck Norris uses pepper spray to spice up his steaks.",
        "God said let there be light Chuck Norris said say please.",
        "When Chuck Norris is asleep the sheep count him.",
        "You can see Chuck Norris's charisma from space.",
        "Chuck Norris' shadow follows him from a safe distance.",
        "There is no theory of evolution, just a list of creatures Chuck Norris allows to live.",
        "Chuck Norris never wet his bed as a child. The bed wet itself out of fear.",
        "Chuck Norris doesn't wear sunscreen when he is in the sun. The Sun wears Chuck Norris Screen.")*/

    //var list_jokes_adapter_joke : List<Joke> = list_jokes_adapter.map{Joke(value = it)}


    var listOfJoke = listOf<Joke>()

    fun addJoke(joke: Joke) {
        listOfJoke = listOfJoke + joke
        notifyDataSetChanged()
    }

    class JokeViewHolder(val constraintLayout: ConstraintLayout) : RecyclerView.ViewHolder(constraintLayout){
        val textView : TextView = constraintLayout.findViewById<TextView>(R.id.jokes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val view: ConstraintLayout = LayoutInflater.from(parent.context).inflate(R.layout.joke_layout, parent, false) as ConstraintLayout
        return (JokeViewHolder(view))
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val joke = listOfJoke[position]
        holder.textView.text = joke.value

        if (position == listOfJoke.lastIndex && position >=9) {
            onBottomReached()
        }

    }

    override fun getItemCount(): Int = listOfJoke.size

}
