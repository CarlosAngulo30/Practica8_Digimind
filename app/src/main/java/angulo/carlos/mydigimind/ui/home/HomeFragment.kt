package angulo.carlos.mydigimind.ui.home

import android.os.Bundle
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import angulo.carlos.mydigimind.R
import angulo.carlos.mydigimind.databinding.FragmentHomeBinding
import angulo.carlos.mydigimind.ui.Task


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var adaptador: AdaptadorTareas? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    companion object {
        var tasks = ArrayList<Task>()
        var first = true
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root = inflater.inflate(R.layout.fragment_home,container,false)
        if (first==true){
            fillTasks()
            first = false
        }
        adaptador = AdaptadorTareas(root.context, tasks)

        val gridView: GridView = root.findViewById(R.id.reminders)
        gridView.adapter = adaptador

        return root
    }
    fun fillTasks(){
        tasks.add(Task("Practice 1", arrayListOf("Monday", "Sunday"), "17:30"))
        tasks.add(Task("Practice 2", arrayListOf("Tuesday"),"17:40"))
        tasks.add(Task("Practice 3", arrayListOf("Wednesday"),"14:00"))
        tasks.add(Task("Practice 4", arrayListOf("Saturday"),"11:00"))
        tasks.add(Task("Practice 5", arrayListOf("Friday"),"13:00"))
        tasks.add(Task("Practice 6", arrayListOf("Thursday"),"10:40"))
        tasks.add(Task("Practice 7", arrayListOf("Monday"),"12:00"))

    }
    override fun onResume() {
        super.onResume()
        tasks.clear()
        fillTasks()
    }

    private class AdaptadorTareas: BaseAdapter {
        var tasks = ArrayList<Task>()
        var contexto: Context?=null
        constructor(contexto: Context, tasks: ArrayList<Task>){
            this.contexto = contexto
            this.tasks = tasks
        }

        override fun getCount(): Int {
            return tasks.size
        }

        override fun getItem(p0: Int): Any {
            return tasks[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var task = tasks[p0]
            var inflador = LayoutInflater.from(contexto)
            var vista = inflador.inflate(R.layout.task_view,null)
            var tv_title: TextView = vista.findViewById(R.id.tv_title)
            var tv_time: TextView = vista.findViewById(R.id.tv_time)
            var tv_days: TextView = vista.findViewById(R.id.tv_days)
            tv_title.setText(task.title)
            tv_days.setText(task.days.toString())
            tv_time.setText(task.time)
            return vista
        }
    }
}