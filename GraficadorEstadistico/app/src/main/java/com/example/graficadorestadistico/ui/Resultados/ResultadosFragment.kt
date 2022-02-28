package com.example.graficadorestadistico.ui.Resultados

import Backend.Objetos.Graficas.Barras
import Backend.Objetos.Graficas.Grafica
import Backend.Objetos.Graficas.Pie
import Backend.Objetos.Reportes.Reporte
import Backend.Objetos.Reportes.ReporteError
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.setFragmentResultListener
import com.example.graficadorestadistico.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate
import Backend.Objetos.Auxiliares.LabelFormatter
import android.widget.TableRow
import androidx.core.view.size

class ResultadosFragment:Fragment() {
    private lateinit var linearLayout:LinearLayout
    private var tvw_msje:TextView? = null

    private var hubieronErrores:Boolean = true
    private lateinit var graficas:ArrayList<Grafica>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //if(savedInstanceState!=null){
            recibirInformacion()//puesto que por la forma de trabajo, siempre le enviaremos un bundle xD
        //}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_resultado, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.linearLayout = view.findViewById(R.id.linearLayout_Resultados)
        this.linearLayout.removeAllViewsInLayout()//si da error add un if linearLayout.getChildCount()>0 y cblo por un removeAllViews, si el InLayout xd
        if(this.tvw_msje!= null && (this.tvw_msje as TextView).parent != null){
            ((this.tvw_msje as TextView).parent as ViewGroup).removeView(this.tvw_msje)
        }

