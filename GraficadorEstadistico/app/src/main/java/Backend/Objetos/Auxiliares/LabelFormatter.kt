package Backend.Objetos.Auxiliares

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter

class LabelFormatter(etiquetas:Array<String>): ValueFormatter() {
    var etiquetas:Array<String> = etiquetas

    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        return etiquetas[value.toInt()]
    }
}