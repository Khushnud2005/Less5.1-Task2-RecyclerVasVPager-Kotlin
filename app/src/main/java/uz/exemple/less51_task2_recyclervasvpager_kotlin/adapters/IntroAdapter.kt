package uz.exemple.less51_task2_recyclervasvpager_kotlin.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import uz.exemple.less51_task2_recyclervasvpager_kotlin.MainActivity
import uz.exemple.less51_task2_recyclervasvpager_kotlin.R
import uz.exemple.less51_task2_recyclervasvpager_kotlin.models.IntroModel

class IntroAdapter(var intros:List<IntroModel>, var context: Context) : RecyclerView.Adapter<IntroAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_intro,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val intro = intros[position]
        holder.title.setText(intro.title)
        holder.desc.setText(intro.desc)
        holder.lottie.setAnimation(intro.lottie)
        if (position == 2) {
            holder.btn_start.visibility = View.VISIBLE
            holder.skip.visibility = View.GONE
            holder.tv_circle2.setBackgroundResource(R.drawable.circle_brushed)
            holder.tv_circle0.setBackgroundResource(R.drawable.circle)
        } else if (position == 1){

            holder.tv_circle0.setBackgroundResource(R.drawable.circle)
            holder.tv_circle1.setBackgroundResource(R.drawable.circle_brushed)
        }
        else {
            holder.btn_start.visibility = View.GONE
            holder.skip.visibility = View.VISIBLE
        }

        holder.skip.setOnClickListener { openMainActivity(holder.itemView.context) }
        holder.btn_start.setOnClickListener { openMainActivity(holder.itemView.context) }
    }

    override fun getItemCount(): Int {
        return intros.size
    }

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        lateinit var title: TextView
        lateinit var desc:TextView
        lateinit var skip:TextView
        lateinit var lottie: LottieAnimationView
        lateinit var btn_start: Button
        lateinit var context: Context
        lateinit var tv_circle0: TextView
        lateinit var tv_circle1: TextView
        lateinit var tv_circle2: TextView
        init {
            title = itemView.findViewById(R.id.tv_title_f1)
            desc = itemView.findViewById(R.id.tv_desc_f1)
            lottie = itemView.findViewById(R.id.lottie_intro)
            skip = itemView.findViewById(R.id.tv_skip_f1)
            btn_start = itemView.findViewById(R.id.btn_start)
            context = itemView.context
            tv_circle0 = itemView.findViewById(R.id.tv_circle_0)
            tv_circle1 = itemView.findViewById(R.id.tv_circle_1)
            tv_circle2 = itemView.findViewById(R.id.tv_circle_2)
        }
    }

    fun openMainActivity(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
        (context as Activity).finish()
    }
}