        this.tvw_msje = TextView(context)

    }

    private fun recibirInformacion(){
        Log.i("Recepcion_resultados", "iniciada")
        parentFragmentManager.setFragmentResultListener("resultados", this, FragmentResultListener(){resultados, bundle ->
            //override fun ()//no me deja crear un método llamado onFragmentResult, que es lo único que me haría falta si me baso en los 2 tuto...

            hubieronErrores = bundle.getBoolean("errores")
            Log.i("Recepcion_resultados", "terminada")

            if(!hubieronErrores){
                this.graficas = bundle.getSerializable("graficas") as ArrayList<Grafica>

                generarGraficas()
                pasarInformacion_Reportes((bundle.getSerializable("reporteGraficasDefinidas") as IntArray),
                    (bundle.getSerializable("reporteOperaciones") as ArrayList<Reporte>))//yo diría que aquí, pues en este punto ya habrá terminado all o que debe hacer este fragment...
            }else{
                this.tvw_msje?.text = "Nada que graficar"
                this.linearLayout.addView(this.tvw_msje)
                pasarInformacionErrados((bundle.getSerializable("reporteErrores") as ArrayList<ReporteError>))
            }

            Log.i("Set_resultados", "terminado")
        })//tengo que ver como llenar ese FragmentResultListener...
    }

    private fun pasarInformacion_Reportes(reporteGraficasDefinidas:IntArray, reporteOperaciones:ArrayList<Reporte>){
        var bundle:Bundle = Bundle()

        bundle.putSerializable("reporteGraficasDefinidas", reporteGraficasDefinidas)
        bundle.putSerializable("reporteOperaciones", reporteOperaciones)
        bundle.putBoolean("errores", false)

        parentFragmentManager.setFragmentResult("resultados_reportes", bundle)
        Log.i("resultados pasados", "se ha bundleado to rep xD")
    }

    private fun pasarInformacionErrados(errores:ArrayList<ReporteError>){
        var bundle:Bundle = Bundle()

        bundle.putSerializable("reporteErrores", errores)
        bundle.putBoolean("errores", true)

        parentFragmentManager.setFragmentResult("resultados_reportes", bundle)//que irá a pasr por haberle dejado el mismo nombre?? se add a los demás y en el caso del boolean, reemplazaría o devolvera un error por el simple hecho de llamarse igual, o no sucederá nada puesto que ese bundle ya desapareció...???
        Log.i("resultados errores", "se han bundleado to rep xD")
    }

    private fun generarGraficas(){
        var numeroGrafica:Int = 1

        for(grafica in this.graficas){
            when(grafica){
                is Barras -> this.linearLayout.addView(generarGraficoBarras(grafica, numeroGrafica), this.linearLayout.width, 1200)
                is Pie -> this.linearLayout.addView(generarGraficoPie(grafica, numeroGrafica), this.linearLayout.width, 1200)
            }
            numeroGrafica++
        }
    }

    private fun generarGraficoBarras(grafica: Barras, numeroGrafica:Int):BarChart{
        var barChart: BarChart = BarChart(context)

        var datos:ArrayList<BarEntry> = this.reunirDatos_Barras(grafica)

        var barDataSet:BarDataSet = BarDataSet(datos, grafica.titulo)
        barDataSet.setColors(ColorTemplate.createColors(ColorTemplate.JOYFUL_COLORS))//de seguro hay 0 xD
        barDataSet.setValueTextColor(Color.BLACK)
        barDataSet.valueTextSize = 16f

        var barData:BarData = BarData(barDataSet)

        barChart.xAxis.valueFormatter = LabelFormatter(grafica.ejeX)

        barChart.setFitBars(true)
        barChart.data = barData
        barChart.description.text = "Grafica #"+numeroGrafica.toString()
        barChart.animateY(2000)

        return barChart
    }

    private fun reunirDatos_Barras(grafica:Barras):ArrayList<BarEntry>{
        var datos:ArrayList<BarEntry> = ArrayList()

        for (elemento in grafica.ejeX.indices){
            datos.add(BarEntry((elemento+1).toFloat(),//aquí van números ordinales, para indicar si las barras aparecerán una la par de otra xD
                (grafica.ejeY.get(elemento)).toFloat()))
        }
        return datos
    }

    private fun generarGraficoPie(grafica: Pie, numeroGrafica:Int):PieChart{
        //var pieChart:PieChart = LayoutInflater.from(context).inflate(R.id, null, false) as PieChart
        var pieChart: PieChart = PieChart(context)
        pieChart.holeRadius = 35f

        var datos:ArrayList<PieEntry> = this.reunirDatos_Pie(grafica)

        var pieDataSet:PieDataSet = PieDataSet(datos, grafica.titulo)
        pieDataSet.setColors(ColorTemplate.createColors(ColorTemplate.PASTEL_COLORS))//de seguro hay 0 xD
        pieDataSet.setValueTextColor(Color.BLACK)
        pieDataSet.valueTextSize = 16f

        var pieData:PieData = PieData(pieDataSet)

        pieChart.data = pieData
        if(grafica.tipo == "Porcentaje"){
            pieChart.setUsePercentValues(true)
        }
        pieChart.description.text = "Grafica #"+numeroGrafica.toString()
        pieChart.centerText = grafica.titulo
        pieChart.animate()

        return pieChart
    }

    private fun reunirDatos_Pie(grafica:Pie):ArrayList<PieEntry>{
        var datos:ArrayList<PieEntry> = ArrayList()
        var sumatoria:Double = 0.0 //ayuda a hacer el cálculo del sobrante para así emplear la etiqueta especificada si en dado caso fuera nec xD

        for (elemento in grafica.valores.indices){
            var cantidadActual:Double =(if (grafica.tipo == "Cantidad") grafica.valores.get(elemento)
            else (360*((grafica.valores.get(elemento))/100)))

            datos.add(PieEntry(cantidadActual.toFloat(),
                ((grafica).etiquetas[elemento])))

            sumatoria += cantidadActual
        }

        if((grafica.total - sumatoria) > 0){
            datos.add(PieEntry((grafica.total-sumatoria).toFloat(), grafica.extra))
        }

        return datos
    }


    //Pasos que hiciste para que funcionara el envío de datos
    //1. usaste parentFragmenteManager antes de tener setFragmentResultListener y luego en lugar de
    // no tener nada en los parám, le enviaste: requestKey, lyfeCycleOwner (this), FragmentResultListener
    //al cual declaraste como cuerpo exactamente lo que tenías como cuerpo general originalmente
    //2. hiciste que recibir info ya no recibiera un bundle [pues no se usaba xD :v]
    //en el OnCreate comentaste la revisión de si el bundle [savedInstance!= null], pues por esa razón
    //nunca entraba a ese método...
}