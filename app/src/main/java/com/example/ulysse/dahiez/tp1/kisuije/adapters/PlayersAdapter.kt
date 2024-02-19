// PlayersAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ulysse.dahiez.tp1.kisuije.R

class PlayersAdapter(private val players: List<String>) : RecyclerView.Adapter<PlayersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.player_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = players[position]
        holder.playerNameTextView.text = player
    }

    override fun getItemCount(): Int {
        return players.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerNameTextView: TextView = itemView.findViewById(R.id.playerNameTextView)
    }
}
