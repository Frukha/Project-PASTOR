package com.example.projectchucknorris;

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView;

class JokeAdapter : RecyclerView.Adapter <JokeAdapter.JokeViewHolder>() {
    var list_jokes_adapter = listOf < String > ("Chuck Norris can run so fast, he can actually shoot an apple off of his own head",
        "Chuck Norris doesn't feel your pain... he causes it",
        "Chuck Norris uses pepper spray to spice up his steaks.",
        "god said let there be light Chuck Norris said say please",
        "when Chuck Norris is asleep the sheep count him",
        "You can see Chuck Norris's charisma from space.",
        "Chuck Norris' shadow follows him from a safe distance.",
        "There is no theory of evolution, just a list of creatures Chuck Norris allows to live.",
        "Chuck Norris never wet his bed as a child. The bed wet itself out of fear.",
        "Chuck Norris doesn't wear sunscreen when he is in the sun. The Sun wears Chuck Norris Screen.")

    class JokeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}